package pis.hue1;
/**
 * Wuerfel Verschueluesselung Methode
 * @author LENOVO
 *
 */

public class Wuerfel implements Codec {
	private String klartext;
	private String geheimtext;
	private String Losung;
	
	/**
	 * konstruktor
	 * @param Losungg das Losungwort
	 */
	public Wuerfel(String Losungg) {
		this.Losung=Losungg;
	}
	/**
	 * konstruktor
	 */
	public Wuerfel() {
		
	}

	@Override
	/**
	 * verschluesselt das Klartext
	 * @param klartext das Klartext
	 * @return das verschluesselte Wort
	 */
	public String kodiere(String klartexte) {
		this.klartext=klartexte;
		String l = Losung.toLowerCase();
		int length = l.length();
		char[] ktext = new char[klartext.length()];
		char[] gtext = new char[klartext.length()];
		char[] losung1 = new char[length];
		int[] erste = new int[length];
		int[] letzte = new int[length];
		int cnt = 1;

		// String klartext to char array
		for (int i = 0; i < klartext.length(); i++) {
			ktext[i] = klartext.charAt(i);
		}

		// String Losungwort to char array
		for (int i = 0; i < length; i++) {
			losung1[i] = l.charAt(i);
		}

		// String Losungwort to int array alphabetisch
		for (char a = 'a'; a <= 'z'; a++) {
			for (int i = 0; i < length; i++) {

				if (losung1[i] == a) {
					erste[i] = cnt;
					cnt++;
				}

			}

		}


		for (int i = 1; i < letzte.length + 1; i++) {
			for (int j = 0; j < erste.length; j++) {
				if (erste[j] == i) {
					letzte[i - 1] = j + 1;
				}
			}
			
		}
		int tem = 0;
		int tem1 = 0;
		for (int i = 0; i < gtext.length; i++) {

			gtext[i] = ktext[(letzte[tem1] - 1 + tem)];
			tem = tem + length;
			if (letzte[tem1] + tem > klartext.length()) {
				tem = 0;
				tem1++;
				;
			}
			 

		}
		String ergebnis=new String(gtext);
		return ergebnis;
		
	}

	@Override
	/**
	 * entschluesselt das Klartext
	 * @param geheimext
	 * @return das entschuesselte Wort
	 */
	public String dekodiere(String geheimtexte) {
		this.geheimtext=geheimtexte;
		String l = Losung.toLowerCase();
		char[] ktext = new char[geheimtext.length()];
		char[] gtext = new char[geheimtext.length()];
		int length = l.length();
		char[] losung1 = new char[length];
		int[] erste = new int[length];
		int[] letzte = new int[length];
		int cnt = 1;

		// String geheimtext to char array
		for (int i = 0; i < gtext.length; i++) {
			gtext[i] = geheimtext.charAt(i);
		}

		// String Losungwort to char array
		for (int i = 0; i < length; i++) {
			losung1[i] = l.charAt(i);
		}

		
		for (char a = 'a'; a <= 'z'; a++) {
			for (int i = 0; i < length; i++) {

				if (losung1[i] == a) {
					erste[i] = cnt;
					cnt++;
				}

			}

		}
		

		for (int i = 1; i < letzte.length + 1; i++) {
			for (int j = 0; j < erste.length; j++) {
				if (erste[j] == i) {
					letzte[i - 1] = j + 1;
				}
			}

		}
		
		
		int tem = 0;
		int tem1 = 0;
		for (int i = 0; i < gtext.length; i++) {

			ktext[(letzte[tem1] - 1 + tem)]=gtext[i];
			tem = tem + length;
			if (letzte[tem1] + tem > geheimtext.length()) {
				tem = 0;
				tem1++;
				;
			}
		}
		
		
		String ergebnis=new String(ktext);
		
		return ergebnis;
	}

	@Override
	/**
	 * gibt das Losungwort aus
	 * @return das Losungwort
	 */
	public String gibLosung() {

		return Losung;
	}

	@Override
	/**
	 * erstellt das Losungwort
	 * @param schluessel das Schluesselwort
	 * @throws IllegalArgumentException fuer ungeignete Losungwort
	 */
	public void setzeLosung(String schluessel) throws IllegalArgumentException {
		
		boolean flag=true;
		for (int i = 0; i < schluessel.length(); i++) {
			if(!Character.isLetter(schluessel.charAt(i))){
				flag=false;
				throw new IllegalArgumentException("Das Losungwort darf nur Buchstaben enthalten");
			}
		}
		if(flag==true) {
			Losung=schluessel;
		}
		
		
		

	}

}
