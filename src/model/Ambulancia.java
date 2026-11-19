package model;

public class Ambulancia extends Veiculo {

    private boolean temMaca;

    public Ambulancia(String codigo, boolean temMaca) throws ModelException {
        super(codigo);
        this.temMaca = temMaca;
    }

    @Override
    public String getTipo() {
        return "Ambulância (Maca: " + (temMaca ? "Sim" : "Não") + ")";
    }

    public boolean isTemMaca() {
        return temMaca;
    }

    public void setTemMaca(boolean temMaca) {
        this.temMaca = temMaca;
    }
}