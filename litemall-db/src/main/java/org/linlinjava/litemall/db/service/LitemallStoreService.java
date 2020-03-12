package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallStoreMapper;
import org.linlinjava.litemall.db.domain.LitemallStore;
import org.linlinjava.litemall.db.domain.LitemallStoreExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallStoreService {

    @Resource
    private LitemallStoreMapper storeMapper;


    public List<LitemallStore> all() {
        LitemallStoreExample example = new LitemallStoreExample();
        example.or().andDeletedEqualTo(false);
        return storeMapper.selectByExample(example);
    }

    public LitemallStore findById(Integer id) {
        return storeMapper.selectByPrimaryKey(id);
    }

    public void add(LitemallStore store) {
        store.setDeleted(false);
        store.setAddTime(LocalDateTime.now());
        store.setUpdateTime(LocalDateTime.now());
        storeMapper.insertSelective(store);
    }

    public int updateById(LitemallStore store) {
        store.setUpdateTime(LocalDateTime.now());
        return storeMapper.updateByPrimaryKeySelective(store);
    }

    public void deleteById(Integer id) {
        storeMapper.logicalDeleteByPrimaryKey(id);
    }

    public Long countByRegionId(Integer regionId) {
        LitemallStoreExample example = new LitemallStoreExample();
        example.or().andRegionIdEqualTo(regionId);
        return storeMapper.countByExample(example);
    }
}
