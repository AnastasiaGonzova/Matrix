package sample.Lab4;

import sample.Lab1.Matrix.Matrix;
import sample.Lab2.Drawer.Drawer;

public class TransposedMatrix implements Matrix {

    private Matrix matrix;

    public TransposedMatrix(Matrix m){
        matrix = m;
    }

    @Override
    public boolean WriteElement(int row, int column, int element) {
        return matrix.WriteElement(column, row, element);
    }

    @Override
    public Integer ReadElement(int row, int column) {
        return matrix.ReadElement(column, row);
    }

    @Override
    public int getRowSize() {
        return matrix.getColumnSize();
    }

    @Override
    public int getColumnSize() {
        return matrix.getRowSize();
    }

    @Override
    final public String ElementToString(int row, int column) {
        return matrix.ElementToString(column, row);
    }

    @Override
    public Drawer getDrawer() {
        return matrix.getDrawer();
    }

    @Override
    public void setDrawer(Drawer d) {
        if(d == null) throw new IllegalArgumentException("Drawer can't be null");
        matrix.setDrawer(d);
    }

    @Override
    public boolean isComponent() {
        return true;
    }

    public Matrix getCopy() {
        TransposedMatrix copy = new TransposedMatrix(this.matrix.getCopy());
        return copy;
    }
}
