package io.github.franabril.restponse.error.factory;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import io.github.franabril.restponse.error.model.ErrorDTO;

/**
 * Unit test for ErrorFactory.
 *
 * @author ffrannabril@gmail.com
 *
 */
public class ErrorFactoryTest {

    @Test
    public void testMergeErrorIntoDto() throws Exception {
        String code = "code";
        String message = "message";

        List<String> args = new ArrayList<>();
        args.add("Arg1");

        ErrorDTO dto = new ErrorFactory().create(code, message, args);

        assertEquals(code, dto.getCode());
        assertEquals(message, dto.getMessage());
        assertEquals(args, dto.getArguments());
    }

}
