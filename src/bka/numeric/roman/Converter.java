/*
** Copyright © Bart Kampers
*/

package bka.numeric.roman;

import java.util.ArrayList;

/**
 * Converts between integers and Roman number strings
 */
public class Converter {

    /**
     * Converts a number in the range [1, 3999] to a standardized roman number.
     * @param number integer to convert
     * @return roman number
     * @throws IllegalArgumentException if digital out of range
     */
    public String standard(int number) {
        if ((1 <= number) && (number <= 3999)) {
            StringBuilder roman = new StringBuilder();
            int exponent = 0;
            while (number > 0) {
                int digit = number % 10;
                roman = convertDigit(digit, exponent).append(roman);
                exponent++;
                number /= 10;
            }
            return roman.toString();
        }
        else {
            throw new java.lang.IllegalArgumentException(Integer.toString(number) + " cannot be converted to regular Roman");
        }
    }
    
    /**
     * Convert any positive number to a roman number divided in particals of standard roman numbers
     * representing the thousands groups.
     * @param  number to convert
     * @return array of particals with least significant at index 0
     * @throws IlleagalArgumentException if number is 0 or negative
     */
    String[] large(int number) {
        if (number >= 1) {
            java.util.List<String> particals = new java.util.ArrayList<>();
            while (number > 0) {
                int partical = number % 1000;
                if (partical > 0) {
                    particals.add(standard(partical));
                }
                else {
                    particals.add(new String());
                }
                number /= 1000;
            }
            String[] roman = new String[particals.size()];
            return particals.toArray(roman);
        }
        else {
            throw new java.lang.IllegalArgumentException(Integer.toString(number) + " cannot be converted to Roman");
        }    
    }
    
    /**
     * @param roman number to convert
     * @return integer value of roman
     * @throws NumberFormatException is roman is not a valid Roman number
     */
    public int parseInt(String roman) {
        if (! roman.isEmpty()) {
            int value = 0;
            ArrayList<Integer> values = createValueList(roman);
            for (int v : values) {
                value += v;
            }
            return value;
        }
        else {
            throw new NumberFormatException("Roman number must not be empty");
        }
    }

    /**
     */
    private ArrayList<Integer> createValueList(String roman) {
        java.util.ArrayList<Integer> values = new java.util.ArrayList<>();
        int previousValue = 0;
        int previousExponent = 0;
        for (char ch : roman.toCharArray()) {
            int index = values.size() - 1;
            int symbolValue = symbolValue(ch);
            int symbolExponent = symbolExponent(ch);
            if (values.isEmpty() || symbolExponent < previousExponent) {
                values.add(symbolValue);
            }
            else if (previousValue < symbolValue) {
                values.set(index, symbolValue - values.get(index));
            }
            else {
                values.set(index, values.get(index) + symbolValue);
            }
            previousValue = symbolValue;
            previousExponent = symbolExponent;
        }
        return values;
    }
     
    /**
     */
    private int symbolValue(char ch) {
        int factor = 1;
        for (char[] symbols : SYMBOLS) {
            for (int valueIndex = 0; valueIndex < symbols.length; ++valueIndex) {
                if (symbols[valueIndex] == ch) {
                    return ((valueIndex == 0) ? 1 : 5) * factor;
                }
            }
            factor *= 10;
        }
        throw new NumberFormatException("Invalid character '" + ch + "'");
    }
    
    /**
     */
    private int symbolExponent(char ch) {
        for (int exponent = 0; exponent < SYMBOLS.length; ++exponent) {
            for (char symbol : SYMBOLS[exponent]) {
                if (symbol == ch) {
                    return exponent;
                }
            }
        }
        throw new NumberFormatException("Invalid character '" + ch + "'");
    }
    
    /**
     * Convert one digit to a roman number accounting given exponent
     * @param digit [0, 9] to convert
     * @param exponent of the digit 0, 1 or 2 
     * @return roman number representing the digit
     */
    private StringBuilder convertDigit(int digit, int exponent) {
        StringBuilder roman = new StringBuilder();
        if (digit >= 4) {
            if (digit == 4 || digit == 9) {
                // start with 1-symbol
                roman.append(SYMBOLS[exponent][INDEX_1]);
            }
            if (digit != 9) {
                // append 5-symbol 
                roman.append(SYMBOLS[exponent][INDEX_5]);
                digit -= 5;
            }
            else {
                // append 10-symbol, that is the 1-symbol of the next exponent
                roman.append(SYMBOLS[exponent+1][INDEX_1]);
                digit = 0;
            }
        }
        while (digit > 0) {
            // append up to three 1-symbols
            roman.append(SYMBOLS[exponent][INDEX_1]);
            digit--;
        }
        return roman;
    }
    
    
    private static final char[][] SYMBOLS = {{'I', 'V'}, {'X', 'L'}, {'C', 'D'} , {'M'}};
    
    private static final int INDEX_1 = 0;
    private static final int INDEX_5 = 1;
    
}
