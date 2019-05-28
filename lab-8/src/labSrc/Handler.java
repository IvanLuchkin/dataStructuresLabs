package labSrc;

import java.util.Scanner;

public class Handler {

    public static void main(String...args) {
        Scanner input = new Scanner(System.in);
        int source = input.nextInt();
        int sink = input.nextInt();
        Net net = new Net(10, source, sink);
        net.addArc(4,5,17);
        net.addArc(5,4,17);
        net.addArc(0,5,8);
        net.addArc(5,0,8);
        net.addArc(0,6,55);
        net.addArc(6,0,55);
        net.addArc(0,4,5);
        net.addArc(4,0,5);
        net.addArc(6, 8, 22);
        net.addArc(8,6,22);
        net.addArc(8,3,16);
        net.addArc(3,8,16);
        net.addArc(3,1,15);
        net.addArc(1,3,15);
        net.addArc(4,1,72);
        net.addArc(1,4,72);
        net.addArc(4,2,16);
        net.addArc(2,4,16);
        net.addArc(5,9,2);
        net.addArc(9,5,2);
        net.addArc(5,7,5);
        net.addArc(7,5,5);
        net.addArc(7,2,21);
        net.addArc(2,7,21);
        net.addArc(1,2,11);
        net.addArc(2,1,11);
        net.addArc(2,9,22);
        net.addArc(9,2,22);
        net.addArc(6,5,27);
        net.addArc(5,6,27);
        net.addArc(6,7,12);
        net.addArc(7,6,12);


        int s = new FordFulkerson(net).fulkerson(source, 0);
        System.out.println(s);
    }

}