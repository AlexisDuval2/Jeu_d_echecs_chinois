
package xiangqi;

//////////////////////////////////
// Classe Intersection
//////////////////////////////////
public class Intersection
{
	private Piece p;
	
	//////////////////////////////////
	// méthode constructeur (1)
	//////////////////////////////////
	public Intersection()
	{
		this.p = null;
	}
	
	//////////////////////////////////
	// méthode constructeur (2)
	//////////////////////////////////
	public Intersection(Piece p)
	{
		this.p = p;
	}
	
	//////////////////////////////////
	// méthodes d'accès
	//////////////////////////////////
	public Piece getPiece()
	{ return p; }

	public void setPiece(Piece p)
	{ this.p = p; }
	
	//////////////////////////////////
	// méthode estOccupee
	//////////////////////////////////
	public boolean estOccupee()
	{
		if (p == null)
			return false;
		
		return true;
	}

	//////////////////////////////////
	// méthode estOccupeeParMemeCouleur
	//////////////////////////////////
	public boolean estOccupeeParMemeCouleur(String couleur)
	{
		if (estOccupee() == true  &&  p.getCouleur() == couleur)
			return true;
			
		return false;
	}
}


