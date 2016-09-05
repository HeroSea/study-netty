package me.ysee.netty.server;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

/**
 * Created by herosea on 16/8/21.
 */
public class DataExtractorTest {

    @Test
    public void show() throws Exception {
        String s = "40303030303030303030303e3e303030303030303030303c3630303832323432363238323a323c32" +
                "3f33323336333a34303438343a343c343c343e3530353235333534353430363038303b303c303e30" +
                "3a3039303a303c313230323032303230323032303230343032303230313036303030383032303030" +
                "32303230303032303130363034343a30320d";

        byte[] bytes = Hex.decodeHex(s.toCharArray());

        DataExtractor.show(bytes);
    }
}