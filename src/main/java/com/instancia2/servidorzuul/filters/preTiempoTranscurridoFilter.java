package com.instancia2.servidorzuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

// Registramos la clase como un bean de String
@Component
public class preTiempoTranscurridoFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(preTiempoTranscurridoFilter.class);

    // Definimos el tipo de filtro que queremos hacer pre, post o route
    @Override
    public String filterType() {
        return ("pre");
    }

    // Orden de ejecución
    @Override
    public int filterOrder() {
        return 1;
    }

    // Indica si el filtro debe de ejecutarse, se usa para comprobaciones
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // Ejecutamos el filtro donde usamos la lógica antes del enrutado
    @Override
    public Object run() throws ZuulException {

        // Necesitamos obtener la peticion del request
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));

        Long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempoInicio);



        return null;
    }
}
