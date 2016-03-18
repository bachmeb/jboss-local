# jboss eap 5.1.0 win7 64-bit

## References
* https://access.redhat.com/articles/113083#Cert_5
* https://access.redhat.com/articles/111663
* http://jbossas.jboss.org/downloads
* https://access.redhat.com/articles/112673
* http://hiplab.mc.vanderbilt.edu/projects/soempi/jboss_startstop.html
* http://stackoverflow.com/questions/5415608/jboss-default-password
* http://centerkey.com/jboss/
* https://docs.jboss.org/jbossas/docs/Installation_Guide/4/html/setting_JBOSS_HOME_windows.html

In this tutorial $DEV refers to C:\DEV\ on my system. For consistency, you can choose any folder you like to be your DEV folder. 

##### Download JDK 1.6 update 45, 64-bit
* http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html#jdk-6u45-oth-JPR

##### Install JDK 1.6 update 45
* The filename is jdk-6u45-windows-x64.exe
* *Custom Setup*
	* Install to: $DEV\java\6\64\jdk1.6.0_45\

##### Set JAVA_HOME 
* Start > Control Panel > System > Advanced System Settings > Environment Variables > User variables > New
  * Variable name: JAVA_HOME
  * Variable value: $DEV\java\6\64\jdk1.6.0_45

##### Add bin folder to the PATH user environment variable
* Start > Control Panel > System > Advanced System Settings > Environment Variables > User variables > PATH > Edit
* Append the following: %JAVA_HOME%\bin;

##### Confirm the Java version from the command line
* Start > cmd
```
java -version
```

##### Login to the Red Hat Customer Portal
* https://access.redhat.com/

##### Download Application Platform 5.1.0 Binary (jboss-eap-5.1.0.zip)
* https://access.redhat.com/jbossnetwork/restricted/softwareDetail.html?softwareId=4063&product=appplatform&version=5.1.0&downloadType=distributions

##### Use 7-zip to unpack the contents of jboss-eap-5.1.0.zip
*Do not use Windows Explorer!*

* Target: $DEV\jboss\eap\5.1.0

##### Set JBOSS_HOME 
* Start > Control Panel > System > Advanced System Settings > Environment Variables > User variables > New
  * Variable name: JBOSS_HOME
  * Variable value: $DEV\jboss\eap\5.1.0\jboss-as

##### Set JAVA_HOME, break up the JAVA_OPTS assignments, and add pause statements to run.conf.bat
```bat
rem ### -*- batch file -*- ######################################################
rem #                                                                          ##
rem #  JBoss Bootstrap Script Configuration                                    ##
rem #                                                                          ##
rem #############################################################################

rem # $Id: run.conf.bat 102595 2010-03-18 22:14:39Z rrajesh $

rem #
rem # This batch file is executed by run.bat to initialize the environment 
rem # variables that run.bat uses. It is recommended to use this file to
rem # configure these variables, rather than modifying run.bat itself. 
rem #

echo JAVA_OPTS: %JAVA_OPTS%
pause 
if not "x%JAVA_OPTS%" == "x" goto JAVA_OPTS_SET

rem #
rem # Specify the JBoss Profiler configuration file to load.
rem #
rem # Default is to not load a JBoss Profiler configuration file.
rem #
rem set "PROFILER=%JBOSS_HOME%\bin\jboss-profiler.properties"

rem #
rem # Specify the location of the Java home directory (it is recommended that
rem # this always be set). If set, then "%JAVA_HOME%\bin\java" will be used as
rem # the Java VM executable; otherwise, "%JAVA%" will be used (see below).
rem #
rem set "JAVA_HOME=C:\opt\jdk1.6.0_13"
set "JAVA_HOME=C:\DEV\java\6\64\jdk1.6.0_45"
echo JAVA_HOME: %JAVA_HOME%
pause 

rem #
rem # Specify the exact Java VM executable to use - only used if JAVA_HOME is
rem # not set. Default is "java".
rem #
rem set "JAVA=C:\opt\jdk1.6.0_13\bin\java"

rem #
rem # Specify options to pass to the Java VM. Note, there are some additional
rem # options that are always passed by run.bat.
rem #

echo JAVA_OPTS: %JAVA_OPTS%
pause 

set "JAVA_OPTS="

rem # JVM memory allocation pool parameters - modify as appropriate.

rem # -Xms: Initial heap size
set "JAVA_OPTS=%JAVA_OPTS% -Xms1024m"

rem # -Xmx: Maximum java heap size
set "JAVA_OPTS=%JAVA_OPTS% -Xmx1024m"

rem # -XX:MaxPermSize: Maximum java permanent space size
set "JAVA_OPTS=%JAVA_OPTS% -XX:MaxPermSize=256m"

rem # -
set "JAVA_OPTS=%JAVA_OPTS% -Dsun.lang.ClassLoader.allowArraySyntax=true"

rem # -XX:+UseParallelOldGC: 
rem # set "JAVA_OPTS=%JAVA_OPTS% -XX:+UseParallelOldGC"

rem # -XX:+UseLargePages: 
rem # set "JAVA_OPTS=%JAVA_OPTS% -XX:+UseLargePages"

rem # Reduce the RMI GCs to once per hour for Sun JVMs.
set "JAVA_OPTS=%JAVA_OPTS% -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000"

rem # Warn when resolving remote XML DTDs or schemas.
set "JAVA_OPTS=%JAVA_OPTS% -Dorg.jboss.resolver.warning=true"

rem # Sample JPDA settings for remote socket debugging
rem set "JAVA_OPTS=%JAVA_OPTS% -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n"

rem # Sample JPDA settings for shared memory debugging 
rem set "JAVA_OPTS=%JAVA_OPTS% -Xrunjdwp:transport=dt_shmem,address=jboss,server=y,suspend=n"

:JAVA_OPTS_SET

echo JAVA_OPTS: %JAVA_OPTS%
pause 

```

##### Add a pause statement to run.bat
```bat
@echo off
rem -------------------------------------------------------------------------
rem JBoss Bootstrap Script for Windows
rem -------------------------------------------------------------------------

rem $Id: run.bat 105675 2010-06-03 17:10:14Z permaine $

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT" setlocal

if "%OS%" == "Windows_NT" (
  set "DIRNAME=%~dp0%"
) else (
  set DIRNAME=.\
)

rem Read an optional configuration file.
if "x%RUN_CONF%" == "x" (
   set "RUN_CONF=%DIRNAME%run.conf.bat"
)
if exist "%RUN_CONF%" (
   echo Calling %RUN_CONF%
   call "%RUN_CONF%" %*
) else (
   echo Config file not found %RUN_CONF%
)

pushd %DIRNAME%..
if "x%JBOSS_HOME%" == "x" (
  set "JBOSS_HOME=%CD%"
)
popd

set DIRNAME=

if "%OS%" == "Windows_NT" (
  set "PROGNAME=%~nx0%"
) else (
  set "PROGNAME=run.bat"
)

rem Setup JBoss specific properties
set JAVA_OPTS=%JAVA_OPTS% -Dprogram.name=%PROGNAME%

if "x%JAVA_HOME%" == "x" (
  set  JAVA=java
  echo JAVA_HOME is not set. Unexpected results may occur.
  echo Set JAVA_HOME to the directory of your local JDK to avoid this message.
) else (
  set "JAVA=%JAVA_HOME%\bin\java"
  if exist "%JAVA_HOME%\lib\tools.jar" (
    set "JAVAC_JAR=%JAVA_HOME%\lib\tools.jar"
  )
)

rem Add -server to the JVM options, if supported
"%JAVA%" -server -version 2>&1 | findstr /I hotspot > nul
if not errorlevel == 1 (
  set "JAVA_OPTS=%JAVA_OPTS% -server"
)

rem Add native to the PATH if present
set JBOSS_NATIVE_HOME=
set CHECK_NATIVE_HOME=
if exist "%JBOSS_HOME%\bin\libtcnative-1.dll" (
  set "CHECK_NATIVE_HOME=%JBOSS_HOME%\bin"
) else if exist "%JBOSS_HOME%\..\native\bin" (
  set "CHECK_NATIVE_HOME=%JBOSS_HOME%\..\native\bin"
) else if exist "%JBOSS_HOME%\bin\native\bin" (
  set "CHECK_NATIVE_HOME=%JBOSS_HOME%\bin\native\bin"
) else if exist "%JBOSS_HOME%\..\..\jboss-ep-5.1\native\bin" (
  set "CHECK_NATIVE_HOME=%JBOSS_HOME%\..\..\jboss-ep-5.1\native\bin"
)
if "x%CHECK_NATIVE_HOME%" == "x" goto WITHOUT_JBOSS_NATIVE

rem Translate to the absolute path

pushd "%CHECK_NATIVE_HOME%"
set JBOSS_NATIVE_HOME=%CD%
popd
set CHECK_JBOSS_NATIVE_HOME=
set JAVA_OPTS=%JAVA_OPTS% "-Djava.library.path=%JBOSS_NATIVE_HOME%;%PATH%;%SYSTEMROOT%"
set PATH=%JBOSS_NATIVE_HOME%;%PATH%;%SYSTEMROOT%

:WITHOUT_JBOSS_NATIVE
rem Find run.jar, or we can't continue

if exist "%JBOSS_HOME%\bin\run.jar" (
  if "x%JAVAC_JAR%" == "x" (
    set "RUNJAR=%JBOSS_HOME%\bin\run.jar"
  ) else (
    set "RUNJAR=%JAVAC_JAR%;%JBOSS_HOME%\bin\run.jar"
  )
) else (
  echo Could not locate "%JBOSS_HOME%\bin\run.jar".
  echo Please check that you are in the bin directory when running this script.
  goto END
)

rem If JBOSS_CLASSPATH empty, don't include it, as this will
rem result in including the local directory in the classpath, which makes
rem error tracking harder.
if "x%JBOSS_CLASSPATH%" == "x" (
  set "RUN_CLASSPATH=%RUNJAR%"
) else (
  set "RUN_CLASSPATH=%JBOSS_CLASSPATH%;%RUNJAR%"
)

set JBOSS_CLASSPATH=%RUN_CLASSPATH%

rem Setup JBoss specific properties

rem Setup the java endorsed dirs
set JBOSS_ENDORSED_DIRS=%JBOSS_HOME%\lib\endorsed

echo ===============================================================================
echo.
echo   JBoss Bootstrap Environment
echo.
echo   JBOSS_HOME: %JBOSS_HOME%
echo.
echo   JAVA: %JAVA%
echo.
echo   JAVA_OPTS: %JAVA_OPTS%
echo.
echo   CLASSPATH: %JBOSS_CLASSPATH%
echo.
echo ===============================================================================
echo.

rem read the JBoss Bootstrap Environment details
pause

:RESTART
"%JAVA%" %JAVA_OPTS% ^
   -Djava.endorsed.dirs="%JBOSS_ENDORSED_DIRS%" ^
   -classpath "%JBOSS_CLASSPATH%" ^
   org.jboss.Main %*

if ERRORLEVEL 10 goto RESTART

:END
if "x%NOPAUSE%" == "x" pause

:END_NO_PAUSE

```

##### Start JBoss from the Windows command line
	$JBOSS_HOME\bin\run.bat
```
Calling C:\DEV\jboss\eap\5.1.0\jboss-as\bin\run.conf.bat
JAVA_HOME is not set. Unexpected results may occur.
Set JAVA_HOME to the directory of your local JDK to avoid this message.
===============================================================================

  JBoss Bootstrap Environment

  JBOSS_HOME: C:\DEV\jboss\eap\5.1.0\jboss-as

  JAVA: java

  JAVA_OPTS: -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dorg.jboss.resolver.warning=true -Dprogram.name=run.bat -server

  CLASSPATH: C:\DEV\jboss\eap\5.1.0\jboss-as\bin\run.jar

===============================================================================

16:04:25,555 INFO  [ServerImpl] Starting JBoss (Microcontainer)...
16:04:25,557 INFO  [ServerImpl] Release ID: JBoss [EAP] 5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)
16:04:25,557 INFO  [ServerImpl] Bootstrap URL: null
16:04:25,557 INFO  [ServerImpl] Home Dir: C:\DEV\jboss\eap\5.1.0\jboss-as
16:04:25,557 INFO  [ServerImpl] Home URL: file:/C:/DEV/jboss/eap/5.1.0/jboss-as/
16:04:25,557 INFO  [ServerImpl] Library URL: file:/C:/DEV/jboss/eap/5.1.0/jboss-as/lib/
16:04:25,558 INFO  [ServerImpl] Patch URL: null
16:04:25,559 INFO  [ServerImpl] Common Base URL: file:/C:/DEV/jboss/eap/5.1.0/jboss-as/common/
16:04:25,559 INFO  [ServerImpl] Common Library URL: file:/C:/DEV/jboss/eap/5.1.0/jboss-as/common/lib/
16:04:25,559 INFO  [ServerImpl] Server Name: default
16:04:25,559 INFO  [ServerImpl] Server Base Dir: C:\DEV\jboss\eap\5.1.0\jboss-as\server
16:04:25,559 INFO  [ServerImpl] Server Base URL: file:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/
16:04:25,559 INFO  [ServerImpl] Server Config URL: file:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/default/conf/
16:04:25,559 INFO  [ServerImpl] Server Home Dir: C:\DEV\jboss\eap\5.1.0\jboss-as\server\default
16:04:25,560 INFO  [ServerImpl] Server Home URL: file:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/default/
16:04:25,560 INFO  [ServerImpl] Server Data Dir: C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\data
16:04:25,560 INFO  [ServerImpl] Server Library URL: file:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/default/lib/
16:04:25,560 INFO  [ServerImpl] Server Log Dir: C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\log
16:04:25,560 INFO  [ServerImpl] Server Native Dir: C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\tmp\native
16:04:25,561 INFO  [ServerImpl] Server Temp Dir: C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\tmp
16:04:25,561 INFO  [ServerImpl] Server Temp Deploy Dir: C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\tmp\deploy
16:04:26,411 INFO  [ServerImpl] Starting Microcontainer, bootstrapURL=file:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/default/conf/bootstrap.xml
16:04:27,068 INFO  [VFSCacheFactory] Initializing VFSCache [org.jboss.virtual.plugins.cache.CombinedVFSCache]
16:04:27,072 INFO  [VFSCacheFactory] Using VFSCache [CombinedVFSCache[real-cache: null]]
16:04:27,411 INFO  [CopyMechanism] VFS temp dir: C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\tmp
16:04:27,412 INFO  [ZipEntryContext] VFS force nested jars copy-mode is enabled.
16:04:29,035 INFO  [ServerInfo] Java version: 1.6.0_45,Sun Microsystems Inc.
16:04:29,035 INFO  [ServerInfo] Java Runtime: Java(TM) SE Runtime Environment (build 1.6.0_45-b06)
16:04:29,035 INFO  [ServerInfo] Java VM: Java HotSpot(TM) 64-Bit Server VM 20.45-b01,Sun Microsystems Inc.
16:04:29,036 INFO  [ServerInfo] OS-System: Windows 7 6.1,amd64
16:04:29,037 INFO  [ServerInfo] VM arguments: -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dorg.jboss.resolver.warning=true -Dprogram.name=run.bat -Djava.endorsed.dirs=C:\DEV\jboss\eap\5.1.0\jboss-as\lib\endorsed
16:04:29,078 INFO  [JMXKernel] Legacy JMX core initialized
16:04:34,850 INFO  [WebService] Using RMI server codebase: http://127.0.0.1:8083/
16:04:39,885 INFO  [NativeServerConfig] JBoss Web Services - Stack Native Core
16:04:39,885 INFO  [NativeServerConfig] 3.1.2.SP7
16:04:41,626 INFO  [LogNotificationListener] Adding notification listener for logging mbean "jboss.system:service=Logging,type=Log4jService" to server org.jboss.mx.server.MBeanServerImpl@7f0eeb26[ defaultDomain='jboss' ]
16:04:50,560 INFO  [MailService] Mail Service bound to java:/Mail
16:04:52,089 WARN  [JBossASSecurityMetadataStore] WARNING! POTENTIAL SECURITY RISK. It has been detected that the MessageSucker component which sucks messages from one node to another has not had its password changed from the installation default. Please see the JBoss Messaging user guide for instructions on how to do this.
16:04:54,152 INFO  [TransactionManagerService] JBossTS Transaction Service (JTA version - tag:JBOSSTS_4_6_1_GA_CP07) - JBoss Inc.
16:04:54,153 INFO  [TransactionManagerService] Setting up property manager MBean and JMX layer
16:04:54,672 INFO  [TransactionManagerService] Initializing recovery manager
16:04:54,835 INFO  [TransactionManagerService] Recovery manager configured
16:04:54,835 INFO  [TransactionManagerService] Binding TransactionManager JNDI Reference
16:04:54,886 INFO  [TransactionManagerService] Starting transaction recovery manager
16:04:55,413 INFO  [AprLifecycleListener] The Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: C:\DEV\java\6\64\jdk1.6.0_45\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;C:\Users\bbachmey\bin;C:\Program Files\Git\mingw64\bin;C:\Program Files\Git\usr\local\bin;C:\Program Files\Git\usr\bin;C:\Program Files\Git\usr\bin;C:\Program Files\Git\mingw64\bin;C:\Program Files\Git\usr\bin;C:\Users\bbachmey\bin;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0;C:\Program Files\Git\cmd;C:\Program Files\Git\mingw64\bin;C:\Program Files\Git\usr\bin;C:\DEV\gradle\gradle-2.8\bin;C:\DEV\ant\apache-ant-1.9.6\bin;C:\DEV\java\6\64\jdk1.6.0_45\bin;C:\Program Files\Git\usr\bin\vendor_perl;C:\Program Files\Git\usr\bin\core_perl;.
16:04:55,483 INFO  [Http11Protocol] Initializing Coyote HTTP/1.1 on http-127.0.0.1-8080
16:04:55,484 INFO  [AjpProtocol] Initializing Coyote AJP/1.3 on ajp-127.0.0.1-8009
16:04:55,514 INFO  [StandardService] Starting service jboss.web
16:04:55,517 INFO  [StandardEngine] Starting Servlet Engine: JBoss Web/2.1.10
16:04:55,585 INFO  [Catalina] Server startup in 100 ms
16:04:55,606 INFO  [TomcatDeployment] deploy, ctxPath=/web-console
16:04:56,636 INFO  [TomcatDeployment] deploy, ctxPath=/invoker
16:04:56,707 INFO  [TomcatDeployment] deploy, ctxPath=/jbossws
16:04:56,831 INFO  [RARDeployment] Required license terms exist, view vfsfile:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/jboss-local-jdbc.rar/META-INF/ra.xml
16:04:56,849 INFO  [RARDeployment] Required license terms exist, view vfsfile:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/jboss-xa-jdbc.rar/META-INF/ra.xml
16:04:56,864 INFO  [RARDeployment] Required license terms exist, view vfsfile:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/jms-ra.rar/META-INF/ra.xml
16:04:56,883 INFO  [RARDeployment] Required license terms exist, view vfsfile:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/mail-ra.rar/META-INF/ra.xml
16:04:56,913 INFO  [RARDeployment] Required license terms exist, view vfsfile:/C:/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/quartz-ra.rar/META-INF/ra.xml
16:04:57,049 INFO  [SimpleThreadPool] Job execution threads will use class loader of thread: main
16:04:57,092 INFO  [QuartzScheduler] Quartz Scheduler v.1.5.2 created.
16:04:57,097 INFO  [RAMJobStore] RAMJobStore initialized.
16:04:57,097 INFO  [StdSchedulerFactory] Quartz scheduler 'DefaultQuartzScheduler' initialized from default resource file in Quartz package: 'quartz.properties'
16:04:57,097 INFO  [StdSchedulerFactory] Quartz scheduler version: 1.5.2
16:04:57,101 INFO  [QuartzScheduler] Scheduler DefaultQuartzScheduler_$_NON_CLUSTERED started.
16:04:57,908 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=DataSourceBinding,name=DefaultDS' to JNDI name 'java:DefaultDS'
16:04:58,571 INFO  [ServerPeer] JBoss Messaging 1.4.7.GA server [0] started
16:04:58,711 INFO  [QueueService] Queue[/queue/ExpiryQueue] started, fullSize=200000, pageSize=2000, downCacheSize=2000
16:04:58,713 INFO  [QueueService] Queue[/queue/DLQ] started, fullSize=200000, pageSize=2000, downCacheSize=2000
16:04:58,841 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
16:04:58,841 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@6ffb75c7 started
16:04:58,842 INFO  [ConnectionFactoryJNDIMapper] supportsFailover attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support failover
16:04:58,842 INFO  [ConnectionFactoryJNDIMapper] supportsLoadBalancing attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support load balancing
16:04:58,843 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
16:04:58,844 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@44e00985 started
16:04:58,844 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
16:04:58,845 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@6bb9ae1a started
16:04:59,043 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=ConnectionFactoryBinding,name=JmsXA' to JNDI name 'java:JmsXA'
16:04:59,253 INFO  [TomcatDeployment] deploy, ctxPath=/admin-console
16:04:59,404 INFO  [config] Initializing Mojarra (1.2_13-b01-FCS) for context '/admin-console'
16:05:03,372 INFO  [TomcatDeployment] deploy, ctxPath=/
16:05:03,435 INFO  [TomcatDeployment] deploy, ctxPath=/jmx-console
16:05:03,609 INFO  [ProfileServiceBootstrap] Loading profile: ProfileKey@6eb92a0e[domain=default, server=default, name=default]
16:05:03,624 INFO  [Http11Protocol] Starting Coyote HTTP/1.1 on http-127.0.0.1-8080
16:05:03,648 INFO  [AjpProtocol] Starting Coyote AJP/1.3 on ajp-127.0.0.1-8009
16:05:03,656 INFO  [ServerImpl] JBoss (Microcontainer) [5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)] Started in 38s:92ms

```

##### Go to the JBoss welcome page
* http://localhost:8080/

##### Enable the admin username and password for the JBoss EAP Admin Console
* Edit $DEV\jboss\eap\5.1.0\jboss-as\server\default\deploy\management\console-mgr.sar\web-console.war\WEB-INF\classes\web-console-users.properties
```
# A sample users.properties file for use with the UsersRolesLoginModule
admin=admin
```
* Edit $DEV\jboss\eap\5.1.0\jboss-as\server\default\conf\props\jmx-console-users.properties
```
# A sample users.properties file for use with the UsersRolesLoginModule
admin=admin
```

##### Stop JBoss
	ctrl-c

##### Restart JBoss
	$JBOSS_HOME\bin\run.bat

##### Go to the admin console
* http://localhost:8080/admin-console/

##### Login with the admin credentials
* admin/admin

```
16:35:48,308 INFO  [BootstrapAction] Initializing Administration Console v1.3.4.SP1...
16:35:54,577 INFO  [PluginContainerResourceManager] Discovering Resources...
```

##### Go to the JMX console
* http://localhost:8080/jmx-console/

##### Login with the admin credentials
* admin/admin
