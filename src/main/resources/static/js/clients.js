function deleteClient(cin) {
    Swal.fire({
        title: 'Are you sure?',
        text: 'Once deleted, you will not be able to recover this client!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, keep it'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: '/clients/delete-ajax',
                type: 'POST',
                data: { cin: cin },
                success: function () {
                    Swal.fire({
                        title: 'Deleted!',
                        text: 'Your client has been deleted.',
                        icon: 'success',
                        timer: 1500,
                        onClose: () => {
                            location.reload();
                        }
                    });
                },
                error: function () {
                    Swal.fire({
                        title: 'Error!',
                        text: 'Failed to delete the client.',
                        icon: 'error'
                    });
                }
            });
        } else {
            Swal.fire(
                'Cancelled',
                'Your client is safe :)',
                'error'
            );
        }
    });
}
