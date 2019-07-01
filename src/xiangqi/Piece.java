
package xiangqi;

// classe abstraite Piece servant de point de départ pour
// tous les types de pièces d'un jeu de Xiangqi

public abstract class Piece
{
	// nom de la pièce selon les conventions
	private String nom;

	// couleur de la pièce
	private String couleur;


	//////////////////////////////////
	// méthode constructeur
	//////////////////////////////////
	public Piece (String nom, String couleur)
	// méthode qui permet d'initialiser le nom et la couleur d'un objet Piece
	{
		setNom (nom);
		setCouleur (couleur);
	}


	//////////////////////////////////
	// méthode d'accès
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
	// méthode norme
	//////////////////////////////////
	public double norme (Position depart, Position arrivee)
	// méthode qui calcule la norme mathématique entre deux Positions
	// et qui retroune la somme des carrés des distances
	{
		return Math.pow((depart.getLigne() - arrivee.getLigne()), 2)
			+ Math.pow((depart.getColonne() - arrivee.getColonne()),2);
	}


	//////////////////////////////////
	// méthode estValide
	//////////////////////////////////
	public abstract boolean estValide (Position depart, Position arrivee);
	// méthode abstraite à implémenter dans chacune des sous-classes
}


