package sample.Lab2.Drawer;

public class StraightDrawer extends SomeDrawer {

    private static StraightDrawer instance = null;

    public static StraightDrawer getInstance(){
        if(instance == null) instance = new StraightDrawer();
        return instance;
    }

    private StraightDrawer() {
        this.setBorder(true);
    }

    final protected char HorizontalBorderSymbol(){
        return '-';
    }

    final public String DrawVerticalBorder(int cell, int size){
        if(((cell == 0)||(cell == size))&&(!this.hasBorder())) return " ";
        return "|";
    }
}
