package aplicacao;

import java.util.Scanner;

import entidades.Mensagem;
import fila.FilaMensagens;

public class AtendimentoMensagem {

	static FilaMensagens filaReclamacao = new FilaMensagens();
	public void start() {

		Scanner sc = new Scanner(System.in);
		FilaMensagens filaSugestao = new FilaMensagens();
		FilaMensagens filaResolucao = new FilaMensagens();

		filaReclamacao.INIT();
		filaSugestao.INIT();
		filaResolucao.INIT();

		int escolha;

		do {
			System.out.println("============================================");
			System.out.println("	Bem vindo ao Atendimento");
			System.out.println("\n	Escolha uma das opcoes abaixo:\n");
			System.out.println("0- Encerrar o programa");
			System.out.println("1- Recebimento de Mensagem");
			System.out.println("2- Atendimento de Mensagem");
			System.out.println("3- Recebimento e Encaminhamento de Resolucao");
			System.out.println("============================================");
			System.out.print("Digite o numero: ");
			escolha = sc.nextInt();

			switch (escolha) {
			case 0:
				if (!todasFilasVazias(filaReclamacao, filaResolucao, filaSugestao)) {
					System.out.println("Para encerrar o programa as filas precisam estar vazias");
					escolha = 4;
				}
				break;
			case 1:
				escolheFila();
				break;
			case 2:
				System.out.println("2");
				break;
			case 3:
				System.out.println("3");
				break;
			default:
				System.out.println("O numero escolhido e invalido! Digite um numero entre 0 a 3.");
			}
		} while (escolha != 0);

	}

	public void escolheFila() {
		Scanner sc = new Scanner(System.in);
		String nome = null;
		
		
		System.out.println("Deseja inserir o seu nome? (1 - Sim) (Qualquer número - não)");
		int temNome = sc.nextInt();
		
		if (temNome == 1) {
			sc.nextLine();
			System.out.println("Nome (opcional): ");
			nome = sc.nextLine();
		}
		
		System.out.println("Email/Telefone: ");
		String emailTel = sc.next();
		
		System.out.println("Motivo de Contato (1 - Reclamação) (2 - Sugestão): ");
		int motivo = sc.nextInt();
		
		sc.nextLine();
		System.out.println("Mensagem: ");
		String mensagemUsuario = sc.nextLine();

		if (temNome == 1) {
			Mensagem msg = new Mensagem(nome, emailTel, motivo, mensagemUsuario);
		} else {
			Mensagem msg = new Mensagem(emailTel, motivo, mensagemUsuario);
		}

	}
	
	public void adicionaFila(Mensagem msg, int motivo) {
		if(motivo == 1) {
			filaReclamacao.ENQUEUE(msg);
		}
	}

	public boolean todasFilasVazias(FilaMensagens filaReclamacao, FilaMensagens filaSugestao,
			FilaMensagens filaResolucao) {
		return (filaReclamacao.IsEmpty() && filaSugestao.IsEmpty() && filaResolucao.IsEmpty());
	}
}
