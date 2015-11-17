/**
 * RockPaperScissors - Play a game of Rock-Paper-Scissors... User vs. Computer
 * 
 * @author Hannah Pang and Bonny Lee 
 * @version November 16, 2015
 */

import java.util.*;

public class RockPaperScissors
{
    
    // Each number is associated with an item: 0 for Rock, 1 for Paper, 2 for Scissors
    
    public static String rockPaperScissors (int choice)
    {
        if (choice == 0) {
            return "Rock";
        } else if (choice == 1) {
            return "Paper";
        } else {
            return "Scissors";
        }
    }
    
    // Computer chooses random number that corresponds to their choice of rock, paper, or scissors
    
    public static String computerChoice ()
    {
        Random r = new Random();
        int choice = r.nextInt(3);
        return rockPaperScissors (choice);
    }
    
    // Prompt user for a number (0, 1,  or 2)
    // User enters the number that corresponds to the item (rock, paper, or scissors) they want to choose
    
    public static String userChoice (Scanner console)
    {
        System.out.print("Enter 0 for Rock, 1 for Paper, 2 for Scissors: ");
        int choice = console.nextInt();
        return rockPaperScissors (choice);
    }
    
    // Determines who is the winner of the move
    // Returns "User" if user wins, "Computer" if computer wins, and "Tie" if it's a tie
    
    public static String oneMove (String computer, String user)
    {
        if (computer == user) {
            System.out.println("Computer: " + computer + " and User: " + user + ". It is a tie.");
            return "Tie";
        } else if (computer == "Rock") {
            if (user == "Paper") {
                System.out.println("Computer: Rock  User: Paper  You win!");
                return "User";
            } else if (user == "Scissors") {
                System.out.println("Computer: Rock  User: Scissors  Computer wins!");
                return "Computer";
            }
        } else if (computer == "Paper") {
            if (user == "Rock") {
                System.out.println("Computer: Paper  User: Rock  Computer wins!");
                return "Computer";
            } else if (user == "Scissors") {
                System.out.println("Computer: Paper  User: Scissors  You win!");
                return "User";
            }
        } else if (computer == "Scissors") {                                                
            if (user == "Rock") {
                System.out.println("Computer: Scissors  User: Rock  You win!");
                return "User";
            } else if (user == "Paper"){
                System.out.println("Computer: Scissors  User: Paper  Computer wins!");
                return "Computer";
            }
        }
        return "";
    } 
    
    // Keeps track of how many moves the user and computer have each won
    // Prints out how many moves the computer won, how many moves the user won, and the overall outcome of the game
    
    public static void playGame (int numberOfMoves, Scanner console) 
    {
        int computerWins = 0;
        int userWins = 0;
        for (int i = 1; i <= numberOfMoves; i++) {
            String outcome = oneMove(computerChoice(),userChoice(console));
            if (outcome == "Computer") {
                computerWins++;
            } else if (outcome == "User") {
                userWins++;
            }
        }
        
        System.out.println();
        if (computerWins > userWins) {
            System.out.println("ComputerWins: " + computerWins + "  UserWins: " + userWins + "  Computer wins game!");
        } else if (computerWins < userWins) {
            System.out.println("ComputerWins: " + computerWins + "  UserWins: " + userWins + "  User wins game!");
        } else {
            System.out.println("ComputerWins: " + computerWins + "  UserWins: " + userWins + "  It's a tie!");
        }
    }
    
    public static void main(String[] args) 
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Rock Paper Scissors!");
        System.out.println();
        System.out.print("How many games do you want to play? ");
        int numberOfGames = console.nextInt();
        System.out.print("How many moves in a game? ");
        int numberOfMoves = console.nextInt();
        System.out.println();
        for (int i = 1; i <= numberOfGames; i++) {
            System.out.println("Game #" + i);
            playGame(numberOfMoves, console);
            System.out.println();
        }
    }
}
