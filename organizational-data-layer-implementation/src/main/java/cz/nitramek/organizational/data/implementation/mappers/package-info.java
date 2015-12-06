@DataImplementation(
        implementations = {
                UserMapperImpl.class,
                RoleMapperImpl.class,
                CategoryMapperImpl.class,
                ItemTypeMapperImpl.class,
                AttributeValueTypeMapperImpl.class,
                MessageMapperImpl.class
        }
)
package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.util.DataImplementation;