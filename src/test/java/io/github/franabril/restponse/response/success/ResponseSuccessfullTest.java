package io.github.franabril.restponse.response.success;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

/**
 * Unit test for ResponseSuccessfull.
 *
 * @author ffrannabril@gmail.com
 *
 */
public class ResponseSuccessfullTest {

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
    public void testResponseSuccessfull() throws Exception {
        // prepare expected results
        String nameExpected = "Luke Skywalker";
        int ageExpected = 30;
        Status statusExpected = Status.CREATED;

        // create dto to send in server response
        ExampleDTO dto = new ExampleDTO(nameExpected, ageExpected);
        // create success response
        ResponseSuccessfull response = new ResponseSuccessfull(dto, statusExpected);

        // check
        assertEquals(statusExpected.getStatusCode(), response.getResponse().getStatus());
        assertEquals("name=" + nameExpected + ", age=" + ageExpected + "",
                response.getResponse().getEntity().toString());
    }
}
