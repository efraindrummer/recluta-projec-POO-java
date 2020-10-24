import java.lang.*;
import java.io.*;
import java.util.*;

public class ManejoRecluta {
static RandomAccessFile archivo, archTemp;
static Scanner stdIn = new Scanner(System.in);
static String input;

//========================================================================
public static boolean Abrir(int na, String nomArch, String modo){
try {
if(na == 1)
   archivo = new RandomAccessFile(nomArch, modo);
else
   archTemp = new RandomAccessFile(nomArch, modo);
   return true;
} catch(FileNotFoundException e) {
   System.out.println("No existe archivo");
   return false;
}
}

//========================================================================
private static boolean Escribe(RandomAccessFile archivo, Recluta r) {
try {
   archivo.writeLong(r.getCurp());
   archivo.writeUTF(r.getNombre());
   archivo.writeUTF(r.getApellido());
   archivo.writeUTF(r.getSexo());
   return true;
} catch(IOException e) {
   System.out.println("IOE: "+e);
   return false;
}
} //Escribe Archivo

//========================================================================
private static void Imprime(Recluta r) {
   System.out.print(r.getCurp()+",");
   System.out.print(r.getNombre()+",");
   System.out.print(r.getApellido()+",");
   System.out.println(r.getSexo());
} //Imprime

//========================================================================
public static String getCadena(String sdato, int n){
  if((sdato.length() > 0) && ( sdato.length() < n)) {
    for(int i = sdato.length(); i < n;i++)
    sdato += " ";
  }
  else {
    if(sdato.length() > n)
      sdato = sdato.substring(0,n);
  }
return sdato;
} //getCadena

//========================================================================
public static void Agregar(RandomAccessFile archivo) {
Recluta r = new Recluta();
try {
   archivo.seek(archivo.length());//Se agrega al final del archivo
   System.out.print("Numero de curp: ");
   input = stdIn.nextLine();
   if(r.getCurp() > 0){
      System.out.print("Nombre del alumno: ");
      r.setNombre(getCadena(stdIn.nextLine(),30));//String nombreAlumno = getCadena(stdIn.nextLine(),30);
      System.out.print("Apellido: ");
      r.setApellido(getCadena(stdIn.nextLine(), 40));//String email = getCadena(stdIn.nextLine(), 40);
      System.out.print("Sexo: ");
      input = stdIn.nextLine();
      r.setSexo(getCadena(stdIn.nextLine(), 40));
      //r.setSexo(Long.parseLong(input));//long telefono = Long.parseLong(input);
      Escribe(archivo, r);
   }
} catch(IOException e){System.out.println("IOException "+e);}
}//Agregar

//========================================================================
public static void Buscar(RandomAccessFile archivo) throws IOException {
long curp = 0;
Recluta r = new Recluta();
try {
   System.out.print("Numero de curp: ");
   input = stdIn.nextLine();
   curp = Integer.parseInt(input);
   if(curp > 0) {
   archivo.seek(0);
   while(curp != r.getCurp()){
      r.setCurp(archivo.readLong());//nc=archivo.readInt();
      r.setNombre(archivo.readUTF());//nombreAlumno=archivo.readUTF();
      r.setApellido(archivo.readUTF());//email=archivo.readUTF();
      r.setSexo(archivo.readUTF());//telefono=archivo.readLong();
      archivo.seek(archivo.getFilePointer());
   }//while
   Imprime(r);
}
} catch (EOFException e) {//Encontro fin de archivo
System.out.println("No se encontro el numero de curp: " + curp);
}
}//Buscar

//========================================================================
public static void Mostrar(RandomAccessFile archivo) throws IOException {
Recluta  r = new Recluta();
try {
    archivo.seek(0);
    while(true){
    	r.setCurp(archivo.readLong());//nc=archivo.readInt();
      r.setNombre(archivo.readUTF());//nom=archivo.readUTF();
      r.setApellido(archivo.readUTF());//em=archivo.readUTF();
      r.setSexo(archivo.readUTF());//tel=archivo.readLong();
      //if (a.getNumCtl()!= -1){
	      Imprime(r);
      //}
        archivo.seek(archivo.getFilePointer());
    }//while
} catch (EOFException e) {//Encontro fin de archivo
    System.out.println("Fin de archivo");
}
}//Mostrar

//========================================================================
public static void Editar(RandomAccessFile archivo) throws IOException {
long curp = 0;//, nc = 0;
long posicion, tel;//, telefono = 0, tel;
//String S, nombreAlumno = "", email = "";
Recluta r = new Recluta();
try {
System.out.print("Numero de curp: ");
input = stdIn.nextLine();
curp = Integer.parseInt(input);
if(curp > 0) {
    archivo.seek(0);
    do {
        posicion = archivo.getFilePointer();
        archivo.seek(posicion);
        r.setCurp(archivo.readLong());//nc=archivo.readInt();
        r.setNombre(archivo.readUTF());//nombreAlumno=archivo.readUTF();
        r.setApellido(archivo.readUTF());// email= archivo.readUTF();
        r.setSexo(archivo.readUTF());//telefono = archivo.readLong();
    }while(curp != r.getCurp());
    if(curp == r.getCurp()) {
        Imprime(r);
        System.out.print("Nombre del recluta: ");
        input = getCadena(stdIn.nextLine(), 30);
        r.setNombre((input.length()>0)?input:r.getNombre());//nombreAlumno=(S.length()>0)?S:nombreAlumno;
        
        System.out.print("Apellido ");
        input = getCadena(stdIn.nextLine(), 40);
        r.setApellido((input.length()>0)?input:r.getApellido());//email=(S.length()>0)?S:email;
        
        System.out.print("sexo: ");
        input = getCadena(stdIn.nextLine(), 10);
        r.setSexo((input.length()>0)?input:r.getSexo());
        //r.setSexo((sexo>0)?sexo:r.getSexo());//telefono=(tel>0)?tel:telefono;
        archivo.seek(posicion);
        Escribe(archivo, r);
    }
  }
} catch (EOFException e) {// Encontro fin de archivo
System.out.println("No se encontro numero de curp: "+curp);
}
}//Editar

//========================================================================
public static void ElimLogica(RandomAccessFile archivo) throws IOException {
long curp = 0;//, nc = 0;
long posicion;//, telefono = 0, tel;
//String S, nombreAlumno = "", email = "";
Recluta r= new Recluta();
try {
    System.out.print("Numero de curp: ");
    input = stdIn.nextLine();
    curp = Integer.parseInt(input);
    if(curp > 0) {
        archivo.seek(0);
        do {
           posicion = archivo.getFilePointer();
           archivo.seek(posicion);
           r.setCurp(archivo.readLong());//nc=archivo.readInt();
           r.setNombre(archivo.readUTF());//nombreAlumno=archivo.readUTF();
           r.setApellido(archivo.readUTF());// email= archivo.readUTF();
           r.setSexo(archivo.readUTF());//telefono = archivo.readLong();
        }while(curp != r.getCurp());
        if(curp == r.getCurp()) {
           archivo.seek(posicion);
           r.setCurp(archivo.readLong());//nc=archivo.readInt();
           r.setNombre(archivo.readUTF());//nombreAlumno=archivo.readUTF();
           r.setApellido(archivo.readUTF());// email= archivo.readUTF();
           r.setSexo(archivo.readUTF());//telefono = archivo.readLong();
           Imprime(r);
           archivo.seek(posicion);
           r.setCurp(-1);
            Escribe(archivo, r);
        }
    }
} catch (EOFException e) {// Encontro fin de archivo
    System.out.println("No se encontro numero de curp: "+curp);
}
}//ElimLogica

//========================================================================
public static void pasaReg(RandomAccessFile archivo, RandomAccessFile archTemp) {
//int nc;
//long tel, pos, posT;
//String nombreA = "", email = "";
boolean correcto;
Recluta r = new Recluta();
try {
   if(Abrir(1, "Codigo804.dat", "r") && Abrir(2, "archTmp.dat", "rw")) {
        archivo.seek(0);
        archTemp.seek(0);
        while (true) {
           r.setCurp(archivo.readLong());//nc=archivo.readInt();
           r.setNombre(archivo.readUTF());//nombreA=archivo.readUTF();
           r.setApellido(archivo.readUTF());// email= archivo.readUTF();
           r.setSexo(archivo.readUTF());//tel = archivo.readLong();
           if(r.getCurp() != -1){ //graba al archivo temporal
               Escribe(archTemp, r);
               Imprime(r);
            }
            else { //Solo muestra en pantalla
               System.out.print("Registro eliminado: ");
               Imprime(r);
            }
        }//fin de while
    }//fin de if abrir
} //fin de try
catch(IOException ve){
   System.out.println("Error I/O: "+ve);
}
try{
   System.out.println("Cerrando archivos");
   archivo.close();
   archTemp.close();
}catch(IOException ve){
   System.out.println("Ocurrio error: "+ve);
}
}//elimFisica

//========================================================================
public static void elimArc(File file, File ftemp) {
boolean correcto;
if (file.exists()) {
   System.out.println("Codigo804.dat= "+ file.length());
   correcto = file.delete();
   if(correcto){
      System.out.println("Codigo804.dat eliminado");
      System.out.println("archTem.dat= "+ ftemp.length());
      correcto = ftemp.renameTo(file);
      if (correcto){
         System.out.println("archTem renombrado");
         System.out.println("Codigo804.dat="+ file.length());
      }
      else
         System.out.println("No se renombro temporal");
   }
	else
      System.out.println("NO se elimino Codigo804.dat");
}
}//elimArc

/========================================================================
public static void main(String[] args) throws IOException {
int opcion = 7;
File file = new File("Codigo804.dat");
File ftemp = new File("archTmp.dat");

do {
System.out.print("\n\t1)Agregar\n\t2)Buscar\n\t3)Editar\n\t4)Mostrar");
System.out.println("\n\t5)Eliminar Fis\n\t6)EliminarLog\n\t7)Salir");
System.out.print("Elige opcion: ");
input = stdIn.nextLine();
opcion = Integer.parseInt(input);
switch(opcion){
case 1:
if(Abrir(1, "Codigo804.dat", "rw")){
	Agregar(archivo);
	archivo.close();
}
break;
case 2:
if(Abrir(1, "Codigo804.dat", "r")){
 	Buscar(archivo);
	archivo.close();
}
break;
case 3:
if(Abrir(1, "Codigo804.dat", "rw")){
	 Editar(archivo);
	archivo.close();
}
break;
case 4:
if(Abrir(1, "Codigo804.dat", "r")){
	Mostrar(archivo);
	archivo.close();
}
break;
case 5:
 if(Abrir(1, "Codigo804.dat", "rw") && Abrir(2, "archTmp.dat", "rw")){
	pasaReg(archivo, archTemp);
	archivo.close(); archTemp.close();
   input = stdIn.nextLine();
 	elimArc(file, ftemp);
}
break;
case 6:
if(Abrir(1, "Codigo804.dat", "rw")){
 	ElimLogica(archivo);
	archivo.close();
}
break;
} //switch
} while(opcion != 7);
System.out.println("Bye");
}//main
}// Codigo804

