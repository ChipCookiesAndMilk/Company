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
    private JTextField textFieldNSEmpleado, textFieldNombreEmpleado, textFieldDireccion, textFieldSalario,
            textFieldFecha, tfSexo, textFieldNSSuperior, textFieldNDep;
    private JButton botonCaptura, botonConsulta, botonNSS, botonActualizar;
    private JPanel panelContenido, panelPrincipal;
    private JTextArea taDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public CatalogoEmpleadoGUI() {
        super("Catalogo de Empleados");
        panelContenido = new JPanel();
        panelPrincipal = new JPanel();

        taDatos = new JTextArea(10, 38);

        textFieldNSEmpleado = new JTextField();
        textFieldNombreEmpleado = new JTextField();
        textFieldDireccion = new JTextField();
        textFieldSalario = new JTextField();
        textFieldFecha = new JTextField();
        tfSexo = new JTextField();
        textFieldNSSuperior = new JTextField();
        textFieldNDep = new JTextField();
        botonCaptura = new JButton("Capturar datos");
        botonConsulta = new JButton("Consultar General");
        botonNSS = new JButton("Consulta por NSS");
        botonActualizar = new JButton("Actualizar Datos");

        botonCaptura.addActionListener(this);
        botonConsulta.addActionListener(this);
        botonNSS.addActionListener(this);
        botonActualizar.addActionListener(this);

        panelContenido.setLayout(new GridLayout(12, 2));
        panelPrincipal.setLayout(new FlowLayout());

        panelPrincipal.add(new JLabel("Catalogo de Empleados"));
        panelContenido.add(new JLabel("NO. SS. EMPLEADO: "));
        panelContenido.add(textFieldNSEmpleado);
        panelContenido.add(new JLabel("NOMBRE: "));
        panelContenido.add(textFieldNombreEmpleado);
        panelContenido.add(new JLabel("DIRECCION: "));
        panelContenido.add(textFieldDireccion);
        panelContenido.add(new JLabel("SALARIO: "));
        panelContenido.add(textFieldSalario);
        panelContenido.add(new JLabel("FECHA DE NACIMIENTO: "));
        panelContenido.add(textFieldFecha);
        panelContenido.add(new JLabel("SEXO: "));
        panelContenido.add(tfSexo);
        panelContenido.add(new JLabel("NO. SS. SUPERVISOR: "));
        panelContenido.add(textFieldNSSuperior);
        panelContenido.add(new JLabel("NO. DEPARTAMENTO: "));
        panelContenido.add(textFieldNDep);
        panelContenido.add(botonCaptura);
        panelContenido.add(botonConsulta);
        panelContenido.add(botonNSS);
        panelContenido.add(botonActualizar);

        panelPrincipal.add(panelContenido);
        panelPrincipal.add(new JScrollPane(taDatos));
        taDatos.setEditable(false);

        add(panelPrincipal);
        setSize(600, 620);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel getPanel2() {
        return this.panelPrincipal;
    }

    public String obtenerDatos() {
        String datos = "";

        String nssEmpleado = textFieldNSEmpleado.getText();
        String nombre = textFieldNombreEmpleado.getText();
        String direccion = textFieldDireccion.getText();
        String salario = textFieldSalario.getText();
        String fechaNacimiento = textFieldFecha.getText();
        String sexo = tfSexo.getText();
        String nssSupervisor = textFieldNSSuperior.getText();
        String nDepto = textFieldNDep.getText();

        if (nssEmpleado.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || salario.isEmpty()
                || fechaNacimiento.isEmpty() || sexo.isEmpty())
            datos = "vacio";
        else {
            int nSalario = 0;
            try {
                nSalario = Integer.parseInt(salario);
            } catch (NumberFormatException nfe) {
                datos = "NO_NUMERICO";
            }
            if (nssSupervisor.isEmpty()) {
                nssSupervisor = "0011";
            }
            if (nDepto.isEmpty()) {
                nDepto = "D0";
            }
            if (datos != "NO_NUMERICO")
                datos = nssEmpleado + "_" + nombre + "_" + direccion + "_" + nSalario + "_" + fechaNacimiento + "_"
                        + sexo + "_" + nssSupervisor + "_" + nDepto;
            System.out.println("\nGUI: " + datos);
        }
        return datos;
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
        if (e.getSource() == botonCaptura) {
            datos = obtenerDatos();

            if (datos.equals("vacio")) {
                datos = "Algun campo esta vacio. Verique para continuar";
            } else if (datos.equals("NO_NUMERICO")) {
                datos = "El salario debe ser numerico. Verique para continuar";
            } else {
                datos = companyad.capturarEmpleado(datos);
            }
            taDatos.setText(datos);
        }
        if (e.getSource() == botonConsulta) {
            datos = companyad.consultarEmpleados();
            if (datos.isEmpty()) {
                datos = "No se encontraron empleados registrados";
            }
            taDatos.setText(datos);
        }
        if (e.getSource() == botonNSS) {

            datos = textFieldNSEmpleado.getText();

            if (datos.isEmpty()) {
                datos = "Ingrese el nss del empleado. Verifique para continuar";
            } else {
                datos = companyad.validarEmpleado(datos);

                if (datos.equals("FOUND")) {
                    datos = companyad.consultaEmpleado(textFieldNSEmpleado.getText());
                } else {
                    datos = "No se encontro al empleado " + textFieldNSEmpleado.getText();
                }
            }
            taDatos.setText(datos);
        }
        if (e.getSource() == botonActualizar) {

            String nssEmpleado = textFieldNSEmpleado.getText();
            String nombre = textFieldNombreEmpleado.getText();
            String direccion = textFieldDireccion.getText();
            String salario = textFieldSalario.getText();
            String fechaNacimiento = textFieldFecha.getText();
            String sexo = tfSexo.getText();
            String nssSupervisor = textFieldNSSuperior.getText();
            String nDepto = textFieldNDep.getText();

            if (!nombre.isEmpty()) {
                if (!datos.isEmpty()) {
                    datos += ",";
                }
                datos += "nombre = '" + nombre + "'";
            }
            if (!direccion.isEmpty()) {
                if (!datos.isEmpty()) {
                    datos += ",";
                }
                datos += "direccion = '" + direccion + "'";
            }
            if (!salario.isEmpty()) {
                if (!datos.isEmpty()) {
                    datos += ",";
                }
                datos += "salario = " + salario;
            }
            if (!fechaNacimiento.isEmpty()) {
                if (!datos.isEmpty()) {
                    datos += ",";
                }
                datos += "fechaNacimiento = '" + fechaNacimiento + "'";
            }
            if (!sexo.isEmpty()) {
                if (!datos.isEmpty()) {
                    datos += ",";
                }
                datos += "sexo = '" + sexo + "'";
            }
            if (!nssSupervisor.isEmpty()) {
                if (!datos.isEmpty()) {
                    datos += ",";
                }
                datos += "nssSup = '" + nssSupervisor + "'";
            }
            if (!nDepto.isEmpty()) {
                if (!datos.isEmpty()) {
                    datos += ",";
                }
                datos += "nDepto = '" + nDepto + "'";
            }

            System.out.println(datos);
            datos = companyad.actualizarEmpleado(datos, nssEmpleado);
            if (datos.equals("ACTUALIZADO")) {
                datos = "Se actualizo con exito\n";
                datos += companyad.consultaEmpleado(nssEmpleado);
            }
            taDatos.setText(datos);
        }
    }

    public static void main(String args[]) {
        new CatalogoEmpleadoGUI();
    }
}