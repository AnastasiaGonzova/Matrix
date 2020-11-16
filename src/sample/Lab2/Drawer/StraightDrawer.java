package sample.Lab2.Drawer;

public class StraightDrawer extends SomeDrawer {

    private static StraightDrawer instance = null;

    public static StraightDrawer getInstance(){
        if(instance == null) instance = new StraightDrawer();
        return instance;
    }

    private StraightDrawer(){

    }

    final protected char HorizontalBorderSymbol(){
        return '-';
    }

    final public char DrawVerticalBorder(int cell, int size){
        return '|';
    }
}
