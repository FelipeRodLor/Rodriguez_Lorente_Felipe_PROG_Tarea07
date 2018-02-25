/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dao;

import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author Felipillo
 */
public class Clientes {

    private final int MAX_CLIENTES = 3;
    private Cliente[] clientes;

    public Clientes() {
        clientes = new Cliente[MAX_CLIENTES];
    }

    public Cliente[] getClientes() {
        return clientes.clone();
    }

    public void añadir(Cliente cliente) {
        int indice = buscarPrimerIndiceLibreComprobandoExistencia(cliente);
        if (indiceNoSuperaTamano(indice)) {
            clientes[indice] = new Cliente(cliente);
        } else {
            throw new ExcepcionAlquilerVehiculos("El array de clientes está lleno.");
        }
    }

    private int buscarPrimerIndiceLibreComprobandoExistencia(Cliente cliente) {
        int indice = 0;
        boolean clienteEncontrado = false;
        while (indiceNoSuperaTamano(indice) && !clienteEncontrado) {
            if (clientes[indice] == null) {
                clienteEncontrado = true;
            } else if (clientes[indice].getDni().equals(cliente.getDni())) {
                throw new ExcepcionAlquilerVehiculos("Ya existe un cliente con ese DNI");
            } else {
                indice++;
            }
        }
        return indice;
    }

    private boolean indiceNoSuperaTamano(int indice) {
        return indice < clientes.length;
    }

    public void borrar(String dni) {

        int indice = buscarIndiceCliente(dni);
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    private int buscarIndiceCliente(String dni) {

        int indice = 0;
        boolean existe = false;
        while (indiceNoSuperaTamano(indice) && !existe) {
            if (clientes[indice] != null && clientes[indice].getDni().equals(dni)) {
                existe = true;
            } else {
                indice++;
            }
        }
        if (existe) {
            return indice;
        } else {
            throw new ExcepcionAlquilerVehiculos("El cliente introducido no existe");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int posicion) {

        for (int i = posicion; i < clientes.length - 1 && clientes[i] != null; i++) {
            clientes[i] = clientes[i + 1];
        }
        clientes[clientes.length - 1] = null;
    }

    public Cliente buscar(String dni) {

        int posicion = buscarIndiceCliente(dni);
        if (indiceNoSuperaTamano(posicion)) {
            return new Cliente(clientes[posicion]);
        } else {
            return null;
        }
    }

}