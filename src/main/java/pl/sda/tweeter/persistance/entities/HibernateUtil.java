package pl.sda.tweeter.persistance.entities;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            Metadata metadata = new MetadataSources(serviceRegistry)
                    .getMetadataBuilder()
                    .build();
//            SessionFactory factory = new Configuration()
//                    .configure("hibernate.cfg.xml")
//                    .buildSessionFactory();
            return metadata
                    .getSessionFactoryBuilder()
                    .build();
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
}
