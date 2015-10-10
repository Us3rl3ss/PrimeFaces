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