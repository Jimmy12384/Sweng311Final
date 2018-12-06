import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public abstract class Commands {
    private String command;
    protected String formattedText;
    public boolean isCommand(String opt){
        return opt.equals(this.command);
    }
    public abstract String doCommand();

    public Commands(String command){
        this.command = command;
        this.formattedText = "";
    }
    public void setText(String formattedText){
        this.formattedText = formattedText;
    }
}
class Quit extends Commands {
    public Quit(){
        super("q");
    }
    public String doCommand(){
        return this.formattedText;
    }
}
class Replace extends Commands {
    public Replace() {
        super("r");
    }
    public String doCommand(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("What word would you like to replace: ");
        String oldWord = keyboard.nextLine();
        System.out.print("What is the word to replace it with: ");
        String newWord = keyboard.nextLine();
        this.formattedText = this.formattedText.replace(oldWord, newWord);
        System.out.println("--------------------------------------------------");
        System.out.println("---------------## Done Replacing ##---------------");
        System.out.println("--------------------------------------------------");
        return this.formattedText;
    }
}
class Insert extends Commands {
    public Insert(){
        super("i");
    }
    public String doCommand(){
        return this.formattedText;
    }
}
class Save extends Commands {
    public Save(){
        super("w");
    }
    public String doCommand(){
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
        return this.formattedText;
    }
}