
package xiangqi;

public class Elephant extends Piece
{
	//////////////////////////////////
	// m�thode constructeur
	//////////////////////////////////
	public Elephant(String nom, String couleur)
	{ super (nom, couleur);	}

	
	//////////////////////////////////
	// m�thode estValide
	//////////////////////////////////
	@Override
	public boolean estValide (Position depart, Position arrivee)
	{
		// Ne peut pas traverser la rivi�re (d�pendant de sa couleur)
		// Se d�place de deux intersections verticales � la fois

		if (this.norme(depart, arrivee) == 8)
		// la norme est toujours de 8
		{
			if (getCouleur() == "noir")
			{
				if (arrivee.getLigne() >= 0  ||  arrivee.getLigne() <= 4)
					return true;
			}
			else // alors c'est "rouge"
			{
				if (arrivee.getLigne() >= 5  ||  arrivee.getLigne() <= 9)
					return true;
			}
		}
		else if (norme(depart, arrivee) == 0) // s'il ne bouge pas
			return true;

		return false;
	}
}


