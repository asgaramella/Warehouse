package it.polito.tdp.warehouse.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.warehouse.bean.WMovement;
import it.polito.tdp.warehouse.bean.WMovement.directionEnum;
import it.polito.tdp.warehouse.bean.WObject;
import it.polito.tdp.warehouse.db.WarehouseDAO;

public class Model {
	
	private WarehouseDAO dao;
	private List<WMovement> movements;
	private Map<Integer,WObject> objectsMap;
	private SimMenoOccupato sim;
	private SimPiùOccupato simulator;

	public Model() {
		super();
		dao= new  WarehouseDAO(); 
		
	}
	
	public List<WMovement> getAllMovements(){
		if(this.movements==null)
			movements=dao.getAllMovements();
			
			return movements;
	}
	
	public Map<Integer,WObject> getAllObjects(){
		if(this.objectsMap==null)
			objectsMap=dao.getAllObjects();
		
		return objectsMap;
	}
	
	public int getMaxCapienza(){
		int max=Integer.MIN_VALUE;
		int tot=0;
		this.getAllObjects();
		for(WMovement wm: this.getAllMovements()){
			if(wm.getDirection()==directionEnum.IN)
				tot+=this.objectsMap.get(wm.getOnjectId()).getSize();
			else
				tot-=this.objectsMap.get(wm.getOnjectId()).getSize();
			
			if(tot>max)
				max=tot;
			
		}
		
		
		
		return max;
	}

	public SimStat doSimulazione(String s,int N) {
		SimStat stats=null;
		switch(s){
    	case "Meno occupato":
    		sim=new SimMenoOccupato(N,objectsMap,this.getAllMovements());
    		stats=sim.getRisultati();
    		break;
    	
    		
    	case "Più occupato":
    		simulator=new SimPiùOccupato(N,objectsMap,this.getAllMovements());
    		stats=simulator.getRisultati();
    		break;
    	
    	}
		
		return stats;
	}
	
	
	
	


}
