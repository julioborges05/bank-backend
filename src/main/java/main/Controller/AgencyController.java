package main.Controller;

import main.DTO.AgencyDTO;
import main.Service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @GetMapping("/findAllAgencies")
    public List<AgencyDTO> findAll() {
        return agencyService.findAll();
    }
}
