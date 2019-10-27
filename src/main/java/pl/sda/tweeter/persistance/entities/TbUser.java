package pl.sda.tweeter.persistance.entities;

import lombok.Value;

@Value
public class TbUser {
    private Integer userId;
    private String login;
}
