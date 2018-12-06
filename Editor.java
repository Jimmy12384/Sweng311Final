import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Main {
	public static void main(String args[]) {
		new Editor();
	}
}
class Editor implements Serializable{
	private String formattedText = "";
	ArrayList<Commands> commands = new ArrayList<>();
	public Editor(){
		boolean keepGoing = true;
		System.out.println("Welcome to ViM-proved!");
		System.out.println("Group:\tJames Fennelly\n\t\tLogan Jones\n\t\tJake Fisher");
		System.out.println("All available commands are listed in :h\n--------------------------------------------------");
		commands.add(new Quit());
		commands.add(new Save());
		commands.add(new Replace());
		commands.add(new Insert());
		while(keepGoing){
			String line = this.getText();
			if(line.matches(":[A-Za-z!]+")){
				String option = line.substring(1);
				for(Commands command : commands){
					if(command.isCommand(option)){
						command.setText(this.formattedText);
						this.formattedText = command.doCommand();
						System.out.print(this.formattedText);
					}
				}
			} else {
				this.formattedText += line + "\n";
			}
		}

	}
	public String getText(){
		Scanner keyboard = new Scanner(System.in);
		return keyboard.nextLine();
	}
}