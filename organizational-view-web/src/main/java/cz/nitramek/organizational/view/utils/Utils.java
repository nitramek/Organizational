package cz.nitramek.organizational.view.utils;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Utils {

    public static Optional<String> getParameter(String name) {
        final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                                                                            .getExternalContext()
                                                                            .getRequest();
        return Optional.ofNullable(request.getParameter(name));
    }
}
