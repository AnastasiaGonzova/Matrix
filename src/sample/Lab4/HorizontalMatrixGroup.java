package sample.Lab4;

import sample.Lab1.Matrix.Matrix;
import sample.Lab2.Drawer.Drawer;
import sample.Lab3.Transfer;

import java.util.LinkedList;

public class HorizontalMatrixGroup implements Matrix {

    private LinkedList<Matrix> matrixGroup;
    private boolean border;
    private Drawer drawer;

    public HorizontalMatrixGroup(Drawer d){
        if(d == null) throw new IllegalArgumentException("Drawer can't be null");
        matrixGroup = new LinkedList<>();
        border = true;
        drawer = d;
    }

    public void AddMatrix(Matrix m){
        matrixGroup.add(m);
    }

    private boolean CheckRow(int row){
        return (row>=0)&&(row<this.getRowSize());
    }

    private boolean CheckCol(int column){
        return (column>=0)&&(column<this.getColumnSize());
    }


    @Override
    public boolean WriteElement(int row, int column, int element) {
        if(!CheckRow(row)) throw new IndexOutOfBoundsException("There is no row with this number");
        if(!CheckCol(column)) throw new IndexOutOfBoundsException("There is no column with this number");

        boolean res = false;
        for(Matrix m : matrixGroup){
            if((row < m.getRowSize())&&(column < m.getColumnSize())){
                res = m.WriteElement(row, column, element);
                break;
            }
            else {
                column -= m.getColumnSize();
                if (column < 0) break;
                continue;
            }
        }

        return res;
    }

    @Override
    public int ReadElement(int row, int column) {
        if(!CheckRow(row)) throw new IndexOutOfBoundsException("There is no row with this number");
        if(!CheckCol(column)) throw new IndexOutOfBoundsException("There is no column with this number");

        Integer res = null;
        for(Matrix m : matrixGroup){
            if((row < m.getRowSize())&&(column < m.getColumnSize())){
                res = m.ReadElement(row, column);
                break;
            }
            else {
                column -= m.getColumnSize();
                if (column < 0) break;
                continue;
            }
        }

        if(res == null) throw new IndexOutOfBoundsException("There is no this cell");

        return res;
    }

    @Override
    public int getRowSize() {
        int rowSize = 0;
        for(Matrix i : matrixGroup){
            if(i.getRowSize() > rowSize) rowSize = i.getRowSize();
        }
        return rowSize;
    }

    @Override
    public int getColumnSize() {
        int columnSize = 0;
        for(Matrix i : matrixGroup){
            columnSize += i.getColumnSize();
        }
        return columnSize;
    }

    @Override
    public String[] Draw(Transfer my) {
        if (drawer == null) throw new IllegalArgumentException("Drawer not found!");

        String[] Image = new String[this.getRowSize()];
        for(int i = 0; i < Image.length; i++){
            Image[i] = new String("");
        }

        StringBuilder[] temp = new StringBuilder[this.getRowSize()];
        for(int i = 0; i < Image.length; i++){
            temp[i] = new StringBuilder("");
        }

        int count = 0;
        int size = 0;
        for(Matrix i : matrixGroup) {
            i.setBorder(border);
            i.setDrawer(drawer);
            String[] matrixView = i.Draw(my);

            for (int j = 0; j < matrixView.length; j++) {
                temp[j].append(matrixView[j]);

                if (count > 0) temp[j].deleteCharAt(size);

                if ((count < matrixGroup.size() - 1)&&(j%2 != 0)) {
                    if (border) {
                        temp[j].replace(temp[j].length() - 1, temp[j].length(), drawer.DrawVerticalBorder(temp[j].length() - 1, this.getColumnSize()));
                    } else {
                        temp[j].append(drawer.DrawVerticalBorder(temp[j].length() - 1, this.getColumnSize()));
                    }
                }
            }
            size += temp[0].length();
            count++;
        }

        for(int i = 0; i < Image.length; i++)
            Image[i] = temp[i].toString();

        return Image;
    }

    @Override
    public Drawer getDrawer() {
        return drawer;
    }

    @Override
    public void setDrawer(Drawer d) {
        if(d == null) throw new IllegalArgumentException("Drawer can't be null");
        drawer = d;
    }

    @Override
    public boolean hasBorder() {
        return border;
    }

    @Override
    public void setBorder(boolean check) {
        border = check;
    }

    @Override
    public void Swap(int i, int j) {
        throw new UnsupportedOperationException("It's not a decorator");
    }

    @Override
    public void DefaultView() {
        throw new UnsupportedOperationException("It's not a decorator");
    }

    @Override
    public boolean isComponent() {
        return false;
    }
}
