
package org.mengyun.tcctransaction.unittest.thirdservice;

import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 账户记录服务接口.
 * Created by changmingxie on 12/8/15.
 */
public interface AccountRecordService {
    public void record(TransactionContext transactionContext, long accountId, int amount);

    void recordConfirm(TransactionContext transactionContext, long accountId, int amount);

    void recordCancel(TransactionContext transactionContext, long accountId, int amount);
}
