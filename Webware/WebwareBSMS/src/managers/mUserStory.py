'''
Created on Dec 30, 2009

@author: Eric Greer
'''
from google.appengine.ext import db
from entities import UserStory
from entities import Estimate

class mUserStory(object):
    '''
    The User Story manager
    '''

    def __init__(self):
        '''
        Constructor
        '''

    def CreateUserStory(self, user, title, description, testnotes, usernames):
        '''
        Creates user and adds to database
        @param User: The user trying to delete
        @param title: The title of the story
        @param description: The description
        @param testnotes: The test notes of the story
        @param usernames: The usernames to give estimates
        @return: True if successfully created, False is unable to create
        '''
        
        #Retrieve user
        query = db.GqlQuery("SELECT * FROM User WHERE username = :1", user)
        
        if query.count(5) == 1: 
            user = query.get()
        
            story = UserStory.UserStory(title = title, description = description, testNotes = testnotes, users = usernames, creator = user)
            db.put(story)
            return True
        
        else:
            return False
        
    def EditUserStory(self, key, user, title, description, testnotes, usernames):
        '''
        Edit user from the database
        @param key: The key of the user story to edit
        @param User: The user trying to edit
        @param title: The new title of the story
        @param description: The new description
        @param testnotes: The new test notes of the story
        @param usernames: The new usernames to give estimates        
        @return: True if successfully edited, False is unable to edit
        '''
        results = db.get(key)
        if results == None:
            return False
        else:
            story = results
            if story.creator.username == user and story.editable:
                story.title = title
                story.description = description
                story.testNotes = testnotes
                
                del story.users[:]
                story.users.extend(usernames)

                db.put(story)
                return True
            else:
                return False
                
    def DeleteUserStory(self, key, user):
        '''
        Delete user story from user database
        @param key: The key of the user story to delete
        @param User: The user trying to delete
        @return: True if successfully deleted, False is unable to delete  
        '''
        result = db.get(key)
        if result == None:
            return False
        else:
            if result.creator.username == user:
                db.delete(result)
                return True
            else:
                return False
            
    def RetrieveEstimate(self, key, user):
        '''
        Retrieves the estimate for that given user and given story key
        @param key: The key for the user story
        @param user: The user to look up the estimate for   
        '''
        query = db.GqlQuery("SELECT * FROM UserStory WHERE __key__ = :1", db.Key(key));
        story = query.get()
        
        if story != None and story.ContainsUser(user):
            query2 = db.GqlQuery("SELECT * FROM Estimate WHERE userStory = :1", story)
            
            for estimate in query2:
                if estimate.estimator.username == user:
                    return estimate
        return None
    
    def EstimateUserStory(self, key, user, estimate):
        '''
        Estimates the user story
        @param key: The key for  the user story
        @param user: The username of the estimator
        @param estimate: The estimate
        '''
        query = db.GqlQuery("SELECT * FROM UserStory WHERE __key__ = :1", db.Key(key));
        story = query.get()
        
        if story != None:
            result = self.RetrieveEstimate(key, user)
            
            if result != None and story.finalEstimate == None:
                result.estimate = estimate
                db.put(result)
                num = self.CheckEstimates(key)
                if num != None:
                    story.finalEstimate = num
                story.editable = False
                db.put(story)
                return True
            elif story.finalEstimate == None:
                query3 = db.GqlQuery("SELECT * FROM User WHERE username = :1", user);
                dbUser = query3.get()
                
                if dbUser != None:
                    result = Estimate.Estimate(userStory = story, estimator = dbUser, estimate = estimate)
                    db.put(result)
                    num = self.CheckEstimates(key)
                    if num != None:
                        story.finalEstimate = num
                    story.editable = False
                    db.put(story)
                    return True
                
        return False
    
    def CheckEstimates(self, key):
        query = db.GqlQuery("SELECT * FROM UserStory WHERE __key__ = :1", db.Key(key));
        story = query.get()
        
        done = True
        total = 0
        num = 0
        
        if story != None:
            for user in story.users:
                result = self.RetrieveEstimate(key, user)
                if result == None:
                    done = False
                    break
                
                total += result.estimate 
                num += 1

            if done == True:
                return total / num
            
        return None
            