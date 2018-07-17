package com.qxt.pojo;

import java.io.Serializable;
import java.util.Date;

public class Mergant implements Serializable {
    private Long uId;

    private String mName;

    private String mTel;

    private String location;

    private Date opentime;

    private Date closetime;

    private Integer rId;

    private String type;

    private String bossName;

    private String bossTel;

    private Date createTime;

    private Date updateTime;
    
    private Account account;
    
    private Region region;
    
    private Double minPrice;
    
    private Double addPrice;

    private static final long serialVersionUID = 1L;

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName == null ? null : mName.trim();
    }

    public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(Double addPrice) {
		this.addPrice = addPrice;
	}

	public String getmTel() {
        return mTel;
    }

    public void setmTel(String mTel) {
        this.mTel = mTel == null ? null : mTel.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Date getOpentime() {
        return opentime;
    }

    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }
    

    public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName == null ? null : bossName.trim();
    }

    public String getBossTel() {
        return bossTel;
    }

    public void setBossTel(String bossTel) {
        this.bossTel = bossTel == null ? null : bossTel.trim();
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
        Mergant other = (Mergant) that;
        return (this.getuId() == null ? other.getuId() == null : this.getuId().equals(other.getuId()))
            && (this.getmName() == null ? other.getmName() == null : this.getmName().equals(other.getmName()))
            && (this.getmTel() == null ? other.getmTel() == null : this.getmTel().equals(other.getmTel()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getOpentime() == null ? other.getOpentime() == null : this.getOpentime().equals(other.getOpentime()))
            && (this.getClosetime() == null ? other.getClosetime() == null : this.getClosetime().equals(other.getClosetime()))
            && (this.getrId() == null ? other.getrId() == null : this.getrId().equals(other.getrId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getBossName() == null ? other.getBossName() == null : this.getBossName().equals(other.getBossName()))
            && (this.getBossTel() == null ? other.getBossTel() == null : this.getBossTel().equals(other.getBossTel()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getuId() == null) ? 0 : getuId().hashCode());
        result = prime * result + ((getmName() == null) ? 0 : getmName().hashCode());
        result = prime * result + ((getmTel() == null) ? 0 : getmTel().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getOpentime() == null) ? 0 : getOpentime().hashCode());
        result = prime * result + ((getClosetime() == null) ? 0 : getClosetime().hashCode());
        result = prime * result + ((getrId() == null) ? 0 : getrId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getBossName() == null) ? 0 : getBossName().hashCode());
        result = prime * result + ((getBossTel() == null) ? 0 : getBossTel().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uId=").append(uId);
        sb.append(", mName=").append(mName);
        sb.append(", mTel=").append(mTel);
        sb.append(", location=").append(location);
        sb.append(", opentime=").append(opentime);
        sb.append(", closetime=").append(closetime);
        sb.append(", rId=").append(rId);
        sb.append(", type=").append(type);
        sb.append(", bossName=").append(bossName);
        sb.append(", bossTel=").append(bossTel);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}