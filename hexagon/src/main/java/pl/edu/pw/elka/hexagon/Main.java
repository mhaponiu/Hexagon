package pl.edu.pw.elka.hexagon;

import pl.edu.pw.elka.hexagon.model.Model;
import pl.edu.pw.elka.hexagon.widok.Widok;

public class Main
{
	public static void main(final String[] args)
	{
		final Model model = new Model();
		final Widok widok = new Widok(model);
		model.setWidok(widok);
		widok.wyswietl();
		model.inicjalizujPlansze();
	}
}
