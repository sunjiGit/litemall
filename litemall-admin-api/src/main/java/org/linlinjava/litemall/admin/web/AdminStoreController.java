package org.linlinjava.litemall.admin.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.location.LocationService;
import org.linlinjava.litemall.core.location.model.Location;
import org.linlinjava.litemall.core.store.enums.StoreStatus;
import org.linlinjava.litemall.core.store.enums.StoreType;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallStore;
import org.linlinjava.litemall.db.service.LitemallStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/store")
@Validated
@Api(value = "门店的后台操作")
public class AdminStoreController {
    private final Log logger = LogFactory.getLog(AdminStoreController.class);

    @Autowired
    private LitemallStoreService storeService;
    @Autowired
    private LocationService locationService;

    /**
     * 查询门店列表
     *
     * @return
     */
    @RequiresPermissions("admin:store:list")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店管理"}, button = "查询")
    @GetMapping("/list")
    @ApiOperation(value = "获取门店列表")
    public Object list() {
        logger.info("list all");
        return ResponseUtil.okList(storeService.all());
    }

//    /**
//     * 按用户查询门店列表
//     *
//     * @return
//     */
//    @RequiresPermissions("admin:store:list")
//    @RequiresPermissionsDesc(menu = {"门店管理", "门店管理"}, button = "查询")
//    @GetMapping("/listByUser")
//    @ApiOperation(value = "按用户获取门店列表 TODO")
//    public Object listByUser(@NotNull Integer userId) {
//        logger.info("listByUser uid=" + userId);
//        return ResponseUtil.ok();
//    }

    /**
     * 门店详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:store:read")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店管理"}, button = "详情")
    @GetMapping("/detail")
    @ApiOperation(value = "获取门店详情")
    public Object detail(@NotNull Integer id) {
        logger.info(String.format("detail id:%d", id));
        return ResponseUtil.ok(storeService.findById(id));
    }

    /**
     * 删除门店
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:store:delete")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店管理"}, button = "删除")
    @PostMapping("/delete")
    @ApiOperation(value = "删除门店")
    public Object delete(@NotNull Integer id) {
        logger.info(String.format("delete id:%d", id));
        storeService.deleteById(id);
        return ResponseUtil.ok();
    }

    /**
     * 添加门店
     *
     * @param store
     * @return
     */
    @RequiresPermissions("admin:store:create")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店管理"}, button = "新增")
    @PostMapping("/create")
    @ApiOperation(value = "创建门店")
    public Object create(@RequestBody LitemallStore store) {
        logger.info(String.format("create id:%d", store.getId()));

        Long count = storeService.countByRegionId(store.getRegionId());
        store.setCityNo(count.intValue() + 1);
        StoreType st = StoreType.getByCode(store.getType());
        if (st == null) {
            logger.info("store type is not fit. store=" + store);
            return ResponseUtil.badArgument();
        }

        // 门店地址 转化为经纬度信息
        Location location = locationService.getLocation(store.getAddressDetail());
        if (location == null) {
            logger.info("store type is not fit. store=" + store);
            return ResponseUtil.badArgument();
        }
        store.setLatitude(location.getLat());
        store.setLongitude(location.getLon());

        store.setStatus(StoreStatus.STOP.getCode());
        storeService.add(store);
        return ResponseUtil.ok();
    }

    /**
     * 修改门店
     *
     * @param store
     * @return
     */
    @RequiresPermissions("admin:store:update")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店管理"}, button = "编辑")
    @PostMapping("/update")
    @ApiOperation(value = "更新门店")
    public Object update(@RequestBody LitemallStore store) {
        logger.info(String.format("update id:%d", store.getId()));

        LitemallStore origin = storeService.findById(store.getId());
        if (origin == null) {
            logger.info("no such store. storeId=" + store.getId());
            return ResponseUtil.badArgument();
        }

        StoreType st = StoreType.getByCode(store.getType());
        if (StringUtils.isNotBlank(store.getType()) && st == null) {
            logger.info("store type is not fit. store=" + store);
            return ResponseUtil.badArgument();
        }

        if (StringUtils.isNotBlank(store.getAddressDetail()) &&
                !store.getAddressDetail().equalsIgnoreCase(origin.getAddressDetail())) {
            // 门店地址 转化为经纬度信息
            Location location = locationService.getLocation(store.getAddressDetail());
            if (location == null) {
                logger.info("store type is not fit. store=" + store);
                return ResponseUtil.badArgument();
            }
            store.setLatitude(location.getLat());
            store.setLongitude(location.getLon());
        }

        int res = storeService.updateById(store);
        logger.info(String.format("update result:%d id:%d", res, store.getId()));
        return ResponseUtil.ok();

    }

}
