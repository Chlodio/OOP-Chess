import javax.swing.JButton;

/*En keksi parempaa tapaa saada id:n GUI:sta, niin annan joka ruudun GUI:ille iiden joka vastaa sen hashmap keyta*/

public class Ruutu extends JButton{
	private int id;
	public static Ruutu valittu;

	public Ruutu(int id){
		this.id = id;
	}

	public int getId(){
		return this.id;
	}


	public int haeX(){
		return this.id%8;
	}

	public int haeY(){
		return this.id/8;
	}

	public void valikoi(){
		valittu = this;
	}

}
