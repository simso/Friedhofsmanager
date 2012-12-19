package de.fhflensburg.graveyardmanager.core;

import de.fhflensburg.graveyardmanager.utils.Timer;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 11:24
 */
public class Order {

	private String title;

	private int price;

	private String kindOfBurial;

	private int strafe;

	private Player owner;

	private String description;

	private Player declinedBy;

	private Timer expired;

	private Task tasks;

	private Timer startTime;

	private Timer acceptTime;

	private Task[] task;

	public void expires() {

	}

	public void fullfiledBy() {

	}

	public void declinedBy() {

	}

	public void operation() {

	}

	public String getTitle() {
		return null;
	}

	public int getPrice() {
		return 0;
	}

	public String getKindOfBurial() {
		return null;
	}

	public int getPenalty() {
		return 0;
	}

	public Player getOwner() {
		return null;
	}

	public String getDescription() {
		return null;
	}

	public Player getDeclinedBy() {
		return null;
	}

	public Timer getExpireDate() {
		return null;
	}

	public void getChecklist() {

	}

	public Timer getStartDate() {
		return null;
	}

	public Timer getAcceptTime() {
		return null;
	}

	public void addTask(int id, String title, String description) {

	}

	public Task getTaskById(int id) {
		return null;
	}

	public Task getTaskByTitle(String title) {
		return null;
	}

	public Task getAllTasks() {
		return null;
	}

}

