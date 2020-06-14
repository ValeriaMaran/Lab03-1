package it.polito.tdp.spellcheck.model;
import java.util.*;
public class TestModel {

	public static void main(String[] args) {
		Dictionary model=new Dictionary();
		List<String> parole = new LinkedList<String>();
		parole.add("mamma"); 
		parole.add("papa");
		parole.add("orcodio");
		parole.add("ops");
		parole.add("cierto");
		parole.add("seia");
		model.loadDictionary("Italian");
		
		List<RichWord> result = new LinkedList<RichWord>();
		result.addAll(model.spellCheckText(parole));
		result.toString();
		System.out.println(result);
		
	}

}
