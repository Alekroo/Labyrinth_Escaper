public class Stabel<T> extends Lenkeliste<T>
{
	//legger til et nytt element i lenkelisten helt sist
	public void leggPaa(T x)
	{
		leggTil(x);
	}

	//fjerner det siste elementet
	public T taAv()
	{
		return fjern(stoerrelse()-1);
	}

}