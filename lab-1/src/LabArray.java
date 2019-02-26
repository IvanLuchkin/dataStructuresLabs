import java.util.Scanner;

public class LabArray {

    public static int findMax(int[] array){
        int indexMax = 0;
        for(int counter = 0; counter < array.length; counter++) {
            if (array[counter] > array[indexMax]) {
                indexMax = counter;
            }
        }
        return indexMax;
    }

    public static int findMin(int [] array) {
        int indexMin = 0;
        for (int counter = 0; counter < array.length; counter++) {
            if (array[counter] < array[indexMin]) {
                indexMin = counter;
            }
        }
        return indexMin;
    }

    public static int[] replaceElements(int indexMax, int indexMin, int[] array) {
        int temp = array[indexMax];
        array[indexMax] = array[indexMin];
        array[indexMin] = temp;
        return array;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the size of the stack");
        Stack stack = new Stack(input.nextInt());
        System.out.println("Input the lower edge of the range:");
        int lowerEdge = input.nextInt();
        System.out.println("Input the top edge of the range:");
        int topEdge = input.nextInt();

        stack.randPush(lowerEdge,topEdge);

        stack.printStack(stack.stackArray);
        System.out.println();
        if(!stack.isEmpty()) {
            replaceElements(findMax(stack.stackArray), findMin(stack.stackArray), stack.stackArray);
        } else System.out.println();
        stack.printStack(stack.stackArray);
        input.close();
    }

}
