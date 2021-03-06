package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallRechargeCouponCfg;
import org.linlinjava.litemall.db.domain.LitemallRechargeCouponCfgExample;

public interface LitemallRechargeCouponCfgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    long countByExample(LitemallRechargeCouponCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallRechargeCouponCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int insert(LitemallRechargeCouponCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int insertSelective(LitemallRechargeCouponCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    LitemallRechargeCouponCfg selectOneByExample(LitemallRechargeCouponCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    LitemallRechargeCouponCfg selectOneByExampleSelective(@Param("example") LitemallRechargeCouponCfgExample example, @Param("selective") LitemallRechargeCouponCfg.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    List<LitemallRechargeCouponCfg> selectByExampleSelective(@Param("example") LitemallRechargeCouponCfgExample example, @Param("selective") LitemallRechargeCouponCfg.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    List<LitemallRechargeCouponCfg> selectByExample(LitemallRechargeCouponCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    LitemallRechargeCouponCfg selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallRechargeCouponCfg.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    LitemallRechargeCouponCfg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    LitemallRechargeCouponCfg selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallRechargeCouponCfg record, @Param("example") LitemallRechargeCouponCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallRechargeCouponCfg record, @Param("example") LitemallRechargeCouponCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallRechargeCouponCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallRechargeCouponCfg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallRechargeCouponCfgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_recharge_coupon_cfg
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}