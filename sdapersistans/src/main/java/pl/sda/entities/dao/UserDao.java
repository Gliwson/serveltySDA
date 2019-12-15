package pl.sda.entities.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.sda.entities.TbUser;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.io.Serializable;

import static pl.sda.entities.HibernateUtil.getSessionFactory;

public class UserDao implements Serializable {
    private static final long serialVersionUID = -1345744980472312709L;

    public TbUser getUserByLogin(String login)
            throws HibernateException, NoResultException, NonUniqueResultException {
        try (Session session = getSessionFactory()
                .openSession()) {
            Query q = session.createQuery("select e from " + TbUser.class.getName() + " e where e.login =:username");
            q.setParameter("username", login);
            //Jeśli jeden z kroków transakcji zawiedzie, możliwe będzie cofnięcie całej transakcji
            session.beginTransaction();//starts a new transaction.
            TbUser singleResult = (TbUser) q.getSingleResult();
            session.getTransaction().commit();//ends the unit of work unless we are in FlushMode.NEVER
            return singleResult;
        }
        //try catch with resources sam wszystko pozamyka, czyli zrobi to co poniżej:
//        catch (HibernateException e) {
//            session.getTransaction().rollback();//forces this transaction to rollback.
//            throw e;
//        } finally {
//            session.close();
//        }

    }
}
