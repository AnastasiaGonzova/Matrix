package sample.Lab5;

import sample.Controller;
import sample.Lab1.Matrix.Matrix;

public abstract class AppInitiatorCommand extends  SomeCommand{
    private Controller controller;
    private Matrix condition;

    public AppInitiatorCommand(Controller c, Matrix cond){
        controller = c;
        condition = cond;
    }
}
