package oldcode;

public class ListTest {
    static class Node {
        int val;
        Node next;
    }

    public static void main(String[] args) {
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();
        Node n5 = new Node();
        Node n6 = new Node();
        n1.val = 1; n2.val = 2; n3.val = 3; n4.val = 4; n5.val = 5; n6.val = 6;
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5; n5.next = n6;
        n6.next = n3;
        func(n1);
    }


    public static void func(Node head) {
        Node slow = head, fast = head;
        while (true) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null)
                fast = head;
            fast = fast.next;
            if (slow == fast) {
                System.out.println(fast.val);
                break;
            }
            else if (fast == null)
                fast = head;
        }
        if (fast != null) {
            fast = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        System.out.println(slow == null ? -1 : slow.val);
    }
}
