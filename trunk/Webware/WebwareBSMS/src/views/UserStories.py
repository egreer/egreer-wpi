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
    manageTemplate = Templates.Templates()
    storiespage = ''

    def __init__(self):
        '''
        Constructor
        '''

    def writeStories(self, success, error, currUser, display, filter):
        '''
        Creates the string representing the login page
        @param success: The success message to print on the page
        @param error: The error message to print on the page
        @param currUser: The current username to get stories on
        @param display: a mode of display for the user stories (Owned | Assigned | Pending | Completed)
        @param filter: a filter of the stories displayed, (All| My)
        @return: Returns a string representing the user stories page 
        '''
        totalEstimate = 0
        storyURI = "/UserStory";
            
        self.storiespage += self.manageTemplate.SetTitle('User Stories')
        
        self.storiespage += '<body id="type-a" onload="updateTopTotal();">'
        self.storiespage += self.manageTemplate.SetHeaderLinks('''<li class="first active"><a href="/UserStories">User Stories</a></li>
                <li><a href="/UserStory?type=Create">New User Story</a></li>
                <li><a href="/UserManagement">User Management</a></li>
                <li><a href="/Logout">Logout</a></li>''')

        self.storiespage += self.manageTemplate.contentStart
        self.storiespage += '''
                <!-- User Story List -->
                <h1>User Stories</h1>
                '''

        self.storiespage += self.manageTemplate.SetMessages(success, error)
        self.storiespage += '''           
                <div id="resultslist-wrap">
                    <div class="breadcrumb">
                        <strong>Filter by:&nbsp;&nbsp;&nbsp;</strong><a href="/UserStories">All</a> | 
                        <a href="/UserStories?display=owned">Owned</a> | 
                        <a href="/UserStories?display=assigned">Assigned</a> | 
                        <a href="/UserStories?display=open">Pending</a> | 
                        <a href="/UserStories?display=closed">Completed</a>
                    </div>'''
        if display == 'open' or display == 'closed':
            self.storiespage += '''
                    <div class="breadcrumb" style="margin-left: 55px;">
                        <a href="/UserStories?display=''' + display + '''&filter=all">All Stories</a> | 
                        <a href="/UserStories?display=''' + display + '''&filter=my">My Stories</a>
                    </div>'''
                    
        self.storiespage +='<span class="totalEstimate"><b id="totalEstimateTop">Total Estimated Units:  </b></span>'
        stories = db.GqlQuery("SELECT * FROM UserStory")
          
        
        if stories.count(3) == 0:
            self.storiespage += '<p>There are no User Stories.</p>'
        else:
            #BEGIN ELSE Output of stories                
            self.storiespage += '<ol>'
            
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

               
                self.storiespage += '<li><dl><dt><a href="'+ URI +'us='+ str(story.key()) +'">'+ story.title +'</a></dt><dd class="desc">'+ desc +'</dd>'
    
                finalEstimate = ' ';
                estimateValue = 0.0;
                if story.finalEstimate != None:
                    estimateValue = story.finalEstimate
                    totalEstimate += estimateValue
                    finalEstimate = str(estimateValue)
                       
                self.storiespage += '<dd class="desc">Final Estimate: '+ finalEstimate +'</dd> <dd class="filetype">'
                if finalEstimate.isspace():
                    self.storiespage += 'Pending'
                else:
                    self.storiespage += 'Completed'
                
                if isOwner:
                    self.storiespage += '<dd class="date">'
                    if story.editable:
                        self.storiespage += 'editable'
                    else:
                        self.storiespage += 'readonly'
                    
                    self.storiespage += '</dd><dd class="date"><a href="'+ storyURI +'?type=Delete&amp;us='+ str(story.key()) +'">Delete</a></dd>'
                    
                self.storiespage += '</dl></li>'
            self.storiespage += '</ol><span class="totalEstimate"><b id="totalEstimateBottom">Total Estimated Units: '+ str(totalEstimate) +'</b></span>'
    
        #END ELSE
        self.storiespage +='''
                </div>
                <!-- User Story List -->
                '''
        self.storiespage += self.manageTemplate.contentEnd
        
        self.storiespage += '''<script type="text/javascript">
                function updateTopTotal() {
                    var topTotal = document.getElementById("totalEstimateTop");
                    var bottomTotal = document.getElementById("totalEstimateBottom");
                    
                    var total = bottomTotal.innerHTML;
                    
                    topTotal.innerHTML = total;
                }
            </script>'''
        
        self.storiespage += self.manageTemplate.footer
        
        return self.storiespage