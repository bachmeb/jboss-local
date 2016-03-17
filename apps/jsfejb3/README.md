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

##### Download and install JBoss
* https://github.com/bachmeb/jboss-local

##### Download the Application Platform 4.3.0.GA_CP09 documentation
* https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?downloadType=distributions&product=appplatform&version=4.3.0.GA_CP09

##### Unpack the contents of jboss-eap-docs-4.3.0.GA_CP09 to
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
* DEV\git\jboss-local\apps\jsfejb3\src\TodoDaoInt.java
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
* DEV\git\jboss-local\apps\jsfejb3\src\TodoDao.java

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

##### Review the jboss-app.xml
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

##### Review the Faces config file
```
//faces-config.xml
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
`





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

##### Verify ant
    ant version

##### Open a command prompt
	* Start > Run > cmd

##### Build the project
	cd $DEV\git\jboss-local\apps\jsfejb3
	ant

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


##### Setup a local MySQL server
* https://github.com/bachmeb/mysql-local
