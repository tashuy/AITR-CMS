package CMSAIML.example.CMSAIML.Service;


import CMSAIML.example.CMSAIML.Entity.AdminInformation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminInformationService {

    private final List<AdminInformation> adminList = new ArrayList<>();

    public List<AdminInformation> getAllAdmins() {
        return adminList;
    }

    public AdminInformation saveAdmin(AdminInformation admin) {
        adminList.add(admin);
        return admin;
    }

    public void saveAllAdmins(List<AdminInformation> admins) {
        adminList.addAll(admins);
    }

    public void clearAll() {
        adminList.clear();
    }
}