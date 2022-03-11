package io.github.franabril.restponse.response.error.client;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import io.github.franabril.restponse.error.model.ErrorDTO;
import io.github.franabril.restponse.exception.RestponseException;
import io.github.franabril.restponse.response.utils.ExampleEnumError;

/**
 * Unit test for ResponseClientError.
 *
 * @author ffrannabril@gmail.com
 *
 */
public class ResponseClientErrorTest {

    @Test
    public void testResponseClientError() throws Exception {
        String arg = "Hulk";

        // prepare expected results
        Status statusExpected = Status.FORBIDDEN;
        String codeExpected = ExampleEnumError.class.getSimpleName() + "." + ExampleEnumError.CLIENT_ERROR;
        String messageExpected = "The client {" + arg + "} does not have access rights to the content";

        // create some exception
        RestponseException ex = new RestponseException(ExampleEnumError.CLIENT_ERROR, arg);
        // create Response Client Error
        ResponseClientError error = new ResponseClientError(ex, statusExpected);
        ErrorDTO dto = (ErrorDTO) error.getResponse().getEntity();

        // check
        assertEquals(statusExpected.getStatusCode(), error.getResponse().getStatus());
        assertEquals(codeExpected, dto.getCode());
        assertEquals(messageExpected, dto.getMessage());
    }
}
