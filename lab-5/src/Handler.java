/**************************HANDLING CLASS*************************/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Handler {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("/home/ivan/IdeaProjects/dataStructuresLabs/lab-5/src/text");
        Scanner input = new Scanner(new FileReader(inputFile));
        String text = input.nextLine();

        int[] charFrequencies = new int[text.length() * 2];
        for (char c : text.toCharArray()) {
            charFrequencies[c]++;
        }

        HuffmanTree tree = HuffmanTree.buildHuffmanTree(charFrequencies);
        System.out.printf("size before compression = %d%n", text.length() * 8);
        String encoded = tree.encoded(text);
        System.out.println("incoded result = " + encoded);
        System.out.printf("size after compression = %d%n", encoded.length());
        String decoded = tree.decode(encoded, tree);
        System.out.println("Result of decoding: " + decoded);

        tree.printCodes();
        System.out.println("The average leaf depth is : " + tree.sumDepths / tree.uniqueSymbolsCounter);
        System.out.println("The depth of he tree is: " + tree.treeDepth);
        System.out.println(tree.counter);
    }

}