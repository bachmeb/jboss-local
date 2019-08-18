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

### Configure
Read standalone.xml
```
cat {JBOSS_HOME}/standalone/configuration/standalone.xml
```
Tell JBoss to listen for traffic on any network interface
* https://stackoverflow.com/questions/6853409/binding-jboss-as-7-to-all-interfaces
```
vi [JBoss Home]/standalone/configuration/standalone.xml
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
