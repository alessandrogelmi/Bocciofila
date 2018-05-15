import java.io.IOException;
import java.time.LocalDate;

public class MainClass
{

	public static void main(String[] args)
	{
		String[] vociMenu= {"0 --> ESCI","1 --> Inserisci tessera", "2 --> Elimina Tessera", "3 --> Visualizza tessere in ordine alfabetico",
				"4 --> Visualizza tessere in ordine di anzianità", "5 --> Visulizza dati tesserato", "6 --> Modifica quota annuale di tesseramento"};
		Menu menuPrincipale=new Menu("Menu Principale",vociMenu);
		ConsoleInput tastiera= new ConsoleInput();
		
		
		ListaTessere listaTessere=new ListaTessere();
		
		try 
		{
			listaTessere=listaTessere.caricaLista("C:\\Users\\Supermedia\\Documents\\Scuola\\Informatica\\OOP\\ProgettiJava\\Bocciofila\\FileBIN\\tessere.bin");
			System.out.println("Caricamento Completato");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Impossibile caricare dal file perchè la classe Tessera");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
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
					System.out.print("Inserisci la matricola: ");
					t.setMatricola(tastiera.readInt());
					System.out.print("Inserisci il nome: ");
					t.setNome(tastiera.readString());
					System.out.print("Inserisci il cognome: ");
					t.setCognome(tastiera.readString());
					System.out.print("Inserisci il codice fiscale: ");
					t.setCodiceFiscale(tastiera.readString());
					System.out.print("Inserisci anno di nascita:");
					anno=(tastiera.readInt());
					System.out.print("Inserisci mese di nascita:");
					m=(tastiera.readInt());
					System.out.print("Inserisci giorno di nascita:");
					g=(tastiera.readInt());
					t.setDataNascita(LocalDate.of(anno,m,g));
					System.out.print("Inserisci l'info (nuova/rinnovo): ");
					t.setInfo(tastiera.readString());

					
					listaTessere.inserisciTessera(t);
					System.out.println("Tessera aggiunta con successo");
					listaTessere.salvaLista("C:\\Users\\Supermedia\\Documents\\Scuola\\Informatica\\OOP\\ProgettiJava\\Bocciofila\\FileBIN\\tessere.bin");
					break;
				} 
				catch (IOException e)
				{
					System.out.println("File non trovato");
				} 
				catch (TesseraException e)
				{
					System.out.println(e.toString());
				} 
				catch (FileException e)
				{
					System.out.println("Impossibile scrivere sul file");
				}
				
			case 2:
				int x;
				System.out.println("Inserisci la matricola della tessere da eliminare");
				ConsoleInput y=new ConsoleInput();
	
				try 
				{
					x=y.readInt();
					for (int i = 1; i < listaTessere.getElementi(); i++) 
					{
						if(x==listaTessere.getTessera(i).getMatricola())
						{
							listaTessere.eliminaInPosizione(i);
							listaTessere.salvaLista("tessere.bin");
							System.out.println("Salvataggio completo");
						}
					}
				} 
				catch (NumberFormatException e)
				{
					System.out.println("Il dato inserito non è valido");
				} 
				catch(IOException e)
				{
					System.out.println("File non trovato");
				}
				catch (TesseraException e)
				{
					System.out.println(e.toString());
				} 
				catch (FileException e) 
				{
					System.out.println("Impossibile leggere dal file");
				}
				
			/*case 6:
				int nuovaQuota;
				System.out.print("Inserisci il prezzo della nuova quota: ");
				ConsoleInput n=new ConsoleInput();
				try 
				{
					nuovaQuota=n.readInt();
					do
					{
					listaTessere.getTessera().setQuotaAnnuale(nuovaQuota);
					}while(listaTessere.getElementi()!=null)
				} 
				catch (TesseraException e) 
				{
					System.out.println(e.toString());
				} 
				catch (NumberFormatException e) 
				{
					System.out.println("Il dato inserito non è valido");
				} 
				catch (IOException e)
				{
					System.out.println("File non trovato");
				}*/
				
			case 0:
				break;
			case 4:
				System.out.println(listaTessere.toString());
			
			default:
				System.out.println("Inserimento non valido");
				break;
			}
			
		} while (sceltaMenu!=0);

	}

}
