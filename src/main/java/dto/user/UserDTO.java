package dto.user;


import entities.Role;
import entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    String username;
    String password;
    List<Role> roleList;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roleList = user.getRoleList();
    }
}
