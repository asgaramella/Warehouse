package it.polito.tdp.warehouse.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.polito.tdp.warehouse.bean.WMovement;
import it.polito.tdp.warehouse.bean.WObject;

public class SimPiùOccupato {
	
	
	private SimStat stats;
	private int localePieno;
	private Map<Integer,Double> fattCarico;
	private Map<Integer,WObject> mapObj;
	private List<WMovement> queue;

	public SimPiùOccupato(int n, Map<Integer, WObject> objectsMap, List<WMovement> list) {
		stats=new SimStat();
		mapObj=objectsMap;
		//devo pulire struttura dati da prec sim
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
			
			localePieno=this.trovaLocalePieno(obj);
			if(localePieno !=-1){
			Double carico=(double) (obj.getSize()/300.0)+fattCarico.get(localePieno);
			
			
				fattCarico.put(localePieno, carico);
				stats.settTot(stats.gettTot()+(60+500*carico));
				
				if(carico >stats.getMaxCarico())
					stats.setMaxCarico(carico);
				
				obj.getLocali().add(localePieno);
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
				Double carico=fattCarico.get(magazzino)-(double)(obj.getSize()/300.0);
				fattCarico.put(magazzino, carico);
				stats.settTot(stats.gettTot()+(60+500*carico));
				
				
				}else{
					stats.setDisastri(stats.getDisastri()+1);
				}
				
			break;
			
			
			}
			
		}
		
	}


	private int trovaLocalePieno(WObject obj) {
		double max=-3;
		int locale=-1;
		int counter=0;
		for(Double f:fattCarico.values()){
			counter++;
			double carico=fattCarico.get(counter)+(double)(obj.getSize()/300.0);
			if(f>max && carico<=1){
				
				max=f;
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

