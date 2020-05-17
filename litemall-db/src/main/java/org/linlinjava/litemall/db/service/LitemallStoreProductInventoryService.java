package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallStoreProductInventoryMapper;
import org.linlinjava.litemall.db.domain.LitemallStoreProductInventory;
import org.linlinjava.litemall.db.domain.LitemallStoreProductInventoryExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 门店库存的服务
 * 暂时不启用，需和后台一起配合使用
 *
 */
@Service
@Deprecated
public class LitemallStoreProductInventoryService {

    private Logger logger = LoggerFactory.getLogger(LitemallStoreProductInventoryService.class);

    @Resource
    private LitemallStoreProductInventoryMapper productInventoryMapper;

    public List<LitemallStoreProductInventory> listByStoreId(Integer storeId) {
        LitemallStoreProductInventoryExample example = new LitemallStoreProductInventoryExample();
        example.or().andStoreIdEqualTo(storeId);
        return productInventoryMapper.selectByExample(example);
    }

    public List<LitemallStoreProductInventory> listEffectiveByStoreId(Integer storeId) {
        LitemallStoreProductInventoryExample example = new LitemallStoreProductInventoryExample();
        example.or().andStoreIdEqualTo(storeId)
                .andEffectStartTimeLessThan(LocalDateTime.now()).andEffectEndTimeGreaterThan(LocalDateTime.now());
        return productInventoryMapper.selectByExample(example);
    }

    public LitemallStoreProductInventory addProductInventory(LitemallStoreProductInventory productInventory) {
        productInventoryMapper.insert(productInventory);
        return productInventory;
    }

    public boolean updateProductInventory(Integer productId, LitemallStoreProductInventory productInventory) {
        int num = productInventoryMapper.updateByPrimaryKeySelective(productInventory);
        return num == 1;
    }

    public boolean deleteProductInventory(Integer productId) {
        int num = productInventoryMapper.logicalDeleteByPrimaryKey(productId);
        return num == 1;
    }

    public List<LitemallStoreProductInventory> findByStoreIdAndProductId(Integer storeId, Integer productId) {
        LitemallStoreProductInventoryExample example = new LitemallStoreProductInventoryExample();
        example.or().andStoreIdEqualTo(storeId).andProductIdEqualTo(productId)
                .andEffectStartTimeLessThan(LocalDateTime.now()).andEffectEndTimeGreaterThan(LocalDateTime.now());
        return productInventoryMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        productInventoryMapper.logicalDeleteByPrimaryKey(id);
    }
}
