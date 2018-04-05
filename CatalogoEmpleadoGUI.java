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

public class CatalogoEmpleadoGUI extends JFrame implements ActionListener {
    private JTextField tfNoSSEmpleado, tfNomEmpleado, tfDireccion, tfSalario, tfFechaNacimiento, tfSexo, tfNoSSSup, tfNDepto;
    private JButton bCapturar, bConsultar, bConsultarNss, bActualizar;
    private JPanel panel1, panel2;
    private JTextArea taDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public CatalogoEmpleadoGUI() {
        super("Catalogo de Empleados");
        // 1. Crear los objetos de los atributos
        panel1 = new JPanel();
        panel2 = new JPanel();

        taDatos = new JTextArea(10, 38);

        tfNoSSEmpleado = new JTextField();
        tfNomEmpleado = new JTextField();
        tfDireccion = new JTextField();
        tfSalario = new JTextField();
        tfFechaNacimiento = new JTextField();
        tfSexo = new JTextField();
        tfNoSSSup = new JTextField();
        tfNDepto = new JTextField();
        bCapturar = new JButton("Capturar datos");
        bConsultar = new JButton("Consultar General");
        bConsultarNss = new JButton("Consulta por NSS");
        bActualizar = new JButton("Actualizar Datos");

        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarNss.addActionListener(this);
        bActualizar.addActionListener(this);
        // bSalir.addActionListener(this);

        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(12, 2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel2.add(new JLabel("Catalogo de Empleados"));
        panel1.add(new JLabel("NO. SS. EMPLEADO: "));
        panel1.add(tfNoSSEmpleado);
        panel1.add(new JLabel("NOMBRE: "));
        panel1.add(tfNomEmpleado);
        panel1.add(new JLabel("DIRECCION: "));
        panel1.add(tfDireccion);
        panel1.add(new JLabel("SALARIO: "));
        panel1.add(tfSalario);
        panel1.add(new JLabel("FECHA DE NACIMIENTO: "));
        panel1.add(tfFechaNacimiento);
        panel1.add(new JLabel("SEXO: "));
        panel1.add(tfSexo);
        panel1.add(new JLabel("NO. SS. SUPERVISOR: "));
        panel1.add(tfNoSSSup);
        panel1.add(new JLabel("NO. DEPARTAMENTO: "));
        panel1.add(tfNDepto);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarNss);
        panel1.add(bActualizar);

        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        taDatos.setEditable(false);

        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(600, 620);
        // setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel getPanel2() {
        return this.panel2;
    }

    public String obtenerDatos(){
        String datos ="";

        String nssEmpleado      = tfNoSSEmpleado.getText();
        String nombre           = tfNomEmpleado.getText();
        String direccion        = tfDireccion.getText();
        String salario          = tfSalario.getText();
        String fechaNacimiento  = tfFechaNacimiento.getText();
        String sexo             = tfSexo.getText();
        String nssSupervisor    = tfNoSSSup.getText();
        String nDepto           = tfNDepto.getText();

        if(nssEmpleado.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || salario.isEmpty() || fechaNacimiento.isEmpty() || sexo.isEmpty())
            datos = "vacio";
        else {
            int nSalario = 0;
            try { //por si no hay un valor numerico
                nSalario = Integer.parseInt(salario);
            } 
            catch (NumberFormatException nfe) {
                datos = "NO_NUMERICO";
            }
            if(nssSupervisor.isEmpty()){ nssSupervisor = "0011"; }
            if(nDepto.isEmpty()){   nDepto = "D0"; }    
            if (datos!="NO_NUMERICO")
                datos = nssEmpleado+"_"+nombre+"_"+direccion+"_"+nSalario+"_"+fechaNacimiento+"_"+sexo+"_"+nssSupervisor+"_"+nDepto;
            System.out.println("\nGUI: "+datos);
        } 
        return datos;
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
// Cpaturar Empleado
        if (e.getSource() == bCapturar) {
            // Valid fields
            datos = obtenerDatos();

            if(datos.equals("vacio")){
                //System.out.println(datos);
                datos = "Algun campo esta vacio. Verique para continuar";
            }
            else if(datos.equals("NO_NUMERICO")){
                datos = "El salario debe ser numerico. Verique para continuar";
            }   
            else{
                datos = companyad.capturarEmpleado(datos);
            }
            //System.out.println("Capturar");
            taDatos.setText(datos);
        }
// Consulta General
        if (e.getSource() == bConsultar) {
            datos = companyad.consultarEmpleados();
            if(datos.isEmpty()){
                datos = "No se encontraron empleados registrados";
            }
            taDatos.setText(datos); 
        }
        if (e.getSource() == bConsultarNss) {

            datos = tfNoSSEmpleado.getText();

            if(datos.isEmpty()){
                datos = "Ingrese el nss del empleado. Verifique para continuar";
            }
            else{
                datos = companyad.validarEmpleado(datos);

                if(datos.equals("FOUND")){
                    datos = companyad.consultaEmpleado(tfNoSSEmpleado.getText());
                }
                else{
                    datos = "No se encontro al empleado "+tfNoSSEmpleado.getText();
                }
            }
            taDatos.setText(datos); 
        }
        if (e.getSource() == bActualizar) {
            
            String nssEmpleado = tfNoSSEmpleado.getText();
            String nombre = tfNomEmpleado.getText();
            String direccion = tfDireccion.getText();
            String salario = tfSalario.getText();
            String fechaNacimiento = tfFechaNacimiento.getText();
            String sexo = tfSexo.getText();
            String nssSupervisor = tfNoSSSup.getText();
            String nDepto = tfNDepto.getText();
            
            if (!nombre.isEmpty()) {
                if (!datos.isEmpty()){
                    datos += ",";
                }
                datos += "nombre = '" + nombre +"'";
            }
            if (!direccion.isEmpty()) {
                if (!datos.isEmpty()){
                    datos += ",";
                }
                datos += "direccion = '" + direccion +"'";
            }
            if (!salario.isEmpty()) {
                if (!datos.isEmpty()){
                    datos += ",";
                }
                datos += "salario = " + salario;
            }
            if (!fechaNacimiento.isEmpty()) {
                if (!datos.isEmpty()){
                    datos += ",";
                }
                datos += "fechaNacimiento = '" + fechaNacimiento +"'";
            }
            if (!sexo.isEmpty()) {
                if (!datos.isEmpty()){
                    datos += ",";
                }
                datos += "sexo = '" + sexo +"'";
            }
            if (!nssSupervisor.isEmpty()) {
                if (!datos.isEmpty()){
                    datos += ",";
                }
                datos += "nssSup = '" + nssSupervisor +"'";
            }
            if (!nDepto.isEmpty()) {
                if (!datos.isEmpty()){
                    datos += ",";
                }
                datos += "nDepto = '" + nDepto +"'";
            }
            

            System.out.println(datos);
            datos = companyad.actualizarEmpleado(datos, nssEmpleado);
            if (datos.equals("ACTUALIZADO")){
                datos = "Se actualizo con exito\n";
                datos += companyad.consultaEmpleado(nssEmpleado);
            }
            taDatos.setText(datos); 
            // // taDatos.setText("bActualizar");
        }
    }

    public static void main(String args[]) {
        new CatalogoEmpleadoGUI();
    }
}