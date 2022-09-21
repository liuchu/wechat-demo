package priv.liuchu.wechat.demo.route;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author liuchu
 * Date 2022/9/19
 * Time 14:47
 */
@RestController
@Slf4j
public class WechatMsgRouter {

    @GetMapping(value = "/wechat")
    public String verify(@RequestParam("signature") String signature,
                         @RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce,
                         @RequestParam("echostr") String echostr) throws NoSuchAlgorithmException {

        String token = "123456";

        String[] array = new String[] { token, timestamp, nonce };
        StringBuffer sb = new StringBuffer();
        // 字符串排序
        Arrays.sort(array);
        for (int i = 0; i < 3; i++) {
            sb.append(array[i]);
        }
        String str = sb.toString();

        // SHA1签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexstr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }
        String sign = hexstr.toString();

        if (sign.equals(signature)) {
            log.info("verified, go");
            return echostr;
        }else {
            log.error("Denied, signature not equals!");
            return "fail";
        }

    }

    @PostMapping(value = "/wechat", produces = MediaType.APPLICATION_XML_VALUE)
    public MsgXmlResp receiveMsg(@RequestBody MsgXmlReq msgXmlReq) {

        log.info("收到的消息：{}", msgXmlReq);
        MsgXmlResp resp = new MsgXmlResp();
        resp.setToUserName(msgXmlReq.getFromUserName());
        resp.setFromUserName(msgXmlReq.getToUserName());
        resp.setCreateTime(String.valueOf(new Date().getTime()));
        resp.setMsgType("text");
        resp.setContent("收到：" + msgXmlReq.getMsgType());

        log.info("返回的消息：{}", resp);

        return resp;
    }
}
