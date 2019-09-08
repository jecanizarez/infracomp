package Caso1;

public class Cliente extends Thread {
	/**
	 * Indica el id del cliente
	 */
	private int id; 
	/**
	 * Buffer al cual va a enviar los mensajes
	 */
	private static Buffer buffer;
	/**
	 * Indica el numero de mensajes que va a enviar el cliente 
	 */
	private int numMensajes;
	
	public Cliente(int pId,Buffer pBuffer, int mensajes)
	{
		buffer = pBuffer; 
		numMensajes = mensajes; 
		id = pId; 
	}
	public Mensaje generarMensaje()
	{
		return null;
	}
	public void run()
	{
		for(int i = 0; i < numMensajes; i++)
		{
			Mensaje mensaje = generarMensaje();
			buffer.almacenarMensajeNoListo(mensaje);
			System.out.println("El cliente: "+id+ "Almaceno su mensaje");
			buffer.retirarMensajeListo(mensaje);
			System.out.println("El cliente: "+id+ "Retiro su mensaje");
		}
	}
	

}
