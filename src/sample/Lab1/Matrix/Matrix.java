package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;
import sample.Lab3.Transfer;

public interface Matrix {

    boolean WriteElement(int row, int column, int element);

    int ReadElement(int row, int column);

    int getRowSize();
    int getColumnSize();

    String[] Draw(Transfer my);

    default Transfer getTransferEntity(){
        return new Transfer() {
            @Override
            public int Transfer(int i, int j) {
                return ReadElement(i, j);
            }
        };
    }

    Drawer getDrawer();
    void setDrawer(Drawer d);

    boolean hasBorder();
    void setBorder(boolean check);

    void Swap(int i, int j);
    void DefaultView();
    boolean isComponent();
}
