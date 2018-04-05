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
	private JTextField tfNombre, tfFechaNacimiento, tfSexo, tfParentesco, tfNss;
	private JButton bCapturar, bConsultar;
	private JPanel panel1, panel2;
	private JTextArea taDatos;

	private CompanyADjdbc companyad = new CompanyADjdbc();

	public AsignacionDependienteEmpleadoGUI() {
		super("Dependientes de Empleados");
		// 1. Crear los objetos de los atributos
		panel1 = new JPanel();
		panel2 = new JPanel();

		taDatos = new JTextArea(10, 30);

		tfNombre            = new JTextField();
		tfFechaNacimiento   = new JTextField();
		tfSexo              = new JTextField();
		tfParentesco        = new JTextField();
		tfNss               = new JTextField();
		bCapturar           = new JButton("Capturar datos");
		bConsultar          = new JButton("Consultar");
		// bSalir = new JButton("Exit");

		// Adicionar addActionListener a lo JButtons
		bCapturar.addActionListener(this);
		bConsultar.addActionListener(this);
		// bSalir.addActionListener(this);

		// 2. Definir los Layouts de los JPanels
		panel1.setLayout(new GridLayout(9, 2));
		panel2.setLayout(new FlowLayout());

		// 3. Colocar los objetos de los atributos en los JPanels correspondientes
		panel2.add(new JLabel("Asignacion de Dependiente a Empleado"));
		panel1.add(new JLabel("NOMBRE DEPENDIENTE: "));
		panel1.add(tfNombre);
		panel1.add(new JLabel("FECHA NACIMIENTO: "));
		panel1.add(tfFechaNacimiento);
		panel1.add(new JLabel("SEXO: "));
		panel1.add(tfSexo);
		panel1.add(new JLabel("PARENTESCO: "));
		panel1.add(tfParentesco);
		panel1.add(new JLabel("NO. SS EMPLEADO: "));
		panel1.add(tfNss);
		panel1.add(bCapturar);
		panel1.add(bConsultar);
		// panel1.add(bSalir);

		panel2.add(panel1);
		panel2.add(new JScrollPane(taDatos));
		taDatos.setEditable(false);

		// 4. Adicionar el panel2 al JFrame y hacerlo visible
		add(panel2);
		setSize(600, 600);
		// setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public JPanel getPanel2() {
		return this.panel2;
	}

	public String obtenerDatos(){
		String datos = "";

		String nombre       = tfNombre.getText();
		String fecha        = tfFechaNacimiento.getText();
		String sexo         = tfSexo.getText();
		String parentesco   = tfParentesco.getText();
		String nss          = tfNss.getText();

		if(nombre.isEmpty() || fecha.isEmpty() || sexo.isEmpty() || parentesco.isEmpty() || nss.isEmpty())
			datos = "VACIO";
		else {
			datos = nombre+"_"+fecha+"_"+sexo+"_"+parentesco+"_"+nss;
			System.out.println("\nGUI: "+datos);
		} 
		System.out.println("\nGUI: "+datos);
		return datos;
	}

	public void actionPerformed(ActionEvent e) {
		String datos = "";
		if (e.getSource() == bCapturar) {
			// Valid fields
			//System.out.println("Capturar");
			datos = obtenerDatos();
			if(datos.equals("VACIO")){
				datos = "Algun campo esta vacio. Verique para continuar";
			}
			else{
				// All the data is saved in data
				// this else does some validation and the actual insert
				String valid = companyad.validarEmpleado(tfNss.getText());

				if(valid.equals("FOUND")){ 
					System.out.println("Empleado encontrado");
					datos = companyad.capturarDependienteEmpleado(datos);
					System.out.println(datos);
				}
				else{
					datos = "No se encontro al EMPLEADO ingresado "+tfNss.getText()+". Verifique para continuar";
				}
				taDatos.setText(datos);
			}
			//System.out.println("Capturar");
			taDatos.setText(datos);
		}

		if (e.getSource() == bConsultar) {
			datos = companyad.consultarDependientes();
			if(datos.isEmpty()){
				datos = "No se encontraron dependientes";
			}
			taDatos.setText(datos); 
			//System.out.println("Consultar");
		}

	}

	public static void main(String args[]) {
		new AsignacionDependienteEmpleadoGUI();
	}
}