package pl.edu.pw.elka.hexagon.plansza;

import java.awt.*;
import java.awt.geom.Point2D;

class Pole extends Polygon
{
	private static final long serialVersionUID = 1L;
	/** powinna być parzysta wielokrotnosc 3; odległość od środka polygonu do wierzchołka na osi x. */
	private static final int SKALA = 30;
	private Point2D.Float pkt;
	private Point2D.Float pozycja;

	Pole(int x, int y)
	{
		super(new int[] { x - SKALA, x - SKALA / 2, x + SKALA / 2, x + SKALA, x + SKALA / 2, x - SKALA / 2 },
				new int[] { y, y + SKALA, y + SKALA, y, y - SKALA, y - SKALA }, 6);
		pkt = new Point2D.Float(x, y);
	}

	Pole(Point2D.Float point)
	{
		this((int) point.getX(), (int) point.getY());
	}

	Pole(Pole pole, int x, int y)
	{
		this((int) pole.getPkt().getX() + x, (int) pole.getPkt().getY() + y);
	}

	static Pole newPrawoGora(Pole pole)
	{
		int x = (int) (1.5F * (float) SKALA);
		return new Pole(pole, x, -SKALA);
	}

	static Pole newPrawoDol(Pole pole)
	{
		int x = (int) (1.5F * (float) SKALA);
		return new Pole(pole, x, SKALA);
	}

	static Pole newLewoGora(Pole pole)
	{
		int x = (int) (-1.5F * (float) SKALA);
		return new Pole(pole, x, -SKALA);
	}

	static Pole newLewoDol(Pole pole)
	{
		int x = (int) (-1.5F * (float) SKALA);
		return new Pole(pole, x, SKALA);
	}

	static Pole newDol(Pole pole)
	{
		return new Pole(pole, 0, (int) 2 * SKALA);
	}

	static int getSkala()
	{
		return SKALA;
	}

	Point2D.Float getPkt()
	{
		return pkt;
	}

	Point2D setPozycja(int x, int y)
	{
		pozycja = new Point2D.Float(x, y);
		return pozycja;
	}

	Point2D getPozycja()
	{
		return pozycja;
	}
}
