'''
Created on Dec 29, 2009

@author: Eric Greer
'''
from google.appengine.ext import db

class User(db.Model):
    '''
    The User Objects
    '''
    username = db.StringProperty(required=True)
    password = db.StringProperty(required=True)
    firstName = db.StringProperty(required=True)
    lastName = db.StringProperty(required=True)
    
   
    def setPassword(self, oldpw, newpw):
        '''
        Changes the password on the user object
        @param oldpw:  The old password for the user
        @param newpw:  The new password to change to
        '''
        if self.password == oldpw:
            self.password = newpw
            return True
        
        else : 
            return False