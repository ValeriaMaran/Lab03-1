package it.polito.tdp.spellcheck.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	private List<String> dizionario = new LinkedList<String>();
	
	public boolean loadDictionary(String language) {
		if(language.equals("English")) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String parola = "";
				while((parola=br.readLine())!=null) {
					dizionario.add(parola);
				}
				br.close(); 
				return true;
			}
			catch(IOException e) {
				System.out.println("Errore nella lettura del file");
				return false;
			}
			
		}
		if(language.equals("Italian")) {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String parola = "";
				while((parola=br.readLine())!=null) {
					dizionario.add(parola);
					
				}
				br.close();
				return true;
			}
			catch(IOException e) {
				System.out.println("Errore nella Lettura del File");
				return false;
			}
		}
		return false;
	}
	
	public List<RichWord> spellCheckText(List<String> inputStringText){
		List<RichWord> paroleGiusteSbagliate = new  LinkedList<RichWord>();
		
		for(String s: inputStringText) {
			if(dizionario.contains(s)==true) {
				paroleGiusteSbagliate.add(new RichWord(s,true));
			}
			if(dizionario.contains(s)==false) {
				paroleGiusteSbagliate.add(new RichWord(s,false));
			}
		}
		return paroleGiusteSbagliate;
	}
	public List<RichWord> spellCheckTextDichotomic(List<String> inputStringText){
		List<RichWord> paroleGiusteSbagliate = new LinkedList<RichWord>();
		//Ricerca Dicotomica: parto da quello a metà
		
		for(String s : inputStringText) {
			paroleGiusteSbagliate.add(new RichWord(s,dichotomicSearch(s)));
		}
		return paroleGiusteSbagliate;
		
		
	}
	public List<RichWord> spellCheckTextLinear(List<String> parole){
		List<RichWord> paroleGiusteSbagliate = new LinkedList<RichWord>();
		
		for(String s : parole) {
			paroleGiusteSbagliate.add(new RichWord(s,linearSearch(s)));
			
		}
		return paroleGiusteSbagliate;
		
	}
	public boolean dichotomicSearch(String temp) {
		int N = dizionario.size();
		int i = (dizionario.size())/2;
		
		if(temp.equalsIgnoreCase(dizionario.get(i))==true) {
			return true;
		}
		if(temp.equalsIgnoreCase(dizionario.get(i))==false) {
			 
			for(int j=i;j<N;j++) {
				if(temp.equalsIgnoreCase(dizionario.get(j))==true) {
					return true;
				}
			}
			for(int j = 0;j<i;j++) {
				if(temp.equalsIgnoreCase(dizionario.get(i))) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean linearSearch(String temp) {
		int N = dizionario.size();
		boolean flagTrovato =false;
		
		for(int i = 0;i<N;i++) {
			if(temp.equalsIgnoreCase(dizionario.get(i))) {
				flagTrovato=true;
				return flagTrovato;
			}
		}
		return flagTrovato;
	}
}
