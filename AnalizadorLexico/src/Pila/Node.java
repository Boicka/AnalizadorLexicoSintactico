/*
Nodo.java
Instituto Tecnológico de León
Ingenieria en sistemas computacionales
Lenguajes y autómatas 1
Lunes y Miercoles 8:45 - 10:25
Viernes 8:45 - 9:35
Alumnos: Héctor Medel Negrete
         Juan de Dios Yebra Navarro
Version 1.0
 */
package Pila;
public class Node<T>
{
    private T element;
    private Node<T> next;

    /**
     * Creates a node with null references to its element and next node
     */
    public Node()
    {
        this(null, null);
    }

    /**
     * Creates a node with the given element and next node
     */
    public Node(T e, Node<T> n)
    {
        element = e;
        next = n;
    }

    /**
     * Accessor methods
     */
    public T getElement()
    {
        return element;
    }

    public Node<T> getNext()
    {
        return next;
    }

    /**
     * Modifier methods
     */
    public void setElement(T newElem)
    {
        element = newElem;
    }

    public void setNext(Node<T> newNext)
    {
        next = newNext;
    }
}