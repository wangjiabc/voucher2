package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Assets_Check_Date]")
public class Assets_Check_Date implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLString(name="check_id")
	private String check_id;

    @SQLString(name="NAME")
	private String NAME;

    @SQLString(name="TYPE")
	private String TYPE;

    @SQLString(name="URI")
	private String URI;

    @SQLString(name="FileBelong")
	private String FileBelong;

    @SQLInteger(name="FileIndex")
	private Integer FileIndex;

    @SQLDateTime(name="date")
	private Date date;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setCheck_id(String check_id){
		this.check_id = check_id;
	}

	public String getCheck_id(){
		return check_id;
	}

	public void setNAME(String NAME){
		this.NAME = NAME;
	}

	public String getNAME(){
		return NAME;
	}

	public void setTYPE(String TYPE){
		this.TYPE = TYPE;
	}

	public String getTYPE(){
		return TYPE;
	}

	public void setURI(String URI){
		this.URI = URI;
	}

	public String getURI(){
		return URI;
	}

	public void setFileBelong(String FileBelong){
		this.FileBelong = FileBelong;
	}

	public String getFileBelong(){
		return FileBelong;
	}

	public void setFileIndex(Integer FileIndex){
		this.FileIndex = FileIndex;
	}

	public Integer getFileIndex(){
		return FileIndex;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return date;
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

