package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomSellLog]")
public class RoomSellLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="RoomAddress")
	private String RoomAddress;

    @SQLFloat(name="BuildAcreage")
	private Float BuildAcreage;

    @SQLInteger(name="RoomCount")
	private Integer RoomCount;

    @SQLInteger(name="Residence")
	private Integer Residence;

    @SQLInteger(name="Business")
	private Integer Business;

    @SQLString(name="CharterName")
	private String CharterName;

    @SQLString(name="Sex")
	private String Sex;

    @SQLString(name="IDNo")
	private String IDNo;

    @SQLString(name="CharterProperty")
	private String CharterProperty;

    @SQLString(name="Phone")
	private String Phone;

    @SQLDateTime(name="SellDate")
	private Date SellDate;

    @SQLFloat(name="SellAmount")
	private Float SellAmount;

    @SQLFloat(name="PerAmount")
	private Float PerAmount;

    @SQLString(name="sMemo")
	private String sMemo;

    @SQLString(name="operator")
	private String operator;

    @SQLString(name="BelongUnit")
	private String BelongUnit;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setRoomAddress(String RoomAddress){
		this.RoomAddress = RoomAddress;
	}

	public String getRoomAddress(){
		return RoomAddress;
	}

	public void setBuildAcreage(Float BuildAcreage){
		this.BuildAcreage = BuildAcreage;
	}

	public Float getBuildAcreage(){
		return BuildAcreage;
	}

	public void setRoomCount(Integer RoomCount){
		this.RoomCount = RoomCount;
	}

	public Integer getRoomCount(){
		return RoomCount;
	}

	public void setResidence(Integer Residence){
		this.Residence = Residence;
	}

	public Integer getResidence(){
		return Residence;
	}

	public void setBusiness(Integer Business){
		this.Business = Business;
	}

	public Integer getBusiness(){
		return Business;
	}

	public void setCharterName(String CharterName){
		this.CharterName = CharterName;
	}

	public String getCharterName(){
		return CharterName;
	}

	public void setSex(String Sex){
		this.Sex = Sex;
	}

	public String getSex(){
		return Sex;
	}

	public void setIDNo(String IDNo){
		this.IDNo = IDNo;
	}

	public String getIDNo(){
		return IDNo;
	}

	public void setCharterProperty(String CharterProperty){
		this.CharterProperty = CharterProperty;
	}

	public String getCharterProperty(){
		return CharterProperty;
	}

	public void setPhone(String Phone){
		this.Phone = Phone;
	}

	public String getPhone(){
		return Phone;
	}

	public void setSellDate(Date SellDate){
		this.SellDate = SellDate;
	}

	public Date getSellDate(){
		return SellDate;
	}

	public void setSellAmount(Float SellAmount){
		this.SellAmount = SellAmount;
	}

	public Float getSellAmount(){
		return SellAmount;
	}

	public void setPerAmount(Float PerAmount){
		this.PerAmount = PerAmount;
	}

	public Float getPerAmount(){
		return PerAmount;
	}

	public void setSMemo(String sMemo){
		this.sMemo = sMemo;
	}

	public String getSMemo(){
		return sMemo;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public String getOperator(){
		return operator;
	}

	public void setBelongUnit(String BelongUnit){
		this.BelongUnit = BelongUnit;
	}

	public String getBelongUnit(){
		return BelongUnit;
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

