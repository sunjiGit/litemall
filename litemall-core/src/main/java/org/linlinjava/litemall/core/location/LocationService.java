package org.linlinjava.litemall.core.location;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.location.model.Location;
import org.linlinjava.litemall.core.util.HttpUtil;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 物流查询服务
 * <p>
 * 快递鸟即时查询API http://www.kdniao.com/api-track
 */
@Component
public class LocationService {

    private final Log logger = LogFactory.getLog(LocationService.class);

    //请求url
    private String GeoReqURL = "https://restapi.amap.com/v3/geocode/geo";
    private String DistanceReqURL = "https://restapi.amap.com/v3/distance";

    //TODO 用配置项替换
    private static String gaodeKey = "2b43c6e2bab223d9665833e79096c0e1";

    /**
     * 获取GEO信息
     *
     * @param detailAddress
     * @return
     */
    public Location getLocation(String detailAddress) {

        Map<String, String> params = new HashMap<>();
        params.put("address", detailAddress);
        params.put("output", "JSON");
        params.put("key", gaodeKey);
        String result = HttpUtil.sendGet(GeoReqURL, params);

        JsonNode jsonNode = (JsonNode) JacksonUtil.toNode(result);
        if (jsonNode.get("status").textValue().equalsIgnoreCase("1")) {
            JsonNode geocode = jsonNode.get("geocodes").get(0);
            String[] position = geocode.get("location").textValue().split(",");
            return new Location(position[0], position[1]);
        }

        // 异常返回
        logger.info("get location failed. result=" + result);
        return null;
    }

    /**
     * 获取距离
     *
     * @param origin
     * @param destination
     * @return
     */
    public String calcDistance(Location origin, Location destination) {

        Map<String, String> params = new HashMap<>();
        params.put("origins", origin.toPosition());
        params.put("destination", destination.toPosition());
        params.put("type", "0"); // 0：直线距离  1：驾车导航距离（仅支持国内坐标）。 3：步行规划距离（仅支持5km之间的距离）
        params.put("output", "JSON");
        params.put("key", gaodeKey);
        String result = HttpUtil.sendGet(DistanceReqURL, params);

        JsonNode jsonNode = (JsonNode) JacksonUtil.toNode(result);
        if (jsonNode.get("status").textValue().equalsIgnoreCase("1")) {
            JsonNode node = jsonNode.get("results").get(0);
            return node.get("distance").textValue();
        }

        // 异常返回
        logger.info(String.format("calculate location failed. origin=%s, destination=%s", origin, destination));
        return null;
    }

}
