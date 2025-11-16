package model;

import java.util.ArrayList;
import java.util.List;

public class BaseInformaciónCliente {
    private static List<Cliente> clientes = new ArrayList<>();

    public BaseInformaciónCliente() {
    }

    public static void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(List<Cliente> clientes) {
        BaseInformaciónCliente.clientes = clientes;
    }

    public static Cliente buscarPorCedula(String cedula) {
        for (Cliente c : clientes) {
            if (c.getCedula().equals(cedula)) return c;
        }
        return null;
    }
    public static Cliente validarLogin(String cedula, String contrasena) {
        Cliente c = buscarPorCedula(cedula);
        if (c != null && c.getContrasena().equals(contrasena)) {
            return c;
        }
        return null;
    }
    public static Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        for (Cliente c : clientes) {
            for (Cuenta cuenta : c.getCuentas()) {
                if (cuenta.getId().equals(numeroCuenta)) return cuenta;
            }
        }
        return null;
    }
}

