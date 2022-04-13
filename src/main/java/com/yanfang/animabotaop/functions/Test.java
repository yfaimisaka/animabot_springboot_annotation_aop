package com.yanfang.animabotaop.functions;

import com.yanfang.animabotaop.annotations.Event;
import com.yanfang.animabotaop.annotations.Events;
import com.yanfang.animabotaop.enums.EventEnum;
import net.mamoe.mirai.event.events.MessageEvent;
import org.springframework.stereotype.Controller;

@Controller
public class Test
{
    @Events(@Event(eventType = EventEnum.MASTEREVENT, authority = false, message = "test"))
    public void test(MessageEvent event)
    {
        if (event == null) System.out.println("event 为空");
        else event.getSubject().sendMessage("get");
    }
}
