package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;

public interface Matrix {

    boolean WriteElement(int row, int column, int element);

    Integer ReadElement(int row, int column);

    int getRowSize();
    int getColumnSize();

    default String[] Draw(){
        if (getDrawer() == null) throw new IllegalArgumentException("Drawer not found!");

        int rowImageSize = 2*getRowSize() + 1;
        int columnImageSize = 2*getColumnSize() + 1;
        String[] Image = new String[rowImageSize];

        for(int i = 0; i < rowImageSize; i++){
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
                for(int j = 0; j < getColumnSize(); j++){
                    if (j == 0){
                        temp.append(getDrawer().DrawVerticalBorder(j, getColumnSize()));
                    }
                    temp.append(ElementToString(i/2, j));
                    temp.append(getDrawer().DrawVerticalBorder(j+1, getColumnSize())); //меняем ориентировку на право
                }
                Image[i] = temp.toString();
            }
        }
        return Image;
    }
    String ElementToString(int row, int column);

    Drawer getDrawer();
    void setDrawer(Drawer d);

    boolean isComponent();
}
