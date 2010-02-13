'''
Created on Dec 29, 2009

@author: Eric Greer
'''
from google.appengine.ext import db
from entities import UserStory
from entities import User

class Estimate(db.Model):
    '''
    The Estimate Object
    Contains a reference to user story, estimating user, and the estimate 
    '''

    userStory = db.ReferenceProperty(UserStory.UserStory)
    estimator = db.ReferenceProperty(User.User)
    estimate = db.FloatProperty()