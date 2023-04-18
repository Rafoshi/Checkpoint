package entidades;

public class Mensagem {

	private String nome;
	private String telefone_ou_email;
	private int motivo_de_contanto;
	private String mensagem;

	
	public Mensagem(String nome, String telefone_ou_email, int motivo_de_contanto, String mensagem) {
		this.nome = nome;
		this.telefone_ou_email= telefone_ou_email;
		this.motivo_de_contanto = motivo_de_contanto;
		this.mensagem = mensagem;
	}
	
	public Mensagem(String telefone_ou_email, int motivo_de_contanto, String mensagem) {
		this.telefone_ou_email= telefone_ou_email;
		this.motivo_de_contanto = motivo_de_contanto;
		this.mensagem = mensagem;
	}
	
	public String toString() {
		String motivo = motivo_de_contanto == 1 ? "reclamação" : "sugestão";
		return "O " + this.nome + " do telefone/email " + this.telefone_ou_email + " enviou a mensagem " + this.mensagem + " pelo motivo " + motivo;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
}
