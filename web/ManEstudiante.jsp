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
            <h1><h:outputText value="Mantenimiento de Estudiante"/></h1>
            <h:form id="form1">
                <table>
                    <tr>
                        <td>Carnet</td>
                        <td><h:inputText
                                disabled="#{estudianteBean.modificando==true}"
                                id="txtCarnet" 
                                required="true" requiredMessage="Requerido"
                                value="#{estudianteBean.elEstudiante.carnet}"/> 
                                <h:message for="txtCarnet" />
                            </td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><h:inputText binding="#{estudianteBean.nombre}"
                                    id="txtNombre" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{estudianteBean.elEstudiante.nombre}" />
                                <h:message for="txtNombre" /></td>
                        </tr>
                        <tr>
                            <td>Telefono</td>
                            <td><h:inputText 
                                    id="txtTelefono" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{estudianteBean.elEstudiante.telefono}" />
                                <h:message for="txtTelefono" /></td>
                        </tr>
                        <tr>
                            <td>Direccion</td>
                            <td><h:inputText 
                                    id="txtDireccion" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{estudianteBean.elEstudiante.direccion}" />
                                <h:message for="txtDireccion" /></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><h:inputText 
                                    id="txtEmail" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{estudianteBean.elEstudiante.email}" />
                                <h:message for="txtEmail" /></td>
                        </tr>
                        <tr>
                            <td>Escuela</td>
                            <td><h:selectOneMenu 
                                    required="true" 
                                    requiredMessage="Requerido" 
                                    value="#{estudianteBean.elEstudiante.escuela}" 
                                    id="cbEscuela">
                                    <f:selectItems value="#{estudianteBean.llenaEscuela()}"/>
                                </h:selectOneMenu>
                                <h:message for="cbEscuela" />
                            </td>

                </table>
                    <h:commandButton rendered="#{!estudianteBean.modificando}" 
                                     value="#{eti['boton.Agregar']}" 
                                     id="btnAgregar" action="#{estudianteBean.agregar()}"/>
                    
                    <h:commandButton rendered="#{estudianteBean.modificando}" 
                                     value="#{eti['boton.Modificar']}" 
                                     id="btnModificar" action="#{estudianteBean.modificar()}"/>
                    
                    <h:commandButton rendered="#{estudianteBean.modificando}"
                                     immediate="true"
                                     value="#{eti['boton.Cancelar']}" 
                                     id="btnCancelar" action="#{estudianteBean.cancelar()}"/>
                    
                    <h:commandButton value="#{eti['boton.Buscar']}" 
                                     id="btnBuscar" 
                                     immediate="true"
                                     action="#{estudianteBean.buscar()}"/>
                    <h:commandButton value="#{eti['boton.Ver']}"  
                                     id="btnVer" 
                                     immediate="true"
                                     action="#{estudianteBean.listar()}"/>
                    
                    <h:messages globalOnly="true"/>

                    <h:dataTable id="tablaEstudiantes" 
                                 value="#{estudianteBean.listaEstudiante}" border="1"
                                 binding="#{estudianteBean.tableEstudiante}"
                                 var="estudiante">

                        <h:column id="col01">
                            <f:facet name="header">
                                <h:outputText value="Carnet"/>
                            </f:facet>
                            <h:outputText value="#{estudiante.carnet}"/>
                        </h:column>
                        <h:column id="col02">
                            <f:facet name="header">
                                <h:outputText value="Nombre"/>
                            </f:facet>
                            <h:outputText value="#{estudiante.nombre}"/>
                        </h:column>
                        <h:column id="col03">
                            <f:facet name="header">
                                <h:outputText value="Telefono"/>
                            </f:facet>
                            <h:outputText value="#{estudiante.telefono}"/>
                        </h:column>
                        <h:column id="col04">
                            <f:facet name="header">
                                <h:outputText value="Direccion"/>
                            </f:facet>
                            <h:outputText value="#{estudiante.direccion}"/>
                        </h:column>
                        <h:column id="col05">
                            <f:facet name="header">
                                <h:outputText value="Email"/>
                            </f:facet>
                            <h:outputText value="#{estudiante.email}"/>
                        </h:column>
                        <h:column id="col06">
                            <f:facet name="header">
                                <h:outputText value="Escuela"/>
                            </f:facet>
                            <h:outputText value="#{estudiante.escuela}"/>
                        </h:column>
                        <h:column id="colSeleccionar">
                            <f:facet name="header">
                            <h:outputText value="Seleccionar"/>
                            </f:facet>
                            <h:commandLink immediate="true" action="#{estudianteBean.seleccionar()}"  >
                                <h:graphicImage url="img/modificar.png"/>
                            </h:commandLink>
                        </h:column>
                        <h:column id="colEliminar">
                            <f:facet name="header">
                            <h:outputText value="Eliminar"/>
                            </f:facet>
                            <h:commandLink immediate="true" 
                                           onclick="return confirm ('esta seguro que quiere eliminar el cliente')"
                                           action="#{estudianteBean.eliminar()}">
                                <h:graphicImage url="img/eliminar.jpg"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
            </h:form>
        </body>
    </html>
</f:view>
