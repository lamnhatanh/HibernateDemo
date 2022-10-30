import domain.Pizza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class FlushAndClearDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // add a large number of pizzas to the database
            for (int i = 10; i < 999; i++) {
                Pizza pizza = new Pizza(i, "pizza " + i, 50.0);
                session.save(pizza);

                // for every 100 pizzas, flush the session
                if(i % 100 == 0) {
                    // execute the statements in the session
                    session.flush();
                    // clear all statements in the session
                    session.clear();
                }
                session.evict(pizza);
            }
            // commit changes to the database
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;

        } finally {
            session.close();
        }
    }
}
