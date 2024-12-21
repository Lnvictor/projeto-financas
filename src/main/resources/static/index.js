function periodoChangeHandler() {
	let dateSelected = document.getElementById("date-picker").value;
	
	window.location = '/?dateSelected=' + dateSelected;
}

function compraOnclickHandle(compraId) {
	window.location = `/compras/details?secret=${compraId}`
}

function compraDeleteHandle() {
	setInterval(() => {
		document.forms.deleteForm.submit();	
	}, 10000);
}