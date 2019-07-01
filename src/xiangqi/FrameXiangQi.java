
package xiangqi;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.FlowLayout;

public class FrameXiangQi extends JFrame {

	public static final int NB_DE_LIGNES = 10;
	public static final int NB_DE_COLONNES = 9;
	public static final int NB_DE_PIECES = 16;

	private JPanel contentPane;
	private JPanel panelConteneur;
	private JLabel labelImage, labelCouleur;

	private JLabel grille[][]; // 90 JLabels transparents s'apparentant aux intersections
	private JLabel colonneRouge[];
	private JLabel colonneNoire[];

	private JPanel panelControle;
	private JButton boutonDebuter, boutonRecommencer;

	private Ecouteur ec;
	private Echiquier echiquier; // échiquier faisant le lien avec la logique du jeu

	private JPanel panelRouge;
	private JPanel panelNoir;
	private int compteurRouge = 0;
	private int compteurNoir = 0;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameXiangQi frame = new FrameXiangQi();

					frame.setVisible(true);

					// pour que la fenêtre soit centrée par rapport à l'écran
					frame.setLocationRelativeTo(null);

					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameXiangQi() {

		echiquier = new Echiquier(); //création de l'échiquier et des 90 JLabels
		grille = new JLabel[NB_DE_LIGNES][NB_DE_COLONNES];
		colonneRouge = new JLabel[NB_DE_PIECES];
		colonneNoire = new JLabel[NB_DE_PIECES];

		setTitle("XiangQi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 878);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelConteneur= new JPanel();
		panelConteneur.setBackground(new Color(255, 228, 196));
		panelConteneur.setBounds(10, 77, 672, 751);
		panelConteneur.setLayout(new GridLayout(10, 9, 0, 0));
		panelConteneur.setOpaque(false);
		contentPane.add(panelConteneur);

		panelControle = new JPanel();
		panelControle.setBackground(new Color(255, 228, 196));
		panelControle.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelControle.setBounds(0, 11, 918, 58);
		contentPane.add(panelControle);
		panelControle.setLayout(null);

		boutonRecommencer = new JButton("Recommencer");
		boutonRecommencer.setBounds(744, 22, 152, 23);
		boutonRecommencer.setBackground(new Color(255,239,213));
		boutonRecommencer.setContentAreaFilled(false);
		boutonRecommencer.setOpaque(true);
		panelControle.add(boutonRecommencer);
		boutonRecommencer.setFont(new Font("Tahoma", Font.BOLD, 15));

		boutonDebuter = new JButton("D\u00E9buter");
		boutonDebuter.setBackground(new Color(255, 239, 213));
		boutonDebuter.setBounds(610, 22, 119, 23);
		boutonDebuter.setContentAreaFilled(false);
		boutonDebuter.setOpaque(true);
		panelControle.add(boutonDebuter);
		boutonDebuter.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelCouleur = new JLabel("");
		labelCouleur.setBackground(new Color(255, 239, 213));
		labelCouleur.setOpaque(true);
		labelCouleur.setBounds(53, 11, 475, 41);

		panelControle.add(labelCouleur);
		labelCouleur.setFont(new Font("Tahoma", Font.BOLD, 20));

		panelRouge = new JPanel();
		panelRouge.setBackground(Color.RED);
		panelRouge.setBounds(688, 77, 110, 745);
		contentPane.add(panelRouge);
		panelRouge.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panelNoir = new JPanel();
		panelNoir.setBackground(Color.BLACK);
		panelNoir.setBounds(800, 77, 110, 745);
		contentPane.add(panelNoir);
		panelNoir.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		labelImage = new JLabel("");
		labelImage.setBounds(10, 77, 672, 751);
		contentPane.add(labelImage);
		labelImage.setIcon((new ImageIcon("icones/fond2.png")));
		
		for (int i = 0 ; i < NB_DE_PIECES ; i++) {
			
			colonneRouge[i] = new JLabel();
			panelRouge.add(colonneRouge[i]);
			colonneRouge[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			colonneNoire[i] = new JLabel();
			panelNoir.add(colonneNoire[i]);
			colonneNoire[i].setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		
		//gestion des événements 
		ec = new Ecouteur();
		for ( int i = 0 ; i < 10 ; i++) {
			for ( int j = 0 ; j < 9 ; j++) {
				grille[i][j] = new JLabel();
				grille[i][j].addMouseListener(ec);
				panelConteneur.add(grille[i][j]);
				grille[i][j].setHorizontalAlignment(SwingConstants.CENTER); // icône centré :)
			}
		}
		boutonDebuter.addMouseListener(ec);
		boutonRecommencer.addMouseListener(ec);
	}

	
	private class Ecouteur extends MouseAdapter {

		int ligneClic, colonneClic; // ligne et colonne où l'utilisateur a cliqué
		Piece pieceTampon, pieceEnlevee;
		ImageIcon iconeTampon;
		Position depart, arrivee;
		String couleurControle = "rouge"; // les rouges vont tjrs jouer en premier
		String msgCouleur1 = "  C'est au tour du côté ";
		String msgCouleur2 = " de jouer.";
		boolean finDeLaPartie;

		@Override
		public void mouseReleased(MouseEvent e) {

			if (e.getSource() == boutonDebuter && finDeLaPartie != true) {

				//CODER ICI #2

				echiquier.debuter();
				labelCouleur.setText(msgCouleur1 + couleurControle + msgCouleur2);
				//-------------------------------------------------------
				grille[0][0].setIcon (new ImageIcon ("icones/charNoir.png"));
				grille[0][1].setIcon (new ImageIcon ("icones/cavalierNoir.png"));
				grille[0][2].setIcon (new ImageIcon ("icones/elephantNoir.png"));
				grille[0][3].setIcon (new ImageIcon ("icones/mandarinNoir.png"));
				grille[0][4].setIcon (new ImageIcon ("icones/roiNoir.png"));
				grille[0][5].setIcon (new ImageIcon ("icones/mandarinNoir.png"));
				grille[0][6].setIcon (new ImageIcon ("icones/elephantNoir.png"));
				grille[0][7].setIcon (new ImageIcon ("icones/cavalierNoir.png"));
				grille[0][8].setIcon (new ImageIcon ("icones/charNoir.png"));

				grille[2][1].setIcon (new ImageIcon ("icones/bombardeNoir.png"));
				grille[2][7].setIcon (new ImageIcon ("icones/bombardeNoir.png"));

				grille[3][0].setIcon (new ImageIcon ("icones/pionNoir.png"));
				grille[3][2].setIcon (new ImageIcon ("icones/pionNoir.png"));
				grille[3][4].setIcon (new ImageIcon ("icones/pionNoir.png"));
				grille[3][6].setIcon (new ImageIcon ("icones/pionNoir.png"));
				grille[3][8].setIcon (new ImageIcon ("icones/pionNoir.png"));
				//-------------------------------------------------------
				grille[9][0].setIcon (new ImageIcon ("icones/charRouge.png"));
				grille[9][1].setIcon (new ImageIcon ("icones/cavalierRouge.png"));
				grille[9][2].setIcon (new ImageIcon ("icones/elephantRouge.png"));
				grille[9][3].setIcon (new ImageIcon ("icones/mandarinRouge.png"));
				grille[9][4].setIcon (new ImageIcon ("icones/roiRouge.png"));
				grille[9][5].setIcon (new ImageIcon ("icones/mandarinRouge.png"));
				grille[9][6].setIcon (new ImageIcon ("icones/elephantRouge.png"));
				grille[9][7].setIcon (new ImageIcon ("icones/cavalierRouge.png"));
				grille[9][8].setIcon (new ImageIcon ("icones/charRouge.png"));

				grille[7][1].setIcon (new ImageIcon ("icones/bombardeRouge.png"));
				grille[7][7].setIcon (new ImageIcon ("icones/bombardeRouge.png"));

				grille[6][0].setIcon (new ImageIcon ("icones/pionRouge.png"));
				grille[6][2].setIcon (new ImageIcon ("icones/pionRouge.png"));
				grille[6][4].setIcon (new ImageIcon ("icones/pionRouge.png"));
				grille[6][6].setIcon (new ImageIcon ("icones/pionRouge.png"));
				grille[6][8].setIcon (new ImageIcon ("icones/pionRouge.png"));
				
			} else if (e.getSource() == boutonRecommencer) {

				//CODER ICI #3
				
				boutonDebuter.setEnabled(true);
				finDeLaPartie = false;
				couleurControle = "rouge";
				labelCouleur.setText(" ");

				for (int i = 0; i < NB_DE_LIGNES; i++) {
					for (int j = 0; j < NB_DE_COLONNES; j++) {
						grille[i][j].setIcon(null);
					}
				}
				// les pièces disparaissent de l'écran, mais elle sont encore dans le modèle
				// lorsque l'utilisateur clique sur "recommencer", le modèle et le visuel sont
				// réinitialisés
				
				for (int i = 0; i < NB_DE_PIECES; i++) {
					colonneRouge[i].setIcon(null);
					colonneNoire[i].setIcon(null);
				}
				
				panelRouge.updateUI();
				panelNoir.updateUI();

			} else if (finDeLaPartie != true) { // si la partie n'est pas terminée

				// il s'agit d'une intersection, il faut trouver laquelle
				for (int i = 0; i < 10 ; i++ ) {
					for (int j = 0; j < 9; j++ ) {
						if (e.getSource() == grille[i][j]) {
							ligneClic = i;
							colonneClic = j;
						}
					}
				}

				// CODER ICI #4

				//--------------------------
				// CAS NO 1: tampon vide et intersection occupée
				//--------------------------
				Intersection choixClic = echiquier.getIntersection(ligneClic,colonneClic);
				
				if (pieceTampon == null
						&&  choixClic.estOccupee()
						&&  choixClic.getPiece().getCouleur() == couleurControle) {

					// aspect modèle
					depart = new Position(ligneClic, colonneClic);
					pieceTampon = echiquier.getIntersection(ligneClic, colonneClic).getPiece();

					// aspect graphique
					iconeTampon = (ImageIcon) grille[ligneClic][colonneClic].getIcon();
					grille[ligneClic][colonneClic].setIcon(null);
					
				} else if (pieceTampon != null) {
					
					// aspect modèle
					arrivee = new Position(ligneClic, colonneClic);

					//--------------------------
					// CAS NO 2: tampon plein et chemin possible
					//--------------------------

					if (echiquier.cheminPossible(depart, arrivee)
							&& pieceTampon.estValide(depart, arrivee)) {
						// La méthode cheminPossible inclut la vérification des rois face à face.
						// J'ai décidé de faire que "ne pas déplacer aucune pièce" compte
						// comme coup joué. Ainsi, lorsque la position d'arrivée de la pièce
						// choisie est la même que la position de départ, c'est au tour de
						// l'autre couleur de jouer.
						
						// aspect modèle
						int ligneDepart = depart.getLigne();
						int colonneDepart = depart.getColonne();
						Intersection intersectionDepart
						= echiquier.getIntersection(ligneDepart,colonneDepart);

						intersectionDepart.setPiece(null);
						pieceEnlevee = choixClic.getPiece(); // null si pas occupee

						// si une pièce est capturée
						if (pieceEnlevee != null) {
							
							// enlever la pièce du modèle
							choixClic.setPiece(null);

							ImageIcon iconePieceCapturee
							= (ImageIcon) grille[ligneClic][colonneClic].getIcon();
							
							if (pieceEnlevee.getCouleur() == "rouge") {
								// mettre la pièce dans la section noire à droite
								colonneNoire[compteurNoir].setIcon (iconePieceCapturee);
								compteurNoir++;
							} else { // pieceEnlevee est noire
								// mettre la pièce dans la section rouge à droite
								colonneRouge[compteurRouge].setIcon (iconePieceCapturee);
								compteurRouge++;
							}
						}
						choixClic.setPiece(pieceTampon);

						// aspect graphique
						grille[ligneDepart][colonneDepart].setIcon(null);
						grille[ligneClic][colonneClic].setIcon(iconeTampon);
						
						// donner le contrôle à l'autre joueur
						if (couleurControle == "rouge") {
							couleurControle = "noir";
						} else {
							couleurControle = "rouge";
						}
						// si c'est un roi
						if (pieceEnlevee instanceof Roi) {
							finDeLaPartie = true;
							boutonDebuter.setEnabled(false);
							labelCouleur.setText("  C'est le côté "+couleurControle+" qui a gagné.");
						} else {
							labelCouleur.setText(msgCouleur1 + couleurControle + msgCouleur2);
						}

					//--------------------------
					// CAS NO 3: tampon plein et chemin impossible
					//--------------------------
					} else {
						// aspect graphique
						grille[depart.getLigne()][depart.getColonne()].setIcon(iconeTampon);
					}
					pieceTampon = null;
					iconeTampon = null;
				}
			}
		} // fin de la méthode mouseReleased
	}// fin de la classe Ecouteur
}







