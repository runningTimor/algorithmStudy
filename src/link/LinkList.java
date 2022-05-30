package link;

import sun.awt.image.ImageWatched;

//链表
public class LinkList {

    public static LinkNode reverse(LinkNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkNode pre = null;
        LinkNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    //判断链表是否回文
    public static boolean palindromeLinkList(LinkNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //快慢指针，
        LinkNode quickNode = head;
        LinkNode slowNode = head;
        while (quickNode.next != null && quickNode.next.next != null) {
            quickNode = quickNode.next.next;
            slowNode = slowNode.next;
        }

        //将链表后半段反转
        LinkNode pre = null;
        //链表后半段头节点
        LinkNode cur = slowNode.next;
        LinkNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //保存链表最后一个节点
        LinkNode endNode = pre;

        while (pre.next != null) {
            if (pre.value != head.value) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }
        //恢复链表(从中间节点开始)
        cur = endNode;
        pre = null;
        while (cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        slowNode.next = pre;
        return true;

    }

    public static void main(String[] args) {

         LinkNode node1 = new LinkNode(1);
         LinkNode node2 = new LinkNode(2);
         LinkNode node3 = new LinkNode(3);
         LinkNode node4 = new LinkNode(3);
         LinkNode node5 = new LinkNode(2);
         LinkNode node6 = new LinkNode(1);
         node1.next = node2;
         node2.next = node3;
         node3.next = node4;
         node4.next = node5;
         node5.next = node6;
         node6.next = null;

         System.out.println(palindromeLinkList(node1));

    }

    public static class LinkNode {
        public LinkNode pre;
        public LinkNode next;
        public int value;

        public LinkNode(int value) {
            this.value = value;
        }
    }

}
