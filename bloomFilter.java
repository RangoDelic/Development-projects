package psa.naloga0;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Naloga {
    boolean[] podatki;
    String hashAlgorithm = "MD5";
    final MessageDigest digestFunc;
    int hashCount;
    int velikost;
    
    public Naloga(int velikost, int hashCount) {
        this.velikost = velikost;
        this.hashCount = hashCount;
        podatki = new boolean[this.velikost];
        MessageDigest tmp; 
        try {
            tmp = java.security.MessageDigest.getInstance(hashAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            tmp = null;
        }
        digestFunc = tmp;
    }

    public void clear() {
        Arrays.fill(podatki, false);
    }

    public void add(String input) {
        int[] arrayOfHashes = createHashes(input.getBytes(), hashCount);
        for (int i = 0; i < arrayOfHashes.length; i++) {
            podatki[arrayOfHashes[i]] = true;
        }
    }

    public boolean contains(String input) {
        boolean result = true;
        int[] arrayOfHashes = createHashes(input.getBytes(), hashCount);
        for (int i = 0; i < arrayOfHashes.length; i++) {
            result = result & podatki[arrayOfHashes[i]];
        }
        return result;
    }

    public int[] createHashes(byte[] data, int hashes) {
        int[] result = new int[hashes];

        int k = 0;
        while (k < hashes) {
            byte[] digest;
            digest = digestFunc.digest(data);
        
            for (int i = 0, j = 0; i < digest.length && k < hashes; i += 2, j++) {
                result[j] = Math.abs(((int) digest[i] << 8) | ((int) digest[i + 1] & 0xFF)) % velikost;
                k++;
            }
        }
        return result;
    }
}
