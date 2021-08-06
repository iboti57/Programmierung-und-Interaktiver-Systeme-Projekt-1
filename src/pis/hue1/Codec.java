package pis.hue1;
/**
 * Schnitstelle
 * @author Ibrahim Eraslan
 *
 */
public interface Codec {
	/**
	 * verschluesselt das Klartext
	 * @param klartext das Klartext
	 * @return das verschluesselte Wort
	 */
	public String kodiere(String klartext);
	/**
	 * entschluesselt das Klartext
	 * @param geheimext das Geheimtext
	 * @return das entschuesselte Wort
	 */
	public String dekodiere(String geheimext);
	/**
	 * 
	 * @return das Losungwort
	 */
	public String gibLosung();
	/**
	 * erstellt das Losungwort
	 * @param schluessel das Schluesselwort
	 * @throws IllegalArgumentException fuer ungeignete Losungwort
	 */
	public void setzeLosung(String schluessel) throws IllegalArgumentException;
	
}
