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
        Node<E> current = head;
        List <E> entries = new ArrayList<>();
        while (current != null) {
            entries.add(current.getElement());
            current = current.getNext();
        }
        Collections.sort(entries);
        int low = 0;
        int high = entries.size()-1;
        while (low <= high) {
            current = head;
            while (current.getNext().getElement() != entries.get(low)) {
                current = current.getNext();
            }
            Node<E> small = current.getNext();
            Node<E> one_before_small = current;
            current = head;
            while (current.getNext().getElement() != entries.get(high)) {
                current = current.getNext();
            }
            Node<E> large = current.getNext();
            Node<E> one_before_large = current;
            large.setNext(small.getNext());
            small.setNext(large.getNext());
            one_before_small.setNext(large);
            one_before_large.setNext(small);
            low ++;
            high --;            
        }
    }
   
}

