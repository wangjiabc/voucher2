package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomDepreciation]")
public class RoomDepreciation implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="RoomGUID")
	private String RoomGUID;

    @SQLString(name="DepreciationDate")
	private String DepreciationDate;

    @SQLFloat(name="DepreciationAmount")
	private Float DepreciationAmount;

    @SQLString(name="DepreciationFashion")
	private String DepreciationFashion;

    @SQLString(name="Operator")
	private String Operator;

    @SQLDateTime(name="OptDate")
	private Date OptDate;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setRoomGUID(String RoomGUID){
		this.RoomGUID = RoomGUID;
	}

	public String getRoomGUID(){
		return RoomGUID;
	}

	public void setDepreciationDate(String DepreciationDate){
		this.DepreciationDate = DepreciationDate;
	}

	public String getDepreciationDate(){
		return DepreciationDate;
	}

	public void setDepreciationAmount(Float DepreciationAmount){
		this.DepreciationAmount = DepreciationAmount;
	}

	public Float getDepreciationAmount(){
		return DepreciationAmount;
	}

	public void setDepreciationFashion(String DepreciationFashion){
		this.DepreciationFashion = DepreciationFashion;
	}

	public String getDepreciationFashion(){
		return DepreciationFashion;
	}

	public void setOperator(String Operator){
		this.Operator = Operator;
	}

	public String getOperator(){
		return Operator;
	}

	public void setOptDate(Date OptDate){
		this.OptDate = OptDate;
	}

	public Date getOptDate(){
		return OptDate;
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

