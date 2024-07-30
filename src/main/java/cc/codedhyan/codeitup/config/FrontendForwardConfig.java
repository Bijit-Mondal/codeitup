package cc.codedhyan.codeitup.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class FrontendForwardConfig {
    @GetMapping({"/app","/"})
    public String forward() {
        log.info("Forwarding to /app");
        return "forward:/app/index.html";
    }
}