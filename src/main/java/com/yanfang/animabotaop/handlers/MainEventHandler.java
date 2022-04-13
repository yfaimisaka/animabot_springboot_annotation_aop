package com.yanfang.animabotaop.handlers;

import com.yanfang.animabotaop.annotations.Event;
import com.yanfang.animabotaop.annotations.Events;
import com.yanfang.animabotaop.enums.EventEnum;
import com.yanfang.animabotaop.functions.Test;
import com.yanfang.animabotaop.utils.SpringUtil;
import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.MessageEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Aspect
public class MainEventHandler extends SimpleListenerHost
{
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception)
    {
        log.warn(exception.toString());
        exception.printStackTrace();
    }

    @EventHandler
    public ListeningStatus onMessage(@NotNull MessageEvent event) throws Exception
    {

        System.out.println("执行了");
        @NotNull Test test = SpringUtil.getBean(Test.class);
        test.test(event);
        return ListeningStatus.LISTENING;

    }

    @Pointcut("@annotation(com.yanfang.animabotaop.annotations.Events)")
    public void judgeMethodPointCut() {}

    @Around("judgeMethodPointCut() && @annotation(events)")
    public void around(@NotNull ProceedingJoinPoint joinPoint, @NotNull Events events) throws Throwable
    {
        for (Event eventAnnotation : events.value())
        {
            System.out.println("测试");
            Object[] argValues = joinPoint.getArgs();
            MessageEvent event = (MessageEvent) argValues[0];
            if (eventAnnotation.authority())
            {
                System.out.println("已授权");
                // 有复杂逻辑就将judgeByMethod设为true， 交给方法进行复杂逻辑处理
                if (eventAnnotation.judgeByMethod() || (eventAnnotation.message().equals(event.getMessage().contentToString())))
                {
                    System.out.println("通过第二层测试");
                    if (event.getSubject() instanceof Group && eventAnnotation.eventType() == EventEnum.GROUPEVENT)
                    {
                        joinPoint.proceed(argValues);
                    }
                    else if (event.getSubject() instanceof Friend && eventAnnotation.eventType() == EventEnum.MASTEREVENT)
                    {
                        joinPoint.proceed(argValues);
                    }
                    else if (event.getSubject() instanceof Member && eventAnnotation.eventType() == EventEnum.TEMPEVENT)
                    {
                        joinPoint.proceed(argValues);
                    }
                }
            }
        }
    }

}
