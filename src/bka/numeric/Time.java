/*
** Copyright © Bart Kampers
*/
package bka.numeric;


public class Time {

    public static final int HOURS_PER_DAY = 24;

    public static final int MINUTES_PER_HOUR = 60;
    public static final int MINUTES_PER_DAY  = MINUTES_PER_HOUR * HOURS_PER_DAY;

    public static final int SECONDS_PER_MINUTE = 60;
    public static final int SECONDS_PER_HOUR   = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    public static final int SECONDS_PER_DAY    = SECONDS_PER_MINUTE * MINUTES_PER_DAY;

    public static final long MILLIS_PER_SECOND = 1000;
    public static final long MILLIS_PER_MINUTE = MILLIS_PER_SECOND * SECONDS_PER_MINUTE;
    public static final long MILLIS_PER_HOUR   = MILLIS_PER_SECOND * SECONDS_PER_HOUR;
    public static final long MILLIS_PER_DAY    = MILLIS_PER_SECOND * SECONDS_PER_DAY;
    
    
    public enum Unit { 
        
        SECOND(1), 
        MINUTE(SECONDS_PER_MINUTE), 
        HOUR(SECONDS_PER_HOUR);
    
        Unit(int seconds) {
            this.seconds = seconds;
        }
    
        int seconds;
    }
    
    
    public Time(int value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    
    public Time(int seconds) {
        if (seconds % SECONDS_PER_HOUR == 0) {
            this.value = seconds / bka.numeric.Time.SECONDS_PER_HOUR;
            unit = Unit.HOUR;
        }
        else if (seconds % SECONDS_PER_MINUTE == 0) {
            this.value = seconds / SECONDS_PER_MINUTE;
            unit = Unit.MINUTE;
        }
        else {
            this.value = seconds;
            unit = Unit.SECOND;
        }
    }


    public int getValue() {
        return value;
    }


    public Unit getUnit() {
        return unit;
    }

    
    public long computeSeconds() {
        return value * unit.seconds;
    }

    
    private final int value;
    private final Unit unit;

}
