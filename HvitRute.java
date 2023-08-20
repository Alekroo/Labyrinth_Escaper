public class HvitRute extends Rute
{
		//Nabo rute variabler
		private Rute nord;
		private Rute soor;
		private Rute oost;
		private Rute vest;

		//Variabel for lagring av streng koordinater
		protected static String v;

		public HvitRute(int kolonne, int rad, Labyrint labyrint)
		{
			super(kolonne, rad, labyrint);
		}


		public char tilTegn()
		{
			return '.';
		}

		public void settNord(Rute r)
		{
			nord = r;
		}

		public void settSoor(Rute r)
		{
			soor = r;
		}

		public void settOost(Rute r)
		{
			oost = r;
		}

		public void settVest(Rute r)
		{
			vest = r;
		}

		public Rute getNord()
		{
			return nord;
		}

		public Rute getSoor()
		{
			return soor;
		}

		public Rute getOost()
		{
			return oost;
		}

		public Rute getVest()
		{	
			return vest;
		}

		public String hentV()
		{
			return v;
	}

	//"gaar" innom hver mulig rute til den finner en utvei
	@Override
 	public void gaa(String vei, Rute forrigeRute)
 	{ 

 		vei += this.faaKoordinater() + " -> ";

		if (nord != forrigeRute){
			nord.gaa(vei, this);
		}

		if (soor != forrigeRute) {
			soor.gaa(vei, this);
		}

		if (oost != forrigeRute) {
			oost.gaa(vei, this);
		}

		if (vest != forrigeRute) {
			vest.gaa(vei, this);
		}


	}

	public void restartV()
	{
		v = "";
	}

	public void finnUtvei() 
	{
		this.gaa("", this);
	}

}