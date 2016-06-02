<%-- 
    Document   : ManPeliculas
    Created on : Apr 13, 2016, 7:30:36 PM
    Author     : eric.martinez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
            <h1><h:outputText value="Mantenimiento Libros"/></h1>
            <h:form id="form1">
                <table>
                    <tr>
                        <td>Libros</td>
                        <td>
                            <h:selectOneMenu id="cbLibros"
                                             value="#{prestamoBean.elPrestamo.id.elLibro}"
                                             required="true" 
                                             requiredMessage="campo requerido">
                                <f:selectItems value="#{libroBean.llenaLibros()}"/>
                            </h:selectOneMenu>
                            <h:message for="cbLibros"/>
                        </td>
                    </tr>
                </table>
                <h:commandButton
                    value="#{eti['boton.GenerarReporte']}" 
                    id="btnGenerar" 
                    action="#{libroBean.generaReporte()}"/>
                <h:messages globalOnly="true"/>
            </h:form>
        </body>
    </html>
</f:view>