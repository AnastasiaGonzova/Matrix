package sample.Lab2.Drawer;

public abstract class SomeDrawer implements Drawer {

    private boolean border;

    @Override
    public boolean hasBorder() {
        return border;
    }

    @Override
    public void setBorder(boolean value) {
        border = value;
    }

    final public String DrawHorizontalBorder(int line){
        StringBuilder S = new StringBuilder("");
        for(int i = 0; i < line; i++)
            S.append(HorizontalBorderSymbol());

        String res = S.toString();
        return res;
    }

    protected abstract char HorizontalBorderSymbol();

}
