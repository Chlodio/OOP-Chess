/*Pitää huolta että kaikki tehdää järjestyksessä*/

public class Tuomari{
	private static int vaihe = -1;
	//-1 	= nappuloihin ei saa koskea
	//0 	= mahdollisuus valita nappula
	//1 	= ruudun valinta
	//2 	= ruutu johon uusi nappula siirrettän

	public static void nostaVaihe(){
		vaihe = 1;
	}

	public static void laskeVaihe(){
		vaihe = 0;
	}

	public static void asetaVaihe(int v){
		vaihe = v;
	}

	public static boolean onkoVaihe(int v){
		return vaihe == v;
	}

}
