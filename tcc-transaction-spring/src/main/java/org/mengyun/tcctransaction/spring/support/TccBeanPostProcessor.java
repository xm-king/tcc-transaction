
package org.mengyun.tcctransaction.spring.support;

import org.mengyun.tcctransaction.support.BeanFactory;
import org.mengyun.tcctransaction.support.BeanFactoryAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * TCC Bean后置处理程序
 * Created by changmingxie on 11/20/15.
 */
@Component
public class TccBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

	/**
	 * Spring启动时加载.
	 */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();

        if (applicationContext.getParent() == null) {

            BeanFactoryAdapter.setBeanFactory(applicationContext.getBean(BeanFactory.class));
        }
    }
}
