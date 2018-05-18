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
	
	public void inserisciTessera(Tessera tessera) throws IOException, TesseraException, FileException, ClassNotFoundException
	{
		Nodo p=creaNodo(tessera, head);
		head=p;
		elementi++;
		
		esportaTessereCSV("tessere.txt");
		
	}
	
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
	
	public void eliminaInTesta(int posizione) throws TesseraException, IOException, FileException
	{
		if (elementi==0)
			throw new TesseraException("Lista vuota");
		head=head.getLink();
		esportaEliminatiCSV("eliminati.txt",posizione);
		elementi--;
		
		
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
		esportaEliminatiCSV("eliminati.txt",posizione);
		elementi--;
		
	}
	
	public void eliminaInPosizione(int matricola) throws TesseraException, IOException, FileException, ClassNotFoundException
	{
		boolean isElim=false;
		if (elementi==0)
			throw new TesseraException("Lista vuota");
		
		
		for (int i = 1; i <= elementi; i++)
		{
			if((i==1) && (getLinkPosizione(i).getInfo().getMatricola()==matricola))
			{
				eliminaInCoda(matricola);
				isElim=true;
				return;
			}
			
			if (i==getElementi() && getLinkPosizione(i).getInfo().getMatricola()==matricola) 
			{
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
		
		/*FileInputStream file=new FileInputStream("tessere.bin");
		ObjectInputStream reader= new ObjectInputStream(file);
		
		ListaTessere listaDaEliminare;
		
		listaDaEliminare=(ListaTessere)(reader.readObject());
		file.close();
		
		for (int i = 1; i < listaDaEliminare.getElementi(); i++)
		{
			if (listaDaEliminare.getTessera(i).getMatricola()==matricola)
			{
				Nodo p;
				p=getLinkPosizione(matricola);
				Nodo precedente=getLinkPosizione(matricola-1);
				precedente.setLink(p.getLink());
				elementi--;
				esportaEliminatiCSV("eliminati.txt",matricola);
				
			}
		}*/
		
	}
	
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
	
	public void convertiTessera(Tessera[] tessera) throws TesseraException, IOException, FileException, ClassNotFoundException
	{
		for (int i = tessera.length; i > 0; i--) 
			inserisciTessera(tessera[i-1]);
	}
	
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
	
	private static Tessera[] arrayCopia(Tessera[] array)
	{
		Tessera[] copia=new Tessera[array.length];
		
		for (int i = 0; i < copia.length; i++)
		{
			copia[i]=array[i];
		}
		return copia;
	}
	
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
	
	public String[] caricaCSV(String nomeFile) throws IOException, FileException, EccezioneTextFileEOF
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
