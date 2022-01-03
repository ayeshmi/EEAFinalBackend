<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="deleteLectureModal" data-backdrop="static" tabindex="-1" aria-labelledby="deleteLectureModal" aria-hidden="true"  role="dialog">

    <div class="modal-dialog modal-dialog-centered" role="document">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><B>Delete User</B></h5>
                
            </div>

            <div class="modal-body">

                <P>Confirm deletion of the selected User.</P>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

                    <form action="${contextPath}/api/auth/deleteUser" method="get" id="myForm">
                        <input hidden id="deleteId" name="userId">
                        <select name="returnItem"
			id="pet-select" required>
			<option value="">--Please choose an option--</option>
			 <c:forEach var="item" items="${pharmacist}">
			
			<option value="Yes">${item.email}</option>
			</c:forEach>
			</select>
                        <button type="submit" class="btn btn-danger" >Confirm</button>
                    </form>
                </div>


            </div>


        </div>

    </div>


</div>