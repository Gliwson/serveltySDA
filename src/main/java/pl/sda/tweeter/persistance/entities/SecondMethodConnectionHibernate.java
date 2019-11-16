package pl.sda.tweeter.persistance.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class SecondMethodConnectionHibernate {

    public TbUser getUserByLogin(String login) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("select e from " + TbUser.class.getName() + " e where e.login =:usname");
            query.setParameter("usname", login);
            TbUser tbUser = (TbUser) query.getSingleResult();
            session.getTransaction().commit();
            return tbUser;
        }
    }

}
