package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "role_sequence"),
                    @Parameter(name = "initial_value", value = "100"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @NotBlank
    @Email (message = "invalid email")
    private String email;

    @NotBlank
    @Size(min = 2, max = 255, message = "firstName has to be from 2 to 255 symbols and can contain hyphen but not first, not last")
    @Pattern(regexp = "^(?!-)[A-Z][a-zA-Z0-9]{1,251}(?:-[A-Z][a-zA-Z0-9]{2,})?(?<!-)$")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 255, message = "firstName has to be from 2 to 255 symbols and can contain hyphen but not first, not last")
    @Pattern(regexp = "^(?!-)[A-Z][a-zA-Z0-9]{1,251}(?:-[A-Z][a-zA-Z0-9]{2,})?(?<!-)$")
    private String lastName;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "role_id")
    private Role role;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@_$#%&]).{8,}$", message =
            "password should be 8 symbols or more and contain at least one letter, one capital letter,one number and one specific symbol")
    private String password;


    @OneToMany (mappedBy = "owner")
    private Set<ToDo> ownerOf;

    @ManyToMany (mappedBy = "collaborators")
    private Set<ToDo> commonToDos;

    public User() {
    }
    @Generated
    public long getId() {
        return id;
    }
    @Generated
    public void setId(long id) {
        this.id = id;
    }
    @Generated
    public String getEmail() {
        return email;
    }
    @Generated
    public void setEmail(String email) {
        this.email = email;
    }
    @Generated
    public String getFirstName() {
        return firstName;
    }
    @Generated
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Generated
    public String getLastName() {
        return lastName;
    }
    @Generated
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Generated
    public Role getRole() {
        return role;
    }
    @Generated
    public void setRole(Role role) {
        this.role = role;
    }
    @Generated
    public String getPassword() {
        return password;
    }
    @Generated
    public void setPassword(String password) {
        this.password = password;
    }
    @Generated
    public Set<ToDo> getOwnerOf() {
        return ownerOf;
    }
    @Generated
    public void setOwnerOf(Set<ToDo> ownerOf) {
        this.ownerOf = ownerOf;
    }
    @Generated
    public Set<ToDo> getCommonToDos() {
        return commonToDos;
    }
    @Generated
    public void setCommonToDos(Set<ToDo> commonToDos) {
        this.commonToDos = commonToDos;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getRole(), user.getRole()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getOwnerOf(), user.getOwnerOf()) && Objects.equals(getCommonToDos(), user.getCommonToDos());
    }
    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getFirstName(), getLastName(), getRole(), getPassword(), getOwnerOf(), getCommonToDos());
    }
    @Generated
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", ownerOf=" + ownerOf +
                ", commonToDos=" + commonToDos +
                '}';
    }
}
