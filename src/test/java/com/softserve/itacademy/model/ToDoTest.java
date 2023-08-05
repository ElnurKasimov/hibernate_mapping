package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {
    private Validator validator;

    @Test
    @DisplayName("Test that title not null")
    void testViolationOnNullToDoTitle() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ToDo todoWithNullTitle = new ToDo();
        todoWithNullTitle.setTitle(null);
        todoWithNullTitle.setOwner(new User());
        todoWithNullTitle.setCollaborators(new HashSet<>());
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todoWithNullTitle);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Test that title not empty")
    void testViolationOnEmptyToDoTitle() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ToDo todoWithEmptyTitle = new ToDo();
        todoWithEmptyTitle.setTitle("");
        todoWithEmptyTitle.setOwner(new User());
        todoWithEmptyTitle.setCollaborators(new HashSet<>());
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todoWithEmptyTitle);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Test that title not blank")
    void testViolationOnBlankToDoTitle() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ToDo todoWithBlankTitle = new ToDo();
        todoWithBlankTitle.setTitle("        ");
        todoWithBlankTitle.setOwner(new User());
        todoWithBlankTitle.setCollaborators(new HashSet<>());
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todoWithBlankTitle);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Test that owner not null")
    void testViolationOnNullToDoOwner() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ToDo todoWithNullOwner = new ToDo();
        todoWithNullOwner.setTitle("invalidOwner");
        todoWithNullOwner.setOwner(null);
        todoWithNullOwner.setCollaborators(new HashSet<>());
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todoWithNullOwner);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Test that collaborators not null")
    void testViolationOnNullToDoCollaborators() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ToDo todoWithNullCollaborators = new ToDo();
        todoWithNullCollaborators.setTitle("invalidCollaborators");
        todoWithNullCollaborators.setOwner(new User());
        todoWithNullCollaborators.setCollaborators(null);
        Set<ConstraintViolation<ToDo>> violations = validator.validate(todoWithNullCollaborators);
        assertEquals(1, violations.size());
    }
}