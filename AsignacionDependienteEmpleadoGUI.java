import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AsignacionDependienteEmpleadoGUI extends JFrame implements ActionListener {
	private JTextField textFieldNombre, textFieldFecha, textFieldSexo, textFieldParentesco, textFieldNSS;
	private JButton botonCaptura, botonConsulta;
	private JPanel panelContenido, panelPrincipal;
	private JTextArea taDatos;

	private CompanyADjdbc companyad = new CompanyADjdbc();

	public AsignacionDependienteEmpleadoGUI() {
		super("Dependientes de Empleados");
		// 1. Crear los objetos de los atributos
		panelContenido = new JPanel();
		panelPrincipal = new JPanel();

		taDatos = new JTextArea(10, 30);

		textFieldNombre = new JTextField();
		textFieldFecha = new JTextField();
		textFieldSexo = new JTextField();
		textFieldParentesco = new JTextField();
		textFieldNSS = new JTextField();
		botonCaptura = new JButton("Capturar datos");
		botonConsulta = new JButton("Consultar");
		// bSalir = new JButton("Exit");

		// Adicionar addActionListener a lo JButtons
		botonCaptura.addActionListener(this);
		botonConsulta.addActionListener(this);
		// bSalir.addActionListener(this);

		// 2. Definir los Layouts de los JPanels
		panelContenido.setLayout(new GridLayout(9, 2));
		panelPrincipal.setLayout(new FlowLayout());

		// 3. Colocar los objetos de los atributos en los JPanels correspondientes
		panelPrincipal.add(new JLabel("Asignacion de Dependiente a Empleado"));
		panelContenido.add(new JLabel("NOMBRE DEPENDIENTE: "));
		panelContenido.add(textFieldNombre);
		panelContenido.add(new JLabel("FECHA NACIMIENTO: "));
		panelContenido.add(textFieldFecha);
		panelContenido.add(new JLabel("SEXO: "));
		panelContenido.add(textFieldSexo);
		panelContenido.add(new JLabel("PARENTESCO: "));
		panelContenido.add(textFieldParentesco);
		panelContenido.add(new JLabel("NO. SS EMPLEADO: "));
		panelContenido.add(textFieldNSS);
		panelContenido.add(botonCaptura);
		panelContenido.add(botonConsulta);
		// panel1.add(bSalir);

		panelPrincipal.add(panelContenido);
		panelPrincipal.add(new JScrollPane(taDatos));
		taDatos.setEditable(false);

		// 4. Adicionar el panel2 al JFrame y hacerlo visible
		add(panelPrincipal);
		setSize(600, 600);
		// setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		String datos = "";
		if (e.getSource() == botonCaptura) {
			// Valid fields
			//System.out.println("Capturar");
			datos = obtenerDatos();
			if (datos.equals("VACIO")) {
				datos = "Algun campo esta vacio. Verique para continuar";
			} else {
				// All the data is saved in data
				// this else does some validation and the actual insert
				String valid = companyad.validarEmpleado(textFieldNSS.getText());

				if (valid.equals("FOUND")) {
					System.out.println("Empleado encontrado");
					datos = companyad.capturarDependienteEmpleado(datos);
					System.out.println(datos);
				} else {
					datos = "No se encontro al EMPLEADO ingresado " + textFieldNSS.getText()
							+ ". Verifique para continuar";
				}
				taDatos.setText(datos);
			}
			//System.out.println("Capturar");
			taDatos.setText(datos);
		}

		if (e.getSource() == botonConsulta) {
			datos = companyad.consultarDependientes();
			if (datos.isEmpty()) {
				datos = "No se encontraron dependientes";
			}
			taDatos.setText(datos);
			//System.out.println("Consultar");
		}

	}

	public String obtenerDatos() {
		String datos = "";

		String nombre = textFieldNombre.getText();
		String fecha = textFieldFecha.getText();
		String sexo = textFieldSexo.getText();
		String parentesco = textFieldParentesco.getText();
		String nss = textFieldNSS.getText();

		if (nombre.isEmpty() || fecha.isEmpty() || sexo.isEmpty() || parentesco.isEmpty() || nss.isEmpty())
			datos = "VACIO";
		else {
			datos = nombre + "_" + fecha + "_" + sexo + "_" + parentesco + "_" + nss;
			System.out.println("\nGUI: " + datos);
		}
		System.out.println("\nGUI: " + datos);
		return datos;
	}

	public JPanel getPanelPrincipal() {
		return this.panelPrincipal;
	}

	public static void main(String args[]) {
		new AsignacionDependienteEmpleadoGUI();
	}
}