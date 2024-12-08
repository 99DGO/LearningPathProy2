package menuSwingProfesor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import creadores.CreadorCamino;

public class VentanaCreacionCamino extends JFrame implements ActionListener
{
	private JPanel pInfoCamino;
	
	private JButton bRegresar;
	private static final String REGRESAR="regresar";
	
	private JButton bCrearCamino;
	private static final String CREARCAMINO="crear camino";
		
	private String idProfesor;
	
	private JTextField txtTitulo;
	private JTextField txtDescripcion;
	private JTextField txtDificultad;
		
	private JComboBox<Integer> ccbNumObjetivos;
	private static final String NUMOBJETIVOS="numero objetivos";
	
	private JPanel pObjetivos = new JPanel();
	
	private List<String> lstObjetivos=new LinkedList<String>();
	
	public VentanaCreacionCamino(String idProfesor)
	{
		this.setLayout(new BorderLayout());

		this.idProfesor=idProfesor;
	
		addPBotones();
		addPInfoCamino();
		
        // Termina de configurar la ventana
        setTitle( "Learning Path System: Ver caminos disponibles" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 700, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
	}

	private void addPBotones()
	{
    	//Boton de regresar
		JPanel pBotones = new JPanel();
		pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS ) );
		
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pBotones.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        bCrearCamino= new JButton( "Crear camino" );
        bCrearCamino.setActionCommand( CREARCAMINO );
        bCrearCamino.addActionListener( this );
        pBotones.add( bCrearCamino );
        bCrearCamino.setAlignmentX(Component.CENTER_ALIGNMENT);
        
    	//Añado a la ventana
		this.add(pBotones, BorderLayout.SOUTH);
	}
	
	private void addPInfoCamino()
	{
		pInfoCamino = new JPanel();
		pInfoCamino.setLayout(new BoxLayout(pInfoCamino, BoxLayout.Y_AXIS ) );
        
        //Titulo 
        txtTitulo = new JTextField( 30 );
        txtTitulo.setEditable( true );
    	
    	JLabel lblTitulo= new JLabel("Titulo: ");

    	JPanel pTitulo= new JPanel();
    	pTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pTitulo.add(lblTitulo);
    	pTitulo.add(txtTitulo);
    	
    	pInfoCamino.add(pTitulo);
    	pTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Descripcion
        txtDescripcion = new JTextField( 30 );
        txtDescripcion.setEditable( true );
    	
    	JLabel lblDescripcion= new JLabel("Descripcion: ");

    	JPanel pDescripcion= new JPanel();
    	pDescripcion.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pDescripcion.add(lblDescripcion);
    	pDescripcion.add(txtDescripcion);
    	
    	pInfoCamino.add(pDescripcion);
    	pDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Dificultad
        txtDificultad = new JTextField( 15 );
        txtDificultad.setEditable( true );
    	
    	JLabel lblDificultad= new JLabel("Dificultad (escribe el numero en formato 1.0): ");

    	JPanel pDificultad= new JPanel();
    	pDificultad.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pDificultad.add(lblDificultad);
    	pDificultad.add(txtDificultad);
    	
    	pInfoCamino.add(pDificultad);
    	pDificultad.setAlignmentX(Component.CENTER_ALIGNMENT);
       	
    	//Panel numero de objetivos
    	JPanel pNumObjetivos= new JPanel();

    	JLabel lblObjetivos= new JLabel("Número de objetivos: ");

    	Integer[] numeroObjetivos = new Integer[]{1,2,3,4,5,6,7,8,9,10};
    	ccbNumObjetivos=new JComboBox<Integer>(numeroObjetivos);
    	ccbNumObjetivos.setEnabled(true);
    	ccbNumObjetivos.addActionListener(this);
    	ccbNumObjetivos.setActionCommand( NUMOBJETIVOS );
    	
    	pNumObjetivos.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pNumObjetivos.add(lblObjetivos);
    	pNumObjetivos.add(ccbNumObjetivos);
       	
    	pInfoCamino.add(pNumObjetivos);
    	pNumObjetivos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Panel objetivos
    	pObjetivos.setLayout(new BoxLayout(pObjetivos, BoxLayout.Y_AXIS ) );
    	pInfoCamino.add(pObjetivos);
    	
        //Add el panel
		this.add(pInfoCamino, BorderLayout.CENTER);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        String comando = e.getActionCommand( );
        
        if( comando.equals( REGRESAR ) )
        {
        	dispose();
        }
        else if (comando.equals(CREARCAMINO))
        {
        	try 
        	{
        		guardarObjetivos();
				CreadorCamino.crearCaminoCero(txtTitulo.getText(), txtDescripcion.getText(), lstObjetivos, 
						Double.valueOf(txtDificultad.getText()), idProfesor);
				
        		JOptionPane.showMessageDialog(null, "Camino creado exitosamente");

			} 
        	catch (Exception e1) 
        	{
        		JOptionPane.showMessageDialog(null, e1.getMessage());
			}
        }
        else if (comando.equals(NUMOBJETIVOS))
        {
        	mostrarPanelObjetivos();
        }
	}

	private void guardarObjetivos() 
	{
		for (Component pObjetivoInd : pObjetivos.getComponents())
		{
			Component[] lblObjTxtObjetivo = ((JPanel) pObjetivoInd).getComponents();
			String strObjetivo = ((JTextField) lblObjTxtObjetivo[1]).getText();
			
			lstObjetivos.add(strObjetivo);
		}
	}

	private void mostrarPanelObjetivos() 
	{
		pObjetivos.setVisible(false);

    	pObjetivos.removeAll();

		Integer numObjetivos=(Integer) ccbNumObjetivos.getSelectedItem();
		
		for (int i = 1; i < numObjetivos+1; i++) 
		{
			JTextField txtObjetivo = new JTextField( 25 );
			txtObjetivo.setEditable( true );
	    	
	    	JLabel lblObjetivo= new JLabel("Objetivo "+String.valueOf(i)+": ");

	    	JPanel pObjetivoInd= new JPanel();
	    	pObjetivoInd.setLayout(new FlowLayout(FlowLayout.CENTER));
	    	pObjetivoInd.add(lblObjetivo);
	    	pObjetivoInd.add(txtObjetivo);
	    	
	    	pObjetivos.add(pObjetivoInd);
	    	pObjetivoInd.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		pObjetivos.setVisible(true);
		
		
	}
}
