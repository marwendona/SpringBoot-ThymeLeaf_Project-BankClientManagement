function deleteAccount(rib) {
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this account!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "/accounts/delete-ajax",
                    type: "POST",
                    data: {'rib': rib},
                    success: function () {
                        $("#" + rib).remove();
                        swal("Poof! Your account has been deleted!", {
                            icon: "success",
                        });
                    }
                });
            } else {
                swal("Your account is safe!");
            }
        });
}
