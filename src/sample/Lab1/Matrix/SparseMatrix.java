package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;
import sample.Lab1.Vector.SparseVector;
import sample.Lab1.Vector.Vector;

public class SparseMatrix extends SomeMatrix {

    public SparseMatrix(Drawer d){
        super(d);
    }

    public Vector Create(){
        return new SparseVector();
    }

    final public String DrawElement(int i, int j){
        Integer res = ReadELement(i, j);
        if(ReadELement(i,j) != 0) return res.toString();
        else return "_";
    }
}
