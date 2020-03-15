package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallStoreInventoryFlowMapper;
import org.linlinjava.litemall.db.dao.LitemallStoreInventoryMapper;
import org.linlinjava.litemall.db.dao.LitemallStoreMapper;
import org.linlinjava.litemall.db.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class LitemallStoreInventoryService {

    private Logger logger = LoggerFactory.getLogger(LitemallStoreInventoryService.class);

    @Resource
    private LitemallStoreInventoryMapper inventoryMapper;
    @Resource
    private LitemallStoreInventoryFlowMapper flowMapper;


    public List<LitemallStoreInventory> findByStoreId(Integer storeId) {
        LitemallStoreInventoryExample example = new LitemallStoreInventoryExample();
        example.or().andStoreIdEqualTo(storeId);
        return inventoryMapper.selectByExample(example);
    }

    public LitemallStoreInventory findByStoreIdAndGoodsId(Integer storeId, Integer goodsId) {
        LitemallStoreInventoryExample example = new LitemallStoreInventoryExample();
        example.or().andStoreIdEqualTo(storeId).andGoodsIdEqualTo(goodsId);
        return inventoryMapper.selectOneByExample(example);
    }

    public void plusFlow(LitemallStoreInventoryFlow flow) {
        LitemallStoreInventoryExample example = new LitemallStoreInventoryExample();
        example.or().andStoreIdEqualTo(flow.getStoreId()).andGoodsIdEqualTo(flow.getGoodsId());
        LitemallStoreInventory inventory = inventoryMapper.selectOneByExample(example);
        if (inventory == null) {
            LitemallStoreInventory newInventory = new LitemallStoreInventory();
            newInventory.setStoreId(flow.getStoreId());
            newInventory.setGoodsId(flow.getGoodsId());
            newInventory.setAmount(flow.getAmount());
            newInventory.setAddTime(LocalDateTime.now());
            newInventory.setUpdateTime(LocalDateTime.now());
            inventoryMapper.insertSelective(newInventory);

            flow.setInventoryId(newInventory.getId());
            flow.setAddTime(LocalDateTime.now());
            flow.setUpdateTime(LocalDateTime.now());
            flowMapper.insertSelective(flow);
        } else {
            inventory.setAmount(inventory.getAmount() + flow.getAmount()); // 正负表示数量增减，这里应该是加
            inventory.setUpdateTime(LocalDateTime.now());
            inventoryMapper.updateByPrimaryKeySelective(inventory);

            flow.setInventoryId(inventory.getId());
            flow.setAddTime(LocalDateTime.now());
            flow.setUpdateTime(LocalDateTime.now());
            flowMapper.insertSelective(flow);
        }

    }

    public void minusFlow(LitemallStoreInventoryFlow flow) {
        LitemallStoreInventoryExample example = new LitemallStoreInventoryExample();
        example.or().andStoreIdEqualTo(flow.getStoreId()).andGoodsIdEqualTo(flow.getGoodsId());
        LitemallStoreInventory inventory = inventoryMapper.selectOneByExample(example);
        if (inventory == null || inventory.getAmount() < flow.getAmount()) {
            // 无法扣减
            logger.info(String.format("can not minus inventory, because amount is not enough. storeId=%d, " +
                    "goodsId=%d, has amount=%d, minus amount=%d", flow.getStoreId(), flow.getGoodsId(),
                    inventory == null ? 0 : inventory.getAmount(),
                    flow.getAmount()));
            return;
        } else {
            inventory.setAmount(inventory.getAmount() + flow.getAmount()); // 正负表示数量增减，这里应该是减
            inventory.setUpdateTime(LocalDateTime.now());
            inventoryMapper.updateByPrimaryKeySelective(inventory);

            flow.setInventoryId(inventory.getId());
            flow.setUpdateTime(LocalDateTime.now());
            flowMapper.insertSelective(flow);
        }

    }

    public void deleteById(Integer id) {
        inventoryMapper.logicalDeleteByPrimaryKey(id);
    }

}
