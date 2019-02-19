package com.voucher.manage.daoModel.TTT;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[SendMessage]")
public class SendMessage implements Serializable{

    private static final long serialVersionUID = 1L;

	@SQLInteger(name="id")
	private Integer id;

    @SQLString(name="Phone")
	private String Phone;

    @SQLString(name="SendWho")
	private String SendWho;

    @SQLString(name="Message")
	private String Message;

    @SQLString(name="SendDate")
	private String SendDate;

    @SQLString(name="SendTime")
	private String SendTime;

    @SQLString(name="SendType")
	private String SendType;

    @SQLString(name="State")
	private String State;

    @SQLString(name="BelongUnit")
	private String BelongUnit;

    @SQLString(name="Operator")
	private String Operator;

    @SQLString(name="ChartGUID")
	private String ChartGUID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPhone(String Phone){
		this.Phone = Phone;
	}

	public String getPhone(){
		return Phone;
	}

	public void setSendWho(String SendWho){
		this.SendWho = SendWho;
	}

	public String getSendWho(){
		return SendWho;
	}

	public void setMessage(String Message){
		this.Message = Message;
	}

	public String getMessage(){
		return Message;
	}

	public void setSendDate(String SendDate){
		this.SendDate = SendDate;
	}

	public String getSendDate(){
		return SendDate;
	}

	public void setSendTime(String SendTime){
		this.SendTime = SendTime;
	}

	public String getSendTime(){
		return SendTime;
	}

	public void setSendType(String SendType){
		this.SendType = SendType;
	}

	public String getSendType(){
		return SendType;
	}

	public void setState(String State){
		this.State = State;
	}

	public String getState(){
		return State;
	}

	public void setBelongUnit(String BelongUnit){
		this.BelongUnit = BelongUnit;
	}

	public String getBelongUnit(){
		return BelongUnit;
	}

	public void setOperator(String Operator){
		this.Operator = Operator;
	}

	public String getOperator(){
		return Operator;
	}

	public void setChartGUID(String ChartGUID){
		this.ChartGUID = ChartGUID;
	}

	public String getChartGUID(){
		return ChartGUID;
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

