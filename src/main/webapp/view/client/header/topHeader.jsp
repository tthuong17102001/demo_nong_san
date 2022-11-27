<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/27/2022
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>
<!-- start header top  -->
<div class="aa-header-top">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-header-top-area">
                    <!-- start header top left -->
                    <div class="aa-header-top-left">
                        <div class="cellphone hidden-xs">
                            <p><span class="fas fa-home"></span>Website bán hàng nông sản</p>
                        </div>
                        <!-- start language -->
                        <div class="aa-language">
                            <div class="dropdown">
                                <a class="btn dropdown-toggle" href="#" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <img src="${url}/img/flag/vietnam.png" alt="english flag">Việt Nam
                                </a>

                            </div>
                        </div>
                        <!-- / language -->

                    </div>
                    <!-- / header top left -->
                    <div class="aa-header-top-right">
                        <ul class="aa-head-top-nav-right">

                            <c:if test="${sessionScope.username != null}">
                                <li>
                                    <a><strong>Chào</strong> ${username }</a>
                                </li>
                                <li class="hidden-xs"><a href="${pageContext.request.contextPath}/ServletLogout">Đăng xuất</a></li>
                            </c:if>

                            <c:if test="${sessionScope.username == null}">
                                <li class="hidden-xs"><a href="${pageContext.request.contextPath}/ServletRegistration">Đăng ký</a></li>
                                <li><a href="${pageContext.request.contextPath}/ServletLogin">Đăng nhập</a></li>
                            </c:if>

                            <!--  data-toggle="modal" data-target="#login-modal" -->
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- / header top  -->

