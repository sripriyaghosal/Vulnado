package com.scalesec.vulnado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ServerErrorTest {
    /**
     * Method under test: {@link ServerError#ServerError(String)}
     */
    @Test
    public void testConstructor() {
        ServerError actualServerError = new ServerError("Exception");
        assertNull(actualServerError.getCause());
        assertEquals(0, actualServerError.getSuppressed().length);
        assertEquals("Exception", actualServerError.getMessage());
        assertEquals("Exception", actualServerError.getLocalizedMessage());
    }

    /**
     * Method under test: {@link ServerError#ServerError(String)}
     */
    @Test
    public void testConstructor2() {
        ServerError actualServerError = new ServerError("Exception");
        assertNull(actualServerError.getCause());
        assertEquals(0, actualServerError.getSuppressed().length);
        assertEquals("Exception", actualServerError.getMessage());
        assertEquals("Exception", actualServerError.getLocalizedMessage());
    }
}

