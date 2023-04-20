package aplicacao;

import java.util.Scanner;

import entidades.Mensagem;
import fila.FilaMensagens;

public class AtendimentoMensagem {

	Scanner sc = new Scanner(System.in);
	FilaMensagens filaReclamacao = new FilaMensagens();
	FilaMensagens filaSugestao = new FilaMensagens();
	FilaMensagens filaResolucao = new FilaMensagens();
	
	int meioDeUso;

	public void start() {


		filaReclamacao.INIT();
		filaSugestao.INIT();
		filaResolucao.INIT();

		int escolha;
		
		System.out.println("Qual meio de uso? (1 - Whatsapp) (2- Aplicativo da empresa)");
		meioDeUso = sc.nextInt();

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
				atenderMensagem();
				break;
			case 3:
				removerFila(3);
				System.out.println("Enviada resposta para cliente: sua solicitação já foi resolvida pelo setor responsável. Obrigado!!!");
				break;
			default:
				System.out.println("O numero escolhido e invalido! Digite um numero entre 0 a 3.");
			}
		} while (escolha != 0);

	}
	
	

	public void escolheFila() {
		String nome = null;
		
		//TRATAR ISSO
		System.out.println("Deseja inserir o seu nome? (1 - Sim) (Qualquer número - não)");
		int temNome = sc.nextInt();
		
		if (temNome == 1) {
			sc.nextLine();
			System.out.println("Nome (opcional): ");
			nome = sc.nextLine();
		}
		
		if(meioDeUso == 1) {
			System.out.println("Telefone: ");
		}else {
			System.out.println("Email: ");
		}
		String emailTel = sc.next();
		
		//TRATAR ISSO
		System.out.println("Motivo de Contato (1 - Reclamação) (2 - Sugestão): ");
		int motivo = sc.nextInt();
		
		sc.nextLine();
		
		System.out.println("Mensagem: ");
		String mensagemUsuario = sc.nextLine();
		Mensagem msg;
		
		if (temNome == 1) {
			msg = new Mensagem(nome, emailTel, motivo, mensagemUsuario);
		} else {
			msg = new Mensagem(emailTel, motivo, mensagemUsuario);
		}
		
		adicionarFila(motivo, msg);
	}
	
	public void adicionarFila(int motivo, Mensagem msg) {
		
		if(motivo == 1) {
			filaSugestao.ENQUEUE(msg);
		}else if(motivo == 2){
			filaReclamacao.ENQUEUE(msg);
		}else {
			filaResolucao.ENQUEUE(msg);
		}
	}
	
	
	public int atenderMensagem() {
		System.out.println("Digite (1 - Atender Reclamacao) (2 - Atender Sugestao)");
		int opcao = sc.nextInt();

		if(opcao == 1) {
			apagarEncaminhar(opcao);
		}else {
			apagarEncaminhar(opcao);			
		}
		
		return opcao;
	}
	
	public void removerFila(int motivo) {
		if(motivo == 1) {
			filaSugestao.DEQUEUE();
		}else if(motivo == 2){
			filaReclamacao.DEQUEUE();
		}else {
			filaResolucao.DEQUEUE();
		}
	}
	
	public void apagarEncaminhar(int fila) {
		System.out.println("Deseja atender essa fila ou encaminhar para resolução? (1- Atender) (2- Encaminhar)");
		int opcao = sc.nextInt();
		
		if(opcao == 1) {
			removerFila(fila);
			System.out.println("Enviada resposta para cliente: sua solicitação já foi resolvida. Obrigado!!!");
		}else {
			Mensagem msg = topFila(fila);
			System.out.println(msg);
			adicionarFila(3, msg);
			removerFila(fila);
		}
	}
	
	public Mensagem topFila(int motivo) {
		if(motivo == 1) {
			return filaSugestao.TOP();
		}else if(motivo == 2){
			return filaReclamacao.TOP();
		}else {
			return filaResolucao.TOP();
		}
	}
	

	public boolean todasFilasVazias(FilaMensagens filaReclamacao, FilaMensagens filaSugestao,
			FilaMensagens filaResolucao) {
		return (filaReclamacao.IsEmpty() && filaSugestao.IsEmpty() && filaResolucao.IsEmpty());
	}
}
