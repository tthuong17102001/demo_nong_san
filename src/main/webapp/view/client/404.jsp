<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/27/2022
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Start header section -->
<jsp:include page = "./header/mainHeader.jsp" flush = "true" />
<!-- / header section -->

<!--  content -->
<!-- 404 error section -->
<section id="aa-error">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-error-area">
                    <h2>404</h2>
                    <span>Xin lỗi! Không tìm thấy trang</span>

                    <a href="${pageContext.request.contextPath}/"> Trở về trang chủ</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / 404 error section -->
<!--  end content-->

<!--  footer-->
<jsp:include page = "./footer/footer.jsp" flush = "true" />
<!-- end footer-->


