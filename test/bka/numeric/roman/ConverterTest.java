package bka.numeric.roman;

/*
 * Copyright © Bart Kampers
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 */
public class ConverterTest {
    
    
    public ConverterTest() {
    }
    

    @Before
    public void setUp() {
        converter = new Converter();
    }
    

    @Test
    public void regularNumbers() {
        assertEquals("I", converter.standard(1));
        assertEquals("X", converter.standard(10));
        assertEquals("XXII", converter.standard(22));
        assertEquals("XXXIII", converter.standard(33));
        assertEquals("XLIV", converter.standard(44));
        assertEquals("LXXVII", converter.standard(77));
        assertEquals("LXXXVIII", converter.standard(88));
        assertEquals("C", converter.standard(100));
        assertEquals("CCII", converter.standard(202));
        assertEquals("CCCXXX", converter.standard(330));
        assertEquals("CDIV", converter.standard(404));
        assertEquals("DLV", converter.standard(555));
        assertEquals("M", converter.standard(1000));
        assertEquals("MMVIII", converter.standard(2008));
        assertEquals("MMDCCVI", converter.standard(2706));
        assertEquals("MMMDCCCLX", converter.standard(3860));
        assertEquals("MMMCMXCIX", converter.standard(3999));
    }

    
    @Test
    public void largeNumbers() {
        assertArrayEquals(reverse(new Object[] {"I"}), converter.large(1));
        assertArrayEquals(reverse(new Object[] {"II", ""}), converter.large(2000));
        assertArrayEquals(reverse(new Object[] {"III", "CCX"}), converter.large(3210));
        assertArrayEquals(reverse(new Object[] {"IV", "", "CXXIII"}), converter.large(4000123));
        assertArrayEquals(reverse(new Object[] {"V", "CMXCIX", ""}), converter.large(5999000));
        assertArrayEquals(reverse(new Object[] {"I", "CCXXXIV", "DLXVII", "DCCCXC"}), converter.large(1234567890));
    }
    
    
    @Test
    public void parseStandard() {
        assertEquals(1, converter.parseInt("I"));
        assertEquals(10, converter.parseInt("X"));
        assertEquals(22, converter.parseInt("XXII"));
        assertEquals(33, converter.parseInt("XXXIII"));
        assertEquals(44, converter.parseInt("XLIV"));
        assertEquals(77, converter.parseInt("LXXVII"));
        assertEquals(88, converter.parseInt("LXXXVIII"));
        assertEquals(100, converter.parseInt("C"));
        assertEquals(202, converter.parseInt("CCII"));
        assertEquals(330, converter.parseInt("CCCXXX"));
        assertEquals(404, converter.parseInt("CDIV"));
        assertEquals(555, converter.parseInt("DLV"));
        assertEquals(1000, converter.parseInt("M"));
        assertEquals(2008, converter.parseInt("MMVIII"));
        assertEquals(2706, converter.parseInt("MMDCCVI"));
        assertEquals(3860, converter.parseInt("MMMDCCCLX"));
        assertEquals(3999, converter.parseInt("MMMCMXCIX")); 
    }
    
    
    @Test
    public void parseNonStandard() {
        assertEquals(4, converter.parseInt("IIII"));
        assertEquals(50, converter.parseInt("XXXXX"));
        assertEquals(46, converter.parseInt("IVL"));
        assertEquals(44, converter.parseInt("VIL"));
        assertEquals(98, converter.parseInt("IIC"));
        assertEquals(198, converter.parseInt("CIIC"));
        assertEquals(0, converter.parseInt("VVX"));
        assertEquals(403, converter.parseInt("IIICD"));
    }
    
    
    @Test
    public void parseWithBrackets() {
        assertEquals(2, converter.parseLong("II"));
        assertEquals(4000, converter.parseLong("(IV)"));
        assertEquals(5001, converter.parseLong("(V)I"));
        assertEquals(1000000, converter.parseLong("(M)"));
        assertEquals(9000050, converter.parseLong("((IX))L"));
        assertEquals(123456789, converter.parseLong("((CXXIII))(CDLVI)DCCLXXXIX"));
        assertEquals(9876543210L, converter.parseLong("(((IX)))((CCMXXXCVI))(DVIIL)CCX"));
        assertEquals(111222333444L, converter.parseLong("(((CXI)CCXXII)CCCXXXIII)CDXLIV"));
    }
    
    
    @Test 
    public void parseEmpty() {
        assertEquals(0, converter.parseLong(""));        
        assertEquals(0, converter.parseLong("()"));
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void negativeNumber() {
        converter.standard(-1);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void zero() {
        converter.standard(0);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void outOfStandardRange() {
        converter.standard(4000);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void largeNnegativeNumber() {
        converter.large(Integer.MIN_VALUE);
    }

    
    @Test (expected = IllegalArgumentException.class)
    public void empty() {
        converter.parseInt("");
    }
    
    
    @Test (expected = NumberFormatException.class)
    public void testMissingClosingBrackets() {
        converter.parseLong("(CDIX");
    }
    
    
    @Test (expected = NumberFormatException.class)
    public void testMissingOpeningBrackets() {
        converter.parseLong("I)I");
    }
    
    
    private Object[] reverse(Object[] array) {
        Object[] reverted = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            reverted[array.length - i - 1] = array[i];
        }
        return reverted;
    }
    
    
    private Converter converter;
}
