
package xiangqi;

public class Position 
{
	private int ligne ; // tjrs entre 0 et 9 voir sch�ma
	private int colonne; // tjrs entre 0 et 8

	
	//////////////////////////////////
	// m�thode constructeur
	//////////////////////////////////
	public Position(int ligne, int colonne)
	{
		this.ligne = ligne;
		this.colonne= colonne;
	}

	
	//////////////////////////////////
	// m�thode d'acc�s
	//////////////////////////////////
	public int getLigne ()
	{ return ligne; }
	
	public void setLigne (int ligne)
	{ this.ligne = ligne; }
	

	public int getColonne ()
	{ return colonne; }
	
	public void setColonne ( int colonne )
	{ this.colonne = colonne; }
}


