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

