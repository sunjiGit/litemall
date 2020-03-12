package org.linlinjava.litemall.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class LitemallStore {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table litemall_store
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table litemall_store
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.address_detail
     *
     * @mbg.generated
     */
    private String addressDetail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.longitude
     *
     * @mbg.generated
     */
    private String longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.latitude
     *
     * @mbg.generated
     */
    private String latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.type
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.workday_start_time
     *
     * @mbg.generated
     */
    private LocalDateTime workdayStartTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.workday_end_time
     *
     * @mbg.generated
     */
    private LocalDateTime workdayEndTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.weekend_start_time
     *
     * @mbg.generated
     */
    private LocalDateTime weekendStartTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.weekend_end_time
     *
     * @mbg.generated
     */
    private LocalDateTime weekendEndTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.city_no
     *
     * @mbg.generated
     */
    private Integer cityNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.order_start_seq
     *
     * @mbg.generated
     */
    private Integer orderStartSeq;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.add_time
     *
     * @mbg.generated
     */
    private LocalDateTime addTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_store.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.id
     *
     * @return the value of litemall_store.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.id
     *
     * @param id the value for litemall_store.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.name
     *
     * @return the value of litemall_store.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.name
     *
     * @param name the value for litemall_store.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.address_detail
     *
     * @return the value of litemall_store.address_detail
     *
     * @mbg.generated
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.address_detail
     *
     * @param addressDetail the value for litemall_store.address_detail
     *
     * @mbg.generated
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.longitude
     *
     * @return the value of litemall_store.longitude
     *
     * @mbg.generated
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.longitude
     *
     * @param longitude the value for litemall_store.longitude
     *
     * @mbg.generated
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.latitude
     *
     * @return the value of litemall_store.latitude
     *
     * @mbg.generated
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.latitude
     *
     * @param latitude the value for litemall_store.latitude
     *
     * @mbg.generated
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.type
     *
     * @return the value of litemall_store.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.type
     *
     * @param type the value for litemall_store.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.workday_start_time
     *
     * @return the value of litemall_store.workday_start_time
     *
     * @mbg.generated
     */
    public LocalDateTime getWorkdayStartTime() {
        return workdayStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.workday_start_time
     *
     * @param workdayStartTime the value for litemall_store.workday_start_time
     *
     * @mbg.generated
     */
    public void setWorkdayStartTime(LocalDateTime workdayStartTime) {
        this.workdayStartTime = workdayStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.workday_end_time
     *
     * @return the value of litemall_store.workday_end_time
     *
     * @mbg.generated
     */
    public LocalDateTime getWorkdayEndTime() {
        return workdayEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.workday_end_time
     *
     * @param workdayEndTime the value for litemall_store.workday_end_time
     *
     * @mbg.generated
     */
    public void setWorkdayEndTime(LocalDateTime workdayEndTime) {
        this.workdayEndTime = workdayEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.weekend_start_time
     *
     * @return the value of litemall_store.weekend_start_time
     *
     * @mbg.generated
     */
    public LocalDateTime getWeekendStartTime() {
        return weekendStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.weekend_start_time
     *
     * @param weekendStartTime the value for litemall_store.weekend_start_time
     *
     * @mbg.generated
     */
    public void setWeekendStartTime(LocalDateTime weekendStartTime) {
        this.weekendStartTime = weekendStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.weekend_end_time
     *
     * @return the value of litemall_store.weekend_end_time
     *
     * @mbg.generated
     */
    public LocalDateTime getWeekendEndTime() {
        return weekendEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.weekend_end_time
     *
     * @param weekendEndTime the value for litemall_store.weekend_end_time
     *
     * @mbg.generated
     */
    public void setWeekendEndTime(LocalDateTime weekendEndTime) {
        this.weekendEndTime = weekendEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.status
     *
     * @return the value of litemall_store.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.status
     *
     * @param status the value for litemall_store.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.city_no
     *
     * @return the value of litemall_store.city_no
     *
     * @mbg.generated
     */
    public Integer getCityNo() {
        return cityNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.city_no
     *
     * @param cityNo the value for litemall_store.city_no
     *
     * @mbg.generated
     */
    public void setCityNo(Integer cityNo) {
        this.cityNo = cityNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.order_start_seq
     *
     * @return the value of litemall_store.order_start_seq
     *
     * @mbg.generated
     */
    public Integer getOrderStartSeq() {
        return orderStartSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.order_start_seq
     *
     * @param orderStartSeq the value for litemall_store.order_start_seq
     *
     * @mbg.generated
     */
    public void setOrderStartSeq(Integer orderStartSeq) {
        this.orderStartSeq = orderStartSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.add_time
     *
     * @return the value of litemall_store.add_time
     *
     * @mbg.generated
     */
    public LocalDateTime getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.add_time
     *
     * @param addTime the value for litemall_store.add_time
     *
     * @mbg.generated
     */
    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.update_time
     *
     * @return the value of litemall_store.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.update_time
     *
     * @param updateTime the value for litemall_store.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_store.deleted
     *
     * @return the value of litemall_store.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_store.deleted
     *
     * @param deleted the value for litemall_store.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", addressDetail=").append(addressDetail);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", type=").append(type);
        sb.append(", workdayStartTime=").append(workdayStartTime);
        sb.append(", workdayEndTime=").append(workdayEndTime);
        sb.append(", weekendStartTime=").append(weekendStartTime);
        sb.append(", weekendEndTime=").append(weekendEndTime);
        sb.append(", status=").append(status);
        sb.append(", cityNo=").append(cityNo);
        sb.append(", orderStartSeq=").append(orderStartSeq);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store
     *
     * @mbg.generated
     */
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
        LitemallStore other = (LitemallStore) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddressDetail() == null ? other.getAddressDetail() == null : this.getAddressDetail().equals(other.getAddressDetail()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getWorkdayStartTime() == null ? other.getWorkdayStartTime() == null : this.getWorkdayStartTime().equals(other.getWorkdayStartTime()))
            && (this.getWorkdayEndTime() == null ? other.getWorkdayEndTime() == null : this.getWorkdayEndTime().equals(other.getWorkdayEndTime()))
            && (this.getWeekendStartTime() == null ? other.getWeekendStartTime() == null : this.getWeekendStartTime().equals(other.getWeekendStartTime()))
            && (this.getWeekendEndTime() == null ? other.getWeekendEndTime() == null : this.getWeekendEndTime().equals(other.getWeekendEndTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCityNo() == null ? other.getCityNo() == null : this.getCityNo().equals(other.getCityNo()))
            && (this.getOrderStartSeq() == null ? other.getOrderStartSeq() == null : this.getOrderStartSeq().equals(other.getOrderStartSeq()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAddressDetail() == null) ? 0 : getAddressDetail().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getWorkdayStartTime() == null) ? 0 : getWorkdayStartTime().hashCode());
        result = prime * result + ((getWorkdayEndTime() == null) ? 0 : getWorkdayEndTime().hashCode());
        result = prime * result + ((getWeekendStartTime() == null) ? 0 : getWeekendStartTime().hashCode());
        result = prime * result + ((getWeekendEndTime() == null) ? 0 : getWeekendEndTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCityNo() == null) ? 0 : getCityNo().hashCode());
        result = prime * result + ((getOrderStartSeq() == null) ? 0 : getOrderStartSeq().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table litemall_store
     *
     * @mbg.generated
     */
    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table litemall_store
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        name("name", "name", "VARCHAR", true),
        addressDetail("address_detail", "addressDetail", "VARCHAR", false),
        longitude("longitude", "longitude", "VARCHAR", false),
        latitude("latitude", "latitude", "VARCHAR", false),
        type("type", "type", "VARCHAR", true),
        workdayStartTime("workday_start_time", "workdayStartTime", "TIMESTAMP", false),
        workdayEndTime("workday_end_time", "workdayEndTime", "TIMESTAMP", false),
        weekendStartTime("weekend_start_time", "weekendStartTime", "TIMESTAMP", false),
        weekendEndTime("weekend_end_time", "weekendEndTime", "TIMESTAMP", false),
        status("status", "status", "VARCHAR", true),
        cityNo("city_no", "cityNo", "INTEGER", false),
        orderStartSeq("order_start_seq", "orderStartSeq", "INTEGER", false),
        addTime("add_time", "addTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        deleted("deleted", "deleted", "BIT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_store
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}