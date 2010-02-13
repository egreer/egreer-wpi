'''
Created on Dec 29, 2009

@author: Eric Greer
'''
from views import Templates 
from google.appengine.ext import db
from entities import UserStory as story
from managers import mUserStory as mstory

class UserStory(object):
    '''
    The User Story page creator
    '''
    manageTemplate = Templates.Templates()
    managepage = ''


    def writeStory(self, success, error, currUser, mode, key):
        '''
        Creates the string representing the story page
        @param success: The success message to print on the page
        @param error: The error message to print on the page
        @param currUser: The current username to manage
        @param mode: The mode of the page (Create | Edit | Delete | Estimate) 
        @param key: The key of the user story (from DB) 
        @return: Returns a string representing the user story page 
        '''
        currentStory = None
        
        if mode == "Edit" or mode == "Delete" or mode == "Estimate" or mode == "View":
            stories = db.GqlQuery("SELECT * FROM UserStory WHERE __key__ = :1", db.Key(key))
            if stories.count(5) == 1:
                currentStory = stories.get();
            else:
                mode = "Create"
        
        if currentStory == None:
                currentStory = story.UserStory(title = '', description = '', testNotes = '', creator = None);
        
        readOnly = ''
        estReadOnly = ''
        estimateValue = ''
        manager = mstory.mUserStory()
        
        if mode == "Estimate":
            
            theEst = manager.RetrieveEstimate(key, currUser)
            
            if theEst != None:
                estimateValue = str(theEst.estimate)
            
        if (not currentStory.editable) or mode == 'Delete' or mode == 'View' or mode == 'Estimate':
            readOnly = 'disabled=\"disabled\"'
        
        if mode != 'Estimate' or currentStory.finalEstimate != None:
            estReadOnly = 'disabled=\"disabled\"'
             
            
        self.managepage += self.manageTemplate.SetTitle(mode + ' User Story')
        self.managepage += self.manageTemplate.SetHeaderLinks('''<li class="first"><a href="/UserStories">User Stories</a></li>
                <li class="active"><a href="/UserStory?type=Create">New User Story</a></li>
                <li><a href="/UserManagement.jsp">User Management</a></li>
                <li><a href="/Logout">Logout</a></li>
                ''')
        self.managepage += self.manageTemplate.contentStart
        
        
        self.managepage += '''
                    <!-- User Story Form  -->
                    <form action="/UserStory" method="post" class="f-wrap-1" onsubmit="return '''
                    
        if mode == "Estimate":
            self.managepage += 'checkEstimate(this);">'
        else:            
            self.managepage += 'checkUserStory(this);">'
                
        self.managepage += '''
                    <div class="req"><b>*</b> Indicates required field</div>
                
                    <fieldset>
                
                    <h3><%= pageType %> User Story</h3>
                  '''
        self.managepage += self.manageTemplate.SetMessages(success, error)

        #Final Estimate
        if currentStory.finalEstimate != None:
            self.managepage += '<span class="totalEstimate"><b id="totalEstimateTop">Total Estimated Units: ' + str(currentStory.finalEstimate) +'</b></span>'
        
                            
        #Title Field
        self.managepage += '<label for="title"><b><span class="req">*</span>Title:</b>'
        self.managepage += '<input ' + readOnly + ' id="title" name="title" type="text" class="f-name" tabindex="1" value="'+ currentStory.title + '" /><br /></label>'
                    
        #Description field
        self.managepage += '<label for="description"><b><span class="req">*</span>Description:</b> <textarea ' + readOnly + ' id="description" name="description" class="f-comments" rows="6" cols="20" tabindex="2">'
        self.managepage += currentStory.description + '</textarea><br /></label>'
                    
        #Test Notes Field
        self.managepage += '<label for="testnotes"><b>Test Notes:</b><textarea ' + readOnly + ' id="testnotes" name="testnotes" class="f-comments" rows="6" cols="20" tabindex="3">'
        self.managepage += currentStory.testNotes + '</textarea><br /></label>'
        
        #hidden Values
        self.managepage += '<input id="mode" name="mode" type="hidden" value="' + mode + '" />'
        self.managepage += '<input id="key" name="key" type="hidden" value="' + key + '" />'
        
        
        #Only show estimate field if the user is assigned
        if mode == "Estimate" and currentStory.ContainsUser(currUser):
            self.managepage += '<label for="estimate"><b><span class="req">*</span>Estimate:</b><input '+ estReadOnly +' id="estimate" name="estimate" type="text" class="f-name" tabindex="1" size="4" value="'+ estimateValue +'" /><br /></label>'
             
        #Buttons
        self.managepage += '<div class="f-submit-wrap">'
        if mode != 'View' and (((currentStory.editable or mode == 'Estimate') and currentStory.finalEstimate == None) or mode == 'Delete'):
            self.managepage += '<input type="submit" value="' + mode + '" class="f-submit" tabindex="4" />'
        
        self.managepage += '<a href="/UserStories"><button type="button" class="f-submit" tabindex="5">Cancel</button></a><br /></div>'
                    
        #Users List
        users = db.GqlQuery("SELECT * FROM User")
        self.managepage += '</fieldset>'
        
        if not (mode == 'Estimate' or mode == 'View'):            
            self.managepage += '''<div id="sidebar">
                        <h3>Estimators:</h3>
                        <fieldset>'''
                            
            if users.count(5) == 0:
                self.managepage += '<p>There are no users available.</p>'
            else:
                
                tabcount = 5
                for result in users:
                    if result.username != currUser:
                        tabcount += 1
                        username = result.username
                        checked = ''
                        estimate = ''
                        
                        #Get the estimate per user
                        if  not (mode == 'Create' or  mode == 'Estimate'):
                            userEst = manager.RetrieveEstimate(key, username)
                            
                            if (userEst != None):
                                estimate = '(' + str(userEst.estimate) + ')'
                   
                            if currentStory.ContainsUser(username):
                                checked = 'checked="checked"'
                        
                        self.managepage += '<label for="un'+ str(tabcount) +'">'
                        self.managepage += '<input '+ readOnly +' '+ checked +' id="un'+ str(tabcount) +'" type="checkbox" name="usernames" value="'+ username +'" class="f-checkbox" tabindex="'+ str(tabcount) +'" />'
                        self.managepage += ' ' + username +' '+ estimate +'</label>'
            
            self.managepage += '</fieldset></div>'
        
        self.managepage += '</form>'
        self.managepage += self.manageTemplate.contentEnd
        self.managepage += self.manageTemplate.footer
        
        return self.managepage