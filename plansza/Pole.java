package plansza;

import java.awt.*;
import java.awt.geom.Point2D;

public class Pole extends Polygon {
	private static final int SKALA = 30; //powinna być parzysta wielokrotnosc 3; odległość od środka polygonu do wierzchołka na osi x
	private Point2D.Float pkt;
	private Point2D.Float pozycja;

	public Pole(int x, int y)
	{
		super(
			new int[] {
				x - SKALA,
				x - SKALA/2,
				x + SKALA/2,
				x + SKALA,
				x + SKALA/2,
				x - SKALA/2
				},
			new int[] {
				y,
				y + SKALA,
				y + SKALA,
				y,
				y - SKALA,
				y - SKALA
				}, 6
		);
		pkt = new Point2D.Float(x, y);		
	}
	
	public Pole(Point2D.Float point)
	{
		this((int)point.getX(), (int)point.getY());
	}
	
	public Pole(Pole pole, int x, int y)
	{
		this((int)pole.getPkt().getX() + x, (int)pole.getPkt().getY() + y);
	}
	
	public static Pole newPrawoGora(Pole pole)
	{
		int x = (int)(1.5F * (float)SKALA);
		return new Pole(pole, x, -SKALA);
	}
	
	public static Pole newPrawoDol(Pole pole)
	{
		int x = (int)(1.5F * (float)SKALA);
		return new Pole(pole, x, SKALA);
	}
	
	public static Pole newLewoGora(Pole pole)
	{
		int x = (int)(-1.5F * (float)SKALA);
		return new Pole(pole, x, -SKALA);
	}
	
	public static Pole newLewoDol(Pole pole)
	{
		int x = (int)(-1.5F * (float)SKALA);
		return new Pole(pole, x, SKALA);
	}
	public static Pole newDol(Pole pole)
	{
		return new Pole(pole, 0, (int)2*SKALA);
	}
	
	public static int getSkala(){
		return SKALA;
	}

	public Point2D.Float getPkt()
	{
		return pkt;
	}
	public Point2D setPozycja(int x, int y)
	{
		pozycja = new Point2D.Float(x, y);
		return pozycja;
	}
	public Point2D getPozycja()
	{
		return pozycja;
	}
}
