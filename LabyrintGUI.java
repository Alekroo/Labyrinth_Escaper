import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class LabyrintGUI
{

	private BorderPane rot;
	private Stage stage;
	private Button velgFilKnapp;
	private Button avsluttKnapp;
	private GridPane labyrinten;
	private Labyrint l = null;
	private Liste<String> utveier;
	private StackPane rute;
	private int labyrintRad;
	private int labyrintKolonne;
	private Rute[][] lab;
	private LabyrintController controller;
	private Text informasjon;
	private int labyrintLoosningTeller = 0;
	private Button nesteKnapp;
	private Button forrigeKnapp;


	public LabyrintGUI(Stage Stage)
	{
		this.stage = stage;
		this.rot = new BorderPane();
		settOppGUI();
		controller = new LabyrintController(stage,this);
	}

	public void settOppGUI()
	{

		Rectangle whiteRectangle = new Rectangle(1000, 700, Color.WHITE);

		velgFilKnapp = new Button("Choose File");
		avsluttKnapp = new Button("Exit");
		informasjon  = new Text();
		informasjon.setFont(new Font(20));
		informasjon.setText("Waiting for a labyrinth file...");

		HBox velgFilAvslutt = new HBox(175,velgFilKnapp, avsluttKnapp);
		velgFilAvslutt.setAlignment(Pos.CENTER);
		nesteKnapp = new Button("Next Solution");
		forrigeKnapp = new Button("Previous Solution");

		forrigeKnapp.setVisible(false);
		nesteKnapp.setVisible(false);


		rot.setRight(nesteKnapp);
		rot.setLeft(forrigeKnapp);
		rot.setTop(informasjon);
		rot.setCenter(whiteRectangle);
		rot.setBottom(velgFilAvslutt);

		rot.setAlignment(nesteKnapp,Pos.CENTER);
		rot.setAlignment(forrigeKnapp,Pos.CENTER);
		rot.setAlignment(whiteRectangle,Pos.CENTER);
		rot.setAlignment(informasjon,Pos.CENTER);

		rot.setMargin(velgFilAvslutt, new Insets(10,0,10,0));
		rot.setMargin(informasjon, new Insets(10,0,0,0));

		rot.setStyle("-fx-background-color: #C0C0C0;");
	}

	//leser inn labyrint filen, og viser den på skjermen
	public void lesInnLabyrint(File fil)
	{
		l = null;
        try 
        {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) 
        {
            System.out.println("ERROR: could not read from file!");
            System.exit(1);
        }

        labyrinten = new GridPane();
        lab = l.getRuteArray();
        labyrintKolonne = l.getKolonne();
        labyrintRad = l.getRad(); 

        for(int rad = 0; rad < labyrintRad; rad++)
        {
        	for(int kol = 0; kol < labyrintKolonne; kol++)
        	{
        		Color farge;
        		if(lab[kol][rad] instanceof HvitRute || lab[kol][rad] instanceof Aapning)
        		{
        			farge = Color.WHITE;
        		}
        		else
        		{
        			farge = Color.BLACK;
        		}

        		Rectangle felt = new Rectangle(35,35,farge);
        		rute = new StackPane(felt);

        		labyrinten.add(rute,rad,kol);
        	}
        }
        labyrinten.setGridLinesVisible(true);
        controller.giRuteAksjon();
        labyrinten.setAlignment(Pos.CENTER);

        rot.setCenter(labyrinten);
        informasjon.setText("Click on a route to find ways out of the labyrinth!");
        rot.setTop(informasjon);
        forrigeKnapp.setVisible(false);
		nesteKnapp.setVisible(false);
		labyrintLoosningTeller = 0;
	}

	public void oppdaterKnapper()
	{
		if(labyrintLoosningTeller == 0 && (utveier.stoerrelse()-1) == 0)
		{
			forrigeKnapp.setVisible(false);
			nesteKnapp.setVisible(false);
		}
		else if(labyrintLoosningTeller < (utveier.stoerrelse()-1) && labyrintLoosningTeller > 0)
		{
			forrigeKnapp.setVisible(true);
			nesteKnapp.setVisible(true);
		}
		else if(labyrintLoosningTeller > 0 && labyrintLoosningTeller == (utveier.stoerrelse()-1))
		{
			forrigeKnapp.setVisible(true);
			nesteKnapp.setVisible(false);
		}
		else if(labyrintLoosningTeller == 0 && labyrintLoosningTeller < (utveier.stoerrelse()-1))
		{
			forrigeKnapp.setVisible(false);
			nesteKnapp.setVisible(true);
		}
	}

	public void loosningGUI(int k, int r)
	{
		utveier = l.finnUtveiFra(r,k); 
		if(utveier.stoerrelse() > 0)
		{
			tegnGui();	
		}
		else
		{
			System.out.println("No ways out!");
			informasjon.setText("No ways out!");
        	rot.setTop(informasjon);
        	forrigeKnapp.setVisible(false);
			nesteKnapp.setVisible(false);
			labyrintLoosningTeller = 0;
		}
	}

public void tegnGui()
{
		int rad = l.getRad();
		int kol = l.getKolonne();
		boolean[][] test = null;

		String s = utveier.hent(labyrintLoosningTeller);
        test = losningStringTilTabell(s,kol,rad);		
        informasjon.setText("Solution " + (labyrintLoosningTeller+1) + " of " + utveier.stoerrelse());
       	rot.setTop(informasjon);	

        oppdaterKnapper();
        labyrinten = new GridPane();

       	for(int y = 0; y < labyrintRad; y++)
        {
        	for(int x = 0; x < labyrintKolonne; x++)
        	{
        		Color farge;
        		if(lab[x][y] instanceof HvitRute || lab[x][y] instanceof Aapning)
        		{
        			if(test[y][x])
        			{
        				farge = Color.YELLOW;
        			}
        			else
        			{
						farge = Color.WHITE;
        			}
        		}
        		else
        		{
        			farge = Color.BLACK;
        		}

        		Rectangle felt = new Rectangle(35,35,farge);
        		rute = new StackPane(felt);

        		labyrinten.add(rute,y,x);
        	}
        }

       	labyrinten.setGridLinesVisible(true);
       	controller.giRuteAksjon();
       	labyrinten.setAlignment(Pos.CENTER);
        rot.setCenter(labyrinten);
}


	public Button hentVelgFilKnapp()
	{
		return velgFilKnapp;
	}

	public Button hentAvsluttKnapp()
	{
		return avsluttKnapp;
	}

	public Button hentNesteKnapp()
	{
		return nesteKnapp;
	}

	public Button hentForrigeKnapp()
	{
		return forrigeKnapp;
	}

	public BorderPane hentRot()
	{
		return rot;
	}

	public GridPane hentLabyrinten()
	{
		return labyrinten;
	}

	public void ookLabyrintTeller()
	{
		if(labyrintLoosningTeller < utveier.stoerrelse())
		{
			labyrintLoosningTeller++;		
			oppdaterKnapper();
		}
		return;
	}

	public void minskLabyrintTeller()
	{
		if(labyrintLoosningTeller > 0)
		{
			labyrintLoosningTeller--;
			oppdaterKnapper();		
		}
		return;
	}

	public static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
	    boolean[][] losning = new boolean[hoyde][bredde];
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
	    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
	    while (m.find()) {
	        int x = Integer.parseInt(m.group(1));
	        int y = Integer.parseInt(m.group(2));
	        losning[y][x] = true;
	    }
	    return losning;
	}

}
