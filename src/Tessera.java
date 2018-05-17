import java.io.Serializable;
import java.time.LocalDate;

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
	
	public Tessera(Tessera t)
	{
		
		setMatricola(t.getMatricola());
		setNome(t.getNome());
		setCognome(t.getCognome());
		setCodiceFiscale(t.getCodiceFiscale());
		setDataNascita(t.getDataNascita());
		setInfo(t.getInfo());
	}
	
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
	public int getMatricola() 
	{
		return matricola;
	}
	
	public void setMatricola(int matricola) 
	{
		this.matricola = matricola;
		if(matricola<0)
		{
			System.out.println("Matricola non valida");
		}
		
	}

	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getCognome() 
	{
		return cognome;
	}

	public void setCognome(String cognome) 
	{
		this.cognome = cognome;
	}

	public String getCodiceFiscale() 
	{
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) 
	{
		this.codiceFiscale = codiceFiscale;
	}

	public LocalDate getDataNascita() 
	{
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) 
	{
		this.dataNascita = dataNascita;
	}

	public String getInfo() 
	{
		return info;
	}

	public void setInfo(String info) 
	{
		this.info = info;
	}
	
	public static int getQuotaAnnuale() 
	{
		return quotaAnnuale;
	}
	
	public static void setQuotaAnnuale(int quota) 
	{
		Tessera.quotaAnnuale = quota;
	}
	
	public String toString()
	{
		return ("Matricola: "+getMatricola()+"; Nome: "+getNome()+"; Cognome: "+getCognome()+"; Data Nascita: "+getDataNascita()+
		"; Codice Fiscale: "+getCodiceFiscale()+"; Info: "+getInfo());
	}
		
}
