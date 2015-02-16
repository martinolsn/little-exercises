import java.util.Scanner; // library used for standard input

public class UnpackingData {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String literals = ".,?!;:";
		
		int numberOfWords = Integer.parseInt(scanner.nextLine());
		
		String[] words = new String[numberOfWords]; // an array of Strings
		
		for (int i = 0; i < numberOfWords; i++) {
			words[i] = scanner.nextLine();
		}
		
		
		while (scanner.hasNextLine()) {
			String lineToDecompress = scanner.nextLine();
			String[] tokensToDecompress = lineToDecompress.split(" ");
			
			boolean isASpaceNeeded = true;
			
			for (int i = 0; i < tokensToDecompress.length; i++) {
				String token = tokensToDecompress[i]; //get ith token
				String space = " ";
				
				if (!isASpaceNeeded || i == 0) {
					isASpaceNeeded = true;
					space = "";
				}
				
				if (literals.contains(token)) {
					System.out.print(token);
				} else if (token.contains("^")) { // 37^
					//capitalize
					String wordIndex = token.substring(0, token.indexOf("^"));
					int index = Integer.parseInt(wordIndex);
					System.out.print(space + capitalize(words[index]));
				} else if (token.contains("!")) {
					//all upper case letters
					String wordIndex = token.substring(0, token.indexOf("!"));
					int index = Integer.parseInt(wordIndex);
					System.out.print(space + words[index].toUpperCase());
				} else if (token.contains("-")) {	
					//hyphen instead of a space
					System.out.print("-");
					isASpaceNeeded = false;
				} else if (token.equals("R")) {
					//new line
					System.out.println();
					isASpaceNeeded = false;
				} else if (token.equals("E")) {
					continue;
					//end of input
				} else {
					//print a regular word
					int index = Integer.parseInt(token);
					System.out.print(space + words[index]);
				}
				
			}
		
		}
		
		
		//String line = scanner.nextLine();
		//System.out.println(line);
		
	}
	
	// programming -> p -> P + rogramming
	
	private static String capitalize(String word) {
		return Character.toUpperCase(word.charAt(0)) + word.substring(1);
	}
	
}