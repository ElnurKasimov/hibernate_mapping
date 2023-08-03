package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
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

    private String email;

    private String firstName;

    private String lastName;

    @ManyToOne
    @JoinColumn (name = "role_id")
    private Role role;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@_$#%&]).{8,}$", message =
            "password should be 8 symbols or more and contain at least one letter, one capital letter,one number and one specific symbol")
    private String password;


    @OneToMany (mappedBy = "owner")
    private Set<ToDo> ownerOf;

    @ManyToMany (mappedBy = "collaborators")
    private Set<ToDo> commonToDos;


}
