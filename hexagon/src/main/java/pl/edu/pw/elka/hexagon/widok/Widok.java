package pl.edu.pw.elka.hexagon.widok;

import pl.edu.pw.elka.hexagon.model.Model;

public class Widok
{
	private final PlanszaComponent plansza = new PlanszaComponent();
	private final Model model;
	
	public Widok(final Model model)
	{
		plansza.setWidok(this);
		this.model = model;
	}
	
	public void wyswietl()
	{
		plansza.wyswietl();
	}

	public void zapal(final Kolor zaznaczony, int x, int y)
	{
		plansza.zapal(zaznaczony, x, y);
	}

	public void zgasWszystkie(final Kolor kolor)
	{
		plansza.zgasWszystkie(kolor);
	}
	
	public void zgasPole(int x, int y)
	{
		plansza.zgasPionki1(x, y);
	}

	public void czysc()
	{
		plansza.czysc();
		plansza.repaint();
	}

	void click(int x, int y)
	{
		model.zaznaczono(x, y);
	}
}
