package utility;

import model.User;

import java.util.Random;

public class DataProvider {

    private static final String ALPHA_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHA_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUM = "1234567890";

    public static String getText(int length) {
        StringBuilder text = new StringBuilder();
        for (int i=0; i<length; i++)
            text.append(ALPHA_LOWER.charAt(getRandomNumber(ALPHA_LOWER.length())));
        return text.toString();
    }

    public static User getUserData() {
        User user = new User();
        user.setFirstName(getName());
        user.setLastName(getName());
        user.setEmail(getEmail(user));
        user.setPhone(getPhone());
        user.setGender(getGender());
        user.setCountry("INDIA");
        user.setYear("1955");
        user.setMonth(getRandomNumber(12)+1);
        user.setDate("23");
        user.setPassword(getPassword());
        return user;
    }

    private static String getPassword() {
        StringBuilder password = new StringBuilder();
        for (int i=0; i<2; i++)
            password.append(ALPHA_LOWER.charAt(getRandomNumber(ALPHA_LOWER.length())));
        for (int i=0; i<2; i++)
            password.append(ALPHA_UPPER.charAt(getRandomNumber(ALPHA_UPPER.length())));
        for (int i=0; i<2; i++)
            password.append(NUM.charAt(getRandomNumber(NUM.length())));
        return password.toString();
    }

    private static String getGender() {
        int index = getRandomNumber(2);
        if (index == 0)
            return "M";
        else return "F";
    }

    public static String getPhone() {
        StringBuilder phone = new StringBuilder();
        for (int i=0; i<10; i++) {
            phone.append(NUM.charAt(getRandomNumber(NUM.length())));
        }
        return phone.toString();
    }

    private static String getEmail(User user) {
        return user.getFirstName()+"@"+user.getLastName()+".com";
    }

    public static String getName() {
        StringBuilder name = new StringBuilder();
        name.append(ALPHA_UPPER.charAt(getRandomNumber(ALPHA_UPPER.length())));
        for (int i = 0; i< 6; i++)
            name.append(ALPHA_LOWER.charAt(getRandomNumber(ALPHA_LOWER.length())));
        return name.toString();
    }

    private static int getRandomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

}
