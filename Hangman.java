import java.util.Scanner;
import java.io.Console;

// Java Hangman Game with 2 players. One player enters word into terminal and the other player guesses.
public class Hangman {
    public static Scanner scanner = new Scanner(System.in);
    public static Console console = System.console();
    public static String word;
    public static String alphabet;
    public static char play = 'y';
    public static int lives = 7;

    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!\n");
        while (play == 'y') {
            alphabet = "abcdefghijklmnopqrstuvwxyz";
            lives = 7;

            word = getWord();
            System.out.println("Your word is " + word.length() + " letters long");

            char[] blanks = createBlanks(word);

            while (!word.equals(String.valueOf(blanks)) && lives > 0) {
                System.out.println("Letters remaining: " + alphabet);
                System.out.println(String.valueOf(blanks).replace("", " ").trim());
                
                char guess = guessLetter().charAt(0);

                if (word.indexOf(guess) >= 0) {
                    System.out.println("Your guess was correct");
                    for (int i = 0; i < blanks.length; i++) 
                        if (word.charAt(i) == guess)
                            blanks[i] = guess;
                } else {
                    System.out.println("Your guess was incorrect");
                    lives--;
                    System.out.println("You have " + lives + " lives remaining");
                }
            }
            if (word.equals(String.valueOf(blanks)))
                System.out.println("You win! The word was: " + word);
            else
                System.out.println("You ran out of lives. The word was: " + word);

            System.out.print("Play again? (y/n) ");
            play = scanner.nextLine().toLowerCase().trim().charAt(0);
            System.out.println();
        }
    }

    public static String guessLetter() {
        while (true) {
            System.out.print("Guess a letter (a-z): ");
            String guess = scanner.nextLine().toLowerCase().trim();

            if (isAlpha(guess) && guess.length() == 1 && alphabet.indexOf(guess) != -1) {
                alphabet = alphabet.replace(guess, "");
                return guess;
            } else if (guess.length() > 1 || !isAlpha(guess))
                System.out.println("Input must only contain one letter");
            else
                System.out.println("You've already guessed that letter.");
        }
    }

    public static String getWord() {
        while (true) {
            System.out.print("Enter a word: ");
            // Makes text invisible in the terminal
            String input = new String(console.readPassword()).toLowerCase().trim();
            if (isAlpha(input))
                return input;
            else
                System.out.println("Input must only contain letters");
        }
    }

    public static char[] createBlanks(String word) {
        char[] blanks = new char[word.length()];
        for (int i = 0; i < word.length(); i++) 
            blanks[i] = '_';
        
        return blanks;
    }

    public static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}


