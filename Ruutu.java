import javax.swing.JButton;

/*En keksi parempaa tapaa saada id:n GUI:sta, niin annan joka ruudun GUI:ille iiden joka vastaa sen hashmap keyta*/

public class Ruutu extends JButton{
	private int id;
	public static Ruutu valittu;
	private int yk;
	private int xk;

	public Ruutu(int id, int y, int x){
		super(Integer.toString(y)+" "+Integer.toString(x));
		this.id = id;
		this.yk = y;
		this.xk = x;
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

}
