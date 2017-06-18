/***************************************************************************\
 *               *                                                         *
 *    #####      *  (!) 2014 by Giovanni Squillero                         *
 *   ######      *  Politecnico di Torino - Dip. Automatica e Informatica  *
 *   ###   \     *  Cso Duca degli Abruzzi 24 / I-10129 TORINO / ITALY     *
 *    ##G  c\    *                                                         *
 *    #     _\   *  tel : +39-011-564.7092  /  Fax: +39-011-564.7099       *
 *    |   _/     *  mail: giovanni.squillero@polito.it                     *
 *    |  _/      *  www : http://www.cad.polito.it/staff/squillero/        *
 *               *                                                         *
\***************************************************************************/

package it.polito.tdp.warehouse.bean;

import java.util.ArrayList;
import java.util.List;

public class WObject {
	int id;
	String description;
	int size;
	List<Integer> locali;
	
	
	

	public WObject() {
		super();
		this.locali=new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	

	public List<Integer> getLocali() {
		return locali;
	}

	public void setLocali(List<Integer> locali) {
		this.locali = locali;
	}

	public WObject(int id, String description, int size) {
		super();
		this.id = id;
		this.description = description;
		this.size = size;
		this.locali=new ArrayList<>();
		
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WObject other = (WObject) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WObject [id=" + id + ", description='" + description + "', size="
				+ size + "]";
	}

}
