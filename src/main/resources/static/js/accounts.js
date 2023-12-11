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

$(document).ready(function () {
    $.ajax({
        url: "/clients/all",
        type: "GET",
        success: function (clients) {
            console.log(clients)
            $('#cin').autocomplete({
                source: function (request, response) {
                    const filteredData = $.grep(clients, function (item) {
                        return item.cin.toString().startsWith(request.term)
                    });

                    const labels = $.map(filteredData, function (item) {
                        return {
                            ...item,
                            value: item.cin
                        }
                    });

                    response(labels);
                },
                select: function (event, ui) {
                    document.getElementById('cin').value = ui.item.cin;
                    document.getElementById('firstName').value = ui.item.firstName;
                    document.getElementById('lastName').value = ui.item.lastName;
                }
            });

            $('#firstName').autocomplete({
                source: function (request, response) {
                    const filteredData = $.grep(clients, function (item) {
                        return item.firstName.toLowerCase().startsWith(request.term.toLowerCase())
                    });

                    const labels = $.map(filteredData, function (item) {
                        return {
                            ...item,
                            value: item.firstName
                        }
                    });

                    response(labels);
                },
                select: function (event, ui) {
                    document.getElementById('cin').value = ui.item.cin;
                    document.getElementById('firstName').value = ui.item.firstName;
                    document.getElementById('lastName').value = ui.item.lastName;
                }
            });

            $('#lastName').autocomplete({
                source: function (request, response) {
                    const filteredData = $.grep(clients, function (item) {
                        return item.lastName.toLowerCase().startsWith(request.term.toLowerCase())
                    });

                    const labels = $.map(filteredData, function (item) {
                        return {
                            ...item,
                            value: item.lastName
                        }
                    });

                    response(labels);
                },
                select: function (event, ui) {
                    document.getElementById('cin').value = ui.item.cin;
                    document.getElementById('firstName').value = ui.item.firstName;
                    document.getElementById('lastName').value = ui.item.lastName;
                }
            });
        }
    });
});