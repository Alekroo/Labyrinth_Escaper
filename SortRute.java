public class SortRute extends Rute
{

	private Rute nord;
	private Rute soor;
	private Rute oost;
	private Rute vest;

	public SortRute(int kolonne, int rad, Labyrint labyrint)
	{
		super(kolonne, rad, labyrint);
	}

	@Override	
	public void gaa(String vei, Rute komFra) {
		return;
	}

	@Override
	public char tilTegn()
	{
		return '#';
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

	public void finnUtvei(){
		return;
	}
}