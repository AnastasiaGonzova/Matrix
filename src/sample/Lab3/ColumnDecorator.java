package sample.Lab3;

import sample.Lab1.Matrix.Matrix;
import sample.Lab2.Drawer.Drawer;

import java.util.HashMap;

public class ColumnDecorator implements Matrix{

    private Matrix matrix;
    private HashMap<Integer, Integer> ColumnMatching;

    public ColumnDecorator(Matrix m){

        matrix = m;
        ColumnMatching = new HashMap<>();
        DefaultView();
    }

    public Matrix getComponent(){
        return matrix;
    }

    public void Swap(int left, int right){
        int keyLeft = ColumnMatching.get(left);
        int keyRight = ColumnMatching.get(right);
        ColumnMatching.put(left, keyRight);
        ColumnMatching.put(right, keyLeft);
    }

    public void DefaultView(){
        for(int i = 0; i < this.getColumnSize(); i++)
            ColumnMatching.put(i,i);
        matrix.DefaultView();
    }

    @Override
    public boolean WriteElement(int row, int column, int element) {
        int newColumn = ColumnMatching.get(column);
        if(!matrix.WriteElement(row, newColumn, element)) return false;
        return true;
    }

    @Override
    public int ReadELement(int row, int column) {
        int newColumn = ColumnMatching.get(column);
        return matrix.ReadELement(row, newColumn);
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
        int newj = ColumnMatching.get(j);
        return matrix.DrawElement(i, newj);
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

}
