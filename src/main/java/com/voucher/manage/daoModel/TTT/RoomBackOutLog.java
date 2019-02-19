package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.sql.Clob;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomBackOutLog]")
public class RoomBackOutLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLClob(name="RoomAddress")
	private Clob RoomAddress;

    @SQLInteger(name="RoomCount")
	private Integer RoomCount;

    @SQLInteger(name="Residence")
	private Integer Residence;

    @SQLInteger(name="Business")
	private Integer Business;

    @SQLFloat(name="Area")
	private Float Area;

    @SQLString(name="BackOutResion")
	private String BackOutResion;

    @SQLFloat(name="BackOutAmount")
	private Float BackOutAmount;

    @SQLString(name="BackOutUnit")
	private String BackOutUnit;

    @SQLDateTime(name="BackOutDate")
	private Date BackOutDate;

    @SQLDateTime(name="OptDate")
	private Date OptDate;

    @SQLString(name="Operator")
	private String Operator;

    @SQLString(name="BelongUnit")
	private String BelongUnit;

    @SQLString(name="sMemo")
	private String sMemo;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setRoomAddress(Clob RoomAddress){
		this.RoomAddress = RoomAddress;
	}

	public Clob getRoomAddress(){
		return RoomAddress;
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

	public void setArea(Float Area){
		this.Area = Area;
	}

	public Float getArea(){
		return Area;
	}

	public void setBackOutResion(String BackOutResion){
		this.BackOutResion = BackOutResion;
	}

	public String getBackOutResion(){
		return BackOutResion;
	}

	public void setBackOutAmount(Float BackOutAmount){
		this.BackOutAmount = BackOutAmount;
	}

	public Float getBackOutAmount(){
		return BackOutAmount;
	}

	public void setBackOutUnit(String BackOutUnit){
		this.BackOutUnit = BackOutUnit;
	}

	public String getBackOutUnit(){
		return BackOutUnit;
	}

	public void setBackOutDate(Date BackOutDate){
		this.BackOutDate = BackOutDate;
	}

	public Date getBackOutDate(){
		return BackOutDate;
	}

	public void setOptDate(Date OptDate){
		this.OptDate = OptDate;
	}

	public Date getOptDate(){
		return OptDate;
	}

	public void setOperator(String Operator){
		this.Operator = Operator;
	}

	public String getOperator(){
		return Operator;
	}

	public void setBelongUnit(String BelongUnit){
		this.BelongUnit = BelongUnit;
	}

	public String getBelongUnit(){
		return BelongUnit;
	}

	public void setSMemo(String sMemo){
		this.sMemo = sMemo;
	}

	public String getSMemo(){
		return sMemo;
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

