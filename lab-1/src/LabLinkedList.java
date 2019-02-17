public class LabLinkedList {

   public static Node findMax(LinkedListStack stack) {
       LinkedListStack stackTemp = stack;
       System.out.println(stackTemp.top == null);
       Node max = new Node(0);
       while (stackTemp.top != null) {
           if(stackTemp.top.value >= max.value) {
               max.value = stackTemp.top.value;
           }
           stackTemp.pop();
       }
       System.out.println(max.value);
       return max;
   }

   public static Node findMin(LinkedListStack stack) {
       LinkedListStack stackTemp = stack;
       Node min = stackTemp.top;
       System.out.println(stackTemp.top.value);
       while (stackTemp.top != null) {
           if(stackTemp.top.value <= min.value) {
               min.value = stackTemp.top.value;
           }
           stackTemp.pop();
       }
       System.out.println(min.value);
       return min;
   }

    public static void main(String[] args) {
        LinkedListStack listStack = new LinkedListStack();
        listStack.push(new Node(10));
        listStack.push(new Node(20));
        listStack.push(new Node(30));
        listStack.push(new Node(70));
        listStack.push(new Node(15));



        findMin(listStack);
        findMax(listStack);


    }

}