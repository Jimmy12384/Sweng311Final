import java.util.Scanner;

public abstract class Commands {
    private String command;
    private String formattedText;
    public boolean isCommand(String opt){
        return opt.equals(this.command);
    }
    public abstract void doCommand();

    public Commands(String command){
        this.command = command;
        this.formattedText = "";
    }
    public Commands(String command, String formattedText){
        this.command = command;
        this.formattedText = formattedText;
    }
}
class Quit extends Commands {
    public Quit(){
        super("q");
    }
    public void doCommand(){
        System.exit(0);
    }
}
class Replace extends Commands {
    private String formattedText;
    public Replace(String formattedText) {
        super("r");
        this.formattedText = formattedText;
    }
    public void doCommand(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("What word would you like to replace: ");
        String oldWord = keyboard.nextLine();
        System.out.print("What is the word to replace it with: ");
        String newWord = keyboard.nextLine();
        this.formattedText = this.formattedText.replace(oldWord, newWord);
        System.out.println("--------------------------------------------------");
        System.out.println("---------------## Done Replacing ##---------------");
        System.out.println("--------------------------------------------------");
        System.out.print(this.formattedText);
    }
}