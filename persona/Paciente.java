/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.persona;

import clinica.base.Persona;
import java.util.ArrayList;

public class Paciente extends Persona {
    private String diagnostico;
    //historial dinamico con arraylist
    private ArrayList<String> historialMedico; 

    public Paciente(String nombre, String documento,String diagnostico) {
        super(nombre, documento);
        this.diagnostico = diagnostico;
        this.historialMedico = new ArrayList<>(); //se inicia vacio siempre
    }

    //metodo que llena el historial poco a poco/
    public void agregarAlHistorial(String nota){
        historialMedico.add(nota);
    }

    public void verHistorial(){
        System.out.println("\n-------- HISTORIAL MEDICO DE: "+ nombre.toUpperCase()+ " --------");
        // validamos si hay algo para mostrar con el isempty
        if (historialMedico.isEmpty()){
            System.out.println("El paciente no tiene registros anteriores.");
        } else{
            //se recorre lista con for each
            for (String nota : historialMedico) {
                System.out.println("-> "+ nota);
            }
        }
    }

    @Override
    public void identificarRol(){
        System.out.println("Este es un Paciente en el sistema.");
    }
    
    // getter usado para la busqueda en el gestor de la clinica
    public String getDocumento(){
        return documento;
    }
    
    public String getDiagnostico(){
        return diagnostico;
    }
}