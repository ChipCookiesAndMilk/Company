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

public class ConsultaEmpleadoProyectoGUI extends JFrame implements ActionListener {
    private JTextField tfNProy;
    private JButton bConsultar;
    private JPanel panel1, panel2;
    private JTextArea taDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public ConsultaEmpleadoProyectoGUI() {
        super("Asignacion de Departamento a Proyectos");
        // 1. Crear los objetos de los atributos
        panel1 = new JPanel();
        panel2 = new JPanel();

        taDatos = new JTextArea(10, 35);

        tfNProy = new JTextField();
        bConsultar = new JButton("Consultar");
        // bSalir = new JButton("Exit");

        // Adicionar addActionListener a lo JButtons
        bConsultar.addActionListener(this);
        // bSalir.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(4, 2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        // panel2.add(new JLabel("Asignacion de Proyectos a Empleados"));
        panel1.add(new JLabel("NO. PROYECTO: "));
        panel1.add(tfNProy);
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
        String datos = "";
        if (e.getSource() == bConsultar) {
            datos = tfNProy.getText();

            if(datos.isEmpty()){
                datos = "Ingrese el numero del Proyecto. Verifique para continuar";
            }
            else{
                datos = companyad.validarProyecto(datos);

                if(datos.equals("FOUND")){
                    //System.out.println("Proyecto Enocntrado");
                    datos = companyad.consultarEmpleadosProyecto(tfNProy.getText());
                    if(datos.isEmpty()){
                        datos = "En el proyecto "+tfNProy.getText()+" hay empleados trabajando en el";
                    }
                    else{
                        datos = "En el proyecto "+tfNProy.getText()+" trabajan los siguientes empleados\n\nnProyecto_nss_nombre_nDepto_horas\n"+datos;
                    }
                }
                else{
                    datos = "No se encontro el Proyecto "+tfNProy.getText();
                }
            }
            taDatos.setText(datos);
        }
    }

    public static void main(String args[]) {
        new ConsultaEmpleadoProyectoGUI();
    }
}