package br.com.waes.json.diff.services;

/**
 * Interface with inversion of control to converting base64 content
 */
public interface Base64Service {
    /**
     * Decodes base64 into a valid {@link String}
     * @param content not null or empty base64 encode
     * @return Valid {@link String} with base64 decoded
     */
    String decode(String content);
}
