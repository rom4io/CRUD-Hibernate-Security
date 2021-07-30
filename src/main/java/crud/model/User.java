package crud.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 3,max = 20,message = "name should be between 3 and 20")
    @NotEmpty(message = "name should not be empty")
    @Column(name = "name")
    private String name;

    @Size(min = 3, max = 30, message = "surname should be between 3 and 30")
    @NotEmpty(message = "surname should not be empty")
    @Column(name = "surname")
    private String surname;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "email should not be empty")
    @Column(name = "email")
    private String email;


    @Min(value = 0, message = "Age should be greater then 0")
    @Column(name = "age")
    private Integer age;

    @Size(min = 4, message = "password should contains at least 4 characters")
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> roleSet;

    public User() {
    }

    public User(String name, String surname, String email, Integer age,String password, Set<Role> roleSet) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.password = password;
        this.roleSet = roleSet;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleSet();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
