Eric Greer

About Me: a service that provides the following information: my name, major, graduation year
http://egreer-wpi2.appspot.com/aboutme

Returned XML Structure:
<student>
  <name>Eric Greer</name>
  <major>Computer Science</major>
  <graduates>2010</graduates>
</student>



Listing of courses: This service provides a list of courses I have taken with the course number course name 
http://egreer-wpi2.appspot.com/myclasses

Returned XML Structure:
<courses>
  <course>
    <name>Webware</name>
    <number>CS4241</number>
  </course>
  ...
</courses>


Did I take a course?: This service takes the course number, and returns the term, replace course number with the course number. 
http://egreer-wpi2.appspot.com/diditake?course=COURSENUMBER

Returned XML Structure:
<term>TERM</term>