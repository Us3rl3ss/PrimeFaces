//Datepicker lokalizacija:
PrimeFaces.locales ['hr'] = {
    closeText: 'Zatvori',
    prevText: 'Prethodni',
    nextText: 'Slijedeći',
    monthNames: ['Siječanj', 'Veljača', 'Ožujak', 'Travanj', 'Svibanj', 'Lipanj', 'Srpanj', 'Kolovoz', 'Rujan', 'Listopad', 'Studeni', 'Prosinac' ],
    monthNamesShort: ['Sij', 'Velj', 'Ožu', 'Tra', 'Svi', 'Lip', 'Srp', 'Kol', 'Ruj', 'Lis', 'Stu', 'Pro' ],
    dayNames: ['Nedjelja', 'Ponedjeljak', 'Utorak', 'Srijeda', 'Četvrtak', 'Petak', 'Subota'],
    dayNamesShort: ['Ne', 'Po', 'Ut', 'Sr', 'Če', 'Pe', 'Su'],
    dayNamesMin: ['N', 'P', 'U', 'S ', 'Č', 'P ', 'S'],
    weekHeader: 'Tjedan',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Samo vrijeme',
    timeText: 'Vrijeme',
    hourText: 'Sat',
    minuteText: 'Minuta',
    secondText: 'Sekunda',
    currentText: 'Sada',
    ampm: false,
    month: 'Mjesec',
    week: 'Tjedan',
    day: 'Dan',
    allDayText: 'Cijeli dan'
};


var ReserveSeats = {
	
		setSeats: function(data) {
			
			// selektor za disable svih već odabranih sjedala
			$($('#seats\\:cinemaSeats').find('div.ui-state-active')).addClass('ui-state-disabled');
			
			// selektor za razdvajanje ćelija
			$($('#seats\\:cinemaSeats').children()).css({'margin': '1em'});
		},
}

/**
 * UIBlocker - loader za jquery ajax request-ove
 */
var uiblocker = uiblocker || {};

uiblocker.timeoutId = null;
uiblocker.addClass = function addClass() {
	document.getElementsByTagName('body')[0].className = 'loading';
};

uiblocker.begin = function begin() {
	// This is the start of the AJAX request.
//	console.log($.now() + " - pocinje...");
	uiblocker.timeoutId = setTimeout(uiblocker.addClass, 500);
};

uiblocker.complete = function complete() {
	// This is invoked right after AJAX response is returned.
//	console.log($.now() + " - gotov dohvat podataka...");
};

uiblocker.success = function success() {
	// This is invoked right after successful processing of AJAX
	// response and update of HTML DOM.
//	console.log($.now() + " - kraj.");
	document.getElementsByTagName('body')[0].className = '';
	clearTimeout(uiblocker.timeoutId);
};

$(document).ready(function() {
//	console.log($.now() + " - DOM ready!!!");
//	$(window).on('beforeunload', function() {
//		return "Sve izmjene će biti izgubljene";
//	});

	// Form Submit
//	$(document).on("submit", "form", "href", function(event) {
//		// disable unload warning
//		$(window).off('beforeunload');
//	});
	
	$(document).on('pfAjaxStart', uiblocker.begin);
	$(document).on('pfAjaxComplete', uiblocker.complete);
	$(document).on('pfAjaxSuccess', uiblocker.success);
});