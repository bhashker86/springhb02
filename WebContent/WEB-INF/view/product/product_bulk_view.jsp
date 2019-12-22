<jsp:include page="../include/header.jsp"></jsp:include>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<div class="container">

<div  class="row" >

<div class="col-md-3">
<jsp:include page="../include/side_menu.jsp"></jsp:include>
</div>


<div class="col-md-8">
<h3 style="color:#42a1f4" align="left"> ${page_heading} </h3>
<hr />
<p><strong>${msg}</strong></p>
<div>
<form method="POST" action="product-upload-sbmt"   enctype=multipart/form-data>


<div class="form-group">
    <label path="product_sheet"><strong>Select Product List File:</strong></label>
    <input type="file" cssClass="form-control-file" name="product_sheet" />
</div>

<div class="form-group">
    <input type="submit" class="btn btn-primary"  name="upload" value="Upload" />
</div>

<form>


</div>


</div>





</div>
</div>
<jsp:include page="../include/footer.jsp"></jsp:include>