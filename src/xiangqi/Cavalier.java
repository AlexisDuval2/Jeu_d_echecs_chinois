
package xiangqi;

public class Cavalier extends Piece
{
	//////////////////////////////////
	// m�thode constructeur
	//////////////////////////////////
	public Cavalier(String nom, String couleur)
	{ super (nom, couleur);	}

	
	//////////////////////////////////
	// m�thode estValide
	//////////////////////////////////
	@Override
	public boolean estValide (Position depart, Position arrivee)
	{
		// Il se d�place d�abord d�une intersection en ligne droite,
		// puis d�une case en diagonale.

		if (this.norme(depart, arrivee) == 5) // la norme est toujours de 5
			return true;
		else if (norme(depart, arrivee) == 0) // s'il ne bouge pas
			return true;

		return false;
	}
}

