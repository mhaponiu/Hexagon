package pl.edu.pw.elka.hexagon.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

class Plansza
{
	private static final Collection<Punkt> dziury = inicjalizujDziury();
	private final Collection<Punkt> czerwone = new HashSet<Punkt>();
	private final Collection<Punkt> niebieskie = new HashSet<Punkt>();
	private final int ROZMIAR;

	Plansza(final Plansza plansza)
	{
		czerwone.addAll(plansza.czerwone);
		niebieskie.addAll(plansza.niebieskie);
		ROZMIAR = plansza.ROZMIAR;
	}

	Plansza(final int ROZMIAR)
	{
		this.ROZMIAR = ROZMIAR;
	}

	static Collection<Punkt> podajDziury()
	{
		return Collections.unmodifiableCollection(dziury);
	}

	int wynik()
	{
		return niebieskie.size() - czerwone.size();
	}

	Collection<Punkt> podajWolnychSasiadow(final Punkt punkt)
	{
		return usunZajetePunkty(usunZlePunkty(punkt.podajSasiadow()));
	}

	Collection<Punkt> podajDozwoloneSkoki(final Punkt punkt)
	{
		return usunZajetePunkty(usunZlePunkty(punkt.podajSkoki()));
	}

	Collection<Punkt> podajCzerwone()
	{
		return Collections.unmodifiableCollection(czerwone);
	}

	void dodajCzerwonego(final Punkt punkt)
	{
		czerwone.add(punkt);
	}

	void usunCzerwonego(final Punkt punkt)
	{
		czerwone.remove(punkt);
	}

	Collection<Punkt> podajNiebieskie()
	{
		return Collections.unmodifiableCollection(niebieskie);
	}

	void dodajNiebieskiego(final Punkt punkt)
	{
		niebieskie.add(punkt);
	}

	void usunNiebieskiego(final Punkt punkt)
	{
		niebieskie.remove(punkt);
	}

	void inicjalizujPlansze()
	{
		niebieskie.clear();
		czerwone.clear();
		niebieskie.addAll(inicjalizujNiebieskich());
		czerwone.addAll(inicjalizujCzerwonych());
	}

	void przesunCzerwonego(final Ruch ruch)
	{
		if (nieSaSasiadami(ruch.skad, ruch.dokad))
		{
			czerwone.remove(ruch.skad);
		}
		czerwone.add(ruch.dokad);
		final Collection<Punkt> sasiedzi = ruch.dokad.podajSasiadow();
		final Collection<Punkt> doSkasowania = new LinkedList<>();
		for (final Punkt punkt : sasiedzi)
		{
			if (niebieskie.contains(punkt))
			{
				doSkasowania.add(punkt);
				czerwone.add(punkt);
			}
		}
		for (final Punkt punkt : doSkasowania)
		{
			niebieskie.remove(punkt);
		}
	}

	void przesunNiebieskiego(final Ruch ruch)
	{
		if (nieSaSasiadami(ruch.skad, ruch.dokad))
		{
			niebieskie.remove(ruch.skad);
		}
		niebieskie.add(ruch.dokad);
		final Collection<Punkt> sasiedzi = ruch.dokad.podajSasiadow();
		final Collection<Punkt> doSkasowania = new LinkedList<>();
		for (final Punkt punkt : sasiedzi)
		{
			if (czerwone.contains(punkt))
			{
				doSkasowania.add(punkt);
				niebieskie.add(punkt);
			}
		}
		for (final Punkt punkt : doSkasowania)
		{
			czerwone.remove(punkt);
		}
	}
	
	private static boolean nieSaSasiadami(final Punkt a, final Punkt b)
	{
		return !a.podajSasiadow().contains(b);
	}
	
	private static Collection<Punkt> inicjalizujDziury()
	{
		final Collection<Punkt> punkty = new HashSet<>();
		punkty.add(new Punkt(4, 3));
		punkty.add(new Punkt(3, 4));
		punkty.add(new Punkt(5, 5));
		return Collections.unmodifiableCollection(punkty);
	}

	private Collection<Punkt> inicjalizujNiebieskich()
	{
		final Collection<Punkt> punkty = new HashSet<>();
		punkty.add(new Punkt(4, 8));
		punkty.add(new Punkt(8, 4));
		punkty.add(new Punkt(0, 0));
		return punkty;
	}

	private Collection<Punkt> inicjalizujCzerwonych()
	{
		final Collection<Punkt> punkty = new HashSet<>();
		punkty.add(new Punkt(8, 8));
		punkty.add(new Punkt(0, 4));
		punkty.add(new Punkt(4, 0));
		return punkty;
	}

	private Collection<Punkt> usunZlePunkty(final Collection<Punkt> punkty)
	{
		final Collection<Punkt> punktyPrawidlowe = new LinkedList<>();
		for (final Punkt punkt : punkty)
		{
			if (punkt.x >= 0 && punkt.y >= 0 && punkt.x < ROZMIAR && punkt.y < ROZMIAR)
			{
				final int POLOWA = ROZMIAR / 2;
				if (punkt.x > POLOWA)
				{
					if (punkt.x - POLOWA <= punkt.y)
					{
						punktyPrawidlowe.add(punkt);
					}
				}
				else
				{
					if (punkt.y - POLOWA <= punkt.x)
					{
						punktyPrawidlowe.add(punkt);
					}
				}
			}
		}
		return punktyPrawidlowe;
	}

	private Collection<Punkt> usunZajetePunkty(final Collection<Punkt> punkty)
	{
		final Collection<Punkt> punktyWolne = new LinkedList<>();
		for (final Punkt punkt : punkty)
		{
			if (!dziury.contains(punkt) && !niebieskie.contains(punkt) && !czerwone.contains(punkt))
			{
				punktyWolne.add(punkt);
			}
		}
		return punktyWolne;
	}

	@Override
	public String toString()
	{
		String res = czerwone.toString();
		res += " ";
		res += niebieskie.toString();
		return res;
	}
}
