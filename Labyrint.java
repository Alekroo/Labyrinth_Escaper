import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Labyrint
{

	private Rute[][] ruteArray;
	private int rad;
	private int kolonne;
	private Stabel<String> linkedList;

	private Labyrint(int kolonne, int rad)
	{	
		this.rad = rad;
		this.kolonne = kolonne;
		ruteArray = new Rute[kolonne][rad];
	}	

	//henter ruteArray
	public Rute[][] getRuteArray()
	{
		return ruteArray;
	}

	public int getRad()
	{
		return rad;
	}

	public int getKolonne()
	{
		return kolonne;
	}
	
	//leser fra fil, lager ruter, setter dem inn paa riktig plass 
	public static Labyrint lesFraFil(File fil) throws FileNotFoundException
	{
		Labyrint l = null;
        Scanner readFile = null;
   
    		readFile = new Scanner(fil);
    	
            String line = readFile.next();
            int k = Integer.parseInt(line);
            line = readFile.next();
            int r = Integer.parseInt(line);

            Labyrint nyLabyrint = new Labyrint(k,r);

            Rute[][] labyrint = nyLabyrint.getRuteArray();

            for(int x = 0; x < k; x++)
            {	
                line = readFile.next();
	            for(int i = 0; i < r; i++)
	            {            	
	            	if(line.charAt(i) == '.')
	            	{
		            	if(i == 0 || i == r-1 || x == 0 || x == k-1) 
		            	{
		            		labyrint[x][i] = new Aapning(x,i,l);
		            	}
		            	else
		            	{
		            		labyrint[x][i] = new HvitRute(x,i,l);
		            	}
	            	}
	               	else if(line.charAt(i) == '#')
	            	{	
		       			labyrint[x][i] = new SortRute(x,i,l);
	            	}
	            }
	        }	
	     

		readFile.close();

		nyLabyrint.settNaboer();

		return nyLabyrint;
	}

	//setter rutenes naboer
	public void settNaboer() 
	{

		for(int k = 0; k < kolonne; k++)
			{
				for (int r = 1; r < rad; r++ )
			{
				ruteArray[k][r].settVest(ruteArray[k][r-1]);
			}
		}

		for(int k = 0; k < kolonne; k++)
		{
			for (int r = 0; r < rad-1; r++ )
			{
				ruteArray[k][r].settOost(ruteArray[k][r+1]);
			}
		}

		for(int k = 0; k < kolonne-1; k++)
		{
			for (int r = 0; r < rad; r++ )
			{
				ruteArray[k][r].settSoor(ruteArray[k+1][r]);
			}
		}

		for(int k = 1; k < kolonne; k++)
		{
			for (int r = 0; r < rad; r++) 
			{
				ruteArray[k][r].settNord(ruteArray[k-1][r]);
			}	
		}
	}

	//finner utvei fra input koordinater og returnerer en liste med kordinatene
	public Liste<String> finnUtveiFra(int kol, int rad)
	{
		linkedList = new Stabel<String>();

		HvitRute hrute = new HvitRute(0,0,null);
		hrute.restartV();
		ruteArray[kol][rad].finnUtvei();

		String[] parts = hrute.hentV().split("x");
		if(parts[0].equals(""))
		{
			return linkedList;
		}

		for(int i = 0; i < parts.length; i++)
		{
			linkedList.leggPaa(parts[i]);
		}

		return linkedList;
	}

}

