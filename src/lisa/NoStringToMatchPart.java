package lisa;

public class NoStringToMatchPart extends Exception {
	private String pieceName;
	
	public NoStringToMatchPart(String name) {
		this.pieceName = name;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return this.pieceName + " piece is not constructble yet";
	}
}
