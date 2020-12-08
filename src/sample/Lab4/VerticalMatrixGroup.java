package sample.Lab4;

import sample.Lab1.Matrix.Matrix;
import sample.Lab2.Drawer.Drawer;
import sample.Lab3.Transfer;

import java.util.LinkedList;

public class VerticalMatrixGroup implements Matrix {

    private HorizontalMatrixGroup matrixGroup;
    private Drawer drawer;

    public VerticalMatrixGroup(Drawer d){
        matrixGroup = new HorizontalMatrixGroup(d);
        drawer = d;
    }

    public void AddMatrix(Matrix m){
        matrixGroup.AddMatrix(m);
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
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for(Matrix m : innerList){
            if((row < m.getRowSize())&&(column < m.getColumnSize())){
                res = m.WriteElement(row, column, element);
                break;
            }
            else {
                row -= m.getRowSize();
                if (row < 0) break;
                continue;
            }
        }

        return res;
    }

    @Override
    public Integer ReadElement(int row, int column) {
        if(!CheckRow(row)) throw new IndexOutOfBoundsException("There is no row with this number");
        if(!CheckCol(column)) throw new IndexOutOfBoundsException("There is no column with this number");

        Integer res = null;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for(Matrix m : innerList){
            if((row < m.getRowSize())&&(column < m.getColumnSize())){
                res = m.ReadElement(row, column);
                break;
            }
            else {
                row -= m.getRowSize();
                if (row < 0) break;
                continue;
            }
        }

        return res;
    }

    @Override
    public int getRowSize() {
        int rowSize = 0;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for(Matrix i : innerList){
            rowSize += i.getRowSize();
        }
        return rowSize;

        //return matrixGroup.getRowSize();
    }

    @Override
    public int getColumnSize() {
        int columnSize = 0;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for(Matrix i : innerList){
            if(i.getColumnSize() > columnSize) columnSize = i.getColumnSize();
        }
        return columnSize;

        //return matrixGroup.getColumnSize();
    }

    @Override
    public String[] Draw(Transfer my) {
        if (drawer == null) throw new IllegalArgumentException("Drawer not found!");
        //if(my == null) my = getTransferEntity();

        int rowImageSize = 2*this.getRowSize() + 1;
        int columnImageSize = 2*this.getColumnSize() + 1;

        String[] Image = new String[rowImageSize];

        /*LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for(Matrix i : innerList) {
            TransposedMatrix ti = new TransposedMatrix(i);
            i = ti;
        }*/

        StringBuilder[] temp = new StringBuilder[rowImageSize];
        for(int i = 0; i < Image.length; i++){
            temp[i] = new StringBuilder("");
        }

        int count = 0;
        int size = 0;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        //TransposedMatrix thisTM = new TransposedMatrix(matrixGroup);
        for(Matrix i : innerList) {
            i.setDrawer(drawer);
            //TransposedMatrix ti = new TransposedMatrix(i);
            String[] matrixView = i.Draw(my);

            for (int j = size; j < matrixView.length + size; j++) {
                if(count < innerList.size() - 1){
                    if(j != matrixView.length + size - 1) temp[j].append(matrixView[j-size]);
                }
                else
                    temp[j].append(matrixView[j-size]);


                if(matrixView[0].length() < columnImageSize) {
                    if ((count < innerList.size() - 1)&&(j%2 != 0)) {
                        temp[j].replace(temp[j].length() - 1, temp[j].length(), drawer.DrawVerticalBorder(temp[j].length(), columnImageSize));
                    }
                    if (j % 2 == 0) {
                        if ((j != rowImageSize - 1)&&(j != 0)) {
                            temp[j].append(drawer.DrawHorizontalBorder(columnImageSize - matrixView[0].length()));
                        } else {
                            if (drawer.hasBorder()) temp[j].append(drawer.DrawHorizontalBorder(columnImageSize - matrixView[0].length()));
                            else
                                for (int k = 0; k < columnImageSize - matrixView[0].length(); k++) {
                                    temp[j].append(" ");
                                }
                        }
                    } else
                        for (int k = 1; k < columnImageSize-matrixView[0].length()+1; k++) {
                            //if ((count == 0) && (k == 0)) temp[j].append(drawer.DrawVerticalBorder(k, columnImageSize));
                            if (k % 2 != 0) temp[j].append("-");
                            else
                                if(k != 0) temp[j].append(drawer.DrawVerticalBorder(temp[j].length(), columnImageSize));
                        }
                }
            }
            size += matrixView.length;
            if(count < innerList.size() - 1) size--;
            count++;
        }

        for(int i = 0; i < Image.length; i++)
            Image[i] = temp[i].toString();


        //TransposedMatrix thisTM = new TransposedMatrix(matrixGroup);
        //Image = thisTM.Draw(my);

        return Image;
    }

    @Override
    public String ElementToString(int row, int column, Transfer my) {
        if(!CheckRow(row)) throw new IndexOutOfBoundsException("There is no row with this number");
        if(!CheckCol(column)) throw new IndexOutOfBoundsException("There is no column with this number");

        String res = null;
        int copyRow = row;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for(Matrix m : innerList){
            if((copyRow < m.getRowSize())&&(column < m.getColumnSize())){
                res = m.ElementToString(row, column, my);
                break;
            }
            else {
                copyRow -= m.getRowSize();
                if (copyRow < 0) break;
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
        drawer = d;
    }

    @Override
    public boolean isComponent() {
        return false;
    }
}
