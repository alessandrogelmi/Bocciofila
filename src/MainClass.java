import java.io.IOException;
import java.time.LocalDate;

public class MainClass
{

	public static void main(String[] args)
	{
		String[] vociMenu= {"0 --> ESCI","1 --> Inserisci tessera", "2 --> Elimina Tessera","3 --> Visualizza tessere in ordine alfabetico",
				"4 --> Visualizza tessere in ordine di anzianità","5 --> Visulizza dati tesserato"};
		Menu menuPrincipale=new Menu("Menu Principale",vociMenu);
		ConsoleInput tastiera= new ConsoleInput();
		int sceltaMenu=0;
		
		
		do 
		{
			sceltaMenu=menuPrincipale.scelta();
			
			switch (sceltaMenu) 
			{
			case 1:
				try 
				{
					int anno,m,g;
					Tessera t=new Tessera();
					System.out.println("Inserisci la matricola: ");
					t.setMatricola(tastiera.readInt());
					System.out.println("Inserisci il nome: ");
					t.setNome(tastiera.readString());
					System.out.println("Inserisci il cognome: ");
					t.setCognome(tastiera.readString());
					System.out.println("Inserisci il codice fiscale: ");
					t.setCodiceFiscale(tastiera.readString());
					System.out.print("Inserisci anno di nascita:");
					anno=(tastiera.readInt());
					System.out.print("Inserisci mese di nascita:");
					m=(tastiera.readInt());
					System.out.print("Inserisci giorno di nascita:");
					g=(tastiera.readInt());
					t.setDataNascita(LocalDate.of(anno,m,g));
					System.out.println("Inserisci l'info (nuova/rinnovo): ");
					t.setInfo(tastiera.readString());

					ListaTessere lista=new ListaTessere();
					lista.inserisciTessera(t);
					System.out.println("Tessera aggiunta con successo");
					lista.salvaLista("tessere.bin");
					break;
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			case 0:
			{
				break;
			}
			}
			
		} while (sceltaMenu!=0);

	}

}
