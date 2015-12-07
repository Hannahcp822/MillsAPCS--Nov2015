package FracCalcStarterProject;

/** 
 * Hannah Pang
 * December 7, 2015
 * Fractional Calculator 
 */

import java.util.*;

public class FracCalc {

    // Creates scanner, reads one line of input
    // Passes input to produceAnswer, prints result returned by produceAnswer
    
    public static void main(String[] args) 
    {   
        Scanner console = new Scanner(System.in);
        System.out.print("Type input: ");
        String userinput = console.nextLine();
        String output = produceAnswer(userinput);
        System.out.println(output);
    }
    
    // Calls parseInput method
    // Returns answer from parseInput method
    
    public static String produceAnswer(String userinput)
    {   
        return parseInput(userinput);
    }
    
    // Breaks up input into three strings: firstOperand, operator, secondOperand
    // Breaks up each operand into three integers (whole, numerator, and denominator)
    // Calls calculation method and returns answer from calculation method
    
    public static String parseInput(String userinput)
    {
        int firstSpaceIndex = userinput.indexOf(" ");
        int operatorIndex = firstSpaceIndex + 1;
        String firstOperand = userinput.substring(0, operatorIndex - 1);
        String operator = userinput.substring(operatorIndex - 1, operatorIndex + 2);
        String secondOperand = userinput.substring(operatorIndex + 2, userinput.length());
        int underscore1 = firstOperand.indexOf("_");
        int slash1 = firstOperand.indexOf("/");
        int length1 = firstOperand.length();
        int underscore2 = secondOperand.indexOf("_");
        int slash2 = secondOperand.indexOf("/");
        int length2 = secondOperand.length();
        int whole1 = Integer.parseInt(wholeNumber(firstOperand, underscore1, slash1));
        int num1 = Integer.parseInt(numerator(firstOperand, underscore1, slash1));
        int denom1 = Integer.parseInt(denominator(firstOperand, slash1));
        int whole2 = Integer.parseInt(wholeNumber(secondOperand, underscore2, slash2));
        int num2 = Integer.parseInt(numerator(secondOperand, underscore2, slash2)); 
        int denom2 = Integer.parseInt(denominator(secondOperand, slash2));
        return calculation(operator, whole1, num1, denom1, whole2, num2, denom2);
    }
    
    // Determines and returns the whole number portion of the operand
    // Return 0 if there is no whole number portion of the operand
    
    public static String wholeNumber(String operand, int underscoreIndex, int slashIndex)
    {
        if (underscoreIndex == -1) {
            if (slashIndex == -1) {
                return operand;
            } else {
                return "0";
            }
        } else {
            return operand.substring(0, underscoreIndex);
        }
    }
    
    // Determines and returns the numerator portion of the operand
    // Returns 0 if there is no numerator portion of the operand (no underscore and no slash)
    
    public static String numerator(String operand, int underscoreIndex, int slashIndex)
    {
        if (underscoreIndex == -1) {
            if (slashIndex == -1) {
                return "0";
            } else { 
                return operand.substring(0, slashIndex);
            }
        } else {
            return operand.substring(underscoreIndex + 1, slashIndex);
        }
    }
    
    // Determines and returns the denominator portion of the operand
    // Returns 1 if there is no denominator portion of the operand (no slash) 
    
    public static String denominator(String operand, int slashIndex)
    {
        if (slashIndex == -1) {
            return "1";
        } else {
            return operand.substring(slashIndex + 1, operand.length());
        }
    }
    
    // Returns error message if there is a 0 in the denominator
    // Makes mixed numbers improper fractions... initializes new numerators for each operand
    // Finds common denominator
    // Calculates appropriate calculation (+,-,*,or /)
    // Calls simplify method to simply fractional answers
    
    public static String calculation(String operator, int whole1, int num1, int denom1, int whole2, int num2, int denom2)
    {
        if (denom1 == 0 || denom2 == 0) {
            return "Error";
        }
        
        int newNum1;
        int newNum2;
        if (whole1 >= 0) {
            newNum1 = whole1 * denom1 + num1;
        } else {
            newNum1 = (Math.abs(whole1) * denom1 + num1) * -1;
        }
        if (whole2 >= 0) {
            newNum2 = whole2 * denom2 + num2;
        } else {
            newNum2 = (Math.abs(whole2) * denom2 + num2) * -1;
        }
        
        int commonDenom = denom1 * denom2;
        int numAnswer;
        int denomAnswer;
        if (operator.equals(" * ")) {
            numAnswer = newNum1 * newNum2;
            denomAnswer = denom1 * denom2;
        } else if (operator.equals(" / ")) {
            numAnswer = newNum1 * denom2;
            denomAnswer = denom1 * newNum2;
        } else if (operator.equals(" + ")) {
            numAnswer = (newNum1 * denom2) + (newNum2 * denom1);
            denomAnswer = commonDenom;
        } else {
            numAnswer = (newNum1 * denom2) - (newNum2 * denom1);
            denomAnswer = commonDenom;
        }

        if (numAnswer == 0) {
            return "0";
        } else {
            return simplify(numAnswer, denomAnswer);
        }
    }
    
    // Simplifies fractional answer:
        // Returns 1 is numerator and denominator are the same
        // Returns numerator is denominator is 1
        // Returns opposite sign of numerator if denominator is -1
        // Calls mixedNumber method if numerator is greater than denominator
        // Calls reduce method if numerator is less than denominator
    // Returns simplified answer
    
    public static String simplify(int num, int denom) 
    {
        if (num == denom) {
            return "1";
        } else if (denom == 1) {
            return String.valueOf(num);
        } else if (denom == -1) {
            return String.valueOf(num * -1);
        } else if (Math.abs(num) > Math.abs(denom)) {
            return mixedNumber(num, denom);
        } else {
            return reduce(num, denom);
        }
    }
    
    // Finds whole number value of the mixed number
    // Finds new numerator for the fractional part of the mixed number
    // Calls reduce method to simplify the fractional part of the mixed number
    // Returns mixedNumber with simplified fractional part
    
    public static String mixedNumber(int num, int denom)
    {
        int whole = num / denom;
        int newNum;
        if (whole < 0) {
            newNum = (num % denom) * -1;
        } else {
            newNum = num % denom;
        }
        
        if (newNum == 0) {
            return String.valueOf(whole);
        } else {
            String fraction = reduce(newNum, denom);
            return whole + "_" + fraction;
        }
    }
    
    // Calls gcf method to find greatest common factor
    // Reduces fraction by diving both numerator and denominator by the gcf
    // Returns reduced fraction
    
    public static String reduce(int num, int denom)
    {
        int gcf = gcf(num, denom);

        if (num > 0 && denom < 0) {
            num = (num / gcf) * -1;
            denom = (denom / gcf) * -1;
        } else {
            num = num / gcf;
            denom = denom / gcf;
        }
        
        return num + "/" + denom;
    }
    
    // Finds greatest common factor of numerator and denominator
    // Assumes factor is equal to whichever (absolute value) is smaller: numerator or denominator
    // Divide both numerator and denominator by factor to find remainder
    // factor decrements by 1 until factor can divide evenly into both numerator and denominator
    // returns factor as the gcf
    
    public static int gcf(int num, int denom)
    {
        int factor = Math.min(Math.abs(num), Math.abs(denom));
        while (factor > 0) {
            int a = Math.abs(num) % factor;
            int b = Math.abs(denom) % factor;
            if (a == 0 && b == 0) {
                if (num < 0 && denom < 0) {
                    factor = factor * -1;
                } 
                return factor;
            }
            factor--;
        }
        return 1;
    }
}