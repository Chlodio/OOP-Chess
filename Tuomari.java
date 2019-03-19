/*Pitää huolta että kaikki tehdää järjestyksessä*/

public class Tuomari{
	private static int vaihe = 0;
	//0 == nappulan valinta
	//1 == ruudun valinta

	public static void nostaVaihe(){
		vaihe = 1;
	}

	public static void laskeVaihe(){
		vaihe = 0;
	}

	public static void asetaVaihe(int v){
		vaihe = v;
	}

	public static boolean onkoValinta(){
		return vaihe == 0;
	}

}
