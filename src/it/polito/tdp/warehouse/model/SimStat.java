package it.polito.tdp.warehouse.model;

public class SimStat {
	int disastri;
	double maxCarico;
	double tTot;
	
	public SimStat() {
		super();
		disastri=0;
		maxCarico=Integer.MIN_VALUE;
		tTot=0;
	}

	public int getDisastri() {
		return disastri;
	}

	public void setDisastri(int disastri) {
		this.disastri = disastri;
	}

	public double getMaxCarico() {
		return maxCarico;
	}

	public void setMaxCarico(double maxCarico) {
		this.maxCarico = maxCarico;
	}

	public double gettTot() {
		return tTot;
	}

	public void settTot(double tTot) {
		this.tTot = tTot;
	}
	
	
	
	
	

}
