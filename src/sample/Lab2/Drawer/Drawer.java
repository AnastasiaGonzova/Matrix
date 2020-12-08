package sample.Lab2.Drawer;

public interface Drawer {

    String DrawHorizontalBorder(int line);
    String DrawVerticalBorder(int cell, int size);

    boolean hasBorder();
    void setBorder(boolean value);
}
