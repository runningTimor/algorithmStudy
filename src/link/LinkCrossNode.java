package link;

//两个单链表相交节点
public class LinkCrossNode {

    public static void main(String[] args) {

    }

    //返回单链表的第一个入环节点，无环则返回null
    public static LinkNode getLoopNode(LinkNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        LinkNode slowNode = head.next;
        LinkNode quickNode = head.next.next;

        while (quickNode != slowNode) {
            if (quickNode.next == null || quickNode.next.next == null) {
                return null;
            }
            slowNode = slowNode.next;
            quickNode = quickNode.next.next;
        }
        quickNode = head;
        while (quickNode != slowNode) {
            quickNode = quickNode.next;
            slowNode = slowNode.next;
        }
        return quickNode;
    }

    public static LinkNode getNoLoopListCrossNode(LinkNode head1, LinkNode head2) {
        int length1 = 0;
        int length2 = 0;
        LinkNode current1 = head1;
        LinkNode current2 = head1;
        while (current1 != null) {
            current1 = current1.next;
            length1++;
        }
        while (current2 != null) {
            current2 = current2.next;
            length2++;
        }
        //判断两个单链表尾节点是否相等
        if (current1 != current2) {
            return null;
        }
        int startLength = Math.abs(length1 - length2);
        LinkNode longestNode = length1 > length2 ? head1 : head2;
        LinkNode shorterNode = length1 > length2 ? head2 : head1;
        while (startLength-- > 0) {
            longestNode = longestNode.next;
        }
        while (longestNode != shorterNode) {
            longestNode = longestNode.next;
            shorterNode = shorterNode.next;
        }
        return shorterNode;
    }

    public static LinkNode getBothLoopCrossNode(LinkNode head1, LinkNode head2, LinkNode loopNode1, LinkNode loopNode2) {
        //两个单链表在入环之前相交
        if (loopNode1 == loopNode2) {
            int diffLength = 0;
            LinkNode current1 = head1;
            LinkNode current2 = head1;
            while (current1 != loopNode1) {
                current1 = current1.next;
                diffLength++;
            }
            while (current2 != loopNode2) {
                current2 = current2.next;
                diffLength--;
            }
            LinkNode longestNode = diffLength > 0 ? head1 : head2;
            LinkNode shorterNode = diffLength > 0 ? head2 : head1;
            diffLength = Math.abs(diffLength);
            while (diffLength-- > 0) {
                longestNode = longestNode.next;
            }
            while (longestNode != shorterNode) {
                longestNode = longestNode.next;
                shorterNode = shorterNode.next;
            }
            return shorterNode;

        } else {
            LinkNode temp = loopNode1.next;
            while (temp != loopNode1) {
                //两个有环链表相交在入环节点
                if (temp == loopNode2) {
                    return loopNode1;
                }
                loopNode1 = loopNode1.next;
            }
            //两个有环链表不相交
            return null;
        }
    }

    public static LinkNode getCrossNode(LinkNode head1, LinkNode head2) {
        LinkNode loopNode1 = getLoopNode(head1);
        LinkNode loopNode2 = getLoopNode(head2);
        //1、判断两个无环单链表是否相交
        if (loopNode1 == null && loopNode2 == null) {
            return getNoLoopListCrossNode(head1, head2);
        }
        //2、两个单链表中只有一个有环，则不会相交
        if (loopNode1 == null || loopNode2 == null) {
            return null;
        }
        //3、两个单链表都有环
        return getBothLoopCrossNode(head1, head2, loopNode1, loopNode2);
    }


    public static class LinkNode {
        public LinkNode next;
        public int value;
        public LinkNode(int value) {
            this.value = value;
        }
    }


}
