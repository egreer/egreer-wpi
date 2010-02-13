'''
Created on Dec 30, 2009

@author: Eric Greer
'''
from google.appengine.ext import db

class mLogin(object):
    '''
    Contains management functions for a user to login
    '''
    message = ''

    def __init__(self):
        '''
        Constructor
        '''
    
    def Login(self, username, password):
        '''
        Processes logging in the user 
        @param username: The username
        @param password: The password 
        '''
        users = db.GqlQuery("SELECT * FROM User WHERE username = :1", username)
        
        if users.count(5) == 1:
            result = users.get()

            if result.username == username and result.password == password:
                self.message += username  
                return True
        else:
            self.message += 'Invalid Username' 
            return False
        
        self.message += 'Invalid Username or Password' 
        return False
        
    def getMessage(self):
        return self.message