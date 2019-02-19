package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[FileSelfBelong]")
public class FileSelfBelong implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="RoomGUID")
	private String RoomGUID;

    @SQLString(name="UpFileFullName")
	private String UpFileFullName;

    @SQLString(name="FileType")
	private String FileType;

    @SQLString(name="FileBelong")
	private String FileBelong;

    @SQLInteger(name="FileIndex")
	private Integer FileIndex;

    @SQLString(name="ViewFileName")
	private String ViewFileName;

    @SQLInteger(name="sequence")
	private Integer sequence;

    @SQLDateTime(name="date_time")
	private Date date_time;

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

	public void setUpFileFullName(String UpFileFullName){
		this.UpFileFullName = UpFileFullName;
	}

	public String getUpFileFullName(){
		return UpFileFullName;
	}

	public void setFileType(String FileType){
		this.FileType = FileType;
	}

	public String getFileType(){
		return FileType;
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

	public void setViewFileName(String ViewFileName){
		this.ViewFileName = ViewFileName;
	}

	public String getViewFileName(){
		return ViewFileName;
	}

	public void setSequence(Integer sequence){
		this.sequence = sequence;
	}

	public Integer getSequence(){
		return sequence;
	}

	public void setDate_time(Date date_time){
		this.date_time = date_time;
	}

	public Date getDate_time(){
		return date_time;
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

