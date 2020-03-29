package org.linlinjava.litemall.wx.web;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.system.SystemConfig;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.RandomUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallAccountFlow;
import org.linlinjava.litemall.db.enums.account.AccountFlowStatus;
import org.linlinjava.litemall.db.enums.account.AccountFlowType;
import org.linlinjava.litemall.db.service.LitemallAccountService;
import org.linlinjava.litemall.db.service.LitemallSystemConfigService;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.service.GetRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.linlinjava.litemall.core.constant.AccountConstant.ACCOUNT_FLOW_ORDER_RED_SUBJECT;
import static org.linlinjava.litemall.core.constant.AccountConstant.ACCOUNT_FLOW_SUBMIT_SUBJECT;

/**
 * 用户账户服务
 */
@RestController
@RequestMapping("/wx/account")
@Validated
public class WxAccountController extends GetRegionService {

    private final Log logger = LogFactory.getLog(WxAccountController.class);

    @Autowired
    private LitemallAccountService accountService;
    @Autowired
    private LitemallSystemConfigService systemConfigService;

    /**
     * 账户概览
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping("info")
    public Object info(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        LitemallAccount account = accountService.queryOrCreateByUid(userId);

        return ResponseUtil.ok(account);
    }

    /**
     * 账户流水
     *
     * @param userId 用户ID
     * @return 账户流水
     */
    @GetMapping("flow")
    public Object flow(@LoginUser Integer userId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallAccountFlow> flowList = accountService.querySelective(userId, page, limit);
        return ResponseUtil.okList(flowList);
    }

    /**
     * 账户充值
     *
     * @param userId 用户ID
     * @param flow   用户充值流水
     */
    @PostMapping("fund")
    public Object fund(@LoginUser Integer userId, @RequestBody LitemallAccountFlow flow) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (flow == null) {
            logger.info("no flow or amount <= 0.");
            return ResponseUtil.badArgument();
        }
        if (flow.getAmount() == null || flow.getAmount() <= 0) {
            logger.info("flow amount is null or amount <= 0.");
            return ResponseUtil.badArgument();
        }
        if (flow.getAccountId() == null || flow.getAccountId() <= 0) {
            logger.info("flow accountId is null or accountId <= 0.");
            return ResponseUtil.badArgument();
        }
        // 账户流水 uid + timestamp
        // 微信支付
        String uniqFlowId = String.format("%d-%d-%s-%d",
                userId,
                AccountFlowType.RECHARGE.getOrder(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                (long) (Math.random() * 10000)
        );

        // 充值金额，
        // 充值账户，
        // 充值业务流水，
        flow.setUserId(userId);
        flow.setUniqFlowId(uniqFlowId);
        flow.setType(AccountFlowType.RECHARGE.getCode());
        flow.setStatus(AccountFlowStatus.SUBMIT.getCode());
        flow.setSubject(ACCOUNT_FLOW_SUBMIT_SUBJECT);
        flow.setAddTime(LocalDateTime.now());
        flow.setUpdateTime(LocalDateTime.now());
        flow.setDeleted(false);
        accountService.createNewAccountFlow(flow);

        // 尚未对接 微信支付之前 完成靠本地调用 confirm 接口
        // TODO 对接完成后删除此段代码
        confirm(userId, flow.getUniqFlowId());

        return ResponseUtil.ok(flow.getUniqFlowId());
    }

    /**
     * 微信转账confirm地址
     *
     * @param userId 用户ID
     * @return 确认操作结果
     */
    @PostMapping("confirm")
    public Object confirm(@LoginUser Integer userId,
                          @RequestParam String uniqFlowId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (StringUtils.isEmpty(uniqFlowId)) {
            logger.info("no uniqFlowId.");
            return ResponseUtil.badArgument();
        }
        LitemallAccountFlow flow = accountService.queryByUniqFlowId(uniqFlowId);
        if (flow == null) {
            logger.info(String.format("flow is null, uniqFlowId=%s", uniqFlowId));
            return ResponseUtil.badArgumentNoData();
        }

        // 幂等
        if (AccountFlowStatus.CONFIRM.getCode().equalsIgnoreCase(flow.getStatus())) {
            return ResponseUtil.ok();
        }

        if (!AccountFlowStatus.SUBMIT.getCode().equalsIgnoreCase(flow.getStatus())) {
            logger.info(String.format("flow status not good. expert=submit, but=%s", flow.getStatus()));
            return ResponseUtil.badArgument();
        }

        flow.setStatus(AccountFlowStatus.CONFIRM.getCode());
        flow.setUpdateTime(LocalDateTime.now());
        accountService.confirmAccountFlowAndBalance(flow);

        return ResponseUtil.ok();
    }

    /**
     * 红包发放 RED_ENVELOPE
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     */
    @PostMapping("redenvelop")
    public Object redenvelop(@LoginUser Integer userId, @RequestParam Integer orderId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (orderId == null) {
            logger.info("no orderId.");
            return ResponseUtil.badArgument();
        }
        // TODO 校验订单状态-支付完成。 订单加索引。
//        if () {}

        // 校验无对应订单的红包
        LitemallAccountFlow flow = accountService.queryByUidAndOrderId(userId, orderId);
        if (flow != null) {
            logger.info(String.format("order already rewarded. orderId=%s", orderId));
            return ResponseUtil.badArgument();
        }

        // 账户流水 uid + timestamp
        // 微信支付
        String uniqFlowId = String.format("%d-%d-%s-%d",
                userId,
                AccountFlowType.RED_ENVELOPE.getOrder(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                (long) (Math.random() * 10000)
        );
        LitemallAccount account = accountService.queryOrCreateByUid(userId);

        flow = new LitemallAccountFlow();
        // 充值金额，
        // 充值账户，
        // 充值业务流水，
        flow.setUserId(userId);
        flow.setUniqFlowId(uniqFlowId);
        flow.setAccountId(account.getId());
        // 计算红包金额
        flow.setAmount(randomRedEnvelopAmount());
        flow.setType(AccountFlowType.RED_ENVELOPE.getCode());
        flow.setStatus(AccountFlowStatus.CONFIRM.getCode()); // 红包立即到账
        flow.setSubject(ACCOUNT_FLOW_ORDER_RED_SUBJECT);
        flow.setAddTime(LocalDateTime.now());
        flow.setUpdateTime(LocalDateTime.now());
        flow.setDeleted(false);
        accountService.insertAccountFlowAndBalance(flow);

        return ResponseUtil.ok(flow.getUniqFlowId());
    }

    /**
     * 计算随机红包
     * 单位：分
     */
    private Integer randomRedEnvelopAmount() {
        Map<String, String> redConfig = systemConfigService.listRed();
        if (!"ON".equalsIgnoreCase(redConfig.get(SystemConfig.LITEMALL_ACCOUNT_RED_SWITCH))) {
            return 0;
        }

        String jsonStr = redConfig.get(SystemConfig.LITEMALL_ACCOUNT_RED_RATE);
        ArrayNode arrayNode = (ArrayNode) JacksonUtil.toNode(jsonStr);

        Iterator<JsonNode> ite = arrayNode.elements();
        Map<String, String> ranges = new HashMap<>();
        Map<String, String> rates = new HashMap<>();

        while (ite.hasNext()) {
            JsonNode node = ite.next();
            ranges.put(node.get("range").textValue(), node.get("rate").textValue());
            rates.put(node.get("rate").textValue(), node.get("range").textValue());
        }

        // 按照金额大小 排序
        List<String> keys = new ArrayList<>(ranges.keySet());
        Collections.sort(keys);
        List<Integer> chooses = new ArrayList<>();
        for (String key : keys) {
            if (StringUtils.isNumber(ranges.get(key))) {
                chooses.add(Integer.valueOf(ranges.get(key)));
            }
        }

        // 两次随机，第一次 看金额 落在哪个排序；第二次随机，看具体数字。
        int amount = 0;
        Integer chosen = RandomUtil.choose(chooses);
        String chosenRange = rates.get("" + chosen);
        String[] crs = chosenRange.split("-");
        if (crs.length == 2) {
            int begin = StringUtils.isNumber(crs[0]) ? Integer.valueOf(crs[0]) : 0;
            int end = StringUtils.isNumber(crs[1]) ? Integer.valueOf(crs[1]) : 0;
            amount = (int) RandomUtil.wave(begin * 100.0, end * 100.0);
        }

        return amount;
    }

}