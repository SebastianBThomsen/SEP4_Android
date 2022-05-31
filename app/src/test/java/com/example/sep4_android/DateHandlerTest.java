package com.example.sep4_android;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import com.example.sep4_android.model.DateHandler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DateHandlerTest {

    @Test
    public void fromStringToLong() {
        assertEquals(968091620L, DateHandler.fromStringToLong("2000-09-04T20:20:20"));
        assertEquals(1649096420L, DateHandler.fromStringToLong("2022-04-04T20:20:20"));
        assertNotEquals(0,DateHandler.fromStringToLong("2000-09-04T20:20:20"));
        assertNotEquals(Integer.MAX_VALUE, DateHandler.fromStringToLong("2022-04-04T20:20:20"));

        System.out.println(DateHandler.fromStringToLong("2000-09-04T20:20:20"));

    }

    @Test
    public void fromLongToString() {
        assertEquals("04/09-2000 20:20:20",DateHandler.fromLongToString(968091620L));
        assertEquals("04/04-2022 20:20:20",DateHandler.fromLongToString(1649096420L));
        assertNotEquals("Poul",DateHandler.fromLongToString(968091620L));
        assertNotEquals(null,DateHandler.fromLongToString(1649096420L));
    }

    @Test
    public void fromLongToStringDatePicker() {

        System.out.println(DateHandler.fromLongToStringDatePicker(968091620000L));
        assertEquals("04/09-2000",DateHandler.fromLongToStringDatePicker(968091620000L));
    }

}
