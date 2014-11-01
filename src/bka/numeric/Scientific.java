/*
** Copyright © Bart Kampers
*/
package bka.numeric;


public class Scientific extends Number {
    
    
    public Scientific(Number number) {
        if (number != null) {
            double value = number.doubleValue();
            exponent = (short) Math.floor(Math.log10(value));
            coefficient = value / factor();
        }
        else {
            coefficient = Double.NaN;
            exponent = 0;
        }
    } 
    
    
    public double getCoefficient() {
        return coefficient;
    }
    
    
    public short getExponent() {
        return exponent;
    }
    
    
    public final double factor() {
        return Math.pow(10.0, exponent);
    }

    
    @Override
    public int intValue() {
        return new Double(doubleValue()).intValue();
    }

    
    @Override
    public long longValue() {
        return new Double(doubleValue()).longValue();
    }

    
    @Override
    public float floatValue() {
        return new Double(doubleValue()).floatValue();
    }

    
    @Override
    public double doubleValue() {
        return coefficient * factor();
    }

    
    private final double coefficient;
    private final  short exponent;

}
