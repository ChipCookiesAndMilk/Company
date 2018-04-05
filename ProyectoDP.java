import java.util.StringTokenizer;

public class ProyectoDP{
    // Atributos de la clase
    private String nProyecto, nombre, localidad, nDepto;
    
    // Constructores
    public ProyectoDP(){
        this.nProyecto    = "";
        this.nombre       = "";
        this.localidad    = "";
        this.nDepto       = "";
    }
    
    public ProyectoDP(String datos){
        StringTokenizer st  = new StringTokenizer(datos,"_");
        this.nProyecto      = st.nextToken();
        this.nombre         = st.nextToken();
        this.localidad      = st.nextToken();
        this.nDepto         = st.nextToken();
    }
    
    // Accesors o geter's
    public String getNProyecto(){
        return this.nProyecto;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getLocalidad(){
        return this.localidad;
    }
    
    public String getNDepto(){
        return this.nDepto;
    }
    
    // Mutators o seter's
    public void setNProyecto(String nproject){
        this.nProyecto = nproject;
    }
    
    public void setNombre(String name){
        this.nombre = name;
    }
    
    public void setLocalidad(String place){
        this.localidad = place;
    }
    
    public void setNDepto(String ndpto){
        this.nDepto = ndpto;
    }
    
    @Override
    public String toString(){
        //System.out.println("toString\nnProyecto: " + this.nProyecto + " Nombre: " + this.nombre + " Localidad: " + this.localidad + " nDepto: " + this.nDepto);
        return this.nProyecto + "_" + this.nombre + "_" + this.localidad + "_" + this.nDepto;
    }
    
    public String toStringSql(){
        //System.out.println("toStringSQL\nnProyecto: " + this.nProyecto + " Nombre: " + this.nombre + " Localidad: " + this.localidad + " nDepto: " + this.nDepto);
        return "'" + this.nProyecto + "','" + this.nombre + "','" + this.localidad + "','" + this.nDepto + "'";
    }
}