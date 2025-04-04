package com.quafresh.web.aquafreshweb.service.impladmin;

import com.quafresh.web.aquafreshweb.dto.admin.ColorAdminDtO;
import com.quafresh.web.aquafreshweb.entity.Color;
import com.quafresh.web.aquafreshweb.repositories.ColorRepository;
import com.quafresh.web.aquafreshweb.service.admin.ColorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public ColorAdminDtO getColor(Integer id) {
        Color color = colorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Color not found"));
        return ColorAdminDtO.builder()
                .id(color.getId())
                .name(color.getName())
                .status(color.getStatus())
                .build();
    }

    @Override
    public List<ColorAdminDtO> getColorList() {
        List<Color> colorList = colorRepository.findAll();
        return colorList.stream().map(Color->ColorAdminDtO.builder()
                .id(Color.getId())
                .name(Color.getName())
                .status(Color.getStatus()).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ColorAdminDtO> searchColor(String keyword) {
        List<Color> colorList = colorRepository.findByName(keyword);
        return colorList.stream().map(Color->ColorAdminDtO.builder()
                .id(Color.getId())
                .name(Color.getName())
                .status(Color.getStatus())
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public ColorAdminDtO addColor(ColorAdminDtO colorAdminDtO) {
        Color color = new Color();
        color.setName(colorAdminDtO.getName());
        color.setStatus(colorAdminDtO.isStatus());
        color = colorRepository.save(color);
        return ColorAdminDtO.builder().id(color.getId())
                .name(color.getName())
                .status(color.getStatus())
                .build();
    }

    @Override
    public ColorAdminDtO updateColor(Integer id, ColorAdminDtO colorAdminDtO) {
        Color color = colorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Color not found"));
        color.setName(colorAdminDtO.getName());
        color.setStatus(colorAdminDtO.isStatus());
        color = colorRepository.save(color);
        return ColorAdminDtO.builder().id(color.getId())
                .name(color.getName())
                .status(color.getStatus())
                .build();
    }

    @Override
    public String deleteColor(Integer id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " +id));
        colorRepository.delete(color);
        return "Color with ID " + id + " has been deleted successfully.";
    }

}
