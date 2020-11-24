package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;
import sample.Lab1.Vector.RegularVector;
import sample.Lab1.Vector.Vector;
import sample.Lab3.Transfer;

public class RegularMatrix extends SomeMatrix {

    public RegularMatrix(Drawer d){
        super(d);
    }

    public Vector Create(){
        return new RegularVector();
    }

    final private String DrawElement(int i, int j, Transfer my){
        Integer res = my.Transfer(i, j);
        return res.toString();
    }

    final protected String[] drawAlgorithm(Transfer my){

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
                    temp.append(DrawElement(i/2, j, my));
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
}
