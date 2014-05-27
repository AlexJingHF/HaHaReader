package com.hahareader.bean;

import java.io.Serializable;

/**
 * 
 * 分类
 * 
 * @author alex
 * 
 */
public class CategoryItem implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7317456487135391231L;

	/**
	 * 分类ID
	 */
	private Integer id;

	/**
	 * 分类名
	 */
	private String name;

	/**
	 * 分类排列顺序
	 */
	private Integer orderId;

	/**
	 * 是否选中
	 */
	private Integer selected;

	public CategoryItem(int id, String name, int orderId, int selected)
	{
		this.id = Integer.valueOf(id);
		this.name = name;
		this.orderId = Integer.valueOf(orderId);
		this.selected = Integer.valueOf(selected);
	}

	@Override
	public String toString()
	{
		return "CategoryItem [id=" + this.id + ", name=" + this.name
				+ ", selected=" + this.selected + "]";
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public Integer getSelected()
	{
		return selected;
	}

	public void setSelected(Integer selected)
	{
		this.selected = selected;
	}
}
