import java.util.ArrayList;
import java.util.Scanner;

class Main {
	public static void main(String args[]) {
		new Editor();
	}
}
class Editor {
	private String formattedText = "";
	private ArrayList<Commands> commands = new ArrayList<>();
	private int iterator = 1;
	Editor(){
		System.out.println("Welcome to ViM-proved!");
		System.out.println("Group:\tJames Fennelly\n\t\tLogan Jones\n\t\tJake Fisher");
		System.out.println("All available commands are listed in :h\n--------------------------------------------------");
		commands.add(new Quit());
		commands.add(new Save());
		commands.add(new Load());
		commands.add(new Replace());
		commands.add(new Insert());
		commands.add(new WriteQuit());
		commands.add(new ForceQuit());
		this.insertMode();
	}
	public void insertMode(){
		boolean keepGoing = true;
		while(keepGoing){
			String line = getText();
			if(line.matches(":[A-Za-z!]+")){
				String option = line.substring(1);
				if(option.equals("h")){
					System.out.println("Available commands: ");
					for(int i = 0; i < commands.size();i++){
						System.out.println(commands.get(i).toString());
					}
					continue;
				}
				for(Commands command : this.commands){
					if(command.isCommand(option)){
						command.setText(this.formattedText);
						command.setIterator(this.iterator);
						String[] commandArr = command.doCommand();
						this.formattedText = commandArr[0];
						this.iterator = Integer.parseInt(commandArr[1]);
						this.printFormattedText();
					}
				}

			} else {
				this.formattedText += line + "\n";
				this.iterator++;
			}
		}
	}
    private void printFormattedText() {
	    String tempFormattedText = this.formattedText;
	    for(int i = 0;i < this.iterator - 1;i++){
	    	if(i < 100)
	        	System.out.println((i + 1) + ".\t| " + tempFormattedText.substring(0,tempFormattedText.indexOf("\n")));
	    	else
				System.out.println((i + 1) + ".| " + tempFormattedText.substring(0,tempFormattedText.indexOf("\n")));
	        tempFormattedText = tempFormattedText.substring(tempFormattedText.indexOf("\n") + 1);
        }
    }
    private String getText(){
		Scanner keyboard = new Scanner(System.in);
		if(this.iterator < 100)
			System.out.print((this.iterator)+ ".\t| ");
		else {
			System.out.print((this.iterator)+ ".| ");
		}
		return keyboard.nextLine();
	}
}
