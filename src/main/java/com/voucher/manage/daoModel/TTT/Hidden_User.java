package com.voucher.manage.daoModel.TTT;

import java.util.Date;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Hidden_User]")
public class Hidden_User implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLInteger(name="id")
	private Integer id;

    @SQLInteger(name="principal")
	private Integer principal;

    @SQLString(name="campusAdmin")
	private String campusAdmin;

    @SQLString(name="password")
	private String password;

    @SQLString(name="principal_name")
	private String principal_name;

    @SQLString(name="phone")
	private String phone;

    @SQLInteger(name="purview")
	private Integer purview;

    @SQLString(name="business")
	private String business;

    @SQLDateTime(name="enter_time")
	private Date enter_time;

    @SQLDateTime(name="register_time")
	private Date register_time;

    @SQLDateTime(name="rework_time")
	private Date rework_time;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setPrincipal(Integer principal){
		this.principal = principal;
	}

	public Integer getPrincipal(){
		return principal;
	}

	public void setCampusAdmin(String campusAdmin){
		this.campusAdmin = campusAdmin;
	}

	public String getCampusAdmin(){
		return campusAdmin;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPrincipal_name(String principal_name){
		this.principal_name = principal_name;
	}

	public String getPrincipal_name(){
		return principal_name;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setPurview(Integer purview){
		this.purview = purview;
	}

	public Integer getPurview(){
		return purview;
	}

	public void setBusiness(String business){
		this.business = business;
	}

	public String getBusiness(){
		return business;
	}

	public void setEnter_time(Date enter_time){
		this.enter_time = enter_time;
	}

	public Date getEnter_time(){
		return enter_time;
	}

	public void setRegister_time(Date register_time){
		this.register_time = register_time;
	}

	public Date getRegister_time(){
		return register_time;
	}

	public void setRework_time(Date rework_time){
		this.rework_time = rework_time;
	}

	public Date getRework_time(){
		return rework_time;
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

