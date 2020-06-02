package de.presti.opsource.altgen.main;

import javax.security.auth.login.LoginException;

import de.presti.opsource.altgen.cmd.Alt;
import de.presti.opsource.altgen.utils.Events;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
public class Main {

	public static JDA jda;
	public static String token = "Your Bot Account Token!";
	public static boolean on = false;
	
	public static void main(String[] args) throws LoginException {
		jda = new JDABuilder(AccountType.BOT).setToken(token).build();
		
		jda.addEventListener(new Events());
		
		jda.addEventListener(new Alt());
	
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				on = false;
				System.out.println("Good Bye ;c");
				jda.shutdownNow();
			}
		});
		
	}
	
}
