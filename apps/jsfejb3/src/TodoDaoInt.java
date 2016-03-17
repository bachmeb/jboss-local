import java.util.List;

public interface TodoDaoInt {

  public void persist (Todo todo);
  public void delete (Todo todo);
  public void update (Todo todo);

  public List <Todo> findTodos ();
  public Todo findTodo (String id);
}
