<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html>
<f:view>
    <html> 
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <h1><h:outputText value="#{eti['inicio.main.titulo']}"/></h1>
            
            <form>
                <a href="ManPeliculas.jsf?init=true">Mantenimiento de Peliculas</a>
            </form>
        </body>
    </html>
</f:view>
