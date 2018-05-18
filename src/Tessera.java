import java.io.Serializable;
import java.time.LocalDate;

/**
 * La classe Tessera rappresenta una tessera appartentente a una persona appertente al bocciofilo di Darfo Boario Terme.
 * La tessera richiede di inserire: un numero di matricola, nome della persona da tesserare, cognome della persona da tesserare, codice fiscale della persona da tesserare,
 * la data di nascita (anno, mese, giorno)) della persona da tesserare e infine specificare se la tessera è una nuova registrazione o una nuova tessera.
 * Infine ha un attributo di tipo statico che indica la quota annuale da pagare che è 50€ se è una nuova tessera o 30€ se è un rinnovo. 
 * @author Alessandro Gelmi
 *
 */
public class Tessera implements Serializable
{
	//Attributi
	private int matricola;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private LocalDate dataNascita;
	private String info;
	private static int quotaAnnuale=50;
		
	/**
	 * Costruttore tessere. Serve per instanziare un oggetto di tipo tessera con tutti i suoi parametri.
	 * @param matricola
	 * @param nome
	 * @param cognome
	 * @param codiceFiscale
	 * @param dataNascita
	 * @param info
	 */
	//costruttori
	public Tessera(int matricola, String nome, String cognome, String codiceFiscale, LocalDate dataNascita, String info)
	{
		setMatricola(matricola);
		setNome(nome);
		setCognome(cognome);
		setCodiceFiscale(codiceFiscale);
		setDataNascita(dataNascita);
		setInfo(info);
	}
	
	/**
	 * Costruttore di copia. Crea una copia di una tessera gia esistente
	 * @param tessera già esistente
	 */
	public Tessera(Tessera t)
	{
		
		setMatricola(t.getMatricola());
		setNome(t.getNome());
		setCognome(t.getCognome());
		setCodiceFiscale(t.getCodiceFiscale());
		setDataNascita(t.getDataNascita());
		setInfo(t.getInfo());
	}
	
	/**
	 * Costruttore vuoto. Crea una nuova tessera senza nessun valore inserito
	 */
	public Tessera()
	{
		
		setMatricola(0);
		setNome(" ");
		setCognome(" ");
		setCodiceFiscale(" ");
		setDataNascita(null);
		setInfo(" ");
	}
	
	//getter setter
	/**
	 * Metodo getter della classe Tessera che restituisce il numero di matricola di una tessera
	 * @return numero di matricola
	 */
	public int getMatricola() 
	{
		return matricola;
	}
	
	/**
	 * Metodo setter della classe Tessera che serve per impostare un nuovo valore di matricola. 
	 * @param numero di matricola da impostare
	 */
	public void setMatricola(int matricola) 
	{
		this.matricola = matricola;
		if(matricola<0)
		{
			System.out.println("Matricola non valida");
		}
		
	}

	/**
	 * Metodo getter della classe Tessera che restituisce il nome del proprietario della tessera
	 * @return nome proprietario
	 */
	public String getNome() 
	{
		return nome;
	}

	/**
	 *  Metodo setter della classe Tessera che serve per impostare un nuovo nome
	 * @param nome da impostare
	 */
	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	/**
	 * Metodo getter della classe Tessera che restituisce il cognome del proprietario della tessera
	 * @return cognome proprietario
	 */
	public String getCognome() 
	{
		return cognome;
	}

	/**
	 * Metodo setter della classe Tessera che serve per impostare un nuovo cognome
	 * @param cognome da impostare
	 */
	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}

	/**
	 * Metodo getter della classe Tessera che restituisce il codice fiscale del proprietario della tessera
	 * @return codice fiscale del proprietario
	 */
	public String getCodiceFiscale() 
	{
		return codiceFiscale;
	}

	/**
	 * Metodo setter della classe Tessera che serve per impostare un nuovo codice fiscale
	 * @param Codice fiscale da impostare
	 */
	public void setCodiceFiscale(String codiceFiscale) 
	{
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * Metodo getter della classe Tessera che restituisce la data di nascita (anno, mese, giorno) del proprietario della tessera
	 * @return Data di nascita del proprietario
	 */
	public LocalDate getDataNascita() 
	{
		return dataNascita;
	}

	/**
	 *  Metodo setter della classe Tessera che serve per impostare una nuova data di nascita
	 * @param Data di nascita da impostare
	 */
	public void setDataNascita(LocalDate dataNascita) 
	{
		this.dataNascita = dataNascita;
	}

	/**
	 * Metodo getter della classe Tessera che restituisce l'informazione inserita (nuovo/rinnovo)
	 * @return Informazione della tessera
	 */
	public String getInfo() 
	{
		return info;
	}
	
	/**
	 * Metodo setter della classe Tessera che serve per impostare una informazione nuova
	 * @param Informazione da impostare
	 */
	public void setInfo(String info) 
	{
		this.info = info;
	}
	
	/**
	 * Metodo statico getter della classe Tessera che restituisce il prezzo della quota annuale
	 * @return Quota annuale da pagare
	 */
	public static int getQuotaAnnuale() 
	{
		return quotaAnnuale;
	}
	
	/**
	 * Metodo statico setter della classe Tessera che serve per impostare un nuovo valore di quota annuale da oagare
	 * @param Nuova quota annuale da pagare
	 */
	public static void setQuotaAnnuale(int quota) 
	{
		Tessera.quotaAnnuale = quota;
	}
	
	/**
	 * Metodo toString della classe Tessera che restituisce una tessera come stringa da visualizzare
	 * return Tessera sotto forma di stringa
	 */
	public String toString()
	{
		return ("Matricola: "+getMatricola()+"; Nome: "+getNome()+"; Cognome: "+getCognome()+"; Data Nascita: "+getDataNascita()+
		"; Codice Fiscale: "+getCodiceFiscale()+"; Info: "+getInfo());
	}
		
}
