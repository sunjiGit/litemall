package org.linlinjava.litemall.wx.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.location.LocationService;
import org.linlinjava.litemall.core.location.model.Location;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallStore;
import org.linlinjava.litemall.db.service.LitemallStoreService;
import org.linlinjava.litemall.wx.dto.LocationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 门店距离服务
 */
@SuppressWarnings("Duplicates")
@Service
public class WxStoreService {
    private final Log logger = LogFactory.getLog(WxStoreService.class);

    @Autowired
    private LitemallStoreService storeService;
    @Autowired
    private LocationService locationService;

    /**
     * 最近N家门店，距离10公里以内
     *
     * @param body
     * @return
     */
    public Object nearestNStores(String body) {
        logger.info(String.format("nearest N store and distance list param body=%s", body));
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        String longitude = JacksonUtil.parseString(body, "lon");
        String latitude = JacksonUtil.parseString(body, "lat");
        String size = JacksonUtil.parseString(body, "size");
        if (StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
            return ResponseUtil.badArgument();
        }
        if (!StringUtils.isNumeric(size)) {
            size = "8";
        }
        try {
            List<LitemallStore> stores = storeService.all();
            if (CollectionUtils.isEmpty(stores)) {
                logger.info("store list is empty");
                return ResponseUtil.badArgumentNoData();
            }

            List<LocationInfo> locationInfoList = new ArrayList<>();
            Location origin = new Location(longitude, latitude);
            for (LitemallStore store : stores) {
                Location destination;
                if (StringUtils.isNotBlank(store.getLongitude()) && StringUtils.isNotBlank(store.getLatitude())) {
                    destination = new Location(store.getLongitude(), store.getLatitude());
                    String distance = locationService.calcDistance(origin, destination);
                    LocationInfo info = new LocationInfo(store, origin, distance);
                    locationInfoList.add(info);
                } else if (StringUtils.isNotBlank(store.getAddressDetail())) {
                    destination = locationService.getLocation(store.getAddressDetail());
                    if (destination != null) {
                        // 经纬度信息顺便更新一波
                        store.setLongitude(destination.getLon());
                        store.setLatitude(destination.getLat());
                        store.setUpdateTime(LocalDateTime.now());
                        storeService.updateById(store);

                        String distance = locationService.calcDistance(origin, destination);
                        LocationInfo info = new LocationInfo(store, origin, distance);
                        locationInfoList.add(info);
                    }
                }
            }

            locationInfoList.sort((o1, o2) -> {
                Long dis1 = Long.valueOf(o1.getDistance());
                Long dis2 = Long.valueOf(o2.getDistance());
                if (dis1 > dis2) {
                    return 1;
                } else if (dis1 < dis2) {
                    return -1;
                } else {
                    return 0;
                }
            });

            Integer sizeInt = Integer.valueOf(size);
            locationInfoList = locationInfoList.subList(0,
                    locationInfoList.size() >= sizeInt ? sizeInt: locationInfoList.size());

            Object result = ResponseUtil.okList(locationInfoList);
            logger.info(String.format("nearest N store+distance result=%s, param body=%s", result, body));
            return result;
        } catch (Exception e) {
            logger.error("nearest N store+distance error.", e);
            return ResponseUtil.serious();
        }
    }

    /**
     * 最近门店 && 距离
     *
     * @param body
     * @return
     */
    public Object nearestStoreAndDistance(String body) {
        logger.info(String.format("nearest store and distance list param body=%s", body));
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        String longitude = JacksonUtil.parseString(body, "lon");
        String latitude = JacksonUtil.parseString(body, "lat");
        if (StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
            return ResponseUtil.badArgument();
        }
        try {
            List<LitemallStore> stores = storeService.all();
            if (CollectionUtils.isEmpty(stores)) {
                logger.info("store list is empty");
                return ResponseUtil.badArgumentNoData();
            }

            List<LocationInfo> locationInfoList = new ArrayList<>();
            Location origin = new Location(longitude, latitude);
            for (LitemallStore store : stores) {
                Location destination;
                if (StringUtils.isNotBlank(store.getLongitude()) && StringUtils.isNotBlank(store.getLatitude())) {
                    destination = new Location(store.getLongitude(), store.getLatitude());
                    String distance = locationService.calcDistance(origin, destination);
                    LocationInfo info = new LocationInfo(store, origin, distance);
                    locationInfoList.add(info);
                } else if (StringUtils.isNotBlank(store.getAddressDetail())) {
                    destination = locationService.getLocation(store.getAddressDetail());
                    if (destination != null) {
                        // 经纬度信息顺便更新一波
                        store.setLongitude(destination.getLon());
                        store.setLatitude(destination.getLat());
                        store.setUpdateTime(LocalDateTime.now());
                        storeService.updateById(store);

                        String distance = locationService.calcDistance(origin, destination);
                        LocationInfo info = new LocationInfo(store, origin, distance);
                        locationInfoList.add(info);
                    }
                }
            }

            locationInfoList.sort((o1, o2) -> {
                Long dis1 = Long.valueOf(o1.getDistance());
                Long dis2 = Long.valueOf(o2.getDistance());
                if (dis1 > dis2) {
                    return 1;
                } else if (dis1 < dis2) {
                    return -1;
                } else {
                    return 0;
                }
            });

            Object result = ResponseUtil.ok(locationInfoList.get(0));
            logger.info(String.format("nearest store+distance result=%s, param body=%s", result, body));
            return result;
        } catch (Exception e) {
            logger.error("nearest store+distance error.", e);
            return ResponseUtil.serious();
        }
    }

    /**
     * 所有门店 && 距离
     *
     * @param body
     * @return
     */
    public Object allStoresAndDistance(String body) {
        logger.info(String.format("all store and distance list param body=%s", body));

        if (body == null) {
            return ResponseUtil.badArgument();
        }
        String longitude = JacksonUtil.parseString(body, "lon");
        String latitude = JacksonUtil.parseString(body, "lat");
        if (StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
            return ResponseUtil.badArgument();
        }

        try {
            List<LitemallStore> stores = storeService.all();
            if (CollectionUtils.isEmpty(stores)) {
                logger.info("store list is empty");
                return ResponseUtil.badArgumentNoData();
            }

            List<LocationInfo> locationInfoList = new ArrayList<>();
            Location origin = new Location(longitude, latitude);
            for (LitemallStore store : stores) {
                Location destination;
                if (StringUtils.isNotBlank(store.getLongitude()) && StringUtils.isNotBlank(store.getLatitude())) {
                    destination = new Location(store.getLongitude(), store.getLatitude());
                    String distance = locationService.calcDistance(origin, destination);
                    LocationInfo info = new LocationInfo(store, origin, distance);
                    locationInfoList.add(info);
                } else if (StringUtils.isNotBlank(store.getAddressDetail())) {
                    destination = locationService.getLocation(store.getAddressDetail());
                    if (destination != null) {
                        // 经纬度信息顺便更新一波
                        store.setLongitude(destination.getLon());
                        store.setLatitude(destination.getLat());
                        store.setUpdateTime(LocalDateTime.now());
                        storeService.updateById(store);

                        String distance = locationService.calcDistance(origin, destination);
                        LocationInfo info = new LocationInfo(store, origin, distance);
                        locationInfoList.add(info);
                    }
                }
            }

            locationInfoList.sort((o1, o2) -> {
                Long dis1 = Long.valueOf(o1.getDistance());
                Long dis2 = Long.valueOf(o2.getDistance());
                if (dis1 > dis2) {
                    return 1;
                } else if (dis1 < dis2) {
                    return -1;
                } else {
                    return 0;
                }
            });

            Object result = ResponseUtil.okList(locationInfoList);
            logger.info(String.format("store+distance list result=%s, param body=%s",
                    result, body));
            return result;
        } catch (Exception e) {
            logger.error("store+distance list error.", e);
            return ResponseUtil.serious();
        }
    }

    /**
     * 选定地址 && 距离
     *
     * @param body
     * @return
     */
    public Object addressToStore(String body) {
        logger.info(String.format("address to store param body=%s", body));

        if (body == null) {
            return ResponseUtil.badArgument();
        }
        String address = JacksonUtil.parseString(body, "address");
        if (StringUtils.isBlank(address)) {
            return ResponseUtil.badArgument();
        }

        try {
            List<LitemallStore> stores = storeService.all();
            if (CollectionUtils.isEmpty(stores)) {
                logger.info("store list is empty");
                return ResponseUtil.badArgumentNoData();
            }

            List<LocationInfo> locationInfoList = new ArrayList<>();
            Location origin = locationService.getLocation(address);

            for (LitemallStore store : stores) {
                Location destination;
                if (StringUtils.isNotBlank(store.getLongitude()) && StringUtils.isNotBlank(store.getLatitude())) {
                    destination = new Location(store.getLongitude(), store.getLatitude());
                    String distance = locationService.calcDistance(origin, destination);
                    LocationInfo info = new LocationInfo(store, origin, distance);
                    locationInfoList.add(info);
                } else if (StringUtils.isNotBlank(store.getAddressDetail())) {
                    destination = locationService.getLocation(store.getAddressDetail());
                    if (destination != null) {
                        // 经纬度信息顺便更新一波
                        store.setLongitude(destination.getLon());
                        store.setLatitude(destination.getLat());
                        store.setUpdateTime(LocalDateTime.now());
                        storeService.updateById(store);

                        String distance = locationService.calcDistance(origin, destination);
                        LocationInfo info = new LocationInfo(store, origin, distance);
                        locationInfoList.add(info);
                    }
                }
            }

            locationInfoList.sort((o1, o2) -> {
                Long dis1 = Long.valueOf(o1.getDistance());
                Long dis2 = Long.valueOf(o2.getDistance());
                if (dis1 > dis2) {
                    return 1;
                } else if (dis1 < dis2) {
                    return -1;
                } else {
                    return 0;
                }
            });

            Object result = ResponseUtil.okList(locationInfoList);
            logger.info(String.format("store+distance list result=%s, param body=%s",
                    result, body));
            return result;
        } catch (Exception e) {
            logger.error("store+distance list error.", e);
            return ResponseUtil.serious();
        }
    }
}