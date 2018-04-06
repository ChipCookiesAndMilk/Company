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

public class AsignacionProyectoGUI extends JFrame implements ActionListener {
    private JTextField textFieldNSEmpleado, textFieldHoras, textFieldNDep, textFieldNProyecto;
    private JButton botonCaptura, botonConsulta;
    private JPanel panelContenido, panelPrincipal;
    private JTextArea textAreaDatos;

    public AsignacionProyectoGUI() {
        super("Asignacion de Proyectos");
        panelContenido = new JPanel();
        panelPrincipal = new JPanel();

        textAreaDatos = new JTextArea(11, 30);

        textFieldNSEmpleado = new JTextField();
        textFieldHoras = new JTextField();
        textFieldNDep = new JTextField();
        textFieldNProyecto = new JTextField();
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
        panelPrincipal.add(new JLabel("Asignacion de Empleados a Proyecto"));
        panelContenido.add(new JLabel("NO. SS EMPLEADO: "));
        panelContenido.add(textFieldNSEmpleado);
        panelContenido.add(new JLabel("HORAS: "));
        panelContenido.add(textFieldHoras);
        panelContenido.add(new JLabel("NO. DEPARTAMENTO: "));
        panelContenido.add(textFieldNDep);
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
        if (e.getSource() == botonCaptura) {
            textAreaDatos.setText("Capturar");
        }

        if (e.getSource() == botonConsulta) {
            textAreaDatos.setText("Consultar");
        }
    }

    public JPanel getPanelPrincipal() {
        return this.panelPrincipal;
    }

    public static void main(String args[]) {
        new AsignacionProyectoGUI();
    }
}