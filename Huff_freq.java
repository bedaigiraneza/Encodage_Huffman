package proj_codage_Huffman;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Huff_freq {
	
	public static String text ;
	private int[] charTable = new int[255];
	
	// get le table ASCII
	public int[] getcharTable() {
		return charTable;
	}
	
	//print text à l'ecran à faire
	
	
	//Lire le fichier à compresser
		public void transTexte(String msg){
		try {
			FileReader fichier = new FileReader(msg); 
			
			 //boucle pour ecrement chacun char une fois trouvé dans le texte
			 
			int c; 
			while ((c=fichier.read()) != -1) 
			{
				charTable[c]++;
				text+=(char)c;
			}
			for (int i = 0; i < charTable.length; i++) {
				if(charTable[i] != 0)
					Huffman.freqFile+= (char)i + " " + charTable[i] + "\n";
//				System.out.println(charTable[i]);
			}
			fichier.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	/*
	cree une chaine de caractère dans le texte enregistré dans la variable text, 
	on cherche le noeud de liason puis on l'ajout dans une chaine binaire
	chaine binaire sera mene à huit char
	*/
	
	public String creeCodeBin(ArrayList<Noeud> nodes) {
		String codeBin = "";
		for (int i = 0; i < text.length(); i++) {
			char letter = text.charAt(i);
			for (Noeud node : nodes) {
				if(letter == node.getChar())
					codeBin += node.getBinCode();
			}
		}
		return normaliseCodeBin(codeBin);
	}
	 
	//Ajoute des 0 à la chaîne binaire pour rendre la chaîne divisible par huit.

	
	private String normaliseCodeBin(String codeBin){
		int diff = 8-codeBin.length()%8;
		for (int i = 0; i < diff; i++) {
			codeBin+="0";	
		}
		return codeBin;
	}
	
	// travaille sur mon fichier
	// entre char dans l'array ASCII après chacun passage de lecture
	public void freqFileToArray(String msg){
		try {
			FileReader fichier = new FileReader(msg);
			
			int i;
			text = "";
			while ((i=fichier.read()) != -1) 
			{
				text += (char)i;
			}
			for (int j = 0; j < text.length(); j++) {
				if(text.charAt(j) == ' ' && text.charAt(j+1) != ' '){
					char charac = text.charAt(j-1);
					j++;
					String freq = "";
					while(text.charAt(j) != '\n'){
						freq += text.charAt(j);
						j++;
						if(j == text.length()-1)
							break;
					}
					charTable[(int)charac] = Integer.parseInt(freq);
				}
			}
			fichier.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	
	// lire chacun char et convertir en binaire
	public String coderEnBin(String msg){
		try {
			File fichier = new File(msg);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fichier), Charset.forName("ISO-8859-1")));
			int i;
			String codeBin = "";
			while ((i=reader.read()) != -1) 
			{
				codeBin += normaliserOct(Integer.toBinaryString(i));
			}
			reader.close();
			return codeBin;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	//normalise en base huit
	private String normaliserOct(String codeBin){
		while(codeBin.length() < 8)
			codeBin = "0" + codeBin;
		return codeBin;
	}
}