package Caso1;

public class Servidor extends Thread 
{
	private static Buffer buffer; 
	/**
	 * Constructor
	 */
	public Servidor( Buffer buffer)
	{
		this.buffer = buffer; 
	}
	public void run()
	{
		while(true)
		{
			while(buffer.mensajesRestantes() == 0)
			{	
				yield();
			}
			System.out.println("El servidor  va retirar un mensaje");
			Mensaje mensaje = buffer.retirarMensaje(); 
			System.out.println("El servidor  va a atender el mensaje");
			mensaje.atenderMensaje();
			mensaje.setListo();
			mensaje.despertarCliente(); 
			
		}
	}



}
