<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
 
      <ul class="x-navigation x-navigation-horizontal">
		<li class="xn-logo"> <a href="./index" ><img src="assets/img/symantec-logo-top.png" class="logo" id="logoNavigation"></a>
		    <a href="#" class="x-navigation-control"></a>
		</li>
		<li class="xn-openable"><a href="#"><span class="fa fa-user"></span> <span class="xn-text">Services</span></a>
			<ul>
				<li><a href="http://localhost:8081/SSoLogin2/index2" target="_new">Issue Troubleshoot </a></li>
				<li><a href="#">Use Full Queries</a></li>
			</ul>
		</li>
			<li><a href="#"><span class="fa fa-print"></span><span class="xn-text">Reports</span></a></li>
		<li class="xn-icon-button pull-right">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
 			<a href="#" class="mb-control" data-box="#mb-signout"><i class="fa fa-sign-out"></i></a>
 		</c:if>
          </li>
		</ul>
		
	<!-- Popup start -->
  <div id="myModal" class="modal fade">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                   <h4 class="modal-title">Delete Confirmation</h4>
               </div>
               <div class="modal-body">
                   <p>Are you sure, Do you want to delete ? </p>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-success" data-dismiss="modal">Cancel</button>
                   <button type="button" class="btn btn-danger del" id="url" data-dismiss="modal" >Delete</button>
               </div>
          </div>
      </div>
   </div>
<!-- PopUp End -->	
 <!-- MESSAGE BOX-->
        <div class="message-box animated fadeIn" data-sound="alert" id="mb-signout">
            <div class="mb-container">
                <div class="mb-middle">
                    <div class="mb-title"><span class="fa fa-sign-out"></span> Log <strong>Out</strong> ?</div>
                    <div class="mb-content">
                        <p>Are you sure you want to log out?</p>                    
                        <p>Press No if you want to continue work. Press Yes to logout current user.</p>
                    </div>
                    <div class="mb-footer">
                        <div class="pull-right">
                            <a href="javascript:document.getElementById('logout').submit(); loadAjax();" class="btn btn-success btn-lg">Yes</a>
                            <button class="btn btn-default btn-lg mb-control-close">No</button>
                            
                            <c:url value="/logout?logout=true" var="logoutUrl" />
						    <form id="logout" action="${logoutUrl}" method="post" >
					   	      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					 	    </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END MESSAGE BOX-->  
    