import java.io.*;
import java.util.Scanner;

public abstract class Commands implements Serializable{
    private String command;
    static String formattedText;
    static int iterator;
    boolean isCommand(String opt){
        return opt.equals(this.command);
    }
    public abstract String[] doCommand();
    public abstract String getDescription();
    Commands(String com){
        command = com;
        formattedText = "";
    }
    void setText(String formattedTxt){
        formattedText = formattedTxt;
    }
    void setIterator(int i){
        iterator = i;
    }
    static void clearScreen(){
        try{
            Runtime.getRuntime().exec("cls");
        } catch(IOException e){
            // do nothing
        }
    }
    public String toString(){
       return "\t:" + this.command + "\t\t" + this.getClass().getSimpleName() + "\t\t" + this.getDescription();
    }
}
class ForceQuit extends Commands {
    ForceQuit(){
        super("q!");
    }
    public String[] doCommand(){
        System.exit(0);
        String[] returnArr = {formattedText, iterator + ""};  //included to prevent compiler error
        return returnArr;
    }
    public String getDescription(){
        return "Forces Program to quit";
    }
}
class Quit extends Commands {
    Quit(){
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
        String[] returnArr = {formattedText, iterator + ""};  //included to prevent compiler error
        return returnArr;
    }
    public String getDescription(){
        return "\tAsks user to save before quitting";
    }
}
class Replace extends Commands {
    Replace() {
        super("r");
    }
    public String[] doCommand(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("What word would you like to replace: ");
        String oldWord = keyboard.nextLine();
        System.out.print("What is the word to replace it with: ");
        String newWord = keyboard.nextLine();
        formattedText = formattedText.replaceFirst(oldWord, newWord);
        System.out.println("\n\nSuccessfully Replaced.\n-----------------------------------------");
        clearScreen();
        String[] returnArr = {formattedText, iterator + ""};
        return returnArr;
    }
    public String getDescription(){
        return "\tsearches for text and replaces it";
    }
}
class Insert extends Commands {
    Insert(){
        super("i");
    }
    public String[] doCommand(){
        String[] returnArr = {formattedText, iterator + ""};
        return returnArr;
    }
    public String getDescription(){
        return "\tInserts text";
    }
}
class Save extends Commands {
    Save(){
        super("w");
    }
    public String[] doCommand(){
        System.out.print("Enter file name: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            String tempText = formattedText;
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
        String[] returnArr = {formattedText, iterator + ""};
        return returnArr;
    }
    public String getDescription(){
        return "\tSaves text input";
    }
}
class Load extends Commands {
    Load() {
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
            iterator = 1;
            formattedText = "";
            while ((line = br.readLine()) != null)   {
                formattedText += line + "\n";
                iterator++;
            }
            System.out.println("\n\nSuccessfully Loaded.\n-----------------------------------------");
        } catch(IOException e){
            System.out.println("Error: " + e +"\n-----------------------------------------");
        }
        String[] returnArr = {formattedText, iterator + ""};
        return returnArr;
    }
    public String getDescription(){
        return "\tLoads a file";
    }
}
class WriteQuit extends Commands {
    WriteQuit(){
        super("wq");
    }
    public String[] doCommand(){
        Save saveFile = new Save();
        saveFile.setText(formattedText);
        saveFile.setIterator((iterator));
        saveFile.doCommand();
        System.exit(0);
        String[] returnArr = {formattedText, iterator + ""};
        return returnArr;
    }
    public String getDescription(){
        return "Saves file and quits";
    }
}