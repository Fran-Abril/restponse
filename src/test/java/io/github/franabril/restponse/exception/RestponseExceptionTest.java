package io.github.franabril.restponse.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.franabril.restponse.response.utils.ExampleEnumError;

/**
 * Unit test for RestponseException.
 *
 * @author ffrannabril@gmail.com
 *
 */
public class RestponseExceptionTest {

    @Test(expected = RestponseException.class)
    public void testThrow() throws Exception {
        throw new RestponseException(ExampleEnumError.GENERIC, "I want to fail");
    }

    @Test
    public void testRestponse() throws Exception {
        RestponseException exception = new RestponseException(ExampleEnumError.GENERIC, "I want to fail");
        assertEquals(ExampleEnumError.GENERIC.getCode(), exception.getCode());
        assertEquals("The project was failed because {I want to fail}", exception.getMessage());
    }
}
