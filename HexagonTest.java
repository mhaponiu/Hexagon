import java.awt.EventQueue;
import javax.swing.JFrame;
import kontroler.Kontroler;
import plansza.PlanszaComponent;

public class HexagonTest {
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Kontroler kontroler = new Kontroler();
				PlanszaComponent plansza = new PlanszaComponent(kontroler);
				kontroler.rejestrujPlansze(plansza);
				
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
