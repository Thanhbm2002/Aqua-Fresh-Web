package com.quafresh.web.aquafreshweb.service.admin;

import com.quafresh.web.aquafreshweb.dto.admin.ColorAdminDtO;
import com.quafresh.web.aquafreshweb.dto.admin.CompanyAdminDTO;

import java.util.List;

public interface CompanyService {
     CompanyAdminDTO getByID(Integer id);
     List<CompanyAdminDTO> getAll();
     List<CompanyAdminDTO> search(String keyword);
     CompanyAdminDTO create(CompanyAdminDTO companyAdminDTO);
     CompanyAdminDTO update(Integer id,CompanyAdminDTO companyAdminDTO);
     String delete(Integer id);
}
