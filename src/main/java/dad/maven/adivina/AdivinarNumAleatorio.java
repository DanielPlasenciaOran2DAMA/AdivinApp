package dad.maven.adivina;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinarNumAleatorio extends Application {

	private Label numeroLabel;
	private Button comprobarButton;
	private TextField introduceNumeroText;
	private Integer numeroAdivina = Adivina();
	private Integer intentos = 1;

	public static int Adivina() {
		int numeroAdivina = (int) ((Math.random() * 100) + 1);
		return numeroAdivina;

	}

	public void reincicioApp() {
		numeroAdivina = Adivina();
		intentos = 0;
	}

	public void start(Stage primaryStage) throws Exception {
		numeroLabel = new Label();
		numeroLabel.setText("Introduce un número del 1 al 100");
		numeroLabel.setAlignment(Pos.CENTER);

		introduceNumeroText = new TextField("0");
		introduceNumeroText.setAlignment(Pos.CENTER);
		introduceNumeroText.setMaxWidth(100);

		comprobarButton = new Button();
		comprobarButton.setText("Comprobar");
		comprobarButton.setDefaultButton(true);
		comprobarButton.setOnAction(e -> onComprobarButtonAction(e));
		comprobarButton.setAlignment(Pos.CENTER);

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(numeroLabel, introduceNumeroText, comprobarButton);

		Scene escena = new Scene(root, 320, 200);

		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinaApp");
		primaryStage.show();
	}

	private void onComprobarButtonAction(ActionEvent e) {
		try {
			int numeroIntroducir = Integer.parseInt(introduceNumeroText.getText());
			if (numeroAdivina == numeroIntroducir) {
				Alert ganadorAlert = new Alert(AlertType.INFORMATION);
				ganadorAlert.setTitle("AdivinaApp");
				ganadorAlert.setHeaderText("¡Has ganado!");
				ganadorAlert.setContentText(
						"Sólo has necesitado " + intentos + " intentos. \n\n Vuelve a jugar y hazlo mejor");

				ganadorAlert.showAndWait();

				reincicioApp();
			} else if (numeroAdivina > numeroIntroducir) {
				Alert falloAlert = new Alert(AlertType.WARNING);
				falloAlert.setTitle("AdivinaApp");
				falloAlert.setHeaderText("¡Has fallado!");
				falloAlert.setContentText(
						"El número a adivinar es mayor que " + numeroIntroducir + ". \n\nVuelve a intentarlo");

				falloAlert.showAndWait();
			} else if (numeroAdivina < numeroIntroducir) {
				Alert falloAlert = new Alert(AlertType.WARNING);
				falloAlert.setTitle("AdivinaApp");
				falloAlert.setHeaderText("¡Has fallado!");
				falloAlert.setContentText(
						"El número a adivinar es menor que " + numeroIntroducir + ". \n\nVuelve a intentarlo");

				falloAlert.showAndWait();
			}
		} catch (NumberFormatException error) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("AdivinaApp");
			errorAlert.setHeaderText("Error");
			errorAlert.setContentText("El número introducido no es válido");

			errorAlert.showAndWait();
		}
		intentos++;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
