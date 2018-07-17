package com.qxt.pojo;

import java.io.Serializable;
import java.util.Date;

public class Food implements Serializable {
    private Long id;

    private Long uId;

    private String name;

    private Double yuanPrice;

    private Double cutPrice;

    private String removed;

    private Date createTime;

    private Date updateTime;

    private String picture;

    private String inf;
   

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getYuanPrice() {
        return yuanPrice;
    }

    public void setYuanPrice(Double yuanPrice) {
        this.yuanPrice = yuanPrice;
    }

    public Double getCutPrice() {
        return cutPrice;
    }

    public void setCutPrice(Double cutPrice) {
        this.cutPrice = cutPrice;
    }

	public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed == null ? null : removed.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf == null ? null : inf.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Food other = (Food) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getuId() == null ? other.getuId() == null : this.getuId().equals(other.getuId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getYuanPrice() == null ? other.getYuanPrice() == null : this.getYuanPrice().equals(other.getYuanPrice()))
            && (this.getCutPrice() == null ? other.getCutPrice() == null : this.getCutPrice().equals(other.getCutPrice()))
            && (this.getRemoved() == null ? other.getRemoved() == null : this.getRemoved().equals(other.getRemoved()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getPicture() == null ? other.getPicture() == null : this.getPicture().equals(other.getPicture()))
            && (this.getInf() == null ? other.getInf() == null : this.getInf().equals(other.getInf()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getuId() == null) ? 0 : getuId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getYuanPrice() == null) ? 0 : getYuanPrice().hashCode());
        result = prime * result + ((getCutPrice() == null) ? 0 : getCutPrice().hashCode());
        result = prime * result + ((getRemoved() == null) ? 0 : getRemoved().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPicture() == null) ? 0 : getPicture().hashCode());
        result = prime * result + ((getInf() == null) ? 0 : getInf().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uId=").append(uId);
        sb.append(", name=").append(name);
        sb.append(", yuanPrice=").append(yuanPrice);
        sb.append(", cutPrice=").append(cutPrice);
        sb.append(", removed=").append(removed);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", picture=").append(picture);
        sb.append(", inf=").append(inf);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}