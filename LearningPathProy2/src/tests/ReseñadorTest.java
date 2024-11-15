package tests;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import controllers.Reseñador;

public class ReseñadorTest 
{

	@Test
	public void dejarRatingActividadTest()
	{
		try 
		{
			Reseñador.dejarRatingActividad(3.5, "", "");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
	}

}
