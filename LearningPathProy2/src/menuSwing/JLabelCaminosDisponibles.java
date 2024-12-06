package menuSwing;

import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import traductores.TraductorCamino;

public class JLabelCaminosDisponibles extends JLabel
{

	private String caminosDispString="";
	
	public JLabelCaminosDisponibles()
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
		this.setVisible(true);
	}
}
