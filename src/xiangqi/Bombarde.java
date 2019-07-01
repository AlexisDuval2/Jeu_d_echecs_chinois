
package xiangqi;

public class Bombarde extends Piece
{
	//////////////////////////////////
	// m�thode constructeur
	//////////////////////////////////
	public Bombarde(String nom, String couleur)
	{
		super(nom, couleur);
	}

	//////////////////////////////////
	// m�thode estValide
	//////////////////////////////////
	@Override
	public boolean estValide (Position depart, Position arrivee)
	{
		// Pour le TP1, elle se d�place comme un char, donc nombre illimit�
		// en autant que ce soit sur la m�me ligne ou la m�me colonne
		
		if (depart.getLigne() == arrivee.getLigne())
			return true;
		else if (depart.getColonne() == arrivee.getColonne())
			return true;
		else if (norme(depart, arrivee) == 0) // s'il ne bouge pas
			return true;

		return false;
	}
}


