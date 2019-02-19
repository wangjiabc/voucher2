package com.voucher.manage.daoModel.TTT;

import java.sql.Clob;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Floor]")
public class Floor implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="Name")
	private String Name;

    @SQLString(name="structure")
	private String structure;

    @SQLInteger(name="Floors")
	private Integer Floors;

    @SQLInteger(name="FactRooms")
	private Integer FactRooms;

    @SQLClob(name="Memo")
	private Clob Memo;

    @SQLBoolean(name="NegativeOne")
	private Boolean NegativeOne;

    @SQLBoolean(name="NegativeTwo")
	private Boolean NegativeTwo;

    @SQLString(name="BelongUnit")
	private String BelongUnit;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setName(String Name){
		this.Name = Name;
	}

	public String getName(){
		return Name;
	}

	public void setStructure(String structure){
		this.structure = structure;
	}

	public String getStructure(){
		return structure;
	}

	public void setFloors(Integer Floors){
		this.Floors = Floors;
	}

	public Integer getFloors(){
		return Floors;
	}

	public void setFactRooms(Integer FactRooms){
		this.FactRooms = FactRooms;
	}

	public Integer getFactRooms(){
		return FactRooms;
	}

	public void setMemo(Clob Memo){
		this.Memo = Memo;
	}

	public Clob getMemo(){
		return Memo;
	}

	public void setNegativeOne(Boolean NegativeOne){
		this.NegativeOne = NegativeOne;
	}

	public Boolean getNegativeOne(){
		return NegativeOne;
	}

	public void setNegativeTwo(Boolean NegativeTwo){
		this.NegativeTwo = NegativeTwo;
	}

	public Boolean getNegativeTwo(){
		return NegativeTwo;
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

