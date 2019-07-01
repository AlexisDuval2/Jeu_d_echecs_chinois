
package xiangqi;

public class Roi extends Piece
{
	//////////////////////////////////
	// m�thode constructeur
	//////////////////////////////////
	public Roi(String nom, String couleur)
	{ super (nom, couleur);	}


	//////////////////////////////////
	// m�thode estValide
	//////////////////////////////////
	@Override
	public boolean estValide (Position depart, Position arrivee)
	{
		// Il se d�place horizontalement ou verticalement de un seul espace
		// Doit demeurer dans son palais (d�pendant de sa couleur)

		boolean couleurNoire = getCouleur() == "noir";
		boolean couleurRouge = getCouleur() == "rouge";

		boolean resteDansLaZoneDuRoiNoir
		= arrivee.getLigne() >= 0 && arrivee.getLigne() <= 2
		&& arrivee.getColonne() >= 3 && arrivee.getColonne() <= 5;

		boolean resteDansLaZoneDuRoiRouge
		= arrivee.getLigne() >= 7 && arrivee.getLigne() <= 9
		&& arrivee.getColonne() >= 3 && arrivee.getColonne() <= 5;

		boolean normeOk = norme(depart,arrivee) == 1 || norme(depart,arrivee) == 2;

		if ((couleurNoire && resteDansLaZoneDuRoiNoir && normeOk)
				|| (couleurRouge && resteDansLaZoneDuRoiRouge && normeOk)
				|| (norme(depart, arrivee) == 0))
			return true;

		return false;
	}
}


