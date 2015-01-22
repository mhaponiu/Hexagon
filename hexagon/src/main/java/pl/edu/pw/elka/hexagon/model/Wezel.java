package pl.edu.pw.elka.hexagon.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Michał Żurawski
 */
class Wezel
{
	private final Plansza plansza;
	private Collection<Wezel> dzieci = new LinkedList<>();

	Wezel(final Plansza plansza)
	{
		this.plansza = plansza;
	}

	/**
	 * Wszystkie możliwe ustawienia planszy, po jednym ruchu czerwonego.
	 * 
	 * @return
	 */
	Collection<Plansza> planszeCzerwonego()
	{
		final Collection<Plansza> plansze = new LinkedHashSet<>();
		final Collection<Punkt> czerwone = plansza.podajCzerwone();
		for (final Punkt skad : czerwone)
		{
			final Collection<Punkt> dostepnePunkty = plansza.podajWolnychSasiadow(skad);
			dostepnePunkty.addAll(plansza.podajDozwoloneSkoki(skad));
			for (final Punkt dokad : dostepnePunkty)
			{
				final Plansza nowaPlansza = new Plansza(plansza);
				nowaPlansza.przesunCzerwonego(new Ruch(skad, dokad));
				plansze.add(nowaPlansza);
			}
		}
		return plansze;
	}

	/**
	 * Wszystkie możliwe ustawienia planszy, po jednym ruchu niebieskiego.
	 * 
	 * @return
	 */
	Collection<Plansza> planszeNiebieskiego()
	{
		final Collection<Plansza> plansze = new LinkedHashSet<>();
		final Collection<Punkt> niebieskie = plansza.podajNiebieskie();
		for (final Punkt skad : niebieskie)
		{
			final Collection<Punkt> dostepnePunkty = plansza.podajWolnychSasiadow(skad);
			dostepnePunkty.addAll(plansza.podajDozwoloneSkoki(skad));
			for (final Punkt dokad : dostepnePunkty)
			{
				final Plansza nowaPlansza = new Plansza(plansza);
				nowaPlansza.przesunNiebieskiego(new Ruch(skad, dokad));
				plansze.add(nowaPlansza);
			}
		}
		return plansze;
	}

	void dodaj(final Wezel wezel)
	{
		dzieci.add(wezel);
	}

	int wynik()
	{
		return plansza.podajNiebieskie().size() - plansza.podajCzerwone().size();
	}

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		final Queue<Wezel> wezly = new LinkedList<>();
		wezly.add(this);
		int i = 1;
		while (!wezly.isEmpty())
		{
			final Wezel wezel = wezly.poll();
			sb.append(i++ + ". ");
			sb.append(wezel.plansza);
			sb.append("\n");
			for (final Wezel dziecko : wezel.dzieci)
			{
				wezly.add(dziecko);
			}
		}
		return sb.toString();
	}
}