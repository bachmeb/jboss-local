# jboss eap 7.0.0 ubuntu linux

## References
* 

In this tutorial $DEV refers to /home/bachmeb/DEV on my system. For consistency, you can choose any folder you like to be your DEV folder. 

##### Download and install the Java JDK 
* https://github.com/bachmeb/java-jdk/blob/master/docs/linux/ubuntu

##### Login to Red Hat
* http://access.redhat.com

##### Get a direct link to JBoss EAP 7.0.0 beta
* http://developers.redhat.com/products/eap/download/

##### Download JBoss EAP 7.0.0 beta
*Each direct link provided by Red Hat is unique and expires after a short amount of time*
```
wget [the direct link to jboss eap 7.0.0 beta]
```

##### Make a jboss directory
```
mkdir -p ~/DEV/jboss/eap/7.0.0
```

##### Intall unzip
```
sudo apt-get install unzip
```

##### Unzip the contents of jboss-eap-7.0.0.Beta.zip to the DEV folder
```
unzip jboss-eap-7.0.0.Beta.zip -d jboss/eap/7.0.0
```

<!--
##### Set JBOSS_HOME 
-->

##### Set JAVA_HOME and JAVA in run.conf
* JAVA_HOME="/Library/Java/JavaVirtualMachines/1.6.0_65-b14-462.jdk/Contents/Commands/"
* JAVA="/Library/Java/JavaVirtualMachines/1.6.0_65-b14-462.jdk/Contents/Commands/java"

```bash
## -*- shell-script -*- ######################################################
##                                                                          ##
##  JBoss Bootstrap Script Configuration                                    ##
##                                                                          ##
##############################################################################

### $Id: run.conf 91533 2009-07-22 01:20:05Z gbadner $

#
# This shell script is sourced by run.sh to initialize the environment 
# variables that run.sh uses. It is recommended to use this file to
# configure these variables, rather than modifying run.sh itself. 
#

#
# Specify the maximum file descriptor limit, use "max" or "maximum" to use
# the default, as queried by the system.
#
# Defaults to "maximum"
#
#MAX_FD="maximum"

#
# Specify the JBoss Profiler configuration file to load.
#
# Default is to not load a JBoss Profiler configuration file.
#
#PROFILER=""

#
# Specify the location of the Java home directory.  If set then $JAVA will
# be defined to $JAVA_HOME/bin/java, else $JAVA will be "java".
#
#JAVA_HOME="/usr/java/jdk1.6.0"

JAVA_HOME="/Library/Java/JavaVirtualMachines/1.6.0_65-b14-462.jdk/Contents/Commands/"

#
# Specify the exact Java VM executable to use.
#
#JAVA=""

JAVA="/Library/Java/JavaVirtualMachines/1.6.0_65-b14-462.jdk/Contents/Commands/java"

#
# Specify options to pass to the Java VM.
#
if [ "x$JAVA_OPTS" = "x" ]; then
   JAVA_OPTS="-Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true"
fi
## Specify the Security Manager options
#JAVA_OPTS="$JAVA_OPTS -Djava.security.manager -Djava.security.policy=$POLICY"

# Sample JPDA settings for remote socket debugging
#JAVA_OPTS="$JAVA_OPTS -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n"

# Sample JPDA settings for shared memory debugging 
#JAVA_OPTS="$JAVA_OPTS -Xrunjdwp:transport=dt_shmem,address=jboss,server=y,suspend=n"
```



##### Start JBoss from Terminal
	$JBOSS_HOME/bin/run.sh
```
=========================================================================

  JBoss Bootstrap Environment

  JBOSS_HOME: /Users/b/DEV/jboss/eap/5.1.0/jboss-as

  JAVA: /Library/Java/JavaVirtualMachines/1.6.0_65-b14-462.jdk/Contents/Commands/java

  JAVA_OPTS: -Dprogram.name=run.sh -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true

  CLASSPATH: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/bin/run.jar

=========================================================================

18:55:31,895 INFO  [ServerImpl] Starting JBoss (Microcontainer)...
18:55:31,895 INFO  [ServerImpl] Release ID: JBoss [EAP] 5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)
18:55:31,896 INFO  [ServerImpl] Bootstrap URL: null
18:55:31,896 INFO  [ServerImpl] Home Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as
18:55:31,896 INFO  [ServerImpl] Home URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/
18:55:31,896 INFO  [ServerImpl] Library URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/lib/
18:55:31,897 INFO  [ServerImpl] Patch URL: null
18:55:31,897 INFO  [ServerImpl] Common Base URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/common/
18:55:31,897 INFO  [ServerImpl] Common Library URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/common/lib/
18:55:31,898 INFO  [ServerImpl] Server Name: default
18:55:31,898 INFO  [ServerImpl] Server Base Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server
18:55:31,898 INFO  [ServerImpl] Server Base URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/
18:55:31,898 INFO  [ServerImpl] Server Config URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/conf/
18:55:31,898 INFO  [ServerImpl] Server Home Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default
18:55:31,899 INFO  [ServerImpl] Server Home URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/
18:55:31,899 INFO  [ServerImpl] Server Data Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/data
18:55:31,899 INFO  [ServerImpl] Server Library URL: file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/lib/
18:55:31,899 INFO  [ServerImpl] Server Log Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/log
18:55:31,899 INFO  [ServerImpl] Server Native Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/tmp/native
18:55:31,900 INFO  [ServerImpl] Server Temp Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/tmp
18:55:31,900 INFO  [ServerImpl] Server Temp Deploy Dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/tmp/deploy
18:55:32,543 INFO  [ServerImpl] Starting Microcontainer, bootstrapURL=file:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/conf/bootstrap.xml
18:55:33,016 INFO  [VFSCacheFactory] Initializing VFSCache [org.jboss.virtual.plugins.cache.CombinedVFSCache]
18:55:33,020 INFO  [VFSCacheFactory] Using VFSCache [CombinedVFSCache[real-cache: null]]
18:55:33,270 INFO  [CopyMechanism] VFS temp dir: /Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/tmp
18:55:33,271 INFO  [ZipEntryContext] VFS force nested jars copy-mode is enabled.
18:55:34,546 INFO  [ServerInfo] Java version: 1.6.0_65,Apple Inc.
18:55:34,546 INFO  [ServerInfo] Java Runtime: Java(TM) SE Runtime Environment (build 1.6.0_65-b14-462-11M4833)
18:55:34,546 INFO  [ServerInfo] Java VM: Java HotSpot(TM) 64-Bit Server VM 20.65-b04-462,Apple Inc.
18:55:34,546 INFO  [ServerInfo] OS-System: Mac OS X 10.10.5,x86_64
18:55:34,549 INFO  [ServerInfo] VM arguments: -Dprogram.name=run.sh -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Djava.endorsed.dirs=/Users/b/DEV/jboss/eap/5.1.0/jboss-as/lib/endorsed 
18:55:34,583 INFO  [JMXKernel] Legacy JMX core initialized
18:55:38,033 INFO  [WebService] Using RMI server codebase: http://127.0.0.1:8083/
18:55:42,199 INFO  [NativeServerConfig] JBoss Web Services - Stack Native Core
18:55:42,199 INFO  [NativeServerConfig] 3.1.2.SP7
18:55:43,191 INFO  [LogNotificationListener] Adding notification listener for logging mbean "jboss.system:service=Logging,type=Log4jService" to server org.jboss.mx.server.MBeanServerImpl@6e05b24c[ defaultDomain='jboss' ]
18:55:48,047 INFO  [MailService] Mail Service bound to java:/Mail
18:55:48,566 WARN  [JBossASSecurityMetadataStore] WARNING! POTENTIAL SECURITY RISK. It has been detected that the MessageSucker component which sucks messages from one node to another has not had its password changed from the installation default. Please see the JBoss Messaging user guide for instructions on how to do this.
18:55:49,584 INFO  [TransactionManagerService] JBossTS Transaction Service (JTA version - tag:JBOSSTS_4_6_1_GA_CP07) - JBoss Inc.
18:55:49,584 INFO  [TransactionManagerService] Setting up property manager MBean and JMX layer
18:55:49,760 INFO  [TransactionManagerService] Initializing recovery manager
18:55:49,839 INFO  [TransactionManagerService] Recovery manager configured
18:55:49,841 INFO  [TransactionManagerService] Binding TransactionManager JNDI Reference
18:55:49,864 INFO  [TransactionManagerService] Starting transaction recovery manager
18:55:50,167 INFO  [AprLifecycleListener] The Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: .:/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java
18:55:50,226 INFO  [Http11Protocol] Initializing Coyote HTTP/1.1 on http-127.0.0.1-8080
18:55:50,227 INFO  [AjpProtocol] Initializing Coyote AJP/1.3 on ajp-127.0.0.1-8009
18:55:50,250 INFO  [StandardService] Starting service jboss.web
18:55:50,253 INFO  [StandardEngine] Starting Servlet Engine: JBoss Web/2.1.10
18:55:50,308 INFO  [Catalina] Server startup in 79 ms
18:55:50,332 INFO  [TomcatDeployment] deploy, ctxPath=/jbossws
18:55:51,070 INFO  [TomcatDeployment] deploy, ctxPath=/web-console
18:55:51,383 INFO  [TomcatDeployment] deploy, ctxPath=/invoker
18:55:51,519 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/jboss-local-jdbc.rar/META-INF/ra.xml
18:55:51,540 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/jboss-xa-jdbc.rar/META-INF/ra.xml
18:55:51,552 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/jms-ra.rar/META-INF/ra.xml
18:55:51,569 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/mail-ra.rar/META-INF/ra.xml
18:55:51,595 INFO  [RARDeployment] Required license terms exist, view vfsfile:/Users/b/DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/quartz-ra.rar/META-INF/ra.xml
18:55:51,723 INFO  [SimpleThreadPool] Job execution threads will use class loader of thread: main
18:55:51,790 INFO  [QuartzScheduler] Quartz Scheduler v.1.5.2 created.
18:55:51,795 INFO  [RAMJobStore] RAMJobStore initialized.
18:55:51,795 INFO  [StdSchedulerFactory] Quartz scheduler 'DefaultQuartzScheduler' initialized from default resource file in Quartz package: 'quartz.properties'
18:55:51,796 INFO  [StdSchedulerFactory] Quartz scheduler version: 1.5.2
18:55:51,801 INFO  [QuartzScheduler] Scheduler DefaultQuartzScheduler_$_NON_CLUSTERED started.
18:55:52,587 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=DataSourceBinding,name=DefaultDS' to JNDI name 'java:DefaultDS'
18:55:53,127 INFO  [ServerPeer] JBoss Messaging 1.4.7.GA server [0] started
18:55:53,239 INFO  [ConnectionFactoryJNDIMapper] supportsFailover attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support failover
18:55:53,239 INFO  [ConnectionFactoryJNDIMapper] supportsLoadBalancing attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support load balancing
18:55:53,308 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
18:55:53,308 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@2227ca20 started
18:55:53,327 INFO  [QueueService] Queue[/queue/ExpiryQueue] started, fullSize=200000, pageSize=2000, downCacheSize=2000
18:55:53,329 INFO  [QueueService] Queue[/queue/DLQ] started, fullSize=200000, pageSize=2000, downCacheSize=2000
18:55:53,331 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
18:55:53,332 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@25f7e4cf started
18:55:53,333 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
18:55:53,333 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@584b9b00 started
18:55:53,465 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=ConnectionFactoryBinding,name=JmsXA' to JNDI name 'java:JmsXA'
18:55:53,578 INFO  [TomcatDeployment] deploy, ctxPath=/admin-console
18:55:53,640 INFO  [config] Initializing Mojarra (1.2_13-b01-FCS) for context '/admin-console'
18:55:55,716 INFO  [TomcatDeployment] deploy, ctxPath=/
18:55:55,753 INFO  [TomcatDeployment] deploy, ctxPath=/jmx-console
18:55:55,796 INFO  [ProfileServiceBootstrap] Loading profile: ProfileKey@734f0076[domain=default, server=default, name=default]
18:55:55,805 INFO  [Http11Protocol] Starting Coyote HTTP/1.1 on http-127.0.0.1-8080
18:55:55,822 INFO  [AjpProtocol] Starting Coyote AJP/1.3 on ajp-127.0.0.1-8009
18:55:55,831 INFO  [ServerImpl] JBoss (Microcontainer) [5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)] Started in 23s:929ms
```

##### Go to the JBoss welcome page
* http://localhost:8080/

##### Enable the admin username and password for the JBoss EAP Admin Console
* Edit $DEV/jboss/eap/5.1.0/jboss-as/server/default/deploy/management/console-mgr.sar/web-console.war/WEB-INF/classes/web-console-users.properties
```
# A sample users.properties file for use with the UsersRolesLoginModule
admin=admin
```
* Edit $DEV/jboss/eap/5.1.0/jboss-as/server/default/conf/props/jmx-console-users.properties
```
# A sample users.properties file for use with the UsersRolesLoginModule
admin=admin
```

##### Stop JBoss
	ctrl-c

##### Restart JBoss
	$JBOSS_HOME/bin/run.bat

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
