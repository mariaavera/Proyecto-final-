package model;

import java.time.LocalDate;

public interface TransaccionProgramada {
        void programarTransaccion(Transaccion transaccion, LocalDate fechaEjecucion);
        void ejecutarTransaccionesProgramadas();
        void cancelarTransaccionProgramada(String idTransaccion);
    }

