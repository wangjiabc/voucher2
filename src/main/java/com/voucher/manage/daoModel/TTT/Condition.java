package com.voucher.manage.daoModel.TTT;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Condition]")
public class Condition implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="ParentGUID")
	private String ParentGUID;

    @SQLString(name="fldname")
	private String fldname;

    @SQLString(name="operaters")
	private String operaters;

    @SQLString(name="fldvalue")
	private String fldvalue;

    @SQLString(name="relation")
	private String relation;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setParentGUID(String ParentGUID){
		this.ParentGUID = ParentGUID;
	}

	public String getParentGUID(){
		return ParentGUID;
	}

	public void setFldname(String fldname){
		this.fldname = fldname;
	}

	public String getFldname(){
		return fldname;
	}

	public void setOperaters(String operaters){
		this.operaters = operaters;
	}

	public String getOperaters(){
		return operaters;
	}

	public void setFldvalue(String fldvalue){
		this.fldvalue = fldvalue;
	}

	public String getFldvalue(){
		return fldvalue;
	}

	public void setRelation(String relation){
		this.relation = relation;
	}

	public String getRelation(){
		return relation;
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

