package sample.Lab4;

import sample.Lab1.Matrix.Matrix;
import sample.Lab2.Drawer.Drawer;

import java.util.LinkedList;

public class HorizontalMatrixGroup implements Matrix {

    private LinkedList<Matrix> matrixGroup;
    private Drawer drawer;

    public HorizontalMatrixGroup(Drawer d){
        if(d == null) throw new IllegalArgumentException("Drawer can't be null");
        matrixGroup = new LinkedList<>();
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

    public LinkedList getGroup(){
        return matrixGroup;
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
    public Integer ReadElement(int row, int column) {
        if(!CheckRow(row))
            throw new IndexOutOfBoundsException("There is no row with this number");
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

    /*@Override
    final public String[] Draw() {
        if (drawer == null) throw new IllegalArgumentException("Drawer not found!");

        int rowImageSize = 2*this.getRowSize() + 1;
        int columnImageSize = 2*this.getColumnSize() + 1;

        String[] Image = new String[rowImageSize];

        StringBuilder[] temp = new StringBuilder[rowImageSize];
        for(int i = 0; i < Image.length; i++){
            temp[i] = new StringBuilder("");
        }

        int count = 0;
        int size = 0;
        for(Matrix i : matrixGroup) {
            i.setDrawer(drawer);
            String[] matrixView = i.Draw();

            for (int j = 0; j < matrixView.length; j++) {
                temp[j].append(matrixView[j]);

                if(count > 0) temp[j].deleteCharAt(size);

                if ((count < matrixGroup.size() - 1)&&(j%2 != 0)) {
                        temp[j].replace(temp[j].length() - 1, temp[j].length(), drawer.DrawVerticalBorder(temp[j].length(), columnImageSize));
                }
            }

            if(matrixView.length < rowImageSize) {
                for(int j = matrixView.length; j < rowImageSize; j++){
                    if(j%2 == 0){
                        if(j != rowImageSize - 1){
                            temp[j].append(drawer.DrawHorizontalBorder(matrixView[0].length()));
                            if(count > 0) temp[j].deleteCharAt(size);
                        }
                        else{
                            if(drawer.hasBorder()) temp[j].append(drawer.DrawHorizontalBorder(matrixView[0].length()));
                            else
                                for(int k = 0; k < matrixView[0].length(); k++) {
                                    temp[j].append(" ");
                                }
                            if(count > 0) temp[j].deleteCharAt(size);
                        }
                    }
                    else
                        for(int k = 0; k < matrixView[0].length(); k++) {
                            if((count == 0)&&(k==0)) temp[j].append(drawer.DrawVerticalBorder(k, columnImageSize));
                            if(k%2 != 0) temp[j].append("-");
                            else
                                if (k != 0)temp[j].append(drawer.DrawVerticalBorder(temp[j].length(), columnImageSize));
                        }
                }
            }
            size = temp[0].length();
            count++;
        }

        for(int i = 0; i < Image.length; i++)
            Image[i] = temp[i].toString();

        return Image;
    }*/

    @Override
    final public String ElementToString(int row, int column) {
        if(!CheckRow(row)) throw new IndexOutOfBoundsException("There is no row with this number");
        if(!CheckCol(column)) throw new IndexOutOfBoundsException("There is no column with this number");

        String res = null;
        int copyJ = column;
        for(Matrix m : matrixGroup){
           if((row < m.getRowSize())&&(copyJ < m.getColumnSize())){
                res = m.ElementToString(row, copyJ);
                break;
            }
            else {
                copyJ -= m.getColumnSize();
                if (copyJ < 0) break;
                continue;
            }
        }

        if(res == null) res = "-";

        return res;
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
    public boolean isComponent() {
        return false;
    }
}
