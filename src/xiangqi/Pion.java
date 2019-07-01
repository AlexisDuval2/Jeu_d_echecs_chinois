
package xiangqi;

public class Pion extends Piece
{
	//////////////////////////////////
	// méthode constructeur
	//////////////////////////////////
	public Pion(String nom, String couleur)
	{
		super(nom, couleur);
	}

	//////////////////////////////////
	// méthode estValide
	//////////////////////////////////
	@Override
	public boolean estValide (Position depart, Position arrivee)
	{
		// De son côté de sa rivière, il avance d’une intersection à la fois en ligne droite.
		// De l’autre côté de la rivière, il peut avancer d’une intersection à la fois en
		// ligne droite ou sur les côtés.
		// Il ne peut pas reculer contrairement aux autres pièces.
		
		boolean couleurNoire = getCouleur() == "noir";
		boolean couleurRouge = getCouleur() == "rouge";
		boolean zoneNoire = depart.getLigne() >= 0  &&  depart.getLigne() <= 4;
		boolean zoneRouge = depart.getLigne() >= 5  &&  depart.getLigne() <= 9;

		boolean noirAvanceDeUn
		= (arrivee.getLigne() - depart.getLigne() == 1)
		&& (arrivee.getColonne() == depart.getColonne());
		// test pr s'assurer que le pion noir avance de un SANS CHANGER DE COLONNE

		boolean rougeAvanceDeUn
		= (arrivee.getLigne() - depart.getLigne() == - 1)
		&& (arrivee.getColonne() == depart.getColonne());
		// test pr s'assurer que le pion rouge avance de un SANS CHANGER DE COLONNE
		
		boolean pionAvanceGaucheDroite
		= (norme(depart,arrivee) == 1)
		&& ((arrivee.getColonne() - depart.getColonne() == 1)
				|| (arrivee.getColonne() - depart.getColonne() == - 1));
		// test pr s'assurer qu'un pion se tasse de un horizontalement (peu importe sa couleur)
		
		if ((couleurNoire && zoneNoire && noirAvanceDeUn)
				|| (couleurNoire && zoneRouge && (noirAvanceDeUn || pionAvanceGaucheDroite))
				|| (couleurRouge && zoneRouge && rougeAvanceDeUn)
				|| (couleurRouge && zoneNoire && (rougeAvanceDeUn || pionAvanceGaucheDroite))
				|| (norme(depart,arrivee) == 0))
			return true;
		
		return false;
	}
}


