/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica.utilidades;

import clinica.errores.ErrorClinica;

public class Validador {

    // valida que no metan numeros en el nombre
    public static void validarNombre(String nombre) throws ErrorClinica{
        if(!nombre.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$")) {
            throw new ErrorClinica("El nombre no puede tener numeros ni simbolos raros.");
        }
    }
    // evita los caracteres invalidos
    public static void validarDocumento(String doc)throws ErrorClinica {
        if (!doc.matches("^[0-9]+$")){
            throw new ErrorClinica("El documento solo debe tener numeros enteros.");
        }
    }
    // se prefiere isar el formato fecha DD/MM/AAA//
    public static void validarFecha(String fecha) throws ErrorClinica {
        if(!fecha.matches("^\\d{2}/\\d{2}/\\d{4}$")){
            throw new ErrorClinica("La fecha debe ser formato DD/MM/AAAA.");
        }
    }
}
