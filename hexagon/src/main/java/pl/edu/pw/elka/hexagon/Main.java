package pl.edu.pw.elka.hexagon;

import pl.edu.pw.elka.hexagon.model.Model;
import pl.edu.pw.elka.hexagon.plansza.PlanszaComponent;

public class Main
{
	public static void main(final String[] args)
	{
		final Model model = new Model();
		final PlanszaComponent plansza = new PlanszaComponent(model);
		model.rejestrujPlansze(plansza);
		plansza.wyswietl();
		model.inicjalizujPlansze();
	}
}
