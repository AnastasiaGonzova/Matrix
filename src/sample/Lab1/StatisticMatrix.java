package sample.Lab1;

import sample.Lab1.Matrix.Matrix;

public class StatisticMatrix {

    Matrix m;

    public StatisticMatrix(Matrix newM){
        m = newM;
    }

    public int Sum(){
        int sum = 0;
        for(int i = 0; i < m.getRowSize(); i++)
            for(int j = 0; j < m.getColumnSize(); j++)
                sum += m.ReadELement(i, j);
        return sum;
    }

    public double Average(){
        return (double)this.Sum()/(m.getRowSize()*m.getColumnSize());
    }

    public int Max(){
        int max = m.ReadELement(0,0);
        for(int i = 0; i < m.getRowSize(); i++)
            for(int j = 0; j < m.getColumnSize(); j++)
                if(m.ReadELement(i,j) > max) max = m.ReadELement(i,j);
        return max;
    }

    public int NotZeros(){
        int notzeros = 0;
        for(int i = 0; i < m.getRowSize(); i++)
            for(int j = 0; j < m.getColumnSize(); j++)
                if(m.ReadELement(i,j) != 0) notzeros++;
        return notzeros;
    }
}
