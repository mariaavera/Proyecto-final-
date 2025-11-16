package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
        private String nombre;
        private String cedula;
    private List<Cuenta> cuentas = new ArrayList<>();
        private String contrasena;
        private int puntos;
        private TransaccionProgramada transaccionProgramada=null;
        private List<MonederoVirtual> monederos;
        private double descuentoTransferencias = 0;
        private LocalDate retirosGratisHasta=null;

    public Cliente(String nombre, String cedula, String contrasena,int puntos) {
        this.nombre = nombre;
        this.cedula=cedula;
        this.contrasena=contrasena;
        this.puntos = puntos;
        this.monederos=new ArrayList<>();
        cuentas = new ArrayList<>();
        cuentas.add(new Cuenta( 0.0,
                GenerarCuentas.generarNumero(),
                this));
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TransaccionProgramada getTransaccionProgramada() {
        return transaccionProgramada;
    }

    public void setTransaccionProgramada(TransaccionProgramada transaccionProgramada) {
        this.transaccionProgramada = transaccionProgramada;
    }

    public List<MonederoVirtual> getMonederos() {
        return monederos;
    }

    public void setMonederos(List<MonederoVirtual> monederos) {
        this.monederos = monederos;
    }

    public LocalDate getRetirosGratisHasta() {
        return retirosGratisHasta;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void rangoCliente(){
        if(puntos>=0 && puntos<=500){
            System.out.println("El rango del cliente es:Bronce");
        }else if(puntos>=501 && puntos<=1000){
            System.out.println("El rango del cliente es:Plata");
        }else if(puntos>=1001 && puntos<=5000){
            System.out.println("El rango del cliente es:Oro");
        }else{
            System.out.println("El rango del cliente es:Platino");
        }
    }
    public void registrarMonedero(MonederoVirtual monedero) {
        monederos.add(monedero);
        System.out.println("Monedero registrado correctamente: " + monedero.getNombre());
    }
    public MonederoVirtual getMonederoPrincipal() {
        return monederos.get(0);
    }
    public double getDescuentoTransferencias() {
        return descuentoTransferencias;
    }

    public void setDescuentoTransferencias(double descuento) {
        this.descuentoTransferencias = descuento;
    }

    public void setRetirosGratisHasta(LocalDate fecha) {
        this.retirosGratisHasta = fecha;
    }
    public boolean tieneRetirosGratis() {
        return retirosGratisHasta != null && LocalDate.now().isBefore(retirosGratisHasta);
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
