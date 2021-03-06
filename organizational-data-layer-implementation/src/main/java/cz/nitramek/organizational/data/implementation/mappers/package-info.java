@DataImplementation(
        implementations = {
                UserMapperImpl.class,
                RoleMapperImpl.class,
                CategoryMapperImpl.class,
                ItemTypeMapperImpl.class,
                ItemMapperImpl.class,
                AttributeValueTypeMapperImpl.class,
                MessageMapperImpl.class,
                NotificationSettingMapperImpl.class
        }
)
package cz.nitramek.organizational.data.implementation.mappers;

import cz.nitramek.organizational.data.util.DataImplementation;