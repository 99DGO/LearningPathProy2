package menuSwingProfesor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import menuSwingEstudiante.VentanaMenuEstudiante;
import persistencia.CentralPersistencia;

public class PanelBotonesMenuProfesor extends JPanel implements ActionListener
{
	
	private JButton bCaminosDisponibles;
	public static final String CAMINOSDISPONIBLES="caminos disp";

	private JButton bSalir;
	public static final String SALIR="salir";
	
	private VentanaMenuProfesor ventMenuProf;

	public PanelBotonesMenuProfesor(VentanaMenuProfesor ventana)
	{
		this.ventMenuProf=ventana;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
	    
	    //Botones
	    bCaminosDisponibles = new JButton( "Ver caminos disponibles" );
	    bCaminosDisponibles.setActionCommand( CAMINOSDISPONIBLES );
	    bCaminosDisponibles.addActionListener( this );
	    this.add( bCaminosDisponibles );
	    bCaminosDisponibles.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    bSalir = new JButton( "Salir" );
	    bSalir.setActionCommand( SALIR );
	    bSalir.addActionListener( this );
	    this.add( bSalir );
	    bSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{       
		String comando = e.getActionCommand( );
    
	    if( comando.equals( SALIR ) )
	    {
			try
			{
				CentralPersistencia.guardarTodo(false);
			}
			catch (Exception e1)
			{
	    		JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
	        System.exit(0);
	    }
        else if (comando.equals(CAMINOSDISPONIBLES))
        {
        	ventMenuProf.mostrarVentanaCaminosDisponibles();
        }

	}
}
