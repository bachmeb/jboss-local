# jboss 5.1.0 eap win7.64

## References
* https://access.redhat.com/articles/113083#Cert_5
* https://access.redhat.com/articles/111663
* http://jbossas.jboss.org/downloads
* https://access.redhat.com/articles/112673

In this tutorial $DEV refers to C:\DEV\ on my system. For consistency, you can choose any folder you like to be your DEV folder. 

##### Download JDK 1.6 update 45, 64-bit
* http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html#jdk-6u45-oth-JPR

##### Install JDK 1.6 update 45
* The filename is jdk-6u45-windows-x64.exe
* *Custom Setup*
	* Install to: $DEV\java\6\64\jdk1.6.0_45\

##### Add bin folder to Windows PATH environment variable
* Start > Control Panel > System > Advanced System Settings > Environment Variables > System Variables > Path > Edit
* Append the following: $DEV\java\6\64\jdk1.6.0_45\bin;

##### Confirm the Java version from the command line
* Start > cmd
```
java -version
```

##### Login to the Red Hat Customer Portal
* https://access.redhat.com/

##### Download Application Platform 5.1.0 Binary Installer
* https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?downloadType=distributions&product=appplatform&version=5.1.0
* https://access.redhat.com/jbossnetwork/restricted/softwareDetail.html?softwareId=4093&product=appplatform&version=5.1.0&downloadType=distributions




