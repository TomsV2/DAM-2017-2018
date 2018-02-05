/*
 * 
 * 
 */

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class fHombresLobo {
	
	
	
/**************************************************************************	
Interfaz
	Comentario: Subprograma que simulará el juego de los hombres lobos
	Cabecera: void JugarHombresLobo()
	Precondiciones: No tiene
	Entrada: No hay
	Salida: No hay
	E/S: No hay
	Postcondiciones: No tiene
**************************************************************************/	
	
	public static void JugarHombresLobo(){
		
		int cantidadHL = 1;
		int cantidadCamp = 5;
		
		int posicionLobo = 0;
		
		int aleatorio = 0;
		int victimaAleatorio = 0;
		
		boolean salirArray = false;
		
		ArrayList <Carta> array6Cartas = new ArrayList <Carta> (6);			//<Carta> para que no te de un warning, se le tiene que especificar el tipo de los objetos que vas a usar
		ArrayList <Carta> arrayTablero = new ArrayList <Carta> (6);

		Carta espada1 = new Carta('e',1);

		Carta bastos1 = new Carta('b',1);
		Carta bastos2 = new Carta('b',2);
		Carta bastos3 = new Carta('b',3);
		Carta bastos4 = new Carta('b',4);
		Carta bastos5 = new Carta('b',5);

		array6Cartas.add(0,espada1);
		array6Cartas.add(1,bastos1);
		array6Cartas.add(2,bastos2);
		array6Cartas.add(3,bastos3);
		array6Cartas.add(4,bastos4);
		array6Cartas.add(5,bastos5);
		
		Random random = new Random();
		
		//Inicio
			//RepartirPersonajes
			do{
				if(array6Cartas.size() > 1){
					
					aleatorio = random.nextInt(array6Cartas.size()-1);
				
					arrayTablero.add(array6Cartas.get(aleatorio));
				
					array6Cartas.remove(aleatorio);
					
				}
				else{
				
					arrayTablero.add(array6Cartas.get(0));
					
					salirArray = true;
				}
				
				System.out.println(array6Cartas.size());	//Para comprobar que el tamaño del array va reduciendose
			}
			while(salirArray == false);
			
			//ImprimirCartaJugador
			System.out.println(arrayTablero.get(0).toString());
			//System.out.println(arrayTablero.get(0).equals(espada1));		//Para ver si el equals es true cuando al jugador le sale la carta de espada
			
			if(arrayTablero.get(0).equals(espada1) == true){
				while(cantidadHL < cantidadCamp && cantidadHL != 0){
					Noche(arrayTablero);
				}
			}
			else{
				
				while(cantidadHL < cantidadCamp && cantidadHL != 0){
					
					for(int i=0; arrayTablero.get(i).equals(espada1) != true; i++){
						posicionLobo = i;
					}
					
					victimaAleatorio = random.nextInt(arrayTablero.size()-1);
					
					
				}
			}
			
			//Día*
		//Fin
	}
	
/**************************************************************************	
Interfaz
	Comentario: Simulará la noche cuando el jugador sea el lobo (espada1)
	Cabecera: ArrayList<Carta> Noche(ArrayList <Carta> array)
	Precondiciones: en su primera iteracion el tamaño del array será 5, cada siguiente iteración será el tamaño del array - 1
	Entrada: No tiene
	Salida: No tiene
	E/S: Un ArrayList<Carta>
	Postcondiciones: el array tendrá el tamaño de entrada - 1
**************************************************************************/	
	
	public static ArrayList<Carta> Noche(ArrayList<Carta> arrayTablero){
		
		int victima = 0; 
		
		Scanner teclado = new Scanner(System.in);
		
		//Inicio
			//PreguntarLeerValidarVictima
			do{
				for(int i=0; i < arrayTablero.size(); i++){
					System.out.println(arrayTablero.get(i).toString());
				}
				
				System.out.println(" ");
				
				System.out.println("Que victima quiere ejecutar?");
				System.out.println("Victima numero: ");
				
				victima = teclado.nextInt();
			}
			while(victima < 0 || victima > arrayTablero.size() - 1);
			
			//Matar victima
			arrayTablero.remove(victima);
			
		//Fin
		
		return arrayTablero;
	}
	
}

