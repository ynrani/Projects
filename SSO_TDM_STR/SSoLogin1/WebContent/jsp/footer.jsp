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
                   <button type="button" class="btn btn-danger del" id="url" data-dismiss="modal"  onclick="loadAjax();">Delete</button>
               </div>
          </div>
      </div>
   </div>
<!-- PopUp End -->

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <span class="copyright">&copy; 2016 Capgemini</span>
        </div>
    </div>
 </div> 
 <div id="ajaxloader" class="modal"  style="background-position : center;display: none; height: 100%; width: 100%; background-color: Black;  opacity: 0.6;  ">
    <div class="center">
        <img alt="" src="assets/img/loader.gif" 
        	  style="position: absolute;display: none; left: 50%; top: 50%;margin-left: -34px;margin-top: -34px;display: block;  "/>
    </div>
 </div>