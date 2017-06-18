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

public class WMovement implements Comparable<WMovement> {
	public enum directionEnum {
		IN, OUT
	};

	int time;
	int onjectId;
	directionEnum direction;

	/**
	 * @param time
	 * @param onjectId
	 * @param size
	 * @param direction
	 */
	public WMovement(int time, int onjectId, directionEnum direction) {
		super();
		this.time = time;
		this.onjectId = onjectId;
		this.direction = direction;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getOnjectId() {
		return onjectId;
	}

	public void setOnjectId(int onjectId) {
		this.onjectId = onjectId;
	}

	public directionEnum getDirection() {
		return direction;
	}

	public void setDirection(directionEnum direction) {
		this.direction = direction;
	}

	@Override
	public int compareTo(WMovement o) {
		return Integer.compare(this.time, o.time);
	}

	@Override
	public String toString() {
		return "WMovement [time=" + time + ", onjectId=" + onjectId
				+ ", direction=" + direction + "]";
	}
	
	
}
