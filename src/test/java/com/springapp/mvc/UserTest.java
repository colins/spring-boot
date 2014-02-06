package com.springapp.mvc;

/**
 * Created by pivotal on 2/6/14.
 */

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Date;
import javax.el.ExpressionFactory.*;

public class UserTest {

    private static Validator validator;
    private User user;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        user = new User();
        user.setFirstName("Joe");
        user.setLastName("Bloggs");
        user.setEmail("email@example.com");
    }

    @Test
    public void firstNameIsEmpty() {
        user.setFirstName("");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "may not be empty",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void lastNameIsEmpty() {
        user.setLastName("");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "may not be empty",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void emailIsEmpty() {
        user.setEmail("");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "may not be empty",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void emailIsInvalid() {
        user.setEmail("Not An Email");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "not a well-formed email address",
                constraintViolations.iterator().next().getMessage()
        );
    }

}
