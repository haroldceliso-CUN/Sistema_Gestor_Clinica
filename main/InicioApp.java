/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.main;

import clinica.persona.*;
import clinica.logica.GestorClinica;
import clinica.config.AjustesGlobales;
import clinica.errores.ErrorClinica;
import clinica.utilidades.Validador;
import java.util.Scanner;

public class InicioApp {
    public static void main(String[] args){
        //se instancia el gestor principal
        GestorClinica app = new GestorClinica();
        clinica.persistencia.ManejadorDatos.cargarDatos(app);
        Scanner leer = new Scanner(System.in);
        int rol = 0;
        int contadorCitas = 1; //contador unico para que los codigos de cita no se rpitan
            // titulo del sistema
        System.out.println("==================================================");
        System.out.println(" BIENVENIDO A "+ AjustesGlobales.NOMBRE_SISTEMA);
        System.out.println("===================================================");

        do {
            System.out.println("\nComo deseas ingresar al sistema?");
            System.out.println("1. Administrativo") ;
            System.out.println("2. Paciente");
            System.out.println("3. Salir del programa");
            System.out.print("Opcion: ");
            
            try {
                // usando parseint para evitar problemas con el enter del scanner
                rol= Integer.parseInt(leer.nextLine());

                //modulo administrativo
                if (rol== 1){
                    int opcFunc = 0;
                    do {
                        System.out.println ("\n-------- PANEL DE FUNCIONARIO --------");
                        System.out.println("1. Agendar cita");
                        System.out.println("2. Cancelar cita");
                        System.out.println("3. Admisionar paciente");
                        System.out.println("4. Ver reporte de todas las citas");
                        System.out.println("5. Volver al menu principal");
                        System.out.print("Elige: ");
                        opcFunc = Integer.parseInt(leer.nextLine());
 
                        try{
                            switch (opcFunc) {
                                case 1:
                                    System.out.print("Documento del Paciente: ");
                                    String doc = leer.nextLine();
                                    Validador.validarDocumento(doc);

                                    // verificar si el paciente ya esta en la lista //
                                    Paciente pac = app.buscarPaciente(doc);
                                    if(pac== null) {
                                        // si no existe, toca crearlo antes de la cita
                                        System.out.print("Nombre completo del paciente: ");
                                        String nom= leer.nextLine();
                                        Validador.validarNombre(nom);
                                        
                                        System.out.print("Motivo de consulta: ");
                                        String motivo = leer.nextLine();
                                        
                                        pac= new Paciente(nom, doc, motivo);
                                        app.listaPacientes.add(pac);
                                    }

                                    System.out.print("Nombre del Medico: ");
                                    String med = leer.nextLine();
                                    Validador.validarNombre(med);

                                    System.out.print("Fecha (DD/MM/AAAA): ");
                                    String fecha = leer.nextLine();
                                    Validador.validarFecha(fecha);

                                    //creamos el objeto medico y el codigo de cita
                                    Medico m= new Medico(med, "999", "General");
                                    String codigoCita= "C-"+ contadorCitas++;

                                    app.agendarNueva(codigoCita, pac, m, fecha);
                                    pac.agregarAlHistorial("Cita programada el "+ fecha + " con Dr. "+ med);
                                    System.out.println("Cita creada con codigo: "+ codigoCita);
                                    break;

                                case 2:
                                    System.out.print("Codigo de la cita a cancelar (Ej. C-1): ");
                                    String idC= leer.nextLine();
                                    app.cancelarCita(idC);//se llama al metodo del gestor
                                    break;

                                case 3:
                                    System.out.print("Documento del paciente a admisionar: ");
                                    String docAd= leer.nextLine();
                                    Validador.validarDocumento(docAd);
                                    app.admisionarPaciente(docAd);
                                    break;

                                case 4:
                                    System.out.println("\n------- LISTADO GENERAL DE CITAS -------");
                                    app.mostrarCitas();
                                    break;
                            }
                        }catch (ErrorClinica e){
                            //se atrapa los errores del validador
                            System.out.println("ERROR DE VALIDACION: " + e.getMessage());
                        }

                    } while(opcFunc != 5);
                }
                
                //menu del paciente
                else if(rol == 2){
                    System.out.print("\nPara ingresar, ingresa su numero de documento: ");
                    String docIngreso= leer.nextLine();
                    
                    try{
                        Validador.validarDocumento(docIngreso);
                        Paciente pacActual= app.buscarPaciente(docIngreso);
                        
                        //si no existe, se le ofrece regisrarse
                        if(pacActual== null) {
                            System.out.print("No estas en el sistema."
                                    + " Deseas registrarte ahora? (S/N): ");
                            if (leer.nextLine().equalsIgnoreCase("S")) {
                                System.out.print("Nombre completo: ");
                                String nomN = leer.nextLine();
                                Validador.validarNombre(nomN);
                                pacActual = new Paciente(nomN, docIngreso,"Revision General");
                                app.listaPacientes.add(pacActual);
                                System.out.println("Registro exitoso... Ya puedes ingresar.");
                            } else {
                                System.out.println("Volviendo al menu principal...");
                            }
                        }

                        // si el paciente ya existe , se muestra el menu
                        if(pacActual != null) {
                            int opcPac= 0;
                            do {
                                System.out.println("\n==============================================");
                                System.out.println(" PORTAL DEL PACIENTE: "+ pacActual.getNombre().toUpperCase());
                                System.out.println("==============================================");
                                System.out.println("1. Agendar una nueva cita");
                                System.out.println("2. Ver mis citas programadas");
                                System.out.println("3. Cancelar cita");
                                System.out.println("4. Ver mi historial medico");
                                System.out.println("5. Volver al inicio");
                                System.out.print("Elige: ");
                                opcPac= Integer.parseInt(leer.nextLine());

                                switch(opcPac)   {
                                    case 1:
                                        System.out.print("Fecha deseada (DD/MM/AAAA): ");
                                        String fecha = leer.nextLine();
                                        Validador.validarFecha(fecha);
                                        
                                        //medico por defecto para el ejercicio
                                        Medico medDef = new Medico("Dr. Especialista", "999", "General");
                                        String codigoCita = "C-" + contadorCitas++;
                                        
                                        app.agendarNueva(codigoCita,pacActual, medDef, fecha);
                                        pacActual.agregarAlHistorial("Agendo cita para el "+ fecha);
                                        System.out.println("Cita confirmada con codigo: "+ codigoCita) ;
                                        break;                                    
                                    case 2:
                                        System.out.println("\n--- TUS PROXIMAS CITAS ---");
                                        app.mostrarCitasPaciente(docIngreso);
                                        break;
                                    case 3:
                                        System.out.print("Codigo de la cita a cancelar (ejemp. C-1): ");
                                        String idC= leer.nextLine();
                                        app.cancelarCita(idC);
                                        pacActual.agregarAlHistorial("Cancelo la cita "+ idC);
                                        break;
                                    case 4:
                                        pacActual.verHistorial(); // imprime las notas guardadas
                                        break;
                                }
                            } while(opcPac != 5);
                        }
                    }catch (ErrorClinica e){
                        System.out.println("ERROR: " + e.getMessage());
                    }
                }

            }catch (NumberFormatException e) {
                // si se meten letras en el menu imprime esto
                System.out.println("Error: Por favor ingresa una opcion numerica valida.") ;
            }

        }while(rol != 3); //ciclo hasta que elijan la opcion salir
        
        System.out.println("Cerrando el sistema .....  .....  Hasta luego");
    }
}