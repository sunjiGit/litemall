package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.linlinjava.litemall.db.dao.LitemallAccountFlowMapper;
import org.linlinjava.litemall.db.dao.LitemallAccountMapper;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallAccountExample;
import org.linlinjava.litemall.db.domain.LitemallAccountFlow;
import org.linlinjava.litemall.db.domain.LitemallAccountFlowExample;
import org.linlinjava.litemall.db.enums.account.AccountStatus;
import org.linlinjava.litemall.db.enums.account.AccountType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallAccountService {
    @Resource
    private LitemallAccountMapper accountMapper;
    @Resource
    private LitemallAccountFlowMapper accountFlowMapper;

    public LitemallAccount queryOrCreateByUid(Integer uid) {
        LitemallAccountExample example = new LitemallAccountExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        LitemallAccount account = accountMapper.selectOneByExample(example);
        if (account == null) {
            account = createNewAccount(uid);
        }
        return account;
    }

    public List<LitemallAccountFlow> querySelective(Integer userId, Integer pageNo, Integer pageSize) {
        LitemallAccountFlowExample example = new LitemallAccountFlowExample();
        example.or().andUserIdEqualTo(userId);

        PageHelper.startPage((pageNo - 1) * pageSize, pageSize);
        return accountFlowMapper.selectByExample(example);
    }

    private LitemallAccount createNewAccount(Integer userId) {
        LitemallAccount account = new LitemallAccount();
        account.setUserId(userId);
        account.setBalance(0);
        account.setAccountType(AccountType.CASH.getCode());
        account.setStatus(AccountStatus.NORMAL.getCode());
        account.setVersion(1);
        account.setAddTime(LocalDateTime.now());
        account.setUpdateTime(LocalDateTime.now());
        account.setDeleted(false);

        accountMapper.insert(account);
        return account;
    }

    public LitemallAccountFlow queryByUniqFlowId(String uniqFlowId) {
        LitemallAccountFlowExample example = new LitemallAccountFlowExample();
        example.or().andUniqFlowIdEqualTo(uniqFlowId);

        return accountFlowMapper.selectOneByExample(example);
    }

    @Transactional
    public void confirmAccountFlowAndBalance(LitemallAccountFlow flow) {
        LitemallAccount account = accountMapper.selectByPrimaryKey(flow.getAccountId());
        account.setBalance(account.getBalance() + flow.getAmount());
        account.setUpdateTime(LocalDateTime.now());
        accountMapper.updateBalanceByPrimaryKeyVersion(account);

        accountFlowMapper.updateByPrimaryKey(flow);
    }

    public int createNewAccountFlow(LitemallAccountFlow flow) {
       return accountFlowMapper.insert(flow);
    }

    public LitemallAccountFlow queryByUidAndOrderId(Integer userId, Integer orderId) {
        return null;
    }
}
