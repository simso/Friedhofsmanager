package de.fhflensburg.graveyardmanager.core;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 11:19
 */
public class Player {

	private int id;

	private String name;

	private int type;

	private int score;

	private int balance;

	private Order order;

	private Player()
	{
		balance = 10000;
		score = 0;
	}

	public Player(String name, int type)
	{
		this();
		this.name = name;
		this.type = type;
	}

	public Player(int id, String name, int type)
	{
		this(name, type);
		this.id = id;
	}

	public void update(Player player)
	{
		id = player.id;
		type = player.type;
		score = player.score;
		balance = player.balance;
		order = player.order;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public boolean isNPC()
	{
		return type == 1;
	}

	public boolean increaseBalance(int amount)
	{
		balance += amount;
		return true;
	}

	public boolean decreaseBalance(int amount)
	{
		if (balance - amount >= 0)
		{
			balance -= amount;
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int value)
	{
		balance = value;
	}

	public void resetBalance(int value)
	{
		setBalance(0);
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int value)
	{
		score = value;
	}

	public void decreaseScore(int value)
	{
		score += value;
	}

	public void increaseScore(int value)
	{
		score -= value;
	}

	public void resetScore()
	{
		setScore(0);
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

}
