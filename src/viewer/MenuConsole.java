package viewer;

import java.util.Scanner;

import controller.CtrlAtendimento;
import model.Chamado;
import model.Veiculo;

public class MenuConsole {

    private Scanner teclado = new Scanner(System.in);
    private CtrlAtendimento controller = new CtrlAtendimento();

    public void iniciar() {
        int opcao = -1;

        do {
            System.out.println("\n========================================");
            System.out.println(" SISTEMA DE DESPACHO E RESGATE (SAMU) ");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Ambulância");
            System.out.println("2 - Cadastrar Moto de Resgate");
            System.out.println("3 - Registrar e Despachar Chamado");
            System.out.println("4 - Listar Chamados Gravados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(">> ERRO: Digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Código/Placa (ex: AMB-01): ");
                    String codAmb = teclado.nextLine();

                    System.out.print("Possui maca? (S/N): ");
                    boolean maca = teclado.nextLine().trim().equalsIgnoreCase("S");

                    System.out.println("\n[STATUS] " + controller.cadastrarAmbulancia(codAmb, maca));
                    break;

                case 2:
                    System.out.print("Código/Placa (ex: MOT-01): ");
                    String codMoto = teclado.nextLine();

                    System.out.print("Cilindradas (ex: 250): ");

                    try {
                        int cil = Integer.parseInt(teclado.nextLine());
                        System.out.println("\n[STATUS] " + controller.cadastrarMoto(codMoto, cil));
                    } catch (NumberFormatException e) {
                        System.out.println("\n[STATUS] Erro: Cilindradas deve ser um número inteiro!");
                    }
                    break;

                case 3:
                    if (controller.getFrota().isEmpty()) {
                        System.out.println("\n[AVISO] Cadastre pelo menos um veículo na frota primeiro!");
                        break;
                    }

                    System.out.print("Protocolo (ex: RES-101): ");
                    String prot = teclado.nextLine();

                    System.out.print("Endereço da ocorrência: ");
                    String end = teclado.nextLine();

                    System.out.print("Gravidade (Alta / Media / Baixa): ");
                    String grav = teclado.nextLine();

                    System.out.println("\nVeículos na frota:");

                    for (int i = 0; i < controller.getFrota().size(); i++) {
                        Veiculo v = controller.getFrota().get(i);
                        String status = v.isDisponivel() ? "DISPONÍVEL" : "OCUPADO";

                        System.out.println(i + " - " + v.getCodigo() + " | " + v.getTipo() + " [" + status + "]");
                    }

                    System.out.print("Selecione o índice do veículo: ");

                    try {
                        int idx = Integer.parseInt(teclado.nextLine());
                        System.out.println("\n[STATUS] " + controller.registrarChamado(prot, end, grav, idx));
                    } catch (NumberFormatException e) {
                        System.out.println("\n[STATUS] Erro: Índice inválido!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Histórico de Chamados Gravados no Arquivo ---");

                    if (controller.getChamados().isEmpty()) {
                        System.out.println("Nenhum chamado registrado até o momento.");
                    } else {
                        for (Chamado c : controller.getChamados()) {
                            System.out.println("Protocolo: " + c.getProtocolo() + " | Local: " + c.getEndereco() + " | Gravidade: " + c.getGravidade() + " | Veículo Alocado: " + c.getVeiculo().getCodigo() + " (" + c.getVeiculo().getTipo() + ")");
                        }
                    }
                    break;

                case 0:
                    System.out.println("\nSistema encerrado. Dados salvos com segurança.");
                    break;

                default:
                    System.out.println(">> Opção inválida!");
            }

        } while (opcao != 0);
    }
}