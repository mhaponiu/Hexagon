package plansza;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

public class PlanszaComponent extends JComponent {
	Click kontroler;

	private static final int DEFAULT_WIDTH = 560;
	private static final int DEFAULT_HEIGHT = 600;
	private static final int PIONEK = Pole.getSkala();

	private Pole[][] pola;
	private Pole roboczy;
	private ArrayList<Pole> zielone;
	private ArrayList<Pole> zolte;
	private ArrayList<Pole> zaznaczone;
	private ArrayList<Pole> puste;
	private ArrayList<Point2D> pionki1;
	private ArrayList<Point2D> pionki2;

	private static final Color SZARY = new Color(198, 206, 206);
	private static final Color JASNYZOLTY = new Color(255, 255, 130);
	private static final Color JASNYZIELONY = new Color(159, 251, 160);
	private static final Color CIEMNYSZARY = new Color(160, 160, 160);

	public PlanszaComponent() {
		pola = createPlanszaHexagon();
		zielone = new ArrayList<>();
		zolte = new ArrayList<>();
		zaznaczone = new ArrayList<>();
		puste = new ArrayList<>();
		pionki1 = new ArrayList<>();
		pionki2 = new ArrayList<>();
		puste.add(pola[4][3]);
		puste.add(pola[3][4]);
		puste.add(pola[5][4]);

		addMouseListener(new MouseHandler());
	}

	public PlanszaComponent(Click kontr) {
		super();
		kontroler = kontr;
	}
	
	public void setKontroler(Click kontr){
		kontroler = kontr;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// rysuje fill'e planszy
		g2.setPaint(SZARY);
		for (Pole[] wiersz : pola) {
			for (Pole kostka : wiersz) {
				g2.fill(kostka);
			}
		}
		// rysuje wszystkie zielone
		g2.setPaint(JASNYZIELONY);
		for (Pole p : zielone)
			g2.fill(p);

		// rysuje wszystkie zielone
		g2.setPaint(JASNYZOLTY);
		for (Pole p : zolte)
			g2.fill(p);

		// rysuje wszystkie ciemneszare
		g2.setPaint(CIEMNYSZARY);
		for (Pole p : zaznaczone)
			g2.fill(p);

		// rysuje wszystkie puste
		g2.setPaint(new Color(237, 237, 237));
		for (Pole p : puste)
			g2.fill(p);

		// rysuje krawędzie planszy -powinno być na końcu funkcji
		g2.setPaint(Color.BLACK);
		for (Pole[] wiersz : pola) {
			for (Pole kostka : wiersz) {
				g2.draw(kostka);
			}
		}
		// rysuje pionki
		g2.setPaint(Color.RED);
		for (Point2D p : pionki1) {
			g2.fillOval((int) p.getX() - (PIONEK / 2), (int) p.getY()
					- (PIONEK / 2), PIONEK, PIONEK);
		}
		g2.setPaint(Color.BLUE);
		for (Point2D p : pionki2) {
			g2.fillOval((int) p.getX() - (PIONEK / 2), (int) p.getY()
					- (PIONEK / 2), PIONEK, PIONEK);
		}
		// obrysy pionkow
		g2.setPaint(Color.BLACK);
		for (Point2D p : pionki1) {
			g2.drawOval((int) p.getX() - (PIONEK / 2), (int) p.getY()
					- (PIONEK / 2), PIONEK, PIONEK);
		}
		g2.setPaint(Color.BLACK);
		for (Point2D p : pionki2) {
			g2.drawOval((int) p.getX() - (PIONEK / 2), (int) p.getY()
					- (PIONEK / 2), PIONEK, PIONEK);
		}

	}

	private Pole[][] createPlanszaHexagon() {
		Pole[][] polehex = new Pole[9][];

		// tworzę postrzępioną tablicę
		for (int i = 0; i <= 4; i++) {
			polehex[i] = new Pole[i + 5];
			System.out.println("wiersz:" + (i + 1) + "  komórek:" + (i + 5));
		}
		// druga połowa
		for (int i = 5, k = 0; i <= 8; i++, k++) {
			polehex[i] = new Pole[8 - k];
			System.out.println("wiersz:" + (i + 1) + "  komórek:" + (8 - k));
		}

		// uzupełniam tablice polami - brzydki kod ale działa; nie czytać
		Pole init = new Pole(new Point2D.Float(100, 6 * Pole.getSkala()));
		// pierwsze 5 wierszy najpierw
		polehex[0][0] = init;
		polehex[0][0].setPozycja(0, 0);
		Pole tmp = init;
		for (int k = 0; k <= 4; k++) {
			for (int i = 0; i < k + 5; i++) {
				if (k == 0 && i == 0)
					continue;
				if (i == 0)
					continue;
				polehex[k][i] = Pole.newPrawoGora(tmp);
				polehex[k][i].setPozycja(k, i);
				tmp = polehex[k][i];
			}
			if (k == 0) {
				polehex[1][0] = Pole.newDol(polehex[0][0]);
				polehex[1][0].setPozycja(1, 0);
				tmp = polehex[1][0];
				continue;
			}
			if (k == 4)
				break;
			polehex[k + 1][0] = Pole.newDol(polehex[k][0]);
			polehex[k + 1][0].setPozycja(k + 1, 0);
			tmp = polehex[k + 1][0];
		}
		// teraz druga połowa od 5 wiersza do 9
		polehex[5][0] = Pole.newPrawoDol(polehex[4][0]);
		polehex[5][0].setPozycja(5, 0);
		tmp = polehex[5][0];
		for (int k = 5; k <= 8; k++) {
			for (int i = 0; i < 13 - k; i++) {
				if (i == 0)
					continue;
				polehex[k][i] = Pole.newPrawoGora(tmp);
				polehex[k][i].setPozycja(k, i);
				tmp = polehex[k][i];
			}
			if (k == 5) {
				polehex[6][0] = Pole.newPrawoDol(polehex[5][0]);
				polehex[6][0].setPozycja(6, 0);
				tmp = polehex[6][0];
				continue;
			}
			if (k == 8)
				break;
			polehex[k + 1][0] = Pole.newPrawoDol(polehex[k][0]);
			polehex[k + 1][0].setPozycja(k + 1, 0);
			tmp = polehex[k + 1][0];
		}

		return polehex;
	}

	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	private Pole find(Point2D p) {
		for (Pole[] wiersz : pola) {
			for (Pole kostka : wiersz) {
				if (kostka.contains(p))
					return kostka;
			}
		}
		return null;
	}

	public void zapal(Kolor k, int x, int y) {
		// usuwam z list jakby pole juz mialo jakis kolor zebym mogl je
		// zamalować
		zolte.remove(pola[x][y]);
		zielone.remove(pola[x][y]);
		puste.remove(pola[x][y]);
		zaznaczone.remove(pola[x][y]);
		switch (k) {
		case ZIELONY:
			zielone.add(pola[x][y]);
			break;
		case ZOLTY:
			zolte.add(pola[x][y]);
			break;
		case ZAZNACZONY:
			zaznaczone.add(pola[x][y]);
			break;
		case GRACZ1:
			if (pionki2.contains(pola[x][y].getPkt()))
				pionki2.remove(pola[x][y].getPkt());
			pionki1.add(pola[x][y].getPkt());
			break;
		case GRACZ2:
			if (pionki1.contains(pola[x][y].getPkt()))
				pionki1.remove(pola[x][y].getPkt());
			pionki2.add(pola[x][y].getPkt());
			break;
		case PUSTY:
			puste.add(pola[x][y]);
			break;
		}
		repaint();
	}

	public void zgasWszystkie(Kolor k) { // np zgasWszystkie(Kolor.ZIELONY);
		switch (k) {
		case ZIELONY:
			zielone.clear();
			break;
		case ZOLTY:
			zolte.clear();
			break;
		case ZAZNACZONY:
			zaznaczone.clear();
			break;
		case GRACZ1:
			pionki1.clear();
			break;
		case GRACZ2:
			pionki2.clear();
			break;
		case PUSTY:
			puste.clear();
			break;
		}
		repaint();
	}

	public void reset() {
		zgasWszystkie(Kolor.ZIELONY);
		zgasWszystkie(Kolor.ZOLTY);
		zgasWszystkie(Kolor.ZAZNACZONY);
		zgasWszystkie(Kolor.GRACZ1);
		zgasWszystkie(Kolor.GRACZ2);
		zgasWszystkie(Kolor.PUSTY);
		puste.add(pola[4][3]);
		puste.add(pola[3][4]);
		puste.add(pola[5][4]);
		repaint();
	}

	private void click(int x, int y) {

		System.out.printf("PlanszaComponent: kliknięto na [%d][%d]\n", x, y);
		Point2D.Float pozycja = new Point2D.Float((int) x, (int) y);
		zapal(Kolor.GRACZ1, (int) pozycja.getX(), (int) pozycja.getY());
	}

	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			roboczy = find(event.getPoint());
			if (roboczy == null)
				System.out.println("kliknales poza planszą");
			else {
				try {
					kontroler.click((int) roboczy.getPozycja().getX(),
							(int) roboczy.getPozycja().getY());
				} catch (NullPointerException e) {
					click((int) roboczy.getPozycja().getX(), (int) roboczy
							.getPozycja().getY());
				}

			}
		}
	}
}
