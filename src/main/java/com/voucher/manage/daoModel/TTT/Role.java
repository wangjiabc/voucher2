package com.voucher.manage.daoModel.TTT;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Role]")
public class Role implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="RoleName")
	private String RoleName;

    @SQLString(name="BelongUnit")
	private String BelongUnit;

    @SQLString(name="ViewList")
	private String ViewList;

    @SQLBoolean(name="AllList")
	private Boolean AllList;

    @SQLBoolean(name="SystemManage")
	private Boolean SystemManage;

    @SQLBoolean(name="systempara")
	private Boolean systempara;

    @SQLBoolean(name="RoleManage")
	private Boolean RoleManage;

    @SQLBoolean(name="usermanage")
	private Boolean usermanage;

    @SQLBoolean(name="dictionary")
	private Boolean dictionary;

    @SQLBoolean(name="FileRelate")
	private Boolean FileRelate;

    @SQLBoolean(name="SysLog")
	private Boolean SysLog;

    @SQLBoolean(name="RoomBaseData")
	private Boolean RoomBaseData;

    @SQLBoolean(name="addroom")
	private Boolean addroom;

    @SQLBoolean(name="updateroom")
	private Boolean updateroom;

    @SQLBoolean(name="delroom")
	private Boolean delroom;

    @SQLBoolean(name="updateChartInfo")
	private Boolean updateChartInfo;

    @SQLBoolean(name="EditOweHire")
	private Boolean EditOweHire;

    @SQLBoolean(name="BussinessOpt")
	private Boolean BussinessOpt;

    @SQLBoolean(name="chart")
	private Boolean chart;

    @SQLBoolean(name="Relet")
	private Boolean Relet;

    @SQLBoolean(name="HireIn")
	private Boolean HireIn;

    @SQLBoolean(name="exitchart")
	private Boolean exitchart;

    @SQLBoolean(name="changehire")
	private Boolean changehire;

    @SQLBoolean(name="changename")
	private Boolean changename;

    @SQLBoolean(name="UnChangeName")
	private Boolean UnChangeName;

    @SQLBoolean(name="Pawn")
	private Boolean Pawn;

    @SQLBoolean(name="CancelPawn")
	private Boolean CancelPawn;

    @SQLBoolean(name="repairmanage")
	private Boolean repairmanage;

    @SQLBoolean(name="RoomPropertyDecrease")
	private Boolean RoomPropertyDecrease;

    @SQLBoolean(name="backout")
	private Boolean backout;

    @SQLBoolean(name="sellout")
	private Boolean sellout;

    @SQLBoolean(name="destory")
	private Boolean destory;

    @SQLBoolean(name="dispart")
	private Boolean dispart;

    @SQLBoolean(name="roomover")
	private Boolean roomover;

    @SQLBoolean(name="RoomFileManage")
	private Boolean RoomFileManage;

    @SQLBoolean(name="FileAdd")
	private Boolean FileAdd;

    @SQLBoolean(name="FileDelete")
	private Boolean FileDelete;

    @SQLBoolean(name="FilePrint")
	private Boolean FilePrint;

    @SQLBoolean(name="SystemQuery")
	private Boolean SystemQuery;

    @SQLBoolean(name="HirelistQuery")
	private Boolean HirelistQuery;

    @SQLBoolean(name="OweHireQuery")
	private Boolean OweHireQuery;

    @SQLBoolean(name="roomlog")
	private Boolean roomlog;

    @SQLBoolean(name="RoomPrAdd")
	private Boolean RoomPrAdd;

    @SQLBoolean(name="RoomPrDecrease")
	private Boolean RoomPrDecrease;

    @SQLBoolean(name="roomrepairquery")
	private Boolean roomrepairquery;

    @SQLBoolean(name="RoomPawn")
	private Boolean RoomPawn;

    @SQLBoolean(name="SystemReport")
	private Boolean SystemReport;

    @SQLBoolean(name="detaillist")
	private Boolean detaillist;

    @SQLBoolean(name="currentstate")
	private Boolean currentstate;

    @SQLBoolean(name="chartinforeport")
	private Boolean chartinforeport;

    @SQLBoolean(name="roomcasereport")
	private Boolean roomcasereport;

    @SQLBoolean(name="Other")
	private Boolean Other;

    @SQLBoolean(name="delroomlog")
	private Boolean delroomlog;

    @SQLBoolean(name="FloorManage")
	private Boolean FloorManage;

    @SQLBoolean(name="PrintData")
	private Boolean PrintData;

    @SQLBoolean(name="complexquery")
	private Boolean complexquery;

    @SQLBoolean(name="chartquery")
	private Boolean chartquery;

    @SQLBoolean(name="hirelisttotal")
	private Boolean hirelisttotal;

    @SQLBoolean(name="hirephyear")
	private Boolean hirephyear;

    @SQLBoolean(name="hirelistft")
	private Boolean hirelistft;

    @SQLBoolean(name="depreciation")
	private Boolean depreciation;

    @SQLBoolean(name="messagecase")
	private Boolean messagecase;

    @SQLBoolean(name="HireList")
	private Boolean HireList;

    @SQLBoolean(name="Evaluation")
	private Boolean Evaluation;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setRoleName(String RoleName){
		this.RoleName = RoleName;
	}

	public String getRoleName(){
		return RoleName;
	}

	public void setBelongUnit(String BelongUnit){
		this.BelongUnit = BelongUnit;
	}

	public String getBelongUnit(){
		return BelongUnit;
	}

	public void setViewList(String ViewList){
		this.ViewList = ViewList;
	}

	public String getViewList(){
		return ViewList;
	}

	public void setAllList(Boolean AllList){
		this.AllList = AllList;
	}

	public Boolean getAllList(){
		return AllList;
	}

	public void setSystemManage(Boolean SystemManage){
		this.SystemManage = SystemManage;
	}

	public Boolean getSystemManage(){
		return SystemManage;
	}

	public void setSystempara(Boolean systempara){
		this.systempara = systempara;
	}

	public Boolean getSystempara(){
		return systempara;
	}

	public void setRoleManage(Boolean RoleManage){
		this.RoleManage = RoleManage;
	}

	public Boolean getRoleManage(){
		return RoleManage;
	}

	public void setUsermanage(Boolean usermanage){
		this.usermanage = usermanage;
	}

	public Boolean getUsermanage(){
		return usermanage;
	}

	public void setDictionary(Boolean dictionary){
		this.dictionary = dictionary;
	}

	public Boolean getDictionary(){
		return dictionary;
	}

	public void setFileRelate(Boolean FileRelate){
		this.FileRelate = FileRelate;
	}

	public Boolean getFileRelate(){
		return FileRelate;
	}

	public void setSysLog(Boolean SysLog){
		this.SysLog = SysLog;
	}

	public Boolean getSysLog(){
		return SysLog;
	}

	public void setRoomBaseData(Boolean RoomBaseData){
		this.RoomBaseData = RoomBaseData;
	}

	public Boolean getRoomBaseData(){
		return RoomBaseData;
	}

	public void setAddroom(Boolean addroom){
		this.addroom = addroom;
	}

	public Boolean getAddroom(){
		return addroom;
	}

	public void setUpdateroom(Boolean updateroom){
		this.updateroom = updateroom;
	}

	public Boolean getUpdateroom(){
		return updateroom;
	}

	public void setDelroom(Boolean delroom){
		this.delroom = delroom;
	}

	public Boolean getDelroom(){
		return delroom;
	}

	public void setUpdateChartInfo(Boolean updateChartInfo){
		this.updateChartInfo = updateChartInfo;
	}

	public Boolean getUpdateChartInfo(){
		return updateChartInfo;
	}

	public void setEditOweHire(Boolean EditOweHire){
		this.EditOweHire = EditOweHire;
	}

	public Boolean getEditOweHire(){
		return EditOweHire;
	}

	public void setBussinessOpt(Boolean BussinessOpt){
		this.BussinessOpt = BussinessOpt;
	}

	public Boolean getBussinessOpt(){
		return BussinessOpt;
	}

	public void setChart(Boolean chart){
		this.chart = chart;
	}

	public Boolean getChart(){
		return chart;
	}

	public void setRelet(Boolean Relet){
		this.Relet = Relet;
	}

	public Boolean getRelet(){
		return Relet;
	}

	public void setHireIn(Boolean HireIn){
		this.HireIn = HireIn;
	}

	public Boolean getHireIn(){
		return HireIn;
	}

	public void setExitchart(Boolean exitchart){
		this.exitchart = exitchart;
	}

	public Boolean getExitchart(){
		return exitchart;
	}

	public void setChangehire(Boolean changehire){
		this.changehire = changehire;
	}

	public Boolean getChangehire(){
		return changehire;
	}

	public void setChangename(Boolean changename){
		this.changename = changename;
	}

	public Boolean getChangename(){
		return changename;
	}

	public void setUnChangeName(Boolean UnChangeName){
		this.UnChangeName = UnChangeName;
	}

	public Boolean getUnChangeName(){
		return UnChangeName;
	}

	public void setPawn(Boolean Pawn){
		this.Pawn = Pawn;
	}

	public Boolean getPawn(){
		return Pawn;
	}

	public void setCancelPawn(Boolean CancelPawn){
		this.CancelPawn = CancelPawn;
	}

	public Boolean getCancelPawn(){
		return CancelPawn;
	}

	public void setRepairmanage(Boolean repairmanage){
		this.repairmanage = repairmanage;
	}

	public Boolean getRepairmanage(){
		return repairmanage;
	}

	public void setRoomPropertyDecrease(Boolean RoomPropertyDecrease){
		this.RoomPropertyDecrease = RoomPropertyDecrease;
	}

	public Boolean getRoomPropertyDecrease(){
		return RoomPropertyDecrease;
	}

	public void setBackout(Boolean backout){
		this.backout = backout;
	}

	public Boolean getBackout(){
		return backout;
	}

	public void setSellout(Boolean sellout){
		this.sellout = sellout;
	}

	public Boolean getSellout(){
		return sellout;
	}

	public void setDestory(Boolean destory){
		this.destory = destory;
	}

	public Boolean getDestory(){
		return destory;
	}

	public void setDispart(Boolean dispart){
		this.dispart = dispart;
	}

	public Boolean getDispart(){
		return dispart;
	}

	public void setRoomover(Boolean roomover){
		this.roomover = roomover;
	}

	public Boolean getRoomover(){
		return roomover;
	}

	public void setRoomFileManage(Boolean RoomFileManage){
		this.RoomFileManage = RoomFileManage;
	}

	public Boolean getRoomFileManage(){
		return RoomFileManage;
	}

	public void setFileAdd(Boolean FileAdd){
		this.FileAdd = FileAdd;
	}

	public Boolean getFileAdd(){
		return FileAdd;
	}

	public void setFileDelete(Boolean FileDelete){
		this.FileDelete = FileDelete;
	}

	public Boolean getFileDelete(){
		return FileDelete;
	}

	public void setFilePrint(Boolean FilePrint){
		this.FilePrint = FilePrint;
	}

	public Boolean getFilePrint(){
		return FilePrint;
	}

	public void setSystemQuery(Boolean SystemQuery){
		this.SystemQuery = SystemQuery;
	}

	public Boolean getSystemQuery(){
		return SystemQuery;
	}

	public void setHirelistQuery(Boolean HirelistQuery){
		this.HirelistQuery = HirelistQuery;
	}

	public Boolean getHirelistQuery(){
		return HirelistQuery;
	}

	public void setOweHireQuery(Boolean OweHireQuery){
		this.OweHireQuery = OweHireQuery;
	}

	public Boolean getOweHireQuery(){
		return OweHireQuery;
	}

	public void setRoomlog(Boolean roomlog){
		this.roomlog = roomlog;
	}

	public Boolean getRoomlog(){
		return roomlog;
	}

	public void setRoomPrAdd(Boolean RoomPrAdd){
		this.RoomPrAdd = RoomPrAdd;
	}

	public Boolean getRoomPrAdd(){
		return RoomPrAdd;
	}

	public void setRoomPrDecrease(Boolean RoomPrDecrease){
		this.RoomPrDecrease = RoomPrDecrease;
	}

	public Boolean getRoomPrDecrease(){
		return RoomPrDecrease;
	}

	public void setRoomrepairquery(Boolean roomrepairquery){
		this.roomrepairquery = roomrepairquery;
	}

	public Boolean getRoomrepairquery(){
		return roomrepairquery;
	}

	public void setRoomPawn(Boolean RoomPawn){
		this.RoomPawn = RoomPawn;
	}

	public Boolean getRoomPawn(){
		return RoomPawn;
	}

	public void setSystemReport(Boolean SystemReport){
		this.SystemReport = SystemReport;
	}

	public Boolean getSystemReport(){
		return SystemReport;
	}

	public void setDetaillist(Boolean detaillist){
		this.detaillist = detaillist;
	}

	public Boolean getDetaillist(){
		return detaillist;
	}

	public void setCurrentstate(Boolean currentstate){
		this.currentstate = currentstate;
	}

	public Boolean getCurrentstate(){
		return currentstate;
	}

	public void setChartinforeport(Boolean chartinforeport){
		this.chartinforeport = chartinforeport;
	}

	public Boolean getChartinforeport(){
		return chartinforeport;
	}

	public void setRoomcasereport(Boolean roomcasereport){
		this.roomcasereport = roomcasereport;
	}

	public Boolean getRoomcasereport(){
		return roomcasereport;
	}

	public void setOther(Boolean Other){
		this.Other = Other;
	}

	public Boolean getOther(){
		return Other;
	}

	public void setDelroomlog(Boolean delroomlog){
		this.delroomlog = delroomlog;
	}

	public Boolean getDelroomlog(){
		return delroomlog;
	}

	public void setFloorManage(Boolean FloorManage){
		this.FloorManage = FloorManage;
	}

	public Boolean getFloorManage(){
		return FloorManage;
	}

	public void setPrintData(Boolean PrintData){
		this.PrintData = PrintData;
	}

	public Boolean getPrintData(){
		return PrintData;
	}

	public void setComplexquery(Boolean complexquery){
		this.complexquery = complexquery;
	}

	public Boolean getComplexquery(){
		return complexquery;
	}

	public void setChartquery(Boolean chartquery){
		this.chartquery = chartquery;
	}

	public Boolean getChartquery(){
		return chartquery;
	}

	public void setHirelisttotal(Boolean hirelisttotal){
		this.hirelisttotal = hirelisttotal;
	}

	public Boolean getHirelisttotal(){
		return hirelisttotal;
	}

	public void setHirephyear(Boolean hirephyear){
		this.hirephyear = hirephyear;
	}

	public Boolean getHirephyear(){
		return hirephyear;
	}

	public void setHirelistft(Boolean hirelistft){
		this.hirelistft = hirelistft;
	}

	public Boolean getHirelistft(){
		return hirelistft;
	}

	public void setDepreciation(Boolean depreciation){
		this.depreciation = depreciation;
	}

	public Boolean getDepreciation(){
		return depreciation;
	}

	public void setMessagecase(Boolean messagecase){
		this.messagecase = messagecase;
	}

	public Boolean getMessagecase(){
		return messagecase;
	}

	public void setHireList(Boolean HireList){
		this.HireList = HireList;
	}

	public Boolean getHireList(){
		return HireList;
	}

	public void setEvaluation(Boolean Evaluation){
		this.Evaluation = Evaluation;
	}

	public Boolean getEvaluation(){
		return Evaluation;
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

