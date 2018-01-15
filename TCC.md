TCC模式属于分布式柔性事务的一种，基于两阶段，达到最终一致性的一种分布式事务一致性解决模式。TCC模式如下图所示：
![TCC模式概览](http://s3.mogucdn.com/mlcdn/c45406/180115_16fbh4g0a1kbgc84d9h1gh3j61di5_588x566.jpg)
一个完整的事务活动由一个主业务服务和若干个从业务服务组成。
  * 主业务服务负责发起并完成整个业务活动
  * 从业务服务负责提供TCC类型操作
  * 业务活动管理器负责控制业务活动的一致性，它登记业务活动中的操作，并在业务活动中提交时确认所有的TCC型操作的Confirm操作，在业务活动取消时调用所有TCC型操作的Cancel操作。
 以TCC开源框架(https://github.com/changmingxie/tcc-transaction)为例，介绍TCC的业务活动提交，确认，回滚等操作。

在框架内部定义了两个拦截切面，对业务活动进行增强，第一个切面负责全局事务的开启，提交等操作，第二个切面服务登记全局事务参与者，对从业务服务提供者的try，confirm，cancel按照场景进行分别调用。
以下是从主业务角度和从业务角度分别来看下TCC操作的流程。
![主业务流程](http://s3.mogucdn.com/mlcdn/c45406/180115_8f44fiee06674i8lggg7fae9glc06_916x1090.png)
![从业务流程](http://s3.mogucdn.com/mlcdn/c45406/180115_0lja5cd56k96170fk1j1ajakgegje_916x893.png)



正常流程解析完之后，来分析一下异常的流程，这里列举几种场景：
- 从服务B try 成功, 从服务C try失败，主服务try 失败
- 从服务B commit  成功, 从服务C commit 失败，主服务commit  失败
- 从服务B cancel  成功, 从服务C cancel 成功，主服务commit  失败

前面提到了，TCC模式也是基于补偿来实现事务的最终一致性，在Tcc-transaction这个框架中，有一个定时任务，扫描出一定时间内未完成的事务来进行补偿操作。
1.如果在事务日志记录中，记录的事务状态是commit ,则执行commit操作。
2.其它情况，均执行cancel操作。
这里就需要保证的一点，任何接口都应该是可幂等操作接口。