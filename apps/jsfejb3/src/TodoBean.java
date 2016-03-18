import javax.naming.InitialContext;
import java.util.*;
import javax.faces.model.*;
import javax.faces.context.*;

@Stateful
public class TodoBean {

  private Todo todo;
  
  public String message;
  
  public int count = 0;

  public TodoBean () {
    FacesContext fc = FacesContext.getCurrentInstance();
    Map requestParams = fc.getExternalContext().getRequestParameterMap();
    String id = (String) requestParams.get("tid");
    if (id != null) {
      todo = getDao().findTodo(id);
    } else {
      todo = new Todo ();
    }
	
	message = new String("HELLO");
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

   
  public String generateLoad () {
    
	System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
	
	count++;
	
	message = ""+count;
	
    return "loaded";
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

  public String getMessage () {
    return message;
  }

  public void setMessage (String mess) {
    this.message = mess;
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

