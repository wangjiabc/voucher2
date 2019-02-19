package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[User_AccessTime]")
public class User_AccessTime implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="open_id")
	private String open_id;

    @SQLDateTime(name="first")
	private Date first;

    @SQLInteger(name="first_count")
	private Integer first_count;

    @SQLDateTime(name="finance")
	private Date finance;

    @SQLInteger(name="finance_count")
	private Integer finance_count;

    @SQLDateTime(name="matureChartInfo")
	private Date matureChartInfo;

    @SQLInteger(name="matureCharInfo_count")
	private Integer matureCharInfo_count;

    @SQLDateTime(name="overdueChartInfo")
	private Date overdueChartInfo;

    @SQLInteger(name="overdueChartInfo_count")
	private Integer overdueChartInfo_count;

	public void setOpen_id(String open_id){
		this.open_id = open_id;
	}

	public String getOpen_id(){
		return open_id;
	}

	public void setFirst(Date first){
		this.first = first;
	}

	public Date getFirst(){
		return first;
	}

	public void setFirst_count(Integer first_count){
		this.first_count = first_count;
	}

	public Integer getFirst_count(){
		return first_count;
	}

	public void setFinance(Date finance){
		this.finance = finance;
	}

	public Date getFinance(){
		return finance;
	}

	public void setFinance_count(Integer finance_count){
		this.finance_count = finance_count;
	}

	public Integer getFinance_count(){
		return finance_count;
	}

	public void setMatureChartInfo(Date matureChartInfo){
		this.matureChartInfo = matureChartInfo;
	}

	public Date getMatureChartInfo(){
		return matureChartInfo;
	}

	public void setMatureCharInfo_count(Integer matureCharInfo_count){
		this.matureCharInfo_count = matureCharInfo_count;
	}

	public Integer getMatureCharInfo_count(){
		return matureCharInfo_count;
	}

	public void setOverdueChartInfo(Date overdueChartInfo){
		this.overdueChartInfo = overdueChartInfo;
	}

	public Date getOverdueChartInfo(){
		return overdueChartInfo;
	}

	public void setOverdueChartInfo_count(Integer overdueChartInfo_count){
		this.overdueChartInfo_count = overdueChartInfo_count;
	}

	public Integer getOverdueChartInfo_count(){
		return overdueChartInfo_count;
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

