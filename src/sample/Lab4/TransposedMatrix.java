package sample.Lab4;

import sample.Lab1.Matrix.Matrix;
import sample.Lab2.Drawer.Drawer;
import sample.Lab3.Transfer;

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
    final public String[] Draw(Transfer my) {
        if (this.getDrawer() == null) throw new IllegalArgumentException("Drawer not found!");
        if(my == null) my = matrix.getTransferEntity();

        int rowImageSize = 2*this.getRowSize() + 1;
        int columnImageSize = 2*this.getColumnSize() + 1;

        String[] Image = new String[rowImageSize];

        for(int i = 0; i < Image.length; i++){
            if (i % 2 == 0){
                if((i!=0)&&(i!= rowImageSize-1)) Image[i] = getDrawer().DrawHorizontalBorder(columnImageSize);
                else{
                    if(this.getDrawer().hasBorder()) Image[i] = getDrawer().DrawHorizontalBorder(columnImageSize);
                    else{
                        StringBuilder temp = new StringBuilder("");
                        for(int j = 0; j < columnImageSize; j++)
                            temp.append(" ");
                        Image[i] = temp.toString();
                    }
                }
            }
            else {
                StringBuilder temp = new StringBuilder("");
                for(int j = 0; j < this.getColumnSize(); j++){
                    if (j == 0){
                        temp.append(getDrawer().DrawVerticalBorder(j, getColumnSize()));
                    }
                    temp.append(this.ElementToString(i/2, j, my));
                    temp.append(getDrawer().DrawVerticalBorder(j+1, this.getColumnSize()));
                }
                Image[i] = temp.toString();
            }
        }

        return Image;
    }


    @Override
    final public String ElementToString(int row, int column, Transfer my) {
        return matrix.ElementToString(column, row, my);
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
}
