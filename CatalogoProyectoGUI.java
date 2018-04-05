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
    private JTextField tfNProyecto, tfNomProyecto, tfLocalidad, tfNDpto;
    private JButton bCapturar, bConsultar, bConsultarProyecto;
    private JPanel panel1, panel2;
    private JTextArea taDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public CatalogoProyectoGUI() {
        super("Catalogo de Empleados");
        // 1. Crear los objetos de los atributos
        panel1 = new JPanel();
        panel2 = new JPanel();

        taDatos = new JTextArea(10, 30);

        tfNProyecto = new JTextField();
        tfNomProyecto = new JTextField();
        tfLocalidad = new JTextField();
        tfNDpto = new JTextField();
        bCapturar = new JButton("Capturar datos");
        bConsultar = new JButton("Consulta General");
        bConsultarProyecto = new JButton("Consultar Proyecto");

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarProyecto.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(8, 2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel2.add(new JLabel("Catalogo de Proyectos"));
        panel1.add(new JLabel("NO. PROYECTO: "));
        panel1.add(tfNProyecto);
        panel1.add(new JLabel("NOMBRE PROYECTO: "));
        panel1.add(tfNomProyecto);
        panel1.add(new JLabel("LOCALIDAD: "));
        panel1.add(tfLocalidad);
        panel1.add(new JLabel("NO. DEPARTAMENTO: "));
        panel1.add(tfNDpto);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarProyecto);

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

    public String obtenerDatos(){
        String datos = "";
        
        String proyecto     = tfNProyecto.getText();
        String nombre       = tfNomProyecto.getText();
        String localidad    = tfLocalidad.getText();
        String departamento = tfNDpto.getText();

        if(proyecto.isEmpty() || nombre.isEmpty() || localidad.isEmpty() || departamento.isEmpty())
            datos = "vacio";
        else{
                datos = proyecto+"_"+nombre+"_"+localidad+"_"+departamento;
            System.out.println("\nGUI: "+datos);
        } 
        return datos;
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
        if (e.getSource() == bCapturar) {
            datos = obtenerDatos();

            if(datos.equals("vacio")){
                datos = "Algun campo esta vacio. Verique para continuar";
            }
            else{
                datos = companyad.capturarProyecto(datos);
            }
            //System.out.println("Capturar");
            taDatos.setText(datos);
        }

        if (e.getSource() == bConsultar) {
            datos = companyad.consultarProyectos();
            if(datos.isEmpty()){
                datos = "No se encontraron Proyecto registrados";
            }
            taDatos.setText(datos); 
            //System.out.println("Consultar");
        }
        if (e.getSource() == bConsultarProyecto) {

            datos = tfNProyecto.getText();
            if(datos.isEmpty()){
                datos = "Ingrese el numero de proyecto. Verifique para continuar";
            }
            else{
                datos = companyad.validarProyecto(datos);

                if(datos.equals("FOUND")){
                    datos = companyad.consultarProyecto(tfNProyecto.getText());
                }
                else{
                    datos = "No se encontro el Proyecto "+tfNProyecto.getText();
                }
            }
            taDatos.setText(datos); 
        }
    }

    public static void main(String args[]) {
        new CatalogoProyectoGUI();
    }
}