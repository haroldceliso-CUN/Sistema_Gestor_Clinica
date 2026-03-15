/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.logica; //

import clinica.persona.*;
import clinica.citas.Cita;
import clinica.interfaces.IGestionable;
import clinica.persistencia.ManejadorDatos;
import clinica.errores.ErrorClinica;
import java.util.ArrayList;

//se cumple los metodos definidos en la interfaz
public class GestorClinica implements IGestionable {
    /// arraylist para el dinamismo de los datos 
    public ArrayList<Cita> listaCitas = new ArrayList<>();
    public ArrayList<Paciente> listaPacientes = new ArrayList<>();
    
    //se crea elobjeto cita y se gusrda en txt.
    public void agendarNueva(String id, Paciente p, Medico m, String fecha) {
        Cita c = new Cita(id, p, m, fecha);
        listaCitas.add(c);
        ManejadorDatos.actualizarArchivo(listaCitas); 
    }
    
    // para cancelarse se recorre lista, si no la encuentra, se lanza la exepcion
    public void cancelarCita(String idBuscar) throws ErrorClinica{
        boolean encontrada= false;
        for (int i= 0; i< listaCitas.size(); i++){
            if (listaCitas.get(i).getIdCita().equalsIgnoreCase(idBuscar)){
                listaCitas.remove(i);
                ManejadorDatos.actualizarArchivo(listaCitas);
                System.out.println("Cita cancelada con exito.");
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            throw new ErrorClinica("No se encontro una cita con ese codigo.");
        }
    }
    // metodo para el funcionario donde se busca lal paciente
    public void admisionarPaciente(String doc) throws ErrorClinica {
        Paciente p = buscarPaciente(doc);
        if (p !=null) {
            p.agregarAlHistorial("Admision registrada en recepcion. Listo para pasar a sala.");
            System.out.println("Paciente admisionado correctamente.");
        } else {
            throw new ErrorClinica("El paciente no esta registrado en el sistema.");
        }
    }

    
    
    // encontrar paciente por doc
    public Paciente buscarPaciente(String doc) {
        for (Paciente p : listaPacientes){
            if (p.getDocumento().equals(doc)) {
                return p;
            }
        }
        return null;
    }

    @Override public void registrarCita() { }
    
    //recorre la lista y usa metodo d ela clase cita para archivar
    @Override
    public void mostrarCitas() {
        if (listaCitas.isEmpty()) {
            System.out.println("No hay citas programadas.");
            return;
        }
        for (Cita c: listaCitas){
            System.out.println(c.prepararParaArchivo());
        }
    }

    
    //Filtra las citas para que el paciente solo vea las suyas
    public void mostrarCitasPaciente(String doc){
        boolean tieneCitas = false;
        for (Cita c : listaCitas) {
            if (c.getPaciente().getDocumento().equals(doc)) {
                System.out.println(c.prepararParaArchivo());
                tieneCitas = true;
            } //
        }
        if(!tieneCitas){
            System.out.println("No tienes citas registradas actualmente.");
        }
    }
}