package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomDispartLog]")
public class RoomDispartLog implements Serializable{

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

    @SQLDateTime(name="DispartDate")
	private Date DispartDate;

    @SQLString(name="Unit")
	private String Unit;

    @SQLString(name="Operator")
	private String Operator;

    @SQLDateTime(name="OptDate")
	private Date OptDate;

    @SQLString(name="sMemo")
	private String sMemo;

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

	public void setDispartDate(Date DispartDate){
		this.DispartDate = DispartDate;
	}

	public Date getDispartDate(){
		return DispartDate;
	}

	public void setUnit(String Unit){
		this.Unit = Unit;
	}

	public String getUnit(){
		return Unit;
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

	public void setSMemo(String sMemo){
		this.sMemo = sMemo;
	}

	public String getSMemo(){
		return sMemo;
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

