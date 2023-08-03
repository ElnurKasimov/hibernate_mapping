package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table (name = "tasks")
public class Task {
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
    @Size(min = 3, max = 200, message = "name has to be from 3 to 200 any symbols")
    @Pattern(regexp = "^.{3,200}$")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;


    @NotNull
    @ManyToOne
    @JoinColumn (name = "state_id")
    private State state;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "todo_id")
    private ToDo toDo;

    public Task() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ToDo getToDo() {
        return toDo;
    }

    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return getId() == task.getId() && getName().equals(task.getName()) && getPriority() == task.getPriority() && getState().equals(task.getState()) && getToDo().equals(task.getToDo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPriority(), getState(), getToDo());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", state=" + state +
                ", toDo=" + toDo +
                '}';
    }
}
