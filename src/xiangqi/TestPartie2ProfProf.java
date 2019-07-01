
package xiangqi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestPartie2ProfProf
{
	Cavalier cRouge, cRouge2, cNoir;
	Roi rRouge, rNoir;
	Char tRouge, tRouge2, tNoir;
	Mandarin mRouge, mNoir;
	Echiquier e;
	Elephant eRouge, eNoir;
	Bombarde bRouge, bNoir, bNoir2;
	Pion pNoir, pNoir2,pNoir3,pNoir4, pRouge, pRouge2;


	@Before
	public void initialisation ()
	{
		e = new Echiquier();

		cRouge = new Cavalier("c1", "rouge");
		e.getIntersection(9, 7).setPiece(cRouge);

		rRouge = new Roi ( "r1", "rouge");
		e.getIntersection(9,3).setPiece(rRouge);

		tRouge = new Char("t1", "rouge");
		e.getIntersection(8, 7).setPiece(tRouge);

		mRouge = new Mandarin("m1", "rouge");
		e.getIntersection(8, 4).setPiece(mRouge);

		eRouge = new Elephant("e1", "rouge");
		e.getIntersection(7, 0).setPiece(eRouge);

		bRouge = new Bombarde ("b1", "rouge");
		e.getIntersection(6, 1).setPiece(bRouge);

		bNoir = new Bombarde ("b1", "noir");
		e.getIntersection(5, 1).setPiece(bNoir);

		pNoir = new Pion ("p1", "noir");
		e.getIntersection(5, 0).setPiece(pNoir);

		bNoir2 = new Bombarde ("b2", "noir");
		e.getIntersection(4, 7).setPiece(bNoir2);

		pNoir2 = new Pion ("p2", "noir");
		e.getIntersection(4, 6).setPiece(pNoir2);

		cRouge2 = new Cavalier ("c2", "rouge");
		e.getIntersection(4, 5).setPiece(cRouge2);

		pRouge = new Pion ("p1", "rouge");
		e.getIntersection(4, 4).setPiece(pRouge);

		pRouge2 = new Pion ("p2", "rouge");
		e.getIntersection(3, 2).setPiece(pRouge2);

		pNoir3 = new Pion ("p3", "noir");
		e.getIntersection(3, 0).setPiece(pNoir3);

		eNoir = new Elephant ("e1", "noir");
		e.getIntersection(2, 4).setPiece(eNoir);

		pNoir4 = new Pion ("p4", "noir");
		e.getIntersection(2, 2).setPiece(pNoir4);

		cNoir = new Cavalier ("c1", "noir");
		e.getIntersection(2, 0).setPiece(cNoir);

		mNoir = new Mandarin ("p1", "noir");
		e.getIntersection(1, 4).setPiece(mNoir);

		tRouge2 = new Char ("t2", "rouge");
		e.getIntersection(1, 3).setPiece(tRouge2);

		tNoir = new Char ("t1", "noir");
		e.getIntersection(1, 1).setPiece(tNoir);

		rNoir = new Roi ("r1", "noir");
		e.getIntersection(0, 3).setPiece(rNoir);
	}

	@Test 
	public void test1() // FAUX - un cavalier ne peut pas sauter
	{
		Position depart = new Position ( 9, 7);
		Position arrivee = new Position ( 7, 8);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}
	@Test 
	public void test2() // VRAI - un déplacement normal pr un cavalier
	{
		Position depart = new Position ( 9, 7);
		Position arrivee = new Position ( 8, 5);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test3() // VRAI - un déplacement normal pr un roi
	{
		Position depart = new Position ( 9, 3);
		Position arrivee = new Position ( 8, 3);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test4() // VRAI - un déplacement normal pr un mandarin
	{
		Position depart = new Position ( 8, 4);
		Position arrivee = new Position ( 7, 5);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test5() // FAUX - un char ne peut pas sauter
	{
		Position depart = new Position ( 8, 7);
		Position arrivee = new Position ( 3, 7);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test6() // VRAI - un char rouge attrappe une pièce noire 
	{
		Position depart = new Position ( 8, 7);
		Position arrivee = new Position ( 4, 7);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test7() // FAUX - un éléphant ne peut pas sauter
	{
		Position depart = new Position ( 7, 0);
		Position arrivee = new Position ( 5, 2);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test8() // FAUX - une bombarde rouge ne peu pas capturer une pièce
						// sans sauter par dessus une autre pièce
	{
		Position depart = new Position ( 6, 1);
		Position arrivee = new Position ( 5, 1);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test9() // VRAI - une bombarde rouge capture une pièce noire
	{
		Position depart = new Position ( 6, 1);
		Position arrivee = new Position ( 1, 1);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test10() // FAUX - un char ne peut pas sauter
	{
		Position depart = new Position ( 1, 1);
		Position arrivee = new Position ( 1, 6);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test11() // VRAI - un déplacement normal pr un mandarin
	{
		Position depart = new Position ( 1, 4);
		Position arrivee = new Position ( 2, 5);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test12() // VRAI - un déplacement normal pr un pion
	{
		Position depart = new Position ( 3, 0);
		Position arrivee = new Position ( 4, 0);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test13() // FAUX - les deux pièces sont de la même couleur
	{
		Position depart = new Position ( 5, 0);
		Position arrivee = new Position ( 5, 1);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test14() // VRAI - un pion noir capture une pièce rouge
	{
		Position depart = new Position ( 2, 2);
		Position arrivee = new Position ( 3, 2);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test15() // FAUX - une bombarde peut slmt capturer avec une pièce entre
	{
		Position depart = new Position ( 4, 7);
		Position arrivee = new Position ( 4, 4);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test16() // VRAI - une bombarde noire capture une pièce rouge
	{
		Position depart = new Position ( 4, 7);
		Position arrivee = new Position ( 4, 5);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test17() // VRAI - un cavalier rouge capture une pièce noire
	{
		Position depart = new Position ( 4, 5);
		Position arrivee = new Position ( 2, 4);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test18() // FAUX - les deux rois seraient face à face
	{
		Position depart = new Position ( 0, 3);
		Position arrivee = new Position ( 1, 3);
//		assertEquals ( true, e.cheminPossible(depart, arrivee));
		// Eric faisait un test sans vérifier les rois... dans ce cas-ci c'est faux
		assertEquals (false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test19() // VRAI - un char rouge capture une pièce noire
	{
		Position depart = new Position ( 1, 3);
		Position arrivee = new Position ( 0, 3);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test20() // VRAI - un éléphant qui se déplace correctement
	{
		Position depart = new Position ( 2, 4);
		Position arrivee = new Position ( 4, 2);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test21() // FAUX - le cavalier ne peut pas sauter
	{
		Position depart = new Position ( 4, 5);
		Position arrivee = new Position ( 3, 7);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test22() // VRAI - un mandarin qui se déplace correctement
	{
		Position depart = new Position ( 1, 4);
		Position arrivee = new Position ( 0, 5);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test23() // VRAI - un cavalier noir capture une pièce rouge
	{
		Position depart = new Position ( 2, 0);
		Position arrivee = new Position ( 3, 2);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test24() // FAUX - un éléphant ne peut pas sauter
	{
		Position depart = new Position ( 2, 4);
		Position arrivee = new Position ( 0, 2);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test25() // FAUX - les pièces sont de la même couleur
	{
		Position depart = new Position ( 4, 4);
		Position arrivee = new Position ( 4, 5);
		assertEquals ( false, e.cheminPossible(depart, arrivee));
	}

	@Test 
	public void test26() // FAUX - si le char se déplace, les rois seront face à face
	{
		Position depart = new Position ( 1, 3);
		Position arrivee = new Position ( 1, 2);
		assertEquals ( false, e.roisNePouvantPasEtreFaceAFace(depart, arrivee));
	}

	@Test 
	public void test27() // VRAI - les rois ne seront pas face à face
	{
		Position depart = new Position ( 0, 3);
		Position arrivee = new Position ( 0, 4);
		assertEquals ( true, e.roisNePouvantPasEtreFaceAFace(depart, arrivee));
	}

	@Test 
	public void test28() // VRAI - les rois ne seront pas face à face
	{
		Position depart = new Position ( 9, 3);
		Position arrivee = new Position ( 9, 4);
		assertEquals ( true, e.roisNePouvantPasEtreFaceAFace(depart, arrivee));
	}
	@Test 
	public void test29() // la pièce ne bouge pas
	{
		Position depart = new Position ( 7, 0);
		Position arrivee = new Position ( 7, 0);
		assertEquals ( true, e.cheminPossible(depart, arrivee));
	}



}
