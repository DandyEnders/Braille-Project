package utility;

/**
 * A class representing a phrase.
 * @author Jinho Hwang
 *
 */

public class Phrase {
	private String type;
	private Phrase flag; // for repeat / end repeat
	private String value; // for button, cell , etc
	
	
	/**
	 * Takes a type (String) of a phrase.
	 *  
	 * @param type
	 * 		Type of a phrase, Ex: reset, repeat, etc
	 */
	public Phrase(String type) {
		this(type,null,null);
	}
	
	/**
	 * Takes a type (String) and value (String) of a phrase.
	 * 
	 * @param type
	 * 		Type of a phrase, Ex: reset, repeat, etc
	 * @param value
	 * 		Value of a argument value, Ex: 1, 2, one, two, etc
	 */
	public Phrase(String type, String value){
		this(type,value,null);
	}
	
	/**
	 * Takes a type (String), value (String), and flag (Phrase)
	 * of a phrase.
	 * 
	 * @param type
	 * 		Type of a phrase, Ex: reset, repeat, etc
	 * @param value
	 * 		Value of a argument value, Ex: 1, 2, one, two, etc
	 * @param flag
	 * 		A reference to another phrase, Ex : repeat phrase has
	 * 		a flag of endrepeat.
	 */
	public Phrase(String type, String value, Phrase flag){
		this.type = type;
		this.value = value;
		this.flag = flag;
	}
}