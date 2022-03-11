package io.github.franabril.restponse.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import io.github.franabril.baseexception.BaseException;
import io.github.franabril.restponse.error.model.ErrorDTO;
import io.github.franabril.restponse.response.utils.ExampleEnumError;

/**
 * Unit test for RestponseError.
 *
 * @author ffrannabril@gmail.com
 *
 */
public class RestponseErrorTest {

    class MyCustomException extends BaseException {

        public MyCustomException(ExampleEnumError reason) {
            super(reason);
        }
    }

    @Test(expected = RestponseError.class)
    public void testExample() throws Exception {
        try {
            // some exception catch in Rest layout
            throw new MyCustomException(ExampleEnumError.CLIENT_ERROR);
        } catch (MyCustomException e) {
            // turn the exception into a Restponse
            throw new RestponseError(e, Status.FORBIDDEN);
        }
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDoNotThis1() throws Exception {
        // NOTE: The Status with StatusType javax.ws.rs.core.FAMILY.SUCCESSFUL do not
        // use here!
        throw new RestponseError(new MyCustomException(ExampleEnumError.CLIENT_ERROR), Status.OK);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDoNotThis2() throws Exception {
        // NOTE: The Status with StatusType javax.ws.rs.core.FAMILY.SUCCESSFUL do not
        // use here!
        new RestponseError(Status.OK, ExampleEnumError.SERVER_ERROR, "testCase2");
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDoNotThis3() throws Exception {
        // NOTE: The Status with StatusType javax.ws.rs.core.FAMILY.SUCCESSFUL do not
        // use here!
        new RestponseError(Status.CREATED, ExampleEnumError.SERVER_ERROR, "testCase2");
    }

    @Test
    public void testCase1() throws Exception {
        Status statusExpected = Status.FORBIDDEN;
        String codeExpected = ExampleEnumError.class.getSimpleName() + "." + ExampleEnumError.CLIENT_ERROR;

        // turn the exception into a Restponse
        RestponseError response = new RestponseError(
                new MyCustomException(ExampleEnumError.CLIENT_ERROR),
                statusExpected);
        ErrorDTO result = (ErrorDTO) response.getResponse().getEntity();

        // check
        assertEquals(statusExpected.getStatusCode(), response.getResponse().getStatus());
        assertEquals(codeExpected, result.getCode());
        assertNotNull(result.getMessage());
    }

    @Test
    public void testCase2() throws Exception {
        Status statusExpected = Status.NOT_IMPLEMENTED;
        String codeExpected = ExampleEnumError.class.getSimpleName() + "." + ExampleEnumError.SERVER_ERROR;

        // turn the exception into a Restponse
        RestponseError response = new RestponseError(
                statusExpected,
                ExampleEnumError.SERVER_ERROR,
                "testCase2");
        ErrorDTO result = (ErrorDTO) response.getResponse().getEntity();

        // check
        assertEquals(statusExpected.getStatusCode(), response.getResponse().getStatus());
        assertEquals(codeExpected, result.getCode());
        assertNotNull(result.getMessage());
    }
}
