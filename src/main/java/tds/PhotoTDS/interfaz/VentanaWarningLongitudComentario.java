package tds.PhotoTDS.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaWarningLongitudComentario extends JDialog {

	private static final long serialVersionUID = -2682605879077064524L;
	
	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args) {
		try {
			VentanaWarningLongitudComentario dialog = new VentanaWarningLongitudComentario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VentanaWarningLongitudComentario() {
		setResizable(false);
		setBounds(100, 100, 302, 136);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel label = new JLabel("Longitud de comentario superada");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(ev -> {
					this.dispose();
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
