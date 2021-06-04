package com.ncr.asr.cencosud.chile.scango;

import java.util.ArrayList;

public class HiloConcurrente {

	public static void main(String args[]) {
		 Bolsa bolsa= new Bolsa();
		 HiloEnvio hilo= new HiloEnvio(bolsa);
		 hilo.start();
		 
		 for(int i=0;i<=10;i++) {
			 Producto p= new Producto();
			 try {
				 synchronized (bolsa) {
					 Thread.sleep(1000);
					 if (bolsa.estaLlena()) {
						 bolsa.notify();
					 }
				 }
			 } catch (InterruptedException e) {
				 e.printStackTrace();
			 }
			 
			 bolsa.addProducto(p);
			 System.out.println(bolsa.getSize());
		 }
	}
}

class Bolsa {
 
	private ArrayList<Producto> listaProductos = new ArrayList<Producto>();
	 
	public void addProducto(Producto producto) {
		if (!estaLlena())
			listaProductos.add(producto);
	}
	 
	public ArrayList<Producto> getListaProductos() {
		return listaProductos; 
	}
	 
	public int getSize() {
		return listaProductos.size();
	}

	public boolean estaLlena() {
		return listaProductos.size() >= 5;
	}
}

class Producto {
	 
	 private String nombre;
	 
	 public String getNombre() {
		 return nombre;
	 }
	 
	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	 }
}

class HiloEnvio extends Thread {
	 
	private Bolsa bolsa;
 
	public HiloEnvio(Bolsa bolsa) {
		 super();
		 this.bolsa = bolsa;
	}
 
	@Override
	public void run() {
	 
		if (bolsa.estaLlena() != true) {
		 
			try {
				synchronized (bolsa) {
					bolsa.wait();
				 }
				 
			} catch (InterruptedException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			}
			 
			System.out.println("Enviando la bolsa con "+ bolsa.getSize()+"elementos");
		}
	} 
}