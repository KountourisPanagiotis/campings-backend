package gr.aueb.cf.cfcampingsjax.service.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.IStaffDAO;
import gr.aueb.cf.cfcampingsjax.dto.StaffDTO;
import gr.aueb.cf.cfcampingsjax.model.Staff;
import gr.aueb.cf.cfcampingsjax.service.interfaces.IStaffService;

import java.util.ArrayList;
import java.util.List;

public class StaffServiceImpl implements IStaffService {

    private final IStaffDAO staffDAO;

    // Dependency Injection
    public StaffServiceImpl(IStaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    @Override
    public StaffDTO getStaffByNumber(int staffNo) {
        Staff staff = staffDAO.getStaffByNumber(staffNo);
        return convertToDTO(staff);
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        List<Staff> staffs = staffDAO.getAllStaff();
        List<StaffDTO> staffDTOs = new ArrayList<>();
        for (Staff staff : staffs) {
            staffDTOs.add(convertToDTO(staff));
        }
        return staffDTOs;
    }

    @Override
    public StaffDTO insertStaff(StaffDTO staffDTO) {
        Staff staff = convertToModel(staffDTO);
        if (staffDAO.getStaffByNumber(staff.getStaffNo()) != null) {
            throw new IllegalArgumentException("Staff with this number already exists.");
        }
        staff = staffDAO.insertStaff(staff);
        return convertToDTO(staff);
    }

    @Override
    public StaffDTO updateStaff(StaffDTO staffDTO) {
        Staff staff = convertToModel(staffDTO);
        if (staffDAO.getStaffByNumber(staff.getStaffNo()) == null) {
            throw new IllegalArgumentException("No such staff exists to update.");
        }
        staff = staffDAO.updateStaff(staff);
        return convertToDTO(staff);
    }

    @Override
    public StaffDTO deleteStaff(int staffNo) {
        Staff staff = staffDAO.deleteStaff(staffNo);
        return convertToDTO(staff);
    }

    private StaffDTO convertToDTO(Staff staff) {
        if (staff == null) {
            return null;
        }
        return new StaffDTO(staff.getStaffNo(), staff.getStaffName(), staff.getStaffSurname());
    }

    private Staff convertToModel(StaffDTO staffDTO) {
        if (staffDTO == null) {
            return null;
        }
        return new Staff(staffDTO.getStaffNo(), staffDTO.getStaffName(), staffDTO.getStaffSurname());
    }
}
