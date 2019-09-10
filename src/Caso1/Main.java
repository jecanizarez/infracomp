package Caso1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

public class Main {
	/**
	 * Indica el numero de clientes
	 */
	private static int numClientes; 
	/**
	 * indica el numero de servidores
	 */
	private static int numServidores; 
	/**
	 * indica el tamaño del buffer
	 */
	private static int tamBuffer; 
	
	private static Cliente[] clientes; 
	
	private static Servidor[] servidores;

	public static void main(String[] args) throws IOException 
	{
		 InputStream input = new FileInputStream("./data/data.properties");
		 Properties prop = new Properties();
		 prop.load(input);
		 
		 numClientes = Integer.parseInt(prop.getProperty("clientes"));
		 tamBuffer = Integer.parseInt(prop.getProperty("tamano"));
		 numServidores = Integer.parseInt(prop.getProperty("servidores"));
	
	     Buffer buffer = new Buffer(tamBuffer, numClientes);
		 clientes = new Cliente[numClientes]; 
		 servidores = new Servidor[numServidores]; 
		 for(int i = 0; i < numClientes; i++)
		 {
			 int mensajes = Integer.parseInt(prop.getProperty("cliente"+(i+1)));
			 clientes[i] = new Cliente(i,buffer, mensajes);
			 clientes[i].setName("Cliente" + i);
		 }
		 for(int j = 0; j < numServidores; j++)
		 {
			 servidores[j] = new Servidor(buffer);
			 servidores[j].setName("Servidor" +j);
		 }
		 for(int i= 0; i < numServidores; i++)
		 {
			 servidores[i].start();
		 }
		 for(int i = 0; i < numClientes; i++)
		 {
			 clientes[i].start();
		 }
		
		

       
		
		
		
	}

}
