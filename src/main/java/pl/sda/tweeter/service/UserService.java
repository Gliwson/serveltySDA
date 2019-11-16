package pl.sda.tweeter.service;

import pl.sda.tweeter.dao.UserDao;
import pl.sda.tweeter.exception.IncorrectLoginOrPassword;
import pl.sda.tweeter.persistance.entities.SecondMethodConnectionHibernate;
import pl.sda.tweeter.persistance.entities.TbUser;

public class UserService {
    private UserDao userDao = new UserDao();
    private SecondMethodConnectionHibernate secondMethodConnectionHibernate = new SecondMethodConnectionHibernate();

    public TbUser getTbUser(String login, String password) throws IncorrectLoginOrPassword {
        TbUser userByLogin = secondMethodConnectionHibernate.getUserByLogin(login);
        if (userByLogin == null || !userByLogin.getPassword().equals(password)) {
            throw new IncorrectLoginOrPassword("incorrect login or password");
        }
        return userByLogin;
    }
}


