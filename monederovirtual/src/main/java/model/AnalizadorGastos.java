package model;

import java.time.LocalDate;

public class AnalizadorGastos {
    private Cuenta cuenta;

    public AnalizadorGastos(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public double calcularGastoTotalMensual(int mes, int año) {
        double total = 0;

        for (Transaccion t : cuenta.consultaTransacciones()) {
            LocalDate fecha = t.getFecha();

            if (fecha.getMonthValue() == mes && fecha.getYear() == año) {
                if (t instanceof Retiro || t instanceof Transferencia) {
                    total += t.getValor();
                }
            }
        }

        return total;
    }
    public double calcularPromedioGastoMensual(int año) {
        double total = 0;
        int mesesConGasto = 0;

        for (int mes = 1; mes <= 12; mes++) {
            double gasto = calcularGastoTotalMensual(mes, año);
            if (gasto > 0) mesesConGasto++;
            total += gasto;
        }

        return (mesesConGasto > 0) ? total / mesesConGasto : 0;
    }
    public String generarAlerta(int mes, int año) {
        double gastoMes = calcularGastoTotalMensual(mes, año);
        double promedio = calcularPromedioGastoMensual(año);

        if (promedio == 0) return "No hay suficiente información para generar alertas.";

        if (gastoMes > promedio * 1.2) {
            return "Tus gastos este mes aumentaron más del 20% respecto al promedio.";
        } else if (gastoMes < promedio * 0.8) {
            return "Este mes gastaste menos de lo habitual. ¡Buen control financiero!";
        } else {
            return "Tus gastos se mantienen dentro del promedio.";
        }
    }
}
