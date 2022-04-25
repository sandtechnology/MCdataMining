package com.snowk.mcdm;

import com.snowk.mcdm.command.CommandHandler;
import com.snowk.mcdm.command.framework.CommandFramework;
import com.snowk.mcdm.config.Config;
import org.bukkit.plugin.java.JavaPlugin;


public class MCDM extends JavaPlugin {

	public static MCDM snowkPlugin;
	public static final String version = "1.0.0";


	@Override
	public void onEnable() {
		snowkPlugin = this;
		Config.loadConfig("config.yml");
		getLogger().info("MCdataMining已启用 - By:Bear");
    	getLogger().info("源码于：https://github.com/i493052739 敬请关注更新！");
    	CommandFramework.register(this, new CommandHandler("dm"));
    }
    
    @Override
    public void onDisable() {
    	getLogger().info("MCdataMining已关闭");
    	
    }
    	
}
