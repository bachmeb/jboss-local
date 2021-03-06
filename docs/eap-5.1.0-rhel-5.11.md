# jboss 5.1.0 rhel 5.11

## References
* https://access.redhat.com/articles/113083
* https://access.redhat.com/articles/111663
* https://access.redhat.com/documentation/en-US/JBoss_Enterprise_Application_Platform/6.2/pdf/Installation_Guide/JBoss_Enterprise_Application_Platform-6.2-Installation_Guide-en-US.pdf
* http://stackoverflow.com/questions/4560591/problem-while-starting-jboss-in-linux
* http://serverfault.com/questions/156909/jboss-tools-allow-remote-access-to-localhost8080
* http://stackoverflow.com/questions/6204784/jboss-only-works-on-localhost8080-but-doesnt-reply-when-called-by-ip
* https://docs.jboss.org/jbossas/docs/Installation_Guide/4/html/setting_JBOSS_HOME_linux.html

##### Download and install Java JDK 1.6
* https://github.com/bachmeb/java-jdk/

##### Login to Red Hat
* http://access.redhat.com

##### Get a direct link to Application Platform 5.1.0 Binary
* https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?downloadType=distributions&product=appplatform&version=5.1.0

##### Download Application Platform 5.1.0 Binary
*Each direct link provided by Red Hat is unique and expires after a short amount of time*
```
cd ~/Downloads
wget https://access.redhat.com/cspdownload/[this is the random]/[part of the url]/JBEAP-5.1.0/jboss-eap-5.1.0.zip
```

##### Create a jboss directory in /opt
```
sudo mkdir /opt/jboss
```

##### Give your account permission to write to the jboss directory
```
sudo chown -R [YOUR USER ACCOUNT] /opt/jboss
```

##### Unzip the contents of jboss-eap-5.1.0.zip
```
cd /opt/jboss
unzip ~/Downloads/jboss-eap-5.1.0.zip
```

##### List the contents of the new directory
```
ls -laR /opt/jboss/jboss-eap-5.1
```

##### Set the JBOSS_HOME environment variable
```
export JBOSS_HOME='/opt/jboss/jboss-eap-5.1/jboss-as'
```

##### Echo the JBOSS_HOME environment variable
```
echo $JBOSS_HOME
```

##### List the contents of the JBOSS_HOME/ directory
```
ls -l $JBOSS_HOME
```

##### Add the command to set $JBOSS_HOME to the .bashrc file
```
echo 'export JBOSS_HOME="/opt/jboss/jboss-eap-5.1/jboss-as"' >> ~/.bashrc
```

##### List the contents of the bin directory
```
ls -l $JBOSS_HOME/bin
```

##### Add the bin directory to PATH 
```
export PATH=$PATH:$JBOSS_HOME/bin
```

##### Add the command to set $JBOSS_HOME to the .bashrc file
```
echo 'PATH=$PATH:$JBOSS_HOME/bin' >> ~/.bashrc
```

##### Read the .bashrc file
```
cat ~/.bashrc
```

##### Read the startup script 
```
less $JBOSS_HOME/bin/run.sh 
```

##### Run the startup script 
```
$JBOSS_HOME/bin/run.sh 
```
```c
/*
=========================================================================

  JBoss Bootstrap Environment

  JBOSS_HOME: /opt/jboss/jboss-eap-5.1/jboss-as

  JAVA: java

  JAVA_OPTS: -Dprogram.name=run.sh -server -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Djava.net.preferIPv4Stack=true

  CLASSPATH: /opt/jboss/jboss-eap-5.1/jboss-as/bin/run.jar

=========================================================================

11:05:34,247 INFO  [ServerImpl] Starting JBoss (Microcontainer)...
11:05:34,248 INFO  [ServerImpl] Release ID: JBoss [EAP] 5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)
11:05:34,248 INFO  [ServerImpl] Bootstrap URL: null
11:05:34,248 INFO  [ServerImpl] Home Dir: /opt/jboss/jboss-eap-5.1/jboss-as
11:05:34,248 INFO  [ServerImpl] Home URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/
11:05:34,248 INFO  [ServerImpl] Library URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/lib/
11:05:34,249 INFO  [ServerImpl] Patch URL: null
11:05:34,250 INFO  [ServerImpl] Common Base URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/common/
11:05:34,250 INFO  [ServerImpl] Common Library URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/common/lib/
11:05:34,250 INFO  [ServerImpl] Server Name: default
11:05:34,250 INFO  [ServerImpl] Server Base Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server
11:05:34,250 INFO  [ServerImpl] Server Base URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/server/
11:05:34,250 INFO  [ServerImpl] Server Config URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/conf/
11:05:34,251 INFO  [ServerImpl] Server Home Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default
11:05:34,251 INFO  [ServerImpl] Server Home URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/
11:05:34,251 INFO  [ServerImpl] Server Data Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/data
11:05:34,251 INFO  [ServerImpl] Server Library URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/lib/
11:05:34,251 INFO  [ServerImpl] Server Log Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/log
11:05:34,251 INFO  [ServerImpl] Server Native Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/tmp/native
11:05:34,251 INFO  [ServerImpl] Server Temp Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/tmp
11:05:34,252 INFO  [ServerImpl] Server Temp Deploy Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/tmp/deploy
11:05:35,057 INFO  [ServerImpl] Starting Microcontainer, bootstrapURL=file:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/conf/bootstrap.xml
11:05:35,711 INFO  [VFSCacheFactory] Initializing VFSCache [org.jboss.virtual.plugins.cache.CombinedVFSCache]
11:05:35,714 INFO  [VFSCacheFactory] Using VFSCache [CombinedVFSCache[real-cache: null]]
11:05:36,057 INFO  [CopyMechanism] VFS temp dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/tmp
11:05:36,058 INFO  [ZipEntryContext] VFS force nested jars copy-mode is enabled.
11:05:37,368 INFO  [ServerInfo] Java version: 1.6.0_38,Sun Microsystems Inc.
11:05:37,368 INFO  [ServerInfo] Java Runtime: OpenJDK Runtime Environment (build 1.6.0_38-b38)
11:05:37,368 INFO  [ServerInfo] Java VM: OpenJDK 64-Bit Server VM 23.25-b01,Sun Microsystems Inc.
11:05:37,369 INFO  [ServerInfo] OS-System: Linux 2.6.18-409.el5,amd64
11:05:37,370 INFO  [ServerInfo] VM arguments: -Dprogram.name=run.sh -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Djava.net.preferIPv4Stack=true -Djava.endorsed.dirs=/opt/jboss/jboss-eap-5.1/jboss-as/lib/endorsed
11:05:37,408 INFO  [JMXKernel] Legacy JMX core initialized
11:05:42,759 INFO  [WebService] Using RMI server codebase: http://127.0.0.1:8083/
11:05:47,634 INFO  [NativeServerConfig] JBoss Web Services - Stack Native Core
11:05:47,634 INFO  [NativeServerConfig] 3.1.2.SP7
11:05:48,687 INFO  [LogNotificationListener] Adding notification listener for logging mbean "jboss.system:service=Logging,type=Log4jService" to server org.jboss.mx.server.MBeanServerImpl@20733e41[ defaultDomain='jboss' ]
11:05:54,665 INFO  [MailService] Mail Service bound to java:/Mail
11:05:55,336 WARN  [JBossASSecurityMetadataStore] WARNING! POTENTIAL SECURITY RISK. It has been detected that the MessageSucker component which sucks messages from one node to another has not had its password changed from the installation default. Please see the JBoss Messaging user guide for instructions on how to do this.
11:05:57,136 INFO  [TransactionManagerService] JBossTS Transaction Service (JTA version - tag:JBOSSTS_4_6_1_GA_CP07) - JBoss Inc.
11:05:57,136 INFO  [TransactionManagerService] Setting up property manager MBean and JMX layer
11:05:57,359 INFO  [TransactionManagerService] Initializing recovery manager
11:05:57,475 INFO  [TransactionManagerService] Recovery manager configured
11:05:57,475 INFO  [TransactionManagerService] Binding TransactionManager JNDI Reference
11:05:57,507 INFO  [TransactionManagerService] Starting transaction recovery manager
11:05:57,899 INFO  [AprLifecycleListener] The Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: /usr/lib/jvm/java-1.6.0-openjdk-1.6.0.38.x86_64/jre/lib/amd64/server:/usr/lib/jvm/java-1.6.0-openjdk-1.6.0.38.x86_64/jre/lib/amd64:/usr/lib/jvm/java-1.6.0-openjdk-1.6.0.38.x86_64/jre/../lib/amd64:/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
11:05:58,181 INFO  [Http11Protocol] Initializing Coyote HTTP/1.1 on http-127.0.0.1-8080
11:05:58,182 INFO  [AjpProtocol] Initializing Coyote AJP/1.3 on ajp-127.0.0.1-8009
11:05:58,209 INFO  [StandardService] Starting service jboss.web
11:05:58,212 INFO  [StandardEngine] Starting Servlet Engine: JBoss Web/2.1.10
11:05:58,303 INFO  [Catalina] Server startup in 120 ms
11:05:58,324 INFO  [TomcatDeployment] deploy, ctxPath=/invoker
11:05:58,962 INFO  [TomcatDeployment] deploy, ctxPath=/web-console
11:05:59,222 INFO  [TomcatDeployment] deploy, ctxPath=/jbossws
11:05:59,325 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/jboss-local-jdbc.rar/META-INF/ra.xml
11:05:59,341 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/jboss-xa-jdbc.rar/META-INF/ra.xml
11:05:59,350 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/jms-ra.rar/META-INF/ra.xml
11:05:59,364 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/mail-ra.rar/META-INF/ra.xml
11:05:59,384 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/quartz-ra.rar/META-INF/ra.xml
11:05:59,481 INFO  [SimpleThreadPool] Job execution threads will use class loader of thread: main
11:05:59,511 INFO  [QuartzScheduler] Quartz Scheduler v.1.5.2 created.
11:05:59,514 INFO  [RAMJobStore] RAMJobStore initialized.
11:05:59,515 INFO  [StdSchedulerFactory] Quartz scheduler 'DefaultQuartzScheduler' initialized from default resource file in Quartz package: 'quartz.properties'
11:05:59,515 INFO  [StdSchedulerFactory] Quartz scheduler version: 1.5.2
11:05:59,517 INFO  [QuartzScheduler] Scheduler DefaultQuartzScheduler_$_NON_CLUSTERED started.
11:06:00,005 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=DataSourceBinding,name=DefaultDS' to JNDI name 'java:DefaultDS'
11:06:00,543 INFO  [ServerPeer] JBoss Messaging 1.4.7.GA server [0] started
11:06:00,653 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
11:06:00,653 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@26ef56eb started
11:06:00,703 INFO  [QueueService] Queue[/queue/DLQ] started, fullSize=200000, pageSize=2000, downCacheSize=2000
11:06:00,704 INFO  [ConnectionFactoryJNDIMapper] supportsFailover attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support failover
11:06:00,704 INFO  [ConnectionFactoryJNDIMapper] supportsLoadBalancing attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support load balancing
11:06:00,706 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
11:06:00,706 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@513212fc started
11:06:00,708 INFO  [QueueService] Queue[/queue/ExpiryQueue] started, fullSize=200000, pageSize=2000, downCacheSize=2000
11:06:00,709 INFO  [ConnectionFactory] Connector bisocket://127.0.0.1:4457 has leasing enabled, lease period 10000 milliseconds
11:06:00,709 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@6dcc664b started
11:06:00,818 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=ConnectionFactoryBinding,name=JmsXA' to JNDI name 'java:JmsXA'
11:06:00,990 INFO  [TomcatDeployment] deploy, ctxPath=/admin-console
11:06:01,081 INFO  [config] Initializing Mojarra (1.2_13-b01-FCS) for context '/admin-console'
11:06:04,206 INFO  [TomcatDeployment] deploy, ctxPath=/
11:06:04,251 INFO  [TomcatDeployment] deploy, ctxPath=/jmx-console
11:06:04,314 INFO  [ProfileServiceBootstrap] Loading profile: ProfileKey@5d43c278[domain=default, server=default, name=default]
11:06:04,327 INFO  [Http11Protocol] Starting Coyote HTTP/1.1 on http-127.0.0.1-8080
11:06:04,357 INFO  [AjpProtocol] Starting Coyote AJP/1.3 on ajp-127.0.0.1-8009
11:06:04,369 INFO  [ServerImpl] JBoss (Microcontainer) [5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)] Started in 30s:115ms
*/
```
##### Open another terminal session to the Red Hat machine
```
ssh [username]@[hostname]
```

##### Install lynx
```
sudo yum install lynx
```

##### Go to the JBoss welcome page
* http://localhost:8080/

##### Check the current firewall settings
```
sudo /sbin/iptables -L
```
```c
Chain INPUT (policy ACCEPT)
target     prot opt source               destination
RH-Firewall-1-INPUT  all  --  anywhere             anywhere

Chain FORWARD (policy ACCEPT)
target     prot opt source               destination
RH-Firewall-1-INPUT  all  --  anywhere             anywhere

Chain OUTPUT (policy ACCEPT)
target     prot opt source               destination

Chain RH-Firewall-1-INPUT (2 references)
target     prot opt source               destination
ACCEPT     all  --  anywhere             anywhere
ACCEPT     icmp --  anywhere             anywhere            icmp any
ACCEPT     esp  --  anywhere             anywhere
ACCEPT     ah   --  anywhere             anywhere
ACCEPT     udp  --  anywhere             224.0.0.251         udp dpt:mdns
ACCEPT     udp  --  anywhere             anywhere            udp dpt:ipp
ACCEPT     tcp  --  anywhere             anywhere            tcp dpt:ipp
ACCEPT     all  --  anywhere             anywhere            state RELATED,ESTABLISHED
ACCEPT     tcp  --  anywhere             anywhere            state NEW tcp dpt:ssh
REJECT     all  --  anywhere             anywhere            reject-with icmp-host-prohibited
```

##### Stop JBoss
* ctrl-c

##### Restart JBoss and use 0.0.0.0 as the bind address to listen to traffic from any IP address
```
sudo $JBOSS_HOME/bin/run.sh -b 0.0.0.0
```
```c
/*
=========================================================================

  JBoss Bootstrap Environment

  JBOSS_HOME: /opt/jboss/jboss-eap-5.1/jboss-as

  JAVA: java

  JAVA_OPTS: -Dprogram.name=run.sh -server -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Djava.net.preferIPv4Stack=true

  CLASSPATH: /opt/jboss/jboss-eap-5.1/jboss-as/bin/run.jar

=========================================================================

11:13:46,199 INFO  [ServerImpl] Starting JBoss (Microcontainer)...
11:13:46,200 INFO  [ServerImpl] Release ID: JBoss [EAP] 5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)
11:13:46,201 INFO  [ServerImpl] Bootstrap URL: null
11:13:46,201 INFO  [ServerImpl] Home Dir: /opt/jboss/jboss-eap-5.1/jboss-as
11:13:46,201 INFO  [ServerImpl] Home URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/
11:13:46,201 INFO  [ServerImpl] Library URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/lib/
11:13:46,203 INFO  [ServerImpl] Patch URL: null
11:13:46,203 INFO  [ServerImpl] Common Base URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/common/
11:13:46,203 INFO  [ServerImpl] Common Library URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/common/lib/
11:13:46,203 INFO  [ServerImpl] Server Name: default
11:13:46,204 INFO  [ServerImpl] Server Base Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server
11:13:46,204 INFO  [ServerImpl] Server Base URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/server/
11:13:46,204 INFO  [ServerImpl] Server Config URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/conf/
11:13:46,204 INFO  [ServerImpl] Server Home Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default
11:13:46,204 INFO  [ServerImpl] Server Home URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/
11:13:46,204 INFO  [ServerImpl] Server Data Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/data
11:13:46,205 INFO  [ServerImpl] Server Library URL: file:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/lib/
11:13:46,205 INFO  [ServerImpl] Server Log Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/log
11:13:46,205 INFO  [ServerImpl] Server Native Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/tmp/native
11:13:46,205 INFO  [ServerImpl] Server Temp Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/tmp
11:13:46,205 INFO  [ServerImpl] Server Temp Deploy Dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/tmp/deploy
11:13:47,018 INFO  [ServerImpl] Starting Microcontainer, bootstrapURL=file:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/conf/bootstrap.xml
11:13:47,673 INFO  [VFSCacheFactory] Initializing VFSCache [org.jboss.virtual.plugins.cache.CombinedVFSCache]
11:13:47,676 INFO  [VFSCacheFactory] Using VFSCache [CombinedVFSCache[real-cache: null]]
11:13:48,019 INFO  [CopyMechanism] VFS temp dir: /opt/jboss/jboss-eap-5.1/jboss-as/server/default/tmp
11:13:48,021 INFO  [ZipEntryContext] VFS force nested jars copy-mode is enabled.
11:13:49,319 INFO  [ServerInfo] Java version: 1.6.0_38,Sun Microsystems Inc.
11:13:49,319 INFO  [ServerInfo] Java Runtime: OpenJDK Runtime Environment (build 1.6.0_38-b38)
11:13:49,319 INFO  [ServerInfo] Java VM: OpenJDK 64-Bit Server VM 23.25-b01,Sun Microsystems Inc.
11:13:49,319 INFO  [ServerInfo] OS-System: Linux 2.6.18-409.el5,amd64
11:13:49,320 INFO  [ServerInfo] VM arguments: -Dprogram.name=run.sh -Xms1303m -Xmx1303m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Djava.net.preferIPv4Stack=true -Djava.endorsed.dirs=/opt/jboss/jboss-eap-5.1/jboss-as/lib/endorsed
11:13:49,359 INFO  [JMXKernel] Legacy JMX core initialized
11:13:55,090 INFO  [WebService] Using RMI server codebase: http://99700hlzx6g1:8083/
11:13:59,984 INFO  [NativeServerConfig] JBoss Web Services - Stack Native Core
11:13:59,984 INFO  [NativeServerConfig] 3.1.2.SP7
11:14:01,212 INFO  [LogNotificationListener] Adding notification listener for logging mbean "jboss.system:service=Logging,type=Log4jService" to server org.jboss.mx.server.MBeanServerImpl@7bfaa588[ defaultDomain='jboss' ]
11:14:07,319 INFO  [MailService] Mail Service bound to java:/Mail
11:14:07,951 WARN  [JBossASSecurityMetadataStore] WARNING! POTENTIAL SECURITY RISK. It has been detected that the MessageSucker component which sucks messages from one node to another has not had its password changed from the installation default. Please see the JBoss Messaging user guide for instructions on how to do this.
11:14:09,316 INFO  [TransactionManagerService] JBossTS Transaction Service (JTA version - tag:JBOSSTS_4_6_1_GA_CP07) - JBoss Inc.
11:14:09,317 INFO  [TransactionManagerService] Setting up property manager MBean and JMX layer
11:14:09,541 INFO  [TransactionManagerService] Initializing recovery manager
11:14:09,652 INFO  [TransactionManagerService] Recovery manager configured
11:14:09,652 INFO  [TransactionManagerService] Binding TransactionManager JNDI Reference
11:14:09,717 INFO  [TransactionManagerService] Starting transaction recovery manager
11:14:10,298 INFO  [AprLifecycleListener] The Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: /usr/lib/jvm/java-1.6.0-openjdk-1.6.0.38.x86_64/jre/lib/amd64/server:/usr/lib/jvm/java-1.6.0-openjdk-1.6.0.38.x86_64/jre/lib/amd64:/usr/lib/jvm/java-1.6.0-openjdk-1.6.0.38.x86_64/jre/../lib/amd64:/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
11:14:10,368 INFO  [Http11Protocol] Initializing Coyote HTTP/1.1 on http-0.0.0.0-8080
11:14:10,369 INFO  [AjpProtocol] Initializing Coyote AJP/1.3 on ajp-0.0.0.0-8009
11:14:10,396 INFO  [StandardService] Starting service jboss.web
11:14:10,400 INFO  [StandardEngine] Starting Servlet Engine: JBoss Web/2.1.10
11:14:10,472 INFO  [Catalina] Server startup in 103 ms
11:14:10,492 INFO  [TomcatDeployment] deploy, ctxPath=/jbossws
11:14:11,412 INFO  [TomcatDeployment] deploy, ctxPath=/invoker
11:14:11,453 INFO  [TomcatDeployment] deploy, ctxPath=/web-console
11:14:11,839 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/jboss-local-jdbc.rar/META-INF/ra.xml
11:14:11,854 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/jboss-xa-jdbc.rar/META-INF/ra.xml
11:14:11,867 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/jms-ra.rar/META-INF/ra.xml
11:14:11,908 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/mail-ra.rar/META-INF/ra.xml
11:14:11,934 INFO  [RARDeployment] Required license terms exist, view vfsfile:/opt/jboss/jboss-eap-5.1/jboss-as/server/default/deploy/quartz-ra.rar/META-INF/ra.xml
11:14:12,054 INFO  [SimpleThreadPool] Job execution threads will use class loader of thread: main
11:14:12,089 INFO  [QuartzScheduler] Quartz Scheduler v.1.5.2 created.
11:14:12,093 INFO  [RAMJobStore] RAMJobStore initialized.
11:14:12,094 INFO  [StdSchedulerFactory] Quartz scheduler 'DefaultQuartzScheduler' initialized from default resource file in Quartz package: 'quartz.properties'
11:14:12,094 INFO  [StdSchedulerFactory] Quartz scheduler version: 1.5.2
11:14:12,097 INFO  [QuartzScheduler] Scheduler DefaultQuartzScheduler_$_NON_CLUSTERED started.
11:14:12,687 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=DataSourceBinding,name=DefaultDS' to JNDI name 'java:DefaultDS'
11:14:13,152 INFO  [ServerPeer] JBoss Messaging 1.4.7.GA server [0] started
11:14:13,251 INFO  [ConnectionFactoryJNDIMapper] supportsFailover attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support failover
11:14:13,252 INFO  [ConnectionFactoryJNDIMapper] supportsLoadBalancing attribute is true on connection factory: jboss.messaging.connectionfactory:service=ClusteredConnectionFactory but post office is non clustered. So connection factory will *not* support load balancing
11:14:13,320 INFO  [ConnectionFactory] Connector bisocket://99700hlzx6g1:4457 has leasing enabled, lease period 10000 milliseconds
11:14:13,321 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@30178dcd started
11:14:13,341 INFO  [QueueService] Queue[/queue/DLQ] started, fullSize=200000, pageSize=2000, downCacheSize=2000
11:14:13,343 INFO  [ConnectionFactory] Connector bisocket://99700hlzx6g1:4457 has leasing enabled, lease period 10000 milliseconds
11:14:13,343 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@17bc8290 started
11:14:13,346 INFO  [QueueService] Queue[/queue/ExpiryQueue] started, fullSize=200000, pageSize=2000, downCacheSize=2000
11:14:13,347 INFO  [ConnectionFactory] Connector bisocket://99700hlzx6g1:4457 has leasing enabled, lease period 10000 milliseconds
11:14:13,347 INFO  [ConnectionFactory] org.jboss.jms.server.connectionfactory.ConnectionFactory@dae8709 started
11:14:13,450 INFO  [ConnectionFactoryBindingService] Bound ConnectionManager 'jboss.jca:service=ConnectionFactoryBinding,name=JmsXA' to JNDI name 'java:JmsXA'
11:14:13,598 INFO  [TomcatDeployment] deploy, ctxPath=/admin-console
11:14:13,737 INFO  [config] Initializing Mojarra (1.2_13-b01-FCS) for context '/admin-console'
11:14:16,220 INFO  [TomcatDeployment] deploy, ctxPath=/
11:14:16,275 INFO  [TomcatDeployment] deploy, ctxPath=/jmx-console
11:14:16,374 INFO  [ProfileServiceBootstrap] Loading profile: ProfileKey@6f16a8ba[domain=default, server=default, name=default]
11:14:16,386 INFO  [Http11Protocol] Starting Coyote HTTP/1.1 on http-0.0.0.0-8080
11:14:16,407 INFO  [AjpProtocol] Starting Coyote AJP/1.3 on ajp-0.0.0.0-8009
11:14:16,418 INFO  [ServerImpl] JBoss (Microcontainer) [5.1.0 (build: SVNTag=JBPAPP_5_1_0 date=201009150028)] Started in 30s:209ms
*/
```

##### Try to access the JBoss Welcome page
* http://[hostname]:8080

##### Stop the firewall
```
sudo /sbin/service iptables stop
```
```c
/*
Flushing firewall rules:                                   [  OK  ]
Setting chains to policy ACCEPT: filter                    [  OK  ]
Unloading iptables modules:                                [  OK  ]
*/
```

##### Use the service command to check the status of the iptables service
```
sudo /sbin/service iptables status
```
```c
// Firewall is stopped.
```

##### Try again to access the JBoss Welcome page
* http://[hostname]:8080

##### Search for jmx-console-users.properties
```
find $JBOSS_HOME -name jmx-console-users.properties
```

##### Set the admin username and password
```
nano $JBOSS_HOME/server/default/conf/props/jmx-console-users.properties
```
```
# A sample users.properties file for use with the UsersRolesLoginModule
admin=admin
```

##### Search for web-console-users.properties
```
find $JBOSS_HOME/server/default -name web-console-users.properties
```

##### Set the admin username and password
```
nano $JBOSS_HOME/server/default/deploy/management/console-mgr.sar/web-console.war/WEB-INF/classes/web-console-users.properties
```
```
# A sample users.properties file for use with the UsersRolesLoginModule
admin=admin
```

##### Go to the admin console
*	http://[hostname]:8080/admin-console/

##### Login with the admin credentials
*	admin/admin

```
16:35:48,308 INFO  [BootstrapAction] Initializing Administration Console v1.3.4.SP1...
16:35:54,577 INFO  [PluginContainerResourceManager] Discovering Resources...
```

##### Go to the JMX console
*	http://localhost:8080/jmx-console/

##### Login with the admin credentials
*	admin/admin

