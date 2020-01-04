package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallBrandMapper;
import org.linlinjava.litemall.db.dao.LitemallGroupOrderMapper;
import org.linlinjava.litemall.db.domain.LitemallBrand;
import org.linlinjava.litemall.db.domain.LitemallBrand.Column;
import org.linlinjava.litemall.db.domain.LitemallBrandExample;
import org.linlinjava.litemall.db.domain.LitemallGroupOrder;
import org.linlinjava.litemall.db.domain.LitemallGroupOrderExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallGroupOrderService {
    @Resource
    private LitemallGroupOrderMapper groupOrderMapper;

    public List<LitemallGroupOrder> query(Integer page, Integer limit, String sort, String order) {
        LitemallGroupOrderExample example = new LitemallGroupOrderExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return groupOrderMapper.selectByExampleSelective(example);
    }

    public List<LitemallGroupOrder> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public LitemallGroupOrder findById(Integer id) {
        return groupOrderMapper.selectByPrimaryKey(id);
    }

    public List<LitemallGroupOrder> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        LitemallGroupOrderExample example = new LitemallGroupOrderExample();
        LitemallGroupOrderExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
//        if (!StringUtils.isEmpty(name)) {
//            criteria.andNameLike("%" + name + "%");
//        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return groupOrderMapper.selectByExample(example);
    }

    public int updateById(LitemallGroupOrder groupOrder) {
        groupOrder.setUpdateTime(LocalDateTime.now());
        return groupOrderMapper.updateByPrimaryKeySelective(groupOrder);
    }

    public void deleteById(Integer id) {
        groupOrderMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallGroupOrder groupOrder) {
        groupOrder.setAddTime(LocalDateTime.now());
        groupOrder.setUpdateTime(LocalDateTime.now());
        groupOrderMapper.insertSelective(groupOrder);
    }

    public List<LitemallGroupOrder> all() {
        LitemallGroupOrderExample example = new LitemallGroupOrderExample();
        example.or().andDeletedEqualTo(false);
        return groupOrderMapper.selectByExample(example);
    }
}
