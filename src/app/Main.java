package app;

import viewer.MenuConsole;

public class Main {

    public static void main(String[] args) {
        // Ponto de entrada: instancia a View e dá início ao ciclo de vida do sistema
        MenuConsole view = new MenuConsole();
        view.iniciar();
    }
}