package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String cedula;
    private List<Cuenta> cuentas;
    private String contrasena;
    private int puntos;
    private TransaccionProgramada transaccionProgramada = null;
    private List<MonederoVirtual> monederos;
    private double descuentoTransferencias = 0;
    private LocalDate retirosGratisHasta = null;

    public Cliente(String nombre, String cedula, String contrasena, int puntos) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.puntos = puntos;
        this.monederos = new ArrayList<>();
        cuentas = new ArrayList<>();
    }

    public Cuenta crearNuevaCuenta(double saldoInicial) {
        Cuenta cuentaNueva = new Cuenta(
                saldoInicial,
                GenerarCuentas.generarNumero(),
                this
        );
        this.cuentas.add(cuentaNueva);
        return cuentaNueva;
    }
    public String getCedula() {
        return cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void agregarPuntos(int puntosGanados) {
        this.puntos += puntosGanados;
    }
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public String getRango() {
        if (puntos <= 500) return "Bronce";
        if (puntos <= 1000) return "Plata";
        if (puntos <= 5000) return "Oro";
        return "Platino";
    }
    public void registrarMonedero(MonederoVirtual monedero) {
        monederos.add(monedero);
        System.out.println("Monedero registrado correctamente: " + monedero.getNombre());
    }

    public void setDescuentoTransferencias(double descuento) {
        this.descuentoTransferencias = descuento;
    }

    public void setRetirosGratisHasta(LocalDate fecha) {
        this.retirosGratisHasta = fecha;
    }

    public void descontarPuntos(int p) {
        puntos -= p;
    }
    public boolean canjear(Beneficio beneficio) {
        if (puntos < beneficio.getPuntosRequeridos()) {
            System.out.println("No tienes suficientes puntos.");
            return false;
        }

        descontarPuntos(beneficio.getPuntosRequeridos());

        if (beneficio instanceof Puntos puntos) {
            puntos.aplicarBeneficio(this);
        }

        System.out.println("Canje exitoso: " + beneficio.getNombre());
        return true;
    }


}
