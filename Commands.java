public abstract class Commands {
    private String command;
    public boolean isCommand(String opt){
        return opt.equals(this.command);
    }
    public abstract void doCommand();

    public Commands(String command){
        this.command = command;
    }
}
class Quit extends Commands {
    public Quit(){
        super("q");
    }
    public void doCommand(){

    }
}
