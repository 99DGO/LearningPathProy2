package menuSwingEstudiante;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.Inscriptor;
import menuSwing.JLabelCaminosDisponibles;
import traductores.TraductorCamino;

public class VentanaCaminosDispInscripcion extends JFrame implements ActionListener
{
	private JLabelCaminosDisponibles lCaminosDisp;
	
	private JPanel pInscripcion;
	private JTextField txtNombreCamino;
	
	private JButton bRegresar;
	private static final String REGRESAR="regresar";
	
	private JButton bInscripcion;
	private static final String INSCRIBIR="inscribir";
	
	private String idEstudiante;
	
	public VentanaCaminosDispInscripcion(String idEstudianteP)
	{
		this.idEstudiante=idEstudianteP;
		
		this.setLayout(new BorderLayout());
		
		//Añado label de los caminos
		lCaminosDisp= new JLabelCaminosDisponibles();
		this.add(lCaminosDisp, BorderLayout.CENTER);
		
		//Añado botones y textField de inscripcion
		pInscripcion= new JPanel();
		
	    //Nombre de camino 
		txtNombreCamino = new JTextField( 15 );
		txtNombreCamino.setEditable( true );
    	
    	JLabel nombreCaminoLabel= new JLabel("Camino que te deseas inscribir: ");

    	JPanel panelNombreCamino= new JPanel();
    	panelNombreCamino.setLayout(new FlowLayout(FlowLayout.CENTER));
    	panelNombreCamino.add(nombreCaminoLabel);
    	panelNombreCamino.add(txtNombreCamino);
    	
    	pInscripcion.add(panelNombreCamino);
    	panelNombreCamino.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
        //Boton de inscripcion
        bInscripcion = new JButton( "Inscribirse" );
        bInscripcion.setActionCommand( INSCRIBIR );
        bInscripcion.addActionListener( this );
        pInscripcion.add( bInscripcion );
        bInscripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Boton de regresar
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pInscripcion.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
 
    	
    	//Añado a la ventana
		this.add(pInscripcion, BorderLayout.SOUTH);
		
		
        // Termina de configurar la ventana
        setTitle( "Learning Path System: Inscribirse a un camino" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setLocationRelativeTo( null );
        setVisible( true );
        
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
        String comando = e.getActionCommand( );
        
        if( comando.equals( REGRESAR ) )
        {
        	dispose();
        }
        else if(comando.equals( INSCRIBIR ))
        {
			try
			{
				String idCamino = TraductorCamino.getIDfromNombre(txtNombreCamino.getText());

				Inscriptor.inscribirseCamino(idCamino, idEstudiante);

				JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
	
			}
			catch (Exception e1)
			{
        		JOptionPane.showMessageDialog(null, e1.getMessage());
			}
        }
		
	}
}
