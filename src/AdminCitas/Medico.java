package AdminCitas;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Medico {

    String nombre;
    String especialidad;
    static String fileName = "Medicos.txt";
    private static HashMap<String,Medico> medicos = new HashMap<String, Medico>();;

    public Medico (String nombre, String especialidad) throws IOException {
        setNombre(nombre);
        setEspecialidad(especialidad);
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public static void saveDoctors (){
        try(FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outF = new PrintWriter(bw))
        {
            for (Map.Entry<String, Medico> entry : medicos.entrySet()) {
                String k = entry.getKey();
                Medico v = entry.getValue();
                String iNombre = v.getNombre();
                String iEspecialidad = v.getEspecialidad();
                outF.println(k + ", " + iNombre + ", " + iEspecialidad + "\r");
                outF.close();
            }

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static void load() throws IOException {
        FileReader fr =  new FileReader(fileName);
        BufferedReader br = new BufferedReader (fr);
        String currLine;
        while((currLine = br.readLine()) != null){
            String [] rec = currLine.split(",",0);
            if (rec.length == 3){
                Medico iMed = new Medico (rec[1], rec[2]);
                medicos.put(rec[0], iMed);
            }
        }
    }

    public static void altaMedico() throws IOException {
        load();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del medico: ");
        String iID = sc.nextLine();
        System.out.println("Ingrese el nombre del medico: ");
        String iNombre = sc.nextLine();
        System.out.println("Ingrese la especialidad del medico: ");
        String iEspecialidad = sc.nextLine();
        Medico iMed = new Medico (iNombre,iEspecialidad );
        medicos.put(iID, iMed);
        saveDoctors();
        System.out.println("Se dio de alta al medico.");
    }

    public static HashMap<String,Medico> printDrs() throws IOException {
        load();
        System.out.println("Medicos disponibles:");
        medicos.forEach((k,v) -> System.out.println("Numero: {" + k + "} , Nombre: {" + v.getNombre() + " } , Especialidad: " + v.getEspecialidad() + "}"));
        return medicos;
    }
}
