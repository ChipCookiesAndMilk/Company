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

public class ConsultaEmpleadoDepartamentoGUI extends JFrame implements ActionListener {
    private JTextField textFieldNDep;
    private JButton botonConsulta;
    private JPanel panelContenido, panelPrincipal;
    private JTextArea textAreaDatos;

    private CompanyADjdbc cad = new CompanyADjdbc();

    public ConsultaEmpleadoDepartamentoGUI() {
        super("Asignacion Departamento a Proyectos");
        panelContenido = new JPanel();
        panelPrincipal = new JPanel();

        textAreaDatos = new JTextArea(10, 35);

        textFieldNDep = new JTextField();
        botonConsulta = new JButton("Consultar");

        botonConsulta.addActionListener(this);

        panelContenido.setLayout(new GridLayout(4, 2));
        panelPrincipal.setLayout(new FlowLayout());

        panelContenido.add(new JLabel("Numero Departamento: "));
        panelContenido.add(textFieldNDep);
        panelContenido.add(botonConsulta);

        panelPrincipal.add(panelContenido);
        panelPrincipal.add(new JScrollPane(textAreaDatos));

        add(panelPrincipal);
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel getPanel2() {
        return this.panelPrincipal;
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
        if (e.getSource() == botonConsulta) {
            datos = textFieldNDep.getText();

            if(datos.isEmpty()){
                datos = "Ingrese el numero del Departamento.";
            }
            else{
                datos = cad.validarDepartamento(datos);

                if(datos.equals("FOUND")){
                    //System.out.println("Departamento Enocntrado");
                    datos = cad.consultarEmpleadosDepartamento(textFieldNDep.getText());
                    if(datos.isEmpty()){
                        datos = "El departamento "+textFieldNDep.getText()+" no tiene empleados";
                    }
                    else{
                        datos = "El departamento "+textFieldNDep.getText()+" tiene los siguientes empleados:\n\n"+datos;
                    }
                }
                else{
                    datos = "No se encontro el Departamento "+textFieldNDep.getText();
                }
            }
            textAreaDatos.setText(datos);
        }
    }

    public static void main(String args[]) {
        new ConsultaEmpleadoDepartamentoGUI();
    }
}