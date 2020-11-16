package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Lab1.InitiatorMatrix;
import sample.Lab1.Matrix.Matrix;
import sample.Lab1.Matrix.RegularMatrix;
import sample.Lab2.Drawer.StraightDrawer;
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
        Matrix m = new RegularMatrix(StraightDrawer.getInstance());
        InitiatorMatrix.Fill(m, 14, 10);
        RowDecorator rd = new RowDecorator(m);
        System.out.println(m);
        rd.Swap(0, 1);
        /*
        for(int i = 0; i < m.getRowSize(); i++)
            for(int j = 0; j < m.getColumnSize(); j++){
                rd.WriteElement(i, j, m.ReadELement(i, j));
            }
        */
        System.out.println(rd);
    }
}
