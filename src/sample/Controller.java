package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.Lab1.InitiatorMatrix;
import sample.Lab1.Matrix.Matrix;
import sample.Lab1.Matrix.RegularMatrix;
import sample.Lab1.Matrix.SomeMatrix;
import sample.Lab1.Matrix.SparseMatrix;
import sample.Lab1.StatisticMatrix;
import sample.Lab2.Drawer.CurlyDrawer;
import sample.Lab2.Drawer.StraightDrawer;
import sample.Lab3.ColumnDecorator;
import sample.Lab3.RowDecorator;

public class Controller{

    @FXML
    private Label Title;

    @FXML
    private Button CreateSpaMatrix;

    @FXML
    private TextArea TextView;

    @FXML
    private Button CreateRegMatrix;

    @FXML
    private CheckBox Border;

    @FXML
    private Label CreateMatrix;

    @FXML
    private ChoiceBox<String> SelectDrawer;

    @FXML
    private Label ChoiceDrawer;

    @FXML
    private Button Statistic;

    @FXML
    private Label Restruct;

    @FXML
    private Button RestructRowCol;

    @FXML
    private Button Back;

    private RowDecorator decorator = null;

    @FXML
    void initialize(){

        ObservableList<String> drawers = FXCollections.observableArrayList("Straight", "Curly");
        SelectDrawer.setValue("Straight");
        SelectDrawer.setItems(drawers);
        ChangeListener<String> changeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(decorator == null) return;

                if(newValue!=null) {
                    if (newValue.equals("Straight")) {
                        decorator.setDrawer(StraightDrawer.getInstance());

                        ConsoleView(decorator.Draw());
                        UIView(decorator.Draw());
                    }
                    if (newValue.equals("Curly")) {
                        decorator.setDrawer(CurlyDrawer.getInstance());

                        ConsoleView(decorator.Draw());
                        UIView(decorator.Draw());
                    }
                }
            }
        };

        SelectDrawer.getSelectionModel().selectedItemProperty().addListener(changeListener);


        CreateRegMatrix.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(SelectDrawer.getValue().equals("Straight")){
                    RegularMatrix m = new RegularMatrix(StraightDrawer.getInstance());
                    decorator = CreateDecorator(m);
                }
                if(SelectDrawer.getValue().equals("Curly")){
                    RegularMatrix m = new RegularMatrix(CurlyDrawer.getInstance());
                    decorator = CreateDecorator(m);
                }

                decorator.setBorder(Border.isSelected());
                InitiatorMatrix.Fill(decorator, (int)(Math.random()*(decorator.getColumnSize()*decorator.getRowSize())),10);

                ConsoleView(decorator.Draw());
                UIView(decorator.Draw());
            }
        });

        CreateSpaMatrix.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(SelectDrawer.getValue().equals("Straight")){
                    SparseMatrix m = new SparseMatrix(StraightDrawer.getInstance());
                    decorator = CreateDecorator(m);
                }
                if(SelectDrawer.getValue().equals("Curly")){
                    SparseMatrix m = new SparseMatrix(CurlyDrawer.getInstance());
                    decorator = CreateDecorator(m);
                }
                decorator.setBorder(Border.isSelected());
                int notzeroelements;
                do{
                    notzeroelements = (int)(Math.random()*(decorator.getColumnSize()*decorator.getRowSize()));
                }while(notzeroelements > (((2*decorator.getColumnSize())/decorator.getColumnSize())*decorator.getRowSize()));

                InitiatorMatrix.Fill(decorator, notzeroelements,10);

                ConsoleView(decorator.Draw());
                UIView(decorator.Draw());
            }
        });

        Border.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(decorator == null) return;
                decorator.setBorder(Border.isSelected());

                ConsoleView(decorator.Draw());
                UIView(decorator.Draw());
            }
        });

        Statistic.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(decorator == null) return;
                StatisticMatrix m = new StatisticMatrix(decorator);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Информация о матрице");
                alert.setContentText("Сумма элементов: " + m.Sum() + "\nСреднее значение элементов: " + m.Average() +
                        "\nМаксимальный элемент: " + m.Max() + "\nКоличество не нулевых элементов: " + m.NotZeros());
                alert.showAndWait();
            }
        });

        RestructRowCol.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(decorator == null) return;
                int Up = (int)(Math.random()*decorator.getRowSize());
                int Down = (int)(Math.random()*decorator.getRowSize());
                int Left = (int)(Math.random()*decorator.getColumnSize());
                int Right = (int)(Math.random()*decorator.getColumnSize());

                decorator.Swap(Up, Down);
                decorator.getComponent().Swap(Left, Right);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Перенумерация");
                alert.setContentText("Изменения: строки " + Up + " и " + Down + ", столбцы " + Left + " и " + Right);
                alert.showAndWait();

                ConsoleView(decorator.Draw());
                UIView(decorator.Draw());

            }
        });

        Back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(decorator == null) return;
                decorator.DefaultView();

                ConsoleView(decorator.Draw());
                UIView(decorator.Draw());
            }
        });
    }

    private void ConsoleView(String[] view){
        for(int i = 0; i < view.length; i++)
            System.out.println(view[i]);
    }

    private RowDecorator CreateDecorator(Matrix in){
        return new RowDecorator(new ColumnDecorator(in));
    }

    private void UIView(String[] view){
        TextView.clear();
        for(int i = 0; i < view.length; i++){
            TextView.appendText(view[i]);
            TextView.appendText("\n");
        }
    }

}