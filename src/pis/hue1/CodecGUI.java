package pis.hue1;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;


/**
 * Date: 07.12.2020
 * Das ist ein Programm zum ver- und entschluesseln von Woerten
 * @author Ibrahim Eraslan
 * version 1.0
 *
 */
public class CodecGUI extends JFrame {
	
	
	/**
	 * 
	 */
	
	/**
	 * GUI Komponente
	 */
	private JPanel contentPane;
	private JTextField los1_K;
	private JTextField los2_K;
	private JTextField los1_G;
	private JTextField los2_G;
	private JTextField loskod_C;
	private JTextField losdekod_C;

	/**
	 * Launch the application.
	 * Main Methode
	 */
	public static void main(String[] args) {
		
		
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CodecGUI frame = new CodecGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public CodecGUI() {
		
		System.out.println(EventQueue.isDispatchThread());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 418);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 537, 357);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Wuerfel kodiere", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Geben Sie den Klartext ein : ");
		lblNewLabel.setBounds(174, 11, 171, 31);
		panel.add(lblNewLabel);
		
		JTextArea klarText = new JTextArea();
		klarText.setLineWrap(true);
		klarText.setBounds(10, 40, 512, 48);
		panel.add(klarText);
		
		
		JLabel label1 = new JLabel("Geben Sie das erste Losungswort ein : ");
		label1.setBounds(10, 99, 232, 31);
		panel.add(label1);
		
		los1_K = new JTextField();
		los1_K.setBounds(10, 141, 232, 20);
		panel.add(los1_K);
		los1_K.setColumns(10);
		
		JLabel lblGebenSieDie = new JLabel("Geben Sie das zweite Losungswort ein : ");
		lblGebenSieDie.setBounds(290, 99, 232, 31);
		panel.add(lblGebenSieDie);
		
		los2_K = new JTextField();
		los2_K.setColumns(10);
		los2_K.setBounds(290, 141, 232, 20);
		panel.add(los2_K);
		
		JLabel lblDasVerschlsselteWort = new JLabel("das verschlüsselte Wort");
		lblDasVerschlsselteWort.setBounds(207, 207, 177, 41);
		panel.add(lblDasVerschlsselteWort);
		
		JTextArea erg_K = new JTextArea();
		erg_K.setEditable(false);
		erg_K.setLineWrap(true);
		erg_K.setBounds(10, 259, 512, 70);
		panel.add(erg_K);
		
		JButton button = new JButton("Verschlüsseln");
		button.setBounds(207, 172, 118, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//loescht das Feld 
				erg_K.setText("");
				erg_K.setEditable(true);
				
				//wenn die Texte leer sind
				if(klarText.getText().isEmpty() || los1_K.getText().isEmpty()||los2_K.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Geben Sie bitte in jedes Feld etwas ein");
				}
				else {
					//loescht die Zeilenumbrüche
					String klar=klarText.getText();
					int cnt=0;
					for (int i = 0; i < klar.length(); i++) {
						if(klar.charAt(i)=='\n') {
							cnt++;
						}
					}
					char[] neu=new char[klar.length()-cnt];
					int cntt=0;
					for (int i = 0; i < klar.length(); i++) {
						if(klar.charAt(i)!='\n') {
							neu[cntt]=klar.charAt(i);
							cntt++;
						}
					}
					String s=new String(neu);
					
					
					try {
						Codec cod=new Wuerfel();
						cod.setzeLosung(los1_K.getText());
						if(los1_K.getText().length()>s.length()) {
							JOptionPane.showMessageDialog(null, "Losungwort darf nicht größer als der Klartext sein");
							
							los1_K.setText("");
							los2_K.setText("");
						}
						else {
							String t=cod.kodiere(s);
							cod.setzeLosung(los2_K.getText());
							if(los2_K.getText().length()>t.length()) {
								JOptionPane.showMessageDialog(null,"Losungwort darf nicht größer als der Klartext sein");
								los2_K.setText("");
							}
							else {
								String a=cod.kodiere(t);
								erg_K.setText(a);
							}
						}
						
						
						
						
					} catch (IllegalArgumentException error) {
						JOptionPane.showMessageDialog(null, error.toString());
						los1_K.setText("");
						los2_K.setText("");
					}
					
					
					
				}
				
				
				
				
				
				
				
				
			}
		});
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Wuerfel dekodiere", null, panel_1, null);
		
		JLabel lblGebenSieDen = new JLabel("Geben Sie den Geheimtext ein : ");
		lblGebenSieDen.setBounds(174, 11, 171, 31);
		panel_1.add(lblGebenSieDen);
		
		JTextArea geheimText = new JTextArea();
		geheimText.setLineWrap(true);
		geheimText.setBounds(10, 40, 512, 48);
		panel_1.add(geheimText);
		
		JLabel label_2 = new JLabel("Geben Sie die erste Losungsworte ein : ");
		label_2.setBounds(10, 99, 232, 31);
		panel_1.add(label_2);
		
		los1_G = new JTextField();
		los1_G.setColumns(10);
		los1_G.setBounds(10, 141, 232, 20);
		panel_1.add(los1_G);
		
		JLabel label_3 = new JLabel("Geben Sie die zweite Losungsworte ein : ");
		label_3.setBounds(290, 99, 232, 31);
		panel_1.add(label_3);
		
		los2_G = new JTextField();
		los2_G.setColumns(10);
		los2_G.setBounds(290, 141, 232, 20);
		panel_1.add(los2_G);
		
		JLabel label_4 = new JLabel("das entschlüsselte Wort");
		label_4.setBounds(207, 207, 177, 41);
		panel_1.add(label_4);
		
		JTextArea erg_G = new JTextArea();
		erg_G.setLineWrap(true);
		erg_G.setBounds(10, 259, 512, 70);
		panel_1.add(erg_G);
		
	
		JButton button_1 = new JButton("Entschlüsseln");
		button_1.setBounds(207, 172, 118, 23);
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				erg_K.setText("");
				
				
				if(geheimText.getText().isEmpty() || los1_G.getText().isEmpty()||los2_G.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Geben Sie bitte in jedes Feld etwas ein");
				}else {
					//loschen leer in der Geheimtext, macht nur eine Zeile
					String geh=geheimText.getText();
					
					
					try {
						Codec cod=new Wuerfel();
						cod.setzeLosung(los1_G.getText());
						if(los1_G.getText().length()>geh.length()) {
							JOptionPane.showMessageDialog(null, "Losungwort darf nicht größer als der Text sein");
						}
						else {
							String t=cod.dekodiere(geh);
							cod.setzeLosung(los2_G.getText());
							if(los2_G.getText().length()>t.length()) {
								JOptionPane.showMessageDialog(null,"Losungwort darf nicht größer als der Text sein");
							}
							else {
								String a=cod.dekodiere(t);
								erg_G.setText(a);
							}
						}
						
						
						
						
					} catch (IllegalArgumentException error) {
						JOptionPane.showMessageDialog(null, error.toString());
						los1_G.setText("");
						los2_G.setText("");
					}
					
				
				}
				
				
					
				
				
			}
		});
		panel_1.add(button_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Caesar kodieren", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Geben Sie den Klartext ein : ");
		label.setBounds(155, 11, 171, 31);
		panel_2.add(label);
		
		JTextArea klartext_C = new JTextArea();
		klartext_C.setLineWrap(true);
		klartext_C.setBounds(10, 37, 512, 48);
		panel_2.add(klartext_C);
		
		JLabel lblGebenSieDas = new JLabel("Geben Sie das Losungswort ein : ");
		lblGebenSieDas.setBounds(155, 80, 232, 31);
		panel_2.add(lblGebenSieDas);
		
		loskod_C = new JTextField();
		loskod_C.setColumns(10);
		loskod_C.setBounds(10, 112, 512, 20);
		panel_2.add(loskod_C);
		
		JLabel lblDasVerschlsselteWort_1 = new JLabel("das verschlüsselte Wort");
		lblDasVerschlsselteWort_1.setBounds(155, 157, 177, 41);
		panel_2.add(lblDasVerschlsselteWort_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setBounds(10, 192, 512, 77);
		panel_2.add(textArea_1);
		
		
		JButton button_2 = new JButton("Verschlüsseln");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//macht die Ergebnisse zuerst leer  
				textArea_1.setText("");
				
				Codec cod=new Caesar();
				cod.setzeLosung(loskod_C.getText());
				String erg_C=cod.kodiere(klartext_C.getText());
				textArea_1.setText(erg_C);
				
			}
		});
		button_2.setBounds(185, 136, 118, 23);
		panel_2.add(button_2);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Caesar dekodiere", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblGebenSieDen_1 = new JLabel("Geben Sie den Geheimtext ein : ");
		lblGebenSieDen_1.setBounds(170, 11, 171, 31);
		panel_3.add(lblGebenSieDen_1);
		
		JTextArea geheimtext_C = new JTextArea();
		geheimtext_C.setLineWrap(true);
		geheimtext_C.setBounds(10, 41, 512, 48);
		panel_3.add(geheimtext_C);
		
		JLabel label_5 = new JLabel("Geben Sie das Losungswort ein : ");
		label_5.setBounds(170, 100, 232, 31);
		panel_3.add(label_5);
		
		losdekod_C = new JTextField();
		losdekod_C.setColumns(10);
		losdekod_C.setBounds(10, 142, 512, 20);
		panel_3.add(losdekod_C);
		
		JLabel label_6 = new JLabel("das entschlüsselte Wort");
		label_6.setBounds(170, 204, 177, 41);
		panel_3.add(label_6);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setLineWrap(true);
		textArea_2.setBounds(10, 241, 512, 77);
		panel_3.add(textArea_2);
		
		JButton btnEntschlsseln = new JButton("Entschlüsseln");
		btnEntschlsseln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//macht die Ergebnisse zuerst leer  
				textArea_2.setText("");
				
				
				Codec cod=new Caesar();
				cod.setzeLosung(losdekod_C.getText());
				String erg_C=cod.dekodiere(geheimtext_C.getText());
				textArea_2.setText(erg_C);
				
			}
		});
		btnEntschlsseln.setBounds(187, 173, 118, 23);
		panel_3.add(btnEntschlsseln);
		
		
		
		
	}
}
