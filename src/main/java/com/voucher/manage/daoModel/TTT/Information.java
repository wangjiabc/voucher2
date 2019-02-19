package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.sql.Clob;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Information]")
public class Information implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="Title")
	private String Title;

    @SQLDateTime(name="sDate")
	private Date sDate;

    @SQLClob(name="Content")
	private Clob Content;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setTitle(String Title){
		this.Title = Title;
	}

	public String getTitle(){
		return Title;
	}

	public void setSDate(Date sDate){
		this.sDate = sDate;
	}

	public Date getSDate(){
		return sDate;
	}

	public void setContent(Clob Content){
		this.Content = Content;
	}

	public Clob getContent(){
		return Content;
	}




/*
*数据库查询参数
*/
    @QualifiLimit(name="limit")
    private Integer limit;
    @QualifiOffset(name="offset")
    private Integer offset;
    @QualifiNotIn(name="notIn")
    private String notIn;
    @QualifiSort(name="sort")
    private String sort;
    @QualifiOrder(name="order")
    private String order;
    @QualifiWhere(name="where")
    private String[] where;
    @QualifiWhereTerm(name="whereTerm")
    private String whereTerm;


	public void setLimit(Integer limit){
		this.limit = limit;
	}

	public Integer getLimit(){
		return limit;
	}

	public void setOffset(Integer offset){
		this.offset = offset;
	}

	public Integer getOffset(){
		return offset;
	}

	public void setNotIn(String notIn){
		this.notIn = notIn;
	}

	public String getNotIn(){
		return notIn;
	}

	public void setSort(String sort){
		this.sort = sort;
	}

	public String getSort(){
		return sort;
	}

	public void setOrder(String order){
		this.order = order;
	}

	public String getOrder(){
		return order;
	}

	public void setWhere(String[] where){
		this.where = where;
	}

	public String[] getWhere(){
		return where;
	}

	public void setWhereTerm(String whereTerm){
		this.whereTerm = whereTerm;
	}

	public String getWhereTerm(){
		return whereTerm;
	}

}

