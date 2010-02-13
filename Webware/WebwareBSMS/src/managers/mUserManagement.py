'''
Created on Dec 30, 2009

@author: Eric Greer
'''
from google.appengine.ext import db

class mUserManagement(object):
    '''
    Manages User functions like changing password
    '''

    def __init__(self):
        '''
        Constructor
        '''

    def ChangePassword(self, user, oldPW, newPW, confirmPW):
        '''
        Changes user password
        @param User: The user trying to change password
        @param oldPW: The users current password
        @param newPW: The new password for the user.
        @param confirmPW: should be the same as the newPW 
        @return: True if successfully changed, False is unable to change
        '''
        
        #Retrieve user
        query = db.GqlQuery("SELECT * FROM User WHERE username = :1", user)
        
        if query.count(5) == 1 and newPW == confirmPW: 
            user = query.get()
            
            b = user.setPassword(oldPW, newPW);
            
            if b:
                db.put(user)
                return True
            else:
                return False
        
        else:
            return False
