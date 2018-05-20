import java.io.Serializable;

public class Nodo implements Serializable
{
	private Tessera info;
	private Nodo link;
	
	/**
	 * Costruttore della classe nodo, serve per creare un nuovo nodo inserendo nella parte informativa una tessera e la parte link che ounta al prossimo nodo sarà impostata a 
	 * null
	 * @param Tessera da inserire nella parte informativa
	 */
	public Nodo(Tessera tessera)
	{
		setInfo(tessera);
		link=null;
	}
	
	/**
	 * Meteo getter della classe Nodo che restituisce la parte informativa del nodo
	 * @return Parte informativa
	 */
	public Tessera getInfo() 
	{
		return info;
	}

	/**
	 * Metodo setter della classe Nodo che consente di impostare la parte informativa con una tessera 
	 * @param Tessera da inserire nella parte informativa
	 */
	public void setInfo(Tessera info) 
	{
		this.info = new Tessera(info);
	}

	/**
	 * Metodo getter della classe Nodo che consente di ritornare la parte di Nodo contenente il link 
	 * @return Link di un nodo
	 */
	public Nodo getLink()
	{
		return link;
	}

	/**
	 * Metodo setter della classe Nodo che consente di impostare il link di un nodo
	 * @param Link da impostare
	 */
	public void setLink(Nodo link) 
	{
		this.link = link;
	}

}
