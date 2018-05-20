import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * La classe ListaTessere consente di instanziare una lista che conterrà tessere. Gli attributi sono head e elementi. Head punta sempre al primo elemento della lista
 * o nel caso fosse vuota punta a null, mentre elementi indica quante tessere sono presenti all'interno della lista.
 * @author Alessandro Gelmi
 *
 */
public class ListaTessere implements Serializable
{
	private Nodo head;
	private int elementi;
	
	/**
	 * Costruttore lista tessere. Serve per creare una nuova lista vuota, ovvero dove sono presenti 0 elementi
	 */
	public ListaTessere()
	{
		head=null;
		elementi=0;
	}
	/**
	 * Metodo getter della classe ListaTessere che restituisce il numero di elementi presenti nella lista
	 * @return Numero di elementi presenti nella lista
	 */
	public int getElementi()
	{
		return elementi;
	}
	
	/**
	 * Metodo privato della classe ListaTessere che consente di creare un nodo, formato da una parte informativa che conterrà una tessera e una parte link che conterrà l'indirizzo
	 * al prossimo nodo della lista
	 * @param Persona da inserire nella lista
	 * @param Link del prossimo nodo
	 * @return
	 */
	private Nodo creaNodo(Tessera persona, Nodo link)
	{
		Nodo nodo=new Nodo(persona);
		nodo.setLink(link);
		return nodo;
	}
	
	/**
	 * Metodo della classe ListaTessere che restituisce il link a cui punta un nodo in una determinata posizione
	 * @param Posizione del nodo
	 * @return Nodo contenente il link del nodo successivo
	 * @throws TesseraException
	 */
	private Nodo getLinkPosizione(int posizione) throws TesseraException
	{
		if (elementi==0)
			throw new TesseraException("Lista vuota");
		if (posizione<=0 || posizione>elementi)
			throw new TesseraException("Posizione non valida");
		
		Nodo p=head;
		int n=1;
		while(p.getLink()!=null && n<posizione)
		{
			p=p.getLink();
			n++;
		}
		return p;
	}
	
	/**
	 * Metodo della classe ListaTessere che restituisce una stringa contenente nome e cognome di tutte le tessere presenti nella lista
	 * @return Stringa di tutte le tessere nella lista
	 */
	public String toString()
	{
		String risultato="Head";
		if(elementi==0)
			return risultato;
		Nodo p=head;
		while(p!=null)
		{
			risultato+="-->"+p.getInfo().getNome()+" "+p.getInfo().getCognome();
			p=p.getLink();
		}
		return risultato;
	}
	
	/**
	 * Metodo getter della classe ListaTessere che restituisce una tessera appartentente alla lista
	 * @param Posizione della tessera all'interno della lista
	 * @return Le informazioni della tessera cercata
	 * @throws TesseraException
	 */
	public Tessera getTessera (int posizione) throws TesseraException
	{
		if (elementi==0)
			throw new TesseraException("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new TesseraException("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo();		
	}
	
	/**
	 * Metodo della classe ListaTessera che consente di inserire una nuova tessera all'interno di una lista e di esportarla come file di testo CSV.
	 * @param Tessera da inserire nella lista
	 * @throws IOException
	 * @throws TesseraException
	 * @throws FileException
	 * @throws ClassNotFoundException
	 */
	public void inserisciTessera(Tessera tessera) throws IOException, TesseraException, FileException, ClassNotFoundException
	{
		Nodo p=creaNodo(tessera, head);
		head=p;
		elementi++;
		
		esportaTessereCSV("tessere.txt");
		
	}
	
	/**
	 * Metodo della classe ListaTessere che consente di esportare le tessere su un file di testo CSV.
	 * @param Il nome del file sul qule si vogliono esportare le tessere
	 * @throws IOException
	 * @throws TesseraException
	 * @throws FileException
	 */
	public void esportaTessereCSV (String nomeFile) throws IOException, TesseraException, FileException		//IMPOSSIBILE ELIMINARE IN CODA PERCHE' PUNTA NULL
	{
		Tessera tessera;
		String personaCSV;
		
		TextFile file= new TextFile (nomeFile,'W');
		
		
		for (int i = 1; i <= getElementi(); i++) 
		{
			tessera=getLinkPosizione(i).getInfo();
			personaCSV=tessera.getMatricola()+";"+tessera.getNome()+";"+tessera.getCognome()+";"+tessera.getCodiceFiscale()+";"+tessera.getDataNascita()+";"+tessera.getInfo();
			file.toFile(personaCSV);
		}					
		file.closeFile();
	}
	
	/**
	 * Metodo della classe ListaTessere che consente di eliminare ,tramite il numero di matricola, una tessera che si trova in testa, ovvero in prima posizione
	 * @param Matricola della tessera da eliminare 
	 * @throws TesseraException
	 * @throws IOException
	 * @throws FileException
	 */
	public void eliminaInTesta(int posizione) throws TesseraException, IOException, FileException
	{
		if (elementi==0)
			throw new TesseraException("Lista vuota");
		head=head.getLink();
		elementi--;	
	}
	
	/**
	 *  Metodo della classe ListaTessere che consente di eliminare ,tramite il numero di matricola, una tessera che si trova in coda, ovvero in ultima posizione
	 * @param Matricola della tessera da eliminare
	 * @throws TesseraException
	 * @throws FileException
	 * @throws IOException
	 */
	public void eliminaInCoda(int posizione) throws TesseraException, FileException, IOException
	{
		if (elementi==0)
			throw new TesseraException("Lista vuota");
		if (elementi==1)
		{
			eliminaInTesta(posizione);
			return;
		}
		
		Nodo p=getLinkPosizione(elementi-1);
		p.setLink(null);
		elementi--;
	}
	
	/**
	 * Metodo della classe ListaTessere che consente di eliminare una tessera tramite il suo numero di matricola. Se è presente un solo emento si eliminerà in testa, se sono 
	 * presenti tante tessere quanti sono gli elementi della lista si eliminerà in coda. Completata l'eliminazione si andrà a salvare la tessera eliminata su un file di testo CSV.
	 * @param Matricola della tessera da eliminare
	 * @throws TesseraException
	 * @throws IOException
	 * @throws FileException
	 * @throws ClassNotFoundException
	 */
	public void eliminaInPosizione(int matricola) throws TesseraException, IOException, FileException, ClassNotFoundException
	{
		boolean isElim=false;
		if (elementi==0)
			throw new TesseraException("Lista vuota");
		
		
		for (int i = 1; i <= elementi; i++)
		{
			if((i==1) && (getLinkPosizione(i).getInfo().getMatricola()==matricola))
			{
				esportaEliminatiCSV("eliminati.txt",matricola);
				eliminaInTesta(matricola);
				isElim=true;
				return;
			}
			
			if (i==getElementi() && getLinkPosizione(i).getInfo().getMatricola()==matricola) 
			{
				esportaEliminatiCSV("eliminati.txt",matricola);
				eliminaInTesta(matricola);
				isElim=true;
				return;
			}
			
			if(isElim=false)
			{
				if(getLinkPosizione(i).getInfo().getMatricola()==matricola)
				{
					Nodo p;
					p=getLinkPosizione(matricola);
					Nodo precedente=getLinkPosizione(matricola-1);
					precedente.setLink(p.getLink());
					elementi--;
					esportaEliminatiCSV("eliminati.txt",matricola);
				}
			}
			
			if(elementi>0 && getLinkPosizione(i).getInfo().getMatricola()==matricola)
				i=0;
		}
	}
	
	/**
	 * Metodo della classe ListaTessere che consente di esportare le tessere eliminate su un file di testo CSV. Sul file verrà memorizzata l'intera tessera.
	 * @param Nome del file su cui esportare le tessere
	 * @param Matricola della tessera da eliminare
	 * @throws IOException
	 * @throws TesseraException
	 * @throws FileException
	 */
	public void esportaEliminatiCSV (String nomeFile, int posizione) throws IOException, TesseraException, FileException
	{
		Tessera tessera;
		String eliminatoCSV;
		
		TextFile file= new TextFile (nomeFile,'W');
		
		tessera=getTessera(posizione);
		eliminatoCSV=tessera.getMatricola()+";"+tessera.getNome()+";"+tessera.getCognome()+";"+tessera.getCodiceFiscale()+";"+tessera.getDataNascita()+";"+tessera.getInfo();
		file.toFile(eliminatoCSV);
		
		file.closeFile();
	}
	
	/**
	 * Metodo della classe ListaTessere che consente di trasformare una lista contente tessere in un array contenente le stesse tessere della lista
	 * @return Array di tessere
	 * @throws TesseraException
	 */
	public Tessera[] toArray() throws TesseraException
	{
		Tessera[] arrayTessere=new Tessera[elementi];
		Nodo n;
		
		for (int i = 0; i < elementi; i++) 
		{
			n=getLinkPosizione(i+1);
			arrayTessere[i]=n.getInfo();
		}
		return arrayTessere;
	}
	
	/**
	 * Inserisce le tessere di un array in una lista.
	 * @param Array di tessere
	 * @throws TesseraException
	 * @throws IOException
	 * @throws FileException
	 * @throws ClassNotFoundException
	 */
	public void convertiTessera(Tessera[] tessera) throws TesseraException, IOException, FileException, ClassNotFoundException
	{
		for (int i = tessera.length; i > 0; i--) 
			inserisciTessera(tessera[i-1]);
	}
	
	/**
	 * Metodo dell classe ListaTessere che consente di scambaire le posizioni di due tessere all'interno di un array.
	 * @param Array di tessere
	 * @param Posizione 1 da scambiare
	 * @param Posizione 2 da scambiare
	 * @return 0
	 */
	public static int scambia(Tessera[] array, int p1, int p2)
	{
		Tessera c;
		
		if(p1<0 || p2<0 || p1>=array.length || p2>=array.length)
			return -1;
		else
		{
			c=array[p1];
			array[p1]=array[p2];
			array[p2]=c;
			return 0;
		}
	}
	
	/**
	 * Metodo della classe ListaTessere che consente di creare una copia di un altro array
	 * @param Array di tessere da copiare
	 * @return Array di tessere copiato
	 */
	private static Tessera[] arrayCopia(Tessera[] array)
	{
		Tessera[] copia=new Tessera[array.length];
		
		for (int i = 0; i < copia.length; i++)
		{
			copia[i]=array[i];
		}
		return copia;
	}
	
	/**
	 * Metodo della classe ListaTessere che consente di ordinare gli utenti delle tessere, presenti in un array, in ordine di anzianità ovvero dal più vecchio al più giovane 
	 * @param Array di tessere da ordinare
	 * @return Array di copia con al proprio interno le tessere ordinate
	 */
	public Tessera[] ordinaAnzianita(Tessera[] array)
	{
		Tessera[] copia=arrayCopia(array);
		
		for (int i = 0; i < copia.length-1; i++) 
		{
			for (int j = i+1; j < copia.length; j++) 
			{
				 if(copia[i].getDataNascita().isAfter(copia[j].getDataNascita()))
					scambia(copia,i,j);
			}
		}
		return copia;
	}
	
	/**
	 * Metodo della classe ListaTessere che consente di ordinare gli utenti delle tessere, presenti in un array, in ordine alfabetico dalla A alla Z
	 * @param Array di tessere da ordinare
	 * @return Array di copia con al proprio interno le tessere ordinate
	 */
	public Tessera[] ordinaAlfabetico(Tessera[] array)
	{
		Tessera[] copia=arrayCopia(array);
		
		for (int i = 0; i < copia.length-1; i++) 
		{
			
			for (int j = i+1; j < copia.length; j++) 
			{
				if (copia[i].getNome().compareToIgnoreCase(copia[j].getNome())>0)
					scambia(copia,i,j);
			}
		}
		return copia;
	}

	/**
	 * Metodo della classe ListaTessere che consente di visualizzare le informazioni di una tessera inserendo nome e cognome.
	 * @param Nome del tesserato
	 * @param Cognome del tesserato
	 */
	public void visualizzaDatiTesserato(String nome, String cognome)
	{
		Nodo p=head;
		int x=0;
		
		while(p!=null)
		{
			if(p.getInfo().getNome().compareTo(nome)==0)
			{
				if(p.getInfo().getCognome().compareTo(cognome)==0)
				{
					System.out.println(p.getInfo().toString());
					x++;
				}
			}
			p=p.getLink();
		}
		
	}
	
	/**
	 * Meotodo della classe ListaTessere che consente di caricare un file di testo CSV e creare un array contenente tutti i codici fiscali delle tessere gia presenti nel file su cui
	 * sono memorizzate le tessere
	 * @param Nome del file da cui caricare i codici fiscali
	 * @return Array contenente i codici fiscali
	 * @throws IOException
	 * @throws FileException
	 * @throws EccezioneTextFileEOF
	 */
	public String[] caricaCodiciFiscali(String nomeFile) throws IOException, FileException, EccezioneTextFileEOF
	{
		String tesseraCSV;
		String[] elementiTessera;
		String[] codiciFiscali = new String[50];
		int i=0;
		
		TextFile file=new TextFile(nomeFile,'R');
		
		try
		{
			while(true)
			{
				tesseraCSV=file.fromFile();
				elementiTessera=tesseraCSV.split(";");
				codiciFiscali[i]=elementiTessera[3];
				i++;
			}
		}
		catch (EccezioneTextFileEOF e) 
		{
			
		}
		return codiciFiscali;
	}
	
	
	//Serializzazione e deserializzazione
	/**
	 * Metodo della classe Lista Tessere che consente di serializzare ovvero di salvare una lista su un file binario.
	 * @param Nome del file binario su cui memorizzare la lista
	 * @throws IOException
	 */
	public void salvaLista(String nomeFile) throws IOException
	{
		FileOutputStream file =new FileOutputStream(nomeFile);
		ObjectOutputStream writer=new ObjectOutputStream(file);
		writer.writeObject(this);
		writer.flush();
		file.close();
	}
	
	/**
	 * Metodo della classe Lista Tessere che consente di deserializzare ovvero di caricare da un file binario una lista di tessere.
	 * @param Nome del file dal quale caricare le tessere
	 * @return Lista contente le tessere caricate
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ListaTessere caricaLista(String nomeFile) throws IOException, ClassNotFoundException
	{
		FileInputStream file=new FileInputStream(nomeFile);
		ObjectInputStream reader= new ObjectInputStream(file);
		
		ListaTessere lista;
		
		lista=(ListaTessere)(reader.readObject());
		file.close();
		return lista;
	}
}
