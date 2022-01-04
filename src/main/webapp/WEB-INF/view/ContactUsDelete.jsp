
<div class="modal fade" id="deleteLectureModal" data-backdrop="static" tabindex="-1" aria-labelledby="deleteLectureModal" aria-hidden="true"  role="dialog">

    <div class="modal-dialog modal-dialog-centered" role="document">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><B>Delete ContactUs Request</B></h5>
                
            </div>

            <div class="modal-body">

                <P>Confirm deletion of the selected ContactUs Request.</P>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

                    <form action="${contextPath}/api/auth/deleteContactUs" method="get" id="myForm">
                        <input hidden id="deleteId" name="itemId">
                        <button type="submit" class="btn btn-danger" >Confirm</button>
                    </form>
                </div>


            </div>


        </div>

    </div>


</div>