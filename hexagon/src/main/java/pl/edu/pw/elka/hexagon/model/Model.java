package pl.edu.pw.elka.hexagon.model;

import java.util.Collection;
import java.util.LinkedList;

import pl.edu.pw.elka.hexagon.widok.Kolor;
import pl.edu.pw.elka.hexagon.widok.Widok;

/**
 * @author Michał Żurawki
 */
public class Model
{
	private static final int ROZMIAR = 9;
	private final Collection<Punkt> zaznaczone = new LinkedList<>();
	private Plansza plansza = new Plansza(ROZMIAR);
	private Widok widok;
	private Punkt ostatniKliknietyPunkt;

	public void zaznaczono(int x, int y)
	{
		final Punkt kliknietyPunkt = new Punkt(x, y);
		if (pierwszeKlikniecie())
		{
			if (wybranoCzerwonego(kliknietyPunkt))
			{
				ostatniKliknietyPunkt = kliknietyPunkt;
				podswietlDostepnePola(kliknietyPunkt);
			}
		}
		else if (moznaWybracPunkt(kliknietyPunkt))
		{
			zaznaczone.clear();
			if (kliknietyPunkt.equals(ostatniKliknietyPunkt))
			{
				wyslijPlansze();
				return;
			}
			final Ruch ruchGracza = new Ruch(ostatniKliknietyPunkt, kliknietyPunkt);
			plansza.przesunCzerwonego(ruchGracza);
			wyslijPlansze();
			ruchKomputera();
			wyslijPlansze();
		}
	}

	public void inicjalizujPlansze()
	{
		plansza.inicjalizujPlansze();
		wyslijPlansze();
	}

	public void setWidok(final Widok widok)
	{
		this.widok = widok;
	}
	
	private void ruchKomputera()
	{
		final Drzewo drzewo = new Drzewo(plansza, 5);
		plansza = drzewo.buduj();
//		System.out.println(drzewo.buduj());
//		System.out.println(drzewo);
	}
	
	private boolean moznaWybracPunkt(final Punkt punkt)
	{
		return zaznaczone.contains(punkt);
	}

	private void podswietlDostepnePola(final Punkt punktDoPodswietlenia)
	{
		zaznaczone.add(punktDoPodswietlenia);
		widok.zapal(Kolor.ZAZNACZONY, punktDoPodswietlenia.x, punktDoPodswietlenia.y);

		final Collection<Punkt> sasiedzi = plansza.podajWolnychSasiadow(punktDoPodswietlenia);
		zaznaczone.addAll(sasiedzi);
		for (final Punkt punkt : sasiedzi)
		{
			widok.zapal(Kolor.ZIELONY, punkt.x, punkt.y);
		}

		final Collection<Punkt> skoki = plansza.podajDozwoloneSkoki(punktDoPodswietlenia);
		zaznaczone.addAll(skoki);
		for (final Punkt punkt : skoki)
		{
			widok.zapal(Kolor.ZOLTY, punkt.x, punkt.y);
		}
	}

	private void wyslijPlansze()
	{
		widok.czysc();
		for (final Punkt punkt : Plansza.podajDziury())
		{
			widok.zapal(Kolor.PUSTY, punkt.x, punkt.y);
		}
		for (final Punkt punkt : plansza.podajCzerwone())
		{
			widok.zapal(Kolor.GRACZ1, punkt.x, punkt.y);
		}
		for (final Punkt punkt : plansza.podajNiebieskie())
		{
			widok.zapal(Kolor.GRACZ2, punkt.x, punkt.y);
		}
	}

	private boolean pierwszeKlikniecie()
	{
		return zaznaczone.isEmpty();
	}

	private boolean wybranoCzerwonego(final Punkt punkt)
	{
		return plansza.podajCzerwone().contains(punkt);
	}
}