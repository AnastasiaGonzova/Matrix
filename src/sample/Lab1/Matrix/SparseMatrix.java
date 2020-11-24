package sample.Lab1.Matrix;

import sample.Lab2.Drawer.Drawer;
import sample.Lab1.Vector.SparseVector;
import sample.Lab1.Vector.Vector;
import sample.Lab3.Transfer;

import java.util.LinkedList;
import java.util.Objects;

public class SparseMatrix extends SomeMatrix {

    public SparseMatrix(Drawer d){
        super(d);
    }

    private LinkedList<Order> orderList = new LinkedList<>();

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

    final private String DrawElement(int i, int j, Transfer my){
        Integer res = my.Transfer(i, j);
        if(my.Transfer(i, j) != 0) return res.toString();
        else return "_";
    }

    final protected String[] drawAlgorithm (Transfer my) {

        int rowImageSize = 2 * getRowSize() + 1;
        int columnImageSize = 2 * getColumnSize() + 1;
        String[] Image = new String[rowImageSize];
        for (int i = 0; i < rowImageSize; i++)
            Image[i] = "";


        StringBuilder[] tempMatrix = new StringBuilder[getRowSize()];
        for (int i = 0; i < getRowSize(); i++){
            tempMatrix[i] = new StringBuilder("");
            for (int j = 0; j < getColumnSize(); j++) {
                tempMatrix[i].append("_");
            }
        }


        for (Order i : orderList) {
            tempMatrix[i.getRow()].replace(i.getColumn(), i.getColumn() + 1, DrawElement(i.getRow(), i.getColumn(), my));
        }

        for (int i = 0; i < rowImageSize; i++) {
            if (i % 2 == 0) {
                if ((hasBorder()) || ((i != 0) && (i != rowImageSize - 1)))
                    Image[i] = getDrawer().DrawHorizontalBorder(columnImageSize);
            } else {
                StringBuilder tempString = new StringBuilder("");
                for (int j = 0; j < getColumnSize(); j++) {
                    if (j == 0) {
                        if (hasBorder()) tempString.append(getDrawer().DrawVerticalBorder(j, getColumnSize()));
                        else tempString.append(" ");
                    }
                    tempString.append(tempMatrix[i / 2].charAt(j));
                    if (j < getColumnSize() - 1)
                        tempString.append(getDrawer().DrawVerticalBorder(j + 1, getColumnSize())); //меняем ориентировку на право
                    else if (j == getColumnSize() - 1) {
                        if (hasBorder()) tempString.append(getDrawer().DrawVerticalBorder(j + 1, getColumnSize()));
                        else tempString.append(" ");
                    }
                }
                Image[i] = tempString.toString();
            }
        }

            return Image;
        }
}
