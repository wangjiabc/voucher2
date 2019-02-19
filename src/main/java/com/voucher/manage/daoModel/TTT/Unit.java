package com.voucher.manage.daoModel.TTT;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Unit]")
public class Unit implements Serializable{

    private static final long serialVersionUID = 1L;

	@SQLInteger(name="id")
	private Integer id;

    @SQLInteger(name="dIndex")
	private Integer dIndex;

    @SQLString(name="Name")
	private String Name;

    @SQLInteger(name="PerCount")
	private Integer PerCount;

    @SQLString(name="sOrder")
	private String sOrder;

    @SQLInteger(name="FactRoom")
	private Integer FactRoom;

    @SQLString(name="FloorGUID")
	private String FloorGUID;

    @SQLInteger(name="BeginNo")
	private Integer BeginNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDIndex(Integer dIndex){
		this.dIndex = dIndex;
	}

	public Integer getDIndex(){
		return dIndex;
	}

	public void setName(String Name){
		this.Name = Name;
	}

	public String getName(){
		return Name;
	}

	public void setPerCount(Integer PerCount){
		this.PerCount = PerCount;
	}

	public Integer getPerCount(){
		return PerCount;
	}

	public void setSOrder(String sOrder){
		this.sOrder = sOrder;
	}

	public String getSOrder(){
		return sOrder;
	}

	public void setFactRoom(Integer FactRoom){
		this.FactRoom = FactRoom;
	}

	public Integer getFactRoom(){
		return FactRoom;
	}

	public void setFloorGUID(String FloorGUID){
		this.FloorGUID = FloorGUID;
	}

	public String getFloorGUID(){
		return FloorGUID;
	}

	public void setBeginNo(Integer BeginNo){
		this.BeginNo = BeginNo;
	}

	public Integer getBeginNo(){
		return BeginNo;
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

