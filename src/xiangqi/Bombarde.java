
package xiangqi;

public class Bombarde extends Piece
{
	//////////////////////////////////
	// méthode constructeur
	//////////////////////////////////
	public Bombarde(String nom, String couleur)
	{
		super(nom, couleur);
	}

	//////////////////////////////////
	// méthode estValide
	//////////////////////////////////
	@Override
	public boolean estValide (Position depart, Position arrivee)
	{
		// Pour le TP1, elle se déplace comme un char, donc nombre illimité
		// en autant que ce soit sur la même ligne ou la même colonne
		
		if (depart.getLigne() == arrivee.getLigne())
			return true;
		else if (depart.getColonne() == arrivee.getColonne())
			return true;
		else if (norme(depart, arrivee) == 0) // s'il ne bouge pas
			return true;

		return false;
	}
}


