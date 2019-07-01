
package xiangqi;

public class Position 
{
	private int ligne ; // tjrs entre 0 et 9 voir schéma
	private int colonne; // tjrs entre 0 et 8

	
	//////////////////////////////////
	// méthode constructeur
	//////////////////////////////////
	public Position(int ligne, int colonne)
	{
		this.ligne = ligne;
		this.colonne= colonne;
	}

	
	//////////////////////////////////
	// méthode d'accès
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


