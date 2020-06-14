/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;
import java.net.URL;
import java.util.ResourceBundle;

import com.zaxxer.hikari.util.ClockSource.NanosecondClockSource;

import it.polito.tdp.spellcheck.model.Dictionary;
import it.polito.tdp.spellcheck.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*;

public class FXMLController {
	Dictionary model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboLingua;

    @FXML
    private TextArea txtAreaInserimento;

    @FXML
    private Button btnCheckLanguage;

    @FXML
    private TextArea txtAreaRisultato;

    @FXML
    private Label labelErrori;

    @FXML
    private Button btnClearAll;

    @FXML
    private TextField textFieldTempoEsecuzione;

    @FXML
    void doActivation(ActionEvent event) {
    	if(comboLingua.getValue()!=null) {
    		txtAreaInserimento.setDisable(false);
    		txtAreaRisultato.setDisable(false);
    		btnCheckLanguage.setDisable(false);
    		btnClearAll.setDisable(false);
    		txtAreaInserimento.clear();
    		txtAreaRisultato.clear();
    	}
    	if(comboLingua.getValue()== null) {
    		txtAreaInserimento.setDisable(true);
    		txtAreaRisultato.setDisable(true);
    		btnCheckLanguage.setDisable(true);
    		btnClearAll.setDisable(true);
    		txtAreaInserimento.setText("Selezionare una Lingua");
    	}

    }

    @FXML
    void doClearText(ActionEvent event) {
    	txtAreaInserimento.clear();
    	txtAreaRisultato.clear();
    	labelErrori.setText("Numbers of Errors");
    	textFieldTempoEsecuzione.setText("Spell check Status");

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	txtAreaRisultato.clear();
    	//creo una lista di parole dove vado a inserire le parole dell'utente.
    	List<String> paroleInserite = new LinkedList<String>();
        if(comboLingua.getValue()==null) {
    		txtAreaInserimento.setText("Choose a Language");
    	return;
    	}
    	if(model.loadDictionary(comboLingua.getValue())==false) {
    			txtAreaRisultato.appendText("Errore nel caricamento del dizionario");
    			return;
    	}
    
    	String parole = txtAreaInserimento.getText();
    	
    		//se true allora il dizionario è stato riempito correttamente in base a quello che ho passato da interfaccia
    	if(txtAreaInserimento.getText().isEmpty()==true) {
    			txtAreaRisultato.appendText("Inserire una frase da controllare");
    			return;
    	}
    	else {
    			parole.replaceAll("\n"," ");
    			parole.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", " ");
    			StringTokenizer st = new StringTokenizer(parole," ");
    			while(st.hasMoreTokens()) {
    				paroleInserite.add(st.nextToken());
    			}
    	}
    	
    	Long start = System.nanoTime();
    	List<RichWord> risultatoCorrezione = new LinkedList<RichWord>();
    	//risultatoCorrezione.addAll(model.spellCheckText(paroleInserite));
    	//risultatoCorrezione.addAll(model.spellCheckTextDichotomic(paroleInserite));
    	risultatoCorrezione.addAll(model.spellCheckTextLinear(paroleInserite));
    	long end = System.nanoTime();
    	int errori = 0;
    	for(RichWord r : risultatoCorrezione) {
    		if(r.isGiustaSbagliata()==false) {
    			errori++;
    			txtAreaRisultato.appendText(r+"\n");
    		}
    	}
    	labelErrori.setText("Number of Errors: "+errori);
    	textFieldTempoEsecuzione.appendText("Spell check completed in: "+(end-start)+ " seconds");
    	
    }

    @FXML
    void initialize() {
        assert comboLingua != null : "fx:id=\"comboLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAreaInserimento != null : "fx:id=\"txtAreaInserimento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheckLanguage != null : "fx:id=\"btnCheckLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAreaRisultato != null : "fx:id=\"txtAreaRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelErrori != null : "fx:id=\"labelErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearAll != null : "fx:id=\"btnClearAll\" was not injected: check your FXML file 'Scene.fxml'.";
        assert textFieldTempoEsecuzione != null : "fx:id=\"textFieldTempoEsecuzione\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Dictionary model) {
    	txtAreaInserimento.setDisable(true);
    	txtAreaInserimento.setText("Scegliere una lingua");
    	txtAreaRisultato.setDisable(true);
    	comboLingua.getItems().addAll("English","Italian");
    	
    	btnCheckLanguage.setDisable(true);
    	btnClearAll.setDisable(true);
    	
    	this.model = model;
    }
}
