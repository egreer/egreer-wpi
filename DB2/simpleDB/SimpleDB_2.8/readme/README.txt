Project 3
Team 17
Eric Greer

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
INSTALLATION INSTRUCTIONS

1) Import the file as an Eclipse Java project.
2) Set up two run configuration one for running the file, and one for the test
	a) To run simpleDB create a run configuration on simpledb.server.Startup
		and give it an argument that will be the DB name
	b) To run the tests set up a JUnit configuration for the class studentTest.TestStarter 

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
TESTING INSTRUCTIONS
By default the JUnit run configuration runs both test but if you want to see individual
results you can run the separate tests. If you want to change which policy you are using for
the tests then go to line 23 in lock table and change which policy is created there. There 
are two choices "new WaitDiePolicy()" or "new WaitGraph();" 
 
Blackbox - run the individual test testBlackBox()

Glassbox - run the individual test testGlassBox() 
