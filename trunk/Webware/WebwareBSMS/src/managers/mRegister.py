'''
Created on Dec 30, 2009

@author: Eric Greer
'''
from google.appengine.ext import db
from entities import User 

class mRegister(object):
    '''
    The Registration manager
    '''
    
    message = ''

    def __init__(self):
        '''
        Constructor
        '''
        
    def Register(self, First, Last, uname, pwd, cpwd):
        '''
        @param First:   First Name
        @param Last:    Last Name
        @param uname:   User Name
        @param pwd:     Password
        @param cpwd:    Confirm Password
        '''
        
        if pwd == cpwd:
            users = db.GqlQuery("SELECT * FROM User WHERE username = :1", uname)
                        
            #return False
            if users.count(2) > 0:
                self.message += 'Username: ' + uname + ' already exists'
                return False
            else:
                user = User.User(username = uname, firstName = First, lastName = Last, password = pwd)
                user.put()
                self.message += uname
                return True
            
        else:
            self.message += 'Passwords do not match'
            return False
    
    def getMessage(self):
        '''
        Retrieves the message
        '''
        return self.message