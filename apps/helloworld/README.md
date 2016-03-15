# helloworld

## References
* http://centerkey.com/jboss/

##### Create a project directory
* $DEV\git\jboss-local\helloworld

##### Create a JSP file in the project directory
```jsp
<!doctype html> 
<html> 
	<head> 
		<title>JSP Test</title> 
		<%! 
		String message = "Hello, World."; 
		%> 
	</head> 
	<body> 
		<h2><%= message%></h2> 
		<%= new java.util.Date() %> 
	</body> 
</html> 
```
##### Create a WEB-INF directory in the project directory
* $DEV\git\jboss-local\helloworld\WEB-INF

##### Create a web.xml file in the WEB-INF directory
```xml
<web-app> 
<display-name>Hello World</display-name> 
</web-app> 
```

##### Create a deployment script in the project directory
```bat
@echo off 

set JAVA_HOME=C:\DEV\java\6\64\jdk1.6.0_45
echo %JAVA_HOME%
pause

set JBossHome=C:\DEV\jboss\eap\5.1.0\jboss-as
echo %JBossHome%
pause

set ASDF="%JAVA_HOME%\bin\jar.exe" -cvf helloworld.war *.jsp WEB-INF 
echo %ASDF%
pause

%ASDF%

copy helloworld.war "%JBossHome%\server\default\deploy

pause 
```

##### Run the deployment script
* $DEV\git\jboss-local\helloworld\WEB-INF\Deploy.cmd
```
13:30:13,307 INFO  [TomcatDeployment] deploy, ctxPath=/helloworld
```

##### View the web app in a browser
* http://localhost:8080/helloworld/hi.jsp
```
Hello, World.

Tue Mar 15 13:30:47 EDT 2016
```

