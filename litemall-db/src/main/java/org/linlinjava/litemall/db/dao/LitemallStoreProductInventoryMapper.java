package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallStoreProductInventory;
import org.linlinjava.litemall.db.domain.LitemallStoreProductInventoryExample;

public interface LitemallStoreProductInventoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    long countByExample(LitemallStoreProductInventoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallStoreProductInventoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int insert(LitemallStoreProductInventory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int insertSelective(LitemallStoreProductInventory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    LitemallStoreProductInventory selectOneByExample(LitemallStoreProductInventoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    LitemallStoreProductInventory selectOneByExampleSelective(@Param("example") LitemallStoreProductInventoryExample example, @Param("selective") LitemallStoreProductInventory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    List<LitemallStoreProductInventory> selectByExampleSelective(@Param("example") LitemallStoreProductInventoryExample example, @Param("selective") LitemallStoreProductInventory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    List<LitemallStoreProductInventory> selectByExample(LitemallStoreProductInventoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    LitemallStoreProductInventory selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallStoreProductInventory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    LitemallStoreProductInventory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    LitemallStoreProductInventory selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallStoreProductInventory record, @Param("example") LitemallStoreProductInventoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallStoreProductInventory record, @Param("example") LitemallStoreProductInventoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallStoreProductInventory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallStoreProductInventory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallStoreProductInventoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_store_product_inventory
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}