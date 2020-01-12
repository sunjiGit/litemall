package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.wx.service.WxStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 门店地址服务
 */
@RestController
@RequestMapping("/wx/store")
@Validated
public class WxStoreController {
    private final Log logger = LogFactory.getLog(WxStoreController.class);

    @Autowired
    private WxStoreService wxStoreService;


    /**
     * 当前定位 距离最近的门店
     *
     * @param body   地址信息-经纬度，{ lon: XXX.XXXXXX , lat: XXX.XXXXXX }
     * @return 门店 + 距离（单位：米）
     * {..., "data":{"store": {"id":1,"name":"测试门店","addressDetail":"北京市朝阳区阜通东大街6号"}, "distance": 500} }
     */
    @PostMapping("/nearest_store_distance")
    public Object nearestStoreAndDistance(@RequestBody String body) {
        return wxStoreService.nearestStoreAndDistance(body);
    }

    /**
     * 当前定位 到 所有门店距离
     *
     * @param body   地址信息-经纬度，{ lon: XXX.XXXXXX , lat: XXX.XXXXXX }
     * @return 门店 + 距离（单位：米）
     * {..., "data":{"total":9,"pages":1,"limit":10,"page":1,"list":[XXXX]}}
     * ps: list里的格式同nearest_store_distance接口
     */
    @PostMapping("/all_store_distance")
    public Object allStoresAndDistance(@RequestBody String body) {
        return wxStoreService.allStoresAndDistance(body);
    }

    /**
     * 填写的地址 到 所有门店距离
     *
     * @param body   地址信息-经纬度，{ address: 北京市朝阳区阜通东大街6号 }
     * @return 门店 + 距离（单位：米）
     * {..., "data":{"total":9,"pages":1,"limit":10,"page":1,"list":[XXXX]}}
     * ps: list里的格式同nearest_store_distance接口
     */
    @PostMapping("/address_store_distance")
    public Object addressToStore(@RequestBody String body) {
        return wxStoreService.addressToStore(body);
    }
}
