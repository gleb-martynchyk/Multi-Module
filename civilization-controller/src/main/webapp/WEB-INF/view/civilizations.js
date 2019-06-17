function deleteCiv() {
    var form = document.createElement('form');
    form.setAttribute('method', 'delete');
    form.setAttribute('action', 'localhost:8084/civilizations/2');
    form.style.display = 'hidden';
    document.body.appendChild(form)
    form.submit();
};

function deleteCivilization(id) {
    console.log('lol')
    return fetch(`http://localhost:8084/civilizations/${id}`, {
        method: 'DELETE'
    })
}

$(document).on("click", "#buttonDelete", function () {
    $.ajax({
        url: '/civilizations/${item.id}',
        type: 'DELETE',
        success: function (result) {
            // Do something with the result
        }
    });
});