package menuSwingProfesor;

import javax.swing.JFrame;

public class VentanaMenuProfesor extends JFrame
{
	private String idProfesor;
	
	private String idEstudiante;
	
	public VentanaMenuProfesor( String idProfesorP)
	{
		this.idProfesor=idProfesorP;
		
        setTitle( "Learning Path System: Menu profesor" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setLocationRelativeTo( null );
        setVisible( true );

	}
}
