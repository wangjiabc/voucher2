package com.voucher.manage.daoModel.TTT;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[FileRalate]")
public class FileRalate implements Serializable{

    private static final long serialVersionUID = 1L;

	@SQLInteger(name="id")
	private Integer id;

    @SQLString(name="LogGUID")
	private String LogGUID;

    @SQLString(name="UpFileFullName")
	private String UpFileFullName;

    @SQLString(name="FileTypeName")
	private String FileTypeName;

    @SQLInteger(name="FileIndex")
	private Integer FileIndex;

    @SQLString(name="ParentGUID")
	private String ParentGUID;

	public void setID(Integer ID){
		this.id = ID;
	}

	public Integer getID(){
		return id;
	}

	public void setLogGUID(String LogGUID){
		this.LogGUID = LogGUID;
	}

	public String getLogGUID(){
		return LogGUID;
	}

	public void setUpFileFullName(String UpFileFullName){
		this.UpFileFullName = UpFileFullName;
	}

	public String getUpFileFullName(){
		return UpFileFullName;
	}

	public void setFileTypeName(String FileTypeName){
		this.FileTypeName = FileTypeName;
	}

	public String getFileTypeName(){
		return FileTypeName;
	}

	public void setFileIndex(Integer FileIndex){
		this.FileIndex = FileIndex;
	}

	public Integer getFileIndex(){
		return FileIndex;
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

