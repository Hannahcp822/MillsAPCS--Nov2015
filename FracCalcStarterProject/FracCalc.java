package FracCalcStarterProject;

/** 
 * Hannah Pang
 * November 30, 2015
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
        // String display = printIntegers(secondOperand);
        // return display;
        String output = parseInput(userinput);
        return output;
    }
    
    // Breaks up input into three strings: firstOperand, operator, secondOperand
    // Breaks up each operand into three integers (whole, numerator, and denominator)
    // Calls calculation method
    // Returns answer from calculation method
    
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
        String output = calculation(operator, whole1, num1, denom1, whole2, num2, denom2);
        return output;
        // return secondOperand;
    }
    
    // Checkpoint 2
    // Finds index of the underscore and index of the slash in the operand 
    // Calls wholeNumber, numerator, and denominator methods to find these components of the operand
    // Returns a string that consists of the whole number, numerator, and denominator
    
    public static String printIntegers(String operand)
    {
        int underscoreIndex = operand.indexOf("_");
        int slashIndex = operand.indexOf("/");
        String whole = wholeNumber(operand, underscoreIndex, slashIndex);
        String num = numerator(operand, underscoreIndex, slashIndex);
        String denom = denominator(operand, slashIndex);
        String display = ("whole:" + whole + " numerator:" + num + " denominator:" + denom);
        return display;
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
    
    // Makes mixed numbers improper fractions... initializes new numerators for each operand
    // Finds common denominator
    // Returns answer of appropriate calculation (+,-,*,or /)
    
    public static String calculation(String operator, int whole1, int num1, int denom1, int whole2, int num2, int denom2)
    {
        int newNum1 = whole1 * denom1 + num1;
        int newNum2 = whole2 * denom2 + num2;
        int commonDenom = denom1 * denom2;
        int numAnswer;
        int denomAnswer;
        if (operator.equals(" * ")) {
            numAnswer = newNum1 * newNum2;
            denomAnswer = denom1 * denom2;
            // String answer = numAnswer + "/" + denomAnswer;
            // return answer;
        } else if (operator.equals(" / ")) {
            numAnswer = newNum1 * denom2;
            denomAnswer = denom1 * newNum2;
            // String answer = numAnswer + "/" + denomAnswer;
            // return answer;
        } else if (operator.equals(" + ")) {
            numAnswer = (newNum1 * denom2) + (newNum2 * denom1);
            denomAnswer = commonDenom;
            // String answer = numAnswer + "/" + commonDenom;
            // return answer;
        } else {
            numAnswer = (newNum1 * denom2) - (newNum2 * denom1);
            denomAnswer = commonDenom;
            // String answer = numAnswer + "/" + commonDenom;
            // return answer;
        }
        String answer = simplify(numAnswer, denomAnswer);
        return answer;
    }
    
    public static String simplify(int numAnswer, int denomAnswer) 
    {
        int wholeAnswer;
        int newNumAnswer;
        int newDenomAnswer;
        if (numAnswer > denomAnswer) {
            wholeAnswer = numAnswer / denomAnswer;
            newNumAnswer = numAnswer % denomAnswer;
            newDenomAnswer = denomAnswer; 
        } else if (numAnswer < denomAnswer) {
            wholeAnswer = 0;
        } else if (numAnswer == denomAnswer) {
            String answer = "1";
            return answer;
        } else {
            return "0";
        }
        return "0"; 
    }
}
