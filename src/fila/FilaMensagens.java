package fila;

import entidades.Mensagem;

public class FilaMensagens {

	public class NO {
		Mensagem dado;
		NO prox;
	}

	private static NO inicio;
	private static NO fim;

	private static class Retorno {
		public String dadoRetorno;
		public boolean ok;
	}

	public void INIT() {
		inicio = null;
		fim = null;
	}

	public boolean IsEmpty() {
		return inicio == null && fim == null;
	}
	
	public Mensagem TOP() {
		return inicio.dado;
	}

	public void ENQUEUE(Mensagem mensagem) {
		NO novo = new NO();
		novo.dado = mensagem;
		novo.prox = null;
		if (IsEmpty())
			inicio = novo;
		else
			fim.prox = novo;
		fim = novo;
	}
	
	public Retorno DEQUEUE() {
		Retorno saida = new Retorno();
		if(!IsEmpty()) {
			saida.dadoRetorno = inicio.dado.getMensagem();
			inicio = inicio.prox;
			if(inicio == null) 
				fim = null;
			saida.ok = true;
		}
		else 
			saida.ok = false;
		return saida;
	}
}
