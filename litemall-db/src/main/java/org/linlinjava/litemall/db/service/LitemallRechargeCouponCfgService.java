package org.linlinjava.litemall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallRechargeCouponCfgMapper;
import org.linlinjava.litemall.db.domain.LitemallRechargeCouponCfg;
import org.linlinjava.litemall.db.domain.LitemallRechargeCouponCfgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallRechargeCouponCfgService {

    @Autowired
    private LitemallRechargeCouponCfgMapper rechargeCouponCfgMapper;

    public List<LitemallRechargeCouponCfg> querySelective(Integer page, Integer limit, String sort, String order) {
        LitemallRechargeCouponCfgExample example = new LitemallRechargeCouponCfgExample();
        example.or().andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return rechargeCouponCfgMapper.selectByExample(example);
    }

    public Integer createRechargeConfig(LitemallRechargeCouponCfg rechargeCouponCfg) {
        return rechargeCouponCfgMapper.insert(rechargeCouponCfg);
    }

    public LitemallRechargeCouponCfg findById(Integer id) {
        LitemallRechargeCouponCfgExample example = new LitemallRechargeCouponCfgExample();
        example.or().andIdEqualTo(id);

        return rechargeCouponCfgMapper.selectOneByExample(example);
    }

    public int updateById(LitemallRechargeCouponCfg config) {
        config.setUpdateTime(LocalDateTime.now());
        return rechargeCouponCfgMapper.updateByPrimaryKeySelective(config);
    }

    public void deleteById(Integer id) {
        LitemallRechargeCouponCfg cfg = new LitemallRechargeCouponCfg();
        cfg.setId(id);
        cfg.setDeleted(true);
        cfg.setUpdateTime(LocalDateTime.now());
        rechargeCouponCfgMapper.updateByPrimaryKeySelective(cfg);
    }
}
