package menuSwingProfesor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import menuSwingEstudiante.PanelBotonesMenuEstudiante;

public class VentanaMenuProfesor extends JFrame
{
	private String idProfesor;
	
	private String idEstudiante;
	
	private PanelBotonesMenuProfesor pBotones;
	
	public VentanaMenuProfesor( String idProfesorP)
	{
		this.idProfesor=idProfesorP;
				
        setLayout( new BorderLayout( ) );
        
        pBotones= new PanelBotonesMenuProfesor(this);
        
        add( pBotones, BorderLayout.CENTER );
        
        setTitle( "Learning Path System: Menu profesor" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setLocationRelativeTo( null );
        setVisible( true );

	}
}
