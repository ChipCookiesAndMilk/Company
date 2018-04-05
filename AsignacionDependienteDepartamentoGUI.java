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

public class AsignacionDependienteDepartamentoGUI extends JFrame implements ActionListener {
    private JTextField tfNDepto, tfNSSAdmin;
    private JButton bCapturar, bConsultarAdministra, bConsultarDepartamento;
    private JPanel panel1, panel2;
    private JTextArea taDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public AsignacionDependienteDepartamentoGUI() {
        super("Asignacion de Dependientes de Departamento");
        // 1. Crear los objetos de los atributos
        panel1 = new JPanel();
        panel2 = new JPanel();

        taDatos = new JTextArea(10, 30);

        tfNDepto = new JTextField();
        tfNSSAdmin = new JTextField();
        bCapturar = new JButton("Capturar datos");
        bConsultarAdministra = new JButton("Consultar Deptos del Empleado");
        bConsultarDepartamento= new JButton("Consultar Empleados de Depto");

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultarAdministra.addActionListener(this);
        bConsultarDepartamento.addActionListener(this);
        // bSalir.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(5, 2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel2.add(new JLabel("Asignacion de Proyectos a Empleados"));
        panel1.add(new JLabel("NO. DEPARTAMENTO: "));
        panel1.add(tfNDepto);
        panel1.add(new JLabel("NO. SS. ADMINISTRADOR: "));
        panel1.add(tfNSSAdmin);
        panel1.add(bCapturar);
        panel1.add(bConsultarAdministra);
        panel1.add(bConsultarDepartamento);

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
        if (e.getSource() == bCapturar) {
            System.out.println("Capturar");
        }

        if (e.getSource() == bConsultarAdministra) {
            datos = tfNSSAdmin.getText();
            if(datos.isEmpty()){
                datos = "Ingrese un nss del Empleado para buscar su(s) departamento(s).\n\tVerifique para continuar";
            }
            else{
                datos = companyad.validarEmpleado(datos);

                if(datos.equals("FOUND")){
                    datos = companyad.consultarAdminDepto(tfNSSAdmin.getText());
                    if(datos.isEmpty()){
                        datos = "El empleado "+tfNSSAdmin.getText()+ " NO esta administrando departamentos";
                    }
                    else{
                        datos = "El empleado "+tfNSSAdmin.getText()+ " administra: \n"+datos;
                    }
                }
                else{
                    System.out.println(datos);
                    datos = "Error: No se encontro el Empleado que ingreso "+tfNSSAdmin.getText();
                }
            }
            taDatos.setText(datos);
        }
        if (e.getSource() == bConsultarDepartamento) {
            datos = tfNDepto.getText();
            if(datos.isEmpty()){
                datos = "Ingrese un numero departamento para buscar su administrador.\n\tVerifique para continuar";
            }
            else{
                datos = companyad.validarDepartamento(datos);

                if(datos.equals("FOUND")){
                    datos = "Administrador de "+tfNDepto.getText()+ " es: "+companyad.consultarDeptoAdmin(tfNDepto.getText());
                }
                else{
                    System.out.println(datos);
                    datos = "Error: No se encontro el Departamento que ingreso "+tfNDepto.getText();
                }
            }
            taDatos.setText(datos);
        }
    }

    public static void main(String args[]) {
        new AsignacionDependienteDepartamentoGUI();
    }
}