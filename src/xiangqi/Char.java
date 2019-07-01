
package xiangqi;

public class Char extends Piece
{
	//////////////////////////////////
	// méthode constructeur
	//////////////////////////////////
	public Char(String nom, String couleur)
	{ super(nom, couleur); }

	
	//////////////////////////////////
	// méthode estValide
	//////////////////////////////////
	@Override
	public boolean estValide (Position depart, Position arrivee)
	{
		// nombre illimité en autant que ce soit sur la même ligne ou
		// la même colonne
		
		if (depart.getLigne() == arrivee.getLigne())
			return true;
		else if (depart.getColonne() == arrivee.getColonne())
			return true;
		else if(norme(depart, arrivee) == 0) // s'il ne bouge pas
			return true;

		return false;
	}
}


