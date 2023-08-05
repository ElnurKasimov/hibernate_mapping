package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;

@SpringBootTest
public class UserTests {

    private static Role mentorRole;
    private static Role traineeRole;
    private static User validUser;

    @BeforeAll
    static void init(){
        mentorRole = new Role();
        mentorRole.setName("MENTOR");
        traineeRole = new Role();
        traineeRole.setName("TRAINEE");
        validUser  = new User();
        validUser.setEmail("valid@cv.edu.ua");
        validUser.setFirstName("Valid-Name");
        validUser.setLastName("Valid-Name");
        validUser.setPassword("qwQW12!@");
        validUser.setRole(traineeRole);
    }

    @Test
    @DisplayName("Test that User with valid email created")
        void userWithValidEmail() {
        User user = new User();
        user.setEmail("rty5@i.ua");
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    @DisplayName("Test that valid User created")
    void createValidUser() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @DisplayName("Test checking different cases with invalid User email")
    @MethodSource("provideInvalidEmailUser")
    void constraintViolationInvalid(String input, String errorValue) {
        User user = new User();
        user.setEmail(input);
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidEmailUser(){
        return Stream.of(
                Arguments.of("invalidEmail", "invalidEmail"),
                Arguments.of("email@", "email@"),
                Arguments.of("@invalid", "@invalid"),
                Arguments.of("", ""),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @DisplayName("Test checking different cases with invalid User firstName")
    @MethodSource("provideInvalidFirstNameUser")
    void constraintViolationInvalidFirstName(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(input);
        user.setLastName(validUser.getLastName());
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidFirstNameUser(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("-Invalid", "-Invalid"),
                Arguments.of("Invalid-invalid", "Invalid-invalid"),
                Arguments.of("invalid-Invalid", "invalid-Invalid"),
                Arguments.of("invalid-invalid", "invalid-invalid")
        );
    }

    @ParameterizedTest
    @DisplayName("Test checking different cases with invalid User lastName")
    @MethodSource("provideInvalidLastNameUser")
    void constraintViolationInvalidLastName(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(input);
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidLastNameUser(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("-Invalid", "-Invalid"),
                Arguments.of("Invalid-invalid", "Invalid-invalid"),
                Arguments.of("invalid-Invalid", "invalid-Invalid"),
                Arguments.of("invalid-invalid", "invalid-invalid")
        );
    }

    @Test
    @DisplayName("Test that User role not null")
    void testViolationOnNullUserRole() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword("qwQW12!@");
        user.setRole(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @ParameterizedTest
    @DisplayName("Test checking different cases with invalid User password")
    @MethodSource("provideInvalidUserPassword")
    void constraintViolationInvalidUserPassword(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword(input);
        user.setRole(traineeRole);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidUserPassword(){
        return Stream.of(
                Arguments.of("aA&1", "aA&1"),
                Arguments.of("aaAA^%ds", "aaAA^%ds"),
                Arguments.of("aaaAAA123", "aaaAAA123"),
                Arguments.of("AAA123^&*", "AAA123^&*"),
                Arguments.of("aaa123@#$", "aaa123@#$")
        );
    }

    @Test
    @DisplayName("Test that User password not null")
    void testViolationOnNullUserPassword() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword(null);
        user.setRole(traineeRole);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Test that User password not empty")
    void testViolationOnEmptyUserPassword() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword("");
        user.setRole(traineeRole);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(2, violations.size());
        //expected =2 because it will be violation @NotBlank and @Pattern
    }

    @Test
    @DisplayName("Test that User password not blank")
    void testViolationOnBlankUserPassword() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword("    ");
        user.setRole(traineeRole);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(2, violations.size());
        //expected =2 because it will be violation @NotBlank and @Pattern
    }



}
