# jboss 5 eap win7.64

## References
* https://access.redhat.com/articles/113083#Cert_5
* https://access.redhat.com/articles/111663
* http://jbossas.jboss.org/downloads

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

##### Download 
