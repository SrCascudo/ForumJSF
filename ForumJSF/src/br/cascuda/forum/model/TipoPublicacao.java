package br.cascuda.forum.model;

public enum TipoPublicacao {
	QUESTAO(1, "Publicação"), COMENTARIO(2, "Comentário");
	
	private int value;
	private String label;
	
	private TipoPublicacao(int value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
