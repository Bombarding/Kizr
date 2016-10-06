import java.util.Scanner;

public class CaesarCipher
{
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	public static String encrypt(String plainText, int shiftKey)
	{
		plainText = plainText.toLowerCase();
		String cipherText = "";
		for(int i=0; i<plainText.length(); i++)
		{
			int charPosition = ALPHABET.indexOf(plainText.charAt(i));
			int keyVal = (shiftKey + charPosition) % 26;
			char replaceVal = ALPHABET.charAt(keyVal);
			cipherText += replaceVal;
		}
		return cipherText;
	}
	public static String decrypt(String cipherText, int shiftKey)
	{
		cipherText = cipherText.toLowerCase();
		String plainText = "";
		for(int i=0; i<cipherText.length(); i++)
		{
			int charPosition = ALPHABET.indexOf(cipherText.charAt(i));
			int keyVal = (charPosition - shiftKey) % 26;
			if(keyVal < 0)
			{
				keyVal = ALPHABET.length() + keyVal;
			}
			char replaceVal = ALPHABET.charAt(keyVal);
			plainText += replaceVal;
		}
		return plainText;
	}
	public static void intro()
	{
		System.out.println("**Welcome to the Caesar Cipher Algorithm:");
		System.out.println("Please enter the string or word for encryption:");
	}
	
	public static void main(String [] args)
	{
		Scanner scan = new Scanner(System.in);
		intro();
		String message = new String();
		message = scan.nextLine();
		System.out.println(encrypt(message,3));
		System.out.println(decrypt(encrypt(message,3),3));
		scan.close();
		System.out.println("Thank you for using the Encryption");
	}
	
	
}