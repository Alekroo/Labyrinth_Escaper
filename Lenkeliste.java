import java.util.Iterator;

public class Lenkeliste<T> 	implements Liste<T>
{
	private Node forst; 

	private class Node 
	{
		T verdi;
		Node neste; 

		public Node(T verdi) 
		{
			this.verdi = verdi;
		}
	}

	//legger til et nytt element i lenkelisten helt sist
	public void leggTil(T nyVerdi)
	{
		Node nyNode = new Node(nyVerdi);
		if(stoerrelse() == 0)
		{
			forst = nyNode;
		}
		else
		{
			Node p = forst;
			while(p.neste != null)
			{
				p = p.neste;
			}	
			p.neste = nyNode;
		}
	}

	//legger til et nytt element i lenkelisten i angitt posisjon
	public void leggTil(int pos, T x)
	{
		Node nyNode = new Node(x);
		Node peker = forst;
		Node peker2;
		if(pos < 0 || pos > stoerrelse())
		{
			throw new UgyldigListeIndeks(pos);
		}
		else if(pos == 0)
		{
			forst = nyNode;
			nyNode.neste = peker;	
			return;
		}
		else
		{
			for(int i = 0; i < pos-1; i++)
			{
				peker = peker.neste;
			}

			peker2 = peker.neste;
			peker.neste = nyNode;
			nyNode.neste = peker2;

			return;
		}
	}

	//skriver ut hver element i lenkelisten 
	public void skrivUt()
	{
		Node gjelende = forst;

		while (gjelende != null) 
		{
            System.out.println(gjelende.verdi);
            gjelende = gjelende.neste;
		}
	}

	//returnerer lengden på lenkelisten
    public int stoerrelse() 
    {
        Node tmp = forst;
        int str = 0;

        while (tmp != null) {
            str++;
            tmp = tmp.neste;
        }
        return str;
    }

    //endrer på verdien til et element basert på den sin posisjon
	public void sett(int pos, T x)
	{
		if(pos < 0 || pos >= stoerrelse())
		{
			throw new UgyldigListeIndeks(pos);
		}
		else
		{
			Node peker = forst;
			for(int i = 0; i < pos; i++)
			{
				peker = peker.neste;
			}
			peker.verdi = x;
		}
	}

	//fjerner og returnerer det foorste elementet
	public T fjern()
	{
		if(stoerrelse() < 1)
		{
			throw new UgyldigListeIndeks(-1);
		}
		else
		{
			Node peker = forst;

			forst = forst.neste;
			return peker.verdi;
		}
	}

	//fjerner og returnerer et element fra lenkelisten basert på den sin posisjon
	public T fjern(int pos)
	{
		if(stoerrelse() < 1)
		{
			throw new UgyldigListeIndeks(-1);
		}
		else if(pos < 0 || pos >= stoerrelse())
		{
			throw new UgyldigListeIndeks(pos);
		}
		else if(pos == 0)
		{
			Node peker = forst;

			forst = forst.neste;
			return peker.verdi;
		}
		else 
		{
			Node peker = forst;
			T verdi;

			for(int i = 0; i < pos-1; i++)
			{
				peker = peker.neste;
			}			

			Node n = peker.neste;
			peker.neste = n.neste;

			return n.verdi;
		}
	}

	//henter et element fra lenkelisten basert på den sin posisjon
	public T hent(int pos)
	{
		Node peker = forst;
		if(pos < 0 || pos >= stoerrelse())
		{
			throw new UgyldigListeIndeks(pos);
		}
		else
		{
			T verdi;

			for(int i = 0; i < pos; i++)
			{
				peker = peker.neste;
			}		

			return peker.verdi;
		}
	}

	private class LenkelisteIterator implements Iterator<T>
	{
		private Node gjeldende;

		public LenkelisteIterator()
		{
			gjeldende = forst;
		}

		public boolean hasNext()
		{
			if(gjeldende != null)
			{
				return true;
			}
			return false;
		}

		public T next()
		{
			Node x = gjeldende;
			gjeldende = gjeldende.neste;

			return x.verdi;
		}
	}

	public Iterator<T> iterator()
	{
		return new LenkelisteIterator();
	}

}
