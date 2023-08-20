public class Aapning extends HvitRute {

	Aapning(int rad, int kolonne, Labyrint l) 
	{
		super(rad, kolonne, l);
    }

	@Override
	public char tilTegn()
	{
		return 'O';
	}

	@Override
    public void gaa(String vei, Rute forrigeRute) 
    {

 		v += vei + this.faaKoordinater() + "x";

    	return;
    }


}