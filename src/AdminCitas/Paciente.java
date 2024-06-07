package AdminCitas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Paciente {
    String id;
    String nombre;
    static String fileName = "Pacientes.txt";
    private static HashMap<String,Paciente> pacientes = new HashMap<String, Paciente>();

    public Paciente(String id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void savePacientes (){
        try(FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outF = new PrintWriter(bw))
        {
            for (Map.Entry<String, Paciente> entry : pacientes.entrySet()) {
                String k = entry.getKey();
                Paciente v = entry.getValue();
                outF.print(k + ", " + v + "\r");
                outF.close();
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
