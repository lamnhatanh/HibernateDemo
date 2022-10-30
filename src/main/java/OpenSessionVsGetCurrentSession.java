import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

public class OpenSessionVsGetCurrentSession {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        System.out.println("Will openSession() get the same session? " + (session1 == session2));

        session1.close();
        session2.close();

        Session session3 = sessionFactory.getCurrentSession();
        Session session4 = sessionFactory.getCurrentSession();

        System.out.println("Will getCurrentSession() get the same session? " + (session3 == session4));

        HibernateUtil.closeSessionFactory();
    }
}
