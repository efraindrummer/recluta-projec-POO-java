import java.lang.*;
import java.io.*;
import java.util.*;
public class Principal {
//========================================================================
public static void main(String[] args) throws IOException {
Scanner stdIn = new Scanner(System.in);
String input;
int opcion = 7;
File file = new File("Codigo804.dat");
File ftemp = new File("archTmp.dat");
ManejoRecluta ma = new ManejoRecluta();
do {
System.out.print("\n\t1)Agregar\n\t2)Buscar\n\t3)Editar\n\t4)Mostrar");
System.out.println("\n\t5)Eliminar Fis\n\t6)EliminarLog\n\t7)Salir");
System.out.print("Elige opcion: ");
input = stdIn.nextLine();
opcion = Integer.parseInt(input);
switch(opcion){
case 1:
if(ma.Abrir(1, "Codigo804.dat", "rw")){
	ma.Agregar(ma.archivo);
	ma.archivo.close();
}
break;
case 2:
if(ma.Abrir(1, "Codigo804.dat", "r")){
 	ma.Buscar(ma.archivo);
	ma.archivo.close();
}
break;
case 3:
if(ma.Abrir(1, "Codigo804.dat", "rw")){
	 ma.Editar(ma.archivo);
	ma.archivo.close();
}
break;
case 4:
if(ma.Abrir(1, "Codigo804.dat", "r")){
	ma.Mostrar(ma.archivo);
	ma.archivo.close();
}
break;
case 5:
 if(ma.Abrir(1, "Codigo804.dat", "rw") && ma.Abrir(2, "archTmp.dat", "rw")){
	ma.pasaReg(ma.archivo, ma.archTemp);
	ma.archivo.close();
   ma.archTemp.close();
   input = stdIn.nextLine();
 	ma.elimArc(file, ftemp);
}
break;
case 6:
if(ma.Abrir(1, "Codigo804.dat", "rw")){
 	ma.ElimLogica(ma.archivo);
	ma.archivo.close();
}
break;
} //switch
} while(opcion != 7);
System.out.println("Bye");
}//main
}// Codigo804
