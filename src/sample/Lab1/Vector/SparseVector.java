package sample.Lab1.Vector;

import sample.Lab1.Matrix.SparseMatrix;

import java.util.HashMap;

public class SparseVector implements Vector {

    private HashMap<Integer, Integer> notZeroElements;
    private int size;

    public SparseVector(){
        size = 5;
        notZeroElements = new HashMap<>();
    }

    public SparseVector(int s){
        size = s;
        notZeroElements = new HashMap<>();
    }

    private boolean Check(){
        if((double)(notZeroElements.size() + 1)/size > 0.5) return false;
        return true;
    }


    @Override
    public boolean WriteElement(int position, int element) {
        if (element == 0) return true;
        if(!this.Check()) return false;
        notZeroElements.put(position, element);
        return true;
    }

    @Override
    public int ReadElement(int position) {
        if(notZeroElements.containsKey(position))
            return notZeroElements.get(position);
        else return 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder S = new StringBuilder("");
        for(int i = 0; i < size; i++){
            if(notZeroElements.containsKey(i))
                S.append(this.notZeroElements.get(i));
            else
                S.append(0);
            S.append(" ");
        }
        String res = S.toString();
        return res;
    }

    public Vector getCopy() {
        SparseVector copy = new SparseVector(this.getSize());
        for(int i = 0; i < this.getSize(); i++)
            copy.WriteElement(i, this.ReadElement(i));
        return copy;
    }
}
