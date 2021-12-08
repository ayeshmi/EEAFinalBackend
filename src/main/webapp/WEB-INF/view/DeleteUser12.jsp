
<div class="modal fade" id="deleteLectureModal" data-backdrop="static" tabindex="-1" aria-labelledby="deleteLectureModal" aria-hidden="true"  role="dialog">

    <div class="modal-dialog modal-dialog-centered" role="document">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><B>Delete Lecture</B></h5>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">

                <P>Confirm deletion of the selected Lecture.</P>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

                    <form action="${contextPath}/api/auth/deleteUser" method="get" id="myForm">
                        <input hidden id="deleteId" name="userId">
                        <button type="submit" class="btn btn-danger" >Confirm</button>
                    </form>
                </div>


            </div>


        </div>

    </div>


</div>
