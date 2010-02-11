
TESTING
 * drag the testApps into the applications/ folder of the environment
 * drag stdin-file.txt to the CompUnitRelease project
 * install each component in the 'test' package
 * run each .ca file in testApps with EcleEmma enabled

NOTES
 * The Stdout tests are buggy; the tests will continue to run even after Foundation
   has been closed.  The cause is unknown, but this prevents code coverage from being
   measured in this package.

   