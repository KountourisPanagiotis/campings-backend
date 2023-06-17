package gr.aueb.cf.cfcampingsjax.service.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ISpotrentalsDAO;
import gr.aueb.cf.cfcampingsjax.dto.SpotrentalDTO;
import gr.aueb.cf.cfcampingsjax.model.Spotrental;
import gr.aueb.cf.cfcampingsjax.service.interfaces.ISpotrentalService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpotrentalServiceImpl implements ISpotrentalService {

    private final ISpotrentalsDAO spotrentalsDAO;

    // Dependency Injection
    public SpotrentalServiceImpl(ISpotrentalsDAO spotrentalsDAO) {
        this.spotrentalsDAO = spotrentalsDAO;
    }

    @Override
    public SpotrentalDTO getSpotRentalByKeys(int bookCode, String campCode, int empNo, Date startDt) {
        Spotrental spotrental = spotrentalsDAO.getSpotrentalByPrimaryKeys(bookCode, campCode, empNo, startDt);
        return convertToDTO(spotrental);
    }

    @Override
    public List<SpotrentalDTO> getAllSpotRentals() {
        List<Spotrental> spotrentals = spotrentalsDAO.getAllSpotrentals();
        List<SpotrentalDTO> spotrentalDTOs = new ArrayList<>();
        for (Spotrental spotrental : spotrentals) {
            spotrentalDTOs.add(convertToDTO(spotrental));
        }
        return spotrentalDTOs;
    }

    @Override
    public List<SpotrentalDTO> getAllSpotRentalsWithBookCode(int bookCode) {
        List<Spotrental> spotrentals = spotrentalsDAO.getAllSpotrentalsWithBookCode(bookCode);
        List<SpotrentalDTO> spotrentalDTOs = new ArrayList<>();
        for (Spotrental spotrental : spotrentals) {
            spotrentalDTOs.add(convertToDTO(spotrental));
        }
        return spotrentalDTOs;
    }

    @Override
    public SpotrentalDTO insertSpotRental(SpotrentalDTO spotrentalDTO) {
        Spotrental spotrental = convertToModel(spotrentalDTO);
        spotrental = spotrentalsDAO.insertSpotrental(spotrental);
        return convertToDTO(spotrental);
    }

    @Override
    public SpotrentalDTO updateSpotRental(SpotrentalDTO spotrentalDTO) {
        Spotrental spotrental = convertToModel(spotrentalDTO);
        spotrental = spotrentalsDAO.updateSpotrental(spotrental);
        return convertToDTO(spotrental);
    }

    @Override
    public SpotrentalDTO deleteSpotRental(int bookCode, String campCode, int empNo, Date startDt) {
        Spotrental spotrental = spotrentalsDAO.deleteSpotrental(bookCode, campCode, empNo, startDt);
        return convertToDTO(spotrental);
    }

    private SpotrentalDTO convertToDTO(Spotrental spotrental) {
        if (spotrental == null) {
            return null;
        }

        return new SpotrentalDTO(spotrental.getBookCode(), spotrental.getCampCode(), spotrental.getEmpNo(), spotrental.getStartDt(), spotrental.getEndDt(), spotrental.getNoPers());
    }

    private Spotrental convertToModel(SpotrentalDTO spotrentalDTO) {
        if (spotrentalDTO == null) {
            return null;
        }

        return new Spotrental(spotrentalDTO.getBookCode(), spotrentalDTO.getCampCode(), spotrentalDTO.getEmpNo(), spotrentalDTO.getStartDt(), spotrentalDTO.getEndDt(), spotrentalDTO.getNoPers());
    }
}
