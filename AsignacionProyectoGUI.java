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
    private JTextField tfNoSSEmpleado, tfHoras, tfNDepto, tfNoProyecto;
    private JButton bCapturar, bConsultar;
    private JPanel panel1, panel2;
    private JTextArea taDatos;

    // private CompanyADjdbc companyad = new CompanyADjdbc();

    public AsignacionProyectoGUI() {
        super("Asignacion de Proyectos");
        // 1. Crear los objetos de los atributos
        panel1 = new JPanel();
        panel2 = new JPanel();

        taDatos = new JTextArea(11, 30);

        tfNoSSEmpleado = new JTextField();
        tfHoras = new JTextField();
        tfNDepto = new JTextField();
        tfNoProyecto = new JTextField();
        bCapturar = new JButton("Capturar datos");
        bConsultar = new JButton("Consultar");
        // bSalir = new JButton("Exit");

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        // bSalir.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(9, 2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel2.add(new JLabel("Asignacion de Empleados a Proyecto"));
        panel1.add(new JLabel("NO. SS EMPLEADO: "));
        panel1.add(tfNoSSEmpleado);
        panel1.add(new JLabel("HORAS: "));
        panel1.add(tfHoras);
        panel1.add(new JLabel("NO. DEPARTAMENTO: "));
        panel1.add(tfNDepto);
        panel1.add(new JLabel("NO. PROYECTO: "));
        panel1.add(tfNoProyecto);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        // panel1.add(bSalir);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));

        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(600, 600);
        // setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel getPanel2() {
        return this.panel2;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bCapturar) {
            System.out.println("Capturar");
            taDatos.setText("Capturar");
        }

        if (e.getSource() == bConsultar) {
            System.out.println("Consultar");
            taDatos.setText("Consultar");
        }
    }

    public static void main(String args[]) {
        new AsignacionProyectoGUI();
    }
}