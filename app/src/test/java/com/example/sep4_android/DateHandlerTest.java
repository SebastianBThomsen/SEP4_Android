package com.example.sep4_android;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.sep4_android.model.DateHandler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

@RunWith(JUnit4.class)
public class DateHandlerTest {

    @Test
    public void fromStringToLong() {
        assertEquals(968091620000L, DateHandler.fromStringToLong("2000-09-04T20:20:20"));
        assertEquals(1649096420000L, DateHandler.fromStringToLong("2022-04-04T20:20:20"));
        assertNotEquals(0,DateHandler.fromStringToLong("2000-09-04T20:20:20"));
        assertNotEquals(Integer.MAX_VALUE, DateHandler.fromStringToLong("2022-04-04T20:20:20"));



    }

    @Test
    public void fromLongToString() {
        assertEquals("04/09-2000 - 08:20:20",DateHandler.fromLongToString(968091620000L));
        assertEquals("04/04-2022 - 08:20:20",DateHandler.fromLongToString(1649096420000L));
        assertNotEquals("Poul",DateHandler.fromLongToString(968091620000L));
        assertNotEquals(null,DateHandler.fromLongToString(1649096420000L));


    }

}
