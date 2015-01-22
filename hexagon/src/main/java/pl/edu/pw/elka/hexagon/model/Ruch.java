package pl.edu.pw.elka.hexagon.model;

/**
 * @author Michał Żurawski
 */
class Ruch
{
	final Punkt skad;
	final Punkt dokad;

	Ruch(final Punkt skad, final Punkt dokad)
	{
		this.skad = skad;
		this.dokad = dokad;
	}
}
