package com.example.assignment_4;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParkingPojo extends ParkingExtras implements Parcelable{

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cost_per_minute")
    @Expose
    private String costPerMinute;
    @SerializedName("max_reserve_time_mins")
    @Expose
    private Integer maxReserveTimeMins;
    @SerializedName("min_reserve_time_mins")
    @Expose
    private Integer minReserveTimeMins;
    @SerializedName("is_reserved")
    @Expose
    private Boolean isReserved;
    @SerializedName("reserved_until")
    @Expose
    private String reservedUntil;

    protected ParkingPojo(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        lat = in.readString();
        lng = in.readString();
        name = in.readString();
        costPerMinute = in.readString();
        if (in.readByte() == 0) {
            maxReserveTimeMins = null;
        } else {
            maxReserveTimeMins = in.readInt();
        }
        if (in.readByte() == 0) {
            minReserveTimeMins = null;
        } else {
            minReserveTimeMins = in.readInt();
        }
        byte tmpIsReserved = in.readByte();
        isReserved = tmpIsReserved == 0 ? null : tmpIsReserved == 1;
        reservedUntil = in.readString();
    }

    public static final Creator<ParkingPojo> CREATOR = new Creator<ParkingPojo>() {
        @Override
        public ParkingPojo createFromParcel(Parcel in) {
            return new ParkingPojo(in);
        }

        @Override
        public ParkingPojo[] newArray(int size) {
            return new ParkingPojo[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCostPerMinute() {
        return costPerMinute;
    }

    public void setCostPerMinute(String costPerMinute) {
        this.costPerMinute = costPerMinute;
    }

    public Integer getMaxReserveTimeMins() {
        return maxReserveTimeMins;
    }

    public void setMaxReserveTimeMins(Integer maxReserveTimeMins) {
        this.maxReserveTimeMins = maxReserveTimeMins;
    }

    public Integer getMinReserveTimeMins() {
        return minReserveTimeMins;
    }

    public void setMinReserveTimeMins(Integer minReserveTimeMins) {
        this.minReserveTimeMins = minReserveTimeMins;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public String getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(String reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

    @Override
    public String toString() {
        return "ParkingPojo{" +
                "name='" + name + '\'' +
                ", costPerMinute='" + costPerMinute + '\'' +
                ", address=" + address +
                ", openSpot=" + openSpot +
                ", distance='" + distance + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(name);
        dest.writeString(costPerMinute);
        if (maxReserveTimeMins == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(maxReserveTimeMins);
        }
        if (minReserveTimeMins == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(minReserveTimeMins);
        }
        dest.writeByte((byte) (isReserved == null ? 0 : isReserved ? 1 : 2));
        dest.writeString(reservedUntil);
    }
}
