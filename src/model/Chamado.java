package model;

import java.io.Serializable;

public class Chamado implements Serializable {

    private String protocolo;
    private String endereco;
    private String gravidade;
    private Veiculo veiculo;

    public Chamado(String protocolo, String endereco, String gravidade, Veiculo veiculo) throws ModelException {
        validarProtocolo(protocolo);
        validarTexto(endereco, "Endereço");

        if (veiculo == null || !veiculo.isDisponivel()) {
            throw new ModelException("O veículo selecionado não está disponível!");
        }

        this.protocolo = protocolo;
        this.endereco = endereco;
        this.gravidade = gravidade;
        this.veiculo = veiculo;

        this.veiculo.setDisponivel(false); // Altera o status para ocupado
    }

    public static void validarProtocolo(String p) throws ModelException {
        if (p == null || p.trim().isEmpty()) {
            throw new ModelException("O número de protocolo é obrigatório.");
        }
    }

    public static void validarTexto(String texto, String campo) throws ModelException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new ModelException("O campo " + campo + " não pode ser vazio.");
        }
    }

    public String getProtocolo() {
        return protocolo;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getGravidade() {
        return gravidade;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
}