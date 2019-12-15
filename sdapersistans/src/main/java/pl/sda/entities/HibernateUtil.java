package pl.sda.entities;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.entities.dao.UserDao;
import tweeter.exception.IncorrectLoginOrPassword;

import static pl.sda.entities.util.EnvironmentVariableUtil.getVariable;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final String USERNAME = "hibernate.connection.username";
    private static final String PASSWORD = "hibernate.connection.password";
    private static final String URL = "hibernate.connection.url";

    private static SessionFactory buildSessionFactory() {
        try {

            return new Configuration()
                    .setProperty(USERNAME, getVariable(USERNAME))
                    .setProperty(PASSWORD, getVariable(PASSWORD))
                    .setProperty(URL, getVariable(URL) + getVariable(USERNAME))
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed. " + e);
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutDown() {
        getSessionFactory().close();
    }


    public static class UserService {
        private UserDao userDao = new UserDao();

        public TbUser getTbUser(String login, String password) throws IncorrectLoginOrPassword {
            TbUser userByLogin;

        }
    }
}
