public class LabLinkedList {

   public static void replaceMinMax(LinkedListStack stack) {
       LinkedListStack temp = new LinkedListStack();
       Node max = stack.top();
       Node min = stack.top();

       while(!stack.isEmpty()) {
           Node node = stack.pop();
           if (node.value > max.value) {
               max = node;
           }
           if (node.value < min.value) {
               min = node;
           }
           temp.push(new Node(node.value));
       }

       while(!temp.isEmpty()) {
           Node node = temp.pop();

           if (node.value == max.value) {
               stack.push(new Node(min.value));
           } else if (node.value == min.value) {
               stack.push(new Node(max.value));
           } else {
               stack.push(new Node(node.value));
           }
       }
   }

    public static void main(String[] args) {
        LinkedListStack listStack = new LinkedListStack();
        listStack.push(new Node(10)); //15
        listStack.push(new Node(20)); //10
        listStack.push(new Node(30)); //30
        listStack.push(new Node(70)); //20
        listStack.push(new Node(15)); //70

        replaceMinMax(listStack);
        listStack.printStack(listStack);
    }

}