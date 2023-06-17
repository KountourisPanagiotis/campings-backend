package gr.aueb.cf.cfcampingsjax.service.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IEmplacementsDAO;
import gr.aueb.cf.cfcampingsjax.dto.EmplacementDTO;
import gr.aueb.cf.cfcampingsjax.model.Emplacement;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IEmplacementService;

import java.util.ArrayList;
import java.util.List;

public class EmplacementServiceImpl implements IEmplacementService {

    private final IEmplacementsDAO emplacementsDAO;

    // Dependency Injection
    public EmplacementServiceImpl(IEmplacementsDAO emplacementsDAO) {
        this.emplacementsDAO = emplacementsDAO;
    }

    @Override
    public EmplacementDTO getEmplacementByCampCodeAndEmpNo(String campCode, int empNo) {
        Emplacement emplacement = emplacementsDAO.getEmplacementByCampCodeAndEmpNo(campCode, empNo);
        return convertToDTO(emplacement);
    }

    @Override
    public List<EmplacementDTO> getAllEmplacements() {
        List<Emplacement> emplacements = emplacementsDAO.getAllEmplacements();
        List<EmplacementDTO> emplacementDTOs = new ArrayList<>();
        for (Emplacement emplacement : emplacements) {
            emplacementDTOs.add(convertToDTO(emplacement));
        }
        return emplacementDTOs;
    }

    @Override
    public EmplacementDTO insertEmplacement(EmplacementDTO emplacementDTO) {
        Emplacement emplacement = convertToModel(emplacementDTO);
        if (emplacementsDAO.getEmplacementByCampCodeAndEmpNo(emplacement.getCampCode(), emplacement.getEmpNo()) != null) {
            throw new IllegalArgumentException("Emplacement with this camp code and number already exists.");
        }
        emplacement = emplacementsDAO.insertEmplacement(emplacement);
        return convertToDTO(emplacement);
    }

    @Override
    public EmplacementDTO updateEmplacement(EmplacementDTO emplacementDTO) {
        Emplacement emplacement = convertToModel(emplacementDTO);
        if (emplacementsDAO.getEmplacementByCampCodeAndEmpNo(emplacement.getCampCode(), emplacement.getEmpNo()) == null) {
            throw new IllegalArgumentException("No such emplacement exists to update.");
        }
        emplacement = emplacementsDAO.updateEmplacement(emplacement);
        return convertToDTO(emplacement);
    }

    @Override
    public EmplacementDTO deleteEmplacement(String campCode, int empNo) {
        Emplacement emplacement = emplacementsDAO.deleteEmplacement(campCode, empNo);
        return convertToDTO(emplacement);
    }

    private EmplacementDTO convertToDTO(Emplacement emplacement) {
        if (emplacement == null) {
            return null;
        }
        return new EmplacementDTO(emplacement.getCampCode(), emplacement.getEmpNo(), emplacement.getCatCode());
    }

    private Emplacement convertToModel(EmplacementDTO emplacementDTO) {
        if (emplacementDTO == null) {
            return null;
        }
        return new Emplacement(emplacementDTO.getCampCode(), emplacementDTO.getEmpNo(), emplacementDTO.getCatCode());
    }
}
