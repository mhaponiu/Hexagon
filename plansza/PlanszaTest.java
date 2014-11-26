package plansza;

import java.awt.*;

import javax.swing.*;

public class PlanszaTest extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				System.out.println("RUN!!");
				JFrame frame = new PlanszaTest();
				frame.setTitle("PlanszaTEST");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}

		});

	}

	public PlanszaTest() {
		PlanszaComponent plansza = new PlanszaComponent();
		
		plansza.zapal(Kolor.GRACZ1, 6, 0);
		plansza.zapal(Kolor.ZAZNACZONY, 6, 0);
		plansza.zapal(Kolor.GRACZ2, 5, 1);
		plansza.zapal(Kolor.ZIELONY, 5, 0);
		plansza.zapal(Kolor.ZIELONY, 6, 1);
		plansza.zapal(Kolor.ZIELONY, 7, 0);
		plansza.zapal(Kolor.ZOLTY, 4, 0);
		plansza.zapal(Kolor.ZOLTY, 4, 1);
		plansza.zapal(Kolor.ZOLTY, 4, 2);
		plansza.zapal(Kolor.ZOLTY, 5, 2);
		plansza.zapal(Kolor.ZOLTY, 6, 2);
		plansza.zapal(Kolor.ZOLTY, 7, 1);
		plansza.zapal(Kolor.ZOLTY, 8, 0);
		
		plansza.zapal(Kolor.GRACZ1, 1, 1);
		plansza.zapal(Kolor.GRACZ1, 2, 1);
		plansza.zapal(Kolor.ZAZNACZONY, 2, 1);
		plansza.zapal(Kolor.GRACZ2, 1, 2);
		plansza.zapal(Kolor.GRACZ2, 2, 2);
		plansza.zapal(Kolor.ZAZNACZONY, 2, 2);
		plansza.zapal(Kolor.ZIELONY, 1, 3);
		plansza.zapal(Kolor.ZOLTY, 1, 3);
		plansza.zapal(Kolor.ZIELONY, 1, 4);
		
//		plansza.zgasWszystkie(Kolor.ZIELONY);
//		plansza.zgasWszystkie(Kolor.ZOLTY);
//		plansza.zgasWszystkie(Kolor.ZAZNACZONY);
//		plansza.zgasWszystkie(Kolor.GRACZ1);
//		plansza.zgasWszystkie(Kolor.GRACZ2);
//		plansza.zgasWszystkie(Kolor.PUSTY);
		
//		plansza.reset();
		
		add(plansza);
		pack();
	}

}
