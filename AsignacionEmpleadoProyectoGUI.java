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
    private JTextField tfNoSSEmpleado, tfNoProyecto, tfHoras;
    private JButton bCapturar, bConsultar;
    private JPanel panel1, panel2;
    private JTextArea taDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public AsignacionEmpleadoProyectoGUI() {
        super("Asignacion de Proyectos");
        // 1. Crear los objetos de los atributos
        panel1 = new JPanel();
        panel2 = new JPanel();

        taDatos = new JTextArea(10, 30);

        tfNoSSEmpleado = new JTextField();
        tfNoProyecto = new JTextField();
        tfHoras = new JTextField();
        bCapturar = new JButton("Capturar datos");
        bConsultar = new JButton("Consulta General");
        // bSalir = new JButton("Exit");

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        // bSalir.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(8, 2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel2.add(new JLabel("Asignacion de Empleados a Proyecto"));
        panel1.add(new JLabel("NO. SS EMPLEADO: "));
        panel1.add(tfNoSSEmpleado);
        panel1.add(new JLabel("NO. PROYECTO: "));
        panel1.add(tfNoProyecto);
        panel1.add(new JLabel("HORAS: "));
        panel1.add(tfHoras);
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

    public String obtenerDatos(){
        String datos = "";

        String empleado   = tfNoSSEmpleado.getText();
        String proyecto   = tfNoProyecto.getText();
        String hrs        = tfHoras.getText();
        
        if(empleado.equals("") || proyecto.equals("") || hrs.isEmpty())
            datos ="VACIO";
        else
        {
            try {//por si no hay un valor numerico
                int horas = Integer.parseInt(hrs);
                datos = horas+"_"+empleado+"_"+proyecto;
            } 
            catch (NumberFormatException nfe) {
                datos = "NO_NUMERICO";
            }
        }
        return datos;
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
        if (e.getSource() == bCapturar) {
            //System.out.println("Capturar");
            datos = obtenerDatos();
            if(datos.equals("VACIO")){
                datos = "Algun campo esta vacio. Verique para continuar";
            }
            else if(datos.equals("NO_NUMERICO")){
                datos = "El salario debe ser numerico. Verique para continuar";
            }
            else{
                // All the data is saved in data
                // this else does some validation and the actual insert
                String valid = companyad.validarEmpleado(tfNoSSEmpleado.getText());

                if(valid.equals("FOUND")){
                    valid = companyad.validarProyecto(tfNoProyecto.getText());  
                    
                    if(valid.equals("FOUND")){
                        datos = companyad.capturarEmpleadoProyecto(datos);
                    }
                    else{
                        datos = "No se encontro el PROYECTO ingresado "+tfNoProyecto.getText()+". Verifique para continuar";
                    }
                }
                else{
                    datos = "No se encontro al EMPLEADO ingresado "+tfNoSSEmpleado.getText()+". Verifique para continuar";
                }
                taDatos.setText(datos);
            }
            //System.out.println("Capturar");
            taDatos.setText(datos);
        }

        if (e.getSource() == bConsultar) {
            datos = companyad.consultaAsignacionEmpleadosProyecto();
            if(datos.isEmpty()){
                datos = "No hay empleados asignados al proyecto";
            }
            else {
                datos = "Empleados asignados al proyecto: \n"+datos;
            }
            taDatos.setText(datos); 
        }
    }

    public static void main(String args[]) {
        new AsignacionEmpleadoProyectoGUI();
    }
}