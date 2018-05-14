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
	
	public void inserisciInTesta(Tessera persona)
	{
		Nodo p=creaNodo(persona,head);
		head=p;
		elementi++;
	}
	
	public void inserisciInCoda(Tessera persona) throws TesseraException
	{
		if (elementi==0)
		{
			inserisciInTesta(persona);
			return;
		}
		
		Nodo pn=creaNodo(persona,null);
		Nodo p=getLinkPosizione(elementi);
		p.setLink(pn);
		elementi++;
	}

	public String visita(int posizione) throws TesseraException
	{
		if(elementi==0)
			throw new TesseraException("Lista vuota");
		if (posizione<=0 || posizione>elementi)
			throw new TesseraException("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return (p.getInfo().toString());
	}
	
	public Tessera getInfo(int posizione) throws TesseraException
	{
		if(elementi==0)
			throw new TesseraException("Lista vuota");
		if (posizione<=0 || posizione>elementi)
			throw new TesseraException("Posizione non valida");
		
		
		Nodo p=getLinkPosizione(posizione);
		Tessera i=new Tessera(p.getInfo());
		return i;
	}
	
	public void salvaTessera(String nomeFile) throws IOException
	{
		FileOutputStream file=new FileOutputStream(nomeFile);
		ObjectOutputStream writer=new ObjectOutputStream(file);
		
		writer.writeObject(this);
		writer.flush();
		file.close();
	}
	
	public Tessera caricaTessere(String nomeFile) throws IOException, ClassNotFoundException
	{
		FileInputStream file=new FileInputStream(nomeFile);
		ObjectInputStream reader= new ObjectInputStream(file);
		
		Tessera f;
		f=(Tessera)reader.readObject();
		
		file.close();
		return f;
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
	
	public void inserisciTessera(Tessera tessera)
	{
		Nodo p=creaNodo(tessera, head);
		head=p;
		elementi++;
		
		try 
		{
			esportaCSV("tessere.txt");
		} 
		catch (IOException e) 
		{
			System.out.println("File non trovato");
		} 
		catch (TesseraException e) 
		{
			e.toString();
		}
		catch(FileException e)
		{
			e.toString();
		}
		
	}
	
	public void salvaLista(String nomeFile) throws IOException
	{
		FileOutputStream file =new FileOutputStream(nomeFile);
		ObjectOutputStream writer=new ObjectOutputStream(file);
		writer.writeObject(this);
		writer.flush();
		file.close();
	}
	
	
	public void esportaCSV (String nomeFile) throws IOException, TesseraException, FileException
	{
		TextFile file= new TextFile (nomeFile,'W');
		String personaCSV;
		Tessera persona;
		
		for (int i = 1; i <= getElementi(); i++) 
		{
			persona=getTessera(i);
			personaCSV=persona.getMatricola()+";"+persona.getNome()+";"+persona.getCognome()+";"
						+persona.getCodiceFiscale()+";"+persona.getDataNascita()+";"+persona.getInfo();
			file.toFile(personaCSV);
		}
		file.closeFile();
	}
	
	public void esportaCSVeliminati (String nomeFile, int posizione) throws IOException, TesseraException, FileException
	{
		TextFile file= new TextFile (nomeFile,'W');
		String personaCSV;
		Tessera persona;
		
			persona=getTessera(posizione);
			personaCSV=persona.getMatricola()+";"+persona.getNome()+";"+persona.getCognome()+";"
						+persona.getCodiceFiscale()+";"+persona.getDataNascita()+";"+persona.getInfo();
			file.toFile(personaCSV);
		
		file.closeFile();
	}
}
