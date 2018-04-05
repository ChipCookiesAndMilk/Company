import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.lang.Exception;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompanyGUI extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu adminCatalogos, employees, departments, projects, repYConsultas, company;
    // Company
    private JMenuItem miExit;
    // Catalogos
    private JMenuItem miCatEmploy, miAEmployProj, miDEmploy, miCatDepart, miADepartProj, miDDepart, miCatProj, miAProj;
    // Consultas
    private JMenuItem miEmployDepart, miEmployProject, miProjectEmploy, miProjectDepart, miRelatEmploy;
    
    private JPanel panel;
    
    // private CompanyADjdbc companyad = new CompanyADjdbc();
    // private AsignacionProyectosEmpleadoGUI2 employproj2 = new AsignacionProyectosEmpleadoGUI2();
    private CatalogoEmpleadoGUI catemploy = new CatalogoEmpleadoGUI();
    private AsignacionEmpleadoProyectoGUI employproj = new AsignacionEmpleadoProyectoGUI();
    private AsignacionDependienteEmpleadoGUI depemploy = new AsignacionDependienteEmpleadoGUI();
    
    private CatalogoDepartamentoGUI catdep = new CatalogoDepartamentoGUI();
    private AsignacionDepartamentoProyectoGUI depproj = new AsignacionDepartamentoProyectoGUI();
    private AsignacionDependienteDepartamentoGUI depdepart = new AsignacionDependienteDepartamentoGUI();
    
    private CatalogoProyectoGUI catproy = new CatalogoProyectoGUI();
    private AsignacionProyectoGUI proj = new AsignacionProyectoGUI();
    
    private ConsultaEmpleadoDepartamentoGUI consempdep= new ConsultaEmpleadoDepartamentoGUI();
    private ConsultaEmpleadoProyectoGUI consempproy = new ConsultaEmpleadoProyectoGUI();
    private ConsultaProyectoEmpleadoGUI consproyemp = new ConsultaProyectoEmpleadoGUI();
    private ConsultaProyectoDepartamentoGUI consproydep = new ConsultaProyectoDepartamentoGUI();
    private ConsultaFamiliarEmpleadoGUI consfamemp = new ConsultaFamiliarEmpleadoGUI();
    
    //Faltan los de proyecto
    
    public CompanyGUI() {
        super("Company Administration Software - CAS");
        // 1. Crear los objetos de los atributos
        menuBar = new JMenuBar();
        
        // 1.1 Main Menu
        company = new JMenu("Company");
        adminCatalogos = new JMenu("Administración de catalogos");
        repYConsultas = new JMenu("Reportes y Consultas");
        
        // 1.1.1 JMenuItems of "company"
        miExit = new JMenuItem("Salir");
        
        // 1.2 JMenuItems of "Administración de catalogos"
        employees = new JMenu("Empleados");
        departments = new JMenu("Departamentos");
        projects = new JMenu("Proyectos");
        
        // 1.2.1 JMenuItems: employees
        miCatEmploy = new JMenuItem("Catalogo de Empleados");
        miAEmployProj = new JMenuItem("Asignacion de Empleados a Proyectos");
        miDEmploy = new JMenuItem("Dependientes de Empleados");
        
        // 1.2.2 JMenuItems: departments
        miCatDepart = new JMenuItem("Catalogo de Departamentos");
        miADepartProj = new JMenuItem("Asignacion de Proyectos a Departamento");
        miDDepart = new JMenuItem("Administrador del Departamento");
        
        // 1.2.3 JMenuItems: projects
        miCatProj = new JMenuItem("Catalogo de proyectos");
        miAProj = new JMenuItem("Asignación de proyectos");
        
        // 1.3 JMenuItems of "Reportes y Consultas"
        miEmployDepart = new JMenuItem("1. Empleados de un departamento");
        miEmployProject = new JMenuItem("2. Empleados asignados a un proyecto");
        miProjectEmploy = new JMenuItem("3. Proyectos de un empleado");
        miProjectDepart = new JMenuItem("4. Proyectos de un departamento");
        miRelatEmploy = new JMenuItem("5. Familiares de un empleado");
        
        // 2. Adicionar JMenuItems a los JMenus
        
        // 2.1 JMenuBar: menuBar
        menuBar.add(company);
        menuBar.add(adminCatalogos);
        menuBar.add(repYConsultas);
        
        // 2.2 JMenu: company
        company.add(miExit);
        
        // 2.3 JMenu: adminCatalogos
        adminCatalogos.add(employees);
        adminCatalogos.add(departments);
        adminCatalogos.add(projects);
        
        // 2.3.1 JMenu: employees
        employees.add(miCatEmploy);
        employees.add(miAEmployProj);
        employees.add(miDEmploy);
        
        // 2.3.2 JMenu: departments
        departments.add(miCatDepart);
        departments.add(miADepartProj);
        departments.add(miDDepart);
        
        // 2.3.3 JMenu: projects
        projects.add(miCatProj);
        projects.add(miAProj);
        
        // 2.4 JMenu: repYConsultas
        repYConsultas.add(miEmployDepart);
        repYConsultas.add(miEmployProject);
        repYConsultas.add(miProjectEmploy);
        repYConsultas.add(miProjectDepart);
        repYConsultas.add(miRelatEmploy);
        
        // 3. Adicionar addActionListener a lo JButtons
        miCatEmploy.addActionListener(this);
        miAEmployProj.addActionListener(this);
        miDEmploy.addActionListener(this);
        
        miCatDepart.addActionListener(this);
        miADepartProj.addActionListener(this);
        miDDepart.addActionListener(this);
        
        miCatProj.addActionListener(this);
        miAProj.addActionListener(this);
        
        miEmployDepart.addActionListener(this);
        miEmployProject.addActionListener(this);
        miProjectEmploy.addActionListener(this);
        miProjectDepart.addActionListener(this);
        miRelatEmploy.addActionListener(this);
        
        miExit.addActionListener(this);
        
        // 4. Definir los Layouts de los JPanels
        
        // 5. Colocar los objetos de los atributos en los JPanels correspondientes
        
        // 6. Adicionar el panel2 al JFrame y hacerlo visible
        setJMenuBar(menuBar);
        setSize(500, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public JPanel makePanel(){
        try{
            panel = new JPanel();
            panel.setVisible(false);
            getContentPane().removeAll();
            getContentPane().remove(panel);
        }
        catch(Exception e){
            System.out.println("Exception Panel: "+e);
        }
        //System.out.println("Catch: Panel doesn't exist");
        //panel = new JPanel();
        //panel.setVisible(false);
        // panel.setLayout(null);
        // panel.removeAll();
        pack();
        setSize(600, 600);
        
        return panel;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == miCatEmploy) {
            // JOptionPane.showMessageDialog(null, "Catalogo de Empleados");
            panel = makePanel();
            panel.add(new JLabel("Catalogo de Empleados"));
            panel = catemploy.getPanel2();
            
            getContentPane().add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miAEmployProj) {
            // JOptionPane.showMessageDialog(null, "Asignación de Proyectos");
            panel = makePanel();
            panel.add(new JLabel("Asignacion de Proyectos a Empleados"));
            panel = employproj.getPanel2();
            // panel = employproj2.getFrame();
            // panel.setVisible(true);
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miDEmploy) {
            // JOptionPane.showMessageDialog(null, "Asignación de Dependientes");
            panel = makePanel();
            panel.add(new JLabel("Asignacion de Dependientes a Empleados"));
            panel = depemploy.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miCatDepart) {
            // JOptionPane.showMessageDialog(null, "Catalogo de Departamentos");
            panel = makePanel();
            panel.add(new JLabel("Catalogo de Departamentos"));
            panel = catdep.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miADepartProj) {
            // JOptionPane.showMessageDialog(null, "Asignación de Asignacion de Departamento a Proyectos");
            panel = makePanel();
            panel.add(new JLabel("Asignacion de Departamento a Proyectos"));
            panel = depproj.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miDDepart) {
            // JOptionPane.showMessageDialog(null, "Administrador de Departamento");
            panel = makePanel();
            panel.add(new JLabel("Administrador de Departamento"));
            panel = depdepart.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miCatProj) {
            // JOptionPane.showMessageDialog(null, "Catalogo de proyectos");
            panel = makePanel();
            panel.add(new JLabel("Catalogo de proyectos"));
            panel = catproy.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miAProj) {
            // JOptionPane.showMessageDialog(null, "Asignación de proyectos");
            panel = makePanel();
            panel.add(new JLabel("Asignación de proyectos"));
            panel = employproj.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miEmployDepart) {
            // JOptionPane.showMessageDialog(null, "Empleados de un departamento");
            panel = makePanel();
            panel.add(new JLabel("Empleados de un departamento"));
            panel = consempdep.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miEmployProject) {
            // JOptionPane.showMessageDialog(null, "Empleados de un proyecto");
            panel = makePanel();
            panel.add(new JLabel("Empleados de un proyecto"));
            panel = consempproy.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miProjectEmploy) {
            // JOptionPane.showMessageDialog(null, "Proyectos de un empleado");
            panel = makePanel();
            panel.add(new JLabel("Proyectos de un empleado"));
            panel = consproyemp.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miProjectDepart) {
            // JOptionPane.showMessageDialog(null, "Proyectos de un departamento");
            panel = makePanel();
            panel.add(new JLabel("Proyectos de un departamento"));
            panel = consproydep.getPanel2();
            
            add(panel);
            setVisible(true);
        }
        
        if (e.getSource() == miRelatEmploy) {
            // JOptionPane.showMessageDialog(null, "Familiares de un empleado");
            panel = makePanel();
            panel.add(new JLabel("Familiares de un empleado"));
            panel = consfamemp.getPanel2();
            
            add(panel);
            setVisible(true);
        }        
        
        if (e.getSource() == miExit) {
            System.exit(0);
        }
    }
    
    public static void main(String args[]) {
        new CompanyGUI();
    }
}