(function($){
  $(function(){

    $('.button-collapse').sideNav();

    $('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year,
    today: 'Today',
    clear: 'Clear',
    close: 'Ok',
    closeOnSelect: false // Close upon selecting a date,
    });

    $('input.autocomplete').autocomplete({
    data: {
    },
    limit: 20, // The max amount of results that can be shown at once. Default: Infinity.
    onAutocomplete: function(val) {
      // Callback function when value is autcompleted.
    },
    minLength: 1, // The minimum length of the input for the autocomplete to start. Default: 1.
    });

    $('#autocomplete').on('input', function() {
      $('#start-button').prop('href', "hello?city="+this.value);
    });

  }); // end of document ready
})(jQuery); // end of jQuery name space
