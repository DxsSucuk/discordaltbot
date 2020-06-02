package de.presti.opsource.altgen.cmd;

import java.util.function.Consumer;

import de.presti.opsource.altgen.utils.AltList;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Alt extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

		String msg = e.getMessage().getContentRaw();

		if(e.getAuthor().isBot() || e.getAuthor().isFake())
			return;
		
		if (msg.equalsIgnoreCase("!alt")) {
			if (AltList.size() > 0) {
				e.getAuthor().openPrivateChannel().queue(new Consumer<PrivateChannel>() {

					@Override
					public void accept(PrivateChannel arg0) {
						arg0.sendMessage("Alt: " + AltList.getAlt()).queue(null, new Consumer<Throwable>() {

							@Override
							public void accept(Throwable t) {
								e.getChannel()
										.sendMessage(
												e.getAuthor().getAsMention() + " couldnt send you a Privat Message!")
										.queue();
							}
						});
					}
				});
				e.getChannel().sendMessage("Check DM!").queue();
			} else {
				e.getChannel().sendMessage("Alt list Empty").queue();
			}
		}

		if (msg.startsWith("!alts")) {
			e.getChannel().sendMessage(AltList.size() + "").queue();
		}

		if (msg.startsWith("!addalt") && e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
			if (msg.replace(" ", "").equalsIgnoreCase("!addalt")) {
				e.getChannel().sendMessage("!addalt Alt").queue();
			} else {
				String[] param = msg.split(" ");
				if (param.length < 2) {
					e.getChannel().sendMessage("!addalt Alt").queue();
				} else {
					AltList.addAlt(param[1]);
					e.getChannel().sendMessage("Added Alt " + param[1] + "!").queue();
				}
			}
		}

	}

}
