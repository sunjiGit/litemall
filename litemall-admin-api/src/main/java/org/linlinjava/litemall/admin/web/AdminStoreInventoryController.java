package org.linlinjava.litemall.admin.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.db.enums.store.StoreInventoryOperateType;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallStoreInventoryFlow;
import org.linlinjava.litemall.db.service.LitemallStoreInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/storeInventory")
@Validated
@Api(value = "门店产品库存的后台操作")
public class AdminStoreInventoryController {
    private final Log logger = LogFactory.getLog(AdminStoreInventoryController.class);

    @Autowired
    private LitemallStoreInventoryService storeInventoryService;

    /**
     * 制作门店商品库存
     */
    @RequiresPermissions("admin:store:list")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店库存管理"}, button = "输入")
    @PostMapping("/add")
    @ApiOperation(value = "增加门店库存商品数量  门店=storeId 商品=goodsId 产品=productId 操作=operateType:MAKE-IN 数量=amount 操作人=operator")
    public Object add(@RequestBody LitemallStoreInventoryFlow flow) {
        logger.info(String.format("add flow:%s", flow));
        StoreInventoryOperateType flowType = StoreInventoryOperateType.getByCode(flow.getOperateType());
        if (StoreInventoryOperateType.MAKE_IN != flowType) {
            logger.info("flow type is not fit. flow=" + flow);
            return ResponseUtil.badArgument();
        }
        storeInventoryService.plusFlow(flow);
        return ResponseUtil.ok();
    }

    /**
     * 查询门店库存列表
     */
    @RequiresPermissions("admin:store:list")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店库存管理"}, button = "查询")
    @GetMapping("/detail")
    @ApiOperation(value = "查看门店库存 门店=storeId，返回对象有 goods_name product_id")
    public Object detail(@NotNull Integer storeId) {
        logger.info(String.format("detail id:%d", storeId));
        return ResponseUtil.okList(storeInventoryService.findByStoreId(storeId));
    }

    /**
     * 调整门店商品库存（增加 or 减少）
     */
    @RequiresPermissions("admin:store:list")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店库存管理"}, button = "调整")
    @PostMapping("/adjust")
    @ApiOperation(value = "调整库存 门店=storeId 商品=goodsId 产品=productId 操作=operateType:ADJUST_OUT-调整减少 ADJUST_IN-调整增加, " +
            "数量=amount 操作人=operator")
    public Object adjust(@RequestBody LitemallStoreInventoryFlow flow) {
        logger.info(String.format("adjust flow:%s", flow));
        StoreInventoryOperateType flowType = StoreInventoryOperateType.getByCode(flow.getOperateType());
        if (StoreInventoryOperateType.ADJUST_IN != flowType && StoreInventoryOperateType.ADJUST_OUT != flowType) {
            logger.info("flow type is not fit. flow=" + flow);
            return ResponseUtil.badArgument();
        }

        if (StoreInventoryOperateType.ADJUST_IN == flowType) {
            storeInventoryService.plusFlow(flow);
        }
        if (StoreInventoryOperateType.ADJUST_OUT == flowType) {
            storeInventoryService.minusFlow(flow);
        }
        return ResponseUtil.ok();
    }

}
