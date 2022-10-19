package org.zuzex.model;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity(name = "User")
@Table(name = "users")
@UserDefinition
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends PanacheEntity {
    @Username
    public String username;

    @Password
    public String password;

    @Roles
    public String role;

    /*TODO ВЫнести в сервис*/
    public static void add(String username, String password, String role) {
        User user = new User();
        user.username = username;
        user.password = BcryptUtil.bcryptHash(password);
        user.role = role;
        user.persist();
    }
}
