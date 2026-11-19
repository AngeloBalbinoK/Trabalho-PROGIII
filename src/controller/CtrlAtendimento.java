package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;
import dao.ChamadoDAO;

public class CtrlAtendimento {

    private List<Veiculo> frota = new ArrayList<>();
    private List<Chamado> chamados;
    private ChamadoDAO chamadoDAO = new ChamadoDAO();

    public CtrlAtendimento() {
        // Ao instanciar o Controller, o DAO busca o histórico gravado em arquivo
        this.chamados = chamadoDAO.carregar();
    }

    public String cadastrarAmbulancia(String codigo, boolean temMaca) {
        try {
            Ambulancia a = new Ambulancia(codigo, temMaca);
            frota.add(a);
            return "Ambulância " + codigo + " cadastrada com sucesso!";
        } catch (ModelException e) {
            return "Erro de Validação: " + e.getMessage();
        }
    }

    public String cadastrarMoto(String codigo, int cilindradas) {
        try {
            MotoResgate m = new MotoResgate(codigo, cilindradas);
            frota.add(m);
            return "Moto " + codigo + " cadastrada com sucesso!";
        } catch (ModelException e) {
            return "Erro de Validação: " + e.getMessage();
        }
    }

    public String registrarChamado(String protocolo, String endereco, String gravidade, int indiceVeiculo) {
        try {
            if (indiceVeiculo < 0 || indiceVeiculo >= frota.size()) {
                throw new ModelException("Índice do veículo selecionado é inválido!");
            }

            Veiculo v = frota.get(indiceVeiculo);

            // O Model executa as validações e dispara ModelException se houver inconsistência
            Chamado c = new Chamado(protocolo, endereco, gravidade, v);
            chamados.add(c);

            // Persistência acionada via DAO
            chamadoDAO.salvar(chamados);

            return "Chamado " + protocolo + " despachado com sucesso com " + v.getCodigo() + "!";
        } catch (ModelException e) {
            return "Bloqueio pelo Model: " + e.getMessage();
        } catch (Exception e) {
            return "Erro ao gravar em arquivo via DAO: " + e.getMessage();
        }
    }

    public List<Veiculo> getFrota() {
        return frota;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }
}