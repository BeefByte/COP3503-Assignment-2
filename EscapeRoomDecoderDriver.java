import java.util.ArrayList;
public class EscapeRoomDecoderDriver {
public static void main(String[] args) {
EscapeRoomDecoder decoder = new EscapeRoomDecoder();
String[] testCases = {
"aab",
"aa",
"aabc",
"abcd"
};
for (String input : testCases) {
System.out.println("Input: " + input);
ArrayList<String> passwords = decoder.generatePasswords(input);
for (String p : passwords) {
System.out.println(" " + p);
}
System.out.println();
}
}
}
