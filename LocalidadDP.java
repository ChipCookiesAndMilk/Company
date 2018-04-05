import java.util.StringTokenizer;

public class LocalidadDP{
    // Atributos de la clase
    private String nombre, direccion, nDepto;
    private int telefono;
    
    // Constructores
    public LocalidadDP(){
        this.nombre       = "";
        this.direccion    = "";
        this.telefono     = 0;
        this.nDepto       = "";
    }
    
    public LocalidadDP(String datos){
        StringTokenizer st  = new StringTokenizer(datos,"_");
        this.nombre         = st.nextToken();
        this.direccion      = st.nextToken();
        this.telefono       = Integer.parseInt(st.nextToken());
        this.nDepto         = st.nextToken();
    }
    
    // Accesors o geter's
    public String getNombre(){
        return this.nombre;
    }
    
    public String getDireccion(){
        return this.direccion;
    }
    
    public int getTelefono(){
        return this.telefono;
    }
    
    public String getNDepto(){
        return this.nDepto;
    }
    
    // Mutators o seter's
    public void setNombre(String name){
        this.nombre = name;
    }
    
    public void setDireccion(String address){
        this.direccion = address;
    }
    
    public void setTelefono(int phone){
        this.telefono = phone;
    }
    
    public void setNDepto(String ndpto){
        this.nDepto = ndpto;
    }
    
    @Override
    public String toString(){
        System.out.println("nombre: " + this.nombre + " Direccion: " + this.direccion + " Telefono: " + this.telefono + " nDepto: " + this.nDepto);
        return this.nombre + "_" + this.direccion + "_" + this.telefono + "_" + this.nDepto;
    }
    
    public String toStringSql(){
        return "'" + this.nombre + "','" + this.direccion + "'," + this.telefono + ",'" + this.nDepto + "'";
    }
}