package model;

import java.util.List;

public class VerificadorIntegridad {
    public boolean validarTransaccion(Transaccion t) {
        if (t == null) return false;
        if (t.valor <= 0) return false;
        if (t.cliente == null) return false;
        if (t instanceof Deposito deposito) {
            return validarDeposito(deposito);
        }
        else if (t instanceof Retiro retiro) {
            return validarRetiro(retiro);
        }
        else if (t instanceof Transferencia transferencia) {
            return validarTransferencia(transferencia);
        }

        return false;
    }

    private boolean validarDeposito(Deposito d) {
        return d.getCuentaDestino() != null;
    }

    private boolean validarRetiro(Retiro r) {
        return r.getCuentaOrigen() != null;
    }

    private boolean validarTransferencia(Transferencia tr) {
        return tr.getCuentaOrigen() != null
                && tr.getCuentaDestino() != null
                && tr.getCuentaOrigen() != tr.getCuentaDestino();
    }

    public boolean validarLista(List<Transaccion> lista) {
        return validarListaRec(lista, 0);
    }

    private boolean validarListaRec(List<Transaccion> lista, int index) {
        if (index == lista.size()) {
            return true;
        }
        if (!validarTransaccion(lista.get(index))) {
            return false;
        }
        return validarListaRec(lista, index + 1);
    }
}
