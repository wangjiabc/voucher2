package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.sql.Clob;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomEvaluationLog]")
public class RoomEvaluationLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLClob(name="RoomAddress")
	private Clob RoomAddress;

    @SQLDateTime(name="EvaluationPlace")
	private Date EvaluationPlace;

    @SQLString(name="EvaluationUnit")
	private String EvaluationUnit;

    @SQLString(name="EvaluationNo")
	private String EvaluationNo;

    @SQLFloat(name="EvaluationPrice")
	private Float EvaluationPrice;

    @SQLFloat(name="EvaluationSinglePrice")
	private Float EvaluationSinglePrice;

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

	public void setEvaluationPlace(Date EvaluationPlace){
		this.EvaluationPlace = EvaluationPlace;
	}

	public Date getEvaluationPlace(){
		return EvaluationPlace;
	}

	public void setEvaluationUnit(String EvaluationUnit){
		this.EvaluationUnit = EvaluationUnit;
	}

	public String getEvaluationUnit(){
		return EvaluationUnit;
	}

	public void setEvaluationNo(String EvaluationNo){
		this.EvaluationNo = EvaluationNo;
	}

	public String getEvaluationNo(){
		return EvaluationNo;
	}

	public void setEvaluationPrice(Float EvaluationPrice){
		this.EvaluationPrice = EvaluationPrice;
	}

	public Float getEvaluationPrice(){
		return EvaluationPrice;
	}

	public void setEvaluationSinglePrice(Float EvaluationSinglePrice){
		this.EvaluationSinglePrice = EvaluationSinglePrice;
	}

	public Float getEvaluationSinglePrice(){
		return EvaluationSinglePrice;
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

