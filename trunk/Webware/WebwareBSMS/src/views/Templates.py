'''
Created on Dec 29, 2009

@author: Eric Greer
'''
from string import Template

class Templates(object):
    '''
    Contains HTML templates useful on all HTML pages 
    '''

    title = Template('''
    <?xml version="1.0" encoding="UTF-8"?>
    <!-- Planning Poker -->
    <!-- Created by Eric Greer -->
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <!--
    Copyright: Daemon Pty Limited 2006, http://www.daemon.com.au
    Community: Mollio http://www.mollio.org $$
    License: Released Under the "Common Public License 1.0", 
    http://www.opensource.org/licenses/cpl.php
    License: Released Under the "Creative Commons License", 
    http://creativecommons.org/licenses/by/2.5/
    License: Released Under the "GNU Creative Commons License", 
    http://creativecommons.org/licenses/GPL/2.0/
    -->
    <head>
        <title>$title</title>
        <link rel="stylesheet" type="text/css" href="static/css/main.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="static/css/print.css" media="print" />
        <!--[if lte IE 6]>
            <link rel="stylesheet" type="text/css" href="static/css/ie6_or_less.css" />
        <![endif]-->
    </head>
    ''')

    header = Template('''
        <body id="type-a" onload="document.getElementById('username').focus();">
        <div id="wrap">
    
        <!-- Header -->
        <div id="header">
            <div id="site-name">Planning Poker</div>
            <ul id="nav">
                $links
            </ul>
        </div>
        <!-- End Header -->
        ''')

    contentStart = '''<!-- Content Section --><div id="content-wrap"><div id="content">'''
    
    contentEnd = '''</div></div><!-- End Content Section -->'''

    messagesLines = Template('''<span class="errormsg"><b id="errorMsg">$errorMsg</b></span>
                        <span class="successmsg"><b id="successMsg">$successMsg</b></span>''')

    footer = '''
            <div id="footer">
                <p>Planning Poker &copy; 2009 Webware CS4241 | <a href="http://wpi.edu">WPI</a></p>
                <p>Eric Greer</p>
                </div>
            </div>
        </body>
        </html>
        '''

    def __init__(self):
        '''
        Constructor
        '''
    
    def SetTitle(self, theTitle):
        '''
        Sets the displayed tile of the page
        @param theTitle: The tile of the page to display in the title bar
        @return: the string containing HTML for the title.  
        '''
        return self.title.substitute(title = theTitle)
    
    def SetHeaderLinks(self, theLinks):
        '''
        Sets the links of the tab header
        @param theLinks: 
        @return: The string containing the HTML for the header and the links. 
        '''
        return self.header.substitute(links = theLinks)
    
    def SetMessages(self, success, error):
        '''
        Sets the messages
        @param success: a success message (green)
        @param error: an error message  (red)
        @return: The string containing HTML for the messages. 
        '''
        return self.messagesLines.safe_substitute(errorMsg = error, successMsg = success)