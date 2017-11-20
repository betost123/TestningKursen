import org.junit.*;
import static org.junit.Assert.*;

public class TestSet {
    private Set set;

    //a)

    @Test
    public void testInsertStatementCoverage() {
        set = new Set();

        set.insert(2);
        assertTrue(set.toArray().length == 1);
        assertTrue(set.toArray()[0] == 2);

        set.insert(3);
        assertTrue(set.toArray().length == 2);
        assertTrue(set.toArray()[0] == 2);
        assertTrue(set.toArray()[1] == 3);

        set.insert(1);
        assertTrue(set.toArray().length == 4);
        assertTrue(set.toArray()[0] == 1);
        assertTrue(set.toArray()[1] == 2);
        assertTrue(set.toArray()[2] == 3);
        assertTrue(set.toArray()[3] == 1);

        set.insert(1);
        assertTrue(set.toArray().length == 5);
        assertTrue(set.toArray()[0] == 1);
        assertTrue(set.toArray()[1] == 2);
        assertTrue(set.toArray()[2] == 3);
        assertTrue(set.toArray()[3] == 1);
        assertTrue(set.toArray()[4] == 1);

    }

    @Test
    public void testInsertBranchCoverage () {
        set = new Set();

        set.insert(2);
        set.insert(2);
        set.insert(3);
        set.insert(1);

        assertTrue(set.toArray().length == 5);
        assertTrue(set.toArray()[0] == 1);
        assertTrue(set.toArray()[1] == 2);
        assertTrue(set.toArray()[2] == 2);
        assertTrue(set.toArray()[3] == 3);
        assertTrue(set.toArray()[4] == 1);


    }

    //b

    @Test
    public void testMemberStatementCoverage() {
        set = new Set();

        assertFalse(set.member(1));

        set.insert(2);
        assertTrue(set.member(2));

        assertFalse(set.member(1));
    }

    @Test
    public void testMemberBranchCoverage() {
        set = new Set();

        assertFalse(set.member(1));

        set.insert(1);
        set.insert(3);

        assertFalse(set.member(2));
        assertTrue(set.member(1));
    }

    //c

    @Test
    public void testSectionStatementAndBranchCoverage() {
        set = new Set();
        Set setS = new Set();

        set.insert(1);
        set.insert(2);
        set.insert(3);
        set.insert(5);

        setS.insert(1);
        setS.insert(4);
        setS.insert(2);

        set.section(setS);

        assertFalse(set.member(1));
        assertTrue(set.member(3));

    }

    //d

    @Test
    public void testContainsArithTripeStatementAndBranchCoverage() {
        set = new Set();

        set.insert(1);
        set.insert(2);
        set.insert(3);

        assertTrue(set.containsArithTriple());

        set = new Set();

        set.insert(1);
        set.insert(2);
        set.insert(5);

        assertFalse(set.containsArithTriple());
    }

    //e See Set.java

}
