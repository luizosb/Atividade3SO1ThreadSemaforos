package controller;

import java.util.concurrent.Semaphore;

public class ThreadPratos extends Thread{
	
	private double maxSegundos;
	private double minSegundos;
	private String id;
	private Semaphore semaforo;

	public ThreadPratos(String id, double maxSegundos, double minSegundos, Semaphore semaforo) {
		super(id);
		this.id = id;
		this.maxSegundos = maxSegundos;
		this.minSegundos = minSegundos;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		comecoPrato();
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			entregaPrato();
			semaforo.release();
		}
		avisoDePratoEntregue();	
	}

	private void comecoPrato() {
		long tempo = (long) (Math.random() * (maxSegundos - minSegundos) + minSegundos);
		int porcentagem = 0;
		System.out.println("Começou o prato: " + id);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(porcentagem < 100) {
			System.out.println("Porcentagem do prato " + id +": " + porcentagem + "% concluído...");
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			porcentagem += (tempo/100);
			if(porcentagem > 100) {
				porcentagem = 100;
				System.out.println("Porcentagem do prato " + id +": " + porcentagem + "% concluído...");
			}
		}
	}

	private void entregaPrato() {
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void avisoDePratoEntregue() {
		System.out.println("\nPrato " + id + " entregue!");
	}
}
