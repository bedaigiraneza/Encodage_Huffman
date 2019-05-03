//IGIRANEZA Beda
//FI-3 IDU G2

package proj_codage_Huffman;

public class Main {

	public static void main(String[] args) {
	/*	
		try {
		      File myObj = new File("alice29.txt");
		      Scanner myReader = new Scanner(myObj); 
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } 
*/

		
	Huffman texteACoder = new Huffman();

		texteACoder.codageHuff("alice29.txt");
		texteACoder.decodageHuff("ascii_freq.txt", "codeBin.txt");
	

}
}