package io.github.franabril.restponse.response;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

/**
 * Unit test for Restponse.
 *
 * @author ffrannabril@gmail.com
 *
 */
public class RestponseTest {

    class ExampleDTO {

        String name;
        int age;

        ExampleDTO(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "name=" + name + ", age=" + age;
        }
    }

    @Test
    public void testExamples() throws Exception {
        Response result = Restponse.get();
        assertEquals(Status.OK.getStatusCode(), result.getStatus());
        assertEquals(Status.OK.name(), result.getEntity().toString());

        Object entityExpected = "Hello World!";
        result = Restponse.with(entityExpected);
        assertEquals(Status.OK.getStatusCode(), result.getStatus());
        assertEquals(entityExpected, result.getEntity().toString());

        result = Restponse.with(new ExampleDTO("Anakin Skywalker", 28));
        assertEquals(Status.OK.getStatusCode(), result.getStatus());
        assertEquals("name=Anakin Skywalker, age=28", result.getEntity().toString());

        result = Restponse.with(new ExampleDTO("Obi Wan Kenobi", 40),
                Status.CREATED);
        assertEquals(Status.CREATED.getStatusCode(), result.getStatus());
        assertEquals("name=Obi Wan Kenobi, age=40", result.getEntity().toString());

        result = Restponse.with(Status.CREATED);
        assertEquals(Status.CREATED.getStatusCode(), result.getStatus());
        assertEquals(Status.CREATED.name(), result.getEntity().toString());
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDoNotThis() throws Exception {
        // NOTE: The Status with StatusType
        // javax.ws.rs.core.FAMILY.CLIENT_ERROR,SERVER_ERROR do not
        // use here!
        Restponse.with(Status.NOT_FOUND);
    }
}
