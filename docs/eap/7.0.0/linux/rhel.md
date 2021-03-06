# JBoss EAP 7.0.0 - RHEL

## References

### Prepare
Install Java JDK 1.8
```
sudo yum search openjdk
sudo yum install java-1.8.0-openjdk
```
### Download
Login to redhat.com and download the EAP 7.0.0 installer jar. 
* https://developers.redhat.com/products/eap/download
* https://developers.redhat.com/download-manager/file/jboss-eap-7.0.0-installer.jar

### Install
```
java -jar jboss-eap-7.0.0-installer.jar
```
Choose a directory for the installation, like /opt/jboss/EAP-7.0.0

### Configure
Set an environment variable for JBOSS_HOME
```
export JBOSS_HOME=/opt/jboss/EAP-7.0.0 or whereever you installed it
```
List the contents of $JBOSS_HOME
```
ll $JBOSS_HOME
```
Create a symbolic link to jboss home from the home directory of the jboss user
```
sudo su -l jboss
pwd
ln -s /opt/jboss/EAP-7.0.0/ jboss-home
```
Read standalone.xml
```
cat $JBOSS_HOME/standalone/configuration/standalone.xml
```
Tell JBoss to listen for traffic on any network interface
* https://stackoverflow.com/questions/6853409/binding-jboss-as-7-to-all-interfaces
```
vi $JBOSS_HOME/standalone/configuration/standalone.xml
```
```
/<interfaces
```
Change this...
```
    <interfaces>
        <interface name="management">
            <inet-address value="${jboss.bind.address.management:127.0.0.1}"/>
        </interface>
        <interface name="public">
            <inet-address value="${jboss.bind.address:127.0.0.1}"/>
        </interface>
    </interfaces>
```
...to this: 
```
<interfaces>
    <interface name="management">
        <inet-address value="${jboss.bind.address.management:127.0.0.1}"/>
    </interface>
    <interface name="public">
        <any-address/>
    </interface>
</interfaces>
```
Start JBoss
```
nohup $JBOSS_HOME/bin/standalone.sh & > ~/nohup.out
```
Monitor the output in nohup.out
```
tail -f ~/nohup.out
```
Go to the Administration Console
* http://localhost:8080

Stop JBoss
```
$JBOSS_HOME/bin/jboss-cli.sh --connect command=:shutdown
```
Bind management-http to the public interface
```
vi $JBOSS_HOME/standalone/configuration/standalone.xml
```
```
/<socket-binding-group
```
```
<socket-binding name="management-http" interface="public" port="${jboss.management.http.port:9990}"/>
```
Start JBoss
```
nohup $JBOSS_HOME/bin/standalone.sh & > ~/nohup.out
```
Monitor the output in nohup.out
```
tail -f ~/nohup.out
```
Go to the JBoss EAP 7 Welcome page
* http://localhost:8080

Login to the Administration Console
* http://10.158.160.52:9990/console/App.html

