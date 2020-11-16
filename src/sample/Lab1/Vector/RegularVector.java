package sample.Lab1.Vector;

public class RegularVector implements Vector {

    private int[] elements;
    private int size;

    public RegularVector(){
        size = 5;
        elements = new int[size];
    }

    @Override
    public boolean WriteElement(int position, int element) {
        elements[position] = element;
        return true;
    }

    @Override
    public int ReadElement(int position) {
        return elements[position];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder S = new StringBuilder("");
        for(int i = 0; i < size; i++){
            S.append(this.elements[i]);
            S.append(" ");
        }
        String res = S.toString();
        return res;
    }
}
