package gr.aueb.cf.cfcampingsjax.service.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ICampingDAO;
import gr.aueb.cf.cfcampingsjax.dto.CampingDTO;
import gr.aueb.cf.cfcampingsjax.model.Camping;
import gr.aueb.cf.cfcampingsjax.service.interfaces.ICampingService;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

public class CampingServiceImpl implements ICampingService {

    private final ICampingDAO campingDAO;

    // Dependency Injection
    public CampingServiceImpl(ICampingDAO campingDAO) {
        this.campingDAO = campingDAO;
    }

    @Override
    public CampingDTO getCampingByCode(String campCode) {
        if(campCode == null || campCode.isEmpty()) {
            throw new IllegalArgumentException("Camping code cannot be null or empty.");
        }
        Camping camping = campingDAO.getCampingByCode(campCode);
        return convertToDTO(camping);
    }

    @Override
    public List<CampingDTO> getAllCampings() {
        List<Camping> campings = campingDAO.getAllCampings();
        List<CampingDTO> campingDTOs = new ArrayList<>();
        for (Camping camping : campings) {
            campingDTOs.add(convertToDTO(camping));
        }
        return campingDTOs;
    }

    @Override
    public CampingDTO insertCamping(CampingDTO campingDTO) {
        Camping camping = convertToModel(campingDTO);
        if (campingDAO.getCampingByCode(camping.getCampCode()) != null) {
            throw new IllegalArgumentException("Camping with this code already exists.");
        }
        camping = campingDAO.insertCamping(camping);
        return convertToDTO(camping);
    }

    @Override
    public CampingDTO updateCamping(CampingDTO campingDTO) {
        Camping camping = convertToModel(campingDTO);
        if (campingDAO.getCampingByCode(camping.getCampCode()) == null) {
            throw new IllegalArgumentException("No such camping exists to update.");
        }
        camping = campingDAO.updateCamping(camping);
        return convertToDTO(camping);
    }

    @Override
    public CampingDTO deleteCamping(String campCode) {
        if(campCode == null || campCode.isEmpty()) {
            throw new IllegalArgumentException("Camping code cannot be null or empty.");
        }
        Camping camping = campingDAO.deleteCamping(campCode);
        return convertToDTO(camping);
    }

    private CampingDTO convertToDTO(Camping camping) {
        if (camping == null) {
            return null;
        }
        return new CampingDTO(camping.getCampCode(), camping.getCampName(), camping.getNumOfEmp());
    }

    private Camping convertToModel(CampingDTO campingDTO) {
        if (campingDTO == null) {
            return null;
        }
        return new Camping(campingDTO.getCampCode(), campingDTO.getCampName(), campingDTO.getNumOfEmp());
    }
}
