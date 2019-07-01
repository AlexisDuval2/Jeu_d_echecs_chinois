
package xiangqi;

//////////////////////////////////
// Classe Echiquier
//////////////////////////////////

public class Echiquier implements MethodesEchiquier
{
	public static final int NB_DE_LIGNES = 10;
	public static final int NB_DE_COLONNES = 9;
	
	private Intersection[][] jeu;
	
	//////////////////////////////////
	// méthode construcuteur
	//////////////////////////////////
	public Echiquier()
	{
		jeu = new Intersection[NB_DE_LIGNES][NB_DE_COLONNES];
		
		for (int i = 0; i < NB_DE_LIGNES; i++)
			for (int j = 0; j < NB_DE_COLONNES; j++)
				jeu[i][j] = new Intersection();
	}
	
	//////////////////////////////////
	// méthode debuter
	//////////////////////////////////
	@Override
	public void debuter()
	{
		// méthode qui permet d’installer toutes les pièces sur leur intersection
		// de départ.
		for (int i = 0; i < NB_DE_LIGNES; i++) {
			for (int j = 0; j < NB_DE_COLONNES; j++) {
				jeu[i][j].setPiece (null);
			}
		}
		
		jeu[0][0].setPiece (new Char ("char_g", "noir"));
		jeu[0][1].setPiece (new Cavalier ("cavalier_g", "noir"));
		jeu[0][2].setPiece (new Elephant ("elephant_g", "noir"));
		jeu[0][3].setPiece (new Mandarin ("mandarin_g", "noir"));
		jeu[0][4].setPiece (new Roi ("roi", "noir"));
		jeu[0][5].setPiece (new Mandarin ("mandarin_d", "noir"));
		jeu[0][6].setPiece (new Elephant ("elephant_d", "noir"));
		jeu[0][7].setPiece (new Cavalier ("cavalier_d", "noir"));
		jeu[0][8].setPiece (new Char ("char_d", "noir"));

		jeu[2][1].setPiece (new Bombarde ("bombarde_g", "noir"));
		jeu[2][7].setPiece (new Bombarde ("bombarde_d", "noir"));

		jeu[3][0].setPiece (new Pion ("pion_1", "noir"));
		jeu[3][2].setPiece (new Pion ("pion_2", "noir"));
		jeu[3][4].setPiece (new Pion ("pion_3", "noir"));
		jeu[3][6].setPiece (new Pion ("pion_4", "noir"));
		jeu[3][8].setPiece (new Pion ("pion_5", "noir"));

		//-------------------------------------------------------
		
		jeu[9][0].setPiece (new Char ("char_g", "rouge"));
		jeu[9][1].setPiece (new Cavalier ("cavalier_g", "rouge"));
		jeu[9][2].setPiece (new Elephant ("elephant_g", "rouge"));
		jeu[9][3].setPiece (new Mandarin ("mandarin_g", "rouge"));
		jeu[9][4].setPiece (new Roi ("roi", "rouge"));
		jeu[9][5].setPiece (new Mandarin ("mandarin_d", "rouge"));
		jeu[9][6].setPiece (new Elephant ("elephant_d", "rouge"));
		jeu[9][7].setPiece (new Cavalier ("cavalier_d", "rouge"));
		jeu[9][8].setPiece (new Char ("char_d", "rouge"));

		jeu[7][1].setPiece (new Bombarde ("bombarde_g", "rouge"));
		jeu[7][7].setPiece (new Bombarde ("bombarde_d", "rouge"));

		jeu[6][0].setPiece (new Pion ("pion_1", "rouge"));
		jeu[6][2].setPiece (new Pion ("pion_2", "rouge"));
		jeu[6][4].setPiece (new Pion ("pion_3", "rouge"));
		jeu[6][6].setPiece (new Pion ("pion_4", "rouge"));
		jeu[6][8].setPiece (new Pion ("pion_5", "rouge"));
	}
	
	//////////////////////////////////
	// méthode getIntersection
	//////////////////////////////////
	@Override
	public Intersection getIntersection (int ligne, int colonne)
	{ return jeu[ligne][colonne]; }
	
	//////////////////////////////////
	// méthode cheminPossible
	//////////////////////////////////
	@Override
	public boolean cheminPossible (Position depart, Position arrivee)
	{
		// Méthode qui vérifie si la pièce peut se frayer un chemin entre sa position
		// de départ et celle d’arrivée. De plus, cette méthode confirme que la position
		// d’arrivée n’est pas occupée par une pièce de la même couleur.

		// -> On suppose que le déplacement soumis à la méthode est valide.

		int ligneDepart = depart.getLigne();
		int ligneArrivee = arrivee.getLigne();
		int colonneDepart = depart.getColonne();
		int colonneArrivee = arrivee.getColonne();
		
		int diffLigne = ligneArrivee - ligneDepart;
		int diffColonne = colonneArrivee - colonneDepart;
		
		Piece pieceAuDepart = jeu[ligneDepart][colonneDepart].getPiece();
		
		//-------------------------------------------------------
		// Le cas où la pièce ne bouge pas
		//-------------------------------------------------------
		if (diffLigne == 0  &&  diffColonne == 0)
			return true;
		//-------------------------------------------------------
		// Vérifier que les rois ne seront pas face à face
		//-------------------------------------------------------
		if (!roisNePouvantPasEtreFaceAFace(depart, arrivee))
			return false;
		//-------------------------------------------------------
		// Pour toutes les pièces qui se déplacent de un
		//-------------------------------------------------------
		if (pieceAuDepart instanceof Roi
				||  pieceAuDepart instanceof Mandarin
				||  pieceAuDepart instanceof Pion)
		{
			// on vérifie directement le point d'arrivé (sans déplacement intermédiaire)
			Intersection pointDepart = jeu[ligneDepart][colonneDepart];
			Intersection pointArrivee = jeu[ligneArrivee][colonneArrivee];

			if (pointArrivee.estOccupee())
			{
				String couleurDepart = pointDepart.getPiece().getCouleur();

				if (pointArrivee.estOccupeeParMemeCouleur(couleurDepart))
					return false;
			}
		}
		//-------------------------------------------------------
		// Le cas du cavalier (else if)
		//-------------------------------------------------------
		else if (pieceAuDepart instanceof Cavalier)
		{
			// vérification des 4 cas intermédiaires
			if (diffLigne == -2 // vers le haut
					&& jeu[ligneDepart - 1][colonneDepart].estOccupee())
				return false;
			else if (diffLigne == 2 // 2. vers le bas
					&& jeu[ligneDepart + 1][colonneDepart].estOccupee())
				return false;
			else if (diffColonne == -2 // 3. vers la gauche
					&& jeu[ligneDepart][colonneDepart - 1].estOccupee())
				return false;
			else if (diffColonne == 2 // 4. vers la droite
					&& jeu[ligneDepart][colonneDepart + 1].estOccupee())
				return false;
		}
		//-------------------------------------------------------
		// Le cas de la bombarde (else if)
		//-------------------------------------------------------
		else if (pieceAuDepart instanceof Bombarde)
		// si c'est une bombarde, il faut finir dans ce "if",
		// donc il faut passer au travers de toutes les possibilités
		{
			int compteur = 0;	// j'utilise un compteur pr vérifier s'il y a
								// exactement une pièce entre la bombarde et la
								// pièce à capturer
			//-------------------------------------------------------
			// vérification des 4 cas intermédiaires (else if)
			//-------------------------------------------------------
			// 1. vers le haut
			if (diffLigne < 0  &&  diffColonne == 0  &&  compteur <= 1)
			{
				for (int i = 1; i < (-1 * diffLigne); i++)
					if (jeu[ligneDepart - i][colonneDepart].estOccupee())
						compteur++;
			}
			//-------------------------------------------------------
			// 2. vers le bas
			else if (diffLigne > 0  &&  diffColonne == 0  &&  compteur <= 1)
			{
				for (int i = 1; i < diffLigne; i++)
					if (jeu[ligneDepart + i][colonneDepart].estOccupee())
						compteur++;
			}
			//-------------------------------------------------------
			// 3. vers la gauche
			else if (diffLigne == 0  &&  diffColonne < 0  &&  compteur <= 1)
			{
				for (int i = 1; i < (-1 * diffColonne); i++)
					if (jeu[ligneDepart][colonneDepart - i].estOccupee())
						compteur++;
			}
			//-------------------------------------------------------
			// 4. vers la droite
			else if (diffLigne == 0  &&  diffColonne > 0  &&  compteur <= 1)
			{
				for (int i = 1; i < diffColonne; i++)
					if (jeu[ligneDepart][colonneDepart + i].estOccupee())
						compteur++;
			}
			//-------------------------------------------------------
			// Le cas de l'arrivée pour la bombarde (if)
			//-------------------------------------------------------
			Intersection pointDepart = jeu[ligneDepart][colonneDepart];
			Intersection pointArrivee = jeu[ligneArrivee][colonneArrivee];
			
			if (pointArrivee.estOccupee())
			{
				String couleurDepart = pointDepart.getPiece().getCouleur();

				if (pointArrivee.estOccupeeParMemeCouleur(couleurDepart))
					return false;
				else if (compteur != 1) // doit avoir une seule pièce entre pr capturer
					return false;
			}
			else if (compteur != 0) // la bombarde ne peut pas sauter par-dessus une autre pièce
				return false;		// lorsqu'elle n'est pas en train de capturer une pièce

			return true;
		}
		//-------------------------------------------------------
		// vérification des 8 cas intermédiaires (else if)
		//-------------------------------------------------------
		// 1. vers le haut
		else if (diffLigne < 0  &&  diffColonne == 0)
		{
			for (int i = 1; i < (-1 * diffLigne); i++)
				if (jeu[ligneDepart - i][colonneDepart].estOccupee())
					return false;
		}
		//-------------------------------------------------------
		// 2. vers le bas
		else if (diffLigne > 0  &&  diffColonne == 0)
		{
			for (int i = 1; i < diffLigne; i++)
				if (jeu[ligneDepart + i][colonneDepart].estOccupee())
					return false;
		}
		//-------------------------------------------------------
		// 3. vers la gauche
		else if (diffLigne == 0  &&  diffColonne < 0)
		{
			for (int i = 1; i < (-1 * diffColonne); i++)
				if (jeu[ligneDepart][colonneDepart - i].estOccupee())
					return false;
		}
		//-------------------------------------------------------
		// 4. vers la droite
		else if (diffLigne == 0  &&  diffColonne > 0)
		{
			for (int i = 1; i < diffColonne; i++)
				if (jeu[ligneDepart][colonneDepart + i].estOccupee())
					return false;
		}
		//-------------------------------------------------------
		// 5. vers la diagonale en haut à gauche
		else if (diffLigne < 0  &&  diffColonne < 0)
		{
			for (int i = 1; i < (-1 * diffLigne); i++) // diffLigne est négatif!
				if (jeu[ligneDepart - i ][colonneDepart - i].estOccupee())
					return false;
		}
		//-------------------------------------------------------
		// 6. vers la diagonale en haut à droite
		else if (diffLigne < 0  &&  diffColonne > 0)
		{
			for (int i = 1; i < (-1 * diffLigne); i++) // diffLigne est négatif!
				if (jeu[ligneDepart - i][colonneDepart + i].estOccupee())
					return false;
		}
		//-------------------------------------------------------
		// 7. vers la diagonale en bas à gauche
		else if (diffLigne > 0  &&  diffColonne < 0)
		{
			for (int i = 1; i < diffLigne; i++)
				if (jeu[ligneDepart + i][colonneDepart - i].estOccupee())
					return false;
		}
		//-------------------------------------------------------
		// 8. vers la diagonale en bas à droite
		else if (diffLigne > 0  &&  diffColonne > 0)
		{
			for (int i = 1; i < diffLigne; i++)
				if (jeu[ligneDepart + i ][colonneDepart + i].estOccupee())
					return false;
		}

		//-------------------------------------------------------
		// Le cas de l'arrivée (if)
		//-------------------------------------------------------
		Intersection pointDepart = jeu[ligneDepart][colonneDepart];
		Intersection pointArrivee = jeu[ligneArrivee][colonneArrivee];
		
		if (pointArrivee.estOccupee())
		{
			String couleurDepart = pointDepart.getPiece().getCouleur();

			if (pointArrivee.estOccupeeParMemeCouleur(couleurDepart))
				return false;
		}
		
		return true;
	}
	
	//////////////////////////////////
	// méthode getPositionRoi
	//////////////////////////////////
	public Position getPositionRoi (String couleur)
	{
		// Étant donnée que les rois ne peuvent pas sortir de leur zone,
		// la recherche est beaucoup plus petite.
		if (couleur == "noir")
		{
			for (int i = 0; i <= 2; i++) // lignes 0 à 2
				for (int j = 3; j <= 5; j++) // colonnes 3 à 5
					if (jeu[i][j].getPiece() instanceof Roi)
						return new Position(i, j);
		}
		else // le roi est rouge
		{
			for (int i = 7; i <= 9; i++) // lignes 7 à 9
				for (int j = 3; j <= 5; j++) // colonnes 3 à 5
					if (jeu[i][j].getPiece() instanceof Roi)
						return new Position(i, j);
		}
		
		return null; // au cas où mon code ne fonctionnerait pas :)
	}

	//////////////////////////////////
	// méthode nbDePiecesEntreLes2Rois
	//////////////////////////////////
	public int nbDePiecesEntreLes2Rois (String couleur, Position roiNoir, Position roiRouge)
	{
		// méthode pr compter jusqu'à un maximum de 2 pièces entre les deux rois. A utiliser
		// seulement si les rois sont face à face.
		int nbDePieces = 0;
		int ligneDuRoiNoir = roiNoir.getLigne();
		int colonneDuRoiNoir = roiNoir.getColonne();
		int ligneDuRoiRouge = roiRouge.getLigne();
		int colonneDuRoiRouge = roiRouge.getColonne();
		
		if (couleur == "noir")
		{
			for (int i = ligneDuRoiNoir + 1; i < ligneDuRoiRouge
					&&  nbDePieces <= 2; i++) // pr 2 pièces et +, je veux sortir de la boucle
				if (jeu[i][colonneDuRoiNoir].estOccupee())
					nbDePieces++;
		}
		else // donc on test le roi rouge
		{
			for (int i = ligneDuRoiRouge - 1; i > ligneDuRoiNoir
					&&  nbDePieces <= 2; i--) // pr 2 pièces et +, je veux sortir de la boucle
				if (jeu[i][colonneDuRoiRouge].estOccupee())
					nbDePieces++;
		}

		return nbDePieces;
	}
	
	//////////////////////////////////
	// méthode roisNePouvantPasEtreFaceAFace
	//////////////////////////////////
	@Override
	public boolean roisNePouvantPasEtreFaceAFace (Position depart, Position arrivee)
	{
		// On suppose que la situation initiale est correcte.
		
		Position roiNoir = getPositionRoi("noir");
		Position roiRouge = getPositionRoi("rouge");
		
		int ligneDuRoiNoir = roiNoir.getLigne();
		int colonneDuRoiNoir = roiNoir.getColonne();

		int ligneDuRoiRouge = roiRouge.getLigne();
		int colonneDuRoiRouge = roiRouge.getColonne();

		int ligneDepart = depart.getLigne();
		int colonneDepart = depart.getColonne();

		int ligneArrivee = arrivee.getLigne();
		int colonneArrivee = arrivee.getColonne();

		Piece pieceAuDepart = jeu[ligneDepart][colonneDepart].getPiece();
		
		if (pieceAuDepart instanceof Roi)
		{
			int diffLigne = ligneArrivee - ligneDepart;
			int diffColonne = colonneArrivee - colonneDepart;
			
			if (colonneDuRoiNoir == colonneDuRoiRouge) // Si les rois sont face à face...
			{
				if (ligneDepart <= 2) // Si c'est le roi noir
				{
					if (diffLigne == 1) // S'il avance
					{
						Position temp = new Position (ligneDuRoiNoir + 1, colonneDuRoiNoir);
						if (nbDePiecesEntreLes2Rois("noir", temp, roiRouge) == 0)
							return false;
					}
				}
				else // c'est le roi rouge
				{
					if (diffLigne == -1) // S'il avance
					{
						Position temp = new Position (ligneDuRoiRouge - 1, colonneDuRoiRouge);
						if (nbDePiecesEntreLes2Rois("rouge", roiNoir, temp) == 0)
							return false;
					}
				}
			}
			else // les rois ne sont pas face à face
			{
				if (ligneDepart <= 2) // Si c'est le roi noir
				{
					if ((diffColonne == -1  &&  colonneDuRoiNoir - 1 == colonneDuRoiRouge)
							||  (diffColonne == 1  &&  colonneDuRoiNoir + 1 == colonneDuRoiRouge))
						// Je vérifie si un déplacement horizontal mettra les rois
						// dans la même colonne
					{
						Position temp = new Position (ligneDuRoiNoir, colonneDuRoiRouge);
						if (nbDePiecesEntreLes2Rois("noir", temp, roiRouge) == 0)
							return false;
					}
				}
				else // c'est le roi rouge
				{
					if ((diffColonne == -1  &&  colonneDuRoiRouge - 1 == colonneDuRoiNoir)
							||  (diffColonne == 1  &&  colonneDuRoiRouge + 1 == colonneDuRoiNoir))
						// Je vérifie si un déplacement horizontal mettra les rois
						// dans la même colonne
					{
						Position temp = new Position (ligneDuRoiRouge, colonneDuRoiNoir);
						if (nbDePiecesEntreLes2Rois("rouge", roiNoir, temp) == 0)
							return false;
					}
				}
			}
		}
		else if (colonneDuRoiNoir == colonneDuRoiRouge // Si les rois sont face à face
				&&  colonneDepart == colonneDuRoiNoir // et que la pièce est ds la colonne des rois
				&&  colonneArrivee != colonneDepart) // et que cette pièce change de colonne
		{
			if (nbDePiecesEntreLes2Rois("noir", roiNoir, roiRouge) == 1)
				return false;	// S'il y a seulement la pièce (qui n'est pas un roi) entre les
		}						// deux rois, le déplacement ne sera pas permis
		
		return true;
	}
}


