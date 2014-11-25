package fr.mowitnow.core.model;

public class Lawn {
	
	private int xbound;
	private int ybound;
	public Lawn(int xbound, int ybound) {
		super();
		this.xbound = xbound;
		this.ybound = ybound;
	}
	public int getXbound() {
		return xbound;
	}
	public int getYbound() {
		return ybound;
	}
	public void setXbound(int xbound) {
		this.xbound = xbound;
	}
	public void setYbound(int ybound) {
		this.ybound = ybound;
	}
	@Override
	public String toString() {
		return String.format("Lawn [xbound=%s, ybound=%s]", xbound, ybound);
	}
	

}
