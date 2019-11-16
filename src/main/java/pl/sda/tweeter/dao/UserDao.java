package pl.sda.tweeter.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.tweeter.persistance.entities.HibernateUtil;
import pl.sda.tweeter.persistance.entities.TbUser;

import javax.persistence.NoResultException;
import java.io.Serializable;

public class UserDao implements Serializable {
    private static final long serialVersionUID = -1345744980472312709L;

    public TbUser getUserByLogin(String login) {
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("select e from " + TbUser.class.getName() + " e where e.login =:username");
        q.setParameter("username", login);
        TbUser singleResult;
        try {
            singleResult = (TbUser) q.getSingleResult();
        } catch (NoResultException e) {
            singleResult = null;
        }
        transaction.commit();
        return singleResult;
    }
}
