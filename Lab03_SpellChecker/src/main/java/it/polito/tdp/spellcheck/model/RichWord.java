package it.polito.tdp.spellcheck.model;

public class RichWord {
	private String parola;
	private boolean giustaSbagliata;
	public RichWord(String parola, boolean giustaSbagliata) {
		super();
		this.parola = parola;
		this.giustaSbagliata = giustaSbagliata;
	}
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	public boolean isGiustaSbagliata() { 
		return giustaSbagliata;
	}
	public void setGiustaSbagliata(boolean giustaSbagliata) {
		this.giustaSbagliata = giustaSbagliata;
	}
	@Override
	public String toString() {
		return String.format("RichWord [parola=%s, giustaSbagliata=%s]", parola, giustaSbagliata);
	}
	
	
}
