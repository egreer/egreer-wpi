'''
Created on Dec 29, 2009

@author: Eric Greer
'''
from views import Templates 

class Login(object):
    '''
    The login HTML page creator
    '''
    loginTemplate = Templates.Templates()
    loginpage = ''

    def __init__(self):
        '''
        Constructor
        '''
        
    
    def writeLogin(self, success, error):
        '''
        Creates the string representing the login page
        @param success: The success message to print on the page
        @param error: The error message to print on the page
        @return: Returns a string representing the login page 
        '''
          
        self.loginpage += self.loginTemplate.SetTitle('Login')
        
        self.loginpage += '<body id="type-a" onload="document.getElementById(\'username\').focus();">'
        self.loginpage += self.loginTemplate.SetHeaderLinks('''<li class="first active"><a href="/">Home</a></li>
                                <li><a href="/Register">Register</a></li>''')
        self.loginpage += self.loginTemplate.contentStart
        self.loginpage += '''
                <!-- Login Form -->
                <form action="/UserStories" method="post" class="f-wrap-1" onsubmit="return checkLogin(this);">
                
                    <fieldset>
                    
                        <h3>Login</h3>'''
                        
        self.loginpage += self.loginTemplate.SetMessages(success, error)
        self.loginpage += '''<label for="username"><b>Username:</b>
                            <input id="username" name="username" type="text" class="f-name" tabindex="1" /><br />
                        </label>
                        
                        <label for="password"><b>Password:</b>
                            <input id="password" name="password" type="password" class="f-name" tabindex="2" /><br />
                        </label>
                        
                        <div class="f-submit-wrap">
                            <input type="submit" value="Submit" class="f-submit" tabindex="3" /><br />
                        </div>
                        
                        <label>
                            <span>Don't have an account? <a href="/Register">Sign Up!</a></span>
                        </label>
                    
                    </fieldset>
                </form>
                <!-- End Login Form -->
                '''
        self.loginpage += self.loginTemplate.contentEnd
        self.loginpage += '<script type="text/javascript" src="js/validate.js"></script> <script type="text/javascript" src="js/sha512-min.js"></script>'
        self.loginpage += self.loginTemplate.footer
        
        return self.loginpage