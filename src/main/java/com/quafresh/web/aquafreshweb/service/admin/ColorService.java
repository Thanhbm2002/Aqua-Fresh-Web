package com.quafresh.web.aquafreshweb.service.admin;

import com.quafresh.web.aquafreshweb.dto.admin.ColorAdminDtO;

import java.util.List;

public interface ColorService {
     ColorAdminDtO getColor(Integer id);
     List<ColorAdminDtO> getColorList();
     List<ColorAdminDtO> searchColor(String keyword);
     ColorAdminDtO addColor(ColorAdminDtO category);
     ColorAdminDtO updateColor(Integer id,ColorAdminDtO category);
     String deleteColor(Integer id);
}
