# jboss eap 7.0.0 ubuntu linux

## References
* http://blog.eisele.net/2015/11/getting-started-with-eap-7-alpha-and.html

In this tutorial $DEV refers to /home/bachmeb/DEV on my system. For consistency, you can choose any folder you like to be your DEV folder. 

##### Download and install Java JDK 1.8
* https://github.com/bachmeb/java-jdk/

##### Login to Red Hat
* http://access.redhat.com

##### Get a direct link to JBoss EAP 7.0.0 beta
* http://developers.redhat.com/products/eap/download/

##### Download JBoss EAP 7.0.0 beta
*Each direct link provided by Red Hat is unique and expires after a short amount of time*
```
wget [the direct link to jboss eap 7.0.0 beta]
```

##### Install unzip
```
sudo apt-get install unzip
```

##### Unzip the contents of jboss-eap-7.0.0.Beta.zip
```
unzip jboss-eap-7.0.0.Beta.zip
```

##### Make a jboss directory
```
mkdir -p ~/DEV/jboss/eap/7.0.0
```

##### Move the contents of the jboss zip file to the jboss directory
```
mv jboss-eap-7.0 DEV/jboss/eap/7.0.0
```

##### Run the startup script
```
nano DEV/jboss/eap/7.0.0/bin/standalone.sh
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
