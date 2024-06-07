package AdminCitas;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static AdminCitas.Medico.printDrs;
import static AdminCitas.Paciente.printPac;

public class Cita {

    String fechaHora;
    String motivo;
    Medico medico;
    Paciente paciente;
    static String fileName = "Citas.txt";
    private static HashMap<String,Cita> citas = new HashMap<String, Cita>();
    private static HashMap<String,Medico> medicos = new HashMap<String, Medico>();
    private static HashMap<String,Paciente> pacientes = new HashMap<String, Paciente>();

    public Cita(String fechaHora, String motivo, Medico medico, Paciente paciente) {
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

    public static void saveCitas (){
        try(FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outF = new PrintWriter(bw))
        {
            for (Map.Entry<String, Cita> entry : citas.entrySet()) {
                String k = entry.getKey();
                Cita v = entry.getValue();
                String iFechayHora = v.getFechaHora();
                String iMotivo = v.getMotivo();
                String iNombreDr = v.getMedico().nombre;
                String iEspDr = v.getMedico().especialidad;
                String iNombrePac = v.getPaciente().nombre;
                String iAlergiasPac = v.getPaciente().alergias;
                outF.println(k + ", " + iFechayHora + ", " + iMotivo + ", " + iNombreDr + ", " + iEspDr + ", " + iNombrePac + ", " +iAlergiasPac  + "\r");
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
            if (rec.length == 7){
                Medico iMed = new Medico(rec[3], rec[4]);
                Paciente iPac = new Paciente(rec[5], rec[6]);
                Cita iCita = new Cita (rec[1], rec[2],iMed, iPac);
                citas.put(rec[0], iCita);
            }
        }
    }

    public static void altaCita() throws IOException {
        load();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id de la cita: ");
        String iID = sc.nextLine();
        System.out.println("Ingrese la hora de la cita: ");
        String iFechayHora = sc.nextLine();
        System.out.println("Ingrese el motivo de la cita: ");
        String iMotivo = sc.nextLine();
        System.out.println("Seleccione el identificado del medico: ");
        medicos = printDrs();
        String iMed = sc.nextLine();
        Medico medico1 = new Medico (medicos.get(iMed).nombre, medicos.get(iMed).especialidad);
        System.out.println("Seleccione el identificado de un paciente: ");
        pacientes = printPac();
        String iPac = sc.nextLine();
        Paciente paciente1 = pacientes.get(iPac);
        Cita iCita = new Cita(iFechayHora,iMotivo, medico1,paciente1);
        citas.put(iID, iCita);
        saveCitas();
        printCita(iID);
    }

    public static void printCita(String iID) throws IOException {
        load();
        Cita c = citas.get(iID);
        System.out.println("Se genero la cita:");
        System.out.println("=================================");
        System.out.println("ID: " + iID);
        System.out.println("Fecha/Hora: " + c.getFechaHora());
        System.out.println("Motivo: " + c.getMotivo());
        System.out.println("Medico: " + c.getMedico().getNombre());
        System.out.println("Paciente: " + c.getPaciente().getNombre());
        System.out.println("=================================");
    }
}
