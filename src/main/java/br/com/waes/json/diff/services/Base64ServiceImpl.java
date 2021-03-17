package br.com.waes.json.diff.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class Base64ServiceImpl implements Base64Service {

    private final Base64.Decoder decoder = Base64.getDecoder();

    @Override
    public String decode(String content) {
        return new String(decoder.decode(content.getBytes()));
    }
}
