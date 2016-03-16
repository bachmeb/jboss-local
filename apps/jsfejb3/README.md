# jsfejb3

## References
* http://docs.jboss.org/jbossas/docs/Getting_Started_Guide/beta422/html/About_the_Example_Applications.html
* http://ant.apache.org/manual/install.html

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

##### Create a project directory
* $DEV\git\jboss-local\apps\jsfejb3

##### Create Todo class
```java
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

##### Create index.xhtml
```html
<h:form>
<ul>
  <li><h:commandLink type="submit" value="Create New Todo" action="create"/></li>
  <li><h:commandLink type="submit" value="Show All Todos" action="todos"/></li>
</ul>
</h:form>
```

##### Create create.xhtml
```html
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
```

##### Create todos.xhtml
```html
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
```
##### Create edit.xhtml
```html
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
<h:commandButton type="submit" id="delete" value="Delete"
                 action="#{todoBean.delete}"/>
</h:form>
```
##### Create TodoDaoInt.java
```java
public interface TodoDaoInt {

  public void persist (Todo todo);
  public void delete (Todo todo);
  public void update (Todo todo);

  public List <Todo> findTodos ();
  public Todo findTodo (String id);
}
```

##### Create TodoDao
```java
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

```
<jboss-app>
  <loader-repository>
    jsfejb3:archive=jsfejb3.ear
  </loader-repository>
</jboss-app>
```

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
