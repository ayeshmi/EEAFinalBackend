<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="deleteLectureModal" data-backdrop="static"
	tabindex="-1" aria-labelledby="deleteLectureModal" aria-hidden="true"
	role="dialog">

	<div class="modal-dialog modal-dialog-centered" role="document">

		<div class="modal-content">

			<div class="modal-header" style="background: rgb(87, 217, 240);
	background: linear-gradient(135deg, rgb(161, 181, 236) 0%,
		rgb(39, 179, 197) 100%);">
				<h5 class="modal-title" id="exampleModalLabel">
					<B>Select Pharmacist</B>
				</h5>

			</div>

			<div class="modal-body">

				<P>Select pharmacist for conducting order. </P>

				<div class="modal-footer">


					<form action="${contextPath}/api/auth/assignOrderToPharmacist"
						method="post" id="myForm">
						<input hidden id="deleteId" name="deleteId">
						 <select
							name="name" id="pet-select" style="width:400px; height:30px;" required>
							<option value="">--Please choose an option--</option>
							<c:forEach var="item" items="${pharmacist}">

								<option value="${item.username}">${item.username}</option>
							</c:forEach>
						</select>
						<br></br>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-danger">Confirm</button>
					</form>
				</div>


			</div>


		</div>

	</div>


</div>