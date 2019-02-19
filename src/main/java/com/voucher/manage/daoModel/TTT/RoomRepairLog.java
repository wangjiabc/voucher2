package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomRepairLog]")
public class RoomRepairLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="RoomAddress")
	private String RoomAddress;

    @SQLFloat(name="RoomCount")
	private Float RoomCount;

    @SQLFloat(name="Area")
	private Float Area;

    @SQLString(name="Type")
	private String Type;

    @SQLDateTime(name="RepairDate")
	private Date RepairDate;

    @SQLString(name="RepairItem")
	private String RepairItem;

    @SQLFloat(name="AmountTotal")
	private Float AmountTotal;

    @SQLFloat(name="Amount")
	private Float Amount;

    @SQLFloat(name="AuditingAmount")
	private Float AuditingAmount;

    @SQLString(name="WorkUnit")
	private String WorkUnit;

    @SQLString(name="AvailabeLength")
	private String AvailabeLength;

    @SQLString(name="Memo")
	private String Memo;

    @SQLString(name="Operator")
	private String Operator;

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

	public void setArea(Float Area){
		this.Area = Area;
	}

	public Float getArea(){
		return Area;
	}

	public void setType(String Type){
		this.Type = Type;
	}

	public String getType(){
		return Type;
	}

	public void setRepairDate(Date RepairDate){
		this.RepairDate = RepairDate;
	}

	public Date getRepairDate(){
		return RepairDate;
	}

	public void setRepairItem(String RepairItem){
		this.RepairItem = RepairItem;
	}

	public String getRepairItem(){
		return RepairItem;
	}

	public void setAmountTotal(Float AmountTotal){
		this.AmountTotal = AmountTotal;
	}

	public Float getAmountTotal(){
		return AmountTotal;
	}

	public void setAmount(Float Amount){
		this.Amount = Amount;
	}

	public Float getAmount(){
		return Amount;
	}

	public void setAuditingAmount(Float AuditingAmount){
		this.AuditingAmount = AuditingAmount;
	}

	public Float getAuditingAmount(){
		return AuditingAmount;
	}

	public void setWorkUnit(String WorkUnit){
		this.WorkUnit = WorkUnit;
	}

	public String getWorkUnit(){
		return WorkUnit;
	}

	public void setAvailabeLength(String AvailabeLength){
		this.AvailabeLength = AvailabeLength;
	}

	public String getAvailabeLength(){
		return AvailabeLength;
	}

	public void setMemo(String Memo){
		this.Memo = Memo;
	}

	public String getMemo(){
		return Memo;
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

