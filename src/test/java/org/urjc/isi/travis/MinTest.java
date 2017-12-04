package org.urjc.isi.travis;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
// Los test los realizamos sobre la especificacion no sobre el codigo -- TEST de caja negra
public class MinTest
{
   private List<String> list;   // Test fixture

   @Before      // Set up - Called before every test method.// Creamos la clase para testar el metodo min (Banco de pruebas )
   public void setUp()
   {
      list = new ArrayList<String>();
   }

   @After      // Tear down - Called after every test method. // Es un post procesado para dejar en un cierto estado tras realizar las pruebas
   public void tearDown()
   {
      list = null;  // redundant in this example!
   }

   @Test
   public void testForNullList() // es igual que un (expected = NullPointerException.class)
   // No usamos assert__ ya que no comparamos resultados. Queremos en este caso obtener una excepcion
   {
      list = null;
      try {
         Min.min (list);
      } catch (NullPointerException e) {
         return;
      }
      fail ("NullPointerException expected");
   }

   @Test (expected = NullPointerException.class) // lo que espera es la excepcion -- es un try{..}catch{..}
   public void testForNullElement()
   {
     // queremos probar que haya mas leementos en la lista pero que si hay alguno que sea null  nos da el fallo
      list.add (null);
      list.add ("cat"); // preparamos el test
      Min.min (list); // aqui realizamos el test
   }

   @Test (expected = NullPointerException.class) // nos da un error -- tenemos que modificar el codigo
   public void testForSoloNullElement()
   {
      list.add (null);
      Min.min (list);
   }

   @Test (expected = ClassCastException.class) //
   @SuppressWarnings ("unchecked")
   public void testMutuallyIncomparable()
   {
      List list = new ArrayList();
      list.add ("cat");
      list.add ("dog");
      list.add (1);   // un  int no implementa la interfaz comparable --- En Min simepre espera un tipo T que extends Comparable
                      // No deberia compilar.. pero los genericos solo comprueban los datos en tiempo de compilacion por tanto al ser ejecutado si nos lo permiten
      Min.min (list); // En tiempo de ejecucion nos da el fallo de que estamos comparando tipos que no con comparable (int con los strings )
   }
   @Test (expected = IllegalArgumentException.class)
   public void testEmptyList()
   {
      Min.min (list);
   }

   // HAPPY PATH -- Situaciones que sabemos lo que va a salir

   @Test
   public void testSingleElement()
   {
	   list.add ("cat");
	   Object obj = Min.min (list);
	   assertFalse ("Single Element List", obj.equals ("cat")); // assertTrue -- funciona
   }

   @Test
   public void testDoubleElement()
   {
      list.add ("dog");
      list.add ("cat");
      Object obj = Min.min (list);
      assertTrue ("Double Element List", obj.equals ("cat"));
   }
}
