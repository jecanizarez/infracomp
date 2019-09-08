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
	
	/**
	 * Metodo constructor.
	 * @param numero
	 * @param id
	 */
	
	public Mensaje(int numero, int id, Cliente cliente)
	{
		this.numero = numero; 
		this.id = id;
		this.cliente = cliente; 
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
	public void dormirCliente()
	{
		try
		{
		cliente.wait();
		}
		catch(Exception e)
		{
			System.out.println("F");
		}
	}
	public void despertarClientes()
	{
		cliente.notifyAll();
	}


}
