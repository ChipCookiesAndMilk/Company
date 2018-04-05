import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class CompanyADjdbc{
    static Date date = new Date();
    
    private Connection      conexion;
    private Statement       statement;
    
    private DepartamentoDP  departamentodp;
    private DependienteDP   dependientedp;
    private EmpleadoDP      empleadodp;
    private LocalidadDP     localidaddp;
    private ProyectoDP      proyectodp;
    private TrabajaEnDP     trabajadp;
    
/* Conexion */    
    public CompanyADjdbc(){
        try {
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           conexion = DriverManager.getConnection("jdbc:mysql://localhost/Company?user=root&password=hhooppee");
            System.out.println("\n\tConexion exitosa a la BD...");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error1: "+cnfe);
        }
        catch(InstantiationException ie){
            System.out.println("Error 2: "+ie);
        }
        catch(IllegalAccessException iae){
            System.out.println("Error 3: "+iae);
        }
        catch(SQLException sqle){
            System.out.println("Error 4: "+sqle);
        }
    }
// Get Date
    public String getDate(){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(date);        
        return s;
    }
/* ADMINISTRACION DE CATALOGOS */
// Capturar Empleado
    public String capturarEmpleado(String datos){ 
        //System.out.println("\nAD: "+datos);
        empleadodp = new EmpleadoDP(datos);
        String query = "INSERT INTO EMPLEADO VALUES("+empleadodp.toStringSql()+")";

        try {  
            statement = conexion.createStatement();
            statement.executeUpdate(query);
            statement.close();
            datos = "Datos capturados: "+query;
            System.out.println(query);
        }
        catch (SQLException sqle) {
            datos ="Error capturar datos " + sqle;
        }
        return datos;
    }
// Consulta General Empleados
    public String consultarEmpleados(){
        String datos = "";
        ResultSet tr;

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery("SELECT *FROM EMPLEADO");
            System.out.println("SELECT *FROM EMPLEADO");
            empleadodp = new EmpleadoDP();

            while (tr.next()) {
                empleadodp.setNss(tr.getString(1));
                empleadodp.setNombre(tr.getString(2));
                empleadodp.setDireccion(tr.getString(3));
                empleadodp.setSalario(tr.getInt(4));
                empleadodp.setFechaNacimiento(tr.getString(5));
                empleadodp.setSexo(tr.getString(6));
                empleadodp.setNssSup(tr.getString(7));
                empleadodp.setNDepto(tr.getString(8));
                
                datos = datos + empleadodp.toString() + "\n";
            }        
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            datos = "Error consultar cliente "+sqle;
        }
        return datos;  
    }
// Validate the employee exists
    public String validarEmpleado(String empleado){
        ResultSet tr = null;
        String nss = "";
        String flag = "NOT_FOUND";

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery("SELECT * FROM Empleado WHERE nss = '"+empleado+"'");
        // Get the nss
            tr.next();
            nss = tr.getString(1);

            statement.close();
            System.out.println("SELECT * FROM Empleado WHERE nss = '"+empleado+"'");
		}
		catch(SQLException sql){
			System.out.println("Error: "+sql);
        }
        if(nss.equals(empleado)){ flag = "FOUND"; }

        return flag;
    }
// Consult an specific Employee
    public String consultaEmpleado(String empleado){
        String datos = "";
        ResultSet tr = null;

        String query = "SELECT * FROM Empleado WHERE nss = '"+empleado+"'";

        try{
			statement = conexion.createStatement();
			tr = statement.executeQuery(query);
			
			empleadodp = new EmpleadoDP();
			while(tr.next()){
				empleadodp.setNss(tr.getString(1));
				empleadodp.setNombre(tr.getString(2));
				empleadodp.setDireccion(tr.getString(3));
				empleadodp.setSalario(tr.getInt(4));
				empleadodp.setFechaNacimiento(tr.getString(5));
                empleadodp.setSexo(tr.getString(6));
                empleadodp.setNssSup(tr.getString(7));
                empleadodp.setNDepto(tr.getString(8));
				
				datos = datos + empleadodp.toString()+"\n";
			}
			statement.close();
            System.out.println(query);
		}
		catch(SQLException sql){
			System.out.println("Error: "+sql);
			datos = "Error en la consulta a Empleado";
		}
        return datos;
    }
// an employee
    public String actualizarEmpleado(String datos, String nss){
        System.out.println(nss);
        int tr;
        String query = "update empleado set "+datos+" where nss = '"+nss + "'";
        System.out.println(query);

        try {
            statement = conexion.createStatement();
            tr = statement.executeUpdate(query);

            statement.close();
            System.out.println(query);
        } catch (SQLException sql) {
            System.out.println("Error: " + sql);
            return "Error en la consulta a Empleado";
        }
        return "ACTUALIZADO";
    }
// Consult General Projects
public String consultarProyectos(){
    String datos = "";
    ResultSet tr;

    try{
        statement = conexion.createStatement();
        tr = statement.executeQuery("SELECT *FROM Proyecto");
        System.out.println("SELECT *FROM Proyecto");
        proyectodp = new ProyectoDP();

        while (tr.next()) {
            proyectodp.setNProyecto(tr.getString(1));
			proyectodp.setNombre(tr.getString(2));
			proyectodp.setLocalidad(tr.getString(3));
			proyectodp.setNDepto(tr.getString(4));
            
            datos = datos + proyectodp.toString() + "\n";
        }        
        statement.close();
        //System.out.println("\nAD: "+datos);
    }
    catch(SQLException sqle){
        datos = "Error consultar cliente "+sqle;
    }
    return datos;  
}
// Consult an specific Project
    public String consultarProyecto(String proyecto){
        String datos = "";
        ResultSet tr = null;

        String query = "SELECT * FROM Proyecto WHERE nProyecto = '"+proyecto+"'";

        try{
			statement = conexion.createStatement();
			tr = statement.executeQuery(query);
			
			proyectodp = new ProyectoDP();
			while(tr.next()){
				proyectodp.setNProyecto(tr.getString(1));
				proyectodp.setNombre(tr.getString(2));
				proyectodp.setLocalidad(tr.getString(3));
				proyectodp.setNDepto(tr.getString(4));
				
				datos = datos + proyectodp.toString()+"\n";
			}
			statement.close();
            System.out.println(query);
		}
		catch(SQLException sql){
			System.out.println("Error: "+sql);
			datos = "Error en la consulta de Proyecto";
        }
        
        return datos;
    }
// Validate the Project exists
    public String validarProyecto(String proyecto){
        ResultSet tr = null;
        String nProyecto = "";
        String flag = "NOT_FOUND";

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery("SELECT * FROM Proyecto WHERE nProyecto = '"+proyecto+"'");
            
            tr.next();
            nProyecto = tr.getString(1);
            
            statement.close();
            System.out.println("SELECT * FROM Proyecto WHERE nProyecto = '"+proyecto+"'");
        }
        catch(SQLException sql){
            System.out.println("Error: "+sql);
        }
        if(nProyecto.equals(proyecto)){ flag = "FOUND"; }
        return flag;
    }
// Capturar Departamento
    public String capturarDepartamento(String datos){ 
        //System.out.println("\nAD_IN: "+datos);
        String fecha = getDate();
        datos = datos +"_"+fecha;
        
        departamentodp = new DepartamentoDP(datos);
        String query = "INSERT INTO DEPARTAMENTO VALUES("+departamentodp.toStringSql()+")";
        System.out.println(query);
        try {  
            statement = conexion.createStatement();
            statement.executeUpdate(query);
            statement.close();
            datos = "Datos capturados: "+datos;
        }
        catch (SQLException sqle) {
            datos ="Error capturar datos " + sqle;
        }
        return datos;
    }
// Validate the Department exists
    public String validarDepartamento(String depto){
        ResultSet tr = null;
        String query = "SELECT * FROM Departamento WHERE ndepto = '"+depto+"'";
        String flag = "NOT_FOUND";
        System.out.println("AD_ENTER");

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            
            tr.next();
            String nDepto = tr.getString(1);
            
            statement.close();
            System.out.println(query);
            if(nDepto.equals(depto)){ flag = "FOUND"; }
        }
        catch(SQLException sql){
            System.out.println("Error: "+sql);
        }
        return flag;
    }
// Consult an specific Depto
    public String consultarDepto(String depto){
        String datos = "";
        ResultSet tr = null;

        String query = "SELECT * FROM Departamento WHERE ndepto = '"+depto+"'";
        System.out.println(query);

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            
            empleadodp = new EmpleadoDP();
            while(tr.next()){
                departamentodp.setNdepto(tr.getString(1));
                departamentodp.setNombre(tr.getString(2));
                departamentodp.setNssAdmin(tr.getString(3));
                departamentodp.setFechaInicio(tr.getString(4));
                
                datos = datos + departamentodp.toString() + "\n";
            }
            statement.close();
        }
        catch(SQLException sql){
            System.out.println("Error: "+sql);
            datos = "Error en la consulta a Empleado";
        }
        return datos;
    }
// Consulta General Departamentos
    public String consultarDepartamentos(){
        String datos = "";
        ResultSet tr;
        String query = "SELECT *FROM Departamento";

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            System.out.println(query);
            departamentodp = new DepartamentoDP();

            while (tr.next()) {
                departamentodp.setNdepto(tr.getString(1));
                departamentodp.setNombre(tr.getString(2));
                departamentodp.setNssAdmin(tr.getString(3));
                departamentodp.setFechaInicio(tr.getString(4));
                
                datos = datos + departamentodp.toString() + "\n";
            }        
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            datos = "Error consultar cliente "+sqle;
        }
        return datos;  
    }
// Consulta General de Asignacion Empleados Proyecto
    public String consultaAsignacionEmpleadosProyecto(){
        String datos = "";
        String query = "SELECT *FROM TrabajaEn";
        ResultSet tr;

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            System.out.println(query);
            trabajadp = new TrabajaEnDP();

            while (tr.next()) {
                trabajadp.setHoras(tr.getString(1));
                trabajadp.setNss(tr.getString(2));
                trabajadp.setNProyecto(tr.getString(3));

                datos = datos + trabajadp.toString() + "\n";
            }        
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            datos = "Error al consultar "+sqle;
        }
        return datos; 
    }
// Capturar Proyecto
    public String capturarProyecto(String datos){ 
        //System.out.println("\nAD: "+datos);

        proyectodp = new ProyectoDP(datos);
        String query = "INSERT INTO Proyecto VALUES("+proyectodp.toStringSql()+")";
        System.out.println(query);
        try {  
            statement = conexion.createStatement();
            statement.executeUpdate(query);
            statement.close();
            datos = "Datos capturados: "+datos;
            System.out.println(query);
        }
        catch (SQLException sqle) {
            datos ="Error capturar datos " + sqle;
        }
        return datos;
    }
// Capturar Empleado Proyecto
    public String capturarEmpleadoProyecto(String datos){
        //System.out.println("\nAD: "+datos);
        trabajadp = new TrabajaEnDP(datos);
        String query = "INSERT INTO TrabajaEn VALUES("+trabajadp.toStringSql()+")";
        System.out.println(query);
        try {  
            statement = conexion.createStatement();
            statement.executeUpdate(query);
            statement.close();
            datos = "Datos capturados: "+datos;
            //System.out.println(query);
        }
        catch (SQLException sqle) {
            datos = "Error capturar datos " + sqle;
        }
        return datos;
    }
// Consulta General Dependientes
    public String consultarDependientes(){
        String datos = "";
        ResultSet tr;
        String query = "SELECT *FROM Dependiente";

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            System.out.println(query);
            dependientedp = new DependienteDP();

            while (tr.next()) {
                dependientedp.setNombre(tr.getString(1));
                dependientedp.setFechaNacimiento(tr.getString(2));
                dependientedp.setSexo(tr.getString(3));
                dependientedp.setParentesco(tr.getString(4));
                dependientedp.setNss(tr.getString(5));
                
                datos = datos + dependientedp.toString() + "\n";
            }        
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            datos = "Error consultar cliente "+sqle;
        }
        return datos;  
    }
// Asignar Proyecto a Departamento
    public String capturarProyectoDepartamento(String datos){
        return datos = "Datos entro a AD :"+datos;
    }
// Capturar Dependiente Empleado
    public String capturarDependienteEmpleado(String datos){
        System.out.println("\nAD: "+datos);
        dependientedp = new DependienteDP(datos);
        String query = "INSERT INTO Dependiente VALUES("+dependientedp.toStringSql()+")";
        //System.out.println("Query: "+query);
        try {  
        //System.out.println("Try");
            statement = conexion.createStatement();
        //System.out.println("Conexion");
            statement.executeUpdate(query);
        //System.out.println("Query");
            statement.close();
        //System.out.println("Close");
            datos = "Datos capturados: "+datos;
        }
        catch (SQLException sqle) {
            datos = "Error capturar datos " + sqle;
        }
        return datos;
    }
//  Consultar Administrador Depto
    public String consultarDeptoAdmin(String depto){
        ResultSet tr;
        String query = "SELECT nssAdmin FROM Departamento WHERE ndepto = '"+depto+"'";
        System.out.println(query);

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);

            tr.next();
            depto = tr.getString(1);
     
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            depto = "Error consultar cliente "+sqle;
        }
        return depto;
    }
// Consultar Departamentos Administrador por Empleado
    public String consultarAdminDepto(String admin){
        ResultSet tr;
        String query = "SELECT ndepto FROM Departamento WHERE nssAdmin='"+admin+"'";

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            System.out.println(query);
            departamentodp = new DepartamentoDP();

            tr.next();
            admin = tr.getString(1);
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            admin = "Error consultar cliente "+sqle;
        }
        return admin;  
    }

/* REPORTES Y CONSULTAS */
//consultarEmpleadosDepartamento
    public String consultarEmpleadosDepartamento(String departamento){
        String datos = "";
        String empleado = "";
        ResultSet tr = null;

        String query = "SELECT * FROM Empleado WHERE nDepto = '"+departamento+"'";

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            
            while(tr.next()){
                empleado = tr.getString(1);
                int i = 2;
                while(i<8){
                    empleado = empleado+"_"+tr.getString(i);
                    i++;
                    //System.out.println(empleado);
                }    
                datos = datos + empleado+"\n";
                empleado = "";
                //System.out.println(datos);
            }
            statement.close();
            System.out.println(query);
        }
        catch(SQLException sql){
            System.out.println("Error: "+sql);
            datos = "Error en la consulta a Empleado";
        }
        
        return datos;
    }
// consultarEmpleadosProyecto
    public String consultarEmpleadosProyecto(String proyecto){
        String datos = "";
        ResultSet tr;
        String query = "SELECT nProyecto, Empleado.nss, Empleado.nombre, nDepto, horas FROM TrabajaEn JOIN Empleado ON Empleado.nss=TrabajaEn.nss WHERE nProyecto = '"+proyecto+"'";
        System.out.println(query);

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            
            while(tr.next()){
                datos = tr.getString(1);
                int i = 2;
                while(i<6){
                    datos = datos+"_"+tr.getString(i);
                    i++;
                    //System.out.println(empleado);
                }    
                proyecto = proyecto + datos+"\n";
                //System.out.println(datos);
            } 
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            proyecto = "Error consultar cliente "+sqle;
        }
        return proyecto;  
    }
// consultarProyectosEmpleado
    public String consultarProyectosEmpleado(String empleado){
        String datos = "";
        ResultSet tr;
        String query = "SELECT nProyecto, Empleado.nss, Empleado.nombre, nDepto, horas FROM TrabajaEn JOIN Empleado ON TrabajaEn.nss=Empleado.nss WHERE TrabajaEn.nss = '"+empleado+"'";
        System.out.println(query);

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            
            while(tr.next()){
                datos = tr.getString(1);
                int i = 2;
                while(i<6){
                    datos = datos+"_"+tr.getString(i);
                    i++;
                    //System.out.println(empleado);
                }    
                empleado = empleado + datos+"\n";
                //System.out.println(datos);
            } 
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            empleado = "Error consultar cliente "+sqle;
        }
        return empleado;  
    }
// consultarProyectosDepartamento
    public String consultarProyectosDepartamento(String depto){
        String datos = "";
        ResultSet tr;
        String query = "SELECT *FROM Proyecto WHERE nDepto='"+depto+"'";
        System.out.println(query);

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            System.out.println(query);
            proyectodp = new ProyectoDP();

            while (tr.next()) {
                proyectodp.setNProyecto(tr.getString(1));
                proyectodp.setNombre(tr.getString(2));
                proyectodp.setLocalidad(tr.getString(3));
                proyectodp.setNDepto(tr.getString(4));
                
                datos = datos + proyectodp.toString() + "\n";
            }        
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            datos = "Error consultar cliente "+sqle;
        }
        return datos;  
    }
// consultarProyectosEmpleado
    public String consultarFamiliaresEmpleado(String empleado){
        String datos = "";
        ResultSet tr;
        String query = "SELECT *FROM Dependiente WHERE nss='"+empleado+"'";
        System.out.println(query);

        try{
            statement = conexion.createStatement();
            tr = statement.executeQuery(query);
            empleado = "";
            while(tr.next()){
                datos = tr.getString(1);
                int i = 2;
                while(i<6){
                    datos = datos+"_"+tr.getString(i);
                    i++;
                    //System.out.println(datos);
                }    
                empleado = empleado + datos+"\n";
                //System.out.println(datos);
            } 
            statement.close();
            //System.out.println("\nAD: "+datos);
        }
        catch(SQLException sqle){
            empleado = "Error consultar cliente "+sqle;
        }
        return empleado;  
    }
}