package com.challange;

public class DirectRouteResponse {

	private int depSid;
	private int arrSid;
	private boolean directBusRoute;
	
	public int getDepSid() {
		return depSid;
	}
	public void setDepSid(int depSid) {
		this.depSid = depSid;
	}
	public int getArrSid() {
		return arrSid;
	}
	public void setArrSid(int arrSid) {
		this.arrSid = arrSid;
	}
	public boolean isDirectBusRoute() {
		return directBusRoute;
	}
	public void setDirectBusRoute(boolean directBusRoute) {
		this.directBusRoute = directBusRoute;
	}
	@Override
	public String toString() {
		return "DirectRouteResponse [depSid=" + depSid + ", arrSid=" + arrSid
				+ ", directBusRoute=" + directBusRoute + "]";
	}
	
	
}
