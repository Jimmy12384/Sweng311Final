import java.io.*;
import java.util.Scanner;
class Main2 {
	public static void main(String args[]) {
		new Editor().nextLines();
	}
}
class Editor implements Serializable{
	private String formattedText = "";
	private boolean morseCode;
	public Editor(){
		this.morseCode = false;
	}
	public Editor(boolean morseCode){
		this.morseCode = morseCode;
	}
	public String nextLines() {
		String inputStr = "";
		String option = "";
		while(!option.equals("quit")) {
			Scanner keyboard = new Scanner(System.in);
			inputStr = keyboard.nextLine();
			if(inputStr.matches("##[A-Za-z ]+##")) {
				option = inputStr.substring(2,inputStr.indexOf("##",3)).toLowerCase();
				if(option.equals("quit")) {
					if(this.morseCode) {
						keyboard.close();
						return this.formattedText;
					}
					System.exit(0);
				} else if(option.equals("replace")) {
					System.out.print("What word would you like to replace: ");
					String oldWord = keyboard.nextLine();
					System.out.print("What is the word to replace it with: ");
					String newWord = keyboard.nextLine();
					this.formattedText = this.formattedText.replace(oldWord, newWord);
					System.out.println("--------------------------------------------------");
					System.out.println("---------------## Done Replacing ##---------------");
					System.out.println("--------------------------------------------------");
					System.out.print(this.formattedText);
				} else if(option.equals("delete all text")) {
					this.formattedText = "";
					try {
						Runtime.getRuntime().exec("cls");
					} catch (IOException e) {
						System.out.println(new String(new char[50]).replace("\0", "\r\n"));
					}
				} else if(option.equals("save to a file")) {
					System.out.print("Enter file name: ");
					Scanner sc = new Scanner(System.in);
					String fileName = sc.nextLine();
		            
		            try {
		            	FileOutputStream fout = new FileOutputStream(fileName);
		            	ObjectOutputStream oos = new ObjectOutputStream(fout);
		    			oos.writeObject(this);
						System.out.println("--------------------------------------------------");
						System.out.println("----------------## Save Complete ##---------------");
						System.out.println("--------------------------------------------------");
						oos.close();
					} catch (IOException e) {
						System.out.println("--------------------------------------------------");
						System.out.println("-----------------## Save Failed ##----------------");
						System.out.println("--------------------------------------------------");
					}
				}
			} else {
				this.formattedText += inputStr + "\n";
			}
		}
		return this.formattedText;
	}
	
}