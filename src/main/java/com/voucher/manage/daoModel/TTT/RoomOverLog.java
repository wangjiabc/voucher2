package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.sql.Clob;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomOverLog]")
public class RoomOverLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="RoomAddress")
	private String RoomAddress;

    @SQLFloat(name="RoomCount")
	private Float RoomCount;

    @SQLInteger(name="Residence")
	private Integer Residence;

    @SQLInteger(name="Business")
	private Integer Business;

    @SQLFloat(name="Area")
	private Float Area;

    @SQLDateTime(name="OverDate")
	private Date OverDate;

    @SQLString(name="OverResion")
	private String OverResion;

    @SQLClob(name="sMemo")
	private Clob sMemo;

    @SQLString(name="Operator")
	private String Operator;

    @SQLString(name="OptDate")
	private String OptDate;

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

	public void setRoomCount(Float RoomCount){
		this.RoomCount = RoomCount;
	}

	public Float getRoomCount(){
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

	public void setArea(Float Area){
		this.Area = Area;
	}

	public Float getArea(){
		return Area;
	}

	public void setOverDate(Date OverDate){
		this.OverDate = OverDate;
	}

	public Date getOverDate(){
		return OverDate;
	}

	public void setOverResion(String OverResion){
		this.OverResion = OverResion;
	}

	public String getOverResion(){
		return OverResion;
	}

	public void setSMemo(Clob sMemo){
		this.sMemo = sMemo;
	}

	public Clob getSMemo(){
		return sMemo;
	}

	public void setOperator(String Operator){
		this.Operator = Operator;
	}

	public String getOperator(){
		return Operator;
	}

	public void setOptDate(String OptDate){
		this.OptDate = OptDate;
	}

	public String getOptDate(){
		return OptDate;
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

