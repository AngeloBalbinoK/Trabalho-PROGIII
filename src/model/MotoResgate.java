package model;

public class MotoResgate extends Veiculo {

    private int cilindradas;

    public MotoResgate(String codigo, int cilindradas) throws ModelException {
        super(codigo);
        this.cilindradas = cilindradas;
    }

    @Override
    public String getTipo() {
        return "Moto de Resgate (" + cilindradas + "cc)";
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }
}