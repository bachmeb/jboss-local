
# hellothere

## References
* http://centerkey.com/jboss/
* https://github.com/bachmeb/tomcat-aws/

##### Create a project directory
* $DEV\git\jboss-local\hellothere

##### Create a WEB-INF directory in the project directory
* $DEV\git\jboss-local\helloworld\WEB-INF

##### Create a web.xml file in the WEB-INF directory
```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE web-app
    PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
    "http://java.sun.com/dtd/web-app_2_3.dtd">
<web-app>
    <display-name>Hi There</display-name>
    <servlet>
        <servlet-name>sayhi</servlet-name>
        <servlet-class>com.example.packname.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>sayhi</servlet-name>
        <url-pattern>/there</url-pattern>
    </servlet-mapping>
</web-app>
```

##### Create a web directory
* $DEV\git\jboss-local\apps\hellothere\web

##### Create an index file in the web directory
* $DEV\git\jboss-local\apps\hellothere\web\index.html
```html
Hello <a href="./there">There</a>.
```

##### Make a Java class
* $DEV\git\jboss-local\apps\hellothere\src\com\example\greeting\HelloServlet.java
```java
package com.example.packname;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple servlet displays a message
 */
public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse
            response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("HELLO!!!");
        out.close();
    }
}
```

##### Create a deployment script in the project directory
```bat
@echo off 

rem Locate Java
set JAVA_HOME=C:\DEV\java\6\64\jdk1.6.0_45
echo %JAVA_HOME%
pause

rem Locate JBoss application server
set JBossHome=C:\DEV\jboss\eap\5.1.0\jboss-as
echo %JBossHome%
pause

rem Make a deployment package
set ASDF="%JAVA_HOME%\bin\jar.exe" -cvf hellothere.war *.jsp WEB-INF web src
echo %ASDF%
pause

%ASDF%

copy hellothere.war "%JBossHome%\server\default\deploy

pause 
```

##### Run the deployment script
* $DEV\git\jboss-local\hellothere\WEB-INF\Deploy.cmd
```
13:30:13,307 INFO  [TomcatDeployment] deploy, ctxPath=/hellothere
```

##### View the web app in a browser
* http://localhost:8080/hellothere/
```

```


