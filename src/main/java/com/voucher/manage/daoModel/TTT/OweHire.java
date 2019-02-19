package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[OweHire]")
public class OweHire implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="CharterGUID")
	private String CharterGUID;

    @SQLString(name="CharterName")
	private String CharterName;

    @SQLString(name="RoomAddress")
	private String RoomAddress;

    @SQLString(name="RoomProperty")
	private String RoomProperty;

    @SQLString(name="sYear")
	private String sYear;

    @SQLFloat(name="Amount")
	private Float Amount;

    @SQLDateTime(name="sDate")
	private Date sDate;

    @SQLString(name="Operator")
	private String Operator;

    @SQLString(name="Type")
	private String Type;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setCharterGUID(String CharterGUID){
		this.CharterGUID = CharterGUID;
	}

	public String getCharterGUID(){
		return CharterGUID;
	}

	public void setCharterName(String CharterName){
		this.CharterName = CharterName;
	}

	public String getCharterName(){
		return CharterName;
	}

	public void setRoomAddress(String RoomAddress){
		this.RoomAddress = RoomAddress;
	}

	public String getRoomAddress(){
		return RoomAddress;
	}

	public void setRoomProperty(String RoomProperty){
		this.RoomProperty = RoomProperty;
	}

	public String getRoomProperty(){
		return RoomProperty;
	}

	public void setSYear(String sYear){
		this.sYear = sYear;
	}

	public String getSYear(){
		return sYear;
	}

	public void setAmount(Float Amount){
		this.Amount = Amount;
	}

	public Float getAmount(){
		return Amount;
	}

	public void setSDate(Date sDate){
		this.sDate = sDate;
	}

	public Date getSDate(){
		return sDate;
	}

	public void setOperator(String Operator){
		this.Operator = Operator;
	}

	public String getOperator(){
		return Operator;
	}

	public void setType(String Type){
		this.Type = Type;
	}

	public String getType(){
		return Type;
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

