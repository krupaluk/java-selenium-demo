package settings;

import java.util.HashMap;

public class Settings {
	
	public HashMap<String, String> data;	
	public enum ENV {TEST, ACC}
	
	public Settings(ENV env) {
		data = new HashMap<>();
		switch (env) {
			case TEST:		
				data.put("email", "poweweh835@d3bb.com");
				data.put("password", "sparrowjack835");
				data.put("url", "https://www.alza.cz/");
				break;
			case ACC:		
				data.put("email", "poweweh835@d3bb.com");
				data.put("password", "sparrowjack835");
				data.put("url", "https://www.alza.cz/");
				break;
		}	
	}
}
