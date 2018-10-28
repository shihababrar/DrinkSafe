package com.example.shihab.drinksafe.Class;

public class IronTest {
    public static final int redHigh0 = 149;
    public static final int greenHigh0 = 115;
    public static final int blueHigh0 = 45;
    public static final int redLow0 = 129;
    public static final int greenLow0 = 91;
    public static final int blueLow0 = 23;

    public static final int redHigh50 = 213;
    public static final int greenHigh50 = 135;
    public static final int blueHigh50 = 43;
    public static final int redLow50 = 183;
    public static final int greenLow50 = 93;
    public static final int blueLow50 = 9;

    public static final int redHigh125 = 143;
    public static final int greenHigh125 = 83;
    public static final int blueHigh125 = 23;
    public static final int redLow125 = 121;
    public static final int greenLow125 = 63;
    public static final int blueLow125 = 9;

    public static final int redHigh250 = 199;
    public static final int greenHigh250 = 147;
    public static final int blueHigh250 = 97;
    public static final int redLow250 = 115;
    public static final int greenLow250 = 97;
    public static final int blueLow250 = 59;

    public static final int redHigh500 = 155;
    public static final int greenHigh500 = 97;
    public static final int blueHigh500 = 69;
    public static final int redLow500 = 93;
    public static final int greenLow500 = 33;
    public static final int blueLow500 = 17;


    public IronTest(int r,int g, int b)
    {
///////////////////0 ppm soft////////////
        if( (r>=redLow0 && r<=redHigh0) && (g>=greenLow0 && g<=greenHigh0) && (b>=blueLow0 && b<=blueHigh0))
        {
            System.out.println("0 ppm / Soft level iron detected! ");
        }


///////////////////50 ppm soft////////////
        else if( (r>=redLow50 && r<=redHigh50) && (g>=greenLow50 && g<=greenHigh50) && (b>=blueLow50 && b<=blueHigh50) )
        {

            System.out.println("50 ppm/ Soft Level iron detected!!");
        }



///////////////////125 ppm soft////////////
        else if( (r>=redLow125 && r<=redHigh125)&& (g>=greenLow125 && g<=greenHigh125) && (b>=blueLow125 && b<=blueHigh125))
        {
            System.out.println("125 ppm / mid-hard level iron ");
        }


///////////////////250 ppm soft////////////
        else if( (r>=redLow250 && r<=redHigh250) && (g>=greenLow250 && g<=greenHigh250) && (b>=blueLow250 && b<=blueHigh250))
        {
            System.out.println("250 ppm / hard level iron ");
        }

///////////////////500 ppm soft////////////
        else if( (r>=redLow500 && r<=redHigh500) && (g>=greenLow500 && g<=greenHigh500) && (b>=blueLow500 && b<=blueHigh500))
        {
            System.out.println("500 ppm / Very hard level iron ");
        }
        else {
            System.out.println("No Data Found!!");

        }
    }

}
