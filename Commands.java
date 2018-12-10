import java.io.*;
import java.util.Scanner;

public abstract class Commands implements Serializable{
    private String command;
    protected static String formattedText;
    protected static int iterator;
    public boolean isCommand(String opt){
        return opt.equals(this.command);
    }
    public abstract String[] doCommand();

    public Commands(String command){
        this.command = command;
        this.formattedText = "";
    }
    public void setText(String formattedText){
        this.formattedText = formattedText;
    }
    public void setIterator(int iterator){
        this.iterator = iterator;
    }
    public static void clearScreen(){
        try{
            Runtime.getRuntime().exec("cls");
        } catch(IOException e){
            // do nothing
        }

    }
}
class ForceQuit extends Commands {
    public ForceQuit(){
        super("q!");
    }
    public String[] doCommand(){
        System.exit(0);
        String[] returnArr = {this.formattedText, this.iterator + ""};  //included to prevent compiler error
        return returnArr;
    }
}
class Quit extends Commands {
    public Quit(){
        super("q");
    }
    public String[] doCommand(){
        System.out.print("Save file before quitting? (y/n): ");
        Scanner sc = new Scanner(System.in);
        String opt = sc.next();
        switch(opt.toCharArray()[0]){
            case 'y':
                WriteQuit wq = new WriteQuit();
                wq.doCommand();
                break;
            case 'n':
                System.exit(0);
                break;
            default:
                System.out.println("Not valid reponse, exiting command action.");
        }
        String[] returnArr = {this.formattedText, this.iterator + ""};  //included to prevent compiler error
        return returnArr;
    }
}
class Replace extends Commands {
    public Replace() {
        super("r");
    }
    public String[] doCommand(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("What word would you like to replace: ");
        String oldWord = keyboard.nextLine();
        System.out.print("What is the word to replace it with: ");
        String newWord = keyboard.nextLine();
        this.formattedText = this.formattedText.replaceFirst(oldWord, newWord);
        System.out.println("\n\nSuccessfully Replaced.\n-----------------------------------------");
        clearScreen();
        String[] returnArr = {this.formattedText, this.iterator + ""};
        return returnArr;
    }
}
class Insert extends Commands {
    public Insert(){
        super("i");
    }
    public String[] doCommand(){
        String[] returnArr = {this.formattedText, this.iterator + ""};
        return returnArr;
    }
}
class Save extends Commands {
    public Save(){
        super("w");
    }
    public String[] doCommand(){
        System.out.print("Enter file name: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            String tempText = this.formattedText;
            while(tempText.contains("\n")){
                String line = tempText.substring(0,tempText.indexOf("\n"));
                bw.write(line);
                bw.newLine();
                tempText = tempText.substring(tempText.indexOf("\n") + 1);
            }
            System.out.println("\n\nSuccessfully Saved.\n-----------------------------------------");
            bw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e +"\n-----------------------------------------");
        }
        String[] returnArr = {this.formattedText, this.iterator + ""};
        return returnArr;
    }
}
class Load extends Commands {
    public Load() {
        super("l");
    }
    public String[] doCommand(){
        try{
            System.out.print("Enter file name: ");
            Scanner sc = new Scanner(System.in);
            String fileName = sc.nextLine();
            FileInputStream fis = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            this.iterator = 1;
            this.formattedText = "";
            while ((line = br.readLine()) != null)   {
                this.formattedText += line + "\n";
                this.iterator++;
            }
            System.out.println("\n\nSuccessfully Loaded.\n-----------------------------------------");
        } catch(IOException e){
            System.out.println("Error: " + e +"\n-----------------------------------------");
        }
        String[] returnArr = {this.formattedText, this.iterator + ""};
        return returnArr;
    }
}
class WriteQuit extends Commands {
    public WriteQuit(){
        super("wq");
    }
    public String[] doCommand(){
        Save saveFile = new Save();
        saveFile.setText(this.formattedText);
        saveFile.setIterator((this.iterator));
        saveFile.doCommand();
        System.exit(0);
        String[] returnArr = {this.formattedText, this.iterator + ""};
        return returnArr;
    }
}