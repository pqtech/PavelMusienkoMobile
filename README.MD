# PavelMuseinkoMobile

In order to keep safe private token I suggest to keep blank "token" element in pom.xml

**Exercise 3:**
1. Clone this repository to your PC
2. Change settings in the following files if needed:  
cloudAndroidNativeTNG.xml  
cloudAndroidWebTNG.xml  
cloudIOSNativeTNG.xml  
cloudIOSWebTNG.xml  
3. Put your private EPAM Mobile Cloud token to pom.xml (element "token")  
3. Use Maven in order to run tests:   
Cloud Android Native tests:  
`mvn clean test -PcloudAndroidNative`  
Cloud Android Web tests:  
`mvn clean test -PcloudAndroidWeb`  
Cloud iOS Native tests:  
`mvn clean test -PcloudiOSNative`  
Cloud iOS Web tests:  
`mvn clean test -PcloudiOSWeb`