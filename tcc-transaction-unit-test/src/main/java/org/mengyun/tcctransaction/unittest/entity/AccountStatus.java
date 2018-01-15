
package org.mengyun.tcctransaction.unittest.entity;

/**
 * Created by changmingxie on 12/3/15.
 */
public enum AccountStatus {

	/** 常规状态：1 **/
    NORMAL(1),

    /** 转账中：2 **/
    TRANSFERING(2);

    private int id;

    AccountStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
