'''
Created on Dec 29, 2009

@author: Eric Greer
'''
from views import Templates 

class UserManagement(object):
    '''
    The User Management page creator
    '''
    manageTemplate = Templates.Templates()
    managepage = ''

    def __init__(self):
        '''
        Constructor
        '''

    def writeManage(self, success, error, currUser):
        '''
        Creates the string representing the user Management page
        @param success: The success message to print on the page
        @param error: The error message to print on the page
        @param currUser: The current username to manage
        @return: Returns a string representing the User management page 
        '''
            
        self.managepage += self.manageTemplate.SetTitle('User Stories')
        
        self.managepage += '<body id="type-a">'
        self.managepage += self.manageTemplate.SetHeaderLinks('''<li class="first"><a href="/UserStories">User Stories</a></li>
                <li><a href="/UserStory?type=Create">New User Story</a></li>
                <li class="active"><a href="/UserManagement">User Management</a></li>
                <li><a href="/Logout">Logout</a></li>''')
        self.managepage += '''<script type="text/javascript" src="js/validate.js"></script>
                <script type="text/javascript" src="js/sha512-min.js"></script>'''
        self.managepage += self.manageTemplate.contentStart
        self.managepage += '''
                <!-- Change Password Form -->
                <form action="/UserManagement" method="post" class="f-wrap-1" onsubmit="return checkChangePassword(this);">
                      <fieldset>
                      <h3>Change Password</h3>
                '''

        self.managepage += self.manageTemplate.SetMessages(success, error)
        self.managepage += '''<label for="oldpassword"><b>Current Password:</b>
                            <input id="oldpassword" name="oldpassword" type="password" class="f-name" tabindex="1" /><br />
                        </label>
                        
                        <label for="password"><b>New Password:</b>
                            <input id="password" name="password" type="password" class="f-name" tabindex="2" /><br />
                        </label>
                        
                        <label for="pwconfirm"><b>Confirm Password:</b>
                            <input id="pwconfirm" name="pwconfirm" type="password" class="f-name" tabindex="3" /><br />
                        </label>
                        
                        <div class="f-submit-wrap">
                            <input type="submit" value="Change Password" class="f-submit" tabindex="4" /><br />
                        </div>
                    
                    </fieldset>
                </form>
                <!-- End Change Password Form -->
                '''
        self.managepage += self.manageTemplate.contentEnd
        self.managepage += '<script type="text/javascript" src="js/validate.js"></script><script type="text/javascript" src="js/sha512-min.js"></script>'
        self.managepage += self.manageTemplate.footer
        
        return self.managepage