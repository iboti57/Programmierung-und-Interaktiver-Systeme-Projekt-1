package pis.hue1;
/**
 * Caesar Verschueluesselung Methode
 * @author Ibrahim Eraslan
 *
 */
public class Caesar implements Codec{

	private String losung;
	@Override
	/**
	 * verschluesselt das Klartext
	 * @param klartext das Klartext
	 * @return das verschluesselte Wort
	 */
	public String kodiere(String klartext) {
		int laenge=losung.length();
		if(laenge>26) {
			laenge=laenge%26;
		}
		
		StringBuffer ergebnis=new StringBuffer();
		for (int i = 0; i < klartext.length(); i++) {
			char c=klartext.charAt(i);
			if(Character.isLetter(c)) {
				if(Character.isLowerCase(c)) {
					char ch=(char) (c+laenge);
					if(ch>'z') {
						ergebnis.append((char)(c-(26-laenge)));
					}
					else {
						ergebnis.append(ch);
					}
				}
				else if (Character.isUpperCase(c)) {
					char ch=(char) (c+laenge);
					if(ch>'Z') {
						ergebnis.append((char)(c-(26-laenge)));
					}
					else {
						ergebnis.append(ch);
					}
				}
			}
			else {
				ergebnis.append(c);
			}
		}
		 
		
		String erg=ergebnis.toString();
		
		
		
		return erg;
	}

	@Override
	/**
	 * entschluesselt das Klartext
	 * @param geheimtext das Geheimtext
	 * @return das entschluesselte Wort
	 */
	public String dekodiere(String geheimext) {
		int laenge=losung.length();
		if(laenge>26) {
			laenge=laenge%26;
		}
		
		StringBuffer ergebnis=new StringBuffer();
		
		for (int i = 0; i < geheimext.length(); i++) {
			char c=geheimext.charAt(i);
			if(Character.isLetter(c)) {
				if(Character.isLowerCase(c)) {
					char ch=(char) (c-laenge);
					if(ch<'a') {
						ergebnis.append((char)(c+(26-laenge)));
					}
					else {
						ergebnis.append(ch);
					}
				}
				else if (Character.isUpperCase(c)) {
					char ch=(char) (c-laenge);
					if(ch<'A') {
						ergebnis.append((char)(c+(26-laenge)));
					}
					else {
						ergebnis.append(ch);
					}
				}
			}
			else {
				ergebnis.append(c);
			}
		}
		 
		
		String erg=ergebnis.toString();
		
		
		
		return erg;
	}

	@Override
	/**
	 * 
	 * @return das Losungwort
	 */
	public String gibLosung() {
		
		return losung;
	}

	@Override
	/**
	 * erstellt das Losungwort
	 * @param schluessel das Schluesselwort
	 */
	public void setzeLosung(String schluessel){
		losung=schluessel;
	}

}
