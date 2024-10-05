import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CifraDeslocamento {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Cria map de letras p/nro e vice versa
        Map<Character, Integer> charParaNro = criarCharParaNro();
        Map<Integer, Character> nroParaChar = criarNroParaChar();

        // entrada do usuário
        System.out.print("Digite o texto(letras minusculas) para criptografar: ");
        String texto = scan.nextLine();

        System.out.print("Digite a chave(letra com o mesmo tamanho do texto): ");
        String chave = scan.nextLine();
        System.out.println();

        // criptografar
        String textoEncriptado = encriptar(texto, chave, charParaNro, nroParaChar);
        System.out.println("Texto criptografado: " + textoEncriptado);

        // decriptografar
        String textoDecriptado = decriptar(textoEncriptado, chave, charParaNro, nroParaChar);
        System.out.println("Texto decriptografado: " + textoDecriptado);

        scan.close();
    }

    // associar caracteres com numeros
    public static Map<Character, Integer> criarCharParaNro() {
        Map<Character, Integer> charParaNro = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            charParaNro.put((char) ('a' + i), i);
        }
        return charParaNro;
    }

    // associar numeros com caracteres
    public static Map<Integer, Character> criarNroParaChar() {
        Map<Integer, Character> nroParaChar = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            nroParaChar.put(i, (char) ('a' + i));
        }
        return nroParaChar;
    }

    // encriptar o texto
    public static String encriptar(String texto, String chave, Map<Character, Integer> charParaNro,
            Map<Integer, Character> nroParaChar) {
        StringBuilder textoEncriptado = new StringBuilder();
        int tamanhoChave = chave.length();

        for (int i = 0; i < texto.length(); i++) {
            char textoChar = texto.charAt(i);
            char chaveChar = chave.charAt(i % tamanhoChave);

            int nroEncriptado = (charParaNro.get(textoChar) + charParaNro.get(chaveChar)) % 26;
            textoEncriptado.append(nroParaChar.get(nroEncriptado));
        }

        return textoEncriptado.toString();
    }

    // decriptar o texto
    public static String decriptar(String textoEncriptado, String chave, Map<Character, Integer> charParaNro,
            Map<Integer, Character> nroParaChar) {
        StringBuilder textoDecriptado = new StringBuilder();
        int tamanhoChave = chave.length();

        for (int i = 0; i < textoEncriptado.length(); i++) {
            char charEncriptado = textoEncriptado.charAt(i);
            char chaveChar = chave.charAt(i % tamanhoChave);

            int nroDecriptado = (charParaNro.get(charEncriptado) - charParaNro.get(chaveChar) + 26) % 26;
            textoDecriptado.append(nroParaChar.get(nroDecriptado));
        }

        return textoDecriptado.toString();
    }

}