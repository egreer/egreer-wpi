'''
The main file for the Python version of Planning Poker
Contains the mapping of URLs to Python functions 

@author: Eric Greer
'''
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app
from views import Login
from views import Register
from views import UserStories
from views import UserStory
from managers import mLogin
from managers import mRegister
from managers import mUserStory
from managers import mUserManagement
from utilities import session
from views import UserManagement


#Login page
class MainPage(webapp.RequestHandler):
    '''
    This is the main login page
    '''
           
    def get(self):
        '''
        Displays the main login page
        '''
        lpage = Login.Login()
        self.response.out.write(lpage.writeLogin(session["success"], session["error"]))
        resetMessages()
     
#User Stories Page 
class UserStoriesPage(webapp.RequestHandler):
    '''
    This is the User Stories Page
    '''
    def get(self):
        '''
        Displays the user stories page
        From any other pages 
        '''
        uspage = UserStories.UserStories()
        display = ''
        filter = ''
        display += self.request.get('display')
        filter += self.request.get('filter')
        
        #Check to see if a user is logged in
        if session["user"] == '':
            session["error"] = 'You must be logged in to access the User Stories'
            self.redirect("/")
        else:
            self.response.out.write(uspage.writeStories(session["success"], session["error"], session["user"], display, filter))
            resetMessages()
            
    def post(self):
        '''
        Displays the user stories page
        From login page 
        Processes Login
        '''
        username = self.request.get('username')
        password = self.request.get('password')
        lManager = mLogin.mLogin()  
        
        success = lManager.Login(username, password)
        
        if success:
            session["success"] = 'Welcome ' + lManager.getMessage()
            session["user"] = username
            self.redirect("/UserStories", True)
        else :
            session["error"] = lManager.getMessage()
            self.redirect('/', True)

#User Story Page
class UserStoryPage(webapp.RequestHandler):
    '''
    This is the User Story Page
    '''
    def get(self):
        '''
        Displays the user story page
        From any other page 
        '''
        
        #Check to see if a user is logged in
        if session["user"] == '':
            session["error"] = 'You must be logged in to access the User Story'
            self.redirect("/", True)   
        else:
            mode = self.request.get('type')
            id = self.request.get('us')
            
            if not (mode == "Estimate" or mode == "Delete" or mode == "Edit" or mode == "Create"):
                mode = "View";
            upage = UserStory.UserStory()
            self.response.out.write(upage.writeStory(session["success"], session["error"], session["user"], mode, id))
            resetMessages();
    
    def post(self):
        '''
        Processes the user story: Create, Edit, Delete and Estimates 
        '''
        manager = mUserStory.mUserStory()
        user = session["user"]
        key = self.request.get('key')
        title = self.request.get('title')
        description = self.request.get('description')
        testnotes = self.request.get('testnotes')
        estimate = self.request.get('estimate')
        usernames = self.request.get_all('usernames')
        mode = self.request.get('mode')
        
        url = '' + self.request.url + '?type=' + mode + '&us=' + key
        
        #Validate the mode
        if mode == None or mode == '' or not (mode == "Estimate" or mode == "Delete" or mode == "Edit" or mode == "Create"):
            session['error'] = 'Invalid Mode'
            self.redirect(url, True)
            return
        
        #Variable checking
        if not (mode == 'Delete' or  mode == 'Estimate'):
            if title == None or title.isspace() or title == '':
                session['error'] = 'Please enter a title.'
                self.redirect(url, True)
                return
            elif description == None or description.isspace() or description == '':
                session['error'] = 'Please enter a description'
                self.redirect(url, True)
                return
            elif testnotes == None:
                session['error'] = 'Invalid test notes'
                self.redirect(url, True)
                return
            elif (not mode == 'Create') and (key == None or key == ''):
                session['error'] = 'Invalid Key'
                self.redirect(url, True)
                return
            #TODO Strip HTML
            title = removeHTMLTags(title)
            description = removeHTMLTags(description)
            testnotes = removeHTMLTags(testnotes) 
       
        #Mode Processing
        if mode == 'Create':
            b = manager.CreateUserStory(user, title, description, testnotes, usernames)
            if b:
                session['success'] = "Created user story: " + title;
                self.redirect("/UserStories", True)
                return                              
            else:
                session['error'] = "Could not create user story"
                self.redirect(url, True)
                return
         
        elif mode == 'Edit':
            b = manager.EditUserStory(key, user, title, description, testnotes, usernames)
            if b:
                session['success'] = "Edited user story: " + title;
                self.redirect("/UserStories", True)
                return
            else:
                session['error'] = "Could not edit user story"
                self.redirect(url, True)
                return
                
        elif mode == 'Delete':
            #boolean b = UserStoryManager.removeUserStory(key, currUser);
            b = manager.DeleteUserStory(key, user)
            if b:
                session['success'] = 'Deleted user story'
                self.redirect("/UserStories", True)
                return
            else:
                session['error'] = "Could not delete user story"
                self.redirect(url, True)
                return
            
        elif mode == 'Estimate':
            if estimate != None and estimate != '':
                estimate = float(estimate)
                if estimate >= 0:
                    b = manager.EstimateUserStory(key, user, estimate)
                    if b:
                        session['success'] = 'Successfully saved estimate'
                        self.redirect("/UserStories", True)
                        return
                    else:
                        session['error'] = "Could not create estimate, Please try again"
                        self.redirect(url, True)
                    return
            session['error'] = "Please enter a valid estimate and try again"
            self.redirect(url, True)
            return

      
#Registration Page
class RegisterPage (webapp.RequestHandler):
    '''
    This is the registration page
    '''

    def get(self):
        '''
        Displays the registration page
        From any other page
        '''
        rpage = Register.Register()
        self.response.out.write(rpage.writeReg(session["success"], session["error"]))
        resetMessages()
    
    def post(self):
        '''
        Processes the registration request
        '''
        self.response.out.write('Register Page Post')
        firstname = self.request.get('firstname')
        lastname = self.request.get('lastname')
        username = self.request.get('username')
        password = self.request.get('password')
        pwconfirm = self.request.get('pwconfirm')
        rManager = mRegister.mRegister()
        
        success = rManager.Register(firstname, lastname, username, password, pwconfirm);
        
        if success:
            session["success"] = 'Created user: ' + rManager.getMessage()
            session["user"] = username
            self.redirect("/UserStories", True)
        else :
            session["error"] = 'Could not create registration: ' + rManager.getMessage()
            self.redirect('/Register', True)

#User Management Page
class UserManagementPage (webapp.RequestHandler):
    '''
    This is the User Management page
    '''

    def get(self):
        '''
        Displays the User Management page
        From any other page
        '''
        if session["user"] == '':
            session["error"] = 'You must be logged in to access the User Management'
            self.redirect("/", True)   
        else:
            mpage = UserManagement.UserManagement()
            self.response.out.write(mpage.writeManage(session["success"], session["error"], session["user"]))
            resetMessages()
    
    def post(self):
        '''
        Processes the Password change request
        '''

        self.response.out.write('Register Page Post')
        oldPW = self.request.get('oldpassword')
        newPW = self.request.get('password')
        confirmPW = self.request.get('pwconfirm')
        mManager = mUserManagement.mUserManagement()
        
        success = mManager.ChangePassword(session["user"], oldPW, newPW, confirmPW)
        
        if success:
            session["success"] = 'Changed Password'
            self.redirect("/UserManagement", True)
        else :
            session["error"] = 'Could not change password'
            self.redirect('/UserManagement', True)



#Logout Page
class LogoutPage (webapp.RequestHandler):
    def get(self):
        '''
        Processes logout
        '''
        session["user"] = ''
        session["success"] = 'You have successfully logged out'
        self.redirect("/", True)

application = webapp.WSGIApplication([('/Logout', LogoutPage ), ('/Register', RegisterPage ), ('/UserStories', UserStoriesPage ), ('/UserStory', UserStoryPage), ('/UserManagement', UserManagementPage), ('/', MainPage)], debug=True)

session = session.Session()
#Variables
session["user"] = ''
session["success"] = ''
session["error"] = ''

def main():
    run_wsgi_app(application)

def resetMessages():
    '''
    Resets the Success and Error Messages in the session
    '''
    session["success"] = ''
    session["error"] = ''

def removeHTMLTags(input):
    #TODO strip HTML
    return input

if __name__ == "__main__":
    main()
