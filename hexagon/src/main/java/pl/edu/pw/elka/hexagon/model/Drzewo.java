package pl.edu.pw.elka.hexagon.model;

import java.util.Collection;

/**
 * @author Michał Żurawki
 */
class Drzewo
{
	private final int WYSOKOSC;
	private final Wezel korzen;
	private Plansza planszaNajlepsza;

	Drzewo(final Plansza plansza, final int wysokosc)
	{
		this.WYSOKOSC = wysokosc;
		korzen = new Wezel(plansza);
	}

	Plansza buduj()
	{
		final int wynik = buduj(korzen, 0, new MinMax());
		System.out.println(wynik);
		return planszaNajlepsza;
	}
	
//	Plansza buduj2()
//	{
//		final int wynik = buduj(korzen, 1, new MinMax());
//		System.out.println(wynik);
//		return planszaNajlepsza;
//	}

	private int buduj(final Wezel wezel, int n, final MinMax minMax)
	{
		int wynik;
		if (n < WYSOKOSC)
		{
			final boolean ruchCzerwonego = (n & 1) == 1;
			final Collection<Plansza> plansze;
			if (ruchCzerwonego)
			{
				plansze = wezel.planszeCzerwonego();
				wynik = Integer.MAX_VALUE;
			}
			else
			{
				plansze = wezel.planszeNiebieskiego();
				wynik = Integer.MIN_VALUE;
			}
			final MinMax nowyMinMax = new MinMax(minMax);
			for (final Plansza plansza : plansze)
			{
				final Wezel nowyWezel = new Wezel(plansza);
//				wezel.dodaj(nowyWezel);
				final int dzieckoWynik = buduj(nowyWezel, n + 1, nowyMinMax);
				if (ruchCzerwonego)
				{
					wynik = Integer.min(dzieckoWynik, wynik);
					nowyMinMax.beta = wynik;
				}
				else
				{
					if (n == 0 && wynik < dzieckoWynik) 
					{
						planszaNajlepsza = plansza;
					}
					wynik = Integer.max(dzieckoWynik, wynik);
					nowyMinMax.alpha = wynik;
				}
				if (nowyMinMax.alpha >= nowyMinMax.beta)
				{
					break;
				}
			}
		}
		else
		{
			wynik = wezel.wynik();
		}
		return wynik;
	}

	@Override
	public String toString()
	{
		return korzen.toString();
	}
	
	private class MinMax
	{
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		
		MinMax() {}
		
		MinMax(final MinMax minMax)
		{
			alpha = minMax.alpha;
			beta = minMax.beta;
		}
	}
}
