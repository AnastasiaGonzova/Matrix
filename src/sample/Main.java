package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Lab1.InitiatorMatrix;
import sample.Lab1.Matrix.Matrix;
import sample.Lab1.Matrix.RegularMatrix;
import sample.Lab1.Matrix.SparseMatrix;
import sample.Lab2.Drawer.StraightDrawer;
import sample.Lab3.ColumnDecorator;
import sample.Lab3.Decorator;
import sample.Lab3.RowDecorator;
import sample.Lab4.HorizontalMatrixGroup;
import sample.Lab4.TransposedMatrix;
import sample.Lab4.VerticalMatrixGroup;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Матрицы");
        primaryStage.setScene(new Scene(root, 900, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
        Test();

    }

    public static void Test(){
        /*Matrix m = new SparseMatrix(StraightDrawer.getInstance());
        InitiatorMatrix.extraFill(m, 8, 10);
        System.out.println(m);
        System.out.println();
        String[] mView = m.Draw(null);
        for(int i = 0; i < mView.length; i++)
            System.out.println(mView[i]);
        System.out.println();
        Matrix rd = new RowDecorator(m);
        ((Decorator)rd).Swap(0, 1);
        String[] rdView = rd.Draw(null);
        for(int i = 0; i < rdView.length; i++)
            System.out.println(rdView[i]);
        System.out.println();

        Matrix exm = new RegularMatrix(StraightDrawer.getInstance());
        InitiatorMatrix.Fill(exm, 16, 10);
        System.out.println(exm);
        System.out.println();
        String[] exmView = exm.Draw(null);
        for(int i = 0; i < exmView.length; i++)
            System.out.println(exmView[i]);
        System.out.println();
        Matrix cd = new ColumnDecorator(exm);
        /*cd.Swap(1,2);
        String[] cdView = cd.Draw(null);
        for(int i = 0; i < cdView.length; i++)
            System.out.println(cdView[i]);
        System.out.println();

        RowDecorator rcd = new RowDecorator(cd);
        rcd.Swap(0,1);
        ((Decorator)rcd.getComponent()).Swap(2,3);
        String[] rcdView = rcd.Draw(null);
        for(int i = 0; i < rcdView.length; i++)
            System.out.println(rcdView[i]);
        System.out.println();*/

        HorizontalMatrixGroup hmg = new HorizontalMatrixGroup(StraightDrawer.getInstance());
        Matrix m1 = new SparseMatrix(1, 3, StraightDrawer.getInstance());
        Matrix m2 = new RegularMatrix(2, 2, StraightDrawer.getInstance());
        //Matrix m3 = new SparseMatrix(StraightDrawer.getInstance());
        //Matrix m4 = new RegularMatrix(3, 1, StraightDrawer.getInstance());

        InitiatorMatrix.extraFill(m1, 1, 10);
        InitiatorMatrix.extraFill(m2, 3, 10);
        //InitiatorMatrix.extraFill(m3, 8, 10);
        //InitiatorMatrix.extraFill(m4, 1, 10);

        /*RowDecorator rdm3 = new RowDecorator(m3);
        rdm3.Swap(0, 1);
        ColumnDecorator rdm2 = new ColumnDecorator(m2);
        rdm2.Swap(0, 1);*/

        hmg.AddMatrix(m1);
        hmg.AddMatrix(m2);
        //hmg.AddMatrix(rdm3);
        //hmg.AddMatrix(m4);

        String[] hmgView = hmg.Draw(null);

        for(int i = 0; i < hmgView.length; i++)
            System.out.println(hmgView[i]);
        System.out.println();

        /*Matrix m3 = new RegularMatrix(StraightDrawer.getInstance());
        InitiatorMatrix.extraFill(m3, 16, 10);

        String[] m3View = m3.Draw(null);
        for(int i = 0; i < m3View.length; i++)
            System.out.println(m3View[i]);
        System.out.println();



        String[] rdm3View = rdm3.Draw(null);
        for(int i = 0; i < rdm3View.length; i++)
            System.out.println(rdm3View[i]);
        System.out.println();*/

        /*TransposedMatrix thmg = new TransposedMatrix(hmg);
        String[] thmgView = thmg.Draw(null);
        for(int i = 0; i < thmgView.length; i++)
            System.out.println(thmgView[i]);*/


        VerticalMatrixGroup vhmg = new VerticalMatrixGroup(StraightDrawer.getInstance());
        vhmg.AddMatrix(m1);
        vhmg.AddMatrix(m2);
        String[] vhmgView = vhmg.Draw(null);
        for(int i = 0; i < vhmgView.length; i++)
            System.out.println(vhmgView[i]);

    }
}
