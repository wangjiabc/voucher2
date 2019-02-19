package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[HireList]")
public class HireList implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="HireDate")
	private String HireDate;

    @SQLString(name="Instruction")
	private String Instruction;

    @SQLFloat(name="Hire")
	private Float Hire;

    @SQLFloat(name="ChartCriterion")
	private Float ChartCriterion;

    @SQLString(name="State")
	private String State;

    @SQLString(name="PayFashion")
	private String PayFashion;

    @SQLString(name="Operator")
	private String Operator;

    @SQLDateTime(name="OptDate")
	private Date OptDate;

    @SQLString(name="ChartGUID")
	private String ChartGUID;

    @SQLString(name="PayGUID")
	private String PayGUID;

    @SQLString(name="HireGUID")
	private String HireGUID;

    @SQLBoolean(name="IsAddHire")
	private Boolean IsAddHire;

    @SQLDateTime(name="OptAddDate")
	private Date OptAddDate;

    @SQLString(name="OptAddUser")
	private String OptAddUser;

    @SQLDateTime(name="OverDueDate")
	private Date OverDueDate;

    @SQLString(name="open_id")
	private String open_id;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setHireDate(String HireDate){
		this.HireDate = HireDate;
	}

	public String getHireDate(){
		return HireDate;
	}

	public void setInstruction(String Instruction){
		this.Instruction = Instruction;
	}

	public String getInstruction(){
		return Instruction;
	}

	public void setHire(Float Hire){
		this.Hire = Hire;
	}

	public Float getHire(){
		return Hire;
	}

	public void setChartCriterion(Float ChartCriterion){
		this.ChartCriterion = ChartCriterion;
	}

	public Float getChartCriterion(){
		return ChartCriterion;
	}

	public void setState(String State){
		this.State = State;
	}

	public String getState(){
		return State;
	}

	public void setPayFashion(String PayFashion){
		this.PayFashion = PayFashion;
	}

	public String getPayFashion(){
		return PayFashion;
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

	public void setChartGUID(String ChartGUID){
		this.ChartGUID = ChartGUID;
	}

	public String getChartGUID(){
		return ChartGUID;
	}

	public void setPayGUID(String PayGUID){
		this.PayGUID = PayGUID;
	}

	public String getPayGUID(){
		return PayGUID;
	}

	public void setHireGUID(String HireGUID){
		this.HireGUID = HireGUID;
	}

	public String getHireGUID(){
		return HireGUID;
	}

	public void setIsAddHire(Boolean IsAddHire){
		this.IsAddHire = IsAddHire;
	}

	public Boolean getIsAddHire(){
		return IsAddHire;
	}

	public void setOptAddDate(Date OptAddDate){
		this.OptAddDate = OptAddDate;
	}

	public Date getOptAddDate(){
		return OptAddDate;
	}

	public void setOptAddUser(String OptAddUser){
		this.OptAddUser = OptAddUser;
	}

	public String getOptAddUser(){
		return OptAddUser;
	}

	public void setOverDueDate(Date OverDueDate){
		this.OverDueDate = OverDueDate;
	}

	public Date getOverDueDate(){
		return OverDueDate;
	}

	public void setOpen_id(String open_id){
		this.open_id = open_id;
	}

	public String getOpen_id(){
		return open_id;
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

