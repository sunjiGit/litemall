package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
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
public class AdminStoreController {
    private final Log logger = LogFactory.getLog(AdminStoreController.class);

    @Autowired
    private LitemallStoreService storeService;

    /**
     * 查询门店列表
     *
     * @return
     */
    @RequiresPermissions("admin:store:list")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店管理"}, button = "查询")
    @GetMapping("/list")
    public Object list() {
        logger.info("list all");
        return ResponseUtil.okList(storeService.all());
    }

    /**
     * 门店详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:store:read")
    @RequiresPermissionsDesc(menu = {"门店管理", "门店管理"}, button = "详情")
    @GetMapping("/detail")
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
    public Object create(@RequestBody LitemallStore store) {
        logger.info(String.format("create id:%d", store.getId()));
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
    public Object update(@RequestBody LitemallStore store) {
        logger.info(String.format("update id:%d", store.getId()));
        int res = storeService.updateById(store);
        logger.info(String.format("update result:%d id:%d", res, store.getId()));
        return ResponseUtil.ok();

    }

}
