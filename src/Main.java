import java.lang.classfile.Label;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class Main extends Application {

    private TextField campo1 = new TextField();
    private TextField campo2 = new TextField();
    private Label resultado = new Label();

    @Override
    public void start(Stage stage) {
        campo1.setPromptText("Número 1");
        campo2.setPromptText("Número 2");

        Button somar = new Button("+");
        Button subtrair = new Button("-");
        Button multiplicar = new Button("*");
        Button dividir = new Button("/");

        HBox botoes = new HBox(10, somar, subtrair, multiplicar, dividir);
        botoes.setAlignment(Pos.CENTER);

        resultado.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");

        somar.setOnAction(e -> calcular('+'));
        subtrair.setOnAction(e -> calcular('-'));
        multiplicar.setOnAction(e -> calcular('*'));
        dividir.setOnAction(e -> calcular('/'));

        VBox layout = new VBox(15,
                new Label("Calculadora JavaFX"),
                campo1,
                campo2,
                botoes,
                new Label("Resultado:"),
                resultado
        );
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("container");

        Scene scene = new Scene(layout, 350, 300);Scene scene = new Scene(layout, 350, 300);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }

    private void calcular(char operacao) {
        try {
            double num1 = Double.parseDouble(campo1.getText());
            double num2 = Double.parseDouble(campo2.getText());
            double res = switch (operacao) {
                case '+' -> num1 + num2;
                case '-' -> num1 - num2;
                case '*' -> num1 * num2;
                case '/' -> {
                    if (num2 == 0) throw new ArithmeticException("Divisão por zero");
                    yield num1 / num2;
                }
                default -> 0;
            };
            resultado.setText(String.format("%.2f", res));
        } catch (NumberFormatException e) {
            resultado.setText("Entrada inválida");
        } catch (ArithmeticException e) {
            resultado.setText(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
