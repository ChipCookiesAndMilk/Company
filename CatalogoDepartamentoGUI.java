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
    private JTextField tfNDepto, tfNomDepto, tfNSSAdmin;
    private JButton bCapturar, bConsultar, bConsultarDepartamento;
    private JPanel panel1, panel2;
    private JTextArea taDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public CatalogoDepartamentoGUI() {
        super("Catalogo de Departamentos");
        // 1. Crear los objetos de los atributos
        panel1 = new JPanel();
        panel2 = new JPanel();

        taDatos = new JTextArea(10, 30);

        tfNDepto = new JTextField();
        tfNomDepto = new JTextField();
        tfNSSAdmin = new JTextField();
        bCapturar = new JButton("Capturar Departamento");
        bConsultar = new JButton("Consulta General");
        bConsultarDepartamento = new JButton("Consulta Departamento");
        // bSalir = new JButton("Exit");

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarDepartamento.addActionListener(this);
        // bSalir.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(7, 2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel2.add(new JLabel("Catalogo de Departamentos"));
        panel1.add(new JLabel("NO. DEPARTAMENTO: "));
        panel1.add(tfNDepto);
        panel1.add(new JLabel("NOMBRE DEPARTAMENTO: "));
        panel1.add(tfNomDepto);
        panel1.add(new JLabel("NO. SS. ADMINISTRADOR: "));
        panel1.add(tfNSSAdmin);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarDepartamento);
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
        String datos ="";

        String ndepto       = tfNDepto.getText();
        String nombre       = tfNomDepto.getText();
        String nssAdmin     = tfNSSAdmin.getText();

        if(ndepto.isEmpty() || nombre.isEmpty() || nssAdmin.isEmpty())
            datos = "vacio";
        else {
            datos = ndepto+"_"+nombre+"_"+nssAdmin;
            System.out.println("\nGUI: "+datos);
        } 
        return datos;
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
        if (e.getSource() == bCapturar) {
            
            datos = obtenerDatos();
            if(datos.equals("vacio")){
                //System.out.println(datos);
                datos = "Algun campo esta vacio. Verique para continuar";
            }
            else{
                datos = companyad.capturarDepartamento(datos);
            }
            taDatos.setText(datos);
        }

        if (e.getSource() == bConsultar) {
            taDatos.setText(companyad.consultarDepartamentos());
        }

        if (e.getSource() == bConsultarDepartamento) {
            datos = tfNDepto.getText();
            if(datos.isEmpty()){
                datos = "Ingrese un numero departamento para buscar sus empleados.\n\tVerifique para continuar";
            }
            else{
                datos = companyad.validarDepartamento(datos);

                if(datos.equals("FOUND")){
                    datos = companyad.consultarDepto(tfNDepto.getText());
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
        new CatalogoDepartamentoGUI();
    }
}