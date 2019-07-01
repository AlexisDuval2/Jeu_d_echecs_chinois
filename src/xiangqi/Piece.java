
package xiangqi;

// classe abstraite Piece servant de point de d�part pour
// tous les types de pi�ces d'un jeu de Xiangqi

public abstract class Piece
{
	// nom de la pi�ce selon les conventions
	private String nom;

	// couleur de la pi�ce
	private String couleur;


	//////////////////////////////////
	// m�thode constructeur
	//////////////////////////////////
	public Piece (String nom, String couleur)
	// m�thode qui permet d'initialiser le nom et la couleur d'un objet Piece
	{
		setNom (nom);
		setCouleur (couleur);
	}


	//////////////////////////////////
	// m�thode d'acc�s
	//////////////////////////////////
	public String getNom()
	{ return nom; }
	
	public void setNom (String nom)
	{ this.nom = nom; }

	
	public String getCouleur()
	{ return couleur; }
	
	public void setCouleur (String couleur)
	{
		if (( couleur == "noir" ) || ( couleur == "rouge" ))
			this.couleur = couleur;
	}

	
	//////////////////////////////////
	// m�thode norme
	//////////////////////////////////
	public double norme (Position depart, Position arrivee)
	// m�thode qui calcule la norme math�matique entre deux Positions
	// et qui retroune la somme des carr�s des distances
	{
		return Math.pow((depart.getLigne() - arrivee.getLigne()), 2)
			+ Math.pow((depart.getColonne() - arrivee.getColonne()),2);
	}


	//////////////////////////////////
	// m�thode estValide
	//////////////////////////////////
	public abstract boolean estValide (Position depart, Position arrivee);
	// m�thode abstraite � impl�menter dans chacune des sous-classes
}


