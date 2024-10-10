
class Item {
    int key;
    Item prev;
    Item next;

    Item(int k) {
        key = k;
        prev = null;
        next = null;
    }

}

class LinkedList {

    Item head;

    LinkedList() {
        head = null;
    }

    Item Search(int k) {
        Item x = head;
        while (x != null && x.key != k)
            x = x.next;
        return x;
    }

    void Insert(Item x) {
        x.prev = null;
        x.next = head;
        if (head != null)
            head.prev = x;
        head = x;
    }

    void Delete(Item x) {
        if (x.prev != null)
            x.prev.next = x.next;
        else
            head = x.next;
        if (x.next != null)
            x.next.prev = x.prev;
    }

    //prints  all the keys in the  linked list
    void Print() {
        System.out.print("Contents in the linked list:[");
        Item x = head;
        while (x != null) {
            System.out.print("\t" + x.key);
            x = x.next;
        }
        System.out.println("\t]");
    }

    //reverses the order of  items in the linked list.
    void Reverse() {
        Item x = head;
        while (x != null) {

            //switch the prev pointer and next pointer of each item
            Item temp = x.prev;
            x.prev = x.next;
            x.next = temp;

            head = x;    //change the head item
            x = x.prev;    //move to next node
        }
    }

}

class StackSimulation {
    LinkedList linkedList;

    StackSimulation() {
        linkedList = new LinkedList();
    }

    void Push(Item x) {
        //the push method of linked list is the same as stack
        //all follows the first in last out (FILO) rules
        linkedList.Insert(x);
    }

    Item Pop() {
        //since FILO rules, pop stack means delete the head
        Item head = linkedList.head;
        linkedList.Delete(head);
        return head;
    }

    void Print() {
        linkedList.Print();
    }
}


public class Lab2Ans {
    static void TestPrint() {
        //Question 1
        LinkedList L = new LinkedList();

        Item a = new Item(3);
        Item b = new Item(2);
        Item c = new Item(5);
        Item d = new Item(8);
        Item e = new Item(1);

        L.Insert(a);
        L.Insert(b);
        L.Insert(c);
        L.Insert(d);
        L.Insert(e);

        //test Print()
        L.Print();
        System.out.println("\n*** TestPrint method finished *** \n");
    }

    static void TestReverse() {
        //Question 2
        LinkedList L = new LinkedList();

        Item a = new Item(3);
        Item b = new Item(2);
        Item c = new Item(5);
        Item d = new Item(8);
        Item e = new Item(1);

        L.Insert(a);
        L.Insert(b);
        L.Insert(c);
        L.Insert(d);
        L.Insert(e);

        //test Reverse()
        L.Reverse();
        L.Print();
        System.out.println("\n*** TestReverse method finished *** \n");
    }

    static void TestStackSimulation() {
        //Question 3
        StackSimulation S = new StackSimulation();

        Item a = new Item(3);
        Item b = new Item(2);
        Item c = new Item(5);
        Item d = new Item(8);
        Item e = new Item(1);

        S.Push(a);
        S.Push(b);
        S.Push(c);
        S.Push(d);
        S.Push(e);
        S.Print();

        S.Pop();
        S.Pop();
        S.Pop();
        S.Print();
        System.out.println("\n*** TestStackSimulation method finished *** \n");
    }
	

    public static void main(String[] args) {
        TestPrint();
        TestReverse();
        TestStackSimulation();
    }

}