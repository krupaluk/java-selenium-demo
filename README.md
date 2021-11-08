# java-selenium-demo
Simple demonstration of UI test automation using Java & Selenium.
Below is simple description of required installation & configuration steps. 
Installation steps are described for Windows operating system and Eclipse IDE with Maven.
Please note that the instructions below are not meant to cover 100% of potential steps & issues.

## Java installation & config
- Download java from https://www.oracle.com/java/technologies/downloads/.
- Start installation, keep default installation settings and finish.
- Check installation in installation folder (in win → C:\Program Files\Java).
- Check that java installation folder is in the Path.
- Add System variable "JAVA_HOME" and assign Java folder path to it (e.g.: "C:\Program Files\Java\jdk-17.0.1")

## Eclipse installation & config
- Download eclipse installer from https://www.eclipse.org/downloads/.
- Select installation for desired Eclipse package (e.g.: "Java EE developers").
- Start installation, select folder (e.g.: "C:\eclipse").
- Confirm the rest and finish the installation.
- Open eclipse and select desired workspace (e.g.: "C:\SDET\Workspaces").
- (Optional) Set Java perspective -> Window -> Perspective -> Open Perspective -> Java.
- Done.

## Clone the repo & import to Eclipse
- Open your git client.
- Obtain https url repository for cloning: https://github.com/krupaluk/java-selenium-demo.git.
- Clone the repository do desired folder (e.g.: C:\SDET\Workspaces\java-selenium-demo).
- Select from Eclipse menu: File -> Open Projects from File System...
- Click 'Directory' -> find the repo folder (e.g.: "C:\SDET\Workspaces\java-selenium-demo").
- Click Finish button -> This should trigger automatic download of dependencies on background.
- Wait until dependencies are downloaded (should take about 5-20 seconds).

## Download & set TestNG plug-in
- Open eclipse marketplace url -> https://marketplace.eclipse.org/content/testng-eclipse.
- Drag the Eclipse 'Install' button (under Eclipse logo) and drop to to Eclipse app.
- Eclipse marketplace will open with 'TestNG for Eclipse' package -> click 'Confirm'.
- Accept terms during the installation and finish. Eventually confirm additional dialog.
- After installation is done confirm restart.

## Test execution via TestNG
- Find and rightclick the test you wish to run (e.g.: src/test/java/cz.alza.tests/SimpleTests.java).
- Run as TestNG.
