/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pila;

import Pila.*;

public class NodeStack<T> implements Stack<T>
{
    protected Node<T> top;    // reference to the head node
    protected int size;      // number of elements in stack

    public NodeStack()
    {
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(top == null)
            return true;
        return false;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException("Stack is empty.");
        return top.getElement();
    }

    @Override
    public void push(T element) {
        Node<T> v = new Node<T>(element, top);
        top = v;
        size++;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException("Stack is empty.");
        T temp = top.getElement();
        top = top.getNext();
        size--;
        return temp;
    }
}
