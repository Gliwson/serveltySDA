package pl.sda.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_USER", uniqueConstraints = {
        @UniqueConstraint(columnNames = "US_USER_NAME")
})
public class TbUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "US_ID", unique = true, nullable = false)
    private Integer userId;
    @Column(name = "US_USER_NAME", updatable = false, length = 25, nullable = true)
    private String login;
    @Column(name = "US_PASSWORD", updatable = false, length = 25)
    private String password;
}
