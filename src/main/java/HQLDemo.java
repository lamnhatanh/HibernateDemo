import domain.Pizza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import javax.persistence.TypedQuery;
import java.util.List;

public class HQLDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            // construct a query to find the pizza with id = 1
            TypedQuery<Pizza> query = session.createQuery(
                    "from Pizza p where p.pizzaId = :id",
                    Pizza.class);

            // setParameter() is used to set the value of the argument in the query
            query.setParameter("id", 1);

            // call getResultList() to execute the query and store our result in a list
            List<Pizza> resultList = query.getResultList();

            //  print out results
            System.out.println("============= HQL Demo =============");
            System.out.println(resultList);

        } finally {
            session.close();
        }
    }
}
