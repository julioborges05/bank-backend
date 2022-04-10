package main.Service;

import main.DAO.AgencyDAO;
import main.DTO.AgencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {
    @Autowired
    private AgencyDAO agencyDAO;

    public List<AgencyDTO> findAll() {
        return agencyDAO.findAll();
    }
}
