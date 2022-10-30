import domain.Pizza;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class GetVsLoadDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        Pizza pizzaA, pizzaB;

        try{
            transaction = session.beginTransaction();

            // When get() is called, Hibernate returns the actual object or null if the object is not found.
            // When load() is called, Hibernate creates a dummy object (Proxy) which does not contain anything.
            // The actual object is loaded only when it is needed, which is when a method is called on the object.
            // If the object is not found, an exception is thrown.

            int id = 999; // invalid id in database
            pizzaA = session.get(Pizza.class, id);
            pizzaB = session.load(Pizza.class, id);

            System.out.println("pizza A: " + pizzaA); // null
            System.out.println("pizza B: " + pizzaB); // 999

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;

        } finally {
            session.close();
        }
        System.out.println("After the session is closed...");
        System.out.println(pizzaA); // null
        System.out.println(pizzaB); // throws LazyInitializationException

    }
}