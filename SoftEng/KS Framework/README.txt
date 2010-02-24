CS3733 D2008 |
March 30     |
-------------+

Within this Eclipse project, professor Heineman will make available the shared
components that form the infrastructure to the KombatSolitaire application.
 
You will develop your own CompUnit components within the Development project.
The actual execution will take place within a CompUnit workspace, which is
called "CompUnitEnvironment" -- also an Eclipse project (although not a Java 
project as you may see).

I describe the steps you must complete in the accompanying README.ppt file

Since the code (wisely or not!) depends upon Eclipse's SWT library, you need
to make sure that you have a version compatible with your operating system. I 
have created a project 'SWT' with the binaries for the Windows operating system.
If you are developing on a different architecture, then download:
 
 Linux:
 
    http://download.eclipse.org/eclipse/downloads/drops/R-3.3.1.1-200710231652/download.php?dropFile=swt-3.3.1.1-gtk-linux-x86.zip
    
 OS X:
 
    http://download.eclipse.org/eclipse/downloads/drops/R-3.3.1.1-200710231652/download.php?dropFile=swt-3.3.1.1-carbon-macosx.zip
    
If these are your machine architectures.

Note how the project which I extract from these ZIP files is named 'SWT' and then the
Development project simply depends upon the SWT project to supply the relevant SWT 
libraries.
       