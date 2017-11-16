
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import org.junit.jupiter.api.Assumptions.*;

public class TestWork {

    //a) addWorkingPeriods

    //Test boolean addWorkingPeriods(String employee, int starttime, int endtime)
    @Test
    public void testConstructor() {
        WorkSchedule test1 = new WorkSchedule(24);
         assertNotEquals(test1, null);
    }

    //Test that it doesn't work if employee is null
    @Test
    public void testAddWorkingPeriodEmployeeNull() {
        WorkSchedule test2 = new WorkSchedule(24);
        boolean result = test2.addWorkingPeriod(null, 1, 2);
        assumeFalse(result);
    }

    //Test is starttime < 0 returns false and schedule is unchanged
    @Test
    public void testStarttimeLessThanZero() {
        WorkSchedule test3 = new WorkSchedule(24);
        WorkSchedule originalCopy = test3;

        boolean result = test3.addWorkingPeriod("Kurt", -2, 4);
        assertFalse(result);
        assertEquals(originalCopy, test3);

    }

    //Test if endtime >= size, then return false and schedule is unchanged
    @Test
    public void testEndtimeLessOrEqualSize() {
        WorkSchedule test4 = new WorkSchedule(24);
        WorkSchedule originalCopy = test4;

        boolean result = test4.addWorkingPeriod("Kurt", 3, 24);
        assertFalse(result);
        assertEquals(originalCopy, test4);

        boolean result2 = test4.addWorkingPeriod("Kurt", 3, 28);
        assertFalse(result2);
        assertEquals(originalCopy, test4);
    }

    //if for any hour in the interval starttime to endtime the length of workingEmployees is
    // equal to requiredNumber then returns false and the schedule is unchanged
    @Test
    public void testWorkingEmployeesIsEqualToRequiredNumber() {
        WorkSchedule test5 = new WorkSchedule(24);
        int start = 2;
        int end = 12;
        int n = test5.workingEmployees(start, end).length;
        test5.setRequiredNumber(n, start, end);

        assumeTrue((test5.workingEmployees(6, 9).length == n));

        WorkSchedule originalCopy = test5;

        assertFalse(test5.addWorkingPeriod("Bob", 5, 7));
        assertEquals(originalCopy, test5);
    }

    //if for any hour in the interval starttime to endtime there is a string in workingEmployees which equal
    // employee then returns false and the schedule is unchanged
    @Test
    public void testNoDupes() {
        WorkSchedule test6 = new WorkSchedule(24);

        test6.addWorkingPeriod("Bob", 5, 10);

        WorkSchedule originalCopy = test6;

        assertFalse(test6.addWorkingPeriod("Bob", 7, 9));
        assertEquals(originalCopy, test6);

    }

    //returns true,
    //for i between starttime and endtime, workingEmployees contain a string equal to employee and
    //the rest of the schedule is unchanged
    @Test   //doesn't work
    public void testEmployeeIsWoking() {
        WorkSchedule test7 = new WorkSchedule(24);
        int start = 9;
        int end = 15;

        String employee = "Benny";

        test7.addWorkingPeriod(employee, start, end);

        WorkSchedule originalCopy = test7;

        String[] workers = test7.workingEmployees(10, 13);

        boolean contains = false;
        for(String e : workers) {
            if (e.equals(employee)) {
                contains = true;
            }
        }
        assertTrue(contains);
        assertEquals(originalCopy, test7);

    }


    //b) workingEmployee

    //if starttime <= endtime then
    //returns an array with distinct strings -- a string appears in the return array if and only if
    //it appears in the workingEmployees of at least one hour in the interval starttime to endtime
    @Test
    public void testStarttimmeNotLessThanOrEqualEndtime() {
        WorkSchedule test = new WorkSchedule(24);

    }



}
