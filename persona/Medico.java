/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.persona;

import clinica.base.Persona; 

   //esta clase se extiende de persona//
public class Medico extends Persona { 
    private String especialidad;

    public Medico(String nombre,String documento, String especialidad){
        super(nombre, documento); // manda los datos al constructor del padre
        this.especialidad =especialidad;
    }
    
    
    //
    @Override
    public void identificarRol(){
       System.out.println("Este es el Medico encargado.");
    }
    
//
    public String getEspecialidad() { 
        return especialidad; 
    }
    
    public String getDocumento(){ 
        return documento; 
    }
}