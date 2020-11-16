package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;
import sample.Lab1.Vector.Vector;

public abstract class SomeMatrix implements Matrix {

    private Vector[] rows;
    private int rowSize;
    private int columnSize;
    private Drawer drawer = null;
    private boolean border = true;


    public SomeMatrix(Drawer d){
        rowSize = 4;
        columnSize = 5;
        rows = new Vector[rowSize];
        for(int i = 0; i < rowSize; i++){
            rows[i] = Create();
        }
        drawer = d;
    }

    protected abstract Vector Create();

    public Drawer getDrawer(){
        return drawer;
    }

    public void setDrawer(Drawer d){
        drawer = d;
    }

    public boolean hasBorder(){
        return border;
    }

    public void setBorder(boolean check){
        border = check;
    }

    /*public String[] Draw(){
        if (drawer == null) throw new IllegalArgumentException("Drawer not found!");

        int rowImageSize = 2*getRowSize() + 1;
        int columnImageSize = 2*getColumnSize() + 1;
        String[] Image = new String[rowImageSize];
        for(int i = 0; i < rowImageSize; i++)
            Image[i] = "";

        for(int i = 0; i < rowImageSize; i++){
             if (i % 2 == 0){
                if((border)||((i!=0)&&(i!= rowImageSize-1))) Image[i] = drawer.DrawHorizontalBorder(columnImageSize);
            }
            else {
                StringBuilder temp = new StringBuilder("");
                for(int j = 0; j < getColumnSize(); j++){
                    if (j == 0){
                        if(border) temp.append(drawer.DrawVerticalBorder(j, getColumnSize()));
                        else temp.append(" ");
                    }
                    temp.append(DrawElement(i/2, j));
                    if(j < getColumnSize() - 1) temp.append(drawer.DrawVerticalBorder(j+1, getColumnSize())); //меняем ориентировку на право
                    else if (j == getColumnSize() - 1) {
                        if (border) temp.append(drawer.DrawVerticalBorder(j+1, getColumnSize()));
                        else temp.append(" ");
                    }
                }
                Image[i] = temp.toString();
            }
        }

        return Image;
    }*/

    @Override
    public boolean WriteElement(int row, int column, int element){
        if(rows[row].WriteElement(column, element)) return true;
        else return false;
    }

    @Override
    public int ReadELement(int row, int column){
        return rows[row].ReadELement(column);
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

    public void Swap(int i, int j){
        return;
    }

    public void DefaultView(){
        return;
    }
}