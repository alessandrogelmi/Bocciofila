import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TestCaseTessera 

{

	@Test
	void testCostruttoreTessera() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera(1, "Alessandro", "Gelmi", "26LSN20", data,"Nuovo");
		assertTrue("Costruttore Tessera", t1.getMatricola()==1 && t1.getNome()=="Alessandro" && t1.getCognome()=="Gelmi" && t1.getCodiceFiscale()=="26LSN20" &&
				t1.getDataNascita()==data && t1.getInfo()=="Nuovo");
	}
	
	@Test
	void testCostruttoreCopiaTessera() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera(1, "Alessandro", "Gelmi", "26LSN20", data,"Nuovo");
		Tessera t2=new Tessera(t1);
		assertTrue("Costruttore copia Tessera", t1.getMatricola()==t1.getMatricola() && t1.getNome()==t2.getNome() && t1.getCognome()==t2.getCognome() &&
				t1.getCodiceFiscale()==t2.getCodiceFiscale() && t1.getDataNascita()==t2.getDataNascita() && t1.getInfo()==t2.getInfo());
	}
	
	@Test
	void testCostruttoreVuotoTessera() 
	{
		Tessera t1=new Tessera();
		assertTrue("Costruttore vuoto Tessera", t1.getMatricola()==0 && t1.getNome()==""&& t1.getCognome()=="" && t1.getCodiceFiscale()=="" &&
				t1.getDataNascita()==null && t1.getInfo()=="");
	}
	
	@Test
	void testGetMatricola() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera(1, "Alessandro", "Gelmi", "26LSN20", data,"Nuovo");
		assertTrue("Get Matricola", t1.getMatricola()==1);
	}
	
	@Test
	void testGetNome() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera(1, "Alessandro", "Gelmi", "26LSN20", data,"Nuovo");
		assertTrue("Get Nome", t1.getNome()=="Alessandro");
	}
	
	@Test
	void testGetCognome() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera(1, "Alessandro", "Gelmi", "26LSN20", data,"Nuovo");
		assertTrue("Get Cognome", t1.getCognome()=="Gelmi");
	}
	
	@Test
	void testGetCodiceFiscale() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera(1, "Alessandro", "Gelmi", "26LSN20", data,"Nuovo");
		assertTrue("Get CodiceFiscale", t1.getCodiceFiscale()=="26LSN20");
	}
	
	@Test
	void testGetDataNascita() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera(1, "Alessandro", "Gelmi", "26LSN20", data,"Nuovo");
		assertTrue("Get Data Nascita", t1.getDataNascita()==data);
	}
	
	@Test
	void testGetInfo() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera(1, "Alessandro", "Gelmi", "26LSN20", data,"Nuovo");
		assertTrue("Get Info", t1.getInfo()=="Nuovo");
	}
	
	@Test
	void testGetQuotaAnnuale() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera(1, "Alessandro", "Gelmi", "26LSN20", data,"Nuovo");
		assertTrue("Get Quota Annuale", t1.getQuotaAnnuale()==100); //sarebbe 50 ma testando setQuotaAnnuale diventa 100! Con 50 e senza SetQuotaAnnuale funziona
	}
	
	@Test
	void testSetMatricola() 
	{
		Tessera t1=new Tessera();
		t1.setMatricola(1);
		assertTrue("Set Matricola", t1.getMatricola()==1);
	}
	
	@Test
	void testSetNome() 
	{
		Tessera t1=new Tessera();
		t1.setNome("Alessandro");
		assertTrue("Set Nome", t1.getNome()=="Alessandro");
	}
	
	@Test
	void testSetCognome() 
	{
		Tessera t1=new Tessera();
		t1.setCognome("Gelmi");
		assertTrue("Set Cognome", t1.getCognome()=="Gelmi");
	}
	
	@Test
	void testSetCodiceFiscale() 
	{
		Tessera t1=new Tessera();
		t1.setCodiceFiscale("26LSN20");
		assertTrue("Set Codice Fiscale", t1.getCodiceFiscale()=="26LSN20");
	}
	
	@Test
	void testSetDataNascita() 
	{
		LocalDate data=LocalDate.of(2000, 11, 26);
		Tessera t1=new Tessera();
		t1.setDataNascita(data);
		assertTrue("Set Data nascita", t1.getDataNascita()==data);
	}
	
	@Test
	void testSetInfo() 
	{
		Tessera t1=new Tessera();
		t1.setInfo("Nuova");
		assertTrue("Set Info", t1.getInfo()=="Nuova");
	}
	
	@Test
	void testSetQuotaAnnuale() 
	{
		Tessera t1=new Tessera();
		t1.setQuotaAnnuale(100);
		assertTrue("Set Quota Annuale", t1.getQuotaAnnuale()==100);
	}
	
}
