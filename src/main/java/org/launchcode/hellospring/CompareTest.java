package org.launchcode.hellospring;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CompareTest {

    private static int num = 2;
    private static double pi = 3.14;
    private static GregorianCalendar today = (GregorianCalendar) GregorianCalendar.getInstance();

    @NotNull
    private int num2;

    @NotNull
    private double dec;

    @NotNull
    private int day;

    @NotNull
    @Min(1)
    @Max(12)
    private int month;

    @NotNull
    private int year;

    private GregorianCalendar date1;
    private CompareType intComp;
    private CompareType floatComp;
    private CompareType dateComp;

    public CompareTest() {
    }

    public CompareTest(int num2, double dec,
                       int day, int month, int year, CompareType intComp, CompareType floatComp,
                       CompareType dateComp) {
        this.num2 = num2;
        this.dec = dec;
        this.day = day;
        this.month = month;
        this.year = year;
        this.date1 = new GregorianCalendar(year, (month-1), day);
        this.intComp = intComp;
        this.floatComp = floatComp;
        this.dateComp = dateComp;
    }

    public static int getNum() {
        return num;
    }

    public static double getPi() {
        return pi;
    }

    public static GregorianCalendar getToday() {
        return today;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int aNum2) {
        this.num2 = aNum2;
    }

    public double getDec() {
        return dec;
    }

    public void setDec(double aDec) {
        this.dec = aDec;
    }

    public GregorianCalendar getDate1() {
        return date1;
    }

    public void setDate1(GregorianCalendar aDate) {
        this.date1 = aDate;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int aDay) {
        this.day = aDay;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int aMonth) {
        this.month = aMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int aYear) {
        this.year = aYear;
    }

    public CompareType getIntComp() {
        return intComp;
    }

    public void setIntComp(CompareType aIntComp) {
        this.intComp = aIntComp;
    }

    public CompareType getFloatComp() {
        return floatComp;
    }

    public void setFloatComp(CompareType aFloatComp) {
        this.floatComp = aFloatComp;
    }

    public CompareType getDateComp() {
        return dateComp;
    }

    public void setDateComp(CompareType aDateComp) {
        this.dateComp = aDateComp;
    }

    public static boolean compareInt(int value, CompareType type)
    {
        switch (type)
        {
            case EQUAL:
                return (getNum() == value);
            case LESS:
                return (value < getNum());
            case GREATER:
                return (value > getNum());

        }

        throw new IllegalArgumentException("Cannot get field of type " + type);
    }

    public static boolean compareDec(double value, CompareType type)
    {
        switch (type)
        {
            case EQUAL:
                return (value == getPi());
            case LESS:
                return (value < getPi());
            case GREATER:
                return (value > getPi());

        }

        throw new IllegalArgumentException("Cannot get field of type " + type);
    }

    public static boolean compareDate(GregorianCalendar value, CompareType type)
    {
        int result;

        System.out.println("\nNewDate: " + value.get(GregorianCalendar.MONTH) + "/" +
                value.get(GregorianCalendar.DAY_OF_MONTH) + "/" + value.get(GregorianCalendar.YEAR));
        System.out.println("\nToday: " + getToday().get(GregorianCalendar.MONTH) + "/" +
                getToday().get(GregorianCalendar.DAY_OF_MONTH) + "/" + getToday().get(GregorianCalendar.YEAR));

        if (value.get(GregorianCalendar.YEAR) > getToday().get(GregorianCalendar.YEAR))
        {
            result = 1;
        }
        else if (value.get(GregorianCalendar.YEAR) < getToday().get(GregorianCalendar.YEAR))
        {
            result = -1;
        }
        else
        {
            result = 0;
        }

        if(result == 0)
        {
            if (value.get(GregorianCalendar.MONTH) > getToday().get(GregorianCalendar.MONTH))
            {
                result = 1;
            }
            else if (value.get(GregorianCalendar.MONTH) < getToday().get(GregorianCalendar.MONTH))
            {
                result = -1;
            }
            else
            {
                result = 0;
            }
        }

        if(result == 0)
        {
            if (value.get(GregorianCalendar.DAY_OF_MONTH) > getToday().get(GregorianCalendar.DAY_OF_MONTH))
            {
                result = 1;
            }
            else if (value.get(GregorianCalendar.DAY_OF_MONTH) < getToday().get(GregorianCalendar.DAY_OF_MONTH))
            {
                result = -1;
            }
            else
            {
                result = 0;
            }
        }

        switch (type)
        {
            case EQUAL:
                return (result == 0);
            case LESS:
                return (result < 0);
            case GREATER:
                return (result > 0);

        }

        throw new IllegalArgumentException("Cannot get field of type " + type);
    }
}
