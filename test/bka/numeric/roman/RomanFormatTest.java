package bka.numeric.roman;

/*
 * © Bart Kampers
 */

import java.text.*;
import static org.junit.Assert.assertEquals;
import org.junit.*;


public class RomanFormatTest {


    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    
    @Test
    public void test1() {
        RomanNumberFormat formatter = new RomanNumberFormat();
        String one = formatter.format(1);
        assertEquals("I", one);
    }
    
    
    @Test 
    public void testFieldPosition() {
        RomanNumberFormat formatter = new RomanNumberFormat();
        StringBuffer pattern = new StringBuffer("one: ");
        StringBuffer one = formatter.format(1, pattern, new FieldPosition(5));
        assertEquals("one:I", one);
    } 

}