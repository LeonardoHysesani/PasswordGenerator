public class Generator {
    public static StringBuilder create(String selectedChars, int passLength) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passLength; i++) {
            password.append(selectedChars.charAt((int) (Math.random() * selectedChars.length())));
        }
        //debugging
        System.out.println("Password is: " + password);
        return password;
    }
}
