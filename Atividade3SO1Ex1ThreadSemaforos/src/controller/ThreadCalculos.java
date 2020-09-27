package controller;

import java.util.concurrent.Semaphore;

public class ThreadCalculos extends Thread{

	private double maxSegundos;
	private double minSegundos;
	private long tempoBD;
	private String id;
	private int repeticoes;
	private Semaphore semaforo;
	
	public ThreadCalculos(double maxSegundos, double minSegundos,long tempoBD,String id, int repeticoes, Semaphore semaforo) {
		super(id);
		this.id = id;
		this.maxSegundos = maxSegundos;
		this.minSegundos = minSegundos;
		this.tempoBD = tempoBD;
		this.repeticoes = repeticoes;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		System.out.println("\nInício das transações na Thread " + id);
		while(repeticoes !=0) {
			calculosTransacoes();
			try {
				semaforo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				transacaoNoBD();
				semaforo.release();
				repeticoes--;
			}
		}
		avisarFinalizacaoThread();
	}

	private void calculosTransacoes() {
		System.out.println(id + "|Inicio dos calculos...|");
		long tempo = (long) (Math.random()*(maxSegundos - minSegundos) + minSegundos);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(id + "|Fim dos calculos...|");
	}

	private void transacaoNoBD() {
		System.out.println("\n"+id + "|Inicio das transações em Banco de Dados...|");
		try {
			sleep(tempoBD);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n" + id + "|Fim das transações em Banco de Dados...|");
	}
	
	private void avisarFinalizacaoThread() {	
		System.out.println("\nAcabou a Thread " + id);
	}
	
}
