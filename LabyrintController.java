import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.io.File;
import java.io.FileInputStream;

public class LabyrintController
{

	private Stage stage;
	private LabyrintGUI gui;

	//gir eventHandler egenskaper til knapper
	public LabyrintController(Stage stage, LabyrintGUI gui)
	{
		this.stage = stage;
		this.gui = gui;
        VelgFil velgFil = new VelgFil();
        gui.hentVelgFilKnapp().setOnAction(velgFil);
        gui.hentAvsluttKnapp().setOnAction(e -> {
        	Platform.exit();
        });
        gui.hentNesteKnapp().setOnAction(e -> 
        {
        	gui.ookLabyrintTeller();
        	gui.tegnGui();
        });
        gui.hentForrigeKnapp().setOnAction(e -> 
        {
        	gui.minskLabyrintTeller();
        	gui.tegnGui();
        });
	}

	//Gir alle rutene i labyrinten funksjonaliteten til å
	//bli trykt på og finne utvei
	public void giRuteAksjon()
	{
			gui.hentLabyrinten().getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                	int col = (int) gui.hentLabyrinten().getColumnIndex(item);
                	int row = (int) gui.hentLabyrinten().getRowIndex(item);
                    System.out.println("Row: " + row  + "   Col: " + col);
                    gui.loosningGUI(col, row);
                    }
                });
         	});     
	}

	//Metoden åpner grafisk en filechooser og leser inn valgt fil
	class VelgFil implements EventHandler<ActionEvent>
	{
	   	@Override
	   	public void handle(ActionEvent event) 
	   	{
	 		FileInputStream stream = null;
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("."));
			fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All .in files", "*.in*"));
			File selectedFile = fileChooser.showOpenDialog(stage);
			if(selectedFile == null) 
			{
			   return;
			}
			gui.lesInnLabyrint(selectedFile);
		}
	}
}
