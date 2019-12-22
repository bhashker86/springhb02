<jsp:include page="../include/header.jsp"></jsp:include>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<div class="container">

<div  class="row" >

<div class="col-md-3">
<jsp:include page="../include/side_menu.jsp"></jsp:include>
</div>


<div class="col-md-8" >

	<div  class="row">
	   
		<div class="col-md-6"><h4 style="color:#42a1f4" align="left"> ${page_heading} </h4></div>
		<div class="col-md-6"><div class="float-right"><a href="cust-upload-sample">Download Sample</a></div></div>
	
	</div>
<hr />

	<div class="row">
	
			 <c:if test="${not empty msg_sucess }" >
				<div class="alert alert-success">
			        <strong>Success!</strong> ${msg_sucess}.
			    </div>
		    </c:if>
		    
		    <c:if test="${not empty msg_err}" >
			    <div class="alert alert-danger">
			        <strong>Danger!</strong> ${msg_err}.
			    </div>
            </c:if>
            
	
	    <div class="col-md-12">
			<form method="POST" action="bulk-customer-create-sbmt"   enctype=multipart/form-data>
			
			
				<div class="form-group">
				    <label for="customer_file"><strong>Select Customer  File:</strong></label>
				    <input type="file" class="form-control-file" name="customer_file" />
				</div>
				
				<div class="form-group">
				    <input type="submit" class="btn btn-primary"  name="upload" value="Upload" />
				</div>
			
			<form>
	   </div>
	
	</div>


</div>





</div>
</div>
<jsp:include page="../include/footer.jsp"></jsp:include>