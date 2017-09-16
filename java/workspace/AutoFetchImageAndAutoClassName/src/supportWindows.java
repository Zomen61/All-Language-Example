import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class supportWindows extends JFrame{
	private JTextArea supportText;
	private static final String supportlist = "s1:(snis,soe)\n" + "PRESTIGE:(abp,chn,,bgn)\n"
			+ "Moodyz:(midd、mide、miad、migd、mimk)\n" + "EBODY:(ebod、eyan)\n"  + "Kawaii:(kawd、kwbd)\n"
			 + "SOD:(star,sdab,sdmu,dandy)\n" + "Other:(tek,avop,ipz,apak,pppd,dvaj)";
	
	public supportWindows(){
	    Container container = getContentPane();
	    container.setLayout(new FlowLayout());
	    
	    
	    supportText = new JTextArea(supportlist);
	    supportText.setEditable(false);
	    add(supportText);
	    
	    setSize(300, 200);
	    setVisible(true);
	 
	}
}
//s1:(snis,soe)
//PRESTIGE:(abp,chn,,bgn)
//Moodyz:(midd、mide、miad、migd、mimk)
//EBODY:(ebod、eyan)
//Kawaii:(kawd、kwbd)
//SOD:(star,sdab,sdmu,dandy)
//Other:(tek,avop,ipz,apak,pppd)