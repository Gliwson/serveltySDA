package pl.sda.tweeter.service;

import pl.sda.tweeter.exception.IncorrectLoginOrPassword;
import pl.sda.tweeter.persistance.entities.TbUser;

public class UserService {

    public TbUser getTbUser(String login, String password) throws IncorrectLoginOrPassword {
        if (login.equals(password)) {
            return new TbUser(1, login);
        } else {
            throw new IncorrectLoginOrPassword("The incorrect login or password");
        }
    }
}
