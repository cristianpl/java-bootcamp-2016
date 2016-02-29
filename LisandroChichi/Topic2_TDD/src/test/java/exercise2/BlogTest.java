package exercise2;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlogTest {
	Scanner sc = new Scanner(System.in);
	ArrayList<Post> listPosts = new ArrayList<Post>();
	BlogDao blogDao = new BlogDao();
	Blog mockBlog = mock(Blog.class);
	int cont = 5;

	@Before
	public void setUp() {

		Post post1 = new Post(
				"Lunes",
				"Lunes es el segundo d�a de la semana en el calendario gregoriano, y primero de la semana laboral",
				"Lisandro");
		Post post2 = new Post(
				"Martes",
				"El martes es el segundo o tercer d�a de la semana, seg�n el pa�s o cultura.",
				"Lisandro");
		Post post3 = new Post(
				"Miercoles",
				"El mi�rcoles era el cuarto d�a de la semana en la cultura cristiana original (siendo el primero el domingo) y es el tercero en las nuevas culturas.",
				"Lisandro");
		Post post4 = new Post(
				"Jueves",
				"El jueves es el cuarto d�a de la semana. Sigue al mi�rcoles y precede al viernes. El nombre de �jueves� proviene del lat�n Jovis d�es, o �d�a de J�piter�.",
				"Lisandro");
		Post post5 = new Post(
				"Viernes",
				"El viernes es el quinto d�a de la semana. El nombre de �viernes� proviene del lat�n Veneris dies; 'd�a de Venus' (la diosa de la belleza y el amor en la mitolog�a romana, en la mitolog�a n�rdica esa diosa se llama Freyja).",
				"Lisandro");
		Post post6 = new Post(
				"Enero",
				"Enero (del lat�n ianuarius--> janairo --> janero --> enero) es el primer mes del a�o en el calendario gregoriano y tiene 31 d�as. Toma su nombre del dios Jano, del lat�n Janus, representado con dos caras, el esp�ritu de las puertas y del principio y el final",
				"Lisandro");
		Post post7 = new Post(
				"Febrero",
				"Febrero es el segundo mes del a�o en el calendario gregoriano. Tiene 28 d�as y 29 en los a�os bisiestos. ",
				"Lisandro");
		Post post8 = new Post(
				"Marzo",
				"Marzo es el tercer mes del a�o en el calendario gregoriano y tiene 31 d�as. Su nombre deriva del lat�n Martius, que era el primer mes del calendario romano",
				"Lisandro");
		Post post9 = new Post(
				"Abril",
				"Abril es el cuarto mes del a�o juliano y gregoriano y es uno de los cuatro meses que tienen 30 d�as",
				"Lisandro");
		Post post10 = new Post(
				"Mayo",
				"Mayo es el quinto mes del a�o en el calendario gregoriano y tiene 31 d�as; pero era el tercer mes en el antiguo calendario romano donde enero y febrero estaban al final del a�o.",
				"Lisandro");
		Post post11 = new Post(
				"Junio",
				"Junio (del lat�n Iunius, mes de Juno) es el sexto mes del a�o en el Calendario Gregoriano y tiene 30 d�as.",
				"Lisandro");

		listPosts.add(post1);
		listPosts.add(post2);
		listPosts.add(post3);
		listPosts.add(post4);
		listPosts.add(post5);
		listPosts.add(post6);
		listPosts.add(post7);
		listPosts.add(post8);
		listPosts.add(post9);
		listPosts.add(post10);
		listPosts.add(post11);
		mockBlog.deletePost(0);

		verify(mockBlog, times(1)).deletePost(0);// It is shown that has been
													// used once the method
													// deletePost()

		// My App starts HERE

		System.out.println("Seleccione una opcion");
		System.out.println("Agregar post -- Seleccione '1'");
		System.out.println("Eliminar post -- Seleccione '2'");
		System.out
				.println("Mostrar los 10 posts mas recientes -- Seleccione '3'");
		System.out.println("Seleccione '4' para SALIR \n");
		int res = sc.nextInt();

		while (res != 4) {

			if (res == 1) {
				blogDao.addPosts(sc, listPosts, mockBlog);
				cont++;

				System.out.println("Agregar post -- Seleccione '1'");
				System.out.println("Eliminar post -- Seleccione '2'");
				System.out.println("Mostrar posts -- Seleccione '3'");
				System.out.println("Seleccione '4' para SALIR \n");
				res = sc.nextInt();
			}

			else if (res == 2) {
				if(listPosts.size()>0){
				blogDao.deletePosts(sc, listPosts, mockBlog);
				}else
				{
					System.out.println("no hay posts en el blog");
				}
				cont--;
				System.out.println("Agregar post -- Seleccione '1'");
				System.out.println("Eliminar post -- Seleccione '2'");
				System.out.println("Mostrar posts -- Seleccione '3'");
				System.out.println("Seleccione '4' para SALIR \n");
				res = sc.nextInt();

			} else if (res == 3) {

				blogDao.showPosts(listPosts, sc, mockBlog);

				System.out.println("\n");
				System.out.println("Agregar post -- Seleccione '1'");
				System.out.println("Eliminar post -- Seleccione '2'");
				System.out.println("Seleccione '4' para SALIR \n");
				res = sc.nextInt();
			}
		}

	}

	@Test
	public void ListIsNotEmpty() {
		assertFalse(listPosts.isEmpty());
		assertEquals(cont, listPosts.size());
		assertNotNull(listPosts);
		

	}

	@After
	public void tearDown() {
		listPosts = null;
		mockBlog = null;
	}

}
