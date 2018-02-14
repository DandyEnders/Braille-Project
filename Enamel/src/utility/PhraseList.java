package utility;

public class PhraseList {
	
	public class Phrase {
		private String type;
		private Phrase previous;
		private Phrase after;
		private Phrase flag; // for repeat / end repeat
		
		public Phrase(String type) {
			this.type = type;
		}
	}

}
