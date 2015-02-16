import java.util.Scanner;
import java.util.Random;
import java.util.LinkedList;
import java.net.*;
import java.io.*;

public class Hangman {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		//String[] guesses = {"reddit", "programming", "fruit", "donut"};
		LinkedList<String> guesses = getWordList();
		
		boolean weArePlaying = true;
		while (weArePlaying) {
			System.out.println("Welcome to my game of Hangman"); //reddit -> r,e,d,d,i,t
			//char[] randomWordToGuess = guesses[random.nextInt(guesses.length)].toCharArray();
			char[] randomWordToGuess = guesses.get(random.nextInt(guesses.size())).toCharArray();
			int amountOfGuesses = randomWordToGuess.length; //100
			char[] playerGuess = new char[amountOfGuesses]; // _ _ _ _
			
			for (int i = 0; i < playerGuess.length; i++) {
				playerGuess[i] = '_';
			}
			
			boolean wordIsGuessed = false;
			int tries = 0;
			
			while (!wordIsGuessed && tries != amountOfGuesses) {
				System.out.print("Current guesses: ");
				printArray(playerGuess);
				System.out.printf("You have %d tries left.\n", amountOfGuesses - tries);
				System.out.println("Enter a single character");
				char input = scanner.nextLine().charAt(0);
				tries++;
				
				if (input == '-') {
					weArePlaying = false;
					wordIsGuessed = true;
				} else {
					for (int i = 0; i < randomWordToGuess.length; i++) {
						if (randomWordToGuess[i] == input) {
							playerGuess[i] = input;
						}
					}
					
					if (isTheWordGuessed(playerGuess)) {
						wordIsGuessed = true;
						System.out.println("Congratulations you won!");
					}
				}
				
			}
						
			if (!wordIsGuessed) System.out.println("You ran out of guesses :/");
			System.out.println("Do you want to play another game? (yes/no)");
			String anotherGame = scanner.nextLine();
			if (anotherGame.equals("no")) weArePlaying = false;
			
		}		
		System.out.println("Game over.");
		
	}
	
	public static void printArray(char[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public static boolean isTheWordGuessed(char[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '_') return false;
		}
		return true;
	}
	
	//http://www.joereynoldsaudio.com/wordlist.txt
	public static LinkedList<String> getWordList() {
		LinkedList<String> list = new LinkedList<String>();
		int counter = 0;
		int maxWords = 1000;
		try {
			URL wordlist = new URL("http://www.joereynoldsaudio.com/wordlist.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(wordlist.openStream()));
			
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				list.add(inputLine);
				counter++;
				if (counter == maxWords) break;
			}
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	
	}
	
	
}