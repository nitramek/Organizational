# Organizational
This project is supposed to show three-layer system. 

Modules:
* data-layer - mappers defined for domain classes
* data-layer-implementation - data store implementing data-layer abstract mappers
* domain - this probably won't do much, but it is supposed to represent domain layer
* view-web - combines all layers together and defines web views via JSF framework

Data implementation setup:

1.  cz.nitramek.organizational.data.util.DataImplementation package mark in package-info.java to easily find mappers implementation
  ```
  @DataImplementation(implementations = {UserMapperImpl.class})
  package cz.nitramek.organizational.data.implementation.mappers;
  
  import cz.nitramek.organizational.data.util.DataImplementation;
  ```
2.  Concrete mapper implementations via @MapperImplementation

```
@DataImplementation
...
@MapperImplementation(mapper = UserMapper.class)
public class UserMapperImpl implements UserMapper{
....
}
```

3. mapper.properties with  
```
mapper.package = cz.nitramek.organizational.data.implementation.mappers
```


Everything is subject to be changed.
