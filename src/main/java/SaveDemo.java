import domain.Pizza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class SaveDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            // Different ways to save a new instance in Hibernate

            // Save and get the id
            Pizza pizza1 = new Pizza(4, "cosmic pizza", 99.9);
            Integer id = (Integer) session.save(pizza1);
            System.out.println("Saved pizza ID: " + id);

            // Update
            // update the price of the pizza with id = 1 to be 123.0
            Pizza pizza2 = session.get(Pizza.class, 1);
            pizza2.setPrice(123.0);
            session.update(pizza2);

            // SaveOrUpdate, Persist
            Pizza pizza3 = new Pizza(1, "mushroom pizza", 22.0);
            session.saveOrUpdate(pizza3);
            session.persist(pizza3);

            // Merge (returns a new instance)
            Pizza pizza4 = new Pizza(1, "square pizza", 14.0);
            Pizza pizza5 = (Pizza) session.merge(pizza4);
            System.out.println("Merged pizza returns a new instance: " + pizza5);
            System.out.println("Original pizza remains unchanged: " + pizza4);

            // commit the transaction
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;

        } finally {
            session.close();
        }
    }
}
