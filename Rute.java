abstract public class Rute
{

	protected int kolonne;
	protected int rad;
	protected Labyrint labyrint;

	public Rute(int kolonne, int rad, Labyrint labyrint)
	{
		this.kolonne = kolonne;
		this.rad = rad;
		this.labyrint = labyrint;
	}	
	

	abstract public void gaa(String kordinatt, Rute fra);

	abstract public void finnUtvei();

	abstract public char tilTegn();

	abstract public void settNord(Rute r);

	abstract public void settSoor(Rute r);

	abstract public void settOost(Rute r);

	abstract public void settVest(Rute r);

	abstract public Rute getNord();

	abstract public Rute getSoor();

	abstract public Rute getOost();

	abstract public Rute getVest();



	public String faaKoordinater() 
	{
        return "(" + (this.kolonne ) + "," + (this.rad ) + ")";
    }

}
