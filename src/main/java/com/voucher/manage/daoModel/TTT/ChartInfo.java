package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[ChartInfo]")
public class ChartInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="HireGUID")
	private String HireGUID;

    @SQLString(name="RoomAddress")
	private String RoomAddress;

    @SQLString(name="Charter")
	private String Charter;

    @SQLString(name="ChartType")
	private String ChartType;

    @SQLString(name="CharterProperty")
	private String CharterProperty;

    @SQLString(name="Sex")
	private String Sex;

    @SQLString(name="CardType")
	private String CardType;

    @SQLString(name="IDNo")
	private String IDNo;

    @SQLString(name="Corporation")
	private String Corporation;

    @SQLString(name="Phone")
	private String Phone;

    @SQLString(name="HomeAddress")
	private String HomeAddress;

    @SQLString(name="ChartProperty")
	private String ChartProperty;

    @SQLString(name="ContractNo")
	private String ContractNo;

    @SQLDateTime(name="ConcludeDate")
	private Date ConcludeDate;

    @SQLDateTime(name="ChartBeginDate")
	private Date ChartBeginDate;

    @SQLDateTime(name="ChartEndDate")
	private Date ChartEndDate;

    @SQLFloat(name="AssureAmount")
	private Float AssureAmount;

    @SQLFloat(name="Hire")
	private Float Hire;

    @SQLFloat(name="ChartCriterion")
	private Float ChartCriterion;

    @SQLFloat(name="Area")
	private Float Area;

    @SQLFloat(name="TotalHire")
	private Float TotalHire;

    @SQLFloat(name="AddRate")
	private Float AddRate;

    @SQLString(name="AddType")
	private String AddType;

    @SQLString(name="BeginAddDate")
	private String BeginAddDate;

    @SQLDateTime(name="HireEndDate")
	private Date HireEndDate;

    @SQLFloat(name="AccountBalance")
	private Float AccountBalance;

    @SQLString(name="OweHireResion")
	private String OweHireResion;

    @SQLString(name="CreditRating")
	private String CreditRating;

    @SQLString(name="FareItem")
	private String FareItem;

    @SQLString(name="ShopName")
	private String ShopName;

    @SQLString(name="LowProtect")
	private String LowProtect;

    @SQLString(name="CharterCode")
	private String CharterCode;

    @SQLInteger(name="CalculateHireDay")
	private Integer CalculateHireDay;

    @SQLString(name="Memo")
	private String Memo;

    @SQLBoolean(name="IsHistory")
	private Boolean IsHistory;

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

    @SQLString(name="Segment")
	private String Segment;

    @SQLString(name="BelongUnit")
	private String BelongUnit;

    @SQLString(name="Operator")
	private String Operator;

    @SQLInteger(name="AgeLimite")
	private Integer AgeLimite;

    @SQLBoolean(name="IsStopHireIn")
	private Boolean IsStopHireIn;

    @SQLString(name="StopHireInResion")
	private String StopHireInResion;

    @SQLString(name="Manager")
	private String Manager;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setHireGUID(String HireGUID){
		this.HireGUID = HireGUID;
	}

	public String getHireGUID(){
		return HireGUID;
	}

	public void setRoomAddress(String RoomAddress){
		this.RoomAddress = RoomAddress;
	}

	public String getRoomAddress(){
		return RoomAddress;
	}

	public void setCharter(String Charter){
		this.Charter = Charter;
	}

	public String getCharter(){
		return Charter;
	}

	public void setChartType(String ChartType){
		this.ChartType = ChartType;
	}

	public String getChartType(){
		return ChartType;
	}

	public void setCharterProperty(String CharterProperty){
		this.CharterProperty = CharterProperty;
	}

	public String getCharterProperty(){
		return CharterProperty;
	}

	public void setSex(String Sex){
		this.Sex = Sex;
	}

	public String getSex(){
		return Sex;
	}

	public void setCardType(String CardType){
		this.CardType = CardType;
	}

	public String getCardType(){
		return CardType;
	}

	public void setIDNo(String IDNo){
		this.IDNo = IDNo;
	}

	public String getIDNo(){
		return IDNo;
	}

	public void setCorporation(String Corporation){
		this.Corporation = Corporation;
	}

	public String getCorporation(){
		return Corporation;
	}

	public void setPhone(String Phone){
		this.Phone = Phone;
	}

	public String getPhone(){
		return Phone;
	}

	public void setHomeAddress(String HomeAddress){
		this.HomeAddress = HomeAddress;
	}

	public String getHomeAddress(){
		return HomeAddress;
	}

	public void setChartProperty(String ChartProperty){
		this.ChartProperty = ChartProperty;
	}

	public String getChartProperty(){
		return ChartProperty;
	}

	public void setContractNo(String ContractNo){
		this.ContractNo = ContractNo;
	}

	public String getContractNo(){
		return ContractNo;
	}

	public void setConcludeDate(Date ConcludeDate){
		this.ConcludeDate = ConcludeDate;
	}

	public Date getConcludeDate(){
		return ConcludeDate;
	}

	public void setChartBeginDate(Date ChartBeginDate){
		this.ChartBeginDate = ChartBeginDate;
	}

	public Date getChartBeginDate(){
		return ChartBeginDate;
	}

	public void setChartEndDate(Date ChartEndDate){
		this.ChartEndDate = ChartEndDate;
	}

	public Date getChartEndDate(){
		return ChartEndDate;
	}

	public void setAssureAmount(Float AssureAmount){
		this.AssureAmount = AssureAmount;
	}

	public Float getAssureAmount(){
		return AssureAmount;
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

	public void setArea(Float Area){
		this.Area = Area;
	}

	public Float getArea(){
		return Area;
	}

	public void setTotalHire(Float TotalHire){
		this.TotalHire = TotalHire;
	}

	public Float getTotalHire(){
		return TotalHire;
	}

	public void setAddRate(Float AddRate){
		this.AddRate = AddRate;
	}

	public Float getAddRate(){
		return AddRate;
	}

	public void setAddType(String AddType){
		this.AddType = AddType;
	}

	public String getAddType(){
		return AddType;
	}

	public void setBeginAddDate(String BeginAddDate){
		this.BeginAddDate = BeginAddDate;
	}

	public String getBeginAddDate(){
		return BeginAddDate;
	}

	public void setHireEndDate(Date HireEndDate){
		this.HireEndDate = HireEndDate;
	}

	public Date getHireEndDate(){
		return HireEndDate;
	}

	public void setAccountBalance(Float AccountBalance){
		this.AccountBalance = AccountBalance;
	}

	public Float getAccountBalance(){
		return AccountBalance;
	}

	public void setOweHireResion(String OweHireResion){
		this.OweHireResion = OweHireResion;
	}

	public String getOweHireResion(){
		return OweHireResion;
	}

	public void setCreditRating(String CreditRating){
		this.CreditRating = CreditRating;
	}

	public String getCreditRating(){
		return CreditRating;
	}

	public void setFareItem(String FareItem){
		this.FareItem = FareItem;
	}

	public String getFareItem(){
		return FareItem;
	}

	public void setShopName(String ShopName){
		this.ShopName = ShopName;
	}

	public String getShopName(){
		return ShopName;
	}

	public void setLowProtect(String LowProtect){
		this.LowProtect = LowProtect;
	}

	public String getLowProtect(){
		return LowProtect;
	}

	public void setCharterCode(String CharterCode){
		this.CharterCode = CharterCode;
	}

	public String getCharterCode(){
		return CharterCode;
	}

	public void setCalculateHireDay(Integer CalculateHireDay){
		this.CalculateHireDay = CalculateHireDay;
	}

	public Integer getCalculateHireDay(){
		return CalculateHireDay;
	}

	public void setMemo(String Memo){
		this.Memo = Memo;
	}

	public String getMemo(){
		return Memo;
	}

	public void setIsHistory(Boolean IsHistory){
		this.IsHistory = IsHistory;
	}

	public Boolean getIsHistory(){
		return IsHistory;
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

	public void setSegment(String Segment){
		this.Segment = Segment;
	}

	public String getSegment(){
		return Segment;
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

	public void setAgeLimite(Integer AgeLimite){
		this.AgeLimite = AgeLimite;
	}

	public Integer getAgeLimite(){
		return AgeLimite;
	}

	public void setIsStopHireIn(Boolean IsStopHireIn){
		this.IsStopHireIn = IsStopHireIn;
	}

	public Boolean getIsStopHireIn(){
		return IsStopHireIn;
	}

	public void setStopHireInResion(String StopHireInResion){
		this.StopHireInResion = StopHireInResion;
	}

	public String getStopHireInResion(){
		return StopHireInResion;
	}

	public void setManager(String Manager){
		this.Manager = Manager;
	}

	public String getManager(){
		return Manager;
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

