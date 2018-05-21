import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class TestListaTessere {

	@Test
	void testCostruttoreLista() 
	{
		ListaTessere l1=new ListaTessere();
		assertTrue("Test Costruttore", l1.getElementi()==0);
	}
	
	@Test
	void testGetElementi() throws ClassNotFoundException, IOException, TesseraException, FileException 
	{
		Tessera t1=new Tessera();
		ListaTessere l1=new ListaTessere();
		l1.inserisciTessera(t1);
		assertTrue("Test get Elementi", l1.getElementi()==1);
	}
	
	@Test
	void testToString() 
	{
		ListaTessere l1=new ListaTessere();
		assertTrue("Test To String", l1.toString()=="Head");
	}
	
	@Test
	void testGetTessera() throws ClassNotFoundException, IOException, TesseraException, FileException 
	{
		Tessera t1=new Tessera();
		ListaTessere l1=new ListaTessere();
		l1.inserisciTessera(t1);
		
		assertTrue("Test Get Tessera", l1.getTessera(1).getMatricola()==t1.getMatricola());
	}
	
	@Test
	void testInserisciTessera() throws ClassNotFoundException, IOException, TesseraException, FileException 
	{
		Tessera t1=new Tessera();
		ListaTessere l1=new ListaTessere();
		l1.inserisciTessera(t1);
		assertTrue("Test Inserisci tessera", l1.getElementi()==1);
	}
	
	@Test 
	public void testEliminaInTesta() throws ClassNotFoundException, IOException, TesseraException, FileException
	{
		ListaTessere l1=new ListaTessere();
		Tessera t1=new Tessera();
		l1.inserisciTessera(t1);
		l1.eliminaInTesta(0);
		assertTrue("Elimina in coda", l1.getElementi()==0);
	}
	
	@Test 
	public void testEliminaInCoda() throws ClassNotFoundException, IOException, TesseraException, FileException
	{
		ListaTessere l1=new ListaTessere();
		Tessera t1=new Tessera();
		l1.inserisciTessera(t1);
		l1.eliminaInCoda(0);
		assertTrue("Elimina in testa", l1.getElementi()==0);
	}
	
	@Test 
	public void testEliminaInPosizione() throws ClassNotFoundException, IOException, TesseraException, FileException
	{
		ListaTessere l1=new ListaTessere();
		Tessera t1=new Tessera();
		Tessera t2=new Tessera();
		l1.inserisciTessera(t1);
		l1.inserisciTessera(t2);
		l1.eliminaInCoda(0);
		assertTrue("Elimina in posizione", l1.getElementi()==1);
	}
	
	@Test 
	public void testToArray() throws ClassNotFoundException, IOException, TesseraException, FileException
	{
		ListaTessere l1=new ListaTessere();
		Tessera t1=new Tessera();
		l1.inserisciTessera(t1);
		Tessera[] arrayTessere=new Tessera[5];
		l1.toArray();
		assertTrue("To Array", l1.getTessera(1)==arrayTessere.);
	}
	

}
