'''
Created on Dec 29, 2009

@author: Eric Greer
'''
from google.appengine.ext import db
from entities import User

class UserStory(db.Model):
    '''
    User Story Objects
    Contains Title, Description, Notes, Reference to Creator, and List of Users to estimate  
    '''
    title = db.StringProperty()
    description = db.StringProperty()
    testNotes = db.StringProperty()
    creator = db.ReferenceProperty(User.User)
    users = db.StringListProperty()

        
    finalEstimate = db.FloatProperty(default=None)
    editable = db.BooleanProperty(default=True)
    
    def AddUser(self, Username):
        '''
        Adds the user to the user story to give an estimate
        @param Username: The User to add to the User Story
        @return: True if the user was added
        @return: False if the user could not be added 
        '''
        if self.editable:
            self.users.append(Username)
            return True
        
        else: 
            return False
         
    def ClearUsers(self):
        '''
        Clears the Users assigned to the user story if the story is editable
        @return: True if users were removed
        @return: False if users were not removed
        '''
        if self.editable:
            self.users = None
            return True
        
        else:
            return False

    def RemoveUser(self, Username):
        '''
        Removes the user from the user story based on username
        @param Username: the username to remove
        @return: True if the user was removed (or didn't exist)
        @return: False if the user could not be removed (story was not editable)
        '''
        if self.editable:
            for u in self.users:
                if u.username == Username:
                    self.users.remove(u)
                    return True
            return True
        else:
            return False
        
    def ContainsUser(self, Username):
        '''
        Checks to see if a user exists in the list of estimators for the user story
        @param Username: The username to check
        @return: True if username exists
        @return: False if username doesn't exist
        '''
        for u in self.users:
            if u == Username:
                return True
            
            continue
            
        else:
            return False
        
    
        