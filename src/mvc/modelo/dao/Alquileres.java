/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvc.modelo.dao;

import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author Felipillo
 */
public class Alquileres {

    private final int MAX_ALQUILERES = 3;
    private Alquiler[] alquileres;

    public Alquileres() {
        alquileres = new Alquiler[MAX_ALQUILERES];
    }

    public Alquiler[] getAlquiler() {
        return alquileres.clone();
    }

    public void abrir(Cliente cliente, Vehiculo vehiculo) {
        int posicion = 0;
        boolean disponible = false;

        if (vehiculo.getDisponible()) {
            while (posicion < alquileres.length && !disponible) {
                
                if (alquileres[posicion] == null) {
                    disponible = true;
               
                } else {
                    posicion++;
                }
            }
            
        } else {
            throw new ExcepcionAlquilerVehiculos("El vehiculo no esta disponible");
        }
        
        if (disponible) {
            alquileres[posicion] = new Alquiler(cliente, vehiculo);
            vehiculo.setDisponible(false);

        } else {
            throw new ExcepcionAlquilerVehiculos("El registro de alquileres esta lleno. Se deben eliminar registros");
        }
    }

    public void cerrar(Cliente cliente, Vehiculo vehiculo) {
        int posicion = 0;
        boolean existe = false;

        while (posicion < alquileres.length && !existe) {
            if (alquileres[posicion] != null && alquileres[posicion].getCliente().getDni().equals(cliente.getDni()) && alquileres[posicion].getVehiculo().getMatricula().equals(vehiculo.getMatricula()) && alquileres[posicion].getDias() == 0) {
                existe = true;

            } else {
                posicion++;
            }
        }
        if (existe) {

            alquileres[posicion].close();
            vehiculo.setDisponible(true);

        } else {
            throw new ExcepcionAlquilerVehiculos("El alquiler que se desea cerrar no existe");
        }
    }
}