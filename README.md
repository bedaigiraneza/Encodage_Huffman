 Compression de données par codage de Huffman
 
 Déterminer de caractère ASCII


Lis le texte (.txt), 
compte les caractères et 
place les fréquences dans un tableau de 255 entiers. 
on y arrive à l'aide de méthode appellé:


public void transTexte(String msg){


}


Déterminer la fréquence de caractère ASCII 


Incrémente chacun caractère ASCII à chaque fois il est trouvé. 

Puis crée un fichier de fréquences basé sur le tableau

Ensuite calculer la fréquence minimum.
on y arrive à l'aide de méthode appellé:


private void ascii_Freq(){

try {
	File fichier = new File("ascii_freq.txt")

	}

}

CONSTRUCTION D’UN ARBRE


Créer une arborescence à partir de la liste de tableaux ArrayList de noeuds. 

ArrayList va se complète au fur et à mesure de l'exécution de l’algorithme.

Puis supprime chaque nœud du tableau pour éviter la duplication. 


private ArrayList<Noeud> remplNoeud(int[] freq){


}

CONSTRUCTION D’UN ARBRE

Je recherches les deux nœuds minimum puis une fois trouvé, je le considère comme le père.

Les deux autres seront supprimés jusqu'à ce qu'il ne reste plus qu'un nœud



private Noeud consArbre(ArrayList<Noeud> noeuds){

}

PARCOUR DE L’ ARBRE


Les feuilles sont étiquetées avec les caractères proper. Je  parcours l’arbre en attribuant valeur “0” pour le fils gauche et “1” pour le fils droit.




private void parcArbre(Noeud racine, String codeBin){


//Méthode récursive




}

ENCODAGE DE L’ ARBRE


Créer une chaîne de caractères pour chaque octet de la chaîne binaire passée en paramètre.


Place la chaîne d'octets dans un fichier nommé codeBin.txt.


private void bitEnOctet(String codeBin){


}

PARTIE DECODAGE



RECONSTRUCTION DU TEXTE
ENCODÉ



Reconstruis le texte à partir du code binaire et des nœuds. En évitant que la chaîne binaire ne soit pas vide.
	

public void decodageHuff(String texteFreq, String charfile){

}


RECONSTRUCTION DE L’ARBRE



 Je parcours tous les caractères et vérifies si leur code binaire correspond au début du code binaire complet. 



private void reconsArbre(ArrayList<Noeud> noeuds, String codeBin){
}

Ensuite, je crée un fichier avec le texte décodé complet “txtDecompresse.txt"

example pratique: 
si nous prenons example de: “this is an example of a huffman tree” pour l’encoder 
on obtient: “
a 4
e 4
f 3
h 2
i 2
l 1
m 2
n 2
o 1
p 1
r 1
s 2
t 2
u 1
x 2” nombre de frequence de chaque caractere trouvé dans le text puis: “1010110000110001100100001110100101111101001011111000101011100110010000101110010011000011110110111011110001110111110001101110101010001010111100011001100100110010” code binaire du même texte. Mon programe est codé d’une maniere qui permetra d’enregistrer deux fichier l’un qui stokera le caractere et leur frequence “ascii_freq.txt” l’autre qui strokera le code binaire “codeBin.txt”.












