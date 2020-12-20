package sample.Lab5;

public abstract class SomeCommand implements Command{

    @Override
    public void Execute() {
        if(!CommandManager.getInstance().getUndoStatus())
            CommandManager.getInstance().CommandRegistry(this);

        doExecute();
    }

    protected abstract void doExecute();
}
