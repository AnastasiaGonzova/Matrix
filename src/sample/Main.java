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
import sample.Lab3.RowDecorator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Матрицы");
        primaryStage.setScene(new Scene(root, 900, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        //Test();

    }

    public static void Test(){
        Matrix m = new SparseMatrix(StraightDrawer.getInstance());
        InitiatorMatrix.extraFill(m, 8, 10);
        System.out.println(m);
        System.out.println();
        String[] mView = m.Draw(null);
        for(int i = 0; i < mView.length; i++)
            System.out.println(mView[i]);
        System.out.println();
        Matrix rd = new RowDecorator(m);
        rd.Swap(0, 1);
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
        System.out.println();*/

        RowDecorator rcd = new RowDecorator(cd);
        rcd.Swap(0,1);
        rcd.getComponent().Swap(2,3);
        String[] rcdView = rcd.Draw(null);
        for(int i = 0; i < rcdView.length; i++)
            System.out.println(rcdView[i]);
        System.out.println();

    }
}
