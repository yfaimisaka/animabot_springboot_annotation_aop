package com.yanfang.animabotaop.bot;

import com.yanfang.animabotaop.handlers.MainEventHandler;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.stereotype.Component;

/**
 * bot实体类
 */
@Component
public class AnimaBot
{
    public static Bot bot;

    public static Bot getBot()
    {
        return bot;
    }

    // 启动bot
    public void start()
    {
        long account = xxxxxxxxx;
        String password = "xxxxxxxx";

        bot = BotFactory.INSTANCE.newBot(account, password, new BotConfiguration()
        {{
            fileBasedDeviceInfo();
        }});

        GlobalEventChannel.INSTANCE.registerListenerHost(new MainEventHandler());
//        GlobalEventChannel.INSTANCE.registerListenerHost(new OtherEventHandler());
        bot.login();
    }

}
