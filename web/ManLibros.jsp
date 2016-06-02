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
                        <td><h:inputText
                                disabled="#{libroBean.modificando==true}"
                                id="txtISBN" 
                                required="true" requiredMessage="Requerido"
                                value="#{libroBean.elLibro.isbn}"/> 
                                <h:message for="txtISBN" />
                            </td>
                        </tr>
                        <tr>
                            <td>Nombre</td>
                            <td><h:inputText binding="#{libroBean.nombre}"
                                    id="txtNombre" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{libroBean.elLibro.nombre}" />
                                <h:message for="txtNombre" /></td>
                        </tr>
                        <tr>
                            <td>Autor</td>
                            <td><h:inputText 
                                    id="txtAutor" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{libroBean.elLibro.autor}" />
                                <h:message for="txtAutor" /></td>
                        </tr>
                        <tr>
                            <td>Año Publicacion</td>
                            <td><h:inputText 
                                    id="txtPublicacion" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{libroBean.elLibro.anoPublicacion}" />
                                <h:message for="txtPublicacion" /></td>
                        </tr>
                        <tr>
                            <td>Editorial</td>
                            <td><h:inputText 
                                    id="txtEditorial" 
                                    required="true" requiredMessage="Requerido"
                                    value="#{libroBean.elLibro.editorial}" />
                                <h:message for="txtEditorial" /></td>
                        </tr>
                        <tr>
                            <td>Genero</td>
                            <td><h:selectOneMenu 
                                    required="true" 
                                    requiredMessage="Requerido" 
                                    value="#{libroBean.elLibro.genero}" 
                                    id="cbGeneros">
                                    <f:selectItems value="#{libroBean.llenaGenero()}"/>
                                </h:selectOneMenu>
                                <h:message for="cbGeneros" />
                            </td>
                </table>
                    <h:commandButton rendered="#{!libroBean.modificando}" 
                                     value="#{eti['boton.Agregar']}" 
                                     id="btnAgregar" action="#{libroBean.agregar()}"/>
                    
                    <h:commandButton rendered="#{libroBean.modificando}" 
                                     value="#{eti['boton.Modificar']}" 
                                     id="btnModificar" action="#{libroBean.modificar()}"/>
                    
                    <h:commandButton rendered="#{libroBean.modificando}"
                                     immediate="true"
                                     value="#{eti['boton.Cancelar']}" 
                                     id="btnCancelar" action="#{libroBean.cancelar()}"/>
                    
                    <h:commandButton value="#{eti['boton.Buscar']}" 
                                     id="btnBuscar" 
                                     immediate="true"
                                     action="#{libroBean.buscar()}"/>
                    <h:commandButton value="#{eti['boton.Ver']}"  
                                     id="btnVer" 
                                     immediate="true"
                                     action="#{libroBean.listar()}"/>
                    
                    <h:messages globalOnly="true"/>

                    <h:dataTable id="tablaClientes" 
                                 value="#{libroBean.listaLibro}" border="1"
                                 binding="#{libroBean.tableLibro}"
                                 var="libros">

                        <h:column id="col01">
                            <f:facet name="header">
                                <h:outputText value="ISBN"/>
                            </f:facet>
                            <h:outputText value="#{libros.isbn}"/>
                        </h:column>
                        <h:column id="col02">
                            <f:facet name="header">
                                <h:outputText value="Nombre"/>
                            </f:facet>
                            <h:outputText value="#{libros.nombre}"/>
                        </h:column>
                        <h:column id="col03">
                            <f:facet name="header">
                                <h:outputText value="Autor"/>
                            </f:facet>
                            <h:outputText value="#{libros.autor}"/>
                        </h:column>
                        <h:column id="col04">
                            <f:facet name="header">
                                <h:outputText value="Año Publicacion"/>
                            </f:facet>
                            <h:outputText value="#{libros.anoPublicacion}"/>
                        </h:column>
                        <h:column id="col05">
                            <f:facet name="header">
                                <h:outputText value="Editorial"/>
                            </f:facet>
                            <h:outputText value="#{libros.editorial}"/>
                        </h:column>
                        <h:column id="col06">
                            <f:facet name="header">
                                <h:outputText value="Genero"/>
                            </f:facet>
                            <h:outputText value="#{libros.genero}"/>
                        </h:column>
                        <h:column id="colSeleccionar">
                            <f:facet name="header">
                            <h:outputText value="Seleccionar"/>
                            </f:facet>
                            <h:commandLink immediate="true" action="#{libroBean.seleccionar()}"  >
                                <h:graphicImage url="img/modificar.png"/>
                            </h:commandLink>
                        </h:column>
                        <h:column id="colEliminar">
                            <f:facet name="header">
                            <h:outputText value="Eliminar"/>
                            </f:facet>
                            <h:commandLink immediate="true" 
                                           onclick="return confirm ('esta seguro que quiere eliminar el cliente')"
                                           action="#{libroBean.eliminar()}">
                                <h:graphicImage url="img/eliminar.jpg"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
            </h:form>
        </body>
    </html>
</f:view>
