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

public class AsignacionEmpleadoProyectoGUI extends JFrame implements ActionListener {
    private JTextField textFieldNSEmpleado, textFieldNProyecto, textFieldHoras;
    private JButton botonCaptura, bConsulta;
    private JPanel panelContenido, panelPrincipal;
    private JTextArea textAreaDatos;

    private CompanyADjdbc cad = new CompanyADjdbc();

    public AsignacionEmpleadoProyectoGUI() {
        super("Asignacion de Proyectos");
        // 1. Crear los objetos de los atributos
        panelContenido = new JPanel();
        panelPrincipal = new JPanel();

        textAreaDatos = new JTextArea(10, 30);

        textFieldNSEmpleado = new JTextField();
        textFieldNProyecto = new JTextField();
        textFieldHoras = new JTextField();
        botonCaptura = new JButton("Capturar datos");
        bConsulta = new JButton("Consulta General");
        // bSalir = new JButton("Exit");

        // Adicionar addActionListener a lo JButtons
        botonCaptura.addActionListener(this);
        bConsulta.addActionListener(this);
        // bSalir.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panelContenido.setLayout(new GridLayout(8, 2));
        panelPrincipal.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panelPrincipal.add(new JLabel("Asignacion de Empleados a Proyecto"));
        panelContenido.add(new JLabel("NO. SS EMPLEADO: "));
        panelContenido.add(textFieldNSEmpleado);
        panelContenido.add(new JLabel("NO. PROYECTO: "));
        panelContenido.add(textFieldNProyecto);
        panelContenido.add(new JLabel("HORAS: "));
        panelContenido.add(textFieldHoras);
        panelContenido.add(botonCaptura);
        panelContenido.add(bConsulta);
        // panel1.add(bSalir);

        panelPrincipal.add(panelContenido);
        panelPrincipal.add(new JScrollPane(textAreaDatos));

        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panelPrincipal);
        setSize(600, 600);
        // setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
        if (e.getSource() == botonCaptura) {
            //System.out.println("Capturar");
            datos = obtenerDato();
            if (datos.equals("VACIO")) {
                datos = "Algun campo esta vacio. Verique para continuar";
            } else if (datos.equals("NO_NUMERICO")) {
                datos = "El salario debe ser numerico. Verique para continuar";
            } else {
                // All the data is saved in data
                // this else does some validation and the actual insert
                String valid = cad.validarEmpleado(textFieldNSEmpleado.getText());

                if (valid.equals("FOUND")) {
                    valid = cad.validarProyecto(textFieldNProyecto.getText());

                    if (valid.equals("FOUND")) {
                        datos = cad.capturarEmpleadoProyecto(datos);
                    } else {
                        datos = "No se encontro el PROYECTO ingresado " + textFieldNProyecto.getText()
                                + ". Verifique para continuar";
                    }
                } else {
                    datos = "No se encontro al EMPLEADO ingresado " + textFieldNSEmpleado.getText()
                            + ". Verifique para continuar";
                }
                textAreaDatos.setText(datos);
            }
            //System.out.println("Capturar");
            textAreaDatos.setText(datos);
        }

        if (e.getSource() == bConsulta) {
            datos = cad.consultaAsignacionEmpleadosProyecto();
            if (datos.isEmpty()) {
                datos = "No hay empleados asignados al proyecto";
            } else {
                datos = "Empleados asignados al proyecto: \n" + datos;
            }
            textAreaDatos.setText(datos);
        }
    }

    public String obtenerDato() {
        String datos = "";

        String empleado = textFieldNSEmpleado.getText();
        String proyecto = textFieldNProyecto.getText();
        String hrs = textFieldHoras.getText();

        if (empleado.equals("") || proyecto.equals("") || hrs.isEmpty())
            datos = "VACIO";
        else {
            try {//por si no hay un valor numerico
                int horas = Integer.parseInt(hrs);
                datos = horas + "_" + empleado + "_" + proyecto;
            } catch (NumberFormatException nfe) {
                datos = "NO_NUMERICO";
            }
        }
        return datos;
    }

    public JPanel getPanelPrincipal() {
        return this.panelPrincipal;
    }

    public static void main(String args[]) {
        new AsignacionEmpleadoProyectoGUI();
    }
}