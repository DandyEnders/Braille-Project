package utility;

/**
 * A class representing a phrase.
 * @author Jinho Hwang
 *
 */

public class Phrase {
	
	/**
	 * Type represents the type of phrase such as skip, pause, dis-cell-clear, etc.
	 */
	private String type;
	
	/**
	 * Flag represents another phrase that is linked to. (Ex. /~skip:goto is linked to /~goto)
	 */
	private Phrase flag;
	
	/**
	 * Arguments represents argument of the phrase such as 1, 2, ONEE, THREE, OKAY, etc
	 */
	private String[] arguments = new String[2]; // for button, cell , etc
	
	
	/**
	 * Type getter.
	 * @return this.type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Type setter.
	 * @param type
	 * 		Type to set this phrase.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Flag getter.
	 * @return this.flag
	 */
	public Phrase getFlag() {
		return flag;
	}

	/**
	 * Flag setter.
	 * @param flag
	 * 		Flag to set this phrase.
	 */
	public void setFlag(Phrase flag) {
		this.flag = flag;
	}

	/**
	 * Arguments getter.
	 * @return this.arugments
	 */
	public String[] getArguments() {
		return arguments;
	}

	/**
	 * Arguments setter.
	 * @param arguments
	 * 		arguments to set this phrase.
	 */
	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

	/**
	 * Takes a type (String) of a phrase.
	 *  
	 * @param type
	 * 		Type of a phrase, Ex: reset, repeat, etc
	 */
	public Phrase(String type) {
		this.type = type;
		this.flag = null;
		String[] arguments = {null, null};
		
		this.arguments = arguments;
	}
	
	/**
	 * Takes a type (String) and an argument of a phrase.
	 * @param type
	 * 		Type of a phrase, Ex: reset, repeat, etc
	 * @param argument
	 * 		Argument value, Ex: 1, 2, one, two, etc
	 */
	public Phrase(String type, String argument) {
		this.type = type;
		this.flag = null;
		String[] arguments = {argument, null};
		
		this.arguments = arguments;
	}
	
	/**
	 * Takes a type (String) and arguments (String) of a phrase.
	 * 
	 * @param type
	 * 		Type of a phrase, Ex: reset, repeat, etc
	 * @param arguments
	 * 		Argument value, Ex: 1, 2, one, two, etc in array size 2
	 */
	public Phrase(String type, String[] arguments){
		this(type,arguments,null);
	}
	
	/**
	 * Takes a type (String) and argument1 (String) and argument2 (String) of a phrase.
	 * 
	 * @param type
	 * 		Type of a phrase, Ex: reset, repeat, etc
	 * @param argument1 and argument2
	 * 		Argument value, Ex: 1, 2, one, two, etc in array size 2
	 */
	public Phrase(String type, String argument1, String argument2) {
		this.type = type;
		String[] arguments = {argument1, argument2};
		this.arguments = arguments;
		this.flag = null;
	}
	
	/**
	 * Takes a type (String), value (String), and flag (Phrase)
	 * of a phrase.
	 * 
	 * @param type
	 * 		Type of a phrase, Ex: reset, repeat, etc
	 * @param arguments
	 * 		Argument value, Ex: 1, 2, one, two, etc in array size 2
	 * @param flag
	 * 		A reference to another phrase, Ex : repeat phrase has
	 * 		a flag of endrepeat.
	 */
	public Phrase(String type, String[] arguments, Phrase flag){
		this.type = type;
		String[] inputArguments = new String[2];
		
		inputArguments[0] = arguments[0];
		inputArguments[1] = arguments[1];
		
		this.arguments = inputArguments;
		this.flag = flag;
	}

	/**
	 * @param obj
	 * 			Phrase to compare to.
	 * @return true
	 * 			If this.equals(phrase) meets equal contracts. ( look Object API )
	 * @return false
	 * 			If this.equals(phrase) do not satisfy equal contracts.
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == null){
			return false;
		}
		if (this == obj){
			return true;
		}
		if (obj.getClass() != this.getClass()){
			return false;
		}
		
		Phrase phrase = (Phrase)obj;
		
		if(this.getArguments().length != phrase.getArguments().length) {
			return false;
		}
		
		if (this.type.equals(phrase.getType())){
			
			if(this.getArguments()[0] == null && this.getArguments()[1] == null &&
			   phrase.getArguments()[0] == null && phrase.getArguments()[1] == null) {
				return true;
			}
			
			if(this.getArguments()[0].equals(phrase.getArguments()[0])){
				if(this.getArguments()[1].equals(phrase.getArguments()[1])){
					if(this.flag == phrase.getFlag()){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * toString() shows a string representation of a phrase.
	 * It sees if the argument is empty(null) or not and prints out 
	 * argument if it is not empty.
	 * 
	 * Also, if the phrase is linked (for example, /~skip:go is linked to /~go)
	 * it prints out linked phrase after this phrase.
	 * 
	 * 
	 */
	@Override
	public String toString(){
		String firstArg;
		String secondArg;
		
		if(this.getArguments()[0] == null) {
			firstArg = "";
		}else {
			firstArg = this.getArguments()[0];
		}
		
		if(this.getArguments()[1] == null) {
			secondArg = "";	
		}else {
			secondArg = this.getArguments()[1];
		}
		
		String output = "";
		
		String typeStr = this.getType();
		
		if(this.getType().equals("speak") || this.getType().equals("emptyLine")) {
			typeStr = "";
		}
		
		if(this.getArguments()[0] == null && this.getArguments()[1] == null) {
			output += typeStr;
		}
		else if(this.getArguments()[0] != null && this.getArguments()[1] == null) {
			output += typeStr + firstArg;
		}
		else{
			output += typeStr + firstArg + " " + secondArg;
		}
		
		/*if(this.getFlag() != null) {
			output += " linked to " + this.getFlag().getType();
			if(getFlag().getArguments() != null) {
				output += getFlag().getArguments()[0] + " " + getFlag().getArguments()[1];
			}
		}*/
		
		return output;
	}
}
