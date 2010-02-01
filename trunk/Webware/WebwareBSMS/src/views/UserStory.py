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
    The login page creator
    '''
    storyTemplate = Templates.Templates()
    storypage = ''


    def writeStory(self, success, error, currUser, mode, key):
        '''
           Creates the string representing the login page
        '''
        totalEstimate = 0
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
             
            
        self.storypage += self.storyTemplate.SetTitle(mode + ' User Story')
        self.storypage += self.storyTemplate.SetHeaderLinks('''<li class="first"><a href="/UserStories">User Stories</a></li>
                <li class="active"><a href="/UserStory?type=Create">New User Story</a></li>
                <li><a href="/UserManagement.jsp">User Management</a></li>
                <li><a href="/Logout">Logout</a></li>
                ''')
        self.storypage += self.storyTemplate.contentStart
        
        
        self.storypage += '''
                    <!-- User Story Form  -->
                    <form action="/UserStory" method="post" class="f-wrap-1" onsubmit="return '''
                    
        if mode == "Estimate":
            self.storypage += 'checkEstimate(this);">'
        else:            
            self.storypage += 'checkUserStory(this);">'
                
        self.storypage += '''
                    <div class="req"><b>*</b> Indicates required field</div>
                
                    <fieldset>
                
                    <h3><%= pageType %> User Story</h3>
                  '''
        self.storypage += self.storyTemplate.SetMessages(success, error)

        #Final Estimate
        if currentStory.finalEstimate != None:
            self.storypage += '<span class="totalEstimate"><b id="totalEstimateTop">Total Estimated Units: ' + str(currentStory.finalEstimate) +'</b></span>'
        
                            
        #Title Field
        self.storypage += '<label for="title"><b><span class="req">*</span>Title:</b>'
        self.storypage += '<input ' + readOnly + ' id="title" name="title" type="text" class="f-name" tabindex="1" value="'+ currentStory.title + '" /><br /></label>'
                    
        #Description field
        self.storypage += '<label for="description"><b><span class="req">*</span>Description:</b> <textarea ' + readOnly + ' id="description" name="description" class="f-comments" rows="6" cols="20" tabindex="2">'
        self.storypage += currentStory.description + '</textarea><br /></label>'
                    
        #Test Notes Field
        self.storypage += '<label for="testnotes"><b>Test Notes:</b><textarea ' + readOnly + ' id="testnotes" name="testnotes" class="f-comments" rows="6" cols="20" tabindex="3">'
        self.storypage += currentStory.testNotes + '</textarea><br /></label>'
        
        #hidden Values
        self.storypage += '<input id="mode" name="mode" type="hidden" value="' + mode + '" />'
        self.storypage += '<input id="key" name="key" type="hidden" value="' + key + '" />'
        
        
        #Only show estimate field if the user is assigned
        if mode == "Estimate" and currentStory.ContainsUser(currUser):
            self.storypage += '<label for="estimate"><b><span class="req">*</span>Estimate:</b><input '+ estReadOnly +' id="estimate" name="estimate" type="text" class="f-name" tabindex="1" size="4" value="'+ estimateValue +'" /><br /></label>'
             
        #Buttons
        self.storypage += '<div class="f-submit-wrap">'
        if mode != 'View' and (currentStory.editable or mode == 'Delete' or mode == 'Estimate') and currentStory.finalEstimate == None:
            self.storypage += '<input type="submit" value="' + mode + '" class="f-submit" tabindex="4" />'
        
        self.storypage += '<a href="/UserStories"><button type="button" class="f-submit" tabindex="5">Cancel</button></a><br /></div>'
                    
        #Users List
        users = db.GqlQuery("SELECT * FROM User")
        self.storypage += '</fieldset>'
        
        if not (mode == 'Estimate' or mode == 'View'):            
            self.storypage += '''<div id="sidebar">
                        <h3>Estimators:</h3>
                        <fieldset>'''
                            
            if users.count(5) == 0:
                self.storypage += '<p>There are no users available.</p>'
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
                            userEst = manager.RetrieveEstimate(key, currUser)
                            
                            if (userEst != None):
                                estimate = '(' + str(userEst.estimate) + ')'
                   
                            if currentStory.ContainsUser(username):
                                checked = 'checked="checked"'
                        
                        self.storypage += '<label for="un'+ str(tabcount) +'">'
                        self.storypage += '<input '+ readOnly +' '+ checked +' id="un'+ str(tabcount) +'" type="checkbox" name="usernames" value="'+ username +'" class="f-checkbox" tabindex="'+ str(tabcount) +'" />'
                        self.storypage += ' ' + username +' '+ estimate +'</label>'
            
            self.storypage += '</fieldset></div>'
        
        self.storypage += '</form>'
        self.storypage += self.storyTemplate.contentEnd
        self.storypage += self.storyTemplate.footer
        
        return self.storypage