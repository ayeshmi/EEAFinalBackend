
<div class="modal fade" id="deleteLectureModal" data-backdrop="static"
	tabindex="-1" aria-labelledby="deleteLectureModal" aria-hidden="true"
	role="dialog">

	<div class="modal-dialog modal-dialog-centered" role="document">

		<div class="modal-content">

			<div class="modal-header" style="background: rgb(87, 217, 240);
	background: linear-gradient(135deg, rgb(161, 181, 236) 0%,
		rgb(39, 179, 197) 100%);">
				<h5 class="modal-title" id="exampleModalLabel">
					<B>Cancel Order</B>
				</h5>

			</div>

			<div class="modal-body">

				<P>Tell us, Why are you going to cancel this order?</P>

				<div class="modal-footer">


					<form action="${contextPath}/api/auth/OrderCancelation" method="get"
						id="myForm">
						<input hidden id="deleteId" name="deleteId">
						<input hidden id="userId" name="userId">
						<input
						
							type="text" id="form1Example13"
							 name="reason" style="width:400px; height:30px;"
							placeholder="Enter your reason" required />
							<br/>
							<br/>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-danger">Submit</button>
					</form>
				</div>


			</div>


		</div>

	</div>


</div>

