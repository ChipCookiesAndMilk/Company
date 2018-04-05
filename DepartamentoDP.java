import java.util.StringTokenizer;

public class DepartamentoDP{
    // Atributos de la clase
    private String ndepto, nombre, fechaInicio, nssAdmin;
    
    // Constructores
    public DepartamentoDP(){
        this.ndepto         = "";
        this.nombre         = "";
        this.nssAdmin       = "";
        this.fechaInicio    = "";
    }
    
    public DepartamentoDP(String datos){
        StringTokenizer st  = new StringTokenizer(datos,"_");
        this.ndepto         = st.nextToken();
        this.nombre         = st.nextToken();
        this.nssAdmin       = st.nextToken();
        this.fechaInicio    = st.nextToken();
    }
    
    // Accesors o geter's
    public String getNdepto(){
        return this.ndepto;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getNssAdmin(){
        return this.nssAdmin;
    }

    public String getFechaInicio(){
        return this.fechaInicio;
    }
    
    // Mutators o seter's
    public void setNdepto(String ndpto){
        this.ndepto = ndpto;
    }
    
    public void setNombre(String name){
        this.nombre = name;
    }
    
    public void setNssAdmin(String ssnAdmin){
        this.nssAdmin = ssnAdmin;
    }

    public void setFechaInicio(String startDate){
        this.fechaInicio = startDate;
    }
    
    @Override
    public String toString(){
        //System.out.println("nDepto: " + this.ndepto + " Nombre: " + this.nombre + " nssAdmin: " + this.nssAdmin + " fecha: " + this.fechaInicio);
        return this.ndepto + "_" + this.nombre + "_" + this.nssAdmin + "_" + this.fechaInicio;
    }
    
    public String toStringSql(){
        //System.out.println("\ntoStringSQL\n\tnDepto: " + this.ndepto + " Nombre: " + this.nombre + " nssAdmin: " + this.nssAdmin + " fecha: " + this.fechaInicio);
        return "'" + this.ndepto + "','" + this.nombre + "','" + this.nssAdmin + "','" + this.fechaInicio + "'";
    }
}