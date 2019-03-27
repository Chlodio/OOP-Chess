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
//	private final static Color eVari = new Color(0.25f, 0.25f, 0.25f, .5f);
	public Ruutu(int id, int y, int x){
		super();
	//	super(Integer.toString(y)+" "+Integer.toString(x));
		this.id = id;
		this.yk = y;
		this.xk = x;
		this.sallittu = false;
		this.setBackground(sVari);
		//this.setBorder(nBorder);
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
	//	if(ekaValinta != null){ resetEkavalittu(); }
		ekaValinta = this;
		this.setBorder(eBorder);
	}

	public static void resetEkavalittu(){
		if (ekaValinta != null){
			ekaValinta.setBorder(UIManager.getBorder("Button.border"));
			ekaValinta = null;
		}
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
