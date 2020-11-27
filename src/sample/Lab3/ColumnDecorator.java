package sample.Lab3;

import sample.Lab1.Matrix.Matrix;
import sample.Lab1.Vector.Vector;
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
        if(matrix.isComponent()) matrix.DefaultView();
    }

    @Override
    public boolean WriteElement(int row, int column, int element) {
        int newColumn = ColumnMatching.get(column);
        return matrix.WriteElement(row, newColumn, element);
    }

    @Override
    public int ReadElement(int row, int column) {
        int newColumn = ColumnMatching.get(column);
        return matrix.ReadElement(row, newColumn);
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
    public String[] Draw(Transfer my) {
        if(my == null)  return matrix.Draw(getTransferEntity());
        else return matrix.Draw(my);
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
