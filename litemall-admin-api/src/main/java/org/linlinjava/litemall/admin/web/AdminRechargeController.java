package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.vo.RechargeConfigVo;
import org.linlinjava.litemall.admin.vo.RechargeCouponVo;
import org.linlinjava.litemall.core.constant.RechargeConfigConstant;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallCoupon;
import org.linlinjava.litemall.db.domain.LitemallCouponExample;
import org.linlinjava.litemall.db.domain.LitemallRechargeCouponCfg;
import org.linlinjava.litemall.db.service.LitemallCouponService;
import org.linlinjava.litemall.db.service.LitemallRechargeCouponCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/admin/recharge")
@Validated
public class AdminRechargeController {
    private final Log logger = LogFactory.getLog(AdminRechargeController.class);

    @Autowired
    private LitemallRechargeCouponCfgService rechargeCouponService;
    @Autowired
    private LitemallCouponService couponService;

    @RequiresPermissions("admin:coupon:list")
    @RequiresPermissionsDesc(menu = {"充值管理", "充值优惠券管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallRechargeCouponCfg> couponList = rechargeCouponService.querySelective(page, limit, sort, order);
        if (CollectionUtils.isEmpty(couponList)) {
            return ResponseUtil.okList(Collections.emptyList());
        }

        List<RechargeConfigVo> rechargeConfigVoList = new ArrayList<>();
        for (LitemallRechargeCouponCfg recharge : couponList) {
            RechargeConfigVo vo = new RechargeConfigVo();
            vo.setId(recharge.getId());
            vo.setAmount(recharge.getAmount());
            vo.setDesc(recharge.getDesc());
            vo.setTotalCouponAmount(recharge.getTotalCouponAmount());
            vo.setTotalCouponNumber(recharge.getTotalCouponNumber());

            vo.setCouponVos(generateRechargeCouponVos(recharge));

            rechargeConfigVoList.add(vo);
        }

        return ResponseUtil.okList(rechargeConfigVoList);
    }

    @RequiresPermissions("admin:coupon:create")
    @RequiresPermissionsDesc(menu = {"推广管理", "充值优惠券管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody RechargeConfigVo rechargeConfigVo) {
        if (rechargeConfigVo == null || rechargeConfigVo.getAmount() <= 0) {
            return ResponseUtil.badArgument();
        }
        Integer id = rechargeCouponService.createRechargeConfig(convertToDO(rechargeConfigVo));
        return ResponseUtil.ok(id);
    }

    @RequiresPermissions("admin:coupon:read")
    @RequiresPermissionsDesc(menu = {"推广管理", "充值优惠券管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallRechargeCouponCfg config = rechargeCouponService.findById(id);
        return ResponseUtil.ok(convertToVo(config));
    }

    @RequiresPermissions("admin:coupon:update")
    @RequiresPermissionsDesc(menu = {"推广管理", "充值优惠券管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody RechargeConfigVo rechargeConfigVo) {
        if (rechargeConfigVo.getId() == null || rechargeConfigVo.getId() <= 0) {
            return ResponseUtil.badArgument();
        }
        if (rechargeCouponService.updateById(convertToDO(rechargeConfigVo)) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(rechargeConfigVo);
    }

    @RequiresPermissions("admin:coupon:delete")
    @RequiresPermissionsDesc(menu = {"推广管理", "充值优惠券管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(Integer id) {
        rechargeCouponService.deleteById(id);
        return ResponseUtil.ok();
    }

    /**
     * 单条配置
     */
    private List<RechargeCouponVo> generateRechargeCouponVos(LitemallRechargeCouponCfg recharge) {
        String json = recharge.getCouponDetail();

        JSONArray array = new JSONArray(json);
        if (array.length() == 0) {
            return Collections.emptyList();
        }

        List<Integer> couponIds = new ArrayList<>(array.length());
        Map<Integer, Integer> couponSizeMap = new HashMap<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            couponIds.add(object.getInt(RechargeConfigConstant.RECHARGE_COUPON_ID_KEY));
            couponSizeMap.put(object.getInt(RechargeConfigConstant.RECHARGE_COUPON_ID_KEY),
                    object.getInt(RechargeConfigConstant.RECHARGE_COUPON_NUMBER_KEY));
        }

        LitemallCouponExample.Criteria criteria = LitemallCouponExample.newAndCreateCriteria().andIdIn(couponIds);
        List<LitemallCoupon> couponList = couponService.queryList(criteria, 0, couponIds.size(), "", "");

        if (CollectionUtils.isEmpty(couponList)) {
            return Collections.emptyList();
        }

        List<RechargeCouponVo> vos = new ArrayList<>();
        for (LitemallCoupon coupon : couponList) {
            RechargeCouponVo vo = new RechargeCouponVo();
            vo.setCouponId(coupon.getId());
            vo.setCouponName(coupon.getName());
            vo.setCouponAmount(coupon.getDiscount().multiply(new BigDecimal(100)).intValue());
            vo.setCouponSize(couponSizeMap.get(coupon.getId()));

            vos.add(vo);
        }

        return vos;
    }

    private LitemallRechargeCouponCfg convertToDO(RechargeConfigVo rechargeConfigVo) {
        LitemallRechargeCouponCfg cfg = new LitemallRechargeCouponCfg();
        if (rechargeConfigVo.getId() != null) {
            cfg.setId(rechargeConfigVo.getId());
        }
        cfg.setDesc(rechargeConfigVo.getDesc());
        cfg.setAmount(rechargeConfigVo.getAmount());
        cfg.setAddTime(LocalDateTime.now());
        cfg.setUpdateTime(LocalDateTime.now());
        cfg.setDeleted(false);

        Integer totalAmount = 0, totalNumber = 0;

        if (CollectionUtils.isEmpty(rechargeConfigVo.getCouponVos())) {
            return cfg;
        }

        JSONArray jsonArray = new JSONArray();
        for (RechargeCouponVo vo : rechargeConfigVo.getCouponVos()) {
            totalAmount = totalAmount + vo.getCouponAmount() * vo.getCouponSize();
            totalNumber = totalNumber + vo.getCouponSize();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(RechargeConfigConstant.RECHARGE_COUPON_ID_KEY, vo.getCouponId());
            jsonObject.put(RechargeConfigConstant.RECHARGE_COUPON_NUMBER_KEY, vo.getCouponSize());
            jsonArray.put(jsonObject);
        }

        cfg.setTotalCouponAmount(totalAmount.toString());
        cfg.setTotalCouponNumber(totalNumber.toString());
        cfg.setCouponDetail(jsonArray.toString());

        return cfg;
    }

    private RechargeConfigVo convertToVo(LitemallRechargeCouponCfg config) {
        RechargeConfigVo vo = new RechargeConfigVo();
        vo.setId(config.getId());
        vo.setDesc(config.getDesc());
        vo.setAmount(config.getAmount());
        vo.setTotalCouponNumber(config.getTotalCouponNumber());
        vo.setTotalCouponAmount(config.getTotalCouponAmount());

        String detail = config.getCouponDetail();
        if (StringUtils.isEmpty(detail)) {
            vo.setCouponVos(Collections.emptyList());
            return vo;
        }

        try {
            List<RechargeCouponVo> couponVos = new ArrayList<>();

            JSONArray array = new JSONArray(detail);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                RechargeCouponVo couponVo = new RechargeCouponVo();
                Integer couponId = object.getInt(RechargeConfigConstant.RECHARGE_COUPON_ID_KEY);
                LitemallCoupon litemallCoupon = couponService.findById(couponId);

                if (litemallCoupon == null) {
                    continue;
                }

                couponVo.setCouponId(couponId);
                couponVo.setCouponSize(object.getInt(RechargeConfigConstant.RECHARGE_COUPON_NUMBER_KEY));

                couponVo.setCouponAmount(litemallCoupon.getDiscount().multiply(new BigDecimal(100)).intValue());
                couponVo.setCouponName(litemallCoupon.getName());

                couponVos.add(couponVo);
            }

            vo.setCouponVos(couponVos);

            return vo;
        } catch (Exception e) {
            // do nothing
        }

        return vo;
    }

}
