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

public class CatalogoDepartamentoGUI extends JFrame implements ActionListener {
    private JTextField textFieldNDep, textFieldNombreDep, textFieldNSSUP;
    private JButton bCaptura, bConsulta, bConsultaDep;
    private JPanel panelContenido, panelPrincipal;
    private JTextArea textAreaDatos;

    private CompanyADjdbc cad = new CompanyADjdbc();

    public CatalogoDepartamentoGUI() {
        super("Catalogo de Departamentos");
        // 1. Crear los objetos de los atributos
        panelContenido = new JPanel();
        panelPrincipal = new JPanel();

        textAreaDatos = new JTextArea(10, 30);

        textFieldNDep = new JTextField();
        tfNomDepto = new JTextField();
        tfNSSAdmin = new JTextField();
        bCaptura = new JButton("Capturar Departamento");
        bConsulta = new JButton("Consulta General");
        bConsultaDep = new JButton("Consulta Departamento");
        // bSalir = new JButton("Exit");

        // Adicionar addActionListener a lo JButtons
        bCaptura.addActionListener(this);
        bConsulta.addActionListener(this);
        bConsultaDep.addActionListener(this);
        // bSalir.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panelContenido.setLayout(new GridLayout(7, 2));
        panelPrincipal.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panelPrincipal.add(new JLabel("Catalogo de Departamentos"));
        panelContenido.add(new JLabel("NO. DEPARTAMENTO: "));
        panelContenido.add(textFieldNDep);
        panelContenido.add(new JLabel("NOMBRE DEPARTAMENTO: "));
        panelContenido.add(tfNomDepto);
        panelContenido.add(new JLabel("NO. SS. ADMINISTRADOR: "));
        panelContenido.add(tfNSSAdmin);
        panelContenido.add(bCaptura);
        panelContenido.add(bConsulta);
        panelContenido.add(bConsultaDep);
        // panel1.add(bSalir);

        panelPrincipal.add(panelContenido);
        panelPrincipal.add(new JScrollPane(textAreaDatos));
        textAreaDatos.setEditable(false);

        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panelPrincipal);
        setSize(600, 600);
        // setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
        if (e.getSource() == bCaptura) {

            datos = obtenerDato();
            if (datos.equals("vacio")) {
                //System.out.println(datos);
                datos = "Algun campo esta vacio. Verique para continuar";
            } else {
                datos = cad.capturarDepartamento(datos);
            }
            textAreaDatos.setText(datos);
        }

        if (e.getSource() == bConsulta) {
            textAreaDatos.setText(cad.consultarDepartamentos());
        }

        if (e.getSource() == bConsultaDep) {
            datos = textFieldNDep.getText();
            if (datos.isEmpty()) {
                datos = "Ingrese un numero departamento para buscar sus empleados.\n\tVerifique para continuar";
            } else {
                datos = cad.validarDepartamento(datos);

                if (datos.equals("FOUND")) {
                    datos = cad.consultarDepto(textFieldNDep.getText());
                } else {
                    System.out.println(datos);
                    datos = "Error: No se encontro el Departamento que ingreso " + textFieldNDep.getText();
                }
            }
            textAreaDatos.setText(datos);
        }
    }

    public JPanel getPanelPrincipal() {
        return this.panelPrincipal;
    }

    public String obtenerDato() {
        String datos = "";

        String ndepto = textFieldNDep.getText();
        String nombre = tfNomDepto.getText();
        String nssAdmin = tfNSSAdmin.getText();

        if (ndepto.isEmpty() || nombre.isEmpty() || nssAdmin.isEmpty())
            datos = "vacio";
        else {
            datos = ndepto + "_" + nombre + "_" + nssAdmin;
            System.out.println("\nGUI: " + datos);
        }
        return datos;
    }

    public static void main(String args[]) {
        new CatalogoDepartamentoGUI();
    }
}