package com.voucher.manage.daoModel.TTT;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomCoordinate]")
public class RoomCoordinate implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="RoomGUID")
	private String RoomGUID;

    @SQLString(name="RoomName")
	private String RoomName;

    @SQLInteger(name="Unit")
	private Integer Unit;

    @SQLInteger(name="Number")
	private Integer Number;

    @SQLString(name="FloorGUID")
	private String FloorGUID;

    @SQLBoolean(name="Ispublic")
	private Boolean Ispublic;

    @SQLBoolean(name="IsSingle")
	private Boolean IsSingle;

	public void setRoomGUID(String RoomGUID){
		this.RoomGUID = RoomGUID;
	}

	public String getRoomGUID(){
		return RoomGUID;
	}

	public void setRoomName(String RoomName){
		this.RoomName = RoomName;
	}

	public String getRoomName(){
		return RoomName;
	}

	public void setUnit(Integer Unit){
		this.Unit = Unit;
	}

	public Integer getUnit(){
		return Unit;
	}

	public void setNumber(Integer Number){
		this.Number = Number;
	}

	public Integer getNumber(){
		return Number;
	}

	public void setFloorGUID(String FloorGUID){
		this.FloorGUID = FloorGUID;
	}

	public String getFloorGUID(){
		return FloorGUID;
	}

	public void setIspublic(Boolean Ispublic){
		this.Ispublic = Ispublic;
	}

	public Boolean getIspublic(){
		return Ispublic;
	}

	public void setIsSingle(Boolean IsSingle){
		this.IsSingle = IsSingle;
	}

	public Boolean getIsSingle(){
		return IsSingle;
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

