# jsfejb3

## References
* http://docs.jboss.org/jbossas/docs/Getting_Started_Guide/beta422/html/About_the_Example_Applications.html
* http://ant.apache.org/manual/install.html
* https://github.com/jeffzhang/docant-jboss/blob/master/jbossas/6/Installation_And_Getting_Started_Guide/en-US/Sample_JSF_EJB3_Application.xml
* http://stackoverflow.com/questions/5118821/jboss-gettingstarted-zip
* https://developer.jboss.org/wiki/SampleJSF-EJB3Application
* https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?downloadType=distributions&product=appplatform&version=4.3.0.GA_CP09
* http://markmail.org/thread/ldqdq7y37tgr5rcz
* https://github.com/bachmeb/jsfejb3
* http://stackoverflow.com/questions/10652912/jboss-5-1-server
* https://developer.jboss.org/thread/149783?tstart=0
* http://stackoverflow.com/questions/5103384/ant-warning-includeantruntime-was-not-set
* https://developer.jboss.org/thread/159203?tstart=0
* http://ant.apache.org/manual/Tasks/copy.html
* http://ant.apache.org/manual/Tasks/move.html
* http://stackoverflow.com/questions/4597968/how-to-recompile-with-xlintunchecked-in-ant-build-task

##### Download and install JBoss
* https://github.com/bachmeb/jboss-local

##### Download the Application Platform 4.3.0.GA_CP09 documentation
* https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?downloadType=distributions&product=appplatform&version=4.3.0.GA_CP09

##### Use 7-zip to unpack the contents of jboss-eap-docs-4.3.0.GA_CP09. Do not use Windows Explorer. Extract the files to:
* $DEV\jboss\eap\4.3

##### Create a project directory
* $DEV\git\jboss-local\apps\jsfejb3

##### Copy the getting started code to the project directory
* Copy from: $DEV\jboss\eap\4.3\doc\examples\gettingstarted\jsfejb3
* Copy to: $DEV\git\jboss-local\apps\jsfejb3

##### Review index.html
* $DEV\git\jboss-local\apps\jsfejb3\view\index.html
```html
<html>
	<head>
		<meta http-equiv="Refresh" content="0; URL=index.faces">
	</head>
</html>
```

##### Review index.xhtml
* $DEV\git\jboss-local\apps\jsfejb3\view\index.xhtml
```html
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core">

	<head>
		<link href="style.css" rel="stylesheet" type="text/css"/>
	</head>

	<body>
		<h2>JSF-EJB3</h2>

		<p>Actions:</p>

		<h:form>

		<ul>
			<li><h:commandLink value="Create New Todo" action="create"/></li>
			<li><h:commandLink value="Show All Todos" action="todos"/></li>
		</ul>

		</h:form>

	</body>
</html>
```

##### Review create.xhtml
* $DEV\git\jboss-local\apps\jsfejb3\view\create.xhtml
```html
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:ui="http://java.sun.com/jsf/facelets"
	  xmlns:h="http://java.sun.com/jsf/html"
          xmlns:f="http://java.sun.com/jsf/core">

	<head>
		<link href="style.css" rel="stylesheet" type="text/css"/>
	</head>

	<body>
		<h2>DataModel, Validation and Restful Page Demo</h2>

		<h:form id="create">

		<table>

		  <tr>
			<td>Title:</td>
			<td>
			  <h:inputText id="title" value="#{todoBean.todo.title}" size="15">
				<f:validateLength minimum="2"/>
			  </h:inputText>
			</td>
		  </tr>

		  <tr>
			<td>Description:</td>
			<td>
			  <h:inputTextarea id="description" value="#{todoBean.todo.description}">
				<f:validateLength minimum="2" maximum="250"/>
			  </h:inputTextarea>
			</td>
		  </tr>

		</table>

		<h:commandButton type="submit" id="create" value="Create"
						 action="#{todoBean.persist}"/>
		</h:form>

	</body>
</html>
```

##### Review todos.xhtml
* $DEV\git\jboss-local\apps\jsfejb3\view\todos.xhtml
```html
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core">

	<body>

		<h2>The Todo List</h2>

		<h:form>

			<h:dataTable value="#{todoBean.todos}" var="todo">
				<h:column>
					<f:facet name="header">Title</f:facet>
					#{todo.title}
				</h:column>
				<h:column>
					<f:facet name="header">Description</f:facet>
					#{todo.description}
				</h:column>
				<h:column>
					<a href="edit.faces?tid=#{todo.id}">Edit</a>
				</h:column>
			</h:dataTable>

			<center>
				<h:commandButton action="create"
				value="Create New Todo" type="submit"/>
			</center>

		</h:form>

	</body>
</html>
```
##### Review edit.xhtml
* $DEV\git\jboss-local\apps\jsfejb3\view\edit.xhtml
```html
<!DOCTYPE html PUBLIC
	"-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core">

	<head>
		<link href="style.css" rel="stylesheet" type="text/css"/>
	</head>

	<body>
		<h2>Edit #{todoBean.todo.title}</h2>

		<h:form id="edit">

			<input type="hidden" name="tid" value="#{todoBean.todo.id}"/>

			<table>

				<tr>
					<td>Title:</td>
					<td>
						<h:inputText id="title" value="#{todoBean.todo.title}" size="15">
						<f:validateLength minimum="2"/>
						</h:inputText>
					</td>
				</tr>

				<tr>
					<td>Description:</td>
					<td>
						<h:inputTextarea id="description" value="#{todoBean.todo.description}">
						<f:validateLength minimum="2" maximum="250"/>
						</h:inputTextarea>
					</td>
				</tr>

			</table>

			<h:commandButton type="submit" id="update" value="Update"
			action="#{todoBean.update}"/>
			&nbsp;
			<h:commandButton type="submit" id="delete" value="Delete"
			action="#{todoBean.delete}"/>
		</h:form>

	</body>
</html>
```

##### Review style.css
* $DEV\git\jboss-local\apps\jsfejb3\view\style.css
```css
.error {
  color: red;
  border: solid red;
  padding: .4em;
  margin: 10px
}

.errorImg {
  vertical-align: middle;
  width: 16px;
  height: 16px;
}

.errorMsg {
  display: block;
  margin: 5px;
  padding: .1em;
}
```

##### Review the entity class
* $DEV\git\jboss-local\apps\jsfejb3\src\Todo.java
```java
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
public class Todo implements Serializable {

  private long id;
  private String title;
  private String description;

  public Todo () {
    title ="";
    description ="";
  }

  @Id @GeneratedValue
  public long getId() { return id;}
  public void setId(long id) { this.id = id; }

  public String getTitle() { return title; }
  public void setTitle(String title) {this.title = title;}

  public String getDescription() { return description; }
  public void setDescription(String description) {
    this.description = description;
  }

}
```

##### Review the DAO interface
* $DEV\git\jboss-local\apps\jsfejb3\src\TodoDaoInt.java
```java
import java.util.List;

public interface TodoDaoInt {

  public void persist (Todo todo);
  public void delete (Todo todo);
  public void update (Todo todo);

  public List <Todo> findTodos ();
  public Todo findTodo (String id);
}
```

##### Review the DAO class
* $DEV\git\jboss-local\apps\jsfejb3\src\TodoDao.java

*[The @PersistenceContext annotation tells the JBoss Server to inject an entity manager during deployment.](http://docs.jboss.org/jbossas/docs/Getting_Started_Guide/beta422/html/Sample_JSF_EJB3_Application-EJB3_Session_Beans.html)*
```java
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;
import static javax.persistence.PersistenceContextType.EXTENDED;

@Stateless
public class TodoDao implements TodoDaoInt {

  @PersistenceContext
  private EntityManager em;

  public void persist (Todo todo) {
    em.persist (todo);
  }

  public void delete (Todo todo) {
    Todo t = em.merge (todo);
    em.remove( t );
  }

  public void update (Todo todo) {
    em.merge (todo);
  }

  public List <Todo> findTodos () {
    return (List <Todo>) em.createQuery("select t from Todo t")
                                  .getResultList();
  }

  public Todo findTodo (String id) {
    return (Todo) em.find(Todo.class, Long.parseLong(id));
  }

}
```

##### Review the backing bean
* $DEV\git\jboss-local\apps\jsfejb3\src\TodoBean.java
```java
import javax.naming.InitialContext;
import java.util.*;
import javax.faces.model.*;
import javax.faces.context.*;

public class TodoBean {

  private Todo todo;

  public TodoBean () {
    FacesContext fc = FacesContext.getCurrentInstance();
    Map requestParams = fc.getExternalContext().getRequestParameterMap();
    String id = (String) requestParams.get("tid");
    if (id != null) {
      todo = getDao().findTodo(id);
    } else {
      todo = new Todo ();
    }
  }

  private TodoDaoInt getDao () {
    try {
      InitialContext ctx = new InitialContext();
      return (TodoDaoInt) ctx.lookup("jsfejb3/TodoDao/local");
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("couldn't lookup Dao", e);
    }
  }
 
  public String persist () {
    getDao().persist (todo);
    return "persisted";
  }

  public DataModel getTodos () {
    return (new ListDataModel (getDao().findTodos()));
  }

  public Todo getTodo () {
    return todo;
  }

  public void setTodo (Todo todo) {
    this.todo = todo;
  }

  public String delete () {
    getDao().delete( todo );
    return "removed";
  }

  public String update () {
    getDao().update( todo );
    return "updated";
  }

}
```

##### Review the web.xml file
* $DEV\git\bachmeb\jboss-local\apps\jsfejb3\resources\WEB-INF
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.4" 
	xmlns="http://java.sun.com/xml/ns/j2ee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">

          
  <context-param>
    <param-name>javax.faces.CONFIG_FILES</param-name>
    <param-value>/WEB-INF/navigation.xml</param-value>
  </context-param>
  
    <!-- MyFaces -->
    <!--
    <listener>
        <listener-class>org.apache.myfaces.webapp.StartupServletContextListener</listener-class>
    </listener>
    -->
    
    <context-param>
        <param-name>javax.faces.STATE_SAVING_METHOD</param-name>
        <param-value>client</param-value>
    </context-param>
    
    <context-param>
        <param-name>facelets.DEVELOPMENT</param-name>
        <param-value>true</param-value>
    </context-param>

    <context-param>
        <param-name>javax.faces.DEFAULT_SUFFIX</param-name>
        <param-value>.xhtml</param-value>
    </context-param>

    <servlet>
	<servlet-name>Faces Servlet</servlet-name>
	<servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
	<load-on-startup>1</load-on-startup>
    </servlet>

    <!-- Faces Servlet Mapping -->
    <servlet-mapping>
	<servlet-name>Faces Servlet</servlet-name>
	<url-pattern>*.faces</url-pattern>
    </servlet-mapping>
	
</web-app>
```

##### Review the application.xml file
* $DEV\git\jboss-local\apps\jsfejb3\resources\META-INF\application.xml
```xml
<application>
  <display-name>Sample Todo</display-name>
  <module>
    <web>
      <web-uri>app.war</web-uri>
      <context-root>/jsfejb3</context-root>
    </web>
  </module>
  <module>
    <ejb>app.jar</ejb>
  </module>
</application>
```

##### Review the jboss-app.xml file
* $DEV\git\jboss-local\apps\jsfejb3\resources\META-INF\jboss-app.xml
```
<jboss-app>
  <loader-repository>
    jsfejb3:archive=jsfejb3.ear
  </loader-repository>
</jboss-app>
```

##### Review the persistence.xml file
* $DEV\git\jboss-local\apps\jsfejb3\resources\META-INF\persistence.xml
```
<persistence>
   <persistence-unit name="helloworld">
      <provider>org.hibernate.ejb.HibernatePersistence</provider>
      <jta-data-source>java:/DefaultDS</jta-data-source>
      <properties>
         <property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect"/>
         <property name="hibernate.hbm2ddl.auto" value="create-drop"/>
      </properties>
   </persistence-unit>
</persistence>
```

##### Update the root element in persistence.xml to include a xmlns declaration
```xml
  <persistence
    xmlns="http://java.sun.com/xml/ns/persistence"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd"
    version="1.0">  
```

##### Review the Faces config file
* $DEV\git\bachmeb\jboss-local\apps\jsfejb3\resources\WEB-INF\faces-confg.xml
```
<faces-config>
  <application>
    <view-handler>
      com.sun.facelets.FaceletViewHandler
    </view-handler>
  </application>
  <managed-bean>
    <description>Dao</description>
    <managed-bean-name>todoBean</managed-bean-name>
    <managed-bean-class>TodoBean</managed-bean-class>
    <managed-bean-scope>request</managed-bean-scope>
  </managed-bean>
</faces-config>
```

##### Review the build.xml file
* $DEV\git\jboss-local\apps\jsfejb3\build.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project name="HelloWorld" default="main" basedir=".">
	<description>Hello World</description>
	<property name="projname" value="jsfejb3" />
	<property file="../build.properties" />
	<property environment="ENV" />
	<property name="jboss.home" value="${ENV.JBOSS_HOME}" />
	<property name="jboss.deploy" location="${jboss.home}/server/default/deploy" />
	<property name="lib" location="${jboss.home}/lib" />
	<property name="serverlib" location="${jboss.home}/server/default/lib" />
	<property name="clientlib" location="${jboss.home}/client" />
	<property name="jsflib" location="${jboss.home}/server/default/deploy/jboss-web.deployer/jsf-libs" />
	<property name="seamlib" location="${jboss.home}/../seam" />
	<path id="lib.classpath">
		<fileset dir="${lib}" includes="*.jar" />
		<fileset dir="${serverlib}" includes="*.jar" />
		<fileset dir="${clientlib}" includes="*.jar" />
		<fileset dir="${jsflib}" includes="*.jar" />
		<fileset dir="${seamlib}/lib" includes="jsf-facelets.jar" />
	</path>
	<property name="resources" location="resources" />
	<property name="src" location="src" />
	<property name="test" location="test" />
	<property name="view" location="view" />
	<property name="build.classes" location="build/classes" />
	<property name="build.jars" location="build/jars" />
	<property name="build.test" location="build/test" />
	<property name="build.testout" location="build/testout" />
	<target name="clean">
		<delete dir="build" />
	</target>
	<target name="main" depends="compile,war,ejb3jar,ear" />
	<target name="compile">
		<mkdir dir="${build.classes}" />
		<javac destdir="${build.classes}" classpathref="lib.classpath" debug="true">
			<src path="${src}" />
		</javac>
	</target>
	<target name="war" depends="compile">
		<mkdir dir="${build.jars}" />
		<war destfile="${build.jars}/app.war" webxml="${resources}/WEB-INF/web.xml">
			<fileset dir="${build.classes}">
				<include name="TodoBean.class" />
			</fileset>
			<webinf dir="${resources}/WEB-INF">
				<include name="faces-config.xml" />
				<include name="navigation.xml" />
			</webinf>
			<lib dir="${seamlib}/lib">
				<include name="jsf-facelets.jar" />
			</lib>
			<fileset dir="${view}" />
		</war>
	</target>
	<target name="ejb3jar" depends="compile">
		<mkdir dir="${build.jars}" />
		<jar destfile="${build.jars}/app.jar">
			<fileset dir="${build.classes}">
				<include name="Todo.class" />
				<include name="TodoDao.class" />
				<include name="TodoDaoInt.class" />
			</fileset>
			<fileset dir="${resources}">
				<include name="import.sql" />
			</fileset>
			<!--<fileset dir="${applib}">
        <include name="*.jar" />
      </fileset>-->
			<metainf dir="${resources}/META-INF">
				<include name="persistence.xml" />
				<include name="ejb-jar.xml" />
			</metainf>
		</jar>
	</target>
	<target name="ear">
		<mkdir dir="${build.jars}" />
		<ear destfile="${build.jars}/${projname}.ear" appxml="${resources}/META-INF/application.xml">
			<fileset dir="${build.jars}" includes="*.jar, *.war" />
			<metainf dir="${resources}/META-INF">
				<include name="jboss-app.xml" />
			</metainf>
		</ear>
	</target>
	<target name="deploy">
		<copy file="${build.jars}/${projname}.ear" todir="${jboss.deploy}" />
	</target>
	<target name="undeploy">
		<delete file="${jboss.deploy}/${projname}.ear" />
	</target>
</project>
```
##### Update build.xml 
*This line...*
```xml
	<property name="jsflib" location="${jboss.home}/server/default/deploy/jboss-web.deployer/jsf-libs" />
```
*...should be.*
```xml
	<property name="jsflib" location="${jboss.home}/server/default/deploy/jbossweb.sar/jsf-libs" />
```

##### Confirm the value of the JBOSS_HOME environment variable
```
echo $JBOSS_HOME
```
```
C:\DEV\jboss\eap\5.1.0\jboss-as
```

##### Update build.xml so that TodoBean.class is placed in 'WEB-INF/classes' in app.war instead of at the top level of the war file
```xml
	<target name="war" depends="compile">
		<mkdir dir="${build.jars}" />
		<mkdir dir="${build.classes}/WEB-INF/classes" />
		<move file="${build.classes}/TodoBean.class" todir="${build.classes}/WEB-INF/classes"/>
		
		<war destfile="${build.jars}/app.war" webxml="${resources}/WEB-INF/web.xml">
			
			<fileset dir="${build.classes}">
				<include name="WEB-INF/classes/TodoBean.class" />
			</fileset>
			
			<webinf dir="${resources}/WEB-INF">
				<include name="faces-config.xml" />
				<include name="navigation.xml" />
			</webinf>
			
			<lib dir="${seamlib}/lib">
				<include name="jsf-facelets.jar" />
			</lib>
			<fileset dir="${view}" />
		</war>
	</target>
```

##### Download ant
* http://ant.apache.org/bindownload.cgi

##### Unzip apache-ant-1.9.6-bin.zip
* $DEV\ant\apache-ant-1.9.6

##### Set ANT_HOME 
* Start > Control Panel > System > Advanced System Settings > Environment Variables > User variables > New
  * Variable name: ANT_HOME
  * Variable value: $DEV\ant\apache-ant-1.9.6

##### Add bin folder to the PATH user environment variable
* Start > Control Panel > System > Advanced System Settings > Environment Variables > User variables > PATH > Edit
* Append the following: %ANT_HOME%\bin;

##### Open a command prompt
	* Start > Run > cmd

##### Verify the ant version
	ant version
    
##### Build the project
```
cd $DEV\git\jboss-local\apps\jsfejb3
ant
```
```c
/*
Buildfile: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build.xml

compile:
    [javac] C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build.xml:38: warning: 'includeantruntime' was not set, defaulting to build.sysclasspath=last; set to false for repeatable builds
    [javac] Compiling 4 source files to C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\classes
    [javac] Note: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\src\TodoDao.java uses unchecked or unsafe operations.
    [javac] Note: Recompile with -Xlint:unchecked for details.

war:
    [mkdir] Created dir: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars
      [war] Building war: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars\app.war

ejb3jar:
      [jar] Building jar: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars\app.jar

ear:
      [ear] Building ear: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars\jsfejb3.ear

main:

BUILD SUCCESSFUL
Total time: 14 seconds
*/
```

##### Add includeantruntime="false" to the javac node in build.xml
* $DEV\git\jboss-local\apps\jsfejb3\build.xml
```xml
		<javac destdir="${build.classes}" 
			classpathref="lib.classpath" 
			debug="true"
			includeantruntime="false">
			<src path="${src}" />
		</javac>
```

##### Clean the project
```
ant clean
```
```c
/*
Buildfile: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build.xml

clean:
   [delete] Deleting directory C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build

BUILD SUCCESSFUL
Total time: 0 seconds
*/
```

##### Rebuild the project
```
ant -v
```
```c
/*
Apache Ant(TM) version 1.9.6 compiled on June 29 2015
Trying the default build file: build.xml
Buildfile: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build.xml
Detected Java version: 1.6 in: C:\DEV\java\6\64\jdk1.6.0_45\jre
Detected OS: Windows 7
parsing buildfile C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build.xml with URI = file:/C:/DEV/git/bachmeb/jboss-local/apps/jsfejb3/build.xml
Project base dir set to: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3
parsing buildfile jar:file:/C:/DEV/ant/apache-ant-1.9.6/lib/ant.jar!/org/apache/tools/ant/antlib.xml with URI = jar:file:/C:/DEV/ant/apache-ant-1.9.6/lib/ant.jar!/org/apache/tools/ant/antlib.xml from a zip file
 [property] Loading C:\DEV\git\bachmeb\jboss-local\apps\build.properties
 [property] Unable to find property file: C:\DEV\git\bachmeb\jboss-local\apps\build.properties
 [property] Loading Environment ENV.
Build sequence for target(s) `main' is [compile, war, ejb3jar, ear, main]
Complete build sequence is [compile, war, ejb3jar, ear, main, deploy, clean, undeploy, ]

compile:
    [mkdir] Created dir: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\classes
    [javac] Todo.java added as Todo.class doesn't exist.
    [javac] TodoBean.java added as TodoBean.class doesn't exist.
    [javac] TodoDao.java added as TodoDao.class doesn't exist.
    [javac] TodoDaoInt.java added as TodoDaoInt.class doesn't exist.
    [javac] Compiling 4 source files to C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\classes
    [javac] Using modern compiler
    [javac] Compilation arguments:
    [javac] '-d'
    [javac] 'C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\classes'
    [javac] '-classpath'
    [javac] 'C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\classes;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\concurrent.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\dom4j.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\getopt.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\javassist.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jaxb-impl.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jaxb-xjc.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-aop-asintegration-core.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-aop-asintegration-jmx.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-aop-asintegration-mc.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-aop-deployers.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-aop-mc-int.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-aop.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-bootstrap.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-classloader.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-classloading-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-classloading-vfs.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-classloading.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-common-core.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-dependency.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-deployers-client-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-deployers-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-deployers-core-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-deployers-core.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-deployers-impl.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-deployers-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-deployers-structure-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-deployers-vfs-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-deployers-vfs.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-j2se.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-javaee.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-jmx.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-kernel.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-logbridge.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-logging-jdk.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-logging-log4j.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-logging-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-logmanager.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-main.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-managed.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-mbeans.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-mdr.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-metatype.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-profileservice-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-reflect.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-security-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-system-jmx.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-system.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-vfs.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jboss-xml-binding.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jbosssx-server.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\jbosssx.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\log4j-boot.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\trove.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\lib\wstx.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\FastInfoset.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\activation.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\antlr.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\commons-logging.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\concurrent.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\ejb3-persistence.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\getopt.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\hibernate-annotations.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\hibernate-commons-annotations.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\hibernate-core.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\hibernate-validator.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\hornetq-core-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\hornetq-jms-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jacorb.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\javassist.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jaxb-api.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jaxb-impl.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jaxb-xjc.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jaxws-rt.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jaxws-tools.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-aop-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-appclient.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-aspect-jdk50-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-common-core.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-deployers-client-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-deployers-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-deployers-core-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-deployers-core.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-deployers-vfs-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-deployers-vfs.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-deployment.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ejb3-common-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ejb3-core-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ejb3-ext-api-impl.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ejb3-ext-api.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ejb3-proxy-clustered-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ejb3-proxy-impl-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ejb3-proxy-spi-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ejb3-security-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ejb3-vfs-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ha-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-ha-legacy-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-iiop-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-integration.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-j2se.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-jaspi-api.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-javaee.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-jsr77-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-logging-jdk.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-logging-log4j.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-logging-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-mdr.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-messaging-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-metadata.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-profileservice-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-remoting.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-security-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-serialization.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-srp-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-system-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-system-jmx-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jboss-xml-binding.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossall-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbosscx-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossjmx-ant.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossjts-integration.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossjts-jacorb.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossjts.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbosssx-as-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbosssx-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-common.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-framework.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-jboss50.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-native-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-native-core.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-native-jaxrpc.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-native-jaxws-ext.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-native-jaxws.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-native-saaj.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jbossws-spi.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jettison.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jmx-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jmx-invoker-adaptor-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\jnp-client.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\log4j.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\logkit.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\mail.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\netty.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\scout.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\slf4j-api.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\slf4j-jboss-logging.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\stax-api.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\stax-ex.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\streambuffer.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\trove.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\wsdl4j.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\wstx.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\client\xmlsec.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\deploy\jbossweb.sar\jsf-libs\jboss-faces.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\deploy\jbossweb.sar\jsf-libs\jsf-api.jar;C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\deploy\jbossweb.sar\jsf-libs\jsf-impl.jar;C:\DEV\jboss\eap\5.1.0\seam\lib\jsf-facelets.jar'
    [javac] '-sourcepath'
    [javac] 'C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\src'
    [javac] '-g'
    [javac]
    [javac] The ' characters around the executable and arguments are
    [javac] not part of the command.
    [javac] Files to be compiled:
    [javac]     C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\src\Todo.java
    [javac]     C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\src\TodoBean.java
    [javac]     C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\src\TodoDao.java
    [javac]     C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\src\TodoDaoInt.java
    [javac] Note: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\src\TodoDao.java uses unchecked or unsafe operations.
    [javac] Note: Recompile with -Xlint:unchecked for details.

war:
    [mkdir] Created dir: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars
      [war] Building war: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars\app.war
      [war] adding directory META-INF/
      [war] adding entry META-INF/MANIFEST.MF
      [war] adding directory WEB-INF/
      [war] adding entry WEB-INF/web.xml
      [war] adding entry TodoBean.class
      [war] adding entry WEB-INF/faces-config.xml
      [war] adding entry WEB-INF/navigation.xml
      [war] adding directory WEB-INF/lib/
      [war] adding entry WEB-INF/lib/jsf-facelets.jar
      [war] adding entry create.xhtml
      [war] adding entry edit.xhtml
      [war] adding entry error.png
      [war] adding entry index.html
      [war] adding entry index.xhtml
      [war] adding entry style.css
      [war] adding entry todos.xhtml
      [war] No Implementation-Title set.No Implementation-Version set.No Implementation-Vendor set.
      [war] Location: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build.xml:49:

ejb3jar:
    [mkdir] Skipping C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars because it already exists.
      [jar] Building jar: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars\app.jar
      [jar] adding directory META-INF/
      [jar] adding entry META-INF/MANIFEST.MF
      [jar] adding entry Todo.class
      [jar] adding entry TodoDao.class
      [jar] adding entry TodoDaoInt.class
      [jar] adding entry import.sql
      [jar] adding entry META-INF/persistence.xml
      [jar] No Implementation-Title set.No Implementation-Version set.No Implementation-Vendor set.
      [jar] Location: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build.xml:66:

ear:
    [mkdir] Skipping C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars because it already exists.
      [ear] Building ear: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build\jars\jsfejb3.ear
      [ear] adding directory META-INF/
      [ear] adding entry META-INF/MANIFEST.MF
      [ear] adding entry META-INF/application.xml
      [ear] adding entry app.jar
      [ear] adding entry app.war
      [ear] adding entry META-INF/jboss-app.xml
      [ear] No Implementation-Title set.No Implementation-Version set.No Implementation-Vendor set.
      [ear] Location: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build.xml:87:

main:

BUILD SUCCESSFUL
Total time: 5 seconds

*/
```

##### Examine the file structure of the EAR file
```
jsfejb3.ear
|+ app.jar   // contains the EJB code
    |+ import.sql
    |+ Todo.class
    |+ TodoDao.class
    |+ TodoDaoInt.class
    |+ META-INF
        |+ persistence.xml
|+ app.war   // contains web UI
    |+ index.html
    |+ index.xhtml
    |+ create.xhtml
    |+ edit.xhtml
    |+ todos.xhtml
    |+ TodoBean.class
    |+ style.css
    |+ META-INF
    |+ WEB-INF
       |+ faces-config.xml
       |+ navigation.xml
       |+ web.xml
|+ META-INF  // contains the descriptors
    |+ application.xml
    |+ jboss-app.xml
```

##### Deploy the project
```
ant deploy
```
```
/*
Buildfile: C:\DEV\git\bachmeb\jboss-local\apps\jsfejb3\build.xml

deploy:
     [copy] Copying 1 file to C:\DEV\jboss\eap\5.1.0\jboss-as\server\default\deploy

BUILD SUCCESSFUL
Total time: 0 seconds
*/
```

##### Test the application
* http://localhost:8080/jsfejb3/


