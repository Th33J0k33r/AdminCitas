package AdminCitas;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Paciente {
    String nombre;
    String alergias;
    static String fileName = "Pacientes.txt";
    private static HashMap<String,Paciente> pacientes = new HashMap<String, Paciente>();

    public Paciente(String alergias, String nombre) {
        setAlergias(alergias);
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public static void savePacientes (){
        try(FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outF = new PrintWriter(bw))
        {
            for (Map.Entry<String, Paciente> entry : pacientes.entrySet()) {
                String k = entry.getKey();
                Paciente v = entry.getValue();
                String iNombre = v.getNombre();
                String iAlergias = v.getAlergias();
                outF.print(k + ", " + iNombre + ", " + iAlergias + "\r");
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
                Paciente iPac = new Paciente (rec[1], rec[2]);
                pacientes.put(rec[0], iPac);
            }
        }
    }

    public static void altaPaciente() throws IOException {
        load();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del paciente: ");
        String iID = sc.nextLine();
        System.out.println("Ingrese el nombre del paciente: ");
        String iNombre = sc.nextLine();
        System.out.println("Ingrese la alergia del paciente: ");
        String iAlergia = sc.nextLine();
        Paciente iPac = new Paciente (iNombre,iAlergia );
        pacientes.put(iID, iPac);
        savePacientes();
    }

    public static HashMap<String,Paciente> printPac() throws IOException {
        load();
        System.out.println("Pacientes disponibles:");
        pacientes.forEach((k,v) -> System.out.println("Numero: {" + k + "} , Nombre: {" + v.getNombre() + " } , Alergias: " + v.getAlergias() + "}"));
        return pacientes;
    }
}
