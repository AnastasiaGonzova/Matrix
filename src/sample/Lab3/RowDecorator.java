package sample.Lab3;

import sample.Lab1.Matrix.Matrix;
import sample.Lab2.Drawer.Drawer;

import java.util.HashMap;

public class RowDecorator implements Matrix{

    private Matrix matrix;
    private HashMap<Integer, Integer> RowMatching;

    public RowDecorator(Matrix m){

        matrix = m;
        RowMatching = new HashMap<>();
        DefaultView();
    }

    public Matrix getComponent(){
        return matrix;
    }

    public void Swap(int up, int down){
        int keyUp = RowMatching.get(up);
        int keyDown = RowMatching.get(down);
        RowMatching.put(up, keyDown);
        RowMatching.put(down, keyUp);
    }

    public void DefaultView(){
        for(int i = 0; i < this.getRowSize(); i++)
            RowMatching.put(i,i);
        if(matrix.isComponent())matrix.DefaultView();
    }

    @Override
    public boolean WriteElement(int row, int column, int element) {
        int newRow = RowMatching.get(row);
        return matrix.WriteElement(newRow, column, element);
    }

    @Override
    public int ReadElement(int row, int column) {
        int newRow = RowMatching.get(row);
        return matrix.ReadElement(newRow, column);
    }

    @Override
    public int getRowSize() {
        return matrix.getRowSize();
    }

    @Override
    public int getColumnSize() {
        return matrix.getColumnSize();
    }

    @Override
    public String DrawElement(int i, int j){
        int newi = RowMatching.get(i);
        return matrix.DrawElement(newi, j);
    }

    public Drawer getDrawer(){
        return matrix.getDrawer();
    }

    @Override
    public void setDrawer(Drawer d) {
        matrix.setDrawer(d);
    }

    @Override
    public boolean hasBorder() {
        return matrix.hasBorder();
    }

    @Override
    public void setBorder(boolean check) {
        matrix.setBorder(check);
    }

    @Override
    public boolean isComponent() {
        return true;
    }

    @Override
    public String toString() {
        for(int i = 0; i < getRowSize(); i++){
            for(int j = 0; j < getColumnSize(); j++)
                System.out.print(ReadElement(i, j) + " ");
            System.out.print("\n");
        }
        return "";
    }
}
