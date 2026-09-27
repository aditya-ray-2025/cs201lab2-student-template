import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
       public void swap(){
        if (isEmpty()) {
            return;
        }
 
        List<E> entries = new ArrayList<>();
        Map<E, Node<E>> prevNode = new HashMap<>();
        Node<E> current = head;
        Node<E> previous = null;
        while (current != null) {
            entries.add(current.getElement());
            prevNode.put(current.getElement(), previous);
            previous = current;
            current = current.getNext();
        }
        Collections.sort(entries);
 
        int low = 0;
        int high = entries.size() - 1;
 
        while (low < high) {
            E lowValue = entries.get(low);
            E highValue = entries.get(high);

            Node<E> prevSmall;
            Node<E> small;
            if (head.getElement().equals(lowValue)) {
                prevSmall = null;
                small = head;
            } else {
                prevSmall = prevNode.get(lowValue);
                small = prevSmall.getNext();
            }

            Node<E> prevLarge;
            Node<E> large;
            if (head.getElement().equals(highValue)) {
                prevLarge = null;
                large = head;
            } else {
                prevLarge = prevNode.get(highValue);
                large = prevLarge.getNext();
            }
 
            if (small.getNext() == large) {
                Node<E> largeNext = large.getNext();
                if (prevSmall != null) {
                    prevSmall.setNext(large); 
                }
                else {
                    head = large;
                }
                large.setNext(small);
                small.setNext(largeNext);
                if (tail == large) {
                    tail = small;
                }
            } else if (large.getNext() == small) {
                Node<E> smallNext = small.getNext();
                if (prevLarge != null) {
                    prevLarge.setNext(small);
                } 
                else {
                    head = small;
                }
                small.setNext(large);
                large.setNext(smallNext);
                if (tail == small) {
                    tail = large;
                }
            } else {
                Node<E> smallNext = small.getNext();
                Node<E> largeNext = large.getNext();
 
                if (prevSmall != null) {
                    prevSmall.setNext(large);
                } 
                else {
                    head = large;
                }
                if (prevLarge != null) {
                    prevLarge.setNext(small); 
                }
                else {
                    head = small;
                }
 
                small.setNext(largeNext);
                large.setNext(smallNext);
 
                if (tail == small) {
                    tail = large;
                }
                else if (tail == large) {
                    tail = small;
                }
            }
 
            low++;
            high--;
        }
    }   

}
