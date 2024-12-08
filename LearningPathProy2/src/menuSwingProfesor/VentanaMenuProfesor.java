package menuSwingProfesor;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class VentanaMenuProfesor extends JFrame
{
	private String idProfesor;
		
	private PanelBotonesMenuProfesor pBotones;
	
	private VentanaCaminosDisp ventCaminosDisp;
	private VentanaCreacionCamino ventCrearCamino;
	
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

	public void mostrarVentanaCaminosDisponibles() 
	{
		if( ventCaminosDisp == null || !ventCaminosDisp.isVisible( ) )
        {
        	ventCaminosDisp = new VentanaCaminosDisp();
        	ventCaminosDisp.setVisible( true );
        }
		
	}
	
	public void mostrarVentanaCreacionCamino() 
	{
		if( ventCrearCamino == null || !ventCrearCamino.isVisible( ) )
        {
			ventCrearCamino = new VentanaCreacionCamino(idProfesor);
			ventCrearCamino.setVisible( true );
        }
		
	}
}
