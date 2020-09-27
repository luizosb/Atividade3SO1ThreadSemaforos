package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCalculos;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int qtdThread = 21;
		int permissao = 1;
		double maxSegundos = 0;
		double minSegundos = 0;
		long tempoBD = 0;
		int repeticoes = 0;
		int ind;
		String id;
		
		Semaphore semaforo = new Semaphore(permissao);
		
		for(ind = 0; ind < qtdThread; ind++) {
			if(ind % 3 == 1) {
				maxSegundos = 1000;
				minSegundos = 200;
				tempoBD = 1000;
				repeticoes = 2;
			} else if (ind % 3 == 2) {
				maxSegundos = 1500;
				minSegundos = 500;
				tempoBD = 1500;
				repeticoes = 3;
			} else if (ind % 3 == 0 ) {
				maxSegundos = 2000;
				minSegundos = 1000;
				tempoBD = 1500;
				repeticoes = 3;
			}
			Thread tCalculos = new ThreadCalculos(maxSegundos, minSegundos, tempoBD,"ID: " + (ind+1), repeticoes, semaforo);
			tCalculos.start();
		}
			

	}

}
