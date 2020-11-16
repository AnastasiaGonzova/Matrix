package sample.Lab2.Drawer;

public class CurlyDrawer extends SomeDrawer {

    private static CurlyDrawer instance = null;

    public static CurlyDrawer getInstance(){
        if(instance == null) instance = new CurlyDrawer();
        return instance;
    }

    private CurlyDrawer(){

    }

    final protected char HorizontalBorderSymbol(){
        return '~';
    }


    final public char DrawVerticalBorder(int cell, int size){
        if(cell == 0) return '{';
        else if(cell == size) return '}';
        else  return '!';
    }
}
