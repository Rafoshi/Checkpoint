package aplicacao;

import java.util.Scanner;

import fila.FilaMensagens;

public class AtendimentoMensagem {
	
	FilaMensagens filaReclamacao = new FilaMensagens();
	FilaMensagens filaSugestao = new FilaMensagens();
	FilaMensagens filaResolucao = new FilaMensagens();
	
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		System.out.println("============================================");
		System.out.println("	Bem vindo ao Atendimento");
		System.out.println("\n	Escolha uma das opcoes abaixo:\n");
		System.out.println("0- Encerrar o programa");
		System.out.println("1- Recebimento de Mensagem");
		System.out.println("2- Atendimento de Mensagem");
		System.out.println("3- Recebimento e Encaminhamento de Resolucao");
		System.out.println("============================================");
		System.out.print("Digite o numero: ");
		int escolha = sc.nextInt();
		
		switch (escolha) {
		case 0:
			System.out.println("0");
			if(filaReclamacao.IsEmpty() && filaSugestao.IsEmpty() && filaResolucao.IsEmpty())
				break;
		case 1:
			System.out.println("1");
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
		
	}
	
	public void escolheFila() {
		System.out.println("Nome (opcional): ");
		System.out.println("Email/Telefone: ");
	}
}
