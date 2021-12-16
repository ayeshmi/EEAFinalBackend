<div class="modal fade" id="changePasswordModal" data-backdrop="static" tabindex="-1" aria-labelledby="changePasswordModal" aria-hidden="true"  role="dialog">

    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><B>Reset Password</B></h5>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">

                <div class="card-body">
                    <div class="form-body">

                        <form class="row g-3" method="post" action="${contextPath}/user/resetPassword">
                            <div class="row" style="margin-bottom: 15px;">
                            <div class="col-md-6">
                                <label for="oldPassword" class="form-label">Old Password</label>
                                <input type="password" class="form-control" id="oldPassword" name="oldPassword"  required>
                            </div>


                            <div class="col-md-6">
                                <label for="newPassword" class="form-label">New Password</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                            </div>

                            </div>



                    </div>


                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" value="submit">Reset Password</button>
            </div>
            </form>

        </div>

    </div>


</div>