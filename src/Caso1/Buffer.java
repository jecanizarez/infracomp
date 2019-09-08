package Caso1;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.sql.rowset.spi.SyncResolver;

public class Buffer {
	private ArrayList<Mensaje> mensajesNoListo; 
	private Hashtable<Integer, Mensaje> mensajesListo; 


	private int tamaño; 
	Object lleno; 
	Object vacio; 	
	public Buffer(int tamaño)
	{
		this.tamaño = tamaño; 
		mensajesListo = new Hashtable<Integer, Mensaje>();
		mensajesNoListo = new ArrayList<Mensaje>(); 
		lleno = new Object(); 
		vacio = new Object();
	}
	public  void almacenarMensajeNoListo(Mensaje mensaje) 
	{
		while(mensajesListo.size() + mensajesNoListo.size()== tamaño)
		{
			synchronized (lleno) 
			{
				try 
				{
					lleno.wait();
				} catch (Exception e) {
					System.out.println("F");
				}
			}
		}
		synchronized (this) 
		{
			mensajesNoListo.add(mensaje);
		}
		synchronized (vacio) 
		{
			vacio.notify();
		}
	}
	public  void almacenarMensajeListo(Mensaje mensaje) 
	{
		while(mensajesListo.size() + mensajesNoListo.size()== tamaño)
		{
			synchronized (lleno) 
			{
				try 
				{
					lleno.wait();
				} catch (Exception e) {
					System.out.println("F");
				}
			}
		}
		synchronized (this) 
		{
			mensajesListo.put(mensaje.getId(), mensaje);
		}
		synchronized (vacio) 
		{
			vacio.notify();
		}
	}

	public synchronized Mensaje retirarMensajeListo(Mensaje mensaje)
	{
		while(mensajesListo.size() + mensajesNoListo.size()== 0)
		{
			synchronized (vacio) 
			{
				try
				{
					vacio.wait();
				}
				catch(Exception e)
				{
					System.out.println("F");
				}
			}

		}
		Mensaje retorno;
		synchronized (this) 
		{
			while(!mensajesListo.contains(mensaje.getId()))
			{
				mensaje.dormirCliente();
			}
			retorno = mensajesListo.remove(mensaje.getId());

		}
		synchronized (lleno) 
		{
			lleno.notify();	
		}
		return retorno;

	}
	public synchronized Mensaje retirarMensajeNoListo()
	{
		while(mensajesListo.size() + mensajesNoListo.size()== 0)
		{
			synchronized (vacio) 
			{
				try
				{
					vacio.wait();
				}
				catch(Exception e)
				{
					System.out.println("F");
				}
			}

		}
		Mensaje retorno;
		synchronized (this) 
		{
			int numero = (int)Math.random()*tamaño;
			retorno = mensajesNoListo.remove(numero);
		}
		synchronized (lleno) 
		{
			lleno.notify();	
		}
		return retorno;

	}
}
