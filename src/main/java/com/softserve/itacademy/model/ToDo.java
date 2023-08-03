package com.softserve.itacademy.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "todos")

public class ToDo {
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

    @NotNull
    private Instant createdAt;

    @NotBlank (message = "title shouldn't be null, empty or blank")
    @Column(nullable = false, unique = true)
    private String title;

    @NotNull (message = "owner shouldn't be null")
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @NotNull
    @ManyToMany
    @JoinTable (
            name = "todo_collaborator",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "collaborator_id")
    )
    private Set<User> collaborators = new HashSet<>();


    public ToDo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(Set<User> collaborators) {
        this.collaborators = collaborators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return getId() == toDo.getId() && Objects.equals(getCreatedAt(), toDo.getCreatedAt()) && Objects.equals(getTitle(), toDo.getTitle()) && Objects.equals(getOwner(), toDo.getOwner()) && Objects.equals(getCollaborators(), toDo.getCollaborators());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreatedAt(), getTitle(), getOwner(), getCollaborators());
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", title='" + title + '\'' +
                ", owner=" + owner +
                ", collaborators=" + collaborators +
                '}';
    }
}
