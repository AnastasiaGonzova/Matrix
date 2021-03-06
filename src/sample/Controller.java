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
import sample.Lab1.Matrix.SparseMatrix;
import sample.Lab1.StatisticMatrix;
import sample.Lab2.Drawer.CurlyDrawer;
import sample.Lab2.Drawer.StraightDrawer;
import sample.Lab3.ColumnDecorator;
import sample.Lab3.Decorator;
import sample.Lab3.RowDecorator;
import sample.Lab4.HorizontalMatrixGroup;
import sample.Lab4.VerticalMatrixGroup;
import sample.Lab5.AppInitiatorCommand;
import sample.Lab5.CommandManager;
import sample.Lab5.SomeCommand;

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

    @FXML
    private Label Group;

    @FXML
    private Button HGroup;

    @FXML
    private Button VGroup;

    @FXML
    private Button AddHGroup;

    @FXML
    private Button AddVGroup;

    @FXML
    private Button Change;

    @FXML
    private Button Undo;


    private Controller controller = this;

    private RowDecorator decorator = null;

    private HorizontalMatrixGroup hmg = null;

    private VerticalMatrixGroup vhmg = null;


    @FXML
    void initialize(){

        AppInitiatorCommand cdm = new AppInitiatorCommand(controller, decorator){
            @Override
            public void doExecute() {
                controller.decorator = null;
                controller.hmg = null;
                controller.vhmg = null;
                TextView.clear();
            }
        };

        cdm.Execute();

        ObservableList<String> drawers = FXCollections.observableArrayList("Straight", "Curly");
        SelectDrawer.setValue("Straight");
        SelectDrawer.setItems(drawers);
        ChangeListener<String> changeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(decorator != null){
                    if(newValue!=null) {
                        if (newValue.equals("Straight")) {
                            decorator.setDrawer(StraightDrawer.getInstance());
                        }
                        if (newValue.equals("Curly")) {
                            decorator.setDrawer(CurlyDrawer.getInstance());
                        }

                        ControllerDecoratorCommand cdm = new ControllerDecoratorCommand(controller, decorator) {
                            @Override
                            public void doExecute() {
                                setData();
                            }
                        };

                        cdm.Execute();

                        ConsoleView(decorator.Draw());
                        UIView(decorator.Draw());
                  }
                }
                if(hmg != null){
                    if(newValue!=null) {
                        if (newValue.equals("Straight")) {
                            hmg.setDrawer(StraightDrawer.getInstance());
                        }
                        if (newValue.equals("Curly")) {
                            hmg.setDrawer(CurlyDrawer.getInstance());
                        }

                        ControllerHorizontalGroupCommand cdm = new ControllerHorizontalGroupCommand(controller, hmg) {
                            @Override
                            public void doExecute() {
                                setData();
                            }
                        };

                        cdm.Execute();

                        ConsoleView(hmg.Draw());
                        UIView(hmg.Draw());
                    }
                }
                if(vhmg != null){
                    if(newValue!=null) {
                        if (newValue.equals("Straight")) {
                            vhmg.setDrawer(StraightDrawer.getInstance());
                        }
                        if (newValue.equals("Curly")) {
                            vhmg.setDrawer(CurlyDrawer.getInstance());
                        }

                        ControllerVerticalGroupCommand cdm = new ControllerVerticalGroupCommand(controller, vhmg) {
                            @Override
                            public void doExecute() {
                                setData();
                            }
                        };

                        cdm.Execute();

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

                decorator.getDrawer().setBorder(Border.isSelected());
                InitiatorMatrix.extraFill(decorator, (int)(Math.random()*(decorator.getColumnSize()*decorator.getRowSize())),10);

                ControllerDecoratorCommand cdm = new ControllerDecoratorCommand(controller, decorator){
                    @Override
                    public void doExecute() {
                        setData();
                    }
                };

                cdm.Execute();

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
                decorator.getDrawer().setBorder(Border.isSelected());
                int notzeroelements;
                do{
                    notzeroelements = (int)(Math.random()*(decorator.getColumnSize()*decorator.getRowSize()));
                }while(notzeroelements > (((2*decorator.getColumnSize())/decorator.getColumnSize())*decorator.getRowSize()));

                InitiatorMatrix.extraFill(decorator, notzeroelements,10);

                ControllerDecoratorCommand cdm = new ControllerDecoratorCommand(controller, decorator){
                    @Override
                    public void doExecute() {
                        setData();
                    }
                };

                cdm.Execute();

                ConsoleView(decorator.Draw());
                UIView(decorator.Draw());
            }
        });

        Border.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(decorator != null){
                    decorator.getDrawer().setBorder(Border.isSelected());

                    ControllerDecoratorCommand cdm = new ControllerDecoratorCommand(controller, decorator){
                        @Override
                        public void doExecute() {
                            setData();
                        }
                    };

                    cdm.Execute();

                    ConsoleView(decorator.Draw());
                    UIView(decorator.Draw());
                }
                if(hmg != null){
                    hmg.getDrawer().setBorder(Border.isSelected());

                    ControllerHorizontalGroupCommand chgm = new ControllerHorizontalGroupCommand(controller, hmg){
                        @Override
                        public void doExecute() {
                            setData();
                        }
                    };

                    chgm.Execute();

                    ConsoleView(hmg.Draw());
                    UIView(hmg.Draw());
                }
                if(vhmg != null){
                    vhmg.getDrawer().setBorder(Border.isSelected());

                    ControllerVerticalGroupCommand cvgm = new ControllerVerticalGroupCommand(controller, vhmg){
                        @Override
                        public void doExecute() {
                            setData();
                        }
                    };

                    cvgm.Execute();

                    ConsoleView(vhmg.Draw());
                    UIView(vhmg.Draw());
                }
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
                ((Decorator)decorator.getComponent()).Swap(Left, Right);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Перенумерация");
                alert.setContentText("Изменения: строки " + Up + " и " + Down + ", столбцы " + Left + " и " + Right);
                alert.showAndWait();

                ControllerDecoratorCommand cdm = new ControllerDecoratorCommand(controller, decorator){
                    @Override
                    public void doExecute() {
                        setData();
                    }
                };

                cdm.Execute();

                ConsoleView(decorator.Draw());
                UIView(decorator.Draw());
            }
        });

        Back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(decorator == null) return;
                decorator.DefaultView();

                ControllerDecoratorCommand cdm = new ControllerDecoratorCommand(controller, decorator){
                    @Override
                    public void doExecute() {
                        setData();
                    }
                };

                cdm.Execute();

                ConsoleView(decorator.Draw());
                UIView(decorator.Draw());
            }
        });

        HGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(SelectDrawer.getValue().equals("Straight")){
                    hmg = new HorizontalMatrixGroup(StraightDrawer.getInstance());
                    if(decorator != null) {
                        hmg.AddMatrix(decorator);
                        decorator = null;
                    }
                }
                if(SelectDrawer.getValue().equals("Curly")){
                    hmg = new HorizontalMatrixGroup(CurlyDrawer.getInstance());
                    if(decorator != null) {
                        hmg.AddMatrix(decorator);
                        decorator = null;
                    }
                }
                hmg.getDrawer().setBorder(Border.isSelected());

                ControllerHorizontalGroupCommand chgm = new ControllerHorizontalGroupCommand(controller, hmg){
                    @Override
                    public void doExecute() {
                        setData();
                    }
                };

                chgm.Execute();

                ConsoleView(hmg.Draw());
                UIView(hmg.Draw());
            }
        });

        VGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(SelectDrawer.getValue().equals("Straight")){
                    if(hmg == null)
                        vhmg = new VerticalMatrixGroup(StraightDrawer.getInstance());
                    else {
                        vhmg = new VerticalMatrixGroup(StraightDrawer.getInstance());
                        for(Matrix m : hmg.getGroup()){
                            vhmg.AddMatrix(m);
                        }
                    }
                    if(decorator != null) {
                        vhmg.AddMatrix(decorator);
                        decorator = null;
                    }
                    hmg = null;
                }
                if(SelectDrawer.getValue().equals("Curly")){
                    if(hmg == null)
                        vhmg = new VerticalMatrixGroup(CurlyDrawer.getInstance());
                    else {
                        vhmg = new VerticalMatrixGroup(CurlyDrawer.getInstance());
                        for(Matrix m : hmg.getGroup()){
                            vhmg.AddMatrix(m);
                        }
                    }
                    if(decorator != null) {
                        vhmg.AddMatrix(decorator);
                        decorator = null;
                    }
                    hmg = null;
                }
                vhmg.getDrawer().setBorder(Border.isSelected());

                ControllerVerticalGroupCommand cvgm = new ControllerVerticalGroupCommand(controller, vhmg){
                    @Override
                    public void doExecute() {
                        setData();
                    }
                };

                cvgm.Execute();

                ConsoleView(vhmg.Draw());
                UIView(vhmg.Draw());
            }
        });

        AddHGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               if(hmg == null) return;
               int rand = (int)(Math.random()*2);
               Matrix m;
               if(rand == 0){
                   m = new RegularMatrix(hmg.getDrawer());
                   InitiatorMatrix.extraFill(m, (int)(Math.random()*(m.getColumnSize()*m.getRowSize())),10);
               }
               else{
                   m = new SparseMatrix(hmg.getDrawer());
                   int notzeroelements;
                   do{
                       notzeroelements = (int)(Math.random()*(m.getColumnSize()*m.getRowSize()));
                   }while(notzeroelements > (((2*m.getColumnSize())/m.getColumnSize())*m.getRowSize()));

                   InitiatorMatrix.extraFill(m, notzeroelements,10);
               }
               hmg.AddMatrix(m);

                ControllerHorizontalGroupCommand chgm = new ControllerHorizontalGroupCommand(controller, hmg){
                    @Override
                    public void doExecute() {
                        setData();
                    }
                };

                chgm.Execute();

                ConsoleView(hmg.Draw());
                UIView(hmg.Draw());
            }
        });

        AddVGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(vhmg == null) return;
                int rand = (int)(Math.random()*2);
                Matrix m;
                if(rand == 0){
                    m = new RegularMatrix(vhmg.getDrawer());
                    InitiatorMatrix.extraFill(m, (int)(Math.random()*(m.getColumnSize()*m.getRowSize())),10);
                }
                else{
                    m = new SparseMatrix(vhmg.getDrawer());
                    int notzeroelements;
                    do{
                        notzeroelements = (int)(Math.random()*(m.getColumnSize()*m.getRowSize()));
                    }while(notzeroelements > (((2*m.getColumnSize())/m.getColumnSize())*m.getRowSize()));

                    InitiatorMatrix.extraFill(m, notzeroelements,10);
                }
                vhmg.AddMatrix(m);

                ControllerVerticalGroupCommand cvgm = new ControllerVerticalGroupCommand(controller, vhmg){
                    @Override
                    public void doExecute() {
                        setData();
                    }
                };

                cvgm.Execute();

                ConsoleView(vhmg.Draw());
                UIView(vhmg.Draw());
            }
        });

        Change.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(decorator == null) return;

                int x = (int)(Math.random()*decorator.getRowSize());
                int y = (int)(Math.random()*decorator.getRowSize());
                int value = (int)(Math.random()*10);

                decorator.WriteElement(x, y, value);

                ControllerDecoratorCommand cdm = new ControllerDecoratorCommand(controller, decorator){
                    @Override
                    public void doExecute() {
                        setData();
                    }
                };

                cdm.Execute();

                ConsoleView(decorator.Draw());
                UIView(decorator.Draw());

            }
        });

        Undo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {

                CommandManager.getInstance().Undo();

                if(decorator != null) {
                    ConsoleView(decorator.Draw());
                    UIView(decorator.Draw());
                }

                if(hmg != null){
                    ConsoleView(hmg.Draw());
                    UIView(hmg.Draw());
                }
                if(vhmg != null){
                    ConsoleView(vhmg.Draw());
                    UIView(vhmg.Draw());
                }
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

    private abstract class ControllerDecoratorCommand extends SomeCommand{

        private Controller controller;
        private RowDecorator condition;
        private boolean Border;

        public ControllerDecoratorCommand(Controller c, RowDecorator cond){
            controller = c;
            condition = (RowDecorator) cond.getCopy();
            Border = condition.getDrawer().hasBorder();
        }

        public void setData(){
            controller.decorator = (RowDecorator) condition.getCopy();
            controller.hmg = null;
            controller.vhmg = null;
            controller.Border.setSelected(this.Border);
        }

    }

    private abstract class ControllerHorizontalGroupCommand extends SomeCommand {

        private Controller controller;
        private HorizontalMatrixGroup condition;
        private boolean Border;

        public ControllerHorizontalGroupCommand(Controller c, HorizontalMatrixGroup cond) {
            controller = c;
            condition = (HorizontalMatrixGroup) cond.getCopy();
            Border = condition.getDrawer().hasBorder();
        }

        public void setData() {
            controller.decorator = null;
            controller.hmg = (HorizontalMatrixGroup) condition.getCopy();
            controller.vhmg = null;
            controller.Border.setSelected(this.Border);
        }
    }

    private abstract class ControllerVerticalGroupCommand extends SomeCommand{

        private Controller controller;
        private VerticalMatrixGroup condition;
        private boolean Border;

        public ControllerVerticalGroupCommand(Controller c, VerticalMatrixGroup cond){
            controller = c;
            condition = (VerticalMatrixGroup) cond.getCopy();
            Border = condition.getDrawer().hasBorder();
        }

        public void setData(){
            controller.decorator = null;
            controller.hmg = null;
            controller.vhmg = (VerticalMatrixGroup) condition.getCopy();
            controller.Border.setSelected(this.Border);
        }

    }

}