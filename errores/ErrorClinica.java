/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.errores;

public class ErrorClinica extends Exception {// se llama a la clase padre exception
    public ErrorClinica(String error){
        super("Lo siento, fallo en el sistema: " + error);
    }
}
