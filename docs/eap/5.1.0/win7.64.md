# jboss eap 5.1.0 win7 64-bit

## References
* https://access.redhat.com/articles/113083#Cert_5
* https://access.redhat.com/articles/111663
* http://jbossas.jboss.org/downloads
* https://access.redhat.com/articles/112673
* http://hiplab.mc.vanderbilt.edu/projects/soempi/jboss_startstop.html

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

##### Download Application Platform 5.1.0 Binary (jboss-eap-5.1.0.zip)
* https://access.redhat.com/jbossnetwork/restricted/softwareDetail.html?softwareId=4063&product=appplatform&version=5.1.0&downloadType=distributions

##### Extract the contents of jboss-eap-5.1.0.zip
* Target: $DEV\jboss\eap\5.1.0

##### Create a JAVA_HOME environment variable
* Start > Control Panel > System > Advanced System Settings > Environment Variables > System Variables > Path > Edit
* Append the following: $DEV\java\6\64\jdk1.6.0_45\bin;

##### Start JBoss
	$JBOSS_HOME\bin\run.bat
```

$ ./run.bat
Calling C:\DEV\jboss\eap\5.1.0\jboss-as\bin\run.conf.bat
JAVA_HOME is not set. Unexpected results may occur.
Set JAVA_HOME to the directory of your local JDK to avoid this message.
===============================================================================

  JBoss Bootstrap Environment

  JBOSS_HOME: C:\DEV\jboss\eap\5.1.0\jboss-as

  JAVA: java

  JAVA_OPTS: -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warni                                                        ng=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=                                                      3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Dsun.rmi.dgc.client.gcInte                                                      rval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dorg.jboss.resolver.warnin                                                      g=true -Dprogram.name=run.bat -server

  CLASSPATH: C:\DEV\jboss\eap\5.1.0\jboss-as\bin\run.jar

===============================================================================
```
##### Stop JBoss
	ctrl-c
