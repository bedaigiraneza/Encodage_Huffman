package proj_codage_Huffman;

import java.io.*;
import java.util.ArrayList;


public class Huffman {
	
	public static String freqFile;
	
	
//########################################################################################################################	    
// 							ENCODAGE DE HUFFMAN
//########################################################################################################################	    
    
	//codage de texte du fichier entre dans la classe main
	
	public void codageHuff(String txtAcoder) {
		Huff_freq freqHuff = new Huff_freq();
		
		freqHuff.transTexte(txtAcoder);
		ArrayList<Noeud> noeuds = remplNoeud(freqHuff.getcharTable());
		Noeud racine = consArbre(noeuds);
		parcArbre(racine, "");
		ascii_Freq();
		String codeBin = freqHuff.creeCodeBin(noeuds);
		bitEnOctet(codeBin);
		//System.out.println(freqHuff.toString());
	}

	
//################ Etape 1 ########################################################################################################	       
//creation de fichier de char ASCII et leur frequences
	
	private void ascii_Freq(){
		try {
			File fichier = new File("ascii_freq.txt");
			PrintWriter writer = new PrintWriter(fichier);
			writer.write(freqFile);
			writer.close();	
			System.out.println("fichier ascii_freq.txt est bien été creé");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
//#################### Etape 2 ####################################################################################################	    

	//trouve le min frequence 
	private int getMinFreqs(int[] freqs){
		int min = (int)Double.POSITIVE_INFINITY;
		int res = -1;
		for (int i = 0; i < freqs.length; i++) {
			if(freqs[i] != 0 && freqs[i]<min){
				min = freqs[i];
				res=i;
			}
		}
		return res;
	}

	
	//trouve le noeud de petite frequence
	//je cree les noeuds qui sera efface pour eviter la duplication
	
	private Noeud findMin(ArrayList<Noeud> noeuds, Noeud noeudAefface){
		Noeud min = noeuds.get(0) != noeudAefface ? noeuds.get(0) : noeuds.get(1);
		for (Noeud node : noeuds) {
			if(node != noeudAefface)
				min = min.getMin(node);
		}
		return min;
	}
	
	
// construction d'un arbre 
	@SuppressWarnings("unchecked")
	private Noeud consArbre(ArrayList<Noeud> noeuds){
		ArrayList<Noeud> nodes2 = (ArrayList<Noeud>) noeuds.clone();
		while(nodes2.size() > 1){
			Noeud min1 = findMin(nodes2, null);
			Noeud min2 = findMin(nodes2, min1);
			Noeud sup = new Noeud(256, min1.getFreq()+min2.getFreq(), min1, min2); 
			nodes2.add(0, sup);
			nodes2.remove(min1);
			nodes2.remove(min2);
		}
		
		//Retourne la racine de l'arbre
		return nodes2.get(0); 
	}
	
	
//supprime les noeudAefface en ajoutant le pere jusqu'à ce qui il reste un noeud
	
	private ArrayList<Noeud> remplNoeud(int[] freq){
		ArrayList<Noeud> noeuds = new ArrayList<Noeud>();
		int min = 0;
		//trouve le min dans l'odre ASCII en parcourant le tableau de frequence
		//ensuite il met leur valeur à 0 et il s'arret quand tous seront passé à 0
		while((min = getMinFreqs(freq)) != -1){
			Noeud node = new Noeud(min, freq[min], null, null);
			freq[min] = 0;
			noeuds.add(node);
		}
		return noeuds;
	}
	
	
	//parcourt l'arbre et attribue 0 pour le fils gauche et 1 pour le fils droit
		private void parcArbre(Noeud racine, String codeBin){
			if(racine.isLeaf()){
				racine.setBinCode(codeBin);
				System.out.println(racine.getChar() + " " + codeBin);
			}
			else{
				parcArbre(racine.getFilsG(), codeBin + "0");
				parcArbre(racine.getFilsD(), codeBin + "1");			
			}
		}
		

//#################### Etape 3 ####################################################################################################	    
//change chacun caracter en octet puis on cree un fichier text pour le stock
		
		private void bitEnOctet(String codeBin){
			String byteString = "", res = "";
			for (int i = 0; i < codeBin.length(); i++) {
				byteString += codeBin.charAt(i);
				if(byteString.length()==8){
					res +=(char)Integer.parseInt(byteString, 2);
					//System.out.println(Integer.parseInt(byteString, 2) + " " + (char)Integer.parseInt(byteString, 2));
					byteString = "";
				}
			}
			try {
				File fichier = new File("codeBin.txt");
				PrintWriter writer = new PrintWriter(fichier);
				//writer.write(res);

				writer.write(codeBin);
				writer.close();
				System.out.println("fichier codeBien.txt est bien été creé");

			} 
			catch (Exception e) {
				System.out.println(e);
			}
			System.out.println();
		}
		
		
	
//########################################################################################################################	    
//						DECODAGE DE HUFFMAN
//########################################################################################################################	    

	
	// decodage du fichier 
		
	public void decodageHuff(String texteFreq, String charfile){
		Huff_freq freqHuff = new Huff_freq();
		
		freqHuff.freqFileToArray(texteFreq);
		String codeBin = freqHuff.coderEnBin(charfile);
		
		ArrayList<Noeud> noeuds = remplNoeud(freqHuff.getcharTable());
		Noeud racine = consArbre(noeuds);
		parcArbre(racine, "");
		for (Noeud node : noeuds) {
			//System.out.println(node.getChar() + " " + node.getBinCode() + " " + node.getFreq());
		}
		reconsArbre(noeuds, codeBin);
	}
	
	
	
	//rencostruire l'abre
	private void reconsArbre(ArrayList<Noeud> noeuds, String codeBin){
		String text = "";
		Noeud mostFrequent = getMostFrequentChar(noeuds);
		int freqCount = 0;
		while(codeBin.length() > 0){
			for (Noeud node : noeuds) {
				int size = node.getBinCode().length();
				if(size>codeBin.length())
					continue;				
				String reducedBinCode = codeBin.substring(0, size);
				if(node.getBinCode().equals(reducedBinCode)){
					if(node == mostFrequent){
						if(freqCount == mostFrequent.getFreq()){
							codeBin = "";
							break;
						}
						freqCount++;
					}
					text += node.getChar();
					codeBin = codeBin.substring(size);
					break;
				}
			}	
		}
		
		//cree un noveau fichier décompresse
		try {
			File fichier = new File("txtDecompresse.txt");
			PrintWriter writer = new PrintWriter(fichier,"ISO-8859-1");
			writer.write(text);
			writer.close();	
			System.out.println("fichier txtDecompresse.txt est bien été creé");

			
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	
	 //Trouvez les frequence les plus fréquents parmi les noeuds
	
	private Noeud getMostFrequentChar(ArrayList<Noeud> noeuds) {
		Noeud mostFreq = noeuds.get(0);
		for (Noeud node : noeuds) {
			if(node.getFreq() > mostFreq.getFreq())
				mostFreq = node;
		}
		return mostFreq;
	}

}