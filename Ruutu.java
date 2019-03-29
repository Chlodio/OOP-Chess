import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

/*En keksi parempaa tapaa saada id:n GUI:sta, niin annan joka ruudun GUI:ille iiden joka vastaa sen hashmap keyta*/

public class Ruutu extends JButton{
	private int id;
	public static Ruutu valittu;
	public static Ruutu ekaValinta = null;
	private int yk;
	private int xk;
	private boolean sallittu;
	private final static Color sVari = new Color(0.5f, 0f, 0.5f, .5f);
	private final static Border eBorder = new LineBorder(Color.BLACK, 3);
	public Ruutu(int id, int y, int x){
		super();
		this.id = id;
		this.yk = y;
		this.xk = x;
		this.sallittu = false;
		this.setBackground(sVari);
		//this.setBorder(nBorder);
	}

/* kutsuu Ruutu olion id-attribuutin*/
	public int getId(){
		return this.id;
	}

/* kutsuu Ruutu olion xk-attribuutin*/
	public int haeX(){
		return this.xk;
	}

/* kutsuu Ruutu olion yk-attribuutin*/
	public int haeY(){
		return this.yk;
	}

/* asettaa valitun olion stattiseksi muuttujaksi nimeltä valittu*/
	public void valikoi(){
		valittu = this;
	}

/* asettaa valittu-muuttujan nulliiin*/
	public static void resetValittu(){
		valittu = null;
	}

/* palauttaa valittu-muuttujan*/
	public static Ruutu haeValittu(){
		return valittu;
	}

/* asettaa valitun olion staattiseksi  ekaValinta*/
	public void ekavalikoi(){
		ekaValinta = this;
		this.setBorder(eBorder);
	}

/* resetoi ekaValinta muuttajan*/
	public static void resetEkavalittu(){
		if (ekaValinta != null){
			ekaValinta.setBorder(UIManager.getBorder("Button.border"));
			ekaValinta = null;
		}
	}

/* tuo ekaValinta olion*/
	public static Ruutu haeEkavalittu(){
		return ekaValinta;
	}

/*  /* asettaa olion sallittu-attribuutin parametriin*/
	public void asetaSallitu(boolean s){
		this.sallittu = s;
	}

/* palauttaa olion-sallittu attribuutin*/
	public boolean onkoSallitu(){
		return this.sallittu;
	}

}
