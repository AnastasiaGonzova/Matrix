package sample.Lab5;

import java.util.LinkedList;

public class CommandManager {

    private static CommandManager instance = null;
    private LinkedList<Command> commandList;
    private boolean isUndo = false;

    private CommandManager(){
        commandList =  new LinkedList<>();
    }

    public static CommandManager getInstance() {
        if(instance == null) instance = new CommandManager();
        return instance;
    }

    public void CommandRegistry(Command command){
        commandList.add(command);
    }

    public boolean getUndoStatus(){
        return isUndo;
    }

    public void Undo(){
        if(commandList.size() != 0){
            isUndo = true;
            commandList.removeLast();
            for(Command c : commandList)
                c.Execute();
            isUndo = false;
        }
        else return;
    }
}
