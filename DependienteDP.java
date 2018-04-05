import java.util.StringTokenizer;

public class DependienteDP{
    // Atributos de la clase
    private String nombre, fechaNacimiento, sexo, parentesco, nss;
    
    // Constructores
    public DependienteDP(){
        this.nombre             = "";
        this.fechaNacimiento    = "";
        this.sexo               = "";
        this.parentesco         = "";
        this.nss                = "";
    }
    
    public DependienteDP(String datos){
        StringTokenizer st   = new StringTokenizer(datos,"_");
        this.nombre          = st.nextToken();
        this.fechaNacimiento = st.nextToken();
        this.sexo            = st.nextToken();
        this.parentesco      = st.nextToken();
        this.nss             = st.nextToken();
    }
    
    // Accesors o geter's
    public String getNmbre(){
        return this.nombre;
    }
    
    public String getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    
    public String getSexo(){
        return this.sexo;
    }
    
    public String getParentesco(){
        return this.parentesco;
    }

    public String getNss(){
        return this.nss;
    }
    
    // Mutators o seter's
    public void setNombre(String name){
        this.nombre = name;
    }
    
    public void setFechaNacimiento(String nss){
        this.fechaNacimiento = nss;
    }
    
    public void setSexo(String sex){
        this.sexo = sex;
    }
    
    public void setParentesco(String kinship){
        this.parentesco = kinship;
    }

    public void setNss(String ssn){
        this.nss = ssn;
    }
    
    @Override
    public String toString(){
        //System.out.println("toString Nombre: " + this.nombre + " FechaNacimiento: " + this.fechaNacimiento + " Sexo: " + this.sexo + " Parentesco: " + this.parentesco + " Nss: " + this.nss);
        return this.nombre + "_" + this.fechaNacimiento + "_" + this.sexo + "_" + this.parentesco  +  "_"  +  this.nss;
    }
    
    public String toStringSql(){
        System.out.println("toStringSql Nombre: " + this.nombre + " FechaNacimiento: " + this.fechaNacimiento + " Sexo: " + this.sexo + " Parentesco: " + this.parentesco + " Nss: " + this.nss);
        return "'" + this.nombre + "','" + this.fechaNacimiento + "','" + this.sexo + "','" + this.parentesco + "','" + this.nss + "'";
    }
}