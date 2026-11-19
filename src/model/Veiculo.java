package model;

import java.io.Serializable;

public abstract class Veiculo implements Serializable {

    private String codigo;
    private boolean disponivel;

    public Veiculo(String codigo) throws ModelException {
        this.setCodigo(codigo);
        this.disponivel = true;
    }

    public static void validarCodigo(String codigo) throws ModelException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new ModelException("O código/placa do veículo não pode ser vazio.");
        }

        if (codigo.trim().length() < 3) {
            throw new ModelException("O código deve ter pelo menos 3 caracteres.");
        }
    }

    public void setCodigo(String codigo) throws ModelException {
        validarCodigo(codigo);
        this.codigo = codigo.trim().toUpperCase();
    }

    public String getCodigo() {
        return this.codigo;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public abstract String getTipo();
}