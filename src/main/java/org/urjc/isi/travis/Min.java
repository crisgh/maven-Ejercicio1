package org.urjc.isi.travis;

/**
 * Hello world!
 *
 */
//Introduction to Software Testing
//Authors: Paul Ammann & Jeff Offutt
//Chapter 3; page ??
//See MinTest.java for JUnit tests.
//See DataDrivenMinTest.java for JUnit tests. (Instructor only)

import java.util.*;

public class Min
{
/**
 * Returns the mininum element in a list
 * @param list Comparable list of elements to search
 * @return the minimum element in the list
 * @throws NullPointerException if list is null or
 *         if any list elements are null
 * @throws ClassCastException if list elements are not mutually comparable
 * @throws IllegalArgumentException if list is empty
 */
 public static <T extends Comparable<? super T>> T min (List<? extends T> list) // elemento mas pequeñoi de la coleccion LIST
 // Los genericos se comprueban en tiempo de compilacion, luego en tiempo de ejecucion podemos usar tipos (primitivos) sin que nos de fallos al compilar -- compilacion hacia atras
 // le pasamos una lista que sea extienda T
 // T es un generico siempre que su interfaz extienda Comparable.
 // Por tanto nos devuelve un valor que sea T o que herede de T.

 {
    if (list.size() == 0)
    {
       throw new IllegalArgumentException ("Min.min");
    }

    Iterator<? extends T> itr = list.iterator();
    T result = itr.next();
    // Codigo nuevo
    if(result == null) // mira el primer elemento
    {
      throw new NullPointerException("result"); // Creamos la excepcion cuando un itr.next( ) sea nulo. No tenia en cuenta si SOLO teniamos un valor que fuera null
    }
    //

    while (itr.hasNext())
    {   // throws NPE, CCE as needed
        T comp = itr.next();
        if (comp.compareTo (result) < 0)
        {
            result = comp;
        }
    }
    return result;
 }

}
