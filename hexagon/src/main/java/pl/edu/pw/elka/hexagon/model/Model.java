package pl.edu.pw.elka.hexagon.model;

import java.util.Collection;
import java.util.LinkedList;

import pl.edu.pw.elka.hexagon.plansza.Click;
import pl.edu.pw.elka.hexagon.plansza.Kolor;
import pl.edu.pw.elka.hexagon.plansza.PlanszaComponent;

/**
 * 
 * @author Michal Zurawski
 *
 */
public class Model implements Click
{
	private static final int SIZE = 9;
	private PlanszaComponent widok;
	private final Collection<Punkt> zaznaczone = new LinkedList<>();
	private final Kolor[][] plansza = new Kolor[SIZE][SIZE];

	public void click(int x, int y)
	{
		final Punkt klikniety = new Punkt(x, y);
		if (zaznaczone.isEmpty())
		{
			zaznaczone.add(klikniety);
			widok.zapal(Kolor.ZAZNACZONY, x, y);

			final Collection<Punkt> sasiedzi = getSasiedzi(klikniety);
			zaznaczone.addAll(sasiedzi);
			for (final Punkt punkt : sasiedzi)
			{
				widok.zapal(Kolor.ZIELONY, punkt.x, punkt.y);
			}

			final Collection<Punkt> skoki = getSkoki(klikniety);
			zaznaczone.addAll(skoki);
			for (final Punkt punkt : skoki)
			{
				widok.zapal(Kolor.ZOLTY, punkt.x, punkt.y);
			}
		}
		else if (zaznaczone.contains(klikniety))
		{
			widok.zgasWszystkie(Kolor.ZIELONY);
			widok.zgasWszystkie(Kolor.ZAZNACZONY);
			widok.zgasWszystkie(Kolor.ZOLTY);
			zaznaczone.clear();
		}
	}

	public void inicjalizujPlansze()
	{
		widok.czysc();
		for (Kolor[] kolory : plansza)
		{
			for (Kolor kolor : kolory)
			{
			}
		}
		widok.zapal(Kolor.PUSTY, 4, 3);
		widok.zapal(Kolor.PUSTY, 3, 4);
		widok.zapal(Kolor.PUSTY, 5, 5);
		widok.zapal(Kolor.GRACZ1, 0, 0);
		widok.zapal(Kolor.GRACZ1, 4, 8);
		widok.zapal(Kolor.GRACZ1, 8, 4);
		widok.zapal(Kolor.GRACZ2, 4, 0);
		widok.zapal(Kolor.GRACZ2, 0, 4);
		widok.zapal(Kolor.GRACZ2, 8, 8);
	}

	public void rejestrujPlansze(final PlanszaComponent plansza)
	{
		this.widok = plansza;
	}

	private Collection<Punkt> getSasiedzi(final Punkt punkt)
	{
		final Collection<Punkt> sasiedzi = new LinkedList<>();
		sasiedzi.add(new Punkt(punkt.x - 1, punkt.y));
		sasiedzi.add(new Punkt(punkt.x + 1, punkt.y));
		sasiedzi.add(new Punkt(punkt.x - 1, punkt.y - 1));
		sasiedzi.add(new Punkt(punkt.x + 1, punkt.y + 1));
		sasiedzi.add(new Punkt(punkt.x, punkt.y - 1));
		sasiedzi.add(new Punkt(punkt.x, punkt.y + 1));
		return usunZlePunkty(sasiedzi);
	}

	private Collection<Punkt> getSkoki(final Punkt punkt)
	{
		final Collection<Punkt> skoki = new LinkedList<>();
		skoki.add(new Punkt(punkt.x - 2, punkt.y));
		skoki.add(new Punkt(punkt.x + 2, punkt.y));
		skoki.add(new Punkt(punkt.x - 2, punkt.y - 1));
		skoki.add(new Punkt(punkt.x + 2, punkt.y + 1));
		skoki.add(new Punkt(punkt.x - 2, punkt.y - 2));
		skoki.add(new Punkt(punkt.x + 2, punkt.y + 2));
		skoki.add(new Punkt(punkt.x - 1, punkt.y - 2));
		skoki.add(new Punkt(punkt.x + 1, punkt.y + 2));
		skoki.add(new Punkt(punkt.x, punkt.y - 2));
		skoki.add(new Punkt(punkt.x, punkt.y + 2));
		skoki.add(new Punkt(punkt.x + 1, punkt.y - 1));
		skoki.add(new Punkt(punkt.x - 1, punkt.y + 1));
		return usunZlePunkty(skoki);
	}
	
	private Collection<Punkt> usunZlePunkty(final Collection<Punkt> punkty)
	{
		final Collection<Punkt> punktyPrawidlowe = new LinkedList<>();
		for (final Punkt punkt : punkty)
		{
			if (punkt.x >= 0 && punkt.y >= 0 && punkt.x < SIZE && punkt.y < SIZE)
			{
				final int POLOWA = SIZE / 2;
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
}