package AdminCitas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Medico {

    String id;
    String nombre;
    String especialidad;
    static String fileName = "Medicos.txt";
    private static HashMap<String,Medico> medicos = new HashMap<String, Medico>();;

    public Medico (String id, String nombre, String especialidad) throws IOException {
        setId(id);
        setNombre(nombre);
        setEspecialidad(especialidad);
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public static void saveDoctors (){
        try(FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outF = new PrintWriter(bw))
        {
            for (Map.Entry<String, Medico> entry : medicos.entrySet()) {
                String k = entry.getKey();
                Medico v = entry.getValue();
                outF.print(k + ", " + v + "\r");
                outF.close();
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
