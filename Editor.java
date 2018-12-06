import java.io.*;
import java.util.Scanner;
class Main {
	public static void main(String args[]) {
		new Editor().nextLines();
	}
}
class Editor implements Serializable{
	private String formattedText = "";
	public Editor(){
		System.out.println("Welcome to ViM-proved!");
		System.out.println("Group:\tJames Fennelly\n\t\tLogan Jones\n\t\tJake Fisher");
		System.out.println("All available commands are listed in :h\n--------------------------------------------------");
	}
	public String nextLines() {
		String inputStr;
		String option = "";
		while(!option.equals("q")) {
			Scanner keyboard = new Scanner(System.in);
			inputStr = keyboard.nextLine();
			if(inputStr.matches(":[A-Za-z!]+")) {
				option = inputStr.substring(1).toLowerCase();
				if(option.equals("delete all text")) {
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