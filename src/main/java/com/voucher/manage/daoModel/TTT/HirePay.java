package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[HirePay]")
public class HirePay implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLFloat(name="Amount")
	private Float Amount;

    @SQLString(name="PrintMemo")
	private String PrintMemo;

    @SQLFloat(name="PrintCount")
	private Float PrintCount;

    @SQLString(name="Operator")
	private String Operator;

    @SQLDateTime(name="OptDate")
	private Date OptDate;

    @SQLString(name="TicketNo")
	private String TicketNo;

    @SQLString(name="ChartGUID")
	private String ChartGUID;

    @SQLString(name="HireGUID")
	private String HireGUID;

    @SQLString(name="open_id")
	private String open_id;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setAmount(Float Amount){
		this.Amount = Amount;
	}

	public Float getAmount(){
		return Amount;
	}

	public void setPrintMemo(String PrintMemo){
		this.PrintMemo = PrintMemo;
	}

	public String getPrintMemo(){
		return PrintMemo;
	}

	public void setPrintCount(Float PrintCount){
		this.PrintCount = PrintCount;
	}

	public Float getPrintCount(){
		return PrintCount;
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

	public void setTicketNo(String TicketNo){
		this.TicketNo = TicketNo;
	}

	public String getTicketNo(){
		return TicketNo;
	}

	public void setChartGUID(String ChartGUID){
		this.ChartGUID = ChartGUID;
	}

	public String getChartGUID(){
		return ChartGUID;
	}

	public void setHireGUID(String HireGUID){
		this.HireGUID = HireGUID;
	}

	public String getHireGUID(){
		return HireGUID;
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

