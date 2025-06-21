package CMSAIML.example.CMSAIML.Controller;

import java.util.List;

public class AdminInformationService {
    public <AdminInformation> List<AdminInformation> getAllAdmins() {
        return List.of();
    }

    public <AdminInformation> AdminInformation saveAdmin(AdminInformation admin) {
        return admin;
    }

    public <AdminInformation> void saveAllAdmins(List<AdminInformation> admins) {
    }

    public void clearAll() {
    }
}