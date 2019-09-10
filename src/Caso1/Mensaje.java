package Caso1;

public class Mensaje {
	/**
	 * indica el mensaje 
	 */
	private int numero; 
	/**
	 * indica el id del mensaje
	 */
	private int id;
	/**
	 * Indica el cliente que creo el mensaje
	 */
	private Cliente cliente; 
	
	private boolean listo; 
	
	private Buffer buffer;

	/**
	 * Metodo constructor.
	 * @param numero
	 * @param id
	 */

	public Mensaje(int numero, int id, Cliente cliente, Buffer buffer)
	{
		this.numero = numero; 
		this.id = id;
		this.cliente = cliente; 
		listo = false; 
		this.buffer = buffer;
	}

	public int getNumero()
	{
		return numero;
	}
	public int getId()
	{
		return id;
	}
	public Cliente getCliente()
	{
		return cliente;
	}
	public void atenderMensaje()
	{
		this.numero = ++numero; 
	}
	public synchronized void dormirCliente()
	{
		try
		{
            wait();
		}
		catch(Exception e)
		{
			System.out.println("F");
			e.printStackTrace();
		}
	}
	public synchronized void despertarCliente()
	{
		notify();
	}
	public void almacenarEnBuffer()
	{
		buffer.almacenarMensaje(this);
	}
	public void notificarBuffer()
	{
		buffer.reducirClientes();
	}
	public void setListo()
	{
		listo = true;
	}
	public boolean getListo()
	{
		return listo;
	}


}
