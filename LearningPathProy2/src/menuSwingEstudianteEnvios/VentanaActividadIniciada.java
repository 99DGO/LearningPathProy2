package menuSwingEstudianteEnvios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaActividadIniciada extends JFrame implements ActionListener
{
	private PanelActividadInfo pActividad;
	
	private JPanel pBotones;
	
	private JButton bRegresar;
	public static final String REGRESAR="Salir";

	private JButton bRealizarEnvio;
	public static final String REALIZARENVIO="Salir";

	private String idCamino;
	private String idActividad;
	private String idEstudiante;
	
	public VentanaActividadIniciada(String idCamino, String idActividad, String idEstudiante)
	{
		this.idCamino=idCamino;
		this.idActividad=idActividad;
		this.idEstudiante=idEstudiante;
		
		this.setLayout(new BorderLayout());
		
		pActividad=new PanelActividadInfo(idActividad, idCamino);
		
		this.add(pActividad, BorderLayout.CENTER);
		
		addPBotones();
	}
	
	private void addPBotones()
	{
		pBotones= new JPanel();
		pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS ) );

        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pBotones.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bRealizarEnvio = new JButton( "Realizar envio" );
        bRealizarEnvio.setActionCommand( REALIZARENVIO );
        bRealizarEnvio.addActionListener( this );
        pBotones.add( bRealizarEnvio );
        bRealizarEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.add(pBotones, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
        String comando = e.getActionCommand( );
        
        if( comando.equals( REGRESAR ) )
        {
        	dispose();
        }
        else if( comando.equals(REALIZARENVIO))
        {
        	abrirVentanaEnvio();
        }
		
	}

	private void abrirVentanaEnvio() 
	{
		// TODO Auto-generated method stub
		
	}
	
	
}
