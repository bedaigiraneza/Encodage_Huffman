package proj_codage_Huffman;

public class Noeud {
	private int enCode;
	private int freq;
	private String codeBin;
	private Noeud left_child;
	private Noeud right_child;
	
	
	public Noeud(){};
	
	
	public Noeud(int enCode, int freq, Noeud left_child, Noeud right_child) {
		super();
		this.enCode = enCode;
		this.freq = freq;
		this.codeBin = "-1";
		this.left_child = left_child;
		this.right_child = right_child;
	}
	
	public int getUnicode() {
		return enCode;
	}

	public int getFreq() {
		return freq;
	}

	public Noeud getFilsG() {
		return left_child;
	}

	public Noeud getFilsD() {
		return right_child;
	}

	
	 //Transformez le code des frequence en lui même puis retourne le character 'char'

	public char getChar(){
		return (char)enCode;
	}
	
	public String getBinCode() {
		return codeBin;
	}
	
	public void setBinCode(String codeBin) {
		this.codeBin = codeBin;
	}
	
	
	
	//compare 2 noeuds et retourne la plus petite frequence
	public Noeud getMin(Noeud node){
		return this.freq<=node.freq ? this : node; 
	}
	
	
	
	//verfier si le noeud a une feuille ce qui veut dire qu'il n'a pas 
	//le fils gauche et le fils droit
	
	public boolean isLeaf(){
		return ((this.left_child == null) && (this.right_child == null));
	}
	
}