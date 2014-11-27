package kontroler;

import plansza.Click;
import plansza.PlanszaComponent;

public class Kontroler implements Click{

	private static PlanszaComponent plansza;
	
	public void click(int x, int y) {
		System.out.printf("Kontroler: kliknięto na [%d][%d]\n", x, y);
	}
	public void rejestrujPlansze(PlanszaComponent plansza){
		this.plansza = plansza;
	}
}