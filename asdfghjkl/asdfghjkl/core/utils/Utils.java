package core.utils;

import java.io.InputStream;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Utils {

	public static String load(String name) throws Exception {
		String out;
		try(InputStream in = Utils.class.getResourceAsStream(name);
			Scanner scanner = new Scanner(in, UTF_8.name());) {
				out = scanner.useDelimiter("\\A").next();
			
		}
		return out;
	}

}
