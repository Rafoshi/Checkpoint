package entidades;

public class Mensagem {

	private String nome;
	private String telefone_ou_email;
	private int motivo_de_contato;
	private String mensagem;

	
	public Mensagem(String nome, String telefone_ou_email, int motivo_de_contanto, String mensagem) {
		this.nome = nome;
		this.telefone_ou_email= telefone_ou_email;
		this.motivo_de_contato = motivo_de_contanto;
		this.mensagem = mensagem;
	}
	
	public Mensagem(String telefone_ou_email, int motivo_de_contanto, String mensagem) {
		this.telefone_ou_email= telefone_ou_email;
		this.motivo_de_contato = motivo_de_contanto;
		this.mensagem = mensagem;
	}
	
	//FAZER LOGICA SE NAO TIVER NOME COLOCA FULANO OU CICLANO
	public String toString() {
		String motivo = motivo_de_contato == 1 ? "reclamação" : "sugestão";
		
		if(this.nome == null) {
			return "O usuário anônimo do telefone/email " + this.telefone_ou_email + " enviou a  " + motivo + ": " + this.mensagem;
		} 
		
		return this.nome + " do telefone/email " + this.telefone_ou_email + " enviou a  " + motivo + ": " + this.mensagem;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
	
}
