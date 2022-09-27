package priv.liuchu.wechat.demo.view;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liuchu
 * Date 2022/9/27
 * Time 10:52*/
@Controller
public class IndexController {
    @RequestMapping(value = "/biz1", method = RequestMethod.GET)
    public String biz1() {
        return "biz1";
    }

    @RequestMapping(value = "/biz2", method = RequestMethod.GET)
    public String biz2() {
        return "biz2";
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        return "init";
    }

    @RequestMapping(value = "/jump", method = RequestMethod.GET)
    public String jump() {
        return "jump";
    }

    @GetMapping(
            value = "/MP_verify_RD39hijkR83jbIon.txt",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getFile() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/MP_verify_RD39hijkR83jbIon.txt");
        return IOUtils.toByteArray(in);
    }

}
