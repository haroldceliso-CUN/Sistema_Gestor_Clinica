/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.base;

// Clase abstracta que guarda a medico y pacente
public abstract class Persona {
    protected String nombre;
    protected String documento;
    // constrctor que recibe datos al crear instancia
    public Persona(String nombre, String documento) {
        this.nombre = nombre;
        this.documento = documento; //
    }

    // permite crear el rol del medico o paciente
    public abstract void identificarRol();
    
    public String getNombre() { return nombre; } //
}