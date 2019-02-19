package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.sql.Clob;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomPawnLog]")
public class RoomPawnLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLClob(name="RoomAddress")
	private Clob RoomAddress;

    @SQLInteger(name="RoomCount")
	private Integer RoomCount;

    @SQLFloat(name="Amount")
	private Float Amount;

    @SQLString(name="PawnUnit")
	private String PawnUnit;

    @SQLString(name="PawnItem")
	private String PawnItem;

    @SQLDateTime(name="PawnDate")
	private Date PawnDate;

    @SQLDateTime(name="PawnEndDate")
	private Date PawnEndDate;

    @SQLString(name="Operator")
	private String Operator;

    @SQLString(name="OptDate")
	private String OptDate;

    @SQLClob(name="sMemo")
	private Clob sMemo;

    @SQLString(name="Belong")
	private String Belong;

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

	public void setAmount(Float Amount){
		this.Amount = Amount;
	}

	public Float getAmount(){
		return Amount;
	}

	public void setPawnUnit(String PawnUnit){
		this.PawnUnit = PawnUnit;
	}

	public String getPawnUnit(){
		return PawnUnit;
	}

	public void setPawnItem(String PawnItem){
		this.PawnItem = PawnItem;
	}

	public String getPawnItem(){
		return PawnItem;
	}

	public void setPawnDate(Date PawnDate){
		this.PawnDate = PawnDate;
	}

	public Date getPawnDate(){
		return PawnDate;
	}

	public void setPawnEndDate(Date PawnEndDate){
		this.PawnEndDate = PawnEndDate;
	}

	public Date getPawnEndDate(){
		return PawnEndDate;
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

	public void setSMemo(Clob sMemo){
		this.sMemo = sMemo;
	}

	public Clob getSMemo(){
		return sMemo;
	}

	public void setBelong(String Belong){
		this.Belong = Belong;
	}

	public String getBelong(){
		return Belong;
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

