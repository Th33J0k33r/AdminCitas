import AdminCitas.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static AdminCitas.Medico.altaMedico;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static String[] menuOpt = {"1. Dar de alta doctores", "2. Dar de alta pacientes", "3. Crear una cita", "5. Salir"};
    static String myUser = "admin";
    static String myPassword = "admin321";
    static HashMap<String, Medico> medicos = new HashMap<String, Medico>();
    static HashMap<String, Paciente> pacientes = new HashMap<String, Paciente>();
    static HashMap<String, Cita> citas = new HashMap<String, Cita>();
    int selectedOpt;
    public static void main(String[] args) throws IOException {
        System.out.println("Bienvenido al sistema de administracion de consultas");
        String iUser = "";
        String iPass = "";
        while (iUser.equals(myUser)==false || iPass.equals(myPassword)==false) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingresa tu usuario:");
            iUser = sc.nextLine();
            System.out.println("Ingresa tu password:");
            iPass = sc.nextLine();
            if (iUser != myUser && iPass != myPassword) {
                System.out.println("Credenciales incorrectas, intente de nuevo");
            }
        }

        System.out.println("Por favor selecciona una opcion: ");
        printMenu();
        Scanner sc = new Scanner(System.in);
        int selectedOpt = 0;
        while (selectedOpt != 5) {
            selectedOpt = sc.nextInt();
            switch (selectedOpt) {
                case 1:
                altaMedico();
            }
        }
    }
    public static void printMenu () {
        for (int i = 0; i <= 3; i++) {
            System.out.println(menuOpt[i]);
        }
    }
}