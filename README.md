**Prerequisites:**   
1. Install node.js.  
2. Install appium through command 'npm install -g appium'.  
3. Install IntelliJ IDE.  
4. Set JAVA_HOME and ANDROID_HOME.  
  
  
**Steps to test the app:**  
1. Launch IntelliJ.  
2. Import project as gradle project.  
3. Connect the android device through USB or launch the emulator. Check 'adb devices' to check whether device is recognised.  
4. Run command './gradlew test -i' to run the tests from IntelliJ terminal.  
5. Reports will be generated under '/build/reports/tests/test'. Launch index.html to view the results.  
6. For the failed cases, screenshots will be captured and saved under '/screenshots' folder.  
