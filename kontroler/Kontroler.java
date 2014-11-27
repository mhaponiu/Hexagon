package kontroler;

import java.awt.EventQueue;
import java.awt.geom.Point2D;

import plansza.Click;
import plansza.Kolor;
import plansza.PlanszaComponent;

import javax.swing.*;

public class Kontroler implements Click{

	static PlanszaComponent plansza;
	
	private void click(int x, int y) {
		System.out.printf("Kontroler: kliknięto na [%d][%d]\n", x, y);
	}
	
	public Kontroler(){
		plansza = new PlanszaComponent();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Kontroler kontroler = new Kontroler();
				
				JFrame frame = new JFrame();
				frame.add(plansza);
				frame.pack();
				frame.setTitle("Hexagon!");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}
		});
	}
}