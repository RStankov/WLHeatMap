package gps;

public class Tokenizer {
	private static String DELIMITER = ",";

	private String[] tockens;
	private int index;

	public Tokenizer(String s) {
		this.index   = 0;
		this.tockens = s.split(DELIMITER);
	}
	
	public boolean hasNext(){
		return index+1 < tockens.length;
	}
	
	public String current(){
		return tockens[index];
	}
	
	public String next(){
		return tockens[index++];
	}
}