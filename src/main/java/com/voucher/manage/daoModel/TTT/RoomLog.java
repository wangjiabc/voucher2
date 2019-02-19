package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.sql.Clob;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomLog]")
public class RoomLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="RoomGUID")
	private String RoomGUID;

    @SQLDateTime(name="ActionDate")
	private Date ActionDate;

    @SQLString(name="ActionType")
	private String ActionType;

    @SQLClob(name="Instruction")
	private Clob Instruction;

    @SQLString(name="Operator")
	private String Operator;

    @SQLDateTime(name="LogDate")
	private Date LogDate;

    @SQLString(name="IsFile")
	private String IsFile;

    @SQLString(name="FileParentGUID")
	private String FileParentGUID;

    @SQLString(name="ParentGUID")
	private String ParentGUID;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setRoomGUID(String RoomGUID){
		this.RoomGUID = RoomGUID;
	}

	public String getRoomGUID(){
		return RoomGUID;
	}

	public void setActionDate(Date ActionDate){
		this.ActionDate = ActionDate;
	}

	public Date getActionDate(){
		return ActionDate;
	}

	public void setActionType(String ActionType){
		this.ActionType = ActionType;
	}

	public String getActionType(){
		return ActionType;
	}

	public void setInstruction(Clob Instruction){
		this.Instruction = Instruction;
	}

	public Clob getInstruction(){
		return Instruction;
	}

	public void setOperator(String Operator){
		this.Operator = Operator;
	}

	public String getOperator(){
		return Operator;
	}

	public void setLogDate(Date LogDate){
		this.LogDate = LogDate;
	}

	public Date getLogDate(){
		return LogDate;
	}

	public void setIsFile(String IsFile){
		this.IsFile = IsFile;
	}

	public String getIsFile(){
		return IsFile;
	}

	public void setFileParentGUID(String FileParentGUID){
		this.FileParentGUID = FileParentGUID;
	}

	public String getFileParentGUID(){
		return FileParentGUID;
	}

	public void setParentGUID(String ParentGUID){
		this.ParentGUID = ParentGUID;
	}

	public String getParentGUID(){
		return ParentGUID;
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

