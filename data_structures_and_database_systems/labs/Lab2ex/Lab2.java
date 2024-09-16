// COMP5112 Lab 2

class Item {
	int key;
	Item prev;
	Item next;
	
	Item(int k) {
		key=k;
		prev=null;
		next=null;
	}	
}

// 2) 

class LinkedList {

	Item head;

	LinkedList() {
		head=null;
	}
	
	Item Search(int k) {
		Item x=head;
		while (x!=null && x.key!=k)
			x=x.next;
		return x;
	}

	void Insert(Item x) {
		x.prev=null;
		x.next=head;
		if (head!=null)
			head.prev=x;
		head=x;
	}
	
	void Delete(Item x) {
		if (x.prev!=null)
			x.prev.next=x.next;
		else
			head=x.next;
		if (x.next!=null)
			x.next.prev=x.prev;
	}
	
	//prints  all the keys in the  linked list
	void Print() {
		System.out.print("Contents in the linked list:[");
		//Q1: _________
		Item x = head;
		while (x != null) {
			System.out.print(x.key);
			System.out.print(", ");
			x = x.next;
		}
		System.out.println("\t]");
	}
	
	//reverses the order of  items in the linked list.
	void Reverse() {
		//Q2: _________
		Item rec = head;
		Item prev = head.prev;
		while(rec != null) {
			Item next = rec.next;
			rec.prev = next;
			rec.next = prev;
			prev = rec;
			rec = next;
		}
		head = prev;

	}
	
}


public class Lab2 {
	static void TestPrint(){
		LinkedList L=new LinkedList();
		
		Item a=new Item(3);
		Item b=new Item(2);
		Item c=new Item(5);
		Item d=new Item(8);
		Item e=new Item(1);
		
		L.Insert(a);
		L.Insert(b);
		L.Insert(c);
		L.Insert(d);
		L.Insert(e);
		
		//test Print()
		L.Print();
		System.out.println("\n*** TestPrint method finished *** \n");
	}
	
	static  void TestReverse(){
		LinkedList L=new LinkedList();
		
		Item a=new Item(3);
		Item b=new Item(2);
		Item c=new Item(5);
		Item d=new Item(8);
		Item e=new Item(1);
		
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
	
	public static void main(String[] args) {
		TestPrint();
		TestReverse();
	}

}

// Q3
// Push: L.insert(value)
// Pop: L.delete(L.head)
