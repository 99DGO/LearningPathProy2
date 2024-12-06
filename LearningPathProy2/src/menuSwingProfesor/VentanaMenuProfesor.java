package menuSwingProfesor;

import javax.swing.JFrame;

public class VentanaMenuProfesor extends JFrame
{
	private String idProfesor;
	
	public VentanaMenuProfesor( String idProfesorP)
	{
		this.idProfesor=idProfesorP;
		
        setTitle( "Learning Path System: Menu profesor" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

	}
}
