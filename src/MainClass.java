import java.io.IOException;
import java.time.LocalDate;

import javax.naming.directory.ModificationItem;

public class MainClass
{
	public static void main(String[] args)
	{
		ListaTessere listaTessere=new ListaTessere();
	try 
		{
			//listaTessere.salvaLista("tessere.bin");
			listaTessere=listaTessere.caricaLista("tessere.bin");
			System.out.println("Caricamento Completato");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Impossibile caricare oggetti di tipo Tessera");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		String[] vociMenu= {"0 --> ESCI","1 --> Inserisci tessera", "2 --> Elimina Tessera", "3 --> Visualizza tessere in ordine alfabetico",
				"4 --> Visualizza tessere in ordine di anzianità", "5 --> Visulizza dati tesserato", "6 --> Modifica quota annuale di tesseramento",
				"7 --> Visualizza tessere presenti"};
		Menu menuPrincipale=new Menu("Menu Principale",vociMenu);
		ConsoleInput tastiera= new ConsoleInput();
		
		int sceltaMenu=0;
		
		do 
		{
			sceltaMenu=menuPrincipale.scelta();
			
			switch (sceltaMenu) 
			{
			case 1:
			{
				try 
				{
					int anno,m,g;
					boolean isElim = false;
					Tessera t=new Tessera();
					System.out.print("Inserisci la matricola: ");
					try
					{
						t.setMatricola(tastiera.readInt());
					}
					catch (NumberFormatException e)
					{
						System.out.println("Il valore inserito non è numerico! Reinseriscilo");
						break;
					} 
					System.out.print("Inserisci il nome: ");
					t.setNome(tastiera.readString());
					System.out.print("Inserisci il cognome: ");
					t.setCognome(tastiera.readString());
					System.out.print("Inserisci il codice fiscale: ");
					t.setCodiceFiscale(tastiera.readString());
					System.out.print("Inserisci anno di nascita:");
					anno=tastiera.readInt();
					System.out.print("Inserisci mese di nascita:");
					m=(tastiera.readInt());
					System.out.print("Inserisci giorno di nascita:");
					g=(tastiera.readInt());
					t.setDataNascita(LocalDate.of(anno,m,g));
					System.out.print("Inserisci l'info (nuova/rinnovo): ");
					t.setInfo(tastiera.readString());
					System.out.println("La quota annule da pagare è di "+t.getQuotaAnnuale()+" €");
					
					String[] elim = null;
					try 
					{
						elim = listaTessere.caricaCodiciFiscali("eliminati.txt");
						
						for (int i = 0; i < elim.length; i++) 
						{
							if(elim[i]!=null)
							{
								if(elim[i].compareTo(t.getCodiceFiscale())==0)
								{
									System.out.println("L'utente inserito è gia negli eliminati.");
									isElim=true;
									break;
								}
							}
								
						}
					} 
					catch (EccezioneTextFileEOF e)
					{
						
					}
					
					if (isElim==false) 
					{
						listaTessere.inserisciTessera(t);
						System.out.println("Tessera aggiunta con successo");
						listaTessere.salvaLista("tessere.bin");
					}
					
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
				} catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				} 
				break;
			}
			case 2:
			{
				int x;
				System.out.print("Inserisci la matricola della tessere da eliminare: ");
				ConsoleInput y=new ConsoleInput();
	
				try 
				{
					x=y.readInt();
					listaTessere.eliminaInPosizione(x);
					listaTessere.salvaLista("tessere.bin");
					System.out.println("Salvataggio completo");
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
					System.out.println("Tessera non trovata");
				} 
				catch (FileException e) 
				{
					System.out.println("Impossibile leggere dal file");
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
				break;
			}
			
			case 3:
			{
				try 
				{
					Tessera[] arrayList=listaTessere.toArray();
					arrayList=listaTessere.ordinaAlfabetico(arrayList);
					ListaTessere listaAlfabetico=new ListaTessere();
					listaAlfabetico.convertiTessera(arrayList);
					System.out.println(listaAlfabetico.toString());
					
				}
				catch (TesseraException e)
				{
					System.out.println("Registro vuoto");
				} 
				catch (IOException e)
				{
					System.out.println("Impossibile leggere dal file");
				} 
				catch (FileException e) 
				{
					System.out.println("File non trovato");
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
				break;
			}
			
			case 4:
			{
				try 
				{
					Tessera[] arrayList=listaTessere.toArray();
					arrayList=listaTessere.ordinaAnzianita(arrayList);
					ListaTessere listaAnzianita=new ListaTessere();
					listaAnzianita.convertiTessera(arrayList);
					System.out.println(listaAnzianita.toString());
				}
				catch (TesseraException e)
				{
					System.out.println("Registro vuoto");
				} 
				catch (IOException e)
				{
					System.out.println("Impossibile leggere dal file");
				} 
				catch (FileException e) 
				{
					System.out.println("File non trovato");
				}
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				}
				break;
			}
			
			case 5:
			{
				{
					String x="";
					String y="";
					System.out.print("Inserisci il nome del tesserato: ");
					ConsoleInput a=new ConsoleInput();
					try 
					{
						x=a.readString();
					} catch (IOException e)
					{
						System.out.println("Impossibile trovare il file");
					}
					System.out.print("Inserisci il cognome del tesserato: ");
					ConsoleInput b=new ConsoleInput();
					try
					{
						y=b.readString();
					} catch (IOException e)
					{
						System.out.println("Impossibile trovare il file");
					}
					
					listaTessere.visualizzaDatiTesserato(x, y);
				}
				break;
			}
				
			case 6:
			{
				int x;
				System.out.print("Inserisci la nuova quota annuale: ");
				ConsoleInput y=new ConsoleInput();
				try 
				{
					x=y.readInt();
					Tessera.setQuotaAnnuale(x);
				} 
				catch (NumberFormatException e)
				{
					System.out.println("Il dato inserito non è valido");
				} 
				catch (IOException e) 
				{
					System.out.println("Impossibile trovare il file");
				}
				
			}
				
				
			case 7:
				System.out.println(listaTessere.toString());
				
			case 0:
				break;
			
			}
			
		} while (sceltaMenu!=0);

	}

}
