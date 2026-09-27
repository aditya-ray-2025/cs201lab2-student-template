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

        List<Node<E>> order = new ArrayList<>(size);
        List<E> sortedOrder = new ArrayList<>(size);
        Node<E> current = head;
        while (current != null) {
            order.add(current);
            sortedOrder.add(current.getElement());
            current = current.getNext();
        }
        Collections.sort(sortedOrder);
        int n = order.size();

        Integer[] sortedPositions = new Integer[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j<n; j++) {
                if (sortedOrder.get(i).equals(order.get(j).getElement())) {
                    sortedPositions [i] = j; 
                }
            }
        }

        int lo = 0, hi = n - 1;
        while (lo < hi) {
            int posOfSmall = sortedPositions[lo];
            int posOfLarge = sortedPositions[hi];

            Node<E> temp = order.get(posOfSmall);
            order.set(posOfSmall, order.get(posOfLarge));
            order.set(posOfLarge, temp);

            lo++;
            hi--;
        }

        head = order.get(0);
        Node<E> prev = head;
        for (int i = 1; i < n; i++) {
            Node<E> next = order.get(i);
            prev.setNext(next);
            prev = next;
        }
        prev.setNext(null);
        tail = prev;
    }
}
