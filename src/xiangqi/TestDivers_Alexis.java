
package xiangqi;

public class TestDivers_Alexis
{
	public static void main(String[] args)
	{
		// tests pour classe Char
		System.out.println("\n-------------------------------");
		System.out.println("-> méthode estValide pr CHAR");

		Char char1 = new Char("char", "noir");

		Position d1 = new Position(1,1);
		Position a1 = new Position(2,2);

		Position d2 = new Position(1,3);
		Position a2 = new Position(1,7);
		
		System.out.println(char1.estValide(d1, a1)); // false
		System.out.println(char1.estValide(d2, a2)); // true

		
		
		// tests pour classe Roi
		System.out.println("\n-------------------------------");
		System.out.println("-> méthode estValide pr ROI");

		Roi roi1 = new Roi("roi", "noir");
		Roi roi2 = new Roi("roi", "rouge");
		
		Position d3 = new Position(1,4);
		Position a3 = new Position(0,3);

		Position d4 = new Position(7,3);
		Position a4 = new Position(7,4);
		
		System.out.println(roi1.estValide(d3, a3)); // true
		System.out.println(roi2.estValide(d4, a4)); // true

		
		
		// tests pour classe Mandarin
		System.out.println("\n-------------------------------");
		System.out.println("-> méthode estValide pr MANDARIN");

		Mandarin mandarin1 = new Mandarin("mandarin", "noir");
		Mandarin mandarin2 = new Mandarin("mandarin", "rouge");
		
		Position d5 = new Position(1,4);
		Position a5 = new Position(0,3);

		Position d6 = new Position(7,3);
		Position a6 = new Position(7,4);
		
		System.out.println(mandarin1.estValide(d5, a5)); // true
		System.out.println(mandarin2.estValide(d6, a6)); // false

		

		// tests pour classe Elephant
		System.out.println("\n-------------------------------");
		System.out.println("-> méthode estValide pr ÉLÉPHANT");

		Elephant elephant1 = new Elephant("elephant", "noir");
		Elephant elephant2 = new Elephant("elephant", "rouge");
		
		Position d7 = new Position(0,2);
		Position a7 = new Position(2,0);

		Position d8 = new Position(9,2);
		Position a8 = new Position(7,0);
		
		System.out.println(elephant1.estValide(d7, a7)); // true
		System.out.println(elephant2.estValide(d8, a8)); // true

		

		// tests pour classe Cavalier
		System.out.println("\n-------------------------------");
		System.out.println("-> méthode estValide pr CAVALIER");

		Cavalier cavalier1 = new Cavalier("cavalier", "noir");
		
		Position d9 = new Position(0,0);
		Position a9 = new Position(2,0);

		Position d10 = new Position(5,2);
		Position a10 = new Position(7,1);
		
		Position d11 = new Position(6,4);
		Position a11 = new Position(7,0);

		Position d12 = new Position(9,2);
		Position a12 = new Position(7,1);

		System.out.println(cavalier1.estValide(d9, a9)); // false
		System.out.println(cavalier1.estValide(d10, a10)); // true
		System.out.println(cavalier1.estValide(d11, a11)); // false
		System.out.println(cavalier1.estValide(d12, a12)); // true

		
		
		// tests pour cheminPossible
		System.out.println("\n-------------------------------");
		System.out.println("-> méthode cheminPossible");
		
		Position d13 = new Position(0,1);
		Position a13 = new Position(4,5); // false

		Position d14 = new Position(0,1);
		Position a14 = new Position(3,1); // false

		Position d15 = new Position(0,1);
		Position a15 = new Position(2,2); // true 

		Position d16 = new Position(3,6);
		Position a16 = new Position(9,0); // true
		
		Echiquier ec1 = new Echiquier();
		
		ec1.debuter();
		
		System.out.println(ec1.cheminPossible(d13, a13));
		System.out.println(ec1.cheminPossible(d14, a14));
		System.out.println(ec1.cheminPossible(d15, a15));
		System.out.println(ec1.cheminPossible(d16, a16));
	}
}


