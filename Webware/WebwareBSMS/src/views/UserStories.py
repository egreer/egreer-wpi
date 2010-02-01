'''
Created on Dec 29, 2009

@author: Eric Greer
'''
from views import Templates 
from google.appengine.ext import db

class UserStories(object):
    '''
    The login page creator
    '''
    storyTemplate = Templates.Templates()
    storypage = ''

    def __init__(self):
        '''
        Constructor
        '''

    def writeStories(self, success, error, currUser, display, filter):
        '''
           Creates the string representing the login page
        '''
        totalEstimate = 0
        storyURI = "/UserStory";
            
        self.storypage += self.storyTemplate.SetTitle('User Stories')
        self.storypage += self.storyTemplate.SetHeaderLinks('''<li class="first active"><a href="/UserStories">User Stories</a></li>
                <li><a href="/UserStory?type=Create">New User Story</a></li>
                <li><a href="/UserManagement">User Management</a></li>
                <li><a href="/Logout">Logout</a></li>''')
        self.storypage += self.storyTemplate.contentStart
        self.storypage += '''
                <!-- User Story List -->
                <h1>User Stories</h1>
                '''

        self.storypage += self.storyTemplate.SetMessages(success, error)
        self.storypage += '''           
                <div id="resultslist-wrap">
                    <div class="breadcrumb">
                        <strong>Filter by:&nbsp;&nbsp;&nbsp;</strong><a href="/UserStories">All</a> | 
                        <a href="/UserStories?display=owned">Owned</a> | 
                        <a href="/UserStories?display=assigned">Assigned</a> | 
                        <a href="/UserStories?display=open">Pending</a> | 
                        <a href="/UserStories?display=closed">Completed</a>
                    </div>'''
        if display == 'open' or display == 'closed':
            self.storypage += '''
                    <div class="breadcrumb" style="margin-left: 55px;">
                        <a href="/UserStories?display=''' + display + '''&filter=all">All Stories</a> | 
                        <a href="/UserStories?display=''' + display + '''&filter=my">My Stories</a>
                    </div>'''
                    
        #self.storiespage +='<span class="totalEstimate"><b id="totalEstimateTop">Total Estimated Units: ' + str(totalEstimate) + '</b></span>'
        stories = db.GqlQuery("SELECT * FROM UserStory")
          
        
        if stories.count(3) == 0:
            self.storypage += '<p>There are no User Stories.</p>'
        else:
            #BEGIN ELSE Output of stories                
            self.storypage += '<ol>'
            
            #ITERATE over every story
            for story in stories:
                
                #Filters
                if display == "owned":
                    if story.creator.username != currUser:
                        continue
                elif display == 'assigned' or filter == 'my':
                    if not story.ContainsUser(currUser) and story.creator.username != currUser:
                        continue 
                
                if display == 'open':
                    #Display only PENDING stories
                    if not story.finalEstimate == None:
                        continue
                                
                elif display == 'closed':
                    #Display only COMPLETED stories
                    if story.finalEstimate == None:
                        continue
                
                #Get an truncate (if necessary) the story description
                desc = story.description
                #TODO if (desc.length() > 200) desc = desc.substring(0, 200);
                
                URI = storyURI + '?type=View&amp;'
                
                if story.ContainsUser(currUser):
                    URI = storyURI + "?type=Estimate&amp;";
                                    
                #Determine if the current user "owns" this story
                if story.creator.username == currUser:
                    isOwner = True;
                    URI = storyURI + "?type=Edit&amp;";
                else:
                    isOwner = False;

               
                self.storypage += '<li><dl><dt><a href="'+ URI +'us='+ str(story.key()) +'">'+ story.title +'</a></dt><dd class="desc">'+ desc +'</dd>'
    
                finalEstimate = ' ';
                estimateValue = 0.0;
                if story.finalEstimate != None:
                    estimateValue = story.finalEstimate
                    totalEstimate += estimateValue
                    finalEstimate = str(estimateValue)
                       
                self.storypage += '<dd class="desc">Final Estimate: '+ finalEstimate +'</dd> <dd class="filetype">'
                if finalEstimate.isspace():
                    self.storypage += 'Pending'
                else:
                    self.storypage += 'Completed'
                
                if isOwner:
                    self.storypage += '<dd class="date">'
                    if story.editable:
                        self.storypage += 'editable'
                    else:
                        self.storypage += 'readonly'
                    
                    self.storypage += '</dd><dd class="date"><a href="'+ storyURI +'?type=Delete&amp;us='+ str(story.key()) +'">Delete</a></dd>'
                    
                self.storypage += '</dl></li>'
            self.storypage += '</ol><span class="totalEstimate"><b id="totalEstimateBottom">Total Estimated Units: '+ str(totalEstimate) +'</b></span>'
    
        #END ELSE
        self.storypage +='''
                </div>
                <!-- User Story List -->
                '''
        self.storypage += self.storyTemplate.contentEnd
        self.storypage += self.storyTemplate.footer
        
        return self.storypage