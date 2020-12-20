package sample.Lab4;

import sample.Lab1.Matrix.Matrix;
import sample.Lab2.Drawer.Drawer;

import java.util.LinkedList;

public class VerticalMatrixGroup implements Matrix {

    private HorizontalMatrixGroup matrixGroup;
    private Drawer drawer;

    public VerticalMatrixGroup(Drawer d) {
        matrixGroup = new HorizontalMatrixGroup(d);
        drawer = d;
    }

    public VerticalMatrixGroup(HorizontalMatrixGroup m, Drawer d) {
        LinkedList<Matrix> innerList = m.getGroup();
        matrixGroup = new HorizontalMatrixGroup(d);
        for(Matrix i : innerList){
            matrixGroup.AddMatrix(i);
        }
        drawer = d;
    }

    public void AddMatrix(Matrix m) {
        matrixGroup.AddMatrix(new TransposedMatrix(m));
    }

    private boolean CheckRow(int row) {
        return (row >= 0) && (row < this.getRowSize());
    }

    private boolean CheckCol(int column) {
        return (column >= 0) && (column < this.getColumnSize());
    }

    @Override
    public boolean WriteElement(int row, int column, int element) {
        if (!CheckRow(row)) throw new IndexOutOfBoundsException("There is no row with this number");
        if (!CheckCol(column)) throw new IndexOutOfBoundsException("There is no column with this number");

        boolean res = false;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for (Matrix m : innerList) {
            if ((row < m.getRowSize()) && (column < m.getColumnSize())) {
                res = m.WriteElement(row, column, element);
                break;
            } else {
                row -= m.getRowSize();
                if (row < 0) break;
                continue;
            }
        }

        return res;
    }

    @Override
    public Integer ReadElement(int row, int column) {
        if (!CheckRow(row)) throw new IndexOutOfBoundsException("There is no row with this number");
        if (!CheckCol(column)) throw new IndexOutOfBoundsException("There is no column with this number");

        Integer res = null;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for (Matrix m : innerList) {
            if ((row < m.getRowSize()) && (column < m.getColumnSize())) {
                res = m.ReadElement(row, column);
                break;
            } else {
                row -= m.getRowSize();
                if (row < 0) break;
                continue;
            }
        }

        return res;
    }

    @Override
    public int getRowSize() {
        /*int rowSize = 0;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for(Matrix i : innerList){
            rowSize += i.getRowSize();
        }
        return rowSize;*/

        return matrixGroup.getColumnSize();
    }

    @Override
    public int getColumnSize() {
        /*int columnSize = 0;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for(Matrix i : innerList){
            if(i.getColumnSize() > columnSize) columnSize = i.getColumnSize();
        }
        return columnSize;*/

        return matrixGroup.getRowSize();
    }

    @Override
    public String[] Draw() {
        if (drawer == null) throw new IllegalArgumentException("Drawer not found!");

        int rowImageSize = 2 * this.getRowSize() + 1;
        int columnImageSize = 2 * this.getColumnSize() + 1;

        String[] Image = new String[rowImageSize];

        int count = 0;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        LinkedList<String> rowsView = new LinkedList<>();

        for (Matrix i : innerList) {
            i.setDrawer(drawer);
            TransposedMatrix ti = new TransposedMatrix(i);
            String[] matrixView = ti.Draw();

            for (int j = 0; j < matrixView.length; j++) {
                StringBuilder temp = new StringBuilder("");
                if (count < innerList.size() - 1) {
                    if (j != matrixView.length - 1) temp.append(matrixView[j]);
                    else continue;
                } else
                    temp.append(matrixView[j]);


                if (matrixView[0].length() < columnImageSize) {
                    if ((count < innerList.size() - 1) && (j % 2 != 0)) {
                        temp.replace(temp.length() - 1, temp.length(), drawer.DrawVerticalBorder(temp.length(), columnImageSize));
                    }
                    if (j % 2 == 0) {
                        if ((j != rowImageSize - 1) && (j != 0)) {
                            temp.append(drawer.DrawHorizontalBorder(columnImageSize - matrixView[0].length()));
                        } else {
                            if (drawer.hasBorder())
                                temp.append(drawer.DrawHorizontalBorder(columnImageSize - matrixView[0].length()));
                            else
                                for (int k = 0; k < columnImageSize - matrixView[0].length(); k++) {
                                    temp.append(" ");
                                }
                        }
                    } else
                        for (int k = 1; k < columnImageSize - matrixView[0].length() + 1; k++) {
                            if (k % 2 != 0) temp.append("-");
                            else if (k != 0)
                                temp.append(drawer.DrawVerticalBorder(temp.length(), columnImageSize));
                        }

                }
                rowsView.add(temp.toString());
            }

            count++;
        }

        for(int i = 0; i < Image.length; i++)
            Image[i] = rowsView.get(i);

        return Image;
    }

    @Override
    public String ElementToString(int row, int column) {
        if(!CheckRow(row)) throw new IndexOutOfBoundsException("There is no row with this number");
        if(!CheckCol(column)) throw new IndexOutOfBoundsException("There is no column with this number");

        String res = null;
        int copyRow = row;
        LinkedList<Matrix> innerList = matrixGroup.getGroup();
        for(Matrix m : innerList){
            if((copyRow < m.getRowSize())&&(column < m.getColumnSize())){
                res = m.ElementToString(copyRow, column);
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

    public Matrix getCopy() {
        VerticalMatrixGroup copy = new VerticalMatrixGroup(this.getDrawer());
        for(Matrix m : this.matrixGroup.getGroup()){
            copy.matrixGroup.AddMatrix(m.getCopy());
        }
        return copy;
    }
}
