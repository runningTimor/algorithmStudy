package link;

//链表节点包含一个随机指针和next指针
public class RandomIndexLinkNode {

    //copy一个新的链表
    public static Node copy(Node head) {
        Node current = head;
        Node oldNext = null;
        while (current != null) {
            oldNext = current.next;
            current.next = new Node(current.value);
            current.next.next = oldNext;
            current = oldNext;
        }
        current = head;
        while (current != null) {
            current.next.random = current.random == null ? null : current.random.next;
            current = current.next.next;
        }
        current = head;
        Node resHead = current.next;
        while (current != null) {
            Node newNext = current.next;
            current.next = newNext.next;

            newNext.next = current.next == null ? null : current.next.next;
            current = current.next;
        }
        return resHead;
    }

    public static void main(String[] args) {

    }

    public static class Node {
        public Node next;
        public Node random;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

}
