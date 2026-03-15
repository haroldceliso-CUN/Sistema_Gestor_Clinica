/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.persistencia;

import java.io.*;
import java.util.ArrayList;
import clinica.citas.Cita;
import clinica.persona.*;
import clinica.logica.GestorClinica;

public class ManejadorDatos{
    //nombre del archivo de texto
    private static final String ARCHIVO = "RegistroCitas.txt";

    // escribir datos 
    public static void actualizarArchivo(ArrayList<Cita> listaCitas){
        try (BufferedWriter bw= new BufferedWriter(new FileWriter(ARCHIVO, false))) {
            for (Cita c :listaCitas){
                //id;fecha;docPac;nomPac;diagPac;docMed;nomMed;espMed //
                String fila =c.getIdCita() + ";" + 
                             c.getFechaCita() + ";" + 
                             c.getPaciente().getDocumento() + ";" + 
                             c.getPaciente().getNombre() + ";" + 
                             c.getPaciente().getDiagnostico() + ";" + 
                             c.getMedico().getDocumento() + ";" + 
                             c.getMedico().getNombre() + ";" + 
                             c.getMedico().getEspecialidad();
                bw.write(fila);
                bw.newLine();
            }
        } catch(IOException e){
            System.out.println("error al escribir en el archivo") ;
        }
    }

    //lee el archvo y recupera la informacion
    public static void cargarDatos(GestorClinica gestor){
        File f= new File(ARCHIVO);
        if(!f.exists()) return; //si no hay archivo aun, no se hace nada //

        try (BufferedReader br= new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea =br.readLine()) != null){
                String[] datos= linea.split(";");
                //se verifica que tenga las 8 columnas
                if(datos.length == 8) {
                    //objetos temporales para reconstrir lista con los datos reales
                    Paciente p = new Paciente(datos[3], datos[2], datos[4]);
                    Medico m = new Medico(datos[6], datos[5], datos[7]);
                    
                    //solo agregamos al paciente si no esta ya en la lista del gestor
                    if (gestor.buscarPaciente(p.getDocumento())== null){
                        gestor.listaPacientes.add(p);
                    }
                    
                    Cita c = new Cita(datos[0], p, m, datos[1]);
                    gestor.listaCitas.add(c);
                }
            }
        }catch(Exception e){
            System.out.println("error cargando los datos guardados");
        }
    }
}