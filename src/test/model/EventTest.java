// Author: Ali Madooei <alimadooei@gmail.com>
// Upstream: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event event0;
    private Event event1;
    private Event event2;
    private Event event3;
    private Date d0;
    private Date d1;

    @BeforeEach
    public void runBefore() throws InterruptedException {
        d0 = Calendar.getInstance().getTime();

        event0 = new Event("Sensor open at door");
        event1 = new Event("Sensor open at door");
        event2 = new Event("Other event");

        d1 = Calendar.getInstance().getTime();

        Thread.sleep(1);
        event3 = new Event("Sensor open at door");
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", event0.getDescription());

        if (d0 == d1) {
            assertEquals(d0, event0.getDate());
        }
    }

    @Test
    public void testToString() {
        if (d0 == d1) {
            assertEquals(d0.toString() + "\n" + "Sensor open at door", event0.toString());
        }
    }

    @Test
    public void testEquals() {
        assertNotEquals(event0, null);
        assertNotEquals(event0, EventLog.getInstance());

        if (d0 == d1) {
            assertEquals(event1, event0);
        }

        assertNotEquals(event2, event0);
        assertNotEquals(event3, event0);
    }

    @Test
    public void testHashCode() {
        if (d0 == d1) {
            assertEquals(event1.hashCode(), event0.hashCode());
        }

        assertNotEquals(event2.hashCode(), event0.hashCode());
        assertNotEquals(event3.hashCode(), event0.hashCode());
    }
}
