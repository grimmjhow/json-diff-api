package br.com.waes.json.diff.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * Specific implementation of {@link Base64Service},
 * utilizing java default base64 library
 */
@Slf4j
@Service
public class Base64ServiceImpl implements Base64Service {

    private final Base64.Decoder decoder = Base64.getDecoder();

    /**
     * Decodes base64 into a valid {@link String} content
     * @param content not null or empty base64 encode
     * @return A valid {@link String} base64 decoded
     */
    @Override
    public String decode(String content) {
        return new String(decoder.decode(content.getBytes()));
    }
}
