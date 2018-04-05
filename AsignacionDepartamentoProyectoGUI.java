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

public class AsignacionDepartamentoProyectoGUI extends JFrame implements ActionListener {
    private JTextField tfNDepto, tfNoProyecto;
    private JButton bCapturar, bConsultar;
    private JPanel panel1, panel2;
    private JTextArea taDatos;

    private CompanyADjdbc companyad = new CompanyADjdbc();

    public AsignacionDepartamentoProyectoGUI() {
        super("Asignacion de Departamento a Proyectos");
        // 1. Crear los objetos de los atributos
        panel1 = new JPanel();
        panel2 = new JPanel();

        taDatos = new JTextArea(10, 30);

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
        panel1.setLayout(new GridLayout(6, 2));
        panel2.setLayout(new FlowLayout());

        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel2.add(new JLabel("Asignacion de Departamento a Proyectos"));
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

    public String obtenerDatos(){
        String datos = "";

        String depto    = tfNDepto.getText();
        String proyecto = tfNoProyecto.getText(); 
        
        if(depto.isEmpty() || proyecto.isEmpty())
            datos = "VACIO";
            System.out.println("VACIO");
        else
        {
            datos = depto+"_"+proyecto;
        }
        return datos;
    }

    public void actionPerformed(ActionEvent e) {
        String datos = "";
        if (e.getSource() == bCapturar) {
            datos = obtenerDatos();
            if(datos.equals("VACIO")){
                System.out.println("Capturar_VACIO");
                datos = "Algun campo esta vacio. Verique para continuar";
            }else{
                // All the data is saved in data
                // this else does some validation and the actual insert
                System.out.println("Capturar_IN");
                String valid = companyad.validarDepartamento(tfNDepto.getText());

                if(valid.equals("FOUND")){
                    valid = companyad.validarProyecto(tfNoProyecto.getText());  
                    
                    if(valid.equals("FOUND")){
                        datos = companyad.capturarProyectoDepartamento(datos);
                    }
                    else{
                        datos = "No se encontro el PROYECTO ingresado "+tfNoProyecto.getText()+". Verifique para continuar";
                    }
                }
                else{
                    datos = "No se encontro el DEPARTAMENTO ingresado "+tfNDepto.getText()+". Verifique para continuar";
                }
            }
            System.out.println("Capturar_OUT");
            taDatos.setText(datos);
        }

        if (e.getSource() == bConsultar) {
            System.out.println("Consultar");
        }
    }

    public static void main(String args[]) {
        new AsignacionDepartamentoProyectoGUI();
    }
}