package com.snowk.mcdm;

import org.bukkit.plugin.java.JavaPlugin;
import com.snowk.mcdm.command.CommandHandler;
import com.snowk.mcdm.command.framework.CommandFramework;
import com.snowk.mcdm.config.Config;


public class mcdm extends JavaPlugin{
	
	public static mcdm snowkPlugin;
	public static final String version = "1.0.0";

	
    @Override
    public void onEnable() {
    	snowkPlugin = this;
    	Config.loadConfig("config.yml");
    	getLogger().info("MCdataMining������ - By:Bear");
    	getLogger().info("Դ���ڣ�https://github.com/i493052739 �����ע���£�");
    	CommandFramework.register(this, new CommandHandler("dm"));
    }
    
    @Override
    public void onDisable() {
    	getLogger().info("MCdataMining�ѹر�");
    	
    }
    	
}
