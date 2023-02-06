package com.scalesec.vulnado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class BadRequestTest {
    /**
     * Method under test: {@link BadRequest#BadRequest(String)}
     */
    @Test
    public void testConstructor() {
        BadRequest actualBadRequest = new BadRequest("Exception");
        assertNull(actualBadRequest.getCause());
        assertEquals(0, actualBadRequest.getSuppressed().length);
        assertEquals("Exception", actualBadRequest.getMessage());
        assertEquals("Exception", actualBadRequest.getLocalizedMessage());
    }

    /**
     * Method under test: {@link BadRequest#BadRequest(String)}
     */
    @Test
    public void testConstructor2() {
        BadRequest actualBadRequest = new BadRequest("Exception");
        assertNull(actualBadRequest.getCause());
        assertEquals(0, actualBadRequest.getSuppressed().length);
        assertEquals("Exception", actualBadRequest.getMessage());
        assertEquals("Exception", actualBadRequest.getLocalizedMessage());
    }
}

