<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Login</title>
        </head>
        <body>
            <f:loadBundle basename="cr.ac.una.icai.sipreli.etiquetas.recursos" var="eti"/>
            <h1>
                <h:outputText value="#{eti['inicio.titulo']}"/>
            </h1>
                <h:form id="form1">
                    <table>
                        <tr>
                            <td><h:outputText value="#{eti['tabla.user']}"/></td>
                            <td><h:inputText 
                                id="txtUser" 
                                required="true" requiredMessage="el usuario es requerido"
                                value="#{usuarioBean.elUser.usuario}" /></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="#{eti['tabla.password']}"/></td>
                            <td><h:inputText 
                                id="txtPass" 
                                required="true" requiredMessage="la clave es requerida"
                                value="#{usuarioBean.elUser.contrasena}" /></td>
                        </tr>
                    </table>
                    <h:commandButton value="#{eti['boton.Aceptar']}" id="btnAceptar" action="#{usuarioBean.aceptar()}"/>
                    <h:messages globalOnly="true"/>
                </h:form>
        </body>
    </html>
</f:view>
