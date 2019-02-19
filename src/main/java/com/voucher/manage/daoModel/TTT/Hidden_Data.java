package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Hidden_Data]")
public class Hidden_Data implements Serializable{

    private static final long serialVersionUID = 1L;

	@SQLInteger(name="id")
	private Integer id;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="NAME")
	private String NAME;

    @SQLString(name="TYPE")
	private String TYPE;

    @SQLString(name="URI")
	private String URI;

    @SQLDateTime(name="date")
	private Date date;

    @SQLString(name="campusAdmin")
	private String campusAdmin;

    @SQLString(name="terminal")
	private String terminal;

    @SQLString(name="FileBelong")
	private String FileBelong;

    @SQLInteger(name="FileIndex")
	private Integer FileIndex;

    @SQLString(name="UserName")
	private String UserName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
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

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return date;
	}

	public void setCampusAdmin(String campusAdmin){
		this.campusAdmin = campusAdmin;
	}

	public String getCampusAdmin(){
		return campusAdmin;
	}

	public void setTerminal(String terminal){
		this.terminal = terminal;
	}

	public String getTerminal(){
		return terminal;
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

	public void setUserName(String UserName){
		this.UserName = UserName;
	}

	public String getUserName(){
		return UserName;
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

