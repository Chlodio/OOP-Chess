import javax.swing.JButton;

/*En keksi parempaa tapaa saada id:n GUI:sta, niin annan joka ruudun GUI:ille iiden joka vastaa sen hashmap keyta*/

public class Ruutu extends JButton{
	private int id;
	public Ruutu(int id){
		this.id = id;
	}

	public int getId(){
		return this.id;
	}
}
