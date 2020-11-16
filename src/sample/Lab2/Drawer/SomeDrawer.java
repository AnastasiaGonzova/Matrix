package sample.Lab2.Drawer;

import sample.Lab2.Drawer.Drawer;

public abstract class SomeDrawer implements Drawer {

    final public String DrawHorizontalBorder(int line){
        StringBuilder S = new StringBuilder("");
        for(int i = 0; i < line; i++)
            S.append(HorizontalBorderSymbol());
        String res = S.toString();
        return res;
    }

    protected abstract char HorizontalBorderSymbol();
}
