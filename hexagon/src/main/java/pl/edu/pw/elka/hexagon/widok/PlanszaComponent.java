package pl.edu.pw.elka.hexagon.widok;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class PlanszaComponent extends JComponent
{
	private static final int ROZMIAR = 9;

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_WIDTH = 560;
	private static final int DEFAULT_HEIGHT = 600;
	private static final int PIONEK = Pole.getSkala();

	private Widok widok;
	private final Pole[][] pola = createPlanszaHexagon();
	private Pole roboczy;
	private final List<Pole> zielone = new ArrayList<>();
	private final List<Pole> zolte = new ArrayList<>();
	private final List<Pole> zaznaczone = new ArrayList<>();
	private final List<Pole> puste = new ArrayList<>();
	private final List<Point2D> pionki1 = new ArrayList<>();
	private final List<Point2D> pionki2 = new ArrayList<>();
	private final JFrame frame = new JFrame();

	private static final Color SZARY = new Color(198, 206, 206);
	private static final Color JASNYZOLTY = new Color(255, 255, 130);
	private static final Color JASNYZIELONY = new Color(159, 251, 160);
	private static final Color CIEMNYSZARY = new Color(160, 160, 160);

	public PlanszaComponent()
	{
		super();
		addMouseListener(new MouseHandler());
	}
	
	public void setWidok(final Widok widok)
	{
		this.widok = widok;
	}

	public void czysc()
	{
		zielone.clear();
		zolte.clear();
		zaznaczone.clear();
		puste.clear();
		pionki1.clear();
		pionki2.clear();
	}

	public void paintComponent(final Graphics g)
	{
		final Graphics2D g2 = (Graphics2D) g;
		// rysuje fill'e planszy
		g2.setPaint(SZARY);
		for (Pole[] wiersz : pola)
		{
			for (Pole kostka : wiersz)
			{
				if (kostka != null)
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
		for (Pole[] wiersz : pola)
		{
			for (Pole kostka : wiersz)
			{
				if (kostka != null)
					g2.draw(kostka);
			}
		}
		// rysuje pionki
		g2.setPaint(Color.RED);
		for (Point2D p : pionki1)
		{
			g2.fillOval((int) p.getX() - (PIONEK / 2), (int) p.getY() - (PIONEK / 2), PIONEK, PIONEK);
		}
		g2.setPaint(Color.BLUE);
		for (Point2D p : pionki2)
		{
			g2.fillOval((int) p.getX() - (PIONEK / 2), (int) p.getY() - (PIONEK / 2), PIONEK, PIONEK);
		}
		// obrysy pionkow
		g2.setPaint(Color.BLACK);
		for (Point2D p : pionki1)
		{
			g2.drawOval((int) p.getX() - (PIONEK / 2), (int) p.getY() - (PIONEK / 2), PIONEK, PIONEK);
		}
		g2.setPaint(Color.BLACK);
		for (Point2D p : pionki2)
		{
			g2.drawOval((int) p.getX() - (PIONEK / 2), (int) p.getY() - (PIONEK / 2), PIONEK, PIONEK);
		}

	}

	private Pole[][] createPlanszaHexagon()
	{
		final Pole[][] polehex = new Pole[ROZMIAR][ROZMIAR];
		final int POLOWA = ROZMIAR / 2 + 1;
		polehex[0][0] = new Pole(new Point2D.Float(100, 6 * Pole.getSkala()));
		polehex[0][0].setPozycja(0, 0);
		for (int i = 1; i < ROZMIAR; ++i)
		{
			polehex[i][0] = Pole.newDol(polehex[i - 1][0]);
			polehex[i][0].setPozycja(i, 0);
		}
		for (int i = 0; i < ROZMIAR; ++i)
		{
			for (int j = 1; j < ROZMIAR; ++j)
			{
				polehex[i][j] = Pole.newPrawoGora(polehex[i][j - 1]);
				polehex[i][j].setPozycja(i, j);
			}
		}
		for (int i = 0; i < POLOWA; ++i)
		{
			for (int j = POLOWA + i; j < ROZMIAR; ++j)
			{
				polehex[i][j] = null;
			}
		}
		for (int i = POLOWA; i < ROZMIAR; ++i)
		{
			for (int j = 0; j < i - (POLOWA - 1); ++j)
			{
				polehex[i][j] = null;
			}
		}

		return polehex;
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	private Pole find(Point2D p)
	{
		for (Pole[] wiersz : pola)
		{
			for (Pole kostka : wiersz)
			{
				if (kostka != null && kostka.contains(p))
					return kostka;
			}
		}
		return null;
	}

	public void zapal(Kolor k, int x, int y)
	{
		// usuwam z list jakby pole juz mialo jakis kolor zebym mogl je
		// zamalować
		zolte.remove(pola[x][y]);
		zielone.remove(pola[x][y]);
		puste.remove(pola[x][y]);
		zaznaczone.remove(pola[x][y]);
		switch (k)
		{
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
	
	public void zgasPionki1(int x, int y)
	{
		pionki1.remove(pola[x][y].getPkt());
	}

	public void zgasWszystkie(Kolor k)
	{ // np zgasWszystkie(Kolor.ZIELONY);
		switch (k)
		{
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

	public void reset()
	{
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

	public void wyswietl()
	{
		EventQueue.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				frame.add(PlanszaComponent.this);
				frame.pack();
				frame.setTitle("Hexagon!");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}
		});
	}

	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent event)
		{
			roboczy = find(event.getPoint());
			if (roboczy == null)
				System.out.println("PlanszaComponent: kliknales poza planszą");
			else
			{
				try
				{
					widok.click((int) roboczy.getPozycja().getX(), (int) roboczy.getPozycja().getY());
				}
				catch (NullPointerException e)
				{
					/*
					 * gdy nie mam podłączonego kontrolera, raczej do testów tylko
					 */
					int x = (int) roboczy.getPozycja().getX();
					int y = (int) roboczy.getPozycja().getY();
					System.out.printf("PlanszaComponent: kliknięto na [%d][%d]\n", x, y);
					zapal(Kolor.GRACZ1, x, y);
				}

			}
		}
	}
}
