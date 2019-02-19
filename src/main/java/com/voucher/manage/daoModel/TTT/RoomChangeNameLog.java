package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[RoomChangeNameLog]")
public class RoomChangeNameLog implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="RoomAddress")
	private String RoomAddress;

    @SQLInteger(name="RoomCount")
	private Integer RoomCount;

    @SQLString(name="OCharter")
	private String OCharter;

    @SQLString(name="NCharter")
	private String NCharter;

    @SQLString(name="Relate")
	private String Relate;

    @SQLFloat(name="Amount")
	private Float Amount;

    @SQLString(name="ChangeType")
	private String ChangeType;

    @SQLDateTime(name="ConcludeDate")
	private Date ConcludeDate;

    @SQLString(name="Region")
	private String Region;

    @SQLString(name="ManageRegion")
	private String ManageRegion;

    @SQLString(name="RoomProperty")
	private String RoomProperty;

    @SQLString(name="Useful")
	private String Useful;

    @SQLString(name="OriginalUnit")
	private String OriginalUnit;

    @SQLString(name="BelongUnit")
	private String BelongUnit;

    @SQLDateTime(name="OptDate")
	private Date OptDate;

    @SQLString(name="Operator")
	private String Operator;

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

	public void setRoomCount(Integer RoomCount){
		this.RoomCount = RoomCount;
	}

	public Integer getRoomCount(){
		return RoomCount;
	}

	public void setOCharter(String OCharter){
		this.OCharter = OCharter;
	}

	public String getOCharter(){
		return OCharter;
	}

	public void setNCharter(String NCharter){
		this.NCharter = NCharter;
	}

	public String getNCharter(){
		return NCharter;
	}

	public void setRelate(String Relate){
		this.Relate = Relate;
	}

	public String getRelate(){
		return Relate;
	}

	public void setAmount(Float Amount){
		this.Amount = Amount;
	}

	public Float getAmount(){
		return Amount;
	}

	public void setChangeType(String ChangeType){
		this.ChangeType = ChangeType;
	}

	public String getChangeType(){
		return ChangeType;
	}

	public void setConcludeDate(Date ConcludeDate){
		this.ConcludeDate = ConcludeDate;
	}

	public Date getConcludeDate(){
		return ConcludeDate;
	}

	public void setRegion(String Region){
		this.Region = Region;
	}

	public String getRegion(){
		return Region;
	}

	public void setManageRegion(String ManageRegion){
		this.ManageRegion = ManageRegion;
	}

	public String getManageRegion(){
		return ManageRegion;
	}

	public void setRoomProperty(String RoomProperty){
		this.RoomProperty = RoomProperty;
	}

	public String getRoomProperty(){
		return RoomProperty;
	}

	public void setUseful(String Useful){
		this.Useful = Useful;
	}

	public String getUseful(){
		return Useful;
	}

	public void setOriginalUnit(String OriginalUnit){
		this.OriginalUnit = OriginalUnit;
	}

	public String getOriginalUnit(){
		return OriginalUnit;
	}

	public void setBelongUnit(String BelongUnit){
		this.BelongUnit = BelongUnit;
	}

	public String getBelongUnit(){
		return BelongUnit;
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

