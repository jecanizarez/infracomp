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
	
	private Mensaje[] mensajes; 
	
	/**
	 * Metodo constructor
	 * @param pId
	 * @param pBuffer
	 * @param mensajes
	 */
	
	public Cliente(int pId,Buffer pBuffer, int mensajes)
	{
		buffer = pBuffer; 
		numMensajes = mensajes; 
		id = pId; 
		this.mensajes = new Mensaje[mensajes];
	}
	public Mensaje generarMensaje()
	{
		return new Mensaje(id, id, this, buffer);
	}
	public void run()
	{
		for(int i = 0; i < numMensajes; i++)
		{
			mensajes[i] = generarMensaje(); 
		}
		for(int i = 0; i < numMensajes; i++)
		{
			System.out.println("El cliente "+ id + "va a almacenar el mensaje");
			mensajes[i].almacenarEnBuffer();
			mensajes[i].dormirCliente();
			if(mensajes[i].getListo() && mensajes[i].getNumero() == id+1)
			{
				System.out.println("Mi mensaje es correcto");
			}
			else
			{
				System.out.println("Mi mensaje no es correcto");
			}
			if(i == --numMensajes)
			{
				mensajes[i].notificarBuffer();
				System.out.println("El cliente"+ id+ " se va a retirar");
			}
		}
	}
	public Buffer getBuffer()
	{
		return buffer;
	}
	

}
