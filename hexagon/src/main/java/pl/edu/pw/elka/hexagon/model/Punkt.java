package pl.edu.pw.elka.hexagon.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Michał Żurawki
 */
class Punkt
{
	int x;
	int y;

	Punkt(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	Collection<Punkt> podajSasiadow()
	{
		final Collection<Punkt> sasiedzi = new LinkedList<>();
		sasiedzi.add(new Punkt(x - 1, y));
		sasiedzi.add(new Punkt(x + 1, y));
		sasiedzi.add(new Punkt(x - 1, y - 1));
		sasiedzi.add(new Punkt(x + 1, y + 1));
		sasiedzi.add(new Punkt(x, y - 1));
		sasiedzi.add(new Punkt(x, y + 1));
		return sasiedzi;
	}

	Collection<Punkt> podajSkoki()
	{
		final Collection<Punkt> skoki = new LinkedList<>();
		skoki.add(new Punkt(x - 2, y));
		skoki.add(new Punkt(x + 2, y));
		skoki.add(new Punkt(x - 2, y - 1));
		skoki.add(new Punkt(x + 2, y + 1));
		skoki.add(new Punkt(x - 2, y - 2));
		skoki.add(new Punkt(x + 2, y + 2));
		skoki.add(new Punkt(x - 1, y - 2));
		skoki.add(new Punkt(x + 1, y + 2));
		skoki.add(new Punkt(x, y - 2));
		skoki.add(new Punkt(x, y + 2));
		skoki.add(new Punkt(x + 1, y - 1));
		skoki.add(new Punkt(x - 1, y + 1));
		return skoki;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Punkt other = (Punkt) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
