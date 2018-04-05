import java.util.StringTokenizer;

public class EmpleadoDP{
    // Atributos de la clase
    private String nss, nombre, direccion, fechaNacimiento, sexo, nssSup, nDepto;
    private int salario;
    
    // Constructores
    public EmpleadoDP(){
        this.nss                = "";
        this.nombre             = "";
        this.direccion          = "";
        this.salario            = 0;
        this.fechaNacimiento    = "";
        this.sexo               = "";
        this.nssSup             = "";
        this.nDepto             = "";
    }
    
    public EmpleadoDP(String datos){
        StringTokenizer st = new StringTokenizer(datos,"_");
        
        this.nss            = st.nextToken();
        this.nombre         = st.nextToken();
        this.direccion      = st.nextToken();
        this.salario        = Integer.parseInt(st.nextToken());
        this.fechaNacimiento = st.nextToken();
        this.sexo           = st.nextToken();
        this.nssSup         = st.nextToken();
        this.nDepto         = st.nextToken();
    }
    
    // Accesors o geter's
    public String getNss(){
        return this.nss;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getDireccion(){
        return this.direccion;
    }
    
    public int getSalario(){
        return this.salario;
    }

    public String getFechaNacimiento(){
        return this.fechaNacimiento;
    }

    public String getSexo(){
        return this.sexo;
    }

    public String getNssSup() {
        return this.nssSup;
    }
    
    public String getNDepto() {
        return this.nDepto;
    }
    
    // Mutators o seter's
    public void setNss(String ssn){
        this.nss = ssn;
    }
    
    public void setNombre(String name){
        this.nombre = name;
    }
    
    public void setDireccion(String address){
        this.direccion = address;
    }
    
    public void setSalario(int amount){
        this.salario = amount;
    }

    public void setFechaNacimiento(String bdate){
        this.fechaNacimiento = bdate;
    }

    public void setSexo(String sex){
        this.sexo = sex;
    }

    public void setNssSup(String ssnSup) {
        this.nssSup = ssnSup;
    }

    public void setNDepto(String depnum) {
        this.nDepto = depnum;
    }
    
    @Override
    public String toString(){
        //System.out.println("DP: Cuenta: " + this.nss + " Nombre: " + this.nombre + " Direccion: " + this.direccion + " Salario: " + this.salario + " FechaNacimiento: " + this.fechaNacimiento + " Sexo: " + this.sexo + " nssSup: " + this.nssSup + " nDepto: " + this.nDepto);
        return this.nss + "_" + this.nombre + "_" + this.direccion + "_" + this.salario + "_" + this.fechaNacimiento + "_" + this.sexo + "_" + this.nssSup + "_" + this.nDepto;
    }
    
    public String toStringSql(){
        //System.out.println("DP: Cuenta: " + this.nss + " Nombre: " + this.nombre + " Direccion: " + this.direccion + " Salario: " + this.salario + " FechaNacimiento: " + this.fechaNacimiento + " Sexo: " + this.sexo + " nssSup: " + this.nssSup + " nDepto: " + this.nDepto);
        return "'" + this.nss + "','" + this.nombre + "','" + this.direccion + "'," + this.salario + ",'" + this.fechaNacimiento + "','" + this.sexo + "','" + this.nssSup + "','" + this.nDepto + "'";
    }
}