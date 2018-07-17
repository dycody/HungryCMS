package com.qxt.pojo;

import java.io.Serializable;
import java.util.Date;

public class Orderu implements Serializable {
    private Long id;

    private Long courierId;

    private Long mergantId;

    private Long userId;

    private Double addPrice;

    private Double realPrice;

    private String recName;

    private String recTel;

    private String locationFrom;

    private String locationTo;

    private Date createTime;

    private Date updateTime;

    private String removed;

    private String reach;
    
    private Mergant mergant;
    
    private Courier courier;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Long getMergantId() {
        return mergantId;
    }

    public void setMergantId(Long mergantId) {
        this.mergantId = mergantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Mergant getMergant() {
		return mergant;
	}

	public void setMergant(Mergant mergant) {
		this.mergant = mergant;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public Double getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(Double addPrice) {
        this.addPrice = addPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName == null ? null : recName.trim();
    }

    public String getRecTel() {
        return recTel;
    }

    public void setRecTel(String recTel) {
        this.recTel = recTel == null ? null : recTel.trim();
    }

    public String getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(String locationFrom) {
        this.locationFrom = locationFrom == null ? null : locationFrom.trim();
    }

    public String getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(String locationTo) {
        this.locationTo = locationTo == null ? null : locationTo.trim();
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

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed == null ? null : removed.trim();
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach == null ? null : reach.trim();
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
        Orderu other = (Orderu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCourierId() == null ? other.getCourierId() == null : this.getCourierId().equals(other.getCourierId()))
            && (this.getMergantId() == null ? other.getMergantId() == null : this.getMergantId().equals(other.getMergantId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAddPrice() == null ? other.getAddPrice() == null : this.getAddPrice().equals(other.getAddPrice()))
            && (this.getRealPrice() == null ? other.getRealPrice() == null : this.getRealPrice().equals(other.getRealPrice()))
            && (this.getRecName() == null ? other.getRecName() == null : this.getRecName().equals(other.getRecName()))
            && (this.getRecTel() == null ? other.getRecTel() == null : this.getRecTel().equals(other.getRecTel()))
            && (this.getLocationFrom() == null ? other.getLocationFrom() == null : this.getLocationFrom().equals(other.getLocationFrom()))
            && (this.getLocationTo() == null ? other.getLocationTo() == null : this.getLocationTo().equals(other.getLocationTo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemoved() == null ? other.getRemoved() == null : this.getRemoved().equals(other.getRemoved()))
            && (this.getReach() == null ? other.getReach() == null : this.getReach().equals(other.getReach()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCourierId() == null) ? 0 : getCourierId().hashCode());
        result = prime * result + ((getMergantId() == null) ? 0 : getMergantId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAddPrice() == null) ? 0 : getAddPrice().hashCode());
        result = prime * result + ((getRealPrice() == null) ? 0 : getRealPrice().hashCode());
        result = prime * result + ((getRecName() == null) ? 0 : getRecName().hashCode());
        result = prime * result + ((getRecTel() == null) ? 0 : getRecTel().hashCode());
        result = prime * result + ((getLocationFrom() == null) ? 0 : getLocationFrom().hashCode());
        result = prime * result + ((getLocationTo() == null) ? 0 : getLocationTo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemoved() == null) ? 0 : getRemoved().hashCode());
        result = prime * result + ((getReach() == null) ? 0 : getReach().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courierId=").append(courierId);
        sb.append(", mergantId=").append(mergantId);
        sb.append(", userId=").append(userId);
        sb.append(", addPrice=").append(addPrice);
        sb.append(", realPrice=").append(realPrice);
        sb.append(", recName=").append(recName);
        sb.append(", recTel=").append(recTel);
        sb.append(", locationFrom=").append(locationFrom);
        sb.append(", locationTo=").append(locationTo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", removed=").append(removed);
        sb.append(", reach=").append(reach);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}