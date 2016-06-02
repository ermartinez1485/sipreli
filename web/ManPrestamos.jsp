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
                        <td>ISBN</td>
                            <td>
                                    <h:selectOneMenu id="cbLibros"
                                                     value="#{prestamoBean.elPrestamo.id.elLibro}"
                                                     required="true" requiredMessage="campo requerido">
                                        <f:selectItems value="#{libroBean.llenaLibros()}"/>
                                    </h:selectOneMenu>
                                    <h:message for="cbLibros"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Carnet</td>
                            <td>
                                    <h:selectOneMenu id="cbEstudiantes"
                                                     value="#{prestamoBean.elPrestamo.id.elEstudiante}"
                                                     required="true" requiredMessage="campo requerido">
                                        <f:selectItems value="#{estudianteBean.llenaEstudiantes()}"/>
                                    </h:selectOneMenu>
                                    <h:message for="cbEstudiantes"/>
                            </td>
                        </tr>
                </table>
                                    <h:commandButton
                                     value="#{eti['boton.Agregar']}" 
                                     id="btnAgregar" action="#{prestamoBean.agregar()}"/>
                    
                    <h:messages globalOnly="true"/>

                    <h:dataTable id="tablaPrestamos" 
                                 value="#{prestamoBean.listaPrestamo}" border="1"
                                 binding="#{prestamoBean.tablePrestamo}"
                                 var="prestamos">

                        <h:column id="col01">
                            <f:facet name="header">
                                <h:outputText value="ISBN"/>
                            </f:facet>
                            <h:outputText value="#{prestamos.id.elLibro.isbn}"/>
                        </h:column>
                        <h:column id="col02">
                            <f:facet name="header">
                                <h:outputText value="Carnet"/>
                            </f:facet>
                            <h:outputText value="#{prestamos.id.elEstudiante.carnet}"/>
                        </h:column>
                        <h:column id="col03">
                            <f:facet name="header">
                                <h:outputText value="Fecha Prestamo"/>
                            </f:facet>
                            <h:outputText value="#{prestamos.fechaPrestamo}"/>
                        </h:column>
                        <h:column id="col04">
                            <f:facet name="header">
                                <h:outputText value="Fecha Devolucion"/>
                            </f:facet>
                            <h:outputText value="#{prestamos.fechaDevolucion}"/>
                        </h:column>
                        
                        <h:column id="colEliminar">
                            <f:facet name="header">
                            <h:outputText value="Eliminar"/>
                            </f:facet>
                            <h:commandLink immediate="true" 
                                           onclick="return confirm ('esta seguro que quiere eliminar el prestamo')"
                                           action="#{prestamoBean.eliminar()}">
                                <h:graphicImage url="img/eliminar.jpg"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
            </h:form>
        </body>
    </html>








</f:view>
