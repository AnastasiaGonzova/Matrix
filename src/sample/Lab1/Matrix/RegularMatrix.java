package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;
import sample.Lab1.Vector.RegularVector;
import sample.Lab1.Vector.Vector;

public class RegularMatrix extends SomeMatrix {

    public RegularMatrix(Drawer d){
        super(d);
    }

    public Vector Create(){
        return new RegularVector();
    }

    final public String DrawElement(int i, int j){
        Integer res = ReadElement(i, j);
        return res.toString();
    }
}
