package com.yanfang.animabotaop;

import com.yanfang.animabotaop.bot.AnimaBot;
import com.yanfang.animabotaop.utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimaBotAopApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(AnimaBotAopApplication.class, args);
        AnimaBot bot = SpringUtil.getBean(AnimaBot.class);
        bot.start();
    }

}
