package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallGroupOrder;
import org.linlinjava.litemall.db.domain.LitemallGroupOrderExample;

public interface LitemallGroupOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    long countByExample(LitemallGroupOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallGroupOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int insert(LitemallGroupOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int insertSelective(LitemallGroupOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    LitemallGroupOrder selectOneByExample(LitemallGroupOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    LitemallGroupOrder selectOneByExampleSelective(@Param("example") LitemallGroupOrderExample example, @Param("selective") LitemallGroupOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    List<LitemallGroupOrder> selectByExampleSelective(@Param("example") LitemallGroupOrderExample example, @Param("selective") LitemallGroupOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    List<LitemallGroupOrder> selectByExample(LitemallGroupOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    LitemallGroupOrder selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallGroupOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    LitemallGroupOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    LitemallGroupOrder selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallGroupOrder record, @Param("example") LitemallGroupOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallGroupOrder record, @Param("example") LitemallGroupOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallGroupOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallGroupOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallGroupOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_group_order
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}