package AdminCitas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Cita {

    String id;
    String fechaHora;
    String motivo;
    Medico medico;
    Paciente paciente;
    static String fileName = "Citas.txt";
    private static HashMap<String,Cita> citas = new HashMap<String, Cita>();

    public Cita(String id, String fechaHora, String motivo, Medico medico, Paciente paciente) {
        setId (id);
        setFechaHora(fechaHora);
        setMotivo(motivo);
        setMedico(medico);
        setPaciente(paciente);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void saveCita (){
        try(FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outF = new PrintWriter(bw))
        {
            for (Map.Entry<String, Cita> entry : citas.entrySet()) {
                String k = entry.getKey();
                Cita v = entry.getValue();
                outF.print(k + ", " + v + "\r");
                outF.close();
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public void printCita (){
        System.out.println("=================================");
        System.out.println("ID: " + getId());
        System.out.println("Fecha/Hora: " + getFechaHora());
        System.out.println("Motivo: " + getMotivo());
        System.out.println("AdmonCitas.Medico: " + getMedico().getNombre());
        System.out.println("AdmonCitas.Paciente: " + getPaciente().getNombre());
        System.out.println("=================================");
    }
}
