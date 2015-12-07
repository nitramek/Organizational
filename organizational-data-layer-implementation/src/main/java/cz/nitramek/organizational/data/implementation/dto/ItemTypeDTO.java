package cz.nitramek.organizational.data.implementation.dto;


import cz.nitramek.organizational.domain.interafaces.Identifiable;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ItemType")
@Table(name = "ItemType")
@NamedQueries(
        {
                @NamedQuery(name = "ItemType.selectById", query = "SELECT it FROM ItemType it WHERE it.id = :id ")
        }
)
public class ItemTypeDTO implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;


    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "itemTypeId", referencedColumnName = "id")
    private List<AttributeTypeDTO> attributeTypes;

    public ItemTypeDTO() {
    }

    public ItemTypeDTO(String name) {
        this.name = name;
    }

    public List<AttributeTypeDTO> getAttributeTypes() {
        return attributeTypes;
    }

    public void setAttributeTypes(List<AttributeTypeDTO> attributeTypes) {
        this.attributeTypes = attributeTypes;
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
