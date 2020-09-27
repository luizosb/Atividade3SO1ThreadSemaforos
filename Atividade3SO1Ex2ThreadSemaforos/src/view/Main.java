package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPratos;

public class Main {

	public static void main(String[] args) {
		int qtdPratos = 5;
		int permissao = 1;
		double maxSegundos = 0;
		double minSegundos = 0;
		int ind;
		String id = "";
		
		Semaphore semaforo = new Semaphore(permissao);
		
		for(ind = 0; ind < qtdPratos; ind++) {
			if(ind % 2 == 0) {
				maxSegundos = 1200;
				minSegundos = 600;
				id = "Lasanha a Bolonhesa";
			} else if (ind % 2 == 1) {
				maxSegundos = 800;
				minSegundos = 500;
				id = "Sopa de Cebola";
			}
			Thread tCalculos = new ThreadPratos(id + " " + (ind+1), minSegundos, maxSegundos, semaforo);
			tCalculos.start();
		}
			

	}
}


