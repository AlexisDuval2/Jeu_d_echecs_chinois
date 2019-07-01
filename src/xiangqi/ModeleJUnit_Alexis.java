
package xiangqi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/////////////////////////////////////////////////////////////////
//   svp voir le fichier "TestDivers.java" pr d'autres tests   //
/////////////////////////////////////////////////////////////////

public class ModeleJUnit_Alexis
{
	
	Roi r;
	Mandarin m1, m2;
	Elephant e1, e2;
	Cavalier c1, c2;
	Char t1, t2;
	Pion p1, p2;
	Bombarde b1, b2;
	
	@Before
	public void init()
	{
		r = new Roi("r", "noir");

		m1 = new Mandarin("m1", "noir");
		m2 = new Mandarin("m2", "rouge");

		e1 = new Elephant("e1", "noir");
		e2 = new Elephant("e2", "rouge");
		
		c1 = new Cavalier("c1", "noir");
		c2 = new Cavalier("c2", "rouge");
		
		p1 = new Pion("p1", "noir");
		p2 = new Pion("p2", "rouge");
	}

	// #1
	@Test
	public void testRoi_1()
	{
		Position depart = new Position(1,4);
		Position arrivee = new Position(0,3);
		
		assertEquals (true, r.estValide(depart, arrivee));
	}
	
	// #2
	@Test
	public void testRoi_2()
	{
		Position depart = new Position(1,4);
		Position arrivee = new Position(3,0);
		
		assertEquals (false, r.estValide(depart, arrivee));
	}
	
	// #3
	@Test
	public void testMandarin_1()
	{
		Position depart = new Position(1,4);
		Position arrivee = new Position(0,3);
		
		assertEquals (true, m1.estValide(depart, arrivee));
	}
	
	// #4
	@Test
	public void testMandarin_2()
	{
		Position depart = new Position(7,3);
		Position arrivee = new Position(7,4);
		
		assertEquals (false, m2.estValide(depart, arrivee));
	}
	
	// #5
	@Test
	public void testElephant_1()
	{
		Position depart = new Position(0,2);
		Position arrivee = new Position(2,0);
		
		assertEquals (true, e1.estValide(depart, arrivee));
	}
	
	// #6
	@Test
	public void testElephant_2()
	{
		Position depart = new Position(9,2);
		Position arrivee = new Position(7,0);
		
		assertEquals (true, e2.estValide(depart, arrivee));
	}
	
	// #7
	@Test
	public void testCavalier_1()
	{
		Position depart = new Position(0,0);
		Position arrivee = new Position(2,0);
		
		assertEquals (false, c1.estValide(depart, arrivee));
	}
	
	// #8
	@Test
	public void testCavalier_2()
	{
		Position depart = new Position(5,2);
		Position arrivee = new Position(7,1);
		
		assertEquals (true, c2.estValide(depart, arrivee));
	}
	
	// #9
	@Test
	public void testCavalier_3()
	{
		Position depart = new Position(6,4);
		Position arrivee = new Position(7,0);
		
		assertEquals (false, c1.estValide(depart, arrivee));
	}
	
	// #10
	@Test
	public void testCavalier_4()
	{
		Position depart = new Position(9,2);
		Position arrivee = new Position(7,1);
		
		assertEquals (true, c2.estValide(depart, arrivee));
	}

	// #11
	@Test
	public void testPion_1()
	{
		Position depart = new Position(1,2);
		Position arrivee = new Position(2,2);
		
		assertEquals (true, p1.estValide(depart, arrivee));
	}
	
	// #12
	@Test
	public void testPion_2()
	{
		Position depart = new Position(8,2);
		Position arrivee = new Position(9,2);
		
		assertEquals (false, p2.estValide(depart, arrivee));
	}

	
	
}




