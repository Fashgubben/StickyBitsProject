import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArmadaCongifReader {

	public static String usingBufferedReader() {

		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader("./config.txt"))) {

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				contentBuilder.append(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}
}
