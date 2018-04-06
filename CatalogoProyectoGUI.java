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

public class CatalogoProyectoGUI extends JFrame implements ActionListener {
    private JTextField textfieldfNProyecto, textfieldfNomProyecto, textFieldLocal, textFieldNDepto;
    private JButton botonCaptura, botonConsulta, botonConsultarProyecto;
    private JPanel panelContenido, panelPrincipa;
    private JTextArea textAreaDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public CatalogoProyectoGUI() {
        super("Catalogo de Empleados");
        // 1. Crear los objetos de los atributos
        panelContenido = new JPanel();
        panelPrincipa = new JPanel();

        textAreaDatos = new JTextArea(10, 30);

        textfieldfNProyecto = new JTextField();
        textfieldfNomProyecto = new JTextField();
        textFieldLocal = new JTextField();
        textFieldNDepto = new JTextField();
        botonCaptura = new JButton("Capturar datos");
        botonConsulta = new JButton("Consulta General");
        botonConsultarProyecto = new JButton("Consultar Proyecto");

        // Adicionar addActionListener a lo JButtons
        botonCaptura.addActionListener(this);
        botonConsulta.addActionListener(this);
        botonConsultarProyecto.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panelContenido.setLayout(new GridLayout(8, 2));
        panelPrincipa.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panelPrincipa.add(new JLabel("Catalogo de Proyectos"));
        panelContenido.add(new JLabel("Numero de proyecto: "));
        panelContenido.add(textfieldfNProyecto);
        panelContenido.add(new JLabel("Nombre de Proyecto: "));
        panelContenido.add(textfieldfNomProyecto);
        panelContenido.add(new JLabel("Local: "));
        panelContenido.add(textFieldLocal);
        panelContenido.add(new JLabel("Numero Departamento: "));
        panelContenido.add(textFieldNDepto);
        panelContenido.add(botonCaptura);
        panelContenido.add(botonConsulta);
        panelContenido.add(botonConsultarProyecto);

        panelPrincipa.add(panelContenido);
        panelPrincipa.add(new JScrollPane(textAreaDatos));

        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panelPrincipa);
        setSize(600, 600);
        // setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
        if (e.getSource() == botonCaptura) {
            datos = obtenerDatos();
            if (datos.equals("vacio")) {
                datos = "Algun campo esta vacio. Verique para continuar";
            } else {
                datos = companyad.capturarProyecto(datos);
            }
            textAreaDatos.setText(datos);
        }

        if (e.getSource() == botonConsulta) {
            datos = companyad.consultarProyectos();
            if (datos.isEmpty()) {
                datos = "No se encontraron Proyecto registrados";
            }
            textAreaDatos.setText(datos);
        }
        if (e.getSource() == botonConsultarProyecto) {
            datos = textfieldfNProyecto.getText();
            if (datos.isEmpty()) {
                datos = "Ingrese el numero de proyecto. Verifique para continuar";
            } else {
                datos = companyad.validarProyecto(datos);
                if (datos.equals("FOUND")) {
                    datos = companyad.consultarProyecto(textfieldfNProyecto.getText());
                } else {
                    datos = "No se encontro el Proyecto " + textfieldfNProyecto.getText();
                }
            }
            textAreaDatos.setText(datos);
        }
    }

    public String obtenerDatos() {
        String datos = "";

        String proyecto = textfieldfNProyecto.getText();
        String nombre = textfieldfNomProyecto.getText();
        String local = textFieldLocal.getText();
        String departamento = textFieldNDepto.getText();

        if (proyecto.isEmpty() || nombre.isEmpty() || local.isEmpty() || departamento.isEmpty())
            datos = "vacio";
        else {
            datos = proyecto + "_" + nombre + "_" + local + "_" + departamento;
        }
        return datos;
    }

    public JPanel getPanel2() {
        return this.panelPrincipa;
    }

    public static void main(String args[]) {
        new CatalogoProyectoGUI();
    }
}