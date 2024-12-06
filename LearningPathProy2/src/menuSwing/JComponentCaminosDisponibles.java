package menuSwing;

import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import traductores.TraductorCamino;

public class JComponentCaminosDisponibles extends JTextArea
{

	private String caminosDispString="";
	
	public JComponentCaminosDisponibles()
	{
		try
		{
			HashMap<String, String> caminos = TraductorCamino.verTodosCaminos();
			for (HashMap.Entry<String, String> camino : caminos.entrySet())
			{
				caminosDispString+=("Titulo: " + camino.getKey() + " | Creador: " + camino.getValue()+"\n");
			}
		}
		
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		this.setText(caminosDispString);
		this.setEditable(false);
		this.setVisible(true);
	}
}
