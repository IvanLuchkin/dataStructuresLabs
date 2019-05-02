import java.util.ArrayList;

public class Handler {

    private static int findMax(ArrayList<Integer> list) {
        int max = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max) max = list.get(i);
        }
        return max;
    }

    public static void main(String...args) {
        for (int size = 1000; size <= 10000; size += 500) {
            RedBlackTree<Integer> tempRBTree = new RedBlackTree<>();
            AVLTree tempAVLTree = new AVLTree();
            for (int i = 0; i < size; i++) {
                int tempValue = (int) (Math.random() * size * 2);
                tempRBTree.add(tempValue);
                tempAVLTree.insert(tempValue);
            }
            System.out.println(findMax(tempRBTree.rotationCounters) + " : " + findMax(tempAVLTree.rotationCounters));
        }
    }

}
