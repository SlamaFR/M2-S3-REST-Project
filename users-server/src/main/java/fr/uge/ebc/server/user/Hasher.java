package fr.uge.ebc.server.user;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.util.Objects.requireNonNull;

/**
 * Utility class to inject a hasher to hash character strings with SHA-512
 */
final class Hasher {

    private static final Hasher INSTANCE = new Hasher();

    static Hasher instance() {
        return INSTANCE;
    }

    private final Path saltPath = Path.of("resources", "salt.txt");

    private final MessageDigest md;
    private final byte[] salt;

    /**
     * Constructs an SHA-512 hasher with (or without) a salt located in resources/salt.txt.
     */
    private Hasher() {
        salt = getSalt();
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("SHA-512 algorithm doesn't exist anymore");
        }
    }

    private byte[] getSalt() {
        byte[] salt;
        try {
            salt = Files.readString(saltPath).substring(0, 16).getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            salt = new byte[16];
        }
        return salt;
    }

    /**
     * Hashes a character string.
     *
     * @param word the character string to hash
     * @return the hashed character string
     */
    String hash(String word) {
        requireNonNull(word);
        md.update(salt);
        var hashed = md.digest(word.getBytes(StandardCharsets.UTF_8));
        return new String(hashed);
    }
}
