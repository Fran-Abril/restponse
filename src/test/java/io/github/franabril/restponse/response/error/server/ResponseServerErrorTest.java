package io.github.franabril.restponse.response.error.server;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import io.github.franabril.restponse.error.model.ErrorDTO;
import io.github.franabril.restponse.exception.RestponseException;
import io.github.franabril.restponse.response.utils.ExampleEnumError;

/**
 * Unit test for ResponseServerError.
 *
 * @author ffrannabril@gmail.com
 *
 */
public class ResponseServerErrorTest {

    @Test
    public void testResponseServerError() throws Exception {
        String arg = "eat pizza";

        // prepare expected results
        Status statusExpected = Status.NOT_IMPLEMENTED;
        String codeExpected = ExampleEnumError.class.getSimpleName() + "." + ExampleEnumError.SERVER_ERROR;
        String messageExpected = "The request method {"
                + arg
                + "} is not supported by the server and cannot be handled";

        // create some exception
        RestponseException ex = new RestponseException(ExampleEnumError.SERVER_ERROR, arg);
        // create Response Server Error
        ResponseServerError error = new ResponseServerError(ex, statusExpected);
        ErrorDTO dto = (ErrorDTO) error.getResponse().getEntity();

        // check
        assertEquals(statusExpected.getStatusCode(), error.getResponse().getStatus());
        assertEquals(codeExpected, dto.getCode());
        assertEquals(messageExpected, dto.getMessage());
    }
}
