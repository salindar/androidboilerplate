package com.salinda.androidboilerplate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import com.salinda.androidboilerplate.ui.util.Utils;

/**
 * Created by: Salinda Rathnayeka on 22/08/2018.
 * Email: salindakrishantha@gmail.com
 */
public class UtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void trimInput() {
        assertEquals(Utils.trimInput("   123   ").length(),  3);
        assertEquals(Utils.trimInput("   123   "),  "123");
        assertNotEquals(Utils.trimInput("   123   "),  "   123   ");
    }
}