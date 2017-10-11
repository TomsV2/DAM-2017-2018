/*
 * Nombre: DadosApuesta
 * 
 * Comentario: Se escoge un numero entre 2 y 12, se apuesta una cantidad de dinero y se comparan los resultados
 * 
 * Análisis:
 * 		-Entra:
 * 		-Salida:
 * 
 * PG:
 * 		Inicio
 * 			Hacer
 * 				Escoger dinero incial
 * 
 * 				Generar numero aleatorio para la opcion del Pc
 * 				Escoger un numero (Opcion del Jugador)
 * 
 * 				Apuesta Pc
 * 				Apuesta Jugador
 * 
 * 				Generar aleatorioPc1 (dado 1)
 * 				Generar aleatorioPc2 (dado 2)
 * 				Sumar aleatorios de Pc (sumaDadosPc)
 * 
 * 				Generar aleatorioJugador1 (dado 1)
 * 				Generar aleatorioJugador2 (dado 2)
 * 				Sumar aleatorios de Jugador (sumaDadosJugador)
 * 
 * 				Resta opcionPc menos sumaDadosPc
 * 				Si restaPc < 0
 * 					restaPc * -1
 * 				Fin_Si
 * 
 * 				Resta opcionJugador menos sumaDadosJugador
 * 				Si restaJugador < 0
 * 					restaJugador * -1
 * 				Fin_Si
 * 
 * 				Si restaPc y restaJugador es igual
 * 					Empate
 * 				Fin_Si
 * 
 * 				Sino
 * 
 * 					Si restaPc > restaJugador (Gana Jugador)
 * 						Al jugador se le suma la apuesta
 * 						Al Pc se le resta la apuesta
 * 					Fin_Si
 * 
 * 					Sino (Gana Pc)
 * 						Al jugador se le resta la apuesta
 * 						Al Pc se le suma la apuesta
 * 					Fin_Sino
 * 
 * 				Fin_Sino
 * 
 * 				Preguntar para repetir
 * 
 * 			Mientras DineroJugador no sea 0 o DineroPc no sea 0 y se quiera jugar otra vez (repetir)
 * 		Fin
 * 
 */

import java.util.*;

public class DadosApuesta {
	
	public static void main (String[] args) {
		
		//Declaraciones
		char respuesta = ' ';
		
		int dineroJugador = 0;
		int dineroPc = 0;
		
		int opcionPc = 0;
		int opcionJugador = 0;
		
		int apuestaPc = 0;
		int apuestaJugador = 0;
		
		int sumaDadosPc = 0;
		int sumaDadosJugador = 0;
		
		int aleatorioPc1 = 0;
		int aleatorioPc2 = 0;
		int aleatorioJugador1 = 0;
		int aleatorioJugador2 = 0;
		
		int restaPc = 0;
		int restaJugador = 0;
		
		//Inicializaciones
		Scanner teclado = new Scanner (System.in);
		
		Random random = new Random ();
		
		//Inicio
		
			//Preguntar, leer y validar dinero incial
			do{
				System.out.print("Introduzca su dinero inicial: ");
				
				dineroJugador = teclado.nextInt();
				
				dineroPc = dineroJugador;
			}
			while(dineroJugador < 0);
				
			//Hacer
			do{
				//Generar numero aleatorio para la opcion del Pc
				opcionPc = random.nextInt(10) +2;
				
				//Escoger un numero (Opcion del Jugador)
				System.out.print("Elija el numero que cree que saldra en los dados: ");
				
				opcionJugador = teclado.nextInt();
				
				//Apuesta Pc
				apuestaPc = random.nextInt(dineroPc) +1;
				
				System.out.println("La puesta del Pc es: " +apuestaPc);
				
				//Apuesta Jugador
				do{
					System.out.print("Introduzca su apuesta: ");
					
					apuestaJugador = teclado.nextInt();
				}
				
				while(apuestaJugador > dineroJugador);
				
				//Generar aleatorioPc1 (dado 1)
				aleatorioPc1 = random.nextInt(6) +1;
				System.out.println("Dado 1 del Pc: " +aleatorioPc1);
				
				//Generar aleatorioPc2 (dado 2)
				aleatorioPc2 = random.nextInt(6) +1;
				System.out.println("Dado 2 del Pc: " +aleatorioPc2);
				
				//Sumar aleatorios de Pc (sumaDadosPc)
				sumaDadosPc = aleatorioPc1 + aleatorioPc2;
				System.out.println("Suma de los dados: " +sumaDadosPc);
				
				//Generar aleatorioJugador1 (dado 1)
				aleatorioJugador1 = random.nextInt(6) +1;
				System.out.println("Dado 1 del Jugador: " +aleatorioJugador1);
				
				//Generar aleatorioJugador2 (dado 2)
				aleatorioJugador2 = random.nextInt(6) +1;
				System.out.println("Dado 2 del Jugador: " +aleatorioJugador2);
				
				//Sumar aleatorios de Jugador (sumaDadosJugador)
				sumaDadosJugador = aleatorioJugador1 + aleatorioJugador2;
				System.out.println("Suma de los dados: " +sumaDadosJugador);
				
				//Resta opcionPc menos sumaDadosPc
				restaPc = opcionPc - sumaDadosPc;
				
				//Si restaPc < 0
				if(restaPc < 0){
					restaPc = restaPc * -1;
				}
				
				System.out.println("RestaPc: " +restaPc);
				
				//Resta opcionJugador menos sumaDadosJugador
				restaJugador = opcionJugador - sumaDadosJugador;
				
				//Si restaJugador < 0
				if(restaJugador < 0){
					restaJugador = restaJugador * -1;
				}
				
				System.out.println("RestaJugador: " +restaJugador);
				
				//Si restaPc y restaJugador es igual
				if(restaPc == restaJugador){
					System.out.print("Hay un empate, nadie gana ni pierde nada");
				}
				
				//Sino
				else{
					//Si restaPc > restaJugador (Gana Jugador)
					if(restaPc > restaJugador){
						System.out.println("GANA EL JUGADOR!");
						System.out.println(" ");
						
						//Al jugador se le suma la apuesta
						dineroJugador = dineroJugador + apuestaJugador;
						
						System.out.println("El Jugador gana " +apuestaJugador +" y se queda con un total de " +dineroJugador);
						System.out.println(" ");
						
						//Al Pc se le resta la apuesta
						dineroPc = dineroPc - apuestaPc;
						
						System.out.println("El Pc pierde " +apuestaPc +" y se queda con un total de " +dineroPc);
					}
					
					//Sino (Gana Pc)
					else{
						System.out.println("GANA EL PC!");
						System.out.println(" ");
						
						//Al jugador se le resta la apuesta
						dineroJugador = dineroJugador - apuestaJugador;
						
						System.out.println("El Jugador pierde " +apuestaJugador +" y se queda con un total de " +dineroJugador);
						System.out.println(" ");
						
						//Al Pc se le suma la apuesta
						dineroPc = dineroPc + apuestaPc;
						
						System.out.println("El Pc gana " +apuestaPc +" y se queda con un total de " +dineroPc);
					}	
				}
				
				//Preguntar, leer y validar para repetir
				do{
					System.out.print("Quiere seguir jugando? (s/n): ");
				
				respuesta = Character.toLowerCase(teclado.next().charAt(0));
				}
				
				while(respuesta !='s' && respuesta !='n');
			}

			//Mientras DineroJugador no sea 0 o DineroPc no sea 0 y se quiera jugar otra vez (repetir)
			while((dineroJugador != 0 || dineroPc != 0) && respuesta == 's');
		//Fin
		
	}
}

