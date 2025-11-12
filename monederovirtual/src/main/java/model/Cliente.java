package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
        private String nombre;
        private Cuenta cuenta;
        private int puntos;
        private TransaccionProgramada transaccionProgramada;
        private List<MonederoVirtual> monederos;

    public Cliente(String nombre, Cuenta cuenta, int puntos) {
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.puntos = puntos;
        this.monederos=new ArrayList<>();
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

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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
}
