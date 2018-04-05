import java.util.StringTokenizer;

public class TrabajaEnDP{
    // Atributos de la clase
    private String horas, nss, nProyecto;
    
    // Constructores
    public TrabajaEnDP(){
        this.horas      = "";
        this.nss        = "";
        this.nProyecto  = "";
    }
    
    public TrabajaEnDP(String datos){
        StringTokenizer st  = new StringTokenizer(datos,"_");
        this.horas      = st.nextToken();
        this.nss        = st.nextToken();
        this.nProyecto  = st.nextToken();
    }
    
    // Accesors o geter's
    public String getHoras(){
        return this.horas;
    }
    
    public String getNss(){
        return this.nss;
    }
    
    public String getNProyecto(){
        return this.nProyecto;
    }
    
    // Mutators o seter's
    public void setHoras(String hours){
        this.horas = hours;
    }
    
    public void setNss(String ssn){
        this.nss = ssn;
    }
    
    public void setNProyecto(String nproject){
        this.nProyecto = nproject;
    }
    
    @Override
    public String toString(){
        //System.out.println("toString\thoras: " + this.horas + " Nss: " + this.nss + " NProyecto: " + this.nProyecto);
        return this.horas + "_" + this.nss + "_" + this.nProyecto;
    }
    
    public String toStringSql(){
        //System.out.println("toStringSQL\thoras: " + this.horas + " Nss: " + this.nss + " NProyecto: " + this.nProyecto);
        return "'" + this.horas + "','" + this.nss + "','" + this.nProyecto + "'";
    }
}