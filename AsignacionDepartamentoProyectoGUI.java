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

public class AsignacionDepartamentoProyectoGUI extends JFrame implements ActionListener {
    private JTextField textFieldNdepto, textFieldNProyecto;
    private JButton botonCaptura, botonConsulta;
    private JPanel panelContenido, panelPrincipal;
    private JTextArea textAreaDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public AsignacionDepartamentoProyectoGUI() {
        super("Asignacion de Departamento a Proyectos");
        // 1. Crear los objetos de los atributos
        panelContenido = new JPanel();
        panelPrincipal = new JPanel();

        textAreaDatos = new JTextArea(10, 30);

        textFieldNdepto = new JTextField();
        textFieldNProyecto = new JTextField();
        botonCaptura = new JButton("Capturar datos");
        botonConsulta = new JButton("Consultar");
        // bSalir = new JButton("Exit");

        // Adicionar addActionListener a lo JButtons
        botonCaptura.addActionListener(this);
        botonConsulta.addActionListener(this);
        // bSalir.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panelContenido.setLayout(new GridLayout(6, 2));
        panelPrincipal.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panelPrincipal.add(new JLabel("Asignacion de Departamento a Proyectos"));
        panelContenido.add(new JLabel("NO. DEPARTAMENTO: "));
        panelContenido.add(textFieldNdepto);
        panelContenido.add(new JLabel("NO. PROYECTO: "));
        panelContenido.add(textFieldNProyecto);
        panelContenido.add(botonCaptura);
        panelContenido.add(botonConsulta);
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
            datos = obtenerDato();
            if (datos.equals("VACIO")) {
                System.out.println("Capturar_VACIO");
                datos = "Algun campo esta vacio. Verique para continuar";
            } else {
                // All the data is saved in data
                // this else does some validation and the actual insert
                System.out.println("Capturar_IN");
                String valid = companyad.validarDepartamento(textFieldNdepto.getText());

                if (valid.equals("FOUND")) {
                    valid = companyad.validarProyecto(textFieldNProyecto.getText());

                    if (valid.equals("FOUND")) {
                        datos = companyad.capturarProyectoDepartamento(datos);
                    } else {
                        datos = "No se encontro el PROYECTO ingresado " + textFieldNProyecto.getText()
                                + ". Verifique para continuar";
                    }
                } else {
                    datos = "No se encontro el DEPARTAMENTO ingresado " + textFieldNdepto.getText()
                            + ". Verifique para continuar";
                }
            }
            System.out.println("Capturar_OUT");
            textAreaDatos.setText(datos);
        }

        if (e.getSource() == botonConsulta) {
            System.out.println("Consultar");
        }
    }

    public String obtenerDato() {
        String datos = "";

        String depto = textFieldNdepto.getText();
        String proyecto = textFieldNProyecto.getText();

        if (depto.isEmpty() || proyecto.isEmpty())
            return "VACIO";
        else {
            datos = depto + "_" + proyecto;
        }
        return datos;
    }

    public JPanel getPanelPrincipal() {
        return this.panelPrincipal;
    }

    public static void main(String args[]) {
        new AsignacionDepartamentoProyectoGUI();
    }
}