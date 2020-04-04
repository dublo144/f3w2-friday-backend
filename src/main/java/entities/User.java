package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dto.user.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "user_name", length = 25)
  private String username;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String password;
  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private List<Role> roleList = new ArrayList();

  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList();
    for (Role role : roleList) {
      rolesAsStrings.add(role.getRoleName());
    }
    return rolesAsStrings;
  }

  //TODO Change when password is hashed
   public boolean verifyPassword(String password){
      return BCrypt.checkpw(password, this.password);
    }

  public User(String username, String password) {
    this.username = username;
    this.password = BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public User(UserDTO dto) {
    this.username = dto.getUsername();
    this.password = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
  }

  public void addRole(Role userRole) {
    roleList.add(userRole);
  }

}
