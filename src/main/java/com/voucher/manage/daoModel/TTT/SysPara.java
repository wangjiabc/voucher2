package com.voucher.manage.daoModel.TTT;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[SysPara]")
public class SysPara implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="sysID")
	private String sysID;

    @SQLString(name="sysValue")
	private String sysValue;

    @SQLString(name="sysMemo")
	private String sysMemo;

	public void setSysID(String sysID){
		this.sysID = sysID;
	}

	public String getSysID(){
		return sysID;
	}

	public void setSysValue(String sysValue){
		this.sysValue = sysValue;
	}

	public String getSysValue(){
		return sysValue;
	}

	public void setSysMemo(String sysMemo){
		this.sysMemo = sysMemo;
	}

	public String getSysMemo(){
		return sysMemo;
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

