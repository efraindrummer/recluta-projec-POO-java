public class Recluta{
   private long curp;
   private String nombre;
   private String apellido;
   private String sexo;
   
   public Recluta(){
      long curp=0;
      String nombre="";
      String apellido="";
      String sexo="";
   }
   //constructor
   public Recluta(long curp, String nombre, String apellido, String sexo){
      this.curp=curp;
      this.nombre=nombre;
      this.apellido=apellido;
      this.sexo=sexo;
   }
   //metodos set
   public void setCurp(long curp){this.curp=curp;}
   public void setNombre(String nombre){this.nombre=nombre;}
   public void setApellido(String apellido){this.apellido=apellido;}
   public void setSexo(String sexo){this.sexo=sexo;}
   //metodo get de todos los atributos
   public void setTodo(long curp, String nombre, String apellido, String sexo){
      this.curp = curp;
      this.nombre = nombre;
      this.apellido = apellido;
      this.sexo = sexo;
   }//fin del set todo
   
   //meotodos get
   public long getCurp(){return curp;}
   public String getNombre(){return nombre;}
   public String getApellido(){return apellido;}
   public String getSexo(){return sexo;}
}