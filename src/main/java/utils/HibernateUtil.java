package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (HibernateUtil.class) {
                if (sessionFactory == null) {
                    try {
                        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                    } catch (Throwable ex) {
                        System.err.println("Initial SessionFactory creation failed." + ex);
                        throw new ExceptionInInitializerError(ex);
                    }
                }
            }
        }
        return sessionFactory;
    }
    public static synchronized void closeSessionFactory() {
        if (sessionFactory != null){
            sessionFactory.close();
        }
    }

//    public static Session openSession() {
//        return sessionFactory.openSession();
//    }

//    public static Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }

}
