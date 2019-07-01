
package xiangqi;

public class Mandarin extends Piece
{
	//////////////////////////////////
	// méthode constructeur
	//////////////////////////////////
	public Mandarin(String nom, String couleur)
	{ super (nom, couleur);	}

	//////////////////////////////////
	// méthode estValide
	//////////////////////////////////
	@Override
	public boolean estValide (Position depart, Position arrivee)
	{
		// Il se déplace d’une intersection en diagonale à la fois
		// Lui aussi doit demeurer dans son palais d’origine
		
		boolean couleurNoire = getCouleur() == "noir";
		boolean couleurRouge = getCouleur() == "rouge";

		boolean resteDansLaZoneDuRoiNoir
		= arrivee.getLigne() >= 0 && arrivee.getLigne() <= 2
		&& arrivee.getColonne() >= 3 && arrivee.getColonne() <= 5;

		boolean resteDansLaZoneDuRoiRouge
		= arrivee.getLigne() >= 7 && arrivee.getLigne() <= 9
		&& arrivee.getColonne() >= 3 && arrivee.getColonne() <= 5;
		
		if ((couleurNoire && resteDansLaZoneDuRoiNoir && norme(depart,arrivee)==2)
				|| (couleurRouge && resteDansLaZoneDuRoiRouge && norme(depart,arrivee)==2)
				|| (norme(depart, arrivee) == 0))
			return true;

		return false;
	}
}


