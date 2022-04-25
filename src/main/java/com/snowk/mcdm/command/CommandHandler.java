package com.snowk.mcdm.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.snowk.mcdm.command.framework.CommandFramework;
import com.snowk.mcdm.command.doAnalysis;
import com.snowk.mcdm.command.doDBSCAN;

public class CommandHandler extends CommandFramework {
    public CommandHandler(String label) {
        super(label);
    }

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		
		if (args.length == 0) {
        	sender.sendMessage("��b====== MCdataMining v1.0 By:Bear ======");
            sender.sendMessage("��b/dm reload ��f-��e ���ز�� ��b���ָ���6/dm r");
            sender.sendMessage("��b/dm analysis ��f-��e �鿴������ͳ�Ʒ���ϵͳʹ�ð��� ��b���ָ���6/dm a");
            sender.sendMessage("��b/dm dbscan ��f-��e �鿴����������ϵͳʹ�ð��� ��b���ָ���6/dm d");
            sender.sendMessage("��b/dm t <Player> ��f-��e ��ָ����Ҵ��͵����");
            
        } else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("r")) {
	        if (sender.isOp() || sender.hasPermission("snowkfix.reload")) {
	        	sender.sendMessage("��b[Snowk]��e��ǰ�汾�������config����һ����Ҫ��������yumʵ���ȼ���");
	        } 

        } else if (args[0].equalsIgnoreCase("t") && args[1] != null) {
        	if (sender.isOp() && sender instanceof Player) {
        		Location loc = ((Player) sender).getLocation();
        		Player target = Bukkit.getPlayerExact(args[1]);
        		sender.sendMessage("��6���ڴ���...");
        		target.teleport(loc);
        	} else {sender.sendMessage("Ȩ�޲��㣬��ȷ�����Ƿ�Ϊop���Ҳ����ڿ���ִ̨�С�");}
        	
        } else if (args[0].equalsIgnoreCase("analysis") || args[0].equalsIgnoreCase("a")) {
        	sender.sendMessage("��b====== MCdataMining v1.0 By:Bear ======");
            sender.sendMessage("��b/dm analysis mob ��f-��e ����ͳ������������������� ��b���ָ���6/dm a m");
            sender.sendMessage("��b/dm analysis drop ��f-��e ����ͳ��������������е����� ��b���ָ���6/dm a d");
            sender.sendMessage("��b/dm analysis tile ��f-��e ����ͳ���������統ǰ���ص�����tiles���� ��b���ָ���6/dm a t");
            if (args[1].equalsIgnoreCase("mob") || args[1].equalsIgnoreCase("m")) {
            	doAnalysis.run(sender,"mob",true);
            } else if (args[1].equalsIgnoreCase("drop") || args[1].equalsIgnoreCase("d")) {
            	doAnalysis.run(sender,"drop",true);
            } else if (args[1].equalsIgnoreCase("tiles") || args[1].equalsIgnoreCase("t")) {
            	doAnalysis.run(sender,"tiles",true);
            }
            
        } else if (args[0].equalsIgnoreCase("dbscan") || args[0].equalsIgnoreCase("d")) {
        	sender.sendMessage("��b====== MCdataMining v1.0 By:Bear ======");
            sender.sendMessage("��b/dm dbscan mob <world>  ��f-��e �������ﲢѰ���ܼ����� ��b���ָ���6/dm d m <w>");
            sender.sendMessage("��b/dm dbscan drop <world>  ��f-��e ��������ﲢѰ���ܼ����� ��b���ָ���6/dm d d <w>");
            sender.sendMessage("��b/dm dbscan tile <world>  ��f-��e ����tiles��Ѱ���ܼ����� ��b���ָ���6/dm d t <w>");
            if (args[1].equalsIgnoreCase("mob") || args[1].equalsIgnoreCase("m")) {
            	doDBSCAN.run(sender,args[2],doAnalysis.run(sender,"mob",false),"������");
            } else if (args[1].equalsIgnoreCase("drop") || args[1].equalsIgnoreCase("d")) {
            	doDBSCAN.run(sender,args[2],doAnalysis.run(sender,"drop",false),"������");
            } else if (args[1].equalsIgnoreCase("tiles") || args[1].equalsIgnoreCase("t")) {
            	doDBSCAN.run(sender,args[2],doAnalysis.run(sender,"tiles",false),"tiles");
            }
        }
	}
	
}
