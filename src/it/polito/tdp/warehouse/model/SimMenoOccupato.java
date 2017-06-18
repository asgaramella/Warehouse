package it.polito.tdp.warehouse.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.polito.tdp.warehouse.bean.WMovement;
import it.polito.tdp.warehouse.bean.WObject;

public class SimMenoOccupato {
	
	
	private SimStat stats;
	private int localeVuoto;
	private Map<Integer,Double> fattCarico;
	private Map<Integer,WObject> mapObj;
	private List<WMovement> queue;

	public SimMenoOccupato(int n, Map<Integer, WObject> objectsMap, List<WMovement> list) {
		stats=new SimStat();
		mapObj=objectsMap;
		for(WObject otemp:mapObj.values()){
			otemp.getLocali().clear();
		}
		fattCarico=new HashMap<>();
		queue=list;
		for(int i=1;i<=n;i++){
			fattCarico.put(i, 0.0);
		}
	}
	
	
	public void run(){
		for(WMovement mtemp:queue){
			WObject obj=mapObj.get(mtemp.getOnjectId());
			
			switch(mtemp.getDirection()){
			
			case IN:
			
			localeVuoto=this.trovaLocaleVuoto();
			Double carico=(double) (obj.getSize()/300.0)+fattCarico.get(localeVuoto);
			
			if(carico<=1){
				fattCarico.put(localeVuoto, carico);
				stats.settTot(stats.gettTot()+(60+500*carico));
				
				if(carico >stats.getMaxCarico())
					stats.setMaxCarico(carico);
				
				obj.getLocali().add(localeVuoto);
			}else{
				stats.setDisastri(stats.getDisastri()+1);
			}
				
			break;
				
			case OUT:
				Random r=new Random();
				int nr=obj.getLocali().size();
				if(nr!=0){
				int pos=r.nextInt(obj.getLocali().size());
				int magazzino=obj.getLocali().get(pos);
				Double c=fattCarico.get(magazzino)-(double)(obj.getSize()/300.0);
				fattCarico.put(magazzino, c);
				stats.settTot(stats.gettTot()+(60+500*c));
				
				
				}else{
					stats.setDisastri(stats.getDisastri()+1);
				}
				
			break;
			
			
			}
			
		}
		
	}


	private int trovaLocaleVuoto() {
		double min=Double.MAX_VALUE;
		int locale=0;
		int counter=0;
		for(Double f:fattCarico.values()){
			counter++;
			if(f<min){
				min=f;
				locale=counter;
			}
		}
		return locale;
	}
	
	public SimStat getRisultati(){
		this.run();
		
		return stats;
	}

}
