'''
Created on Dec 30, 2009

@author: Eric Greer
'''
from views import Templates 

class Register(object):
    '''
    The registration HTML page creator
    '''
    regTemplate = Templates.Templates()
    regPage = ''
    
    def __init__(self):
        '''
        Constructor
        '''
    
    def writeReg(self, success, error):
        '''
        Creates the string representing the registration page
        @param success: The success message to print on the page
        @param error: The error message to print on the page
        @return: Returns a string representing the registration page 
        '''
        
        
        self.regPage += self.regTemplate.SetTitle('tested')
        
        self.regPage += '<body id="type-a">'
        self.regPage += self.regTemplate.SetHeaderLinks('''<li class="first"><a href="/">Home</a></li>
                                <li class="active"><a href="/Register">Register</a></li>''')
        self.regPage += self.regTemplate.contentStart
        self.regPage += '''<!-- Register Form -->
                <form action="/Register" method="post" class="f-wrap-1" onsubmit="return checkRegister(this);" >
                
                    <div class="req"><b>*</b> Indicates required field</div>
                
                    <fieldset>
                    
                        <h3>Register</h3>'''
                        
        self.regPage += self.regTemplate.SetMessages(success, error)
        self.regPage += '''<label for="firstname"><b><span class="req">*</span>First name:</b>
                            <input id="firstname" name="firstname" type="text" class="f-name" tabindex="1" /><br />
                        </label>
                        
                        <label for="lastname"><b><span class="req">*</span>Last name:</b>
                            <input id="lastname" name="lastname" type="text" class="f-name" tabindex="2" /><br />
                        </label>
                        
                        <label for="username"><b><span class="req">*</span>Username:</b>
                            <input id="username" name="username" type="text" class="f-name" tabindex="3" /><br />
                        </label>
                        
                        <label for="password"><b><span class="req">*</span>Password:</b>
                            <input id="password" name="password" type="password" class="f-name" tabindex="4" /><br />
                        </label>
                        
                        <label for="pwconfirm"><b><span class="req">*</span>Confirm Password:</b>
                            <input id="pwconfirm" name="pwconfirm" type="password" class="f-name" tabindex="5" /><br />
                        </label>
                        
                        <div class="f-submit-wrap">
                            <input type="submit" value="Submit" class="f-submit" tabindex="6" />
                            <a href="/"><button type="button" class="f-submit">Cancel</button></a>
                        </div>
                    
                    </fieldset>
                </form>
                <!-- End Register Form -->
                '''
        self.regPage += self.regTemplate.contentEnd
        self.regPage += '<script type="text/javascript" src="js/validate.js"></script><script type="text/javascript" src="js/sha512-min.js"></script>'
        self.regPage += self.regTemplate.footer
        
        return self.regPage    