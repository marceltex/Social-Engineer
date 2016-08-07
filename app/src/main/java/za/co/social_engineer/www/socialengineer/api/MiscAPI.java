package za.co.social_engineer.www.socialengineer.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class that will contain miscellaneous methods used by various classes in this app. All methods
 * in this class are declared both public and static.
 *
 * Created by Marcel Teixeira on 2016/08/07.
 */
public class MiscAPI {

    /**
     * Method to return a string representation of a file
     *
     * @param inputStream Input Stream of the file
     * @return String representation of the file
     */
    public static String readTextFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        inputStream.close();
        reader.close();
        return stringBuilder.toString();
    }
}
