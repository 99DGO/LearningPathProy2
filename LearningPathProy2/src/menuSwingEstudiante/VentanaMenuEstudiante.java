package menuSwingEstudiante;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import menuSwing.VentanaCaminosDisponibles;
import persistencia.CentralPersistencia;

public class VentanaMenuEstudiante extends JFrame
{
	private PanelBotonesMenuEstudiante pBotones;

	private VentanaCaminosDisponibles ventCaminosDisp=null;
	private VentanaAvancesEstudiante ventAvances=null;
	
	public VentanaMenuEstudiante( )
	{
        setLayout( new BorderLayout( ) );
        
        pBotones= new PanelBotonesMenuEstudiante(this);
        
        add( pBotones, BorderLayout.CENTER );
        
        setTitle( "Learning Path System: Menu estudiante" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
	}


    public void mostrarVentanaCaminosDisponibles( )
    {
        if( ventCaminosDisp == null || !ventCaminosDisp.isVisible( ) )
        {
        	ventCaminosDisp = new VentanaCaminosDisponibles();
        	ventCaminosDisp.setVisible( true );
        }
    }


	public void mostrarVentanaAvances()
	{
        if( ventAvances == null || !ventAvances.isVisible( ) )
        {
        	ventAvances = new VentanaAvancesEstudiante();
        	ventAvances.setVisible( true );
        }
		
	}


	public void mostrarVentanaActividadEnvio() 
	{
		// TODO Auto-generated method stub
		
	}
    
  
		

}
