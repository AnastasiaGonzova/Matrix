package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;
import sample.Lab1.Vector.SparseVector;
import sample.Lab1.Vector.Vector;

import java.util.LinkedList;
import java.util.Objects;

public class SparseMatrix extends SomeMatrix {

    public SparseMatrix(Drawer d){
        super(d);
        orderList = new LinkedList<>();
    }

    public SparseMatrix(int row, int col, Drawer d){
        super(row, col, d);
        orderList = new LinkedList<>();
    }

    private LinkedList<Order> orderList;

    private class Order{

        private int row;
        private int column;

        public Order(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Order order = (Order) o;
            return row == order.row &&
                    column == order.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }

    public Vector Create(){
        return new SparseVector();
    }

    public Vector Create(int s){
        return new SparseVector(s);
    }

    @Override
    public boolean WriteElement(int row, int column, int element){
        if(getRows()[row].WriteElement(column, element)){
            Order order = new Order(row, column);
            if(orderList.contains(order)){
                orderList.remove(order);
            }
            orderList.add(order);
            return true;
        }
        return false;
    }

    final public String ElementToString(int i, int j){

        Integer res = ReadElement(i, j);
        if(res != 0) return res.toString();
        else return "_";
    }

    final protected String[] drawAlgorithm () {

        int rowImageSize = 2 * getRowSize() + 1;
        int columnImageSize = 2 * getColumnSize() + 1;
        String[] Image = new String[rowImageSize];

        StringBuilder[] tempMatrix = new StringBuilder[getRowSize()];
        for (int i = 0; i < getRowSize(); i++){
            tempMatrix[i] = new StringBuilder("");
            for (int j = 0; j < getColumnSize(); j++) {
                tempMatrix[i].append("_");
            }
        }


        for (Order i : orderList) {
            tempMatrix[i.getRow()].replace(i.getColumn(), i.getColumn() + 1, ElementToString(i.getRow(), i.getColumn()));
        }

        for (int i = 0; i < rowImageSize; i++) {
            if (i % 2 == 0) {
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
            } else {
                StringBuilder tempString = new StringBuilder("");
                for (int j = 0; j < getColumnSize(); j++) {
                    if (j == 0) {
                        tempString.append(getDrawer().DrawVerticalBorder(j, getColumnSize()));
                    }
                    tempString.append(tempMatrix[i / 2].charAt(j));
                    tempString.append(getDrawer().DrawVerticalBorder(j + 1, getColumnSize())); //меняем ориентировку на право
                }
                Image[i] = tempString.toString();
            }
        }
            return Image;
    }
}
