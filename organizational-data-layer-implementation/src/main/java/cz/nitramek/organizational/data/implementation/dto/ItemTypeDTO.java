package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

import java.util.List;

public class ItemTypeDTO implements Identifiable {
    private long id;
    private String name;

    private List<AttributeTypeDTO> attributeTypeDTOs;


    public ItemTypeDTO(String name) {
        this.name = name;
    }

    public List<AttributeTypeDTO> getAttributeTypeDTOs() {
        return attributeTypeDTOs;
    }

    public void setAttributeTypeDTOs(List<AttributeTypeDTO> attributeTypeDTOs) {
        this.attributeTypeDTOs = attributeTypeDTOs;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
