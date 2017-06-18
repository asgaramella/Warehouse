package it.polito.tdp.warehouse;

import it.polito.tdp.warehouse.model.Model;
import it.polito.tdp.warehouse.model.SimStat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class WarehouseController {
	
	private Model model ;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> boxStrategia;

    @FXML
    private Button btnCarica;

    @FXML
    private Button btnSimula;

    @FXML
    private TextField txtLocali;

    @FXML
    private TextArea txtResult;


    @FXML
    void doOccupazione(ActionEvent event) {
    	
    	txtResult.appendText("Massima capienza magazzino: "+ model.getMaxCapienza()+"\n");
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	String s=this.boxStrategia.getValue();
    	if(s==null){
    		txtResult.appendText("ERRORE: Selezionare una strategia \n");
    		return;
    	}
    	
    	String Ns= this.txtLocali.getText();
    	if(Ns.equals(" ")){
    		txtResult.appendText("ERRORE:Specificare parametro locali \n");
    		return;
    	}
    	int N;
    	
    	try{
    		N=Integer.parseInt(Ns);
    	}catch(NumberFormatException e){
    		txtResult.appendText("ERRORE:Inserire un nr \n");
    		return;
    	}
    	
    	SimStat stats=model.doSimulazione(s,N);
    	
    	txtResult.appendText("nr dei disastri "+stats.getDisastri()+"\n");
    	txtResult.appendText("Max fattore di carico raggiunto nella simulazione "+stats.getMaxCarico()+"\n");
    	txtResult.appendText("Tempo totale spostamenti "+stats.gettTot()+"\n");
    	
    	
    		
    	
    	
    	
    }
    
    public void setModel(Model model) {
		this.model = model;
	}

    @FXML
    void initialize() {
        assert boxStrategia != null : "fx:id=\"boxStrategia\" was not injected: check your FXML file 'Warehouse.fxml'.";
        assert btnCarica != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'Warehouse.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Warehouse.fxml'.";
        assert txtLocali != null : "fx:id=\"txtLocali\" was not injected: check your FXML file 'Warehouse.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Warehouse.fxml'.";
        
        boxStrategia.getItems().clear() ;
        boxStrategia.getItems().addAll("Meno occupato","Più occupato") ;
    }

}
