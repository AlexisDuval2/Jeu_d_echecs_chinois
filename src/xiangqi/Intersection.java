
package xiangqi;

//////////////////////////////////
// Classe Intersection
//////////////////////////////////
public class Intersection
{
	private Piece p;
	
	//////////////////////////////////
	// m�thode constructeur (1)
	//////////////////////////////////
	public Intersection()
	{
		this.p = null;
	}
	
	//////////////////////////////////
	// m�thode constructeur (2)
	//////////////////////////////////
	public Intersection(Piece p)
	{
		this.p = p;
	}
	
	//////////////////////////////////
	// m�thodes d'acc�s
	//////////////////////////////////
	public Piece getPiece()
	{ return p; }

	public void setPiece(Piece p)
	{ this.p = p; }
	
	//////////////////////////////////
	// m�thode estOccupee
	//////////////////////////////////
	public boolean estOccupee()
	{
		if (p == null)
			return false;
		
		return true;
	}

	//////////////////////////////////
	// m�thode estOccupeeParMemeCouleur
	//////////////////////////////////
	public boolean estOccupeeParMemeCouleur(String couleur)
	{
		if (estOccupee() == true  &&  p.getCouleur() == couleur)
			return true;
			
		return false;
	}
}


