package Caso1;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.sql.rowset.spi.SyncResolver;

public class Buffer {
	/**
	 * Mensajes almacenados en el buffer
	 */
	private ArrayList<Mensaje> mensajes; 
	/**
	 * Tamaño del buffer
	 */
	private int tamaño; 
	/**
	 * Indica el numero de clientes
	 */
	private int clientes;

	Object obj;
	/**
	 * Metodo constructors
	 * @param tamaño
	 * @param clientes
	 */
	public Buffer(int tamaño, int pClientes)
	{
		this.tamaño = tamaño; 
		clientes = pClientes; 
		mensajes = new ArrayList<Mensaje>(); 
		obj = new Object();

	}
	public synchronized  void almacenarMensaje(Mensaje mensaje) 
	{
		while(mensajes.size()== tamaño)
		{
			try 
			{
				wait();
			} catch (Exception e) {
				System.out.println("F");
			}
		}
		System.out.println("Se añadio el mensaje del cliente " +mensaje.getId());
		mensajes.add(mensaje);
		System.out.println("El peso del buffer es " + mensajes.size());
	}

	public synchronized  Mensaje retirarMensaje()
	{
			notify();
			Mensaje retorno = mensajes.remove(0);
			System.out.println("El servidor retiro el mensaje " + retorno.getId());
			return retorno;
		
		
	}

	public synchronized void reducirClientes()
	{
		clientes--; 
		
	}
	public synchronized int mensajesRestantes()
	{
		return mensajes.size(); 
	}
	public int getClientes()
	{
		return clientes;
	}

}
