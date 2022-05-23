package site.metacoding.jusotest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JusoController {

    @GetMapping("/")
    public String home(Model model) {
        String jusoUrl = "https://www.juso.go.kr/addrlink/addrLinkUrl.do?confmKey=devU01TX0FVVEgyMDIyMDUyMzEwNDczNDExMjU5OTk=&returnUrl=http://localhost:8080/juso/callback&resultType=4";

        model.addAttribute("jusoUrl", jusoUrl);
        return "home";
    }

    // 해당 콜백이 호출되면 브라우저한테 푸쉬해주는게 좋다. (stream이 유지되고 있어야한다. - 방법 2가지: 웹소켓, sse)
    @PostMapping("/juso/callback")
    public void jusoCallback(String roadFullAddr) {
        System.out.println(roadFullAddr);
        Store.roadFullAddr = roadFullAddr;
    }

    @GetMapping("/juso/check")
    public @ResponseBody String jusoCheck() {
        return Store.roadFullAddr;
    }
}
