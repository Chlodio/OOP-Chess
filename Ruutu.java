import javax.swing.JButton;

/*En keksi parempaa tapaa saada id:n GUI:sta, niin annan joka ruudun GUI:ille iiden joka vastaa sen hashmap keyta*/

public class Ruutu extends JButton{
	private int id;
	public static Ruutu valittu;
	public static Ruutu ekaValinta;
	private int yk;
	private int xk;
	private boolean sallittu;

	public Ruutu(int id, int y, int x){
		super(Integer.toString(y)+" "+Integer.toString(x));
		this.id = id;
		this.yk = y;
		this.xk = x;
		this.sallittu = false;
	}

	public int getId(){
		return this.id;
	}


	public int haeX(){
		return this.xk;
//		return this.id%8;
	}

	public int haeY(){
		return this.yk;
	//	return this.id/8;
	}

	public void valikoi(){
		valittu = this;
	}

	public static void resetValittu(){
		valittu = null;
	}

	public static Ruutu haeValittu(){
		return valittu;
	}

	public void ekavalikoi(){
		ekaValinta = this;
	}

	public static void resetEkavalittu(){
		ekaValinta = null;
	}

	public static Ruutu haeEkavalittu(){
		return ekaValinta;
	}


	public void asetaSallitu(boolean s){
		this.sallittu = s;
	}

	public boolean onkoSallitu(){
		return this.sallittu;
	}

}
