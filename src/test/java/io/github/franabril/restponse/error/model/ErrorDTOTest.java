package io.github.franabril.restponse.error.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for ErrorDTO.
 *
 * @author ffrannabril@gmail.com
 *
 */
public class ErrorDTOTest {

    @Test
    public void testEmptyConstructor() throws Exception {
        assertNotNull(new ErrorDTO());
    }

    @Test
    public void testParentConstructor() throws Exception {
        ErrorDTO parent = new ErrorDTO();
        parent.code("E1").message("message");
        ErrorDTO dto = new ErrorDTO(parent);
        assertNotNull(dto.getCode());
        assertNotNull(dto.getMessage());
        assertNotNull(dto.getArguments());
        assertEquals(Collections.emptyList(), dto.getArguments());
    }

    @Test
    public void testCode() throws Exception {
        String expected = "code";
        ErrorDTO dto = new ErrorDTO();
        dto.setCode(expected);
        assertEquals(expected, dto.getCode());

        dto = new ErrorDTO();
        dto.code(expected);
        assertEquals(expected, dto.getCode());
    }

    @Test
    public void testMessage() throws Exception {
        String expected = "message";
        ErrorDTO dto = new ErrorDTO();
        dto.setMessage(expected);
        assertEquals(expected, dto.getMessage());

        dto = new ErrorDTO();
        dto.message(expected);
        assertEquals(expected, dto.getMessage());
    }

    @Test
    public void testArguments() throws Exception {
        List<String> expected = new ArrayList<>();
        expected.add("Arg1");
        expected.add("Arg2");
        expected.add("Arg3");

        ErrorDTO dto = new ErrorDTO();
        dto.setArguments(expected);
        assertEquals(expected, dto.getArguments());

        dto = new ErrorDTO();
        dto.arguments(expected);
        assertEquals(expected, dto.getArguments());

        String expectedArg = "Arg4";
        dto = new ErrorDTO();
        dto.addArgumentsItem(expectedArg);
        assertEquals(1, dto.getArguments().size());
    }

    @Test
    public void testToString() throws Exception {
        String expected = "class " + ErrorDTO.class.getSimpleName() + " {\n"
                + "\tcode: code\n"
                + "\tmessage: message\n"
                + "\targuments: [Arg1, Arg2]\n"
                + "}";

        ErrorDTO dto = new ErrorDTO();
        dto.code("code").message("message").addArgumentsItem("Arg1").addArgumentsItem("Arg2");

        assertEquals(expected, dto.toString());

        // Other case
        expected = "class " + ErrorDTO.class.getSimpleName() + " {\n"
                + "\tcode: null\n"
                + "\tmessage: null\n"
                + "\targuments: null\n"
                + "}";

        assertEquals(expected, new ErrorDTO().toString());
    }
}
