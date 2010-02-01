'''
Created on Dec 30, 2009

@author: Eric Greer
'''
from google.appengine.ext import db
from entities import User 

class mLogin(object):
    '''
    classdocs
    '''
    message = ''

    def __init__(self):
        '''
        Constructor
        '''
    
    def Login(self, username, password):
        '''
        @param username: The username
        @param password: The password 
        '''
        users = db.GqlQuery("SELECT * FROM User WHERE username = :1", username)
        
        if users.count(10) > 0:
            results = users.fetch(1)
            for result in results:
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