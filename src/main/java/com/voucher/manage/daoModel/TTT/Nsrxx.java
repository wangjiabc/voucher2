package com.voucher.manage.daoModel.TTT;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Nsrxx]")
public class Nsrxx implements Serializable{

    private static final long serialVersionUID = 1L;

    @SQLString(name="GUID")
	private String GUID;

    @SQLString(name="Nsrsbh")
	private String Nsrsbh;

    @SQLString(name="Nsrmc")
	private String Nsrmc;

    @SQLString(name="Swszzsmm")
	private String Swszzsmm;

    @SQLString(name="Skpkl")
	private String Skpkl;

    @SQLString(name="Skpbh")
	private String Skpbh;

    @SQLString(name="Zcm")
	private String Zcm;

    @SQLString(name="dhjdz")
	private String dhjdz;

    @SQLString(name="khhjzh")
	private String khhjzh;

	public void setGUID(String GUID){
		this.GUID = GUID;
	}

	public String getGUID(){
		return GUID;
	}

	public void setNsrsbh(String Nsrsbh){
		this.Nsrsbh = Nsrsbh;
	}

	public String getNsrsbh(){
		return Nsrsbh;
	}

	public void setNsrmc(String Nsrmc){
		this.Nsrmc = Nsrmc;
	}

	public String getNsrmc(){
		return Nsrmc;
	}

	public void setSwszzsmm(String Swszzsmm){
		this.Swszzsmm = Swszzsmm;
	}

	public String getSwszzsmm(){
		return Swszzsmm;
	}

	public void setSkpkl(String Skpkl){
		this.Skpkl = Skpkl;
	}

	public String getSkpkl(){
		return Skpkl;
	}

	public void setSkpbh(String Skpbh){
		this.Skpbh = Skpbh;
	}

	public String getSkpbh(){
		return Skpbh;
	}

	public void setZcm(String Zcm){
		this.Zcm = Zcm;
	}

	public String getZcm(){
		return Zcm;
	}

	public void setDhjdz(String dhjdz){
		this.dhjdz = dhjdz;
	}

	public String getDhjdz(){
		return dhjdz;
	}

	public void setKhhjzh(String khhjzh){
		this.khhjzh = khhjzh;
	}

	public String getKhhjzh(){
		return khhjzh;
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

