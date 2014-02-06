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

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void firstNameIsEmpty() {
        User user = new User( "", "Bloggs", "email@example.com");
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
        User user = new User( "Joe", "", "email@example.com");
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
        User user = new User( "Joe", "Bloggs", "");
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
        User user = new User( "Joe", "Bloggs", "Not An Email");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "not a well-formed email address",
                constraintViolations.iterator().next().getMessage()
        );
    }

}
