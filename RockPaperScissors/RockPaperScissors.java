
/**
 * Write a description of class RockPaperScissors here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RockPaperScissors
{
    public static String rockPaperScissors (int choice)
    {
        if (choice == 0) {
            return "Rock";
        }
        
        if (choice == 1) {
            return "Paper";
        }
        
        return "Scissors";
    }
    
    public static String computerChoice ()
    {
        Random random = new Random();
        int choice = random.nextInt(3);
        return rockPaperScissors (choice);
    }
    
    public static String userChoice ()
    {
        System.out.print("Enter 0 for Rock, 1 for Paper, 2 for Scissors: ");
        Scanner console = new Scanner(System.in);
        int choice = console.nextInt();
        return rockPaperScissors (choice);
    }
    
    // returns -1 if computer wins, 1 if user wins, and 0 if it's a tie
    
    public static int score (String computer, String user)
    {
        if (computer.equals(user)) {
            System.out.println("Computer: " + computer + " and User: " + user + ". It is a tie.");
            return 0;
        }
        
        if ("Rock".equals(computer)) {
            if ("Paper".equals(user)) {
                System.out.println("Computer: Rock  User: Paper  You win!");
                return 1;
            } 
            if ("Scissors".equals(user)) {
                System.out.println("Computer: Rock  User: Scissors  Computer wins!");
                return -1;
            }
        }
        
        if ("Paper".equals(computer)) {
            if ("Rock".equals(user)) {
                System.out.println("Computer: Paper  User: Rock  Computer wins!");
                return -1;
            } 
            if ("Scissors".equals(user)) {
                System.out.println("Computer: Paper  User: Scissors  You win!");
                return 1;
            }
        }
        
        if ("Scissors".equals(computer)) {
            if ("Rock".equals(user)) {
                System.out.println("Computer: Scissors  User: Rock  You win!");
                return 1;
            } 
            if ("Paper".equals(user)) {
                System.out.println("Computer: Scissors  User: Paper  Computer wins!");
                return -1;
            }
        }
        
        return 0;
    }
    
    // returns who won the game: computer, user, or tie
    
    public static String game (int numberOfMoves) 
    {
        int computerWins = 0;
        int userWins = 0;
        for (int i = 1; i <= numberOfMoves; i++) {
            int outcome = score(computerChoice(),userChoice());
            if (outcome == -1) {
                computerWins++;
            } else if (outcome == 1) {
                userWins++;
            }
        }
        
        System.out.println();
        if (computerWins > userWins) {
            System.out.println("ComputerWins: " + computerWins + "  UserWins: " + userWins + "  Computer wins game!");
            return "Computer Won";
        } else if (computerWins < userWins) {
            System.out.println("ComputerWins: " + computerWins + "  UserWins: " + userWins + "  User wins game!");
            return "User Won";
        } else {
            System.out.println("ComputerWins: " + computerWins + "  UserWins: " + userWins + "  It's a tie!");
            return "Tie";
        }
    }
    
    public static void main(String[] args) 
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Let's play Rock Paper Scissors!");
        System.out.println();
        System.out.print("How many games do you want to play? ");
        int numberOfGames = console.nextInt();
        System.out.print("How many moves in a game? ");
        int numberOfMoves = console.nextInt();
        System.out.println();
        String table = "";
        for (int i = 1; i <= numberOfGames; i++) {
            System.out.println("Game #" + i);
            String gameOutcome = game(numberOfMoves);
            table += "Game " + i + " Outcome: " + gameOutcome + "\n"; 
            System.out.println();
        }
        System.out.println(table);
    }
}
