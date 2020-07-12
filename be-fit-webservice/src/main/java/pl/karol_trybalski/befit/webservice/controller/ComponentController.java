package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karol_trybalski.befit.domain.entity.Component;
import pl.karol_trybalski.befit.service.ComponentServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/components")
public class ComponentController {

    private final ComponentServiceImpl componentService;

    @Autowired
    public ComponentController(final ComponentServiceImpl componentService) {
        this.componentService = componentService;
    }


    @GetMapping
    public List<Component> list() {
        return componentService.findAll();
    }

}
