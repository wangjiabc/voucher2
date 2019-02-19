package com.voucher.manage.daoModel.TTT;

import java.io.Serializable;

import com.voucher.manage.daoSQL.annotations.*;

@DBTable(name="[Nsspxx]")
public class Nsspxx implements Serializable{

    private static final long serialVersionUID = 1L;

	@SQLInteger(name="id")
	private Integer id;

    @SQLString(name="Spbh")
	private String Spbh;

    @SQLString(name="Spmc")
	private String Spmc;

    @SQLString(name="Jldw")
	private String Jldw;

    @SQLString(name="Sl")
	private String Sl;

    @SQLString(name="NsrXXGUID")
	private String NsrXXGUID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSpbh(String Spbh){
		this.Spbh = Spbh;
	}

	public String getSpbh(){
		return Spbh;
	}

	public void setSpmc(String Spmc){
		this.Spmc = Spmc;
	}

	public String getSpmc(){
		return Spmc;
	}

	public void setJldw(String Jldw){
		this.Jldw = Jldw;
	}

	public String getJldw(){
		return Jldw;
	}

	public void setSl(String Sl){
		this.Sl = Sl;
	}

	public String getSl(){
		return Sl;
	}

	public void setNsrXXGUID(String NsrXXGUID){
		this.NsrXXGUID = NsrXXGUID;
	}

	public String getNsrXXGUID(){
		return NsrXXGUID;
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

