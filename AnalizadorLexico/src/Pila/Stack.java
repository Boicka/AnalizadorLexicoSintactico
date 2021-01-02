/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pila;

import java.util.EmptyStackException;

public interface Stack<T>
{
    /**
    * Return the number of elements in the stack.
    */
    public int size();

    /**
     * Return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Return element at the top of the stack.
     * Throws EmptyStackException if the stack is empty.
     */
    public T top()
        throws EmptyStackException;

    /**
     * Insert an element at the top of the stack.
     */
    public void push(T element);

    /**
     * Remove the top element from the stack.
     * Throws EmptyStackException if the stack is empty.
     */
    public T pop()
        throws EmptyStackException;
}
