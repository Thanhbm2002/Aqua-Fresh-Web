package com.quafresh.web.aquafreshweb.service.admin;

import com.quafresh.web.aquafreshweb.dto.admin.CompanyAdminDTO;
import com.quafresh.web.aquafreshweb.dto.admin.TechnologyAdminDTO;

import java.util.List;

public interface TechnologyService {
    TechnologyAdminDTO getByID(Integer id);
    List<TechnologyAdminDTO> getAll();
    List<TechnologyAdminDTO> search(String keyword);
    TechnologyAdminDTO create(TechnologyAdminDTO technologyAdminDTO);
    TechnologyAdminDTO update(Integer id,TechnologyAdminDTO technologyAdminDTO);
    String delete(Integer id);
}
