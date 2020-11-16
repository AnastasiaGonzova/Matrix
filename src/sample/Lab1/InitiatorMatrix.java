package sample.Lab1;

import sample.Lab1.Matrix.Matrix;

public class InitiatorMatrix {

    public static void Fill(Matrix matrix, int notzero, int maxelement){
        int zeros = matrix.getRowSize()*matrix.getColumnSize() - notzero;
        for(int i = 0; i < matrix.getRowSize(); i++)
            for(int j = 0; j < matrix.getColumnSize(); j++){
                int newElement;
                do {
                    do {
                        newElement = (int) (Math.random() * maxelement);
                   }while((zeros <= 0)&&(newElement == 0));
                    if(notzero <= 0) newElement = 0;
                } while (!matrix.WriteElement(i, j, newElement));
                if(newElement != 0) notzero--;
                else zeros--;
            }
    }
}
