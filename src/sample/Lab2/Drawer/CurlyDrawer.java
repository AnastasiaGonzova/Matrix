package sample.Lab2.Drawer;

public class CurlyDrawer extends SomeDrawer {

    private static CurlyDrawer instance = null;

    public static CurlyDrawer getInstance(){
        if(instance == null) instance = new CurlyDrawer();
        return instance;
    }

    private CurlyDrawer(){
        this.setBorder(true);
    }

    final protected char HorizontalBorderSymbol(){
        return '~';
    }


    final public String DrawVerticalBorder(int cell, int size){
        if(this.hasBorder()){
            if(cell == 0) return "{";
            if(cell == size) return "}";
        }
        else if((cell == 0)||(cell==size)) return " ";

        return "!";
    }
}
