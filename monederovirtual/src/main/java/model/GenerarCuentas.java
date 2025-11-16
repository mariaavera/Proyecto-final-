package model;

public class GenerarCuentas {
    private static long contador = 1000000000L;
    public static synchronized String generarNumero() {
        return String.valueOf(contador++);
    }
}
