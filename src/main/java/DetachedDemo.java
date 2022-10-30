import domain.Pizza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class DetachedDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        Pizza pizza = null;
        try {
            transaction = session.beginTransaction();

            // get pizza with id = 1 from the database
            pizza = session.get(Pizza.class, 1);

            //move to detached state
            session.evict(pizza);

            // once evisted, changes to this instance will no longer be persisted in database
            pizza.setName("Detached Pizza");

            // can use either update or saveOrUpdate to move the entity from detached to persistent state
//            session.update(pizza);
//            session.saveOrUpdate(pizza);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;

        } finally {
            session.close();
        }
    }
}
