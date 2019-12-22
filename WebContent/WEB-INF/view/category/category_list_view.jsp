<jsp:include page="../include/header.jsp"></jsp:include>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container">


<h3>${page_heading} </h3>
<div ><a href="category-list-download"><button type="button" class="btn btn-outline-primary">Download Category</button></a></div>

<div class="row" >
<div class="col-md-8">
<table class="table table-bordered">
    <thead>
      <tr class="table-primary">
        <th>Id</th>
        <th>Category</th>
        <th>Preference</th>
             
      </tr>
    </thead>
    <tbody>
    
    <c:if test="${fn:length(category_list) > 0}">
	    <c:forEach items="${category_list }"  var="category" >
		    <tr>
		        <td><c:out  value="${category.id}" > </c:out></td>
		        <td><c:out  value="${category.category }" > </c:out></td>
		        <td><c:out  value="${category.preference}" > </c:out></td>
		                           
		    </tr>
	    
	    </c:forEach>
    </c:if>
    
    <c:if test="${fn:length(category_list) <= 0}">
    <tr><td colspan="12">There is no customer exist</td></tr>
    </c:if>
      
      
    </tbody>
  </table>

</div>
</div>
</div>
<jsp:include page="../include/footer.jsp"></jsp:include>
