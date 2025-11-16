package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnalizadorGastos {

    private Cliente cliente;
    private List<Cuenta> cuentas;

    public AnalizadorGastos(Cliente cliente) {
        this.cliente = cliente;
        this.cuentas = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double calcularGastoTotalMensual(int mes, int año) {
        double total = 0;

        for (Cuenta cuenta : cuentas) {
            for (Transaccion t : cuenta.consultaTransacciones()) {
                LocalDate fecha = t.getFecha();

                if (fecha.getMonthValue() == mes && fecha.getYear() == año) {
                    if (t instanceof Retiro || t instanceof Transferencia) {
                        total += t.getValor();
                    }
                }
            }
        }

        return total;
    }

    public double calcularPromedioGastoMensual(int año) {
        double total = 0;
        int mesesConGasto = 0;

        for (int mes = 1; mes <= 12; mes++) {
            double gastoMes = calcularGastoTotalMensual(mes, año);

            if (gastoMes > 0) {
                mesesConGasto++;
            }

            total += gastoMes;
        }

        return (mesesConGasto > 0) ? total / mesesConGasto : 0;
    }
    public String generarAlerta(int mes, int año) {
        double gastoMes = calcularGastoTotalMensual(mes, año);
        double promedio = calcularPromedioGastoMensual(año);

        if (promedio == 0) {
            return "No hay suficiente información para generar alertas.";
        }

        if (gastoMes > promedio * 1.2) {
            return "Tus gastos este mes aumentaron más del 20% respecto al promedio.";
        }
        else if (gastoMes < promedio * 0.8) {
            return "Este mes gastaste menos de lo habitual. ¡Buen control financiero!";
        }
        else {
            return "Tus gastos se mantienen dentro del promedio.";
        }
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
