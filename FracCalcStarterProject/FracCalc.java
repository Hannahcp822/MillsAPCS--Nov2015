/**
Hannah Pang
November 20, 2015
FracCalc Project
*/

import java.util.*;

public class FracCalc {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner console = new Scanner(System.in);
        System.out.print("Type in a line of input: ");
        String userinput = console.nextLine();
        produceAnswer(userinput);
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String userinput)
    { 
        // TODO: Implement this function to produce the solution to the input
        String secondOperand = separateString(userinput);
        
        /**
        return secondOperand;
        */
       
       String display = printIntegers(secondOperand);
       return display;
    }
    
    // TODO: Fill in the space below with any helper methods that you think you will need
    /**
    public static String separateString(String userinput)
    {
        int operatorIndex = indexOfOperator(userinput);
        String firstOperand = userinput.substring(0, operatorIndex - 2); 
        String operator = userinput.substring(operatorIndex, operatorIndex);
        String secondOperand = userinput.substring(operatorIndex + 2, userinput.length());
        return secondOperand;
    }
    
    public static int indexOfOperator(String userinput)
    {
        int firstSpaceIndex = userinput.indexOf(" ");
        int operatorIndex = firstSpaceIndex + 1;
        return operatorIndex;
    }
    */
   
    public static String printIntegers(String secondOperand)
    {
        int underscoreIndex = secondOperand.indexOf("_");
        if (underscoreIndex == -1) {
            int whole = 0;
        } else {
            int whole = parseSecondOperand(secondOperand,0,underscoreIndex);
        }
        int backslashIndex;
        int numerator = parseSecondOperand(secondOperand);
        int denominator = parseSecondOperand(secondOperand,
        String display = "whole:" + whole + "numerator:" + numerator + "denominator:" + denominator;
        return display;
    }
    
    public static String parseSecondOperand(String secondOperand,int indexOne,int indexTwo)
    {
        String integer = secondOperand.substring(indexOne,indexTwo);
        return integer;
}
