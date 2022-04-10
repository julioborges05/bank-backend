package main.Service;

import main.DAO.AgencyDao;
import main.DTO.AgencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {
    @Autowired
    private AgencyDao agencyDAO;

    public List<AgencyDTO> findAll() {
        return agencyDAO.findAll();
    }
}
