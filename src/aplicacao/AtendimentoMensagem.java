package aplicacao;

import java.util.Scanner;

import entidades.Mensagem;
import fila.FilaMensagens;
/*Erik Giuseppe Kato Bandeira 92988
*João Tancredi Dela Rocca 93527
*Matheus Stelutti Sepulveda 96272
*Rafael Ioshi Imamura Pereira 93102
*Luis Gustavo Profiro da Silva 96258
*/
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
		
		do {
			System.out.print("Qual meio de uso (1 - Whatsapp) (2 - Aplicativo da empresa)? ");
			meioDeUso = sc.nextInt();
			if(meioDeUso != 1 && meioDeUso != 2) {
				System.out.println("Opção inválida.");
			}
			System.out.println();
		} while(meioDeUso != 1 && meioDeUso != 2);


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
			System.out.println();

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
				if(!verificarFila(3)) {					
					removerFila(3);
					System.out.println(
							"Enviada resposta para cliente: sua solicitação já foi resolvida pelo setor responsável. Obrigado!!!");
				}else {
					System.out.println("\nNão é possível encaminhar com a fila vazia.\n");
				}
				break;
			default:
				System.out.println("O numero escolhido e invalido! Digite um numero entre 0 a 3.");
			}
		} while (escolha != 0);

	}

	public void escolheFila() {
		String nome = null;
		int temNome;
		int motivo;
		
		do {
			System.out.print("Deseja inserir o seu nome (1 - Sim) (2 - Não)? ");
			temNome = sc.nextInt();
			if(temNome != 1 && temNome != 2) {
				System.out.println("Opção inválida.");
			} 
			System.out.println();
		} while(temNome != 1 && temNome != 2);
		
		sc.nextLine();

		if (temNome == 1) {
			System.out.print("Nome: ");
			nome = sc.nextLine();
			System.out.println();
		}

		if (meioDeUso == 1) {
			System.out.print("Telefone: ");
		} else {
			System.out.print("Email: ");
		}
		String emailTel = sc.nextLine();
		System.out.println();

		
		do {
			System.out.print("Motivo de Contato (1 - Reclamação) (2 - Sugestão): ");
			motivo = sc.nextInt();
			if(motivo != 1 && motivo != 2) {
				System.out.println("Opção inválida.");
			}
			System.out.println();
		} while(motivo != 1 && motivo != 2);

		sc.nextLine();

		System.out.print("Mensagem: ");
		String mensagemUsuario = sc.nextLine();
		Mensagem msg;

		if (temNome == 1) {
			msg = new Mensagem(nome, emailTel, motivo, mensagemUsuario);
		} else {
			msg = new Mensagem(emailTel, motivo, mensagemUsuario);
		}

		adicionarFila(motivo, msg);

		System.out.println("\nMensagem enviada!\n");

	}

	public void adicionarFila(int motivo, Mensagem msg) {

		if (motivo == 1) {
			filaReclamacao.ENQUEUE(msg);
		} else if (motivo == 2) {
			filaSugestao.ENQUEUE(msg);
		} else {
			filaResolucao.ENQUEUE(msg);
		}
	}

	public int atenderMensagem() {
		
		int opcao;
		
		do {
			System.out.print("Deseja atender uma reclamação ou uma sugestão (1 - Atender Reclamacao) (2 - Atender Sugestao)? ");
			 opcao = sc.nextInt();
			if(opcao != 1 && opcao != 2) {
				System.out.println("Opção inválida.");
			}
			System.out.println();
		} while(opcao != 1 && opcao != 2);

		apagarEncaminhar(opcao);

		return opcao;
	}

	public void removerFila(int motivo) {
		if (motivo == 1) {
			filaReclamacao.DEQUEUE();
		} else if (motivo == 2) {
			filaSugestao.DEQUEUE();
		} else {
			filaResolucao.DEQUEUE();
		}
	}

	public void apagarEncaminhar(int fila) {
		int opcao;
		
		do {
			System.out.print("Deseja atender essa fila ou encaminhar para resolução (1 - Atender) (2 - Encaminhar)? ");
			opcao = sc.nextInt();
			if(opcao != 1 && opcao != 2) {
				System.out.println("Opção inválida.");
			}
			System.out.println();
		} while(opcao != 1 && opcao != 2);
		
		if(!verificarFila(fila)) {
			if(opcao == 1) {
				removerFila(fila);
				System.out.println("Enviada resposta para cliente: sua solicitação já foi resolvida. Obrigado!!!");
			}else {
				Mensagem msg = topFila(fila);
				System.out.println(msg);
				adicionarFila(3, msg);
				removerFila(fila);
			}
		}else {
			System.out.println("\nNão é possível encaminhar ou atender com a fila vazia.\n");
		}
	}

	public Mensagem topFila(int motivo) {
		if (motivo == 1) {
			return filaReclamacao.TOP();
		} else if (motivo == 2) {
			return filaSugestao.TOP();
		} else {
			return filaResolucao.TOP();
		}
	}

	public boolean todasFilasVazias(FilaMensagens filaReclamacao, FilaMensagens filaSugestao,
			FilaMensagens filaResolucao) {
		return (filaReclamacao.IsEmpty() && filaSugestao.IsEmpty() && filaResolucao.IsEmpty());
	}
	
	public boolean verificarFila(int fila){
		if(fila == 1) {
			return filaReclamacao.IsEmpty();
		}else if(fila == 2){
			return filaSugestao.IsEmpty();
		}else {
			return filaResolucao.IsEmpty();
		}
	}
}
