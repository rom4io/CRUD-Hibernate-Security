package crud.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Size(min = 3,max = 20,message = "name should be between 3 and 20")
    @NotEmpty(message = "name should not be empty")
    @Column(name = "user_name")
    private String name;

    @Size(min = 3, max = 30, message = "surname should be between 3 and 30")
    @NotEmpty(message = "surname should not be empty")
    @Column(name = "user_surname")
    private String surname;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "email should not be empty")
    @Column(name = "user_email")
    private String email;


    @Min(value = 0, message = "Age should be greater then 0")
    @Column(name = "user_age")
    private Integer age;

    public User() {
    }

    public User(Long id, String name, String surname, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
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
