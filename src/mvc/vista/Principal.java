/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista;

import mvc.controlador.ControladorAlquilerVehiculos;
import mvc.modelo.ModeloAlquilerVehiculos;

/**
 *
 * @author Felipon
 */
public class Principal {

    public static void main(String[] args) {
        IUTextual vista = new IUTextual();
        ModeloAlquilerVehiculos modelo = new ModeloAlquilerVehiculos();
        ControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(modelo, vista);
        controlador.comenzar();
    }
}
