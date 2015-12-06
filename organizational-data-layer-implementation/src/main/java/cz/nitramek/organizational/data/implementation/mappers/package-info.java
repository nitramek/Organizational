@DataImplementation(
        implementations = {
                UserMapperImpl.class,
                RoleMapperImpl.class,
                CategoryMapperImpl.class,
                ItemTypeMapperImpl.class,
                AttributeValueTypeMapperImpl.class
        }
)
package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.util.DataImplementation;