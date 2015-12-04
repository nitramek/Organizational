import cz.nitramek.organizational.data.mapper.UserMapper;
import cz.nitramek.organizational.data.util.MapperCreationException;
import cz.nitramek.organizational.data.util.MapperFactory;

public class Main {

    public static void main(String[] args) {
        try {
            UserMapper mapper = MapperFactory.createMapper(UserMapper.class);
            Object o = mapper.select();

        } catch (MapperCreationException e) {
            e.printStackTrace();
        }
    }

}
