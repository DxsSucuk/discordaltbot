package de.presti.opsource.altgen.utils;

import de.presti.opsource.altgen.main.Main;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Activity.ActivityType;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Events extends ListenerAdapter {

	public Thread checker;

	@SuppressWarnings("static-access")
	public void onReady(ReadyEvent e) {

		Main.on = true;

		checker = (new Thread(new Runnable() {

			@Override
			public void run() {
				while (Main.on) {
					Main.jda.getPresence()
							.setActivity(Activity.of(ActivityType.WATCHING, "over " + Main.jda.getGuilds().size() + " Server!"));
					try {
						checker.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}));

		try {
			checker.sleep(5000);
			checker.start();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void onShutdown(ShutdownEvent e) {
		Main.on = false;
		checker.interrupt();
	}

}
