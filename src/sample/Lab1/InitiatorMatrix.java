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

    public static void extraFill(Matrix matrix, int notzero, int maxelement){
        int zeros = matrix.getRowSize()*matrix.getColumnSize() - notzero;
        boolean[][] check = new boolean[matrix.getRowSize()][matrix.getColumnSize()];
        for(int i = 0; i < matrix.getRowSize(); i++)
            for(int j = 0; j < matrix.getColumnSize(); j++)
                check[i][j] = false;

        while(!check(check, matrix.getRowSize(), matrix.getColumnSize())){
            int row = (int)(Math.random()*matrix.getRowSize());
            int col = (int)(Math.random()*matrix.getColumnSize());
            if(check[row][col]) continue;

            int newElement;
            do {
                do {
                    newElement = (int) (Math.random() * maxelement);
                }while((zeros <= 0)&&(newElement == 0));
                if(notzero <= 0) newElement = 0;
            } while (!matrix.WriteElement(row, col, newElement));
            if(newElement != 0) notzero--;
            else zeros--;

            check[row][col] = true;
        }
    }

    private static boolean check(boolean[][] b, int row, int column){
        boolean flag = true;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++)
                if(!b[i][j]) {
                    flag = false;
                    break;
                }
            if(!flag) break;
        }

        return flag;
    }
}
