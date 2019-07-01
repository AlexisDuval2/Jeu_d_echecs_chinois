
package xiangqi;

public class Elephant extends Piece
{
	//////////////////////////////////
	// méthode constructeur
	//////////////////////////////////
	public Elephant(String nom, String couleur)
	{ super (nom, couleur);	}

	
	//////////////////////////////////
	// méthode estValide
	//////////////////////////////////
	@Override
	public boolean estValide (Position depart, Position arrivee)
	{
		// Ne peut pas traverser la rivière (dépendant de sa couleur)
		// Se déplace de deux intersections verticales à la fois

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


