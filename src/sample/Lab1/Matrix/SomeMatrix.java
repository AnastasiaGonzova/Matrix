package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;
import sample.Lab1.Vector.Vector;

public abstract class SomeMatrix implements Matrix {

    private Vector[] rows;
    private int rowSize;
    private int columnSize;
    private Drawer drawer;



    public SomeMatrix(Drawer d){
        if(d == null) throw new IllegalArgumentException("Drawer can't be null");
        rowSize = 4;
        columnSize = 5;
        rows = new Vector[rowSize];
        for(int i = 0; i < rowSize; i++){
            rows[i] = Create();
        }
        drawer = d;
    }

    public SomeMatrix(int row, int col, Drawer d){
        if(d == null) throw new IllegalArgumentException("Drawer can't be null");
        rowSize = row;
        columnSize = col;
        rows = new Vector[rowSize];
        for(int i = 0; i < rowSize; i++){
            rows[i] = Create(columnSize);
        }
        drawer = d;
    }

    protected abstract Vector Create();
    protected abstract Vector Create(int s);

    public Drawer getDrawer(){
        return drawer;
    }

    public void setDrawer(Drawer d){
        if(d == null) throw new IllegalArgumentException("Drawer can't be null");
        drawer = d;
    }

    public Vector[] getRows() {
        return rows;
    }


    public String[] Draw(){
        if (getDrawer() == null) throw new IllegalArgumentException("Drawer not found!");

        String[] Image = drawAlgorithm();

        return Image;
    }

    protected abstract String[] drawAlgorithm();

    @Override
    public boolean WriteElement(int row, int column, int element){
        return rows[row].WriteElement(column, element);
    }

    @Override
    public Integer ReadElement(int row, int column){
        return rows[row].ReadElement(column);
    }


    @Override
    public int getRowSize(){
        return rowSize;
    }


    @Override
    public int getColumnSize(){
        return columnSize;
    }

    @Override
    public String toString() {
        for(int i = 0; i < rowSize; i++){
            System.out.println(rows[i]);
        }
        return "";
    }

    @Override
    public boolean isComponent() {
        return false;
    }

}
