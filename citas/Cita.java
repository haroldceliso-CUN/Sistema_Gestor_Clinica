/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.citas;

import clinica.persona.*;

public class Cita {
    // atributos privados para que no puedan modificarse
    private String idCita; // codigo identificador de la cita
    private Paciente paciente; //trae nombre, doc
    private Medico medico;
    private String fechaCita;//se guarda la fecha 
// guardar los objetos anteriores
    public Cita(String idCita, Paciente p, Medico m, String f) {
        this.idCita = idCita;
        this.paciente = p;
        this.medico = m;
        this.fechaCita = f;
    }

    public String getIdCita() { return idCita; }//ayuda a encontrar la cita por el codigo 
    
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public String getFechaCita() { return fechaCita; }
// imprimirpor consola
    public String prepararParaArchivo() {
        return "[" + idCita + "] FECHA: " + fechaCita + " | PACIENTE: " + paciente.getNombre() + " | MEDICO: " + medico.getNombre();
    }
}
