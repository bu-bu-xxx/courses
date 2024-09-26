// Name       : ZHONG Qiaoyang
// Student ID : 24112456g
//// Complete your code for Question 5 in this file
// note: the number of the underscore symbol (_) does not represent the number of
//     characters that you need to fill


class Node {
    String key;
    Node left;
    Node right;

    public Node(String key) {
        // Fill the gaps
        this.key = key;
        this.left = null;
        this.right = null;
    }
}

class Traversal {
    public static void preOrder(Node  node) {

        // implement the code for pre-order traversal
        // hints: about 4 lines
        if (node == null) return;
        System.out.print(node.key + "  ");
        preOrder(node.left);
        preOrder(node.right);
    }
}

// Change <yourStudentID> for both class and file
public class A1_Q5_24112456g {

    public static void main(String[] args) {

        // Based on the node class, create node objects of the given tree.
        // hints: less than 10 lines of code.
        Node root = new Node("T");
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");

        // connect the nodes as the tree given.
        // hints:less than 10 lines of code.
        root.left = nodeA;
        root.right = nodeB;
        nodeA.left = nodeC;
        nodeA.right = nodeD;
        nodeB.left = nodeE;
        nodeB.right = nodeF;
        nodeD.right = nodeH;
        nodeF.left = nodeG;

        //Call preorder traversal function
        // fill the gap
        Traversal.preOrder(root);

    }
}
