package br.cascuda.forum.model;

public enum TypeUser {

	ROOT(1),
	DEFAULT(2);
	
	private int value;

	private TypeUser(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
