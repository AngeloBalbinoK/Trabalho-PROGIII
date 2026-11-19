package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Chamado;

public class ChamadoDAO {

    private final String ARQUIVO = "chamados.dat";

    // Grava a lista completa serializada em arquivo binário
    public void salvar(List<Chamado> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(lista);
        }
    }

    // Carrega a lista persistida ao iniciar o software
    @SuppressWarnings("unchecked")
    public List<Chamado> carregar() {
        File f = new File(ARQUIVO);

        if (!f.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Chamado>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}