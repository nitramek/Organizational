package cz.nitramek.organizational.domain.service;

import cz.nitramek.organizational.data.mapper.AttributeValueTypeMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.domain.classes.AttributeValueType;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local
public class AttributeValueTypeService extends AbstractService<AttributeValueType, AttributeValueTypeMapper>{
    @PostConstruct
    public void init(){
        try {
            this.init(AttributeValueType.class, AttributeValueTypeMapper.class);
        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

    public List<AttributeValueType> getAll(){
        return this.mapper.select();
    }
}
