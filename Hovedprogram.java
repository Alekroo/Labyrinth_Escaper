import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Hovedprogram extends Application
{

	@Override
	public void start(Stage primaryStage)
	{
		LabyrintGUI gui = new LabyrintGUI(primaryStage);
		Scene scene = new Scene(gui.hentRot());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Labyrinth Escaper!");
		primaryStage.show();
	}

	public static void main(String[] args) 
	{
		launch();
	}

}