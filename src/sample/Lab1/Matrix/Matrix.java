package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;

public interface Matrix {

    boolean WriteElement(int row, int column, int element);

    int ReadElement(int row, int column);

    int getRowSize();
    int getColumnSize();

    default String[] Draw(){
        if (getDrawer() == null) throw new IllegalArgumentException("Drawer not found!");

        int rowImageSize = 2*getRowSize() + 1;
        int columnImageSize = 2*getColumnSize() + 1;
        String[] Image = new String[rowImageSize];
        for(int i = 0; i < rowImageSize; i++)
            Image[i] = "";

        for(int i = 0; i < rowImageSize; i++){
            if (i % 2 == 0){
                if((hasBorder())||((i!=0)&&(i!= rowImageSize-1))) Image[i] = getDrawer().DrawHorizontalBorder(columnImageSize);
            }
            else {
                StringBuilder temp = new StringBuilder("");
                for(int j = 0; j < getColumnSize(); j++){
                    if (j == 0){
                        if(hasBorder()) temp.append(getDrawer().DrawVerticalBorder(j, getColumnSize()));
                        else temp.append(" ");
                    }
                    temp.append(DrawElement(i/2, j));
                    if(j < getColumnSize() - 1) temp.append(getDrawer().DrawVerticalBorder(j+1, getColumnSize())); //меняем ориентировку на право
                    else if (j == getColumnSize() - 1) {
                        if (hasBorder()) temp.append(getDrawer().DrawVerticalBorder(j+1, getColumnSize()));
                        else temp.append(" ");
                    }
                }
                Image[i] = temp.toString();
            }
        }

        return Image;
    }

    String DrawElement(int i, int j);

    Drawer getDrawer();
    void setDrawer(Drawer d);

    boolean hasBorder();
    void setBorder(boolean check);

    void Swap(int i, int j);
    void DefaultView();
    boolean isComponent();
}
