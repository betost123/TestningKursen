
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import java.util.Arrays;

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
        assertFalse(result);
    }

    //Test if starttime < 0 returns false and schedule is unchanged
    @Test
    public void testStarttimeLessThanZero() {
        WorkSchedule test3 = new WorkSchedule(24);
        WorkSchedule originalCopy = test3;

        boolean result = test3.addWorkingPeriod("Kurt", -2, 4);
        assertFalse(result);
        assertEquals(originalCopy, test3);

    }

    //Test that start > end returns false and schedule unchanged
    @Test
    public void testStartOverEnd() {                                              //Bug
        WorkSchedule test = new WorkSchedule(24);
        WorkSchedule copy = test;

        boolean result = test.addWorkingPeriod("Knut", 12, 7);
        assertFalse(result);
        assertEquals(test, copy);
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
        WorkSchedule test = new WorkSchedule(24);
        int start = 2;
        int end = 12;

        test.setRequiredNumber(1, start, end);
        test.addWorkingPeriod("Yao", 4, 10);

        WorkSchedule originalCopy = test;

        assertFalse(test.addWorkingPeriod("Bob", 5, 7));
        assertEquals(originalCopy, test);
    }

    //if for any hour in the interval starttime to endtime there is a string in workingEmployees which equal
    // employee then returns false and the schedule is unchanged
    @Test
    public void testNoDupes() {
        WorkSchedule test6 = new WorkSchedule(24);

        test6.addWorkingPeriod("Sven", 5, 10);

        WorkSchedule originalCopy = test6;

        assertFalse(test6.addWorkingPeriod("Sven", 7, 9));
        assertEquals(originalCopy, test6);

    }

    @Test
    public void testItWorks() {
        WorkSchedule test = new WorkSchedule(24);
        test.setRequiredNumber(1, 1, 3);
        WorkSchedule copy = test;

        assertTrue(test.addWorkingPeriod("Hora", 1, 3));
        assertEquals(test, copy);
    }

    //returns true,
    //for i between starttime and endtime, workingEmployees contain a string equal to employee and
    //the rest of the schedule is unchanged
    @Test
    public void testEmployeeIsWorking() {
        WorkSchedule test7 = new WorkSchedule(24);

        int start = 9;
        int end = 15;

        String employee = "Benny";
        test7.setRequiredNumber(1, 9, 17);
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
    //returns an array with olika strings -- a string appears in the return array if and only if
    //it appears in the workingEmployees of at least one hour in the interval starttime to endtime


    //If start < end, should return array with string of the worker, and length 1, schedule unchanged.
    @Test
    public void testStartLessThanEnd() {
        WorkSchedule test = new WorkSchedule(24);
        //WorkSchedule originalCopy = test;

        test.setRequiredNumber(1, 10,12);
        test.addWorkingPeriod("Fressia", 10, 12);

        WorkSchedule originalCopy = test;

        String[] workers = test.workingEmployees(10, 11);


        assertTrue(Arrays.asList(workers).contains("Fressia"));
        assertTrue(workers.length == 1);
        assertEquals(test, originalCopy);

    }

    //If start = end, should return array with string of the worker, and length 1, schedule unchanged.
    @Test
    public void testStartEqualEnd() {
        WorkSchedule test = new WorkSchedule(24);

        test.setRequiredNumber(1, 10,12);
        test.addWorkingPeriod("Fressia", 10, 12);

        WorkSchedule originalCopy = test;

        String[] workers = test.workingEmployees(11, 11);


        assertTrue(Arrays.asList(workers).contains("Fressia"));
        assertTrue(workers.length == 1);
        assertEquals(test, originalCopy);

    }

    //Assert that if starttime exceeds endtime it fails, schedule unchanged.
    @Test
    public void testStartNahOverEnd () {                                             //Bug
        WorkSchedule test = new WorkSchedule(24);
        test.setRequiredNumber(1, 10,12);
        test.addWorkingPeriod("Fressia", 10, 12);
        WorkSchedule original = test;

        assertTrue(test.workingEmployees(12, 10).length == 0);
        assertEquals(test, original);

    }


}
