import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import org.junit.*;
import static org.junit.Assert.*;

public class BinarySearch {

    /**
     * requires: - 'array' is not null.
     *           - the array is sorted by increasing value, i.e. for each index
     *             'i' and index 'j', if 'i' < 'j' then 'array[i]' < 'array[j]'.
     * ensures:  - if there is an index 'i' such that 'array[i] == value', the
     *             method returns true.
     *           - otherwise, it returns false.
     *           - in either case, 'array' is left unchanged.
     **/
    public static boolean search(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        //System.out.println("left: " + left + " right: " + right);
        while (left <= right) {
            int index = (right + left) / 2;
            //System.out.println("index: " + index);
            if (array[index] == value)
                return true;
            if (array[index] < value)
                left = index + 1;
            else
                right = index - 1;
        }
        return false;

    }

    public static void main(String[] arg) {
        int[] array = {1,2,3,4,5,6,7,8};
        int value = 5;

        if (search(array,value))
            System.out.println("The value " + value + " is in the array.");
        else {
            System.out.println("The value " + value + " is not in the array.");
        }
    }

    @Test
    public void testItWorks() {
        int[] array = {3, 4, 7};

        assertTrue(search(array, 4));
        assertTrue(search(array, 3));
        assertTrue(search(array, 7));
        assertFalse(search(array, 5));
        assertFalse(search(array, 1));
        assertFalse(search(array, 2));

    }

}