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
	 * Tama�o del buffer
	 */
	private int tama�o; 
	/**
	 * Indica el numero de clientes
	 */
	private int clientes;

	Object obj;
	/**
	 * Metodo constructors
	 * @param tama�o
	 * @param clientes
	 */
	public Buffer(int tama�o, int pClientes)
	{
		this.tama�o = tama�o; 
		clientes = pClientes; 
		mensajes = new ArrayList<Mensaje>(); 
		obj = new Object();

	}
	public synchronized  void almacenarMensaje(Mensaje mensaje) 
	{
		while(mensajes.size()== tama�o)
		{
			try 
			{
				wait();
			} catch (Exception e) {
				System.out.println("F");
			}
		}
		System.out.println("Se a�adio el mensaje del cliente " +mensaje.getId());
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
