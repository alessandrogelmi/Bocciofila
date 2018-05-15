import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ListaTessere implements Serializable
{
	private Nodo head;
	private int elementi;
	
	public ListaTessere()
	{
		head=null;
		elementi=0;
	}
	
	public int getElementi()
	{
		return elementi;
	}
	
	private Nodo creaNodo(Tessera persona, Nodo link)
	{
		Nodo nodo=new Nodo(persona);
		nodo.setLink(link);
		return nodo;
	}
	
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
		
	public Tessera getTessera (int posizione) throws TesseraException
	{
		if (elementi==0)
			throw new TesseraException("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new TesseraException("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo();		
	}
	
	public void inserisciTessera(Tessera tessera) throws IOException, TesseraException, FileException
	{
		Nodo p=creaNodo(tessera, head);
		head=p;
		elementi++;
		System.out.println("La quota annule da pagare è di "+this.getTessera(1).getQuotaAnnuale()+" €");
		
		esportaTessereCSV("tessere.txt");
		
	}
	
	public void esportaTessereCSV (String nomeFile) throws IOException, TesseraException, FileException
	{
		TextFile file= new TextFile (nomeFile,'W');
		String personaCSV;
		Tessera tessera;
		
		for (int i = 1; i <= getElementi(); i++) 
		{
			tessera=getTessera(i);
			personaCSV=tessera.getMatricola()+";"+tessera.getNome()+";"+tessera.getCognome()+";"
						+tessera.getCodiceFiscale()+";"+tessera.getDataNascita()+";"+tessera.getInfo();
			file.toFile(personaCSV);
		}
		file.closeFile();
	}
	
	public void eliminaInTesta(int posizione) throws TesseraException, IOException, FileException
	{
		if (elementi==0)
			throw new TesseraException("Lista vuota");
		head=head.getLink();
		elementi--;
		esportaEliminatiCSV("eliminati.txt",posizione);
		
	}
	
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
		esportaEliminatiCSV("eliminati.txt",posizione);

	}
	
	public void eliminaInPosizione(int posizione) throws TesseraException, IOException, FileException
	{
		if (posizione<=0 || posizione>elementi)
			throw new TesseraException("Posizione non valida");
		
		if (elementi==0)
			throw new TesseraException("Lista vuota");
	
		if (posizione==1)
		{
			eliminaInTesta(posizione);
			return;
		}
		
		if (posizione==elementi)
		{
			eliminaInCoda(posizione);
			return;
		}
		
		Nodo p;
		p=getLinkPosizione(posizione);
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(p.getLink());
		elementi--;
		esportaEliminatiCSV("eliminati.txt",posizione);
	}
	
	public void esportaEliminatiCSV (String nomeFile, int posizione) throws IOException, TesseraException, FileException
	{
		TextFile file= new TextFile (nomeFile,'W');
		String tesseratoCSV;
		Tessera tessera;
		
			tessera=getTessera(posizione);
			tesseratoCSV=tessera.getMatricola()+";"+tessera.getNome()+";"+tessera.getCognome()+";"+tessera.getCodiceFiscale()+";"+tessera.getDataNascita()+";"
			+tessera.getInfo();
			file.toFile(tesseratoCSV);
		
		file.closeFile();
	}
	
	//Serializzazione e deserializzazione
	public void salvaLista(String nomeFile) throws IOException
	{
		FileOutputStream file =new FileOutputStream(nomeFile);
		ObjectOutputStream writer=new ObjectOutputStream(file);
		writer.writeObject(this);
		writer.flush();
		file.close();
	}
	
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
